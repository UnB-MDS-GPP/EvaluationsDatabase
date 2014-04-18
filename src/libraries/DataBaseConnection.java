package libraries;

import java.sql.*;

public abstract class DataBaseConnection {
	private String dataBaseName;
    protected Connection conn;
    protected Statement stm;

    public DataBaseConnection() throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        this.dataBaseName = "jdbc:sqlite:test.sqlite3.db";
    }

    public DataBaseConnection(String dataBaseName) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        this.dataBaseName = "jdbc:sqlite:"+dataBaseName+".sqlite3.db";
    }

    protected void openConnection() {
    	try {
    		this.conn = DriverManager.getConnection(this.dataBaseName);
            this.stm = this.conn.createStatement();
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