package g11.model;

public class GameStats {
    int score;
    int eatenGhosts;
    int eatenCoins;
    int eatenPP;

    public GameStats(int initialscore) {
        this.score = initialscore;
        this.eatenCoins = 0;
        this.eatenGhosts = 0;
        this.eatenPP = 0;
    }

    public int getScore() {
        return score;
    }

    public int getEatenGhosts() {
        return eatenGhosts;
    }

    public int getEatenCoins() {
        return eatenCoins;
    }

    public int getEatenPP() {
        return eatenPP;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void incrementEatenCoins() {
        this.eatenCoins = eatenCoins + 1;
    }

    public void incrementEatenGhosts() {
        this.eatenGhosts = eatenGhosts + 1;
    }

    public void incrementEatenPP() {
        this.eatenPP = eatenPP + 1;
    }
}
