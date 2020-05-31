package g11.model;

import com.sun.javafx.fxml.expression.ExpressionValue;
import g11.model.elements.*;
import g11.model.elements.ghosts.Blinky;
import g11.model.elements.ghosts.Clyde;
import g11.model.elements.ghosts.Inky;
import g11.model.elements.ghosts.Pinky;
import g11.model.elements.map.*;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Random;

import static org.junit.Assert.assertEquals;

public class MapComponentTest {

    @Test
    public void GhostTest() {
        Random r = new Random();
        int x = r.nextInt(50);
        int y = r.nextInt(50);
        int xMapLimit = r.nextInt();

        Moveable.xValueTP = xMapLimit;

        Position positionRandom = Mockito.mock(Position.class);
        Mockito.when(positionRandom.getX()).thenReturn(x);
        Mockito.when(positionRandom.getY()).thenReturn(y);

        Position position = Mockito.mock(Position.class);
        Mockito.when(position.getX()).thenReturn(0);
        Mockito.when(position.getY()).thenReturn(0);

        Inky inky = new Inky(position, positionRandom);

        inky.setOrientationEnumeration(OrientationEnumeration.LEFT);
        inky.setX(0);
        inky.setY(5);
        inky.moveDirection();
        assertEquals(xMapLimit, inky.getX());

        inky.setOrientationEnumeration(OrientationEnumeration.RIGHT);
        inky.setX(xMapLimit);
        inky.setY(5);
        inky.moveDirection();
        assertEquals(0, inky.getX());

        inky.setOrientationEnumeration(OrientationEnumeration.UP);
        inky.setX(5);
        inky.setY(3);
        inky.moveDirection();
        assertEquals(33, inky.getY());

        inky.setOrientationEnumeration(OrientationEnumeration.DOWN);
        inky.setX(5);
        inky.setY(33);
        inky.moveDirection();
        assertEquals(3, inky.getY());
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
        Gate gate = new Gate(x, y);

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
        assertEquals(gate.getX(), x);
        assertEquals(gate.getY(), y);
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
    public void MapComponentMovement(){
        Position position = Mockito.mock(Position.class);
        Mockito.when(position.getX()).thenReturn(5);
        Mockito.when(position.getY()).thenReturn(10);

        PacMan pacMan = new PacMan(position);

        pacMan.getPosition(OrientationEnumeration.DOWN);
        Mockito.verify(position, Mockito.times(1)).down();

        pacMan.getPosition(OrientationEnumeration.UP);
        Mockito.verify(position, Mockito.times(1)).up();

        pacMan.getPosition(OrientationEnumeration.LEFT);
        Mockito.verify(position, Mockito.times(1)).left();

        pacMan.getPosition(OrientationEnumeration.RIGHT);
        Mockito.verify(position, Mockito.times(1)).right();
    }
}
