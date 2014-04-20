package libraries;

import java.sql.*;
import java.util.Properties;

import org.sqlite.SQLiteConfig;

public abstract class DataBaseConnection {
	private String dataBaseName;
    protected Connection conn;
    protected PreparedStatement pst;
    protected Statement stm;
    private Properties connectionProperties;

    public DataBaseConnection() throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        this.dataBaseName = "jdbc:sqlite:test.sqlite3.db";
        SQLiteConfig config = new SQLiteConfig();
		config.enforceForeignKeys(true);
        connectionProperties = config.toProperties();
    }

    public DataBaseConnection(String dataBaseName) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        this.dataBaseName = "jdbc:sqlite:"+dataBaseName+".sqlite3.db";
        SQLiteConfig config = new SQLiteConfig();
		config.enforceForeignKeys(true);
        connectionProperties = config.toProperties();
    }

    protected void openConnection() {
    	try {
    		this.conn = DriverManager.getConnection(this.dataBaseName,this.connectionProperties);
    		this.stm = conn.createStatement();
    	} catch(SQLException e) {
    		e.printStackTrace();
    	}
    }
    
    protected void closeConnection() {
    	try {
    		this.conn.close();
    	} catch(SQLException e) {
    		e.printStackTrace();
    	}
    }
}