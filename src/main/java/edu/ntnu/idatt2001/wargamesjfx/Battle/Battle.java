package edu.ntnu.idatt2001.wargamesjfx.Battle;
import edu.ntnu.idatt2001.wargamesjfx.Interfaces.BattleListener;
import edu.ntnu.idatt2001.wargamesjfx.Units.*;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Battle.
 */
public class Battle{
    private final Army armyOne;
    private final Army armyTwo;
    public Terrain terrain;
    private final List<BattleListener> listeners= new ArrayList<>();

    /**
     * Instantiates a new Battle.
     *
     * @param armyOne the army one
     * @param armyTwo the army two
     * @param terrain the terrain the battle is taking place in
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
     * calls armyAttack method
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
        if (armyOne.hasUnits()){
            return armyOne;
        }
        return armyTwo;
    }


    /**
     * Method for attacking, a random unit from one army attacks a random unit from the other,
     * if the health of the unit that is attacked is below or equal zero, the unit is removed from the army.
     *
     * When a unit is removed, the thread sleeps for 70 milliseconds and calls the fireUpdate method for updating GUI
     *
     * @param army the army that is attacked, decided by the counter in the simulate method.
    */

    private void armyAttack(Army army){
        try {
            if (army.equals(armyTwo)) {
                Unit armyOneRandomUnit = armyOne.getRandomUnit();
                armyTwo.getRandomUnit().attack(armyOneRandomUnit, this.terrain,
                        armyTwo.getBannerUnits().size() > 0);
                //removes unit if health is zero or lower after attack, the thread then sleeps allowing for updating listeners
                if (armyOneRandomUnit.getHealth() <= 0) {
                    armyOne.removeUnit(armyOneRandomUnit);
                    try {
                        fireUpdate();
                        Thread.sleep(70);
                    }catch (InterruptedException e){
                        throw new InterruptedException(e.getMessage());
                    }
                }
            } else {
                Unit armyTwoRandomUnit = armyTwo.getRandomUnit();
                armyOne.getRandomUnit().attack(armyTwoRandomUnit, this.terrain,
                        armyOne.getBannerUnits().size() > 0);
                //removes unit if health is zero or lower after attack, the thread then sleeps allowing for updating listeners
                if (armyTwoRandomUnit.getHealth() <= 0) {
                    armyTwo.removeUnit(armyTwoRandomUnit);
                    try {
                        fireUpdate();
                        Thread.sleep(70);
                    }catch (InterruptedException e){
                        throw new InterruptedException(e.getMessage());
                    }
                }
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Calls the update method for all listeners
     */
    private void fireUpdate() {
        for (BattleListener listener : listeners){
            listener.update();
        }
    }

    /**
     * Adds listener to the battle
     * @param listener the listener to be added
     * @throws IllegalArgumentException if the listener is null
     */
    public void addListener(BattleListener listener){
        if (listener == null){
            throw new IllegalArgumentException("Listener cannot be null");
        }
        listeners.add(listener);
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
}
