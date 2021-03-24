package test;

import org.junit.Test;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;


import codingfactory.rpgconsole.hero.Hero;
import codingfactory.rpgconsole.enemy.Enemy;

public class EnemyTest {

	Enemy enemy;

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
		enemy = new Enemy("EKIP", 1);
		System.out.println("Avant un test");
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("Après un test");
	}

    @Test
	public void testEnemyAtk() throws Exception {
		assertEquals(1, enemy.getAtk().intValue(), 1);
    }

	@Test
	public void testEnemyTakeDamage() throws Exception {
		assertThat(enemy, hasProperty("hp", is(15)));
		enemy.takeDamage(2);
		assertThat(enemy, hasProperty("hp", is(13)));
	}

	@Test
	public void testHeroProperties() throws Exception {
		assertThat(enemy, hasProperty("name", is("EKIP")));
        assertThat(enemy, hasProperty("hp", is(15)));
        assertThat(enemy, hasProperty("level", is(1)));
        assertThat(enemy, hasProperty("atk", is(1)));
	}

}
