package shapes.com;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FindShapeTest {

    private FindShape findShape;

    @BeforeEach
    void setUp() {
        findShape = new FindShape();
    }

    @Test
    void should_returnNone_when_emptyList(){
        assertEquals("none", findShape.shape(new ArrayList<>()).toLowerCase());
    }

    @Test
    void should_returnDot_when_oneCoordInList(){
        assertEquals("dot", findShape.shape(new ArrayList<>(List.of(new Coords(0,0,0)))).toLowerCase());
    }

    @Test
    void should_returnLine_when_twoCoordsInList(){
        assertEquals("line", findShape.shape(new ArrayList<>(List.of(new Coords(0,0,0),new Coords(0,0,1)))).toLowerCase());
    }

    @Test
    void should_returnTriangle_when_threeCoordsInList(){
        assertEquals("triangle", findShape.shape(new ArrayList<>(List.of(new Coords(0,0,0),new Coords(0,0,1),new Coords(1,0,0)))).toLowerCase());
    }

    @Test
    void should_returnSquare_when_fourCoordsInListOnXPlaneWithEqualDistance(){
        assertEquals("square", findShape.shape(new ArrayList<>(List.of(new Coords(1,0,0),new Coords(1,0,1),new Coords(1,1,0),new Coords(1,1,1)))).toLowerCase());
    }

    @Test
    void should_returnSquare_when_fourCoordsInListOnYPlaneWithEqualDistance(){
        assertEquals("square", findShape.shape(new ArrayList<>(List.of(new Coords(0,0,0),new Coords(0,0,1),new Coords(1,0,0),new Coords(1,0,1)))).toLowerCase());
    }

    @Test
    void should_returnSquare_when_fourCoordsInListOnZPlaneWithEqualDistance(){
        assertEquals("square", findShape.shape(new ArrayList<>(List.of(new Coords(0,0,0),new Coords(0,1,0),new Coords(-1,0,0),new Coords(-1,1,0)))).toLowerCase());
    }

    @Test
    void should_returnSquare_when_fourCoordsInListOnZPlaneWithEqualDistanceOtherPlane(){
        assertEquals("square", findShape.shape(new ArrayList<>(List.of(new Coords(1,2,2),new Coords(1,2,1),new Coords(1,1,2),new Coords(1,1,1)))).toLowerCase());
    }

    @Test
    void should_returnRectangle_when_fourCoordsInListOnSamePlaneWithNotEqualDistanceOnEachAxis(){
        assertEquals("rectangle", findShape.shape(new ArrayList<>(List.of(new Coords(0,0,0),new Coords(0,0,2),new Coords(1,0,0),new Coords(1,0,2)))).toLowerCase());
    }

    @Test
    void should_return2dShape_when_fourCoordsInListOnSamePlaneWithNotEqualDistance(){
        assertEquals("2d shape", findShape.shape(new ArrayList<>(List.of(new Coords(0,0,0),new Coords(0,0,2),new Coords(1,0,0),new Coords(2,0,3)))).toLowerCase());
    }

    @Test
    void should_returnGenericShape_when_fourCoordsInListNotOnSamePlane(){
        assertEquals("generic shape", findShape.shape(new ArrayList<>(List.of(new Coords(0,0,0),new Coords(0,0,2),new Coords(1,1,0),new Coords(2,0,3)))).toLowerCase());
    }

    @Test
    void should_returnParallelogram_when_fourCoordsInListOnSamePlaneWithTwoParallelLinesNotOnTopOfEachother(){
        assertEquals("parallelogram", findShape.shape(new ArrayList<>(List.of(new Coords(0,0,0),new Coords(0,0,2),new Coords(1,0,1),new Coords(1,0,3)))).toLowerCase());
    }

    @Test
    void should_returnParallelogram_when_fourCoordsInListOnSamePlaneWithTwoParallelLinesNotOnTopOfEachotherDifferentBase(){
        assertEquals("parallelogram", findShape.shape(new ArrayList<>(List.of(new Coords(0,0,0),new Coords(0,2,0),new Coords(0, 1,1),new Coords(0,3,1)))).toLowerCase());
    }

    @Test
    void should_returnParallelogram_when_fourCoordsInListOnSamePlaneWithTwoParallelLinesNotOnTopOfEachotherDifferentAxis(){
        assertEquals("parallelogram", findShape.shape(new ArrayList<>(List.of(new Coords(0,0,0),new Coords(1,1,0),new Coords(1,0,0),new Coords(2,1,0)))).toLowerCase());
    }

    @Test
    void should_returnParallelogram_when_fourCoordsInListOnSamePlaneWithTwoParallelLinesNotOnTopOfEachotherDifferentAxisDifferentBase(){
        assertEquals("parallelogram", findShape.shape(new ArrayList<>(List.of(new Coords(0,0,0),new Coords(1,0,1),new Coords(1,0,0),new Coords(2,0,1)))).toLowerCase());
    }

    @Test
    void should_returnParallelogram_when_fourCoordsInListOnSamePlaneWithTwoParallelLinesNotOnTopOfEachotherThirdAxis(){
        assertEquals("parallelogram", findShape.shape(new ArrayList<>(List.of(new Coords(0,0,0),new Coords(1,1,0),new Coords(0,2,0),new Coords(1,3,0)))).toLowerCase());
    }

    @Test
    void should_returnParallelogram_when_fourCoordsInListOnSamePlaneWithTwoParallelLinesNotOnTopOfEachotherThirdAxisDifferentBase(){
        assertEquals("parallelogram", findShape.shape(new ArrayList<>(List.of(new Coords(0,0,0),new Coords(0,1,1),new Coords(0,0,2),new Coords(0,1,3)))).toLowerCase());
    }

    @Test
    void should_returnPyramid_when_fiveCoordsInListWhereFourMakesSquareOnSamePlaneAndFifthIsOnOtherPlane(){
        assertEquals("pyramid", findShape.shape(new ArrayList<>(List.of(new Coords(0,0,0),new Coords(0,0,2),new Coords(0,2,0),new Coords(0,2,2),new Coords(1,1,1)))).toLowerCase());
    }

    @Test
    void should_returnPyramid_when_fiveCoordsInListWhereFourMakesSquareOnSamePlaneAndFifthIsOnOtherPlaneBaseOnZ(){
        assertEquals("pyramid", findShape.shape(new ArrayList<>(List.of(new Coords(4,0,2),new Coords(0,4,2),new Coords(4,4,2),new Coords(0,0,2),new Coords(2,2,0)))).toLowerCase());
    }

    @Test
    void should_returnPyramid_when_fiveCoordsInListWhereFourMakesRectangleOnSamePlaneAndFifthIsOnOtherPlane(){
        assertEquals("pyramid", findShape.shape(new ArrayList<>(List.of(new Coords(0,0,0),new Coords(0,0,4),new Coords(2,0,0),new Coords(2,0,4),new Coords(1,1,2)))).toLowerCase());
    }

    @Test
    void should_return3dShape_when_fiveCoordsInListWhereFourDoesNotMakesRectangleOrSquare(){
        assertEquals("3d shape", findShape.shape(new ArrayList<>(List.of(new Coords(0,0,0),new Coords(0,0,2),new Coords(2,0,0),new Coords(2,0,4),new Coords(1,1,2)))).toLowerCase());
    }

    @Test
    void should_returnRectangularPrism_when_eigthCoordsInListWhereFourMakesRectangleOnSamePlaneAndRectangleOnOtherPlane(){
        assertEquals("rectangular prism", findShape.shape(new ArrayList<>(List.of(new Coords(0,0,0),new Coords(0,0,4),new Coords(2,0,0),new Coords(2,0,4),new Coords(0,1,0),new Coords(0,1,4),new Coords(2,1,0),new Coords(2,1,4)))).toLowerCase());
    }

    @Test
    void should_returnCube_when_eigthCoordsInListWhereFourMakesSquareOnSamePlaneAndSquareOnOtherPlane(){
        assertEquals("cube", findShape.shape(new ArrayList<>(List.of(new Coords(0,0,0),new Coords(0,0,2),new Coords(2,0,0),new Coords(2,0,2),new Coords(0,2,0),new Coords(0,2,2),new Coords(2,2,0),new Coords(2,2,2)))).toLowerCase());
    }

    @Test
    void should_returnRectangularPrism_when_eigthCoordsInListWhereFourMakesRectangleOnSamePlaneAndSquareOnOtherPlane(){
        assertEquals("rectangular prism", findShape.shape(new ArrayList<>(List.of(new Coords(0,0,0),new Coords(0,0,4),new Coords(2,0,0),new Coords(2,0,4),new Coords(0,2,0),new Coords(0,2,4),new Coords(2,2,0),new Coords(2,2,4)))).toLowerCase());
    }

    @Test
    void should_return3dShape_when_sixCoordsInListWith3dPattern(){
        assertEquals("3d shape", findShape.shape(new ArrayList<>(List.of(new Coords(0,0,0),new Coords(0,0,2),new Coords(2,0,0),new Coords(2,0,4),new Coords(1,1,2),new Coords(4,1,2)))).toLowerCase());
    }

    @Test
    void should_return2dShape_when_sixCoordsInListWith2dPattern(){
        assertEquals("2d shape", findShape.shape(new ArrayList<>(List.of(new Coords(0,0,0),new Coords(0,0,2),new Coords(2,0,0),new Coords(2,0,4),new Coords(1,0,2),new Coords(2,0,2)))).toLowerCase());
    }

    @Test
    void should_returnCube_when_eigthCoordsInListWhereOneCoordIsADuplicate(){
        assertEquals("3d shape", findShape.shape(new ArrayList<>(List.of(new Coords(0,0,0),new Coords(0,0,2),new Coords(2,0,0),new Coords(2,0,2),new Coords(0,2,0),new Coords(0,2,2),new Coords(2,2,2),new Coords(2,2,2)))).toLowerCase());
    }
}