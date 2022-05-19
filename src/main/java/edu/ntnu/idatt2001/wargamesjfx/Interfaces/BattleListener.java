package edu.ntnu.idatt2001.wargamesjfx.Interfaces;

/**
 * Interface for the design pattern Observer. Used to observe battle and report to listeners
 */
public interface BattleListener {
    /**
     * Method called when observation is made, inherited by classes that implement BattleListener
     */
    void update();
}
