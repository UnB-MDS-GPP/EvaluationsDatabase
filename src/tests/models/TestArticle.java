package tests.models;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;

import libraries.DataBaseStructures;
import models.Article;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestArticle {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		DataBaseStructures db = new DataBaseStructures();
		db.initDB();
		Article article = new Article();
		article.setArticlesPublishedJournals(Integer.parseInt("1"));
		article.setArticlesPublishedConferenceProceedings(Integer.parseInt("2"));
		article.save();

		article = new Article();
		article.setArticlesPublishedJournals(Integer.parseInt("2"));
		article.setArticlesPublishedConferenceProceedings(Integer.parseInt("8"));
		article.save();
	}


	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		DataBaseStructures db = new DataBaseStructures();
		db.dropDB();
	}


	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void shouldCreateNewArticleOnDataBase() throws ClassNotFoundException, SQLException {
		int initialCount = Article.count();

		Article article = new Article();
		article.setArticlesPublishedJournals(Integer.parseInt("0"));
		article.setArticlesPublishedConferenceProceedings(Integer.parseInt("0"));

		assertEquals(true, article.save());
		assertEquals(initialCount, Article.count()-1);
		article.delete();
	}

	@Test
	public void shouldCountArticleOnDataBase() throws ClassNotFoundException, SQLException {
		int initialCount = Article.count();
		Article article = new Article();
		article.setArticlesPublishedJournals(Integer.parseInt("0"));
		article.setArticlesPublishedConferenceProceedings(Integer.parseInt("0"));
		article.save();
		
		assertEquals(initialCount+1, (int)Article.count());
		assertEquals(Article.getAll().size(), (int)Article.count());
		article.delete();
	}

	@Test
	public void shouldGetArticleOnDataBase() throws ClassNotFoundException, SQLException {
		Article article1 = new Article();
		article1.setArticlesPublishedJournals(Integer.parseInt("0"));
		article1.setArticlesPublishedConferenceProceedings(Integer.parseInt("0"));
		article1.save();

		Article article2 = Article.get(Article.last().getId());

		assertEquals(article1.getArticlesPublishedJournals(), article2.getArticlesPublishedJournals());
		assertEquals(article1.getArticlesPublishedConferenceProceedings(), article2.getArticlesPublishedConferenceProceedings());
		article1.delete();
	}

	@Test
	public void shouldGetAllArticleOnDataBase() throws ClassNotFoundException, SQLException {
		int total = Article.count();
		assertEquals(total, Article.getAll().size());
	}
	
	@Test
	public void shouldGetTheFirstArticleOnDataBase() throws ClassNotFoundException, SQLException {
		Article first = Article.first();
		assertEquals(first.getArticlesPublishedJournals(), Article.getAll().get(0).getArticlesPublishedJournals());
		assertEquals(first.getArticlesPublishedConferenceProceedings(), Article.getAll().get(0).getArticlesPublishedConferenceProceedings());
	}

	@Test
	public void shouldGetTheLastArticleOnDataBase() throws ClassNotFoundException, SQLException {
		Article last = Article.last();

		ArrayList<Article> article = Article.getAll();
		assertEquals(last.getArticlesPublishedJournals(), article.get(article.size()-1).getArticlesPublishedJournals());
		assertEquals(last.getArticlesPublishedConferenceProceedings(), article.get(article.size()-1).getArticlesPublishedConferenceProceedings());
	}
	
	@Test
	public void shouldGetArticleWithWhereOnDataBase() throws ClassNotFoundException, SQLException {
		Article article = new Article();
		article.setArticlesPublishedJournals(Integer.parseInt("8000"));
		article.setArticlesPublishedConferenceProceedings(Integer.parseInt("8"));
		article.save();

		Article article1 = new Article();
		article1.setArticlesPublishedJournals(Integer.parseInt("10000"));
		article1.setArticlesPublishedConferenceProceedings(Integer.parseInt("10"));
		article1.save();

		ArrayList<Article> articles1 = Article.getWhere("articles_published_journals", "00", true);
		ArrayList<Article> articles2 = Article.getWhere("articles_published_journals", "10", true);
		
		assertEquals(2, articles1.size());
		assertEquals(1, articles2.size());
		
		article.delete();
		article1.delete();
	}
}
