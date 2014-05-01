package libraries;

import java.sql.*;
import java.util.Properties;

import org.sqlite.SQLiteConfig;

public abstract class DataBaseConnection {
	private String dataBaseName;
    protected Connection conn;
    protected PreparedStatement pst;
    private Properties connectionProperties;

    public DataBaseConnection() throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        this.dataBaseName = "jdbc:sqlite:database.sqlite3.db";
        SQLiteConfig config = new SQLiteConfig();
		config.enforceForeignKeys(true);
        connectionProperties = config.toProperties();
    }

    protected void openConnection() throws SQLException{
    		this.conn = DriverManager.getConnection(this.dataBaseName,this.connectionProperties);
    }
    
    protected void closeConnection() throws SQLException{
    		this.conn.close();
    }
}