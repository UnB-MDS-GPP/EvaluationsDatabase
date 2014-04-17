package libraries;

import java.sql.*;

public abstract class DataBaseConnection {
    protected Connection conn;
    protected Statement stm;

    public DataBaseConnection() throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        this.conn = DriverManager.getConnection("jdbc:sqlite:test.sqlite3.db");
        this.stm = this.conn.createStatement();
    }

    public DataBaseConnection(String dataBaseName) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        this.conn = DriverManager.getConnection("jdbc:sqlite:"+dataBaseName+".sqlite3.db");
        this.stm = this.conn.createStatement();
    }

}