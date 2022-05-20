package edu.ntnu.idatt2001.wargamesjfx.Battle;
import edu.ntnu.idatt2001.wargamesjfx.Factory.GetUnitFactory;
import edu.ntnu.idatt2001.wargamesjfx.Factory.UnitType;
import edu.ntnu.idatt2001.wargamesjfx.Units.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BattleTest {
    @Test
    @DisplayName("Battle with no units, throws IllegalArgumentException")
    void battleWithNoUnitsThrowsException(){
        Army emptyArmy = new Army("EmptyArmy");
        Army army = new Army("Army");
        army.addUnit(new InfantryUnit(""));
        assertThrows(IllegalArgumentException.class, () ->{
            Battle dummyBattle = new Battle(army,emptyArmy,Terrain.Forest);
        });
    }

    @Test
    @DisplayName("Certain winner")
    void certainWinner() throws InterruptedException {
        Army loser = new Army("Loser");
        Army winner = new Army("Winner");
        loser.addUnit(new InfantryUnit(""));
        winner.addUnit(new InfantryUnit(""));
        Battle testBattle = new Battle(winner,loser,Terrain.Forest);
        Unit randomUnit = loser.getRandomUnit();
        loser.removeUnit(randomUnit);
        assertEquals(winner, testBattle.simulate());
    }

    @Test
    void battleTest(){
        Army humanArmy = new Army("Human Army");
        Army orcArmy = new Army("Orc Army");

        humanArmy.addAllUnits(GetUnitFactory.getXUnits(UnitType.InfantryUnit, 10, ""));
        orcArmy.addAllUnits(GetUnitFactory.getXUnits(UnitType.InfantryUnit, 10, ""));

        humanArmy.addAllUnits(GetUnitFactory.getXUnits(UnitType.RangedUnit, 10, ""));
        orcArmy.addAllUnits(GetUnitFactory.getXUnits(UnitType.RangedUnit, 10, ""));

        humanArmy.addAllUnits(GetUnitFactory.getXUnits(UnitType.CavalryUnit, 5, ""));
        orcArmy.addAllUnits(GetUnitFactory.getXUnits(UnitType.CavalryUnit, 5, ""));

        humanArmy.addUnit(GetUnitFactory.getUnit(UnitType.CommanderUnit, ""));
        orcArmy.addUnit(GetUnitFactory.getUnit(UnitType.CommanderUnit, ""));

        Battle testBattle = new Battle(humanArmy,orcArmy,Terrain.Forest);
        System.out.println(testBattle);
    }

    @Test
    void attackBonusTest(){
        Army armyOne = new Army("Boys");
        Army armyTwo = new Army("Girls");

        armyOne.addUnit(GetUnitFactory.getUnit(UnitType.RangedUnit, "Gutten"));
        armyTwo.addAllUnits(GetUnitFactory.getXUnits(UnitType.InfantryUnit, 4, "Jenta"));

        Battle testBattle = new Battle(armyOne,armyTwo,Terrain.Plains);
        assertEquals(1,armyOne.getRandomUnit().getAttackBonus(UnitType.valueOf(armyTwo.getRandomUnit().getClass().getSimpleName()), Terrain.Forest));
    }
}

