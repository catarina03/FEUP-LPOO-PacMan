package g11.controller;

import g11.model.Elements.EmptySpace;
import g11.model.Elements.PacMan;
import g11.model.Elements.Wall;
import g11.model.Position;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;

public class CollisionCheckerTest {

    @Test
    public void collideTest(){
        CollisionChecker collisionChecker = new CollisionChecker();
        Wall wall = Mockito.mock(Wall.class);
        EmptySpace emptySpace = Mockito.mock(EmptySpace.class);
        PacMan pacman = Mockito.mock(PacMan.class);

        Position pos1 = Mockito.mock(Position.class);
        Mockito.when(pos1.getX()).thenReturn(10);
        Mockito.when(pos1.getY()).thenReturn(20);

        Position pos2 = Mockito.mock(Position.class);
        Mockito.when(pos2.getX()).thenReturn(20);
        Mockito.when(pos2.getY()).thenReturn(30);

        Mockito.when(wall.getPosition()).thenReturn(pos1);
        Mockito.when(pacman.getPosition()).thenReturn(pos1);
        Mockito.when(emptySpace.getPosition()).thenReturn(pos2);

        assertEquals(true, collisionChecker.collide(pacman.getPosition(), wall.getPosition()));
        assertEquals(false, collisionChecker.collide(pacman.getPosition(), emptySpace.getPosition()));
    }
}
