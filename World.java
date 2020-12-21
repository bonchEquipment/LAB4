package pack;

import java.util.ArrayList;
import java.util.List;

public class World {

    private String worldName;

    public String getWorldName() {
        return worldName;
    }

    public World(String worldName) {
        this.worldName = worldName;
        System.out.println("Планета " + worldName + " была сотворена");
    }

    public void addWalker(AbleToWalk walker, Location location) throws ArithmeticException {
        if (walker.getMyWorld() == null) {
            location.addWalkerToTheLocation(walker);
            System.out.printf("%s был помещен на планету %s на локацию %s\n",
                    walker.getName(), getWorldName(), location);
            walker.setMyWorld(this);
        } else {
            throw new AlreadyOnThePlanetException(walker.getName(), getWorldName(),
                    walker.getMyWorld().getWorldName());
        }
    }

    public void moveWalkerToLocation(AbleToWalk walker, Location location) throws OutsideThePlanetException {
        if (walker.getMyWorld() == null) {
            throw new OutsideThePlanetException("невозможно переместить " + walker.getName() + " т.к. он находиться в небытие");
        } else {
            if (!(walker.getMyWorld() == this)) {
                System.out.printf("Коварный %s попытался повлиять над %s, но не смог т.к. тот находится на %s \n",
                        this.getWorldName(), walker.getName(), walker.getMyWorld().getWorldName());
            } else {
                if (location.seeWalkersOnTheLocation().contains(walker)) {
                    System.out.println(walker.getName() + " попытался пойти на локацию " + location +
                            " уже находясь там, глупо с его стороны");
                } else {
                    for (Location loc : Location.values()) {
                        if (loc.seeWalkersOnTheLocation().contains(walker)) {
                            loc.deleteWalkerFromTheLocation(walker);
                            location.addWalkerToTheLocation(walker);
                            System.out.println(walker.getName() + " пошёл на локацию " + location);
                            break;
                        }
                    }
                }
            }
        }
    }

    public enum Location {
        ДОМ,
        УЛИЦА,
        ОФИС;

        private List<AbleToWalk> walkersOnTheLocation = new ArrayList<>();

        private void addWalkerToTheLocation(AbleToWalk walker) {
            walkersOnTheLocation.add(walker);
        }

        private void deleteWalkerFromTheLocation(AbleToWalk walker) {
            walkersOnTheLocation.remove(walker);
        }

        public List<AbleToWalk> seeWalkersOnTheLocation() {
            return walkersOnTheLocation;
        }

    }

}


