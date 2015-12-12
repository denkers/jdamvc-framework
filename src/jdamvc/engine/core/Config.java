
package jdamvc.engine.core;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;


public class Config 
{
    //--------------------------------------------------------------------------
    //      APP CONFIG
    //--------------------------------------------------------------------------
    //Enabling debug mode will show all debug output messages
    public static boolean DEBUG_MODE;
    
    //Enabling GUI mode will show the applications GUI, disable for CUI
    public static boolean GUI_MODE;
    
    //The applications name shown on winodws, titles etc.
    public static String APP_NAME;
    
    //The width of the GUI window
    public static int WINDOW_WIDTH;
    
    //The height of the GUI window
    public static int WINDOW_HEIGHT;
    
    //The relative path to the apps images directory
    public static String RESOURCE_DIR;
    
    //Enabling will allow users to save credentials
    public static boolean ALLOW_CRED_SAVE;
    
    //The path to the user stored credentials file
    public static String CRED_SAVE_FILE;
    
    //Notifications are checked and updated every NOTIFICATION_TIME ms
    public static int NOTIFICATION_TIME;
    
    //Enable to show coloured text in the CUI
    //Colours have only been tested on linux
    public static boolean CUI_COLOURS;
    //--------------------------------------------------------------------------
    
    
    
    //--------------------------------------------------------------------------
    //      AUTH CONFIG
    //--------------------------------------------------------------------------
    //The table name used for user authentication
    public static String AUTH_TABLE;
    
    //The hashing algorithm used for passwords
    public static String HASH_ALGORITHM;
    
    //A prefix used for the salt in the password hash
    public static String SALT_PREFIX;
    
    //The encoding format used in hashing
    public static String ENC_FORMAT;
    
    //The primary key column name in the auth table
    public static String USERNAME_COL;
    
    //The password column in the auth table
    public static String PASSWORD_COL;
    //--------------------------------------------------------------------------
    
    
    
    
    //--------------------------------------------------------------------------
    //      DATABASE CONFIG   
    //--------------------------------------------------------------------------
    //database server address
    public static String SERVER;
    
    //database port - default: 1527
    public static  int PORT;
    
     //database server username
    public static String DB_USERNAME;
    
    //database server password
    public static String DB_PASSWORD;
    
    //active live database name
    public static String DATABASE; 
    
    //the test database name
    public static String TEST_DATABASE;
    
    //db connection driver
    public static String DRIVER;
    
    //the default/using schema
    public static String SCHEMA;
    
    //the default primary key used 
    public static  String DEFAULT_KEY;
    //--------------------------------------------------------------------------
    
    
    
    
    //--------------------------------------------------------------------------
    //      LOGGING CONFIG
    //--------------------------------------------------------------------------
    //enables/disables logging of all admin activities in system
    public static boolean ENABLE_AUTH_LOG;
    
    //enables/disables logging of user authentication and access
    public static boolean ENABLE_ADMIN_LOG;
    
    //enables/disable logging of all database queries from server
    public static  boolean ENABLE_DATA_LOG;
    
    //enables/disable logging of debug messages
    public static boolean ENABLE_DEBUG_LOG;
    
    //set the max size in bytes for each log file
    public static int LOG_MAX_SIZE;
    
    //set the logs directory
    public static String LOG_PATH;
    
    //set the max number of log files created after hitting max size
    public static int LOG_FILE_MAX_COUNT;
    
    //set the default log extension
    public static String LOG_FILE_EXT;
   //--------------------------------------------------------------------------
    
    
    
    private static Document getDocument(String path)
    {
        try
        {
            File configFile             =   new File(Config.class.getResource(path).getFile());
            DocumentBuilder dBuilder    =   DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc                =   dBuilder.parse(configFile);
            doc.getDocumentElement().normalize();
            return doc;
        }
        
        catch(ParserConfigurationException | SAXException | IOException e)
        {
            return null;
        }
    }
    
    public static boolean nodeBoolValue(String value)
    {
        return value.equalsIgnoreCase("true") || value.equals("1");
    }
    
    public static int nodeIntValue(String value)
    {
        try
        {
            return Integer.parseInt(value);
        }
        
        catch(NumberFormatException e)
        {
            return 0;
        }
    }
    
    private static void initAppConfig()
    {
        String path         =   "application/config/AppConfig.xml";
        Document doc        =   getDocument(path);
        
        DEBUG_MODE          =   nodeBoolValue(doc.getElementsByTagName("debugMode").item(0).getTextContent());
        GUI_MODE            =   nodeBoolValue(doc.getElementsByTagName("guiMode").item(0).getTextContent());
        APP_NAME            =   doc.getElementsByTagName("appName").item(0).getTextContent();
        WINDOW_WIDTH        =   nodeIntValue(doc.getElementsByTagName("windowWidth").item(0).getTextContent());
        WINDOW_HEIGHT       =   nodeIntValue(doc.getElementsByTagName("windowHeight").item(0).getTextContent());
        RESOURCE_DIR        =   doc.getElementsByTagName("resourceDirectory").item(0).getTextContent();
        ALLOW_CRED_SAVE     =   nodeBoolValue(doc.getElementsByTagName("saveCredentials").item(0).getTextContent());
        CRED_SAVE_FILE      =   doc.getElementsByTagName("credentialsFile").item(0).getTextContent();
        NOTIFICATION_TIME   =   nodeIntValue(doc.getElementsByTagName("notificationTime").item(0).getTextContent());
        CUI_COLOURS         =   nodeBoolValue(doc.getElementsByTagName("cuiColors").item(0).getTextContent());
    }
    
    private static void initAuthConfig()
    {
        String path         =   "application/config/AuthConfig.xml";
        Document doc        =   getDocument(path);
        
        AUTH_TABLE          =   doc.getElementsByTagName("authTable").item(0).getTextContent();
        HASH_ALGORITHM      =   doc.getElementsByTagName("hashAlgorithm").item(0).getTextContent();
        SALT_PREFIX         =   doc.getElementsByTagName("saltPrefix").item(0).getTextContent();
        ENC_FORMAT          =   doc.getElementsByTagName("encFormat").item(0).getTextContent();
        USERNAME_COL        =   doc.getElementsByTagName("usernameColumn").item(0).getTextContent();
        PASSWORD_COL        =   doc.getElementsByTagName("passwordColumn").item(0).getTextContent();
    }
    
    private static void initDatabaseConfig()
    {
        String path     =   "application/config/DatabaseConfig.xml";
        Document doc    =   getDocument(path);
    }
    
    private static void initLoggingConfig()
    {
        String path     =   "application/config/LoggingConifg.xml";
        Document doc    =   getDocument(path);
    }
    
    public static void initConfig()
    {
        initAppConfig();
        initAuthConfig();
        initDatabaseConfig();
        initLoggingConfig();
    }   
}
