package Battle;
import Units.*;
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
            Battle dummyBattle = new Battle(army,emptyArmy);
        });
    }

    @Test
    @DisplayName("Certain winner")
    void certainWinner() {
        Army loser = new Army("Loser");
        Army winner = new Army("Winner");
        loser.addUnit(new InfantryUnit("",1));
        winner.addUnit(new InfantryUnit("",1));
        Battle testBattle = new Battle(winner,loser);
        Unit randomUnit = loser.getRandomUnit();
        loser.removeUnit(randomUnit);
        assertEquals(winner,testBattle.simulate());
    }
}