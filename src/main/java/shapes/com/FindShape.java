package shapes.com;

import java.util.*;
import java.util.List;

public class FindShape {

    public String shape(ArrayList<Coords> coords){
        if (coords==null || coords.isEmpty())
            return "None";

        if (coords.size() == 1)
            return "Dot";

        if (coords.size() == 2)
            return "Line";

        if (coords.size() == 3)
            return "Triangle";

        if (coords.size() == 4){
            return check2DShape(coords);
        }

        return check3DShape(coords);
     }

    public String check2DShape(ArrayList<Coords> coords){
        int aSide = 0;
        int bSide = -1;
        Map<Integer, List<Coords>> xMap = new HashMap<>();
        Map<Integer, List<Coords>> yMap = new HashMap<>();
        Map<Integer, List<Coords>> zMap = new HashMap<>();

        for (Coords c : coords) {
            List<Coords> xList = xMap.get(c.x);
            if (xList == null) {
                xList = new ArrayList<>();
                xMap.put(c.x, xList);
            }
            xList.add(c);

            List<Coords> yList = yMap.get(c.y);
            if (yList == null) {
                yList = new ArrayList<>();
                yMap.put(c.y, yList);
            }
            yList.add(c);

            List<Coords> zList = zMap.get(c.z);
            if (zList == null) {
                zList = new ArrayList<>();
                zMap.put(c.z, zList);
            }
            zList.add(c);
        }

        switch (xMap.keySet().size() + yMap.keySet().size() + zMap.keySet().size()){
            case 5:
                if (xMap.keySet().size() == 2 && yMap.keySet().size() == 2){
                    aSide = findSideLength(xMap.keySet());
                    bSide = findSideLength(yMap.keySet());
                } else if (yMap.keySet().size() == 2 && zMap.keySet().size() == 2){
                    aSide = findSideLength(yMap.keySet());
                    bSide = findSideLength(zMap.keySet());
                } else if (zMap.keySet().size() == 2 && xMap.keySet().size() == 2){
                    aSide = findSideLength(zMap.keySet());
                    bSide = findSideLength(xMap.keySet());
                }
                return (aSide==bSide) ? "Square" : "Rectangle";
            case 6:
            case 7:
                if (xMap.keySet().size() == 1 && yMap.keySet().size() == 2){
                    for (int key : yMap.keySet()) {
                        int first = yMap.get(key).get(0).z;
                        int second = yMap.get(key).get(1).z;
                        if (aSide == 0) {
                            aSide = length(first, second);
                        } else {
                            bSide = length(first, second);
                        }
                    }
                } else if (xMap.keySet().size() == 1 && zMap.keySet().size() == 2){
                    for (int key : zMap.keySet()) {
                        int first = zMap.get(key).get(0).y;
                        int second = zMap.get(key).get(1).y;
                        if (aSide == 0) {
                            aSide = length(first, second);
                        } else {
                            bSide = length(first, second);
                        }
                    }
                } else if (yMap.keySet().size() == 1 && xMap.keySet().size() == 2){
                    for (int key : xMap.keySet()) {
                        int first = xMap.get(key).get(0).z;
                        int second = xMap.get(key).get(1).z;
                        if (aSide == 0) {
                            aSide = length(first, second);
                        } else {
                            bSide = length(first, second);
                        }
                    }
                } else if (yMap.keySet().size() == 1 && zMap.keySet().size() == 2){
                    for (int key : zMap.keySet()) {
                        int first = zMap.get(key).get(0).x;
                        int second = zMap.get(key).get(1).x;
                        if (aSide == 0) {
                            aSide = length(first, second);
                        } else {
                            bSide = length(first, second);
                        }
                    }
                } else if (zMap.keySet().size() == 1 && yMap.keySet().size() == 2){
                    for (int key : yMap.keySet()) {
                        int first = yMap.get(key).get(0).x;
                        int second = yMap.get(key).get(1).x;
                        if (aSide == 0) {
                            aSide = length(first, second);
                        } else {
                            bSide = length(first, second);
                        }
                    }
                } else if (zMap.keySet().size() == 1 && xMap.keySet().size() == 2){
                    for (int key : xMap.keySet()) {
                        int first = xMap.get(key).get(0).y;
                        int second = xMap.get(key).get(1).y;
                        if (aSide == 0) {
                            aSide = length(first, second);
                        } else {
                            bSide = length(first, second);
                        }
                    }
                }
                return (aSide==bSide) ? "Parallelogram" : "2D shape";
            default:
                return "Generic Shape";
        }
    }

