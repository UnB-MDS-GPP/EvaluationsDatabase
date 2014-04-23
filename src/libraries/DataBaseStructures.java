package libraries;

import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseStructures extends DataBaseConnection {
	
	Statement stm;
	
    public DataBaseStructures()  throws SQLException, ClassNotFoundException {
        super();
        
    }


    public void initDB() throws SQLException{

            this.openConnection();
            this.stm = this.conn.createStatement();
            this.buildTableArticles();
            this.buildTableEvaluation();
            this.buildTableCourse();
            this.buildTableCoursesInstitutions();
            this.buildTableInstitution();
            this.buildTableBooks();

            this.closeConnection();
    }

    public void dropDB() throws SQLException {
    	this.openConnection();
    	this.stm = this.conn.createStatement();
        this.stm.execute("DROP TABLE IF EXISTS 'course'");
        this.stm.execute("DROP TABLE IF EXISTS 'institution'");
        this.stm.execute("DROP TABLE IF EXISTS 'courses_institutions'");
        this.stm.execute("DROP TABLE IF EXISTS 'articles'");
        this.stm.execute("DROP TABLE IF EXISTS 'books'");
        this.stm.execute("DROP TABLE IF EXISTS 'evaluation'");

        this.closeConnection();
    }

    private void buildTableCourse() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS 'course' (" +
                "'id' INTEGER PRIMARY KEY AUTOINCREMENT," +
    			"'name' TEXT NOT NULL)";
    	this.stm.execute(sql);
    }

    private void buildTableInstitution() throws SQLException {
    	String sql = "CREATE TABLE IF NOT EXISTS 'institution' (" +
    		    "'id' INTEGER PRIMARY KEY AUTOINCREMENT,"+
    		    "'acronym' TEXT NOT NULL)";
    	this.stm.execute(sql);
    }

    /*
     * FIXME add foreign key support to database. E.g.:
     * FOREIGN KEY(id_instituicao) REFERENCES institution(id)
     */
    private void buildTableCoursesInstitutions() throws SQLException {
    	String sql = "CREATE TABLE IF NOT EXISTS 'courses_institutions' (" +
    				"'id_institution' INTEGER NOT NULL," +
    				"'id_course' INTEGER NOT NULL)";
    	this.stm.execute(sql);
    }

    private void buildTableArticles() throws SQLException {
    	String sql = "CREATE TABLE IF NOT EXISTS 'articles' (" +
    		    "'id' INTEGER PRIMARY KEY AUTOINCREMENT," +
    		    "'internationals' INTEGER," +
    		    "'nationals' INTEGER," +
    		    "'locals' INTEGER)";
    	this.stm.execute(sql);
    }

    private void buildTableBooks() throws SQLException {
    	String sql = "CREATE TABLE IF NOT EXISTS 'books' (" +
    		    "'id' INTEGER PRIMARY KEY AUTOINCREMENT," +
    		    "'integral_text' INTEGER," +
    		    "'chapters' INTEGER," +
    		    "'collections' INTEGER," +
    		    "'entries' INTEGER)";
    	this.stm.execute(sql);
    }

    /*
     * FIXME add foreign key support to database. E.g.:
     * FOREIGN KEY(id_artigos) REFERENCES articles(id)
     */
    private void buildTableEvaluation() throws SQLException {
    	String sql = "CREATE TABLE IF NOT EXISTS 'evaluation' (" +
    		    "'id' INTEGER PRIMARY KEY AUTOINCREMENT," +
    		    "'year' INTEGER NOT NULL," +
    		    "'modality' TEXT NOT NULL," +
    		    "'master_degree_start_year' INTEGER," +
    		    "'doctorate_start_year' INTEGER," +
    		    "'triennial_evaluation' INTEGER NOT NULL," +
    		    "'permanent_teachers' INTEGER," +
    		    "'theses' INTEGER," +
    		    "'dissertations' INTEGER," +
    		    "'id_articles' INTEGER NOT NULL," +
    		    "'id_books' INTEGER," +
    		    "'published_works' INTEGER," +
    		    "'artistic_production' INTEGER)";
    	this.stm.execute(sql);
    }
}
