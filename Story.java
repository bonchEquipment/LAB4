package pack;


public class Story {

    public static void main(String[] args) {

        class Neznayka extends Shorty{
            {
                giveMoney(101);
            }
            Neznayka(String name){
                super(name);
            }
        };

        Story storyAboutShorties = new Story();
        World moon = new World("Луна");
        Neznayka neznayka = new Neznayka("Незнайка");
        Shorty kozlik = new Shorty("Козлик");

        Shorty newspaperSeller = new Shorty("Продавец газет") {
            public void say(String phrase) {
                System.out.println(getName() + " выкрикнул: \"" + phrase + "\"");
            }
        };
        storyAboutShorties.tellStory(neznayka, kozlik, newspaperSeller, moon);

    }

    public void tellStory(Shorty actor1, Shorty actor2, Shorty actor3, World planet) {
        try {
            planet.addWalker(actor1, World.Location.ДОМ);
        } catch (AlreadyOnThePlanetException AOTPE) {
            AOTPE.printStackTrace();
        }

        try {
            planet.addWalker(actor2, World.Location.ДОМ);
        } catch (AlreadyOnThePlanetException AOTPE) {
            AOTPE.printStackTrace();
        }

        try {
            planet.addWalker(actor3, World.Location.УЛИЦА);
        } catch (AlreadyOnThePlanetException AOTPE) {
            AOTPE.printStackTrace();
        }

        actor1.wakeUp();
        actor2.wakeUp();
        actor1.lookAround();
        actor1.eat();
        actor2.eat();
        try {
            actor1.goTo(World.Location.УЛИЦА);
        } catch (OutsideThePlanetException exception) {
            exception.printStackTrace();
        }

        try {
            actor2.goTo(World.Location.УЛИЦА);
        } catch (OutsideThePlanetException OTPE) {
            OTPE.printStackTrace();
        }
        actor3.say("Самые свежие газеты на луне!");
        actor1.lookAround();
        actor1.buy("Газета");

        try {
            actor1.read();
        } catch (HaveNothingToReadException HNTRE) {
            HNTRE.printStackTrace();
        }

    }
}