    public String check3DShape(ArrayList<Coords> coords){
        Map<Integer, ArrayList<Coords>> xMap = new HashMap<>();
        Map<Integer, ArrayList<Coords>> yMap = new HashMap<>();
        Map<Integer, ArrayList<Coords>> zMap = new HashMap<>();
        int uniqueCoords = new HashSet<>(coords).size();

        for (Coords c : coords) {
            ArrayList<Coords> xList = xMap.get(c.x);
            if (xList == null) {
                xList = new ArrayList<>();
                xMap.put(c.x, xList);
            }
            xList.add(c);

            ArrayList<Coords> yList = yMap.get(c.y);
            if (yList == null) {
                yList = new ArrayList<>();
                yMap.put(c.y, yList);
            }
            yList.add(c);

            ArrayList<Coords> zList = zMap.get(c.z);
            if (zList == null) {
                zList = new ArrayList<>();
                zMap.put(c.z, zList);
            }
            zList.add(c);
        }

        if (xMap.keySet().size() == 1 || yMap.keySet().size() == 1 || zMap.keySet().size() == 1)
            return "2D Shape";

        if (uniqueCoords == 5){
            String shape = "generic shape";
            for (int key : xMap.keySet()) {
                if (xMap.get(key).size() == 4 && shape == "generic shape")
                    shape = check2DShape(xMap.get(key));
            }

            for (int key : yMap.keySet()) {
                if (yMap.get(key).size() == 4 && shape == "generic shape")
                    shape = check2DShape(yMap.get(key));
            }

            for (int key : zMap.keySet()) {
                if (zMap.get(key).size() == 4 && shape == "generic shape")
                    shape = check2DShape(zMap.get(key));
            }

            if (shape.equalsIgnoreCase("square") || shape.equalsIgnoreCase("rectangle"))
                return "Pyramid";
        }

        if (coords.size() == 8 && xMap.keySet().size() == 2 && yMap.keySet().size() == 2 && zMap.keySet().size() == 2) {
            String firstShape = "generic shape";
            String secondShape = "generic shape";
            String thirdShape = "generic shape";

            for (int key : xMap.keySet()) {
                if (xMap.get(key).size() == 4 && firstShape == "generic shape")
                    firstShape = check2DShape(xMap.get(key));
            }

            for (int key : yMap.keySet()) {
                if (yMap.get(key).size() == 4 && secondShape == "generic shape")
                    secondShape = check2DShape(yMap.get(key));
            }

            for (int key : zMap.keySet()) {
                if (zMap.get(key).size() == 4 && thirdShape == "generic shape")
                    thirdShape = check2DShape(zMap.get(key));
            }

            if (!firstShape.equalsIgnoreCase("generic shape") && !secondShape.equalsIgnoreCase("generic shape") && !thirdShape.equalsIgnoreCase("generic shape")) {
                if (firstShape.equalsIgnoreCase("rectangle") || secondShape.equalsIgnoreCase("rectangle")) {
                    return "Rectangular Prism";
                } else {
                    return "Cube";
                }
            }
        }
        return "3D Shape";
    }

    private int length(int first, int second){
        return first < second ? second - first : first - second;
    }

    private int findSideLength(Set<Integer> keyset) {
        int side = 0;
        for (int key : keyset) {
            if (side == 0) {
                side = key;
            } else {
                side = (key > side) ? key - side : side - key;
            }
        }

        if (side < 0)
            return side * -1;

        return side;
    }
}
