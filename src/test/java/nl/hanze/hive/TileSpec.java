package nl.hanze.hive;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TileSpec {

    @Test
    void givenTileColorBlackWhenEqualsThenTrue() {
        Tile t1 = new Tile(Hive.Player.BLACK, Hive.Tile.QUEEN_BEE);
        assertEquals(Hive.Player.BLACK, t1.getColor());
    }

    @Test
    void givenTileColorWhiteWhenEqualsThenTrue() {
        Tile t1 = new Tile(Hive.Player.WHITE, Hive.Tile.QUEEN_BEE);
        assertEquals(Hive.Player.WHITE, t1.getColor());
    }

    @Test
    void givenTileQueenWhenEqualsThenTrue() {
        Tile t1 = new Tile(Hive.Player.BLACK, Hive.Tile.QUEEN_BEE);
        System.out.println(t1.getTile());
        assertEquals(Hive.Tile.QUEEN_BEE, t1.getTile());
    }

    @Test
    void givenTileSpiderWhenEqualsThenTrue() {
        Tile t1 = new Tile(Hive.Player.BLACK, Hive.Tile.SPIDER);
        assertEquals(Hive.Tile.SPIDER, t1.getTile());
    }

    @Test
    void givenTileBeetleWhenEqualsThenTrue() {
        Tile t1 = new Tile(Hive.Player.BLACK, Hive.Tile.BEETLE);
        assertEquals(Hive.Tile.BEETLE, t1.getTile());
    }

    @Test
    void givenTileGrasshopperWhenEqualsThenTrue() {
        Tile t1 = new Tile(Hive.Player.BLACK, Hive.Tile.GRASSHOPPER);
        assertEquals(Hive.Tile.GRASSHOPPER, t1.getTile());
    }

    @Test
    void givenTileSoldierAntWhenEqualsThenTrue() {
        Tile t1 = new Tile(Hive.Player.BLACK, Hive.Tile.SOLDIER_ANT);
        assertEquals(Hive.Tile.SOLDIER_ANT, t1.getTile());
    }

}
