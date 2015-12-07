//====================================
//	Kyle Russell
//	jdamvc
//	DatabaseConfig
//====================================

package engine.config;

public class DatabaseConfig
{ 
    //-------------------------------------------------------
    //                      DATABASE CONFIG
    //-------------------------------------------------------
    
    //database server address
    public static final String SERVER           =   "localhost";
    
    //database port - default: 1527
    public static final int PORT                =   1527;
    
     //database server username
    public static final String DB_USERNAME      =   "username"
    
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
}
