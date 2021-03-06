
package jdamvc.engine.core;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.w3c.dom.Document;


public class Config 
{
    //--------------------------------------------------------------------------
    //      APP CONFIG
    //--------------------------------------------------------------------------
    public static String PACK_NAME;
    
    //Enabling debug mode will show all debug output messages
    public static boolean DEBUG_MODE;   
    
    //The applications name shown on winodws, titles etc.
    public static String APP_NAME;
    
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
    //      LAYOUT CONFIG
    //--------------------------------------------------------------------------
    //Enabling GUI mode will show the applications GUI, disable for CUI
    public static boolean GUI_MODE;
    
    //The width of the GUI window
    public static int WINDOW_WIDTH;
    
    //The height of the GUI window
    public static int WINDOW_HEIGHT;
    
    //The relative path to the apps images directory
    public static String RESOURCE_DIR;
    
    //The route name for the error view 
    public static String ERROR_VIEW_ROUTE;
    
    //The route name for the transition view
    public static String TRANS_VIEW_ROUTE;
    
    //Enables/disables resizing of the window
    public static boolean WINDOW_RESIZE;
    
    //Centres the location of the window
    public static boolean WINDOW_CENTRE;
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
    
    static
    {
        initBuildProperties();
        initConfig();
    }
    
    private static void initBuildProperties()
    {
        try
        {
            String innerPath    =   "jdamvc.properties";
            InputStream is      =   Config.class.getResourceAsStream(innerPath);
            Properties prop     =   new Properties();
            prop.load(is);
            
            PACK_NAME           =   prop.getProperty("app.package");
        }
        
        catch(IOException e)
        {
            PACK_NAME   =   "";
        }
    }
    
    private static void initAppConfig()
    {
        String path         =   PACK_NAME + "/config/AppConfig.xml";
        Document doc        =   XMLParser.getDocument(path);
        
        DEBUG_MODE          =   XMLParser.nodeBoolValue(doc.getElementsByTagName("debugMode").item(0).getTextContent());
        APP_NAME            =   doc.getElementsByTagName("appName").item(0).getTextContent();
        ALLOW_CRED_SAVE     =   XMLParser.nodeBoolValue(doc.getElementsByTagName("saveCredentials").item(0).getTextContent());
        CRED_SAVE_FILE      =   doc.getElementsByTagName("credentialsFile").item(0).getTextContent();
        NOTIFICATION_TIME   =   XMLParser.nodeIntValue(doc.getElementsByTagName("notificationTime").item(0).getTextContent());
        CUI_COLOURS         =   XMLParser.nodeBoolValue(doc.getElementsByTagName("cuiColors").item(0).getTextContent());
    }
    
    private static void initLayoutConfig()
    {
        String path         =   PACK_NAME + "config/LayoutConfig.xml";
        Document doc        =   XMLParser.getDocument(path);
        
        GUI_MODE            =   XMLParser.nodeBoolValue(doc.getElementsByTagName("guiMode").item(0).getTextContent());
        WINDOW_WIDTH        =   XMLParser.nodeIntValue(doc.getElementsByTagName("windowWidth").item(0).getTextContent());
        WINDOW_HEIGHT       =   XMLParser.nodeIntValue(doc.getElementsByTagName("windowHeight").item(0).getTextContent());
        RESOURCE_DIR        =   doc.getElementsByTagName("resourceDirectory").item(0).getTextContent();
        ERROR_VIEW_ROUTE    =   doc.getElementsByTagName("errorRoute").item(0).getTextContent();
        TRANS_VIEW_ROUTE    =   doc.getElementsByTagName("transitionRoute").item(0).getTextContent();
        WINDOW_RESIZE       =   XMLParser.nodeBoolValue(doc.getElementsByTagName("windowResizable").item(0).getTextContent());
        WINDOW_CENTRE       =   XMLParser.nodeBoolValue(doc.getElementsByTagName("centreWindow").item(0).getTextContent());
    }
    
    private static void initAuthConfig()
    {
        String path         =   PACK_NAME + "/config/AuthConfig.xml";
        Document doc        =   XMLParser.getDocument(path);
        
        AUTH_TABLE          =   doc.getElementsByTagName("authTable").item(0).getTextContent();
        HASH_ALGORITHM      =   doc.getElementsByTagName("hashAlgorithm").item(0).getTextContent();
        SALT_PREFIX         =   doc.getElementsByTagName("saltPrefix").item(0).getTextContent();
        ENC_FORMAT          =   doc.getElementsByTagName("encFormat").item(0).getTextContent();
        USERNAME_COL        =   doc.getElementsByTagName("usernameColumn").item(0).getTextContent();
        PASSWORD_COL        =   doc.getElementsByTagName("passwordColumn").item(0).getTextContent();
    }
    
    
    private static void initDatabaseConfig()
    {
        String path         =   PACK_NAME + "/config/DatabaseConfig.xml";
        Document doc        =   XMLParser.getDocument(path);
        
        SERVER              =   doc.getElementsByTagName("server").item(0).getTextContent();
        PORT                =   XMLParser.nodeIntValue(doc.getElementsByTagName("port").item(0).getTextContent());
        DB_USERNAME         =   doc.getElementsByTagName("username").item(0).getTextContent();
        DB_PASSWORD         =   doc.getElementsByTagName("password").item(0).getTextContent();
        DATABASE            =   doc.getElementsByTagName("database").item(0).getTextContent();
        TEST_DATABASE       =   doc.getElementsByTagName("testDatabase").item(0).getTextContent();
        DRIVER              =   doc.getElementsByTagName("driver").item(0).getTextContent();
        SCHEMA              =   doc.getElementsByTagName("schema").item(0).getTextContent();
        DEFAULT_KEY         =   doc.getElementsByTagName("defaultKeyName").item(0).getTextContent();
    }
    
    
    private static void initLoggingConfig()
    {
        String path         =   PACK_NAME + "/config/LoggingConifg.xml";
        Document doc        =   XMLParser.getDocument(path);
        
        ENABLE_AUTH_LOG     =   XMLParser.nodeBoolValue(doc.getElementsByTagName("enableAuthLog").item(0).getTextContent());
        ENABLE_ADMIN_LOG    =   XMLParser.nodeBoolValue(doc.getElementsByTagName("enableAdminLog").item(0).getTextContent());
        ENABLE_DATA_LOG     =   XMLParser.nodeBoolValue(doc.getElementsByTagName("enableDataLog").item(0).getTextContent());
        ENABLE_DEBUG_LOG    =   XMLParser.nodeBoolValue(doc.getElementsByTagName("enableDebugLog").item(0).getTextContent());
        LOG_MAX_SIZE        =   XMLParser.nodeIntValue(doc.getElementsByTagName("maxLogSize").item(0).getTextContent());
        LOG_PATH            =   doc.getElementsByTagName("logPath").item(0).getTextContent();
        LOG_FILE_MAX_COUNT  =   XMLParser.nodeIntValue(doc.getElementsByTagName("logCount").item(0).getTextContent());
        LOG_FILE_EXT        =   doc.getElementsByTagName("logExtension").item(0).getTextContent();
    }
    
    public static void initConfig()
    {
        initAppConfig();
        initLayoutConfig();
        initAuthConfig();
        initDatabaseConfig();
        initLoggingConfig();
    }   
}
