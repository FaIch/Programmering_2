package WarGames_tests;
import Units.*;
import Battle.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BattleTest {
    @Test
    void simulate() {
        InfantryUnit footman = new InfantryUnit("Footman", 100);
        RangedUnit archer = new RangedUnit("Archer", 100);
        CavalryUnit knight = new CavalryUnit("Knight", 100);
        CommanderUnit lord = new CommanderUnit("Lord", 180);

        Army humanArmy = new Army("Human Army");

        humanArmy.addUnit(lord);
        int i;
        for (i = 0; i < 500; i++){
            humanArmy.addUnit(footman);
        }
        for (i = 0; i < 200; i++){
            humanArmy.addUnit(archer);
        }
        for (i = 0; i < 100; i++){
            humanArmy.addUnit(knight);
        }

        InfantryUnit monkey = new InfantryUnit("Monkey", 100);
        RangedUnit llama = new RangedUnit("Llama",100);
        CavalryUnit leopard = new CavalryUnit("Leopard",100);
        CommanderUnit lion = new CommanderUnit("Lion", 180);

        Army animalArmy = new Army("Animal Army");

        animalArmy.addUnit(lion);
        for (i = 0; i < 500; i++){
            animalArmy.addUnit(monkey);
        }
        for (i = 0; i < 200; i++){
            animalArmy.addUnit(llama);
        }
        for (i = 0; i < 100; i++){
            animalArmy.addUnit(leopard);
        }

        Battle battle1 = new Battle(humanArmy,animalArmy);
        System.out.println(battle1);

        Army winner = new Army("Winner Army");
        winner.addUnit(lord);

        Army loser = new Army("Loser Army");
        loser.addUnit(footman);

        Battle battle2 = new Battle(winner,loser);
        assertEquals(winner,battle2.simulate());
        assertEquals("Winner of battle is: \nWinner Army\nNumber of units remaining: \n1",
                battle2.toString());

    }
}