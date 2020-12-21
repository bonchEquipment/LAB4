package pack;

import java.util.*;

public class Shorty extends Human implements AbleToSee, AbleToWalk {

    private List<Shorty> seenByMe = new ArrayList<>();

    private World onWhatPlanetImNow;

    public Shorty() {
        super();
        seenByMe.add(this);
        System.out.println("Безликий человек был создан ");
    }

    public Shorty(String name) {
        super(name);
        seenByMe.add(this);
        System.out.println(name + " был создан ");
    }

    public void say(String phrase) {
        System.out.println(getName() + " сказал: \"" + phrase + "\"");
    }

    public void buy(String product) {
        addThing(product);
        takeMoney(2);
        System.out.println(getName() + " купил " + product);
    }

    public void read() {
        if (!getListOfThings().isEmpty()) {
            if (Math.random() < 0.5) {
                System.out.println(getName() + " прочитал газету и нашел сообщение о крахе общества гигантских растений");
            } else {
                System.out.println(getName() + " прочитал газету, но не нашел сообщение о крахе общества гигантских растений");
            }
        } else {
            throw new HaveNothingToReadException(getName() + " хотел было почитать, но оказалось, что нечего");
        }
    }

    public World getMyWorld() {
        return onWhatPlanetImNow;
    }

    public void setMyWorld(World world) {
        if (getMyWorld() == null) {
            onWhatPlanetImNow = world;

        }
    }

    public void goTo(World.Location location) throws OutsideThePlanetException{
        if (getMyWorld() == null) {
            throw new OutsideThePlanetException(getName() + " не находится ни на какой из планет");
        } else {
                getMyWorld().moveWalkerToLocation(this, location);
        }
    }


    public void lookAround() {
        World.Location myCurrentLocation = null;
        List<Shorty> noticedByMe = new ArrayList<>();
        List<Shorty> missedByMe = new ArrayList<>();
        for (World.Location location : World.Location.values()) {
            if (location.seeWalkersOnTheLocation().contains(this)) {
                myCurrentLocation = location;
            }
        }

        if (myCurrentLocation == null) {
            System.out.println(getName() + " оглянулся, но увидел лишь безысходность");
        } else {
            for (AbleToWalk walker : myCurrentLocation.seeWalkersOnTheLocation()) {
                if (!seenByMe.contains(walker) && walker.getMyWorld() == this.getMyWorld()) {
                    noticedByMe.add((Shorty) walker);
                    seenByMe.add((Shorty) walker);
                }
            }
            for (Shorty shorty : seenByMe) {
                if (!myCurrentLocation.seeWalkersOnTheLocation().contains(shorty)) {
                    missedByMe.add(shorty);
                }
            }
            seenByMe.removeAll(missedByMe);
            if (noticedByMe.size() == 0 && missedByMe.size() == 0) {
                System.out.println(getName() + " огляделся и не знаметил никаких изменений");
            }
            if (noticedByMe.size() > 0 && missedByMe.size() == 0) {
                System.out.print(getName() + " огляделся и увидел ");
                for (Shorty shorty : noticedByMe) {
                    System.out.print(shorty.getName() + " ");
                }
                System.out.println("");
            }
            if (noticedByMe.size() == 0 && missedByMe.size() > 0) {
                System.out.print(getName() + " огляделся и понял, что потерял из виду  ");
                for (Shorty shorty : missedByMe) {
                    System.out.print(shorty.getName() + " ");
                }
                System.out.println("");
            }
            if (noticedByMe.size() > 0 && missedByMe.size() > 0) {
                System.out.print(getName() + " огляделся и увидел ");
                for (Shorty shorty : noticedByMe) {
                    System.out.print(shorty.getName() + " ");
                }
                System.out.print(", но упустил из вида ");
                for (Shorty shorty : missedByMe) {
                    System.out.print(shorty.getName() + " ");
                }
                System.out.println("");
            }
        }
    }
}




