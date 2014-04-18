package libraries;

import java.sql.SQLException;

public class DataBaseStructures extends DataBaseConnection {
    public DataBaseStructures()  throws SQLException, ClassNotFoundException {
        super();
    }
    public DataBaseStructures(String dataBaseName)  throws SQLException, ClassNotFoundException {
        super(dataBaseName);
    }

    public void initDB() {
        try {
            this.openConnection();

            this.buildTableArtigos();
            this.buildTableAvaliacoes();
            this.buildTableCurso();
            this.buildTableCursosInstituicoes();
            this.buildTableInstituicao();
            this.buildTableLivros();

            this.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropDB() throws SQLException {
    	this.openConnection();

        this.stm.execute("DROP TABLE IF EXISTS 'curso'");
        this.stm.execute("DROP TABLE IF EXISTS 'instituicao'");
        this.stm.execute("DROP TABLE IF EXISTS 'cursos_instituicoes'");
        this.stm.execute("DROP TABLE IF EXISTS 'artigos'");
        this.stm.execute("DROP TABLE IF EXISTS 'livros'");
        this.stm.execute("DROP TABLE IF EXISTS 'avaliacoes'");

        this.closeConnection();
    }

    private void buildTableCurso() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS 'curso' (" +
                "'id' INTEGER PRIMARY KEY AUTOINCREMENT," +
    			"'nome' TEXT NOT NULL)";
    	this.stm.execute(sql);
    }

    private void buildTableInstituicao() throws SQLException {
    	String sql = "CREATE TABLE IF NOT EXISTS 'instituicao' (" +
    		    "'id' INTEGER PRIMARY KEY AUTOINCREMENT,"+
    		    "'sigla' TEXT NOT NULL)";
    	this.stm.execute(sql);
    }

    private void buildTableCursosInstituicoes() throws SQLException {
    	String sql = "CREATE TABLE IF NOT EXISTS 'cursos_instituicoes' (" +
    				"'id' INTEGER PRIMARY KEY AUTOINCREMENT," +
    				"'id_instituicao' INTEGER NOT NULL," +
    				"'id_curso' INTEGER NOT NULL," +
    				"'id_avaliacoes' INTEGER NOT NULL)";
    	this.stm.execute(sql);
    }

    private void buildTableArtigos() throws SQLException {
    	String sql = "CREATE TABLE IF NOT EXISTS 'artigos' (" +
    		    "'id' INTEGER PRIMARY KEY AUTOINCREMENT," +
    		    "'internacionais' INTEGER," +
    		    "'nacionais' INTEGER," +
    		    "'locais' INTEGER)";
    	this.stm.execute(sql);
    }

    private void buildTableLivros() throws SQLException {
    	String sql = "CREATE TABLE IF NOT EXISTS 'livros' (" +
    		    "'id' INTEGER PRIMARY KEY AUTOINCREMENT," +
    		    "'texto_integral' INTEGER," +
    		    "'capitulos' INTEGER," +
    		    "'coletanias' INTEGER," +
    		    "'verbetes' INTEGER)";
    	this.stm.execute(sql);
    }

    private void buildTableAvaliacoes() throws SQLException {
    	String sql = "CREATE TABLE IF NOT EXISTS 'avaliacoes' (" +
    		    "'id' INTEGER PRIMARY KEY AUTOINCREMENT," +
    		    "'ano' INTEGER NOT NULL," +
    		    "'modalidade' TEXT NOT NULL," +
    		    "'ano_de_inicio_mestrado' INTEGER," +
    		    "'ano_de_inicio_doutorado' INTEGER," +
    		    "'avaliacao_trienal' INTEGER NOT NULL," +
    		    "'docentes_permanentes' INTEGER," +
    		    "'teses' INTEGER," +
    		    "'dissertacoes' INTEGER," +
    		    "'id_artigos' INTEGER NOT NULL," +
    		    "'id_livros' INTEGER," +
    		    "'trabalhos_publicados' INTEGER," +
    		    "'producao_artistica' INTEGER)";
    	this.stm.execute(sql);
    }
}
