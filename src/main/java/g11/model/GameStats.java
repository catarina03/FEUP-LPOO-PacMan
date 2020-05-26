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

    public void setScore(int score) {
        this.score = score;
    }

    public int getEatenGhosts() {
        return eatenGhosts;
    }

    public void setEatenGhosts(int eatenGhosts) {
        this.eatenGhosts = eatenGhosts;
    }

    public int getEatenCoins() {
        return eatenCoins;
    }

    public void setEatenCoins(int eatenCoins) {
        this.eatenCoins = eatenCoins;
    }

    public int getEatenPP() {
        return eatenPP;
    }

    public void setEatenPP(int eatenPP) {
        this.eatenPP = eatenPP;
    }
}
