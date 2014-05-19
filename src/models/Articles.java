package models;

import java.sql.SQLException;

public class Articles extends GenericBeanDAO{
	private int id;
	private int publishedJournals;
	private int publishedConferenceProceedings;
	
	public Articles() throws ClassNotFoundException, SQLException {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPublishedJournals() {
		return publishedJournals;
	}
	public void setPublishedJournals(int publishedJournals) {
		this.publishedJournals = publishedJournals;
	}
	public int getPublishedConferenceProceedings() {
		return publishedConferenceProceedings;
	}
	public void setPublishedConferenceProceedings(int publishedConferenceProceedings) {
		this.publishedConferenceProceedings = publishedConferenceProceedings;
	}
	

}
