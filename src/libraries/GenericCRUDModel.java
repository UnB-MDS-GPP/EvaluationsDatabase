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
			sqlValues += "?,";
		}
		sqlKeys = sqlKeys.substring(0, sqlKeys.length()-1);
		sqlValues = sqlValues.substring(0, sqlValues.length()-1);

		String sql = "INSERT INTO "+tableName+"("+sqlKeys+") VALUES("+sqlValues+")";

		boolean result  = this.executePreparedStatement(sql,new ArrayList<String>(tableData.values()));
		
		return result;
	}


	public boolean delete(String tableName, int id) throws SQLException {
		String sql = "DELETE FROM "+tableName+" WHERE id=?";
		
		boolean result = this.executePreparedStatement(sql,Integer.toString(id));
		
		return result;
	}


	public boolean update(String tableName, Hashtable<String, String> tableData, int id) throws SQLException {
		String sql = "UPDATE "+tableName+" SET ";
		String key;

		Enumeration<String> keys = tableData.keys();
		while(keys.hasMoreElements()) {
			key = keys.nextElement().toString();
			sql += key+"=?,";
		}
		sql = sql.substring(0, sql.length()-1);
		sql += " WHERE id=?";
		
		boolean result = this.executePreparedStatement(sql, new ArrayList<>(tableData.values()), Integer.toString(id));
		
		return result;
	}


	public ArrayList<Hashtable<String, String>> select(String tableName, ArrayList<String> fields, int id) {
		if(fields.size() == 0)
			return null;

		int i;
		ResultSet rs;
		String sql = this.fieldsSelectSql(tableName, fields);

		ArrayList<Hashtable<String, String>> rows = new ArrayList<Hashtable<String, String>>();
		Hashtable<String, String> fieldsData;

		if(id > 0)
			sql += " WHERE id=?";

		try {
			this.openConnection();
			this.pst = this.conn.prepareStatement(sql);
			if(id > 0)
				this.pst.setInt(1, id);
            rs = this.pst.executeQuery();

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


	public ArrayList<Hashtable<String, String>> selectWhere(String tableName, ArrayList<String> fields, String conditions) {
		if(fields.size() == 0)
			return null;

		int i;
		ResultSet rs;
		String sql = this.fieldsSelectSql(tableName, fields);

		ArrayList<Hashtable<String, String>> rows = new ArrayList<Hashtable<String, String>>();
		Hashtable<String, String> fieldsData;
		
		sql += " WHERE "+conditions;
		
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


	public Hashtable<String, String> firstOrLast(String tableName, ArrayList<String> fields, boolean last) {
		if(fields.size() == 0)
			return null;

		Hashtable<String, String> result = new Hashtable<String, String>();
		ResultSet rs;
		String sql = this.fieldsSelectSql(tableName, fields) + " ORDER BY id";

		if( last == true )
			sql += " DESC";

		sql += " LIMIT 1";

		try {
			this.openConnection();
			rs = this.stm.executeQuery(sql);

			for(int i=0; i < fields.size(); i++)
				result.put(fields.get(i), rs.getString(fields.get(i)));

			this.closeConnection();
		} catch(Exception e) {
			e.printStackTrace();
		}

		return result;
	}


	private String fieldsSelectSql(String tableName, ArrayList<String> fields) {
		String sql = "SELECT ";
		int i;

		for (i = 0; i < fields.size(); i++) {
			sql += fields.get(i).toString()+",";
		}

		sql = sql.substring(0, sql.length()-1);
		sql += " FROM '"+tableName+"' ";

		return sql;
	}
	
	private boolean executePreparedStatement(String sql, ArrayList<String> data) throws SQLException{
		this.openConnection();
		this.pst = conn.prepareStatement(sql);
		for(int i = 0; i < data.size(); i++){
			this.pst.setString(i+1, data.get(i));
		}
		boolean result = this.pst.execute();
		this.closeConnection();
		return result;
	}
	
	private boolean executePreparedStatement(String sql, ArrayList<String> data , String condition) throws SQLException{
		this.openConnection();
		this.pst = conn.prepareStatement(sql);
		data.add(condition);
		for(int i = 0; i < data.size(); i++){
			this.pst.setString(i+1, data.get(i));
		}
		boolean result = this.pst.execute();
		this.closeConnection();
		return result;
	}
	
	private boolean executePreparedStatement(String sql, String data) throws SQLException{
		this.openConnection();
		this.pst = conn.prepareStatement(sql);
		this.pst.setString(1, data);
		boolean result = this.pst.execute();
		this.closeConnection();
		return result;
	}
}
