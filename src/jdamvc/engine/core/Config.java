
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
    public static final String AUTH_TABLE           =   "users";
    
    //The hashing algorithm used for passwords
    public static final String HASH_ALGORITHM       =   "SHA-1";
    
    //A prefix used for the salt in the password hash
    public static final String SALT_PREFIX          =   "vb+FwcR~Sj+bq5imRBJd3%L";
    
    //The encoding format used in hashing
    public static final String ENC_FORMAT           =   "utf-8";
    
    //The primary key column name in the auth table
    public static final String USERNAME_COL         =   "username";
    
    //The password column in the auth table
    public static final String PASSWORD_COL         =   "password";
    //--------------------------------------------------------------------------
    
    
    
    
    //--------------------------------------------------------------------------
    //      DATABASE CONFIG   
    //--------------------------------------------------------------------------
    //database server address
    public static final String SERVER           =   "localhost";
    
    //database port - default: 1527
    public static final int PORT                =   1527;
    
     //database server username
    public static final String DB_USERNAME      =   "username";
    
    //database server password
    public static final String DB_PASSWORD      =   "password";
    
    //active live database name
    public static final String DATABASE         =   "production_db"; 
    
    //the test database name
    public static final String TEST_DATABASE    =   "testing_db"; 
    
    //db connection driver
    public static final String DRIVER           =   "jdbc:derby"; 
    
    //the default/using schema
    public static final String SCHEMA           =   "APP"; 
    
    //the default primary key used 
    public static final String DEFAULT_KEY      =   "id";
    //--------------------------------------------------------------------------
    
    
    
    
    //--------------------------------------------------------------------------
    //      LOGGING CONFIG
    //--------------------------------------------------------------------------
    //enables/disables logging of all admin activities in system
    public static final boolean ENABLE_AUTH_LOG       =       true;
    
    //enables/disables logging of user authentication and access
    public static final boolean ENABLE_ADMIN_LOG      =       true;
    
    //enables/disable logging of all database queries from server
    public static final boolean ENABLE_DATA_LOG       =       true;
    
    //enables/disable logging of debug messages
    public static final boolean ENABLE_DEBUG_LOG      =       true;
    
    //set the max size in bytes for each log file
    public static final int LOG_MAX_SIZE              =       5242880;
    
    //set the logs directory
    public static final String LOG_PATH               =       "data/logs/";
    
    //set the max number of log files created after hitting max size
    public static final int LOG_FILE_MAX_COUNT        =       1;
    
    //set the default log extension
    public static final String LOG_FILE_EXT           =       ".log";
   //--------------------------------------------------------------------------
    
    private static Document getDocument(String path)
    {
        try
        {
            File configFile             =   new File(Config.class.getResource(path).getFile());
            DocumentBuilder dBuilder    =   DocumentBuilderFactory.newInstance().newDocumentBuilder();
            return dBuilder.parse(configFile);
        }
        
        catch(ParserConfigurationException | SAXException | IOException e)
        {
            return null;
        }
    }
    
    private static void initAppConfig()
    {
        String path     =   "application/config/AppConfig.xml";
        Document doc    =   getDocument(path);
        
    }
    
    private static void initAuthConfig()
    {
        String path     =   "application/config/AuthConfig.xml";
        Document doc    =   getDocument(path);
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
