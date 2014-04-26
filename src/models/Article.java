package models;

import java.sql.SQLException;
import java.util.ArrayList;

public class Article extends Bean {
	private int id;
	private int articlesPublishedJournals;
	private int articlesPublishedConferenceProceedings;

	public Article() {
		this.id = 0;
		this.identifier = "articles";
		this.relationship = "";
	}

	public Article(Integer id) {
		this.id = id;
		this.identifier = "articles";
		this.relationship = "";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getArticlesPublishedJournals() {
		return articlesPublishedJournals;
	}

	public void setArticlesPublishedJournals(int articlesPublishedJournals) {
		this.articlesPublishedJournals = articlesPublishedJournals;
	}

	public int getArticlesPublishedConferenceProceedings() {
		return articlesPublishedConferenceProceedings;
	}

	public void setArticlesPublishedConferenceProceedings(
			int articlesPublishedConferenceProceedings) {
		this.articlesPublishedConferenceProceedings = articlesPublishedConferenceProceedings;
	}


	public boolean save() throws ClassNotFoundException, SQLException {
		boolean result = false;
		GenericBeanDAO gDB = new GenericBeanDAO();
		result = gDB.insertBean(this);
		this.setId(Article.last().getId());
		return result;
	}

	public static Article get(Integer id) throws ClassNotFoundException, SQLException {
		Article result = new Article(id);
		GenericBeanDAO gDB = new GenericBeanDAO();
		result = (Article) gDB.selectBean(result);
		return result;
	}

	public static ArrayList<Article> getAll()
			throws ClassNotFoundException, SQLException {
		Article type = new Article();
		ArrayList<Article> result = new ArrayList<Article>();
		GenericBeanDAO gDB = new GenericBeanDAO();
		for (Bean b : gDB.selectAllBeans(type)) {
			result.add((Article) b);
		}
		return result;
	}

	public static int count() throws ClassNotFoundException, SQLException {
		Article type = new Article();
		GenericBeanDAO gDB = new GenericBeanDAO();
		return gDB.countBean(type);
	}

	public static Article first() throws ClassNotFoundException,
			SQLException {
		Article result = new Article();
		GenericBeanDAO gDB = new GenericBeanDAO();
		result = (Article) gDB.firstOrLastBean(result, false);
		return result;
	}

	public static Article last() throws ClassNotFoundException,
			SQLException {
		Article result = new Article();
		GenericBeanDAO gDB = new GenericBeanDAO();
		result = (Article) gDB.firstOrLastBean(result, true);
		return result;
	}

	public static ArrayList<Article> getWhere(String field, String value, boolean like) 
			throws ClassNotFoundException, SQLException {
		Article type = new Article();
		ArrayList<Article> result = new ArrayList<Article>();
		GenericBeanDAO gDB = new GenericBeanDAO();
		for (Bean b : gDB.selectBeanWhere(type, field, value, like)) {
			result.add((Article) b);
		}
		return result;
	}
	
	public boolean delete() throws ClassNotFoundException, SQLException {
		boolean result = false;
		GenericBeanDAO gDB = new GenericBeanDAO();
		result = gDB.deleteBean(this);
		return result;
	}

	@Override
	public String get(String field) {
		if(field.equals("id")) {
			return Integer.toString(this.getId());
		}
		
		else if(field.equals("articles_published_journals")) {
			return Integer.toString(this.getArticlesPublishedJournals());
		}
		
		else if (field.equals("articles_published_conference_proceedings")) {
			return Integer.toString(this.getArticlesPublishedConferenceProceedings());
		}
		
		else {
			return "";
		}
	}

	@Override
	public void set(String field, String data) {
		if (field.equals("id")) {
			this.setId(Integer.parseInt(data));
		} 
		
		else if (field.equals("articles_published_journals")) {
			this.setArticlesPublishedJournals(Integer.parseInt(data));
		}
		
		else if (field.equals("articles_published_conference_proceedings")) {
			this.setArticlesPublishedConferenceProceedings(Integer.parseInt(data));
		}
		
		else {

		}
		
	}

	@Override
	public ArrayList<String> fieldsList() {
		ArrayList<String> fields = new ArrayList<String>();
		fields.add("id");
		fields.add("articles_published_journals");
		fields.add("articles_published_conference_proceedings");
		return fields;
	}
}
