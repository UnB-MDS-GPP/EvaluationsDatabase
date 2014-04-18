package libraries;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.ArrayList;

public class GenericCRUDModel extends DataBaseConnection{
	public GenericCRUDModel() throws SQLException, ClassNotFoundException {
		super();
	}
	
	public GenericCRUDModel(String dataBaseName) throws SQLException, ClassNotFoundException {
		super(dataBaseName);
	}


	public boolean insert(String tableName, Hashtable<String, String> tableData) throws SQLException {
		String key, sqlKeys="", sqlValues="";

		Enumeration<String> keys = tableData.keys();
		while(keys.hasMoreElements()) {
			key = keys.nextElement().toString();
			sqlKeys += "'"+key+"',";
			sqlValues += "'"+tableData.get(key)+"',";
		}

		sqlKeys = sqlKeys.substring(0, sqlKeys.length()-1);
		sqlValues = sqlValues.substring(0, sqlValues.length()-1);

		String sql = "INSERT INTO "+tableName+"("+sqlKeys+") VALUES("+sqlValues+")";

		this.openConnection();
		boolean result = this.stm.execute(sql);
		this.closeConnection();

		return result;
	}


	public boolean delete(String tableName, int id) throws SQLException {
		String sql = "DELETE FROM "+tableName+" WHERE id="+Integer.toString(id);

		this.openConnection();
		boolean result = this.stm.execute(sql);
		this.closeConnection();

		return result;
	}


	public boolean update(String tableName, Hashtable<String, String> tableData, int id) throws SQLException {
		String sql = "UPDATE "+tableName+" SET ";
		String key;

		Enumeration<String> keys = tableData.keys();
		while(keys.hasMoreElements()) {
			key = keys.nextElement().toString();
			sql += key+"='"+tableData.get(key)+"',";
		}
		sql = sql.substring(0, sql.length()-1);
		sql += " WHERE id="+Integer.toString(id);
		
		this.openConnection();
		boolean result = this.stm.execute(sql);
		this.closeConnection();

		return result;
	}


	public ArrayList<Hashtable<String, String>> select(String tableName, ArrayList<String> fields, int id) {
		if(fields.size() == 0)
			return null;

		int i;
		ResultSet rs;
		String sql = "SELECT ";

		ArrayList<Hashtable<String, String>> rows = new ArrayList<Hashtable<String, String>>();
		Hashtable<String, String> fieldsData;

		for (i = 0; i < fields.size(); i++) {
			sql += fields.get(i).toString()+",";
		}

		sql = sql.substring(0, sql.length()-1);
		sql += " FROM '"+tableName+"'";

		if(id > 0)
			sql += " WHERE id="+Integer.toString(id);

		try {
			this.openConnection();
            rs = this.stm.executeQuery(sql);

            while (rs.next()) {
                fieldsData = new Hashtable<String, String>();

                for(i=0; i < fields.size(); i++)
                    fieldsData.put(fields.get(i), rs.getString(fields.get(i)));

                rows.add(fieldsData);
            }

            rs.close();
            this.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

		return rows;
	}


	public ArrayList<Hashtable<String, String>> select(String tableName, ArrayList<String> fields) {
		return this.select(tableName, fields, 0);
	}


	public int count(String tableName) {
		String sql = "select count(*) from "+tableName;
		int result = 0;

		try {
			this.openConnection();
			ResultSet rs = this.stm.executeQuery(sql);
			result = rs.getInt(1);
			this.closeConnection();
		} catch(Exception e) {
			e.printStackTrace();
		}

		return result;
	}
}
