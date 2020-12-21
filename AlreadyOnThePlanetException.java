package pack;

public class AlreadyOnThePlanetException extends Error {

    public AlreadyOnThePlanetException(String shortyName, String newWorld, String recentShortyWorld) {
        super("Невозможно добавить " + shortyName + " на планету " + newWorld +
                "так как он уже находиться на планете " + recentShortyWorld);
    }

}
