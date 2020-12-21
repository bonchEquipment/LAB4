package pack;

public interface AbleToWalk  {
    void goTo(World.Location location) throws OutsideThePlanetException;
    World getMyWorld();
    String getName();
    void setMyWorld(World myWorld);
}

