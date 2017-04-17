
package persistence;


public class DbAccessConfiguration {
	
	static final String DRIVE_NAME = "com.mysql.jdbc.Driver";
        
    static final String DATABASE_NAME = "compare_cabins";
	
	static final String CONNECTION_URL = "jdbc:mysql://localhost:3306/" + DATABASE_NAME + "?autoReconnect=true&useSSL=false";
	
	static final String DB_CONNECTION_USERNAME = "root";
	
	static final String DB_CONNECTION_PASSWORD = "sesame";
	


}

