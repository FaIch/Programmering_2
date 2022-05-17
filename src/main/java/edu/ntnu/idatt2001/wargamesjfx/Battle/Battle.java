package edu.ntnu.idatt2001.wargamesjfx.Battle;
import edu.ntnu.idatt2001.wargamesjfx.Interfaces.BattleListener;
import edu.ntnu.idatt2001.wargamesjfx.Units.*;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Battle.
 */
public class Battle {
    private final Army armyOne;
    private final Army armyTwo;
    public Terrain terrain;
    private final List<BattleListener> listeners= new ArrayList<>();

    /**
     * Instantiates a new Battle.
     *
     * @param armyOne the army one
     * @param armyTwo the army two
     * @throws IllegalArgumentException if any of the armies are empty upon instantiation.
     */
    public Battle(Army armyOne, Army armyTwo, Terrain terrain) throws IllegalArgumentException {
        if (!armyOne.hasUnits()){
            throw new IllegalArgumentException(armyOne.getName() + " has no units");
        }
        else if (!armyTwo.hasUnits()){
            throw new IllegalArgumentException(armyTwo.getName() + " has no units");
        }
        else {
            this.armyOne = armyOne;
            this.armyTwo = armyTwo;
            this.terrain = terrain;
        }
    }

    /**
     * Simulate the battle between the armies.
     * The system is turn based, and which unit that attacks, and the unit that is attacked is random.
     * Uses modulus to decide which player who should attack, armyOne attacks if the number is even
     * while armyTwo attacks if the number is odd.
     *
     * @return the winning army of the battle.
     */
    public Army simulate() throws InterruptedException {
        int counter = 0;
        while (armyOne.hasUnits() && armyTwo.hasUnits()) {
            if (counter % 2 == 0) {
                armyAttack(armyTwo);
            }
            else {
                armyAttack(armyOne);
            }
            counter++;
        }
        if (armyOne.getNumberOfUnits() > 0){
            return armyOne;
        }
        return armyTwo;
    }

    /**
     * Method for attacking, a random unit from one army attacks a random unit from the other,
     * if the health of the unit that is attacked is below or equal zero, the unit is removed from the army.
     * @param army the army that is attacked, decided by the counter in the simulate method.
     */
    private void armyAttack(Army army){
        try {
            if (army.equals(armyTwo)) {
                Unit armyOneRandomUnit = armyOne.getRandomUnit();
                armyTwo.getRandomUnit().attack(armyOneRandomUnit, this.terrain,
                        armyTwo.getBannerUnits().size() > 0);

                if (armyOneRandomUnit.getHealth() <= 0) {
                    armyOne.removeUnit(armyOneRandomUnit);
                    try {
                        Thread.sleep(75);
                    }catch (InterruptedException e){
                        throw new InterruptedException(e.getMessage());
                    }
                    fireUpdate();
                }
            } else {
                Unit armyTwoRandomUnit = armyTwo.getRandomUnit();
                armyOne.getRandomUnit().attack(armyTwoRandomUnit, this.terrain,
                        armyOne.getBannerUnits().size() > 0);

                if (armyTwoRandomUnit.getHealth() <= 0) {
                    armyTwo.removeUnit(armyTwoRandomUnit);
                    try {
                        Thread.sleep(75);
                    }catch (InterruptedException e){
                        throw new InterruptedException(e.getMessage());
                    }
                    fireUpdate();
                }
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private void fireUpdate() {
        for (BattleListener listener : listeners){
            listener.update();
        }
    }

    public void addListener(BattleListener listener){
        if (listener == null){
            throw new IllegalArgumentException("Listener cannot be null");
        } else {
            listeners.add(listener);
        }
    }


    @Override
    public String toString() {
        String returnString = "";
        try {
            returnString = "Winner of battle is: \n" + this.simulate().getName() + "\nNumber of units remaining: \n"
                    + this.simulate().getNumberOfUnits();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return returnString;
    }

    public Army getArmyOne() {
        return armyOne;
    }

    public Army getArmyTwo() {
        return armyTwo;
    }
}
