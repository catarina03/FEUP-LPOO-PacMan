package g11.model;

public class GameStats {
    int highScore;
    int score;
    int eatenGhosts;
    int eatenCoins;
    int eatenPP;

    public GameStats(int hScore) {
        this.highScore = hScore;
        this.score = 0;
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

    public int getHighScore() {
        return highScore;
    }

    public void incrementEatenCoins() {
        this.eatenCoins = eatenCoins + 1;
        score = score + 10;
    }

    public void incrementEatenGhosts(int i) {
        this.eatenGhosts = eatenGhosts + 1;
        // 1 - 200
        // 2 - 400
        // 3 - 800
        // 4 - 1600 etc
        score = score + 200 * (int) Math.pow(2, i - 1);
    }

    public void incrementEatenPP() {
        this.eatenPP = eatenPP + 1;
        score = score + 50;
    }
}
