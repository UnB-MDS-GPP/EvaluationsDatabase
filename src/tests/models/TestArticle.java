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
		article.setInternationals(Integer.parseInt("1"));
		article.setNationals(Integer.parseInt("2"));
		article.setLocals(Integer.parseInt("0"));
		article.save();

		article = new Article();
		article.setInternationals(Integer.parseInt("2"));
		article.setNationals(Integer.parseInt("8"));
		article.setLocals(Integer.parseInt("5"));
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
		article.setInternationals(Integer.parseInt("0"));
		article.setNationals(Integer.parseInt("0"));
		article.setLocals(Integer.parseInt("0"));

		assertEquals(true, article.save());
		assertEquals(initialCount, Article.count()-1);
		article.delete();
	}

	@Test
	public void shouldCountArticleOnDataBase() throws ClassNotFoundException, SQLException {
		int initialCount = Article.count();
		Article article = new Article();
		article.setInternationals(Integer.parseInt("0"));
		article.setNationals(Integer.parseInt("0"));
		article.setLocals(Integer.parseInt("0"));
		article.save();
		
		assertEquals(initialCount+1, (int)Article.count());
		assertEquals(Article.getAll().size(), (int)Article.count());
		article.delete();
	}

	@Test
	public void shouldGetArticleOnDataBase() throws ClassNotFoundException, SQLException {
		Article article1 = new Article();
		article1.setInternationals(Integer.parseInt("0"));
		article1.setNationals(Integer.parseInt("0"));
		article1.setLocals(Integer.parseInt("0"));
		article1.save();

		Article article2 = Article.get(Article.last().getId());

		assertEquals(article1.getInternationals(), article2.getInternationals());
		assertEquals(article1.getNationals(), article2.getNationals());
		assertEquals(article1.getLocals(), article2.getLocals());
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
		assertEquals(first.getInternationals(), Article.getAll().get(0).getInternationals());
		assertEquals(first.getNationals(), Article.getAll().get(0).getNationals());
		assertEquals(first.getLocals(), Article.getAll().get(0).getLocals());
	}

	@Test
	public void shouldGetTheLastArticleOnDataBase() throws ClassNotFoundException, SQLException {
		Article last = Article.last();

		ArrayList<Article> article = Article.getAll();
		assertEquals(last.getInternationals(), article.get(article.size()-1).getInternationals());
		assertEquals(last.getNationals(), article.get(article.size()-1).getNationals());
		assertEquals(last.getLocals(), article.get(article.size()-1).getLocals());
	}
	
	@Test
	public void shouldGetArticleWithWhereOnDataBase() throws ClassNotFoundException, SQLException {
		Article article = new Article();
		article.setInternationals(Integer.parseInt("8000"));
		article.setNationals(Integer.parseInt("8"));
		article.setLocals(Integer.parseInt("1"));
		article.save();

		Article article1 = new Article();
		article1.setInternationals(Integer.parseInt("10000"));
		article1.setNationals(Integer.parseInt("10"));
		article1.setLocals(Integer.parseInt("6"));
		article1.save();

		ArrayList<Article> articles1 = Article.getWhere("internationals", "00", true);
		ArrayList<Article> articles2 = Article.getWhere("internationals", "10", true);
		
		assertEquals(2, articles1.size());
		assertEquals(1, articles2.size());
		
		article.delete();
		article1.delete();
	}
}
