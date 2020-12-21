package pack;

import java.util.*;

public abstract class Human {
    private boolean isAwake;
    private boolean isHungry;
    private String name;
    private int amountOfMoney;
    private List<String> listOfThings = new ArrayList<>();

    public Human() {
        name = "Безликий человек";
    }

    public Human(String name) {
        this.name = name;
    }

    public abstract void say(String str);

    public abstract void buy(String product);

    public String getName() {
        return name;
    }

    public boolean isHungry() {
        return isHungry;
    }

    public void makeHungry() {
        isHungry = true;
        System.out.println(getName() + " проголодался");
    }

    public void eat() {
        if (isHungry()) {
            System.out.println(getName() + " поел с аппетитом");
            isHungry = false;
        } else {
            System.out.println(getName() + " поел без аппетита");
        }
    }

    public void fallAsleep() {
        if (isAwake) {
            isAwake = false;
            System.out.println(getName() + " уснул");
        }
    }

    public void wakeUp() {
        if (!isAwake) {
            isAwake = true;
            System.out.println(getName() + " проснулся");
        }
    }

    public void addThing(String product) {
        listOfThings.add(product);
    }

    public List<String> getListOfThings() {
        return listOfThings;
    }

    public void giveMoney(int amount) {
        amountOfMoney = amountOfMoney + amount;
    }

    public void takeMoney(int amount) {
        amountOfMoney = amountOfMoney - amount;
    }

    public int seeAmountOfMoney() {
        return amountOfMoney;
    }

    @Override
    public String toString() {
        return getName();
    }

    @Override
    public boolean equals(Object obj) {
        int realAmountOfMoney = amountOfMoney;
        if (amountOfMoney == obj.hashCode()) {
            giveMoney(1);
            if (amountOfMoney == obj.hashCode()) {
                return true;
            }
        }
        amountOfMoney = realAmountOfMoney;
        return false;
    }

    @Override
    public int hashCode() {
        return amountOfMoney;
    }
}
