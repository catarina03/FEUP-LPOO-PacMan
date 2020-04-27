package model;

import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

public class GameStatsTest {

    @Test
    public void GameStatsCreation() {
        Random r = new Random();
        int score = r.nextInt();

        GameStats stats = new GameStats(score);
        assertEquals(score, stats.getScore());
    }

    @Test
    public void GameStatsChange(){
        Random r = new Random();

        int score = r.nextInt();
        GameStats stats = new GameStats(score);
        int score_update  = r.nextInt();
        stats.setScore(score_update);

        assertEquals(score_update, stats.getScore());
    }
}
