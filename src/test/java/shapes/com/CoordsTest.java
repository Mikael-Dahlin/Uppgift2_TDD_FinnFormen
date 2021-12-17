package shapes.com;

import org.junit.jupiter.api.Test;
import shapes.com.Coords;

import static org.junit.jupiter.api.Assertions.*;

class CoordsTest {

    @Test
    void should_saveCoords_when_runningConstructor(){
        int expectedX = 1;
        int expectedY = 3;
        int expectedZ = 8;
        Coords coords = new Coords(expectedX,expectedY,expectedZ);
        assertEquals(expectedX, coords.x);
        assertEquals(expectedY, coords.y);
        assertEquals(expectedZ, coords.z);
    }
}