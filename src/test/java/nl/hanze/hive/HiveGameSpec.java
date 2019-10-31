package nl.hanze.hive;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class HiveGameSpec {
    @Test
    void givenPlayerCanPlayWhenHiveGameThenTrue() {
        HiveGame hiveGame = new HiveGame();
        try {
            hiveGame.play(Hive.Tile.QUEEN_BEE, 0,0);
        } catch (Hive.IllegalMove illegalMove) {
            System.out.println("Shouldn't throw exception!");
            illegalMove.printStackTrace();
            assertTrue(false);
        }
    }

    @Test
    void givenPlayerCanMoveWhenHiveGameThenTrue() {
        HiveGame hiveGame = new HiveGame();
        try {
            hiveGame.play(Hive.Tile.QUEEN_BEE, 0,0);
            hiveGame.play(Hive.Tile.QUEEN_BEE, 1, 0);
            hiveGame.play(Hive.Tile.BEETLE, 0,-1);
            hiveGame.play(Hive.Tile.BEETLE, 2,0);
        } catch (Hive.IllegalMove illegalMove) {
            System.out.println("Shouldn't throw exception!");
            illegalMove.printStackTrace();
        }

        try {
            hiveGame.move(0,-1,1,-1);
        } catch (Hive.IllegalMove illegalMove) {
            System.out.println("Shouldn't throw exception!");
            illegalMove.printStackTrace();
            assertTrue(false);
        }
    }

    @Test
    void givenPlayerCanPassWhenHiveGameThenTrue() {
        HiveGame hiveGame = new HiveGame();
        try {
            hiveGame.play(Hive.Tile.QUEEN_BEE, 0,0);
            hiveGame.play(Hive.Tile.QUEEN_BEE, 1, 0);
            hiveGame.play(Hive.Tile.BEETLE, 0,-1);
            hiveGame.play(Hive.Tile.BEETLE, 2,0);
        } catch (Hive.IllegalMove illegalMove) {
            System.out.println("Shouldn't throw exception!");
            illegalMove.printStackTrace();
        }

        try {
            hiveGame.pass();
        } catch (Hive.IllegalMove illegalMove) {
            System.out.println("Shouldn't throw exception!");
            illegalMove.printStackTrace();
            assertTrue(false);
        }
    }
}
