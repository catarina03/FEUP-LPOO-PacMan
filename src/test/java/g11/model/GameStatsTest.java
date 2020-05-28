package g11.model;

import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

public class GameStatsTest {

    @Test
    public void GameStats() {
        Random r = new Random();
        int score = r.nextInt();
        int times = r.nextInt(65);

        GameStats stats = new GameStats(score);
        assertEquals(score, stats.getScore());

        for (int i = 0; i < times; i++) {
            stats.incrementEatenCoins();
        }
        assertEquals(stats.getEatenCoins(), times);
        assertEquals(stats.getScore(), score + times * 10);

        int pp = r.nextInt(5);
        stats.incrementEatenGhosts(pp);
        assertEquals(stats.getEatenGhosts(), 1);
        assertEquals(stats.getScore(), score + times * 10 + 200 * (int) Math.pow(2,pp-1));
        int previous = score + times * 10 + 200 * (int) Math.pow(2,pp-1);

        times = r.nextInt(65);

        for (int i = 0; i < times; i++) {
            stats.incrementEatenPP();
        }
        assertEquals(stats.getEatenPP() - times, 0);
        assertEquals(stats.getScore() - 50*times, previous);
    }
}
