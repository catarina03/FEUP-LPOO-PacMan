package g11.controller;

import g11.model.Position;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;

public class CollisionCheckerTest {

    @Test
    public void collideTest(){
        CollisionChecker collisionChecker = new CollisionChecker();

        Position pos1 = Mockito.mock(Position.class);
        Mockito.when(pos1.getX()).thenReturn(10);
        Mockito.when(pos1.getY()).thenReturn(20);

        Position pos2 = Mockito.mock(Position.class);
        Mockito.when(pos2.getX()).thenReturn(10);
        Mockito.when(pos2.getY()).thenReturn(20);

        Position pos3 = Mockito.mock(Position.class);
        Mockito.when(pos3.getX()).thenReturn(30);
        Mockito.when(pos3.getY()).thenReturn(40);

        assertEquals(true, collisionChecker.collide(pos1, pos2));
        assertEquals(false, collisionChecker.collide(pos1, pos3));
    }
}
