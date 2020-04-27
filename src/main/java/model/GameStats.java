package model;

public class GameStats {
    int score;

    public GameStats(int initialscore) {
        this.score = initialscore;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
