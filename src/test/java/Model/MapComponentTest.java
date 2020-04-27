package Model;

import Model.Elements.*;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Random;

import static org.junit.Assert.assertEquals;

public class MapComponentTest {

    @Test
    public void GhostCreation() {
        Random r = new Random();
        int x = r.nextInt();
        int y = r.nextInt();

        Inky inky = new Inky(x, y);
        Blinky blinky = new Blinky(x, y);
        Clyde clyde = new Clyde(x, y);
        Pinky pinky = new Pinky(x, y);

        assertEquals(inky.getX(), x);
        assertEquals(inky.getY(), y);
        assertEquals(blinky.getX(), x);
        assertEquals(blinky.getY(), y);
        assertEquals(clyde.getX(), x);
        assertEquals(clyde.getY(), y);
        assertEquals(pinky.getX(), x);
        assertEquals(pinky.getY(), y);
    }

    @Test
    public void FixedCreation() {
        Random r = new Random();
        int x = r.nextInt();
        int y = r.nextInt();

        Cherry cherry = new Cherry(x, y);
        EmptySpace emptySpace = new EmptySpace(x, y);
        PowerPellet powerPellet = new PowerPellet(x, y);
        Wall wall = new Wall(x, y);
        Coin coin = new Coin(x, y);

        assertEquals(cherry.getX(), x);
        assertEquals(cherry.getY(), y);
        assertEquals(emptySpace.getX(), x);
        assertEquals(emptySpace.getY(), y);
        assertEquals(powerPellet.getX(), x);
        assertEquals(powerPellet.getY(), y);
        assertEquals(wall.getX(), x);
        assertEquals(wall.getY(), y);
        assertEquals(coin.getX(), x);
        assertEquals(coin.getY(), y);
    }

    @Test
    public void PacManCreation() {
        Random r = new Random();
        int x = r.nextInt();
        int y = r.nextInt();
        Position pos = Mockito.mock(Position.class);

        PacMan pacman = new PacMan(x, y);
        PacMan another_pacman = new PacMan(pos);

        assertEquals(pacman.getX(), x);
        assertEquals(pacman.getY(), y);
        assertEquals(another_pacman.getPosition(), pos);
    }

    @Test
    public void PacManChange() {
        Random r = new Random();
        int x = r.nextInt();
        int y = r.nextInt();
        Position pos = Mockito.mock(Position.class);

        PacMan pacman = new PacMan(x, y);
        PacMan another_pacman = new PacMan(pos);

        int new_x = r.nextInt();
        int new_y = r.nextInt();
        Position new_pos = Mockito.mock(Position.class);

        pacman.setX(new_x);
        pacman.setY(new_y);
        another_pacman.setPosition(new_pos);

        assertEquals(pacman.getX(), new_x);
        assertEquals(pacman.getY(), new_y);
        assertEquals(another_pacman.getPosition(), new_pos);
    }

    @Test
    public void PacManMovement() {
        Random r = new Random();
        int x = r.nextInt();
        int y = r.nextInt();

        PacMan pacman = new PacMan(x, y);
        assertEquals(pacman.getOrientation(), Orientation.LEFT);

        pacman.setOrientation(Orientation.DOWN);
        pacman.moveDirection();
        assertEquals(pacman.getY(), y+1);
        pacman.setOrientation(Orientation.RIGHT);
        pacman.moveDirection();
        assertEquals(pacman.getX(), x+1);
        pacman.setOrientation(Orientation.UP);
        pacman.moveDirection();
        assertEquals(pacman.getY(), y);
        pacman.setOrientation(Orientation.LEFT);
        pacman.moveDirection();
        assertEquals(pacman.getX(), x);
    }
}
