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
        army.addUnit(new InfantryUnit("",1));
        assertThrows(IllegalArgumentException.class, () ->{
            Battle dummyBattle = new Battle(army,emptyArmy,"");
        });
    }

    @Test
    @DisplayName("Certain winner")
    void certainWinner() {
        Army loser = new Army("Loser");
        Army winner = new Army("Winner");
        loser.addUnit(new InfantryUnit("",1));
        winner.addUnit(new InfantryUnit("",1));
        Battle testBattle = new Battle(winner,loser,"");
        Unit randomUnit = loser.getRandomUnit();
        loser.removeUnit(randomUnit);
        assertEquals(winner, testBattle.simulate());
    }

    @Test
    void battleTest(){
        Army humanArmy = new Army("Human Army");
        Army orcArmy = new Army("Orc Army");
        humanArmy.addUnit(new CommanderUnit("Anduin",180));
        orcArmy.addUnit(new CommanderUnit("Garrosh",180));
        int i;
        for (i = 0; i < 200; i++){
            humanArmy.addUnit(new InfantryUnit("Footman",100));
            orcArmy.addUnit(new InfantryUnit("Grunt",100));
        }
        for (i = 0; i < 100; i++){
            humanArmy.addUnit(new RangedUnit("Archer",100));
            orcArmy.addUnit(new RangedUnit("Thrower",100));
        }
        for (i = 0; i < 20; i++){
            humanArmy.addUnit(new CavalryUnit("Knight", 100));
            orcArmy.addUnit(new CavalryUnit("Hog rider",100));
        }
        Battle testBattle = new Battle(humanArmy,orcArmy,"");
        System.out.println(testBattle);
    }

    @Test
    void attackBonusTest(){
        Army armyOne = new Army("Boys");
        Army armyTwo = new Army("Girls");

        armyOne.addUnit(GetUnitFactory.getUnit(UnitType.RangedUnit,"Gutten",100));
        armyTwo.addAllUnits(GetUnitFactory.getXUnits(UnitType.InfantryUnit,"Jenta",100,4));

        Battle testBattle = new Battle(armyOne,armyTwo,"Plains");
        assertEquals(1000,armyOne.getRandomUnit().getAttackBonus());
    }
}