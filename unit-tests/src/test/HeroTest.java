package test;

import org.junit.Test;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import static org.junit.Assert.assertTrue;


import codingfactory.rpgconsole.hero.Hero;
import codingfactory.rpgconsole.enemy.Enemy;

public class HeroTest {

	Hero hero;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("Avant le démarrage");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("Après tous les tests");
	}

	@Before
	public void setUp() throws Exception {
		hero = new Hero("Jaina Portvaillant");
		System.out.println("Avant un test");
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("Après un test");
	}

	@Test
	public void testHeroLevelUp() throws Exception {
		assertThat(hero, hasProperty("level", is(1)));
		hero.levelUp();
		assertThat(hero, hasProperty("level", is(2)));
	}

	@Test
	public void testHeroTakeDamage() throws Exception {
		assertThat(hero, hasProperty("hp", is(20)));
		hero.takeDamage(2);
		assertThat(hero, hasProperty("hp", is(18)));
	}

	@Test
	public void testHeroAtk() throws Exception {
		assertTrue(hero.getAtk().intValue() >= 2);
	}

	@Test
	public void testHeroProperties() throws Exception {
		assertThat(hero, hasProperty("name", is("Jaina Portvaillant")));
		assertThat(hero, hasProperty("hp", is(20)));
		assertThat(hero, hasProperty("level", is(1)));
		assertThat(hero, hasProperty("atk", is(2)));		
	}

}
