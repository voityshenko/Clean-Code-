package airports;

import models.MilitaryType;
import planes.ExperimentalPlane;
import planes.MilitaryPlane;
import planes.PassengerPlane;
import planes.Plane;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Airport {

    private static final Logger LOGGER = Logger.getLogger(Airport.class.getName());
    private final List<? extends Plane> planes;

    public Airport(List<? extends Plane> planes) {
        this.planes = planes;
    }

    public List<PassengerPlane> getPassengerPlanes() {
        return getPlanes(PassengerPlane.class);
    }

    public List<MilitaryPlane> getMilitaryPlanes() {
        return getPlanes(MilitaryPlane.class);
    }

    public List<ExperimentalPlane> getExperimentalPlanes() {
        return getPlanes(ExperimentalPlane.class);
    }

    public <T extends Plane> List<T> getPlanes(Class<T> className) {
        List<T> result = new ArrayList<>();
        for (Plane plane : planes) {
            if (className.isInstance(plane)) {
                result.add(className.cast(plane));
            }
        }
        return result;
    }

    public PassengerPlane getPassengerPlaneWithMaxPassengersCapacity() {
        List<PassengerPlane> passengerPlanes = getPassengerPlanes();
        PassengerPlane planeWithMaxCapacity = passengerPlanes.get(0);
        for (PassengerPlane passengerPlane : passengerPlanes) {
            if (passengerPlane.getPassengersCapacity() > planeWithMaxCapacity.getPassengersCapacity()) {
                planeWithMaxCapacity = passengerPlane;
            }
        }
        return planeWithMaxCapacity;
    }

    public List<MilitaryPlane> getTransportMilitaryPlanes() {
        List<MilitaryPlane> transportMilitaryPlanes = new ArrayList<>();
        List<MilitaryPlane> militaryPlanes = getMilitaryPlanes();
        for (MilitaryPlane plane : militaryPlanes) {
            if (plane.getMilitaryType() == MilitaryType.TRANSPORT) {
                transportMilitaryPlanes.add(plane);
            }
        }
        return transportMilitaryPlanes;
    }

    public List<MilitaryPlane> getBomberMilitaryPlanes() {
        List<MilitaryPlane> bomberMilitaryPlanes = new ArrayList<>();
        List<MilitaryPlane> militaryPlanes = getMilitaryPlanes();
        for (MilitaryPlane plane : militaryPlanes) {
            if (plane.getMilitaryType() == MilitaryType.BOMBER) {
                bomberMilitaryPlanes.add(plane);
            }
        }
        return bomberMilitaryPlanes;
    }

    public Airport sortByMaxDistance() {
        planes.sort((Comparator<Plane>) (firstPlane, secondPlane) -> firstPlane.getMaxFlightDistance() - secondPlane.getMaxFlightDistance());
        return this;
    }

    public Airport sortByMaxSpeed() {
        planes.sort((Comparator<Plane>) (firstPlane, secondPlane) -> firstPlane.getMaxSpeed() - secondPlane.getMaxSpeed());
        return this;
    }

    public void sortByMaxLoadCapacity() {
        planes.sort((Comparator<Plane>) (firstPlane, secondPlane) -> firstPlane.getMinLoadCapacity() - secondPlane.getMinLoadCapacity());
    }

    public List<? extends Plane> getPlanes() {
        return planes;
    }

    private void printPlaneDetails(Collection<? extends Plane> collection) {
        for (Plane plane : collection) {
            LOGGER.log(Level.ALL, "plane details: {}", plane);
        }
    }

    @Override
    public String toString() {
        return "airports.Airport{" +
                "Planes=" + planes.toString() +
                '}';
    }

}
