package utils;

import planes.Plane;

import java.util.List;

public class AssertHelper {

    public static boolean isSorted(List<Plane> listOfPlane) {
        boolean isSorted = true;
        for (int i = 0; i < listOfPlane.size() - 1; i++) {
            if (listOfPlane.get(i).getMinLoadCapacity() > listOfPlane.get(i + 1).getMinLoadCapacity()) {
                isSorted = false;
                break;
            }
        }
        return isSorted;
    }
}
