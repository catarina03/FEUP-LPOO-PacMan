package Model;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class GameStats {
    int score;

    public GameStats(int initialscore) {
        this.score = initialscore;
    }

    public int getScore() {
        return score;
    }

    public void draw(TextGraphics textGraphics){
        textGraphics.setForegroundColor(TextColor.ANSI.RED);
        textGraphics.setBackgroundColor(TextColor.ANSI.BLACK);
        textGraphics.putString(9, 1, "SCORE", SGR.BOLD);
        textGraphics.putString(29, 1, "HI-SCORE", SGR.BOLD);
        textGraphics.setForegroundColor(TextColor.ANSI.WHITE);
        textGraphics.putString(13, 2, String.valueOf(score), SGR.BOLD);
        textGraphics.putString(31, 2, "10000", SGR.BOLD);
        textGraphics.setForegroundColor(TextColor.ANSI.YELLOW);
        textGraphics.putString(9, 34, "00000", SGR.BOLD);
        textGraphics.setForegroundColor(TextColor.ANSI.RED);
        textGraphics.putString(35, 34, "o", SGR.BOLD);
    }
}
