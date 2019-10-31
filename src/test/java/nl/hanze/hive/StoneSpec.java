package nl.hanze.hive;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StoneSpec {

    @Test
    void givenTileColorBlackWhenEqualsThenTrue() {
        Stone t1 = new Stone(Hive.Player.BLACK, Hive.Tile.QUEEN_BEE);
        assertEquals(Hive.Player.BLACK, t1.getColor());
    }

    @Test
    void givenTileColorWhiteWhenEqualsThenTrue() {
        Stone t1 = new Stone(Hive.Player.WHITE, Hive.Tile.QUEEN_BEE);
        assertEquals(Hive.Player.WHITE, t1.getColor());
    }

    @Test
    void givenTileQueenWhenEqualsThenTrue() {
        Stone t1 = new Stone(Hive.Player.BLACK, Hive.Tile.QUEEN_BEE);
        System.out.println(t1.getTile());
        assertEquals(Hive.Tile.QUEEN_BEE, t1.getTile());
    }

    @Test
    void givenTileSpiderWhenEqualsThenTrue() {
        Stone t1 = new Stone(Hive.Player.BLACK, Hive.Tile.SPIDER);
        assertEquals(Hive.Tile.SPIDER, t1.getTile());
    }

    @Test
    void givenTileBeetleWhenEqualsThenTrue() {
        Stone t1 = new Stone(Hive.Player.BLACK, Hive.Tile.BEETLE);
        assertEquals(Hive.Tile.BEETLE, t1.getTile());
    }

    @Test
    void givenTileGrasshopperWhenEqualsThenTrue() {
        Stone t1 = new Stone(Hive.Player.BLACK, Hive.Tile.GRASSHOPPER);
        assertEquals(Hive.Tile.GRASSHOPPER, t1.getTile());
    }

    @Test
    void givenTileSoldierAntWhenEqualsThenTrue() {
        Stone t1 = new Stone(Hive.Player.BLACK, Hive.Tile.SOLDIER_ANT);
        assertEquals(Hive.Tile.SOLDIER_ANT, t1.getTile());
    }

}
