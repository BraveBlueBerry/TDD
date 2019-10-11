package nl.hanze.hive;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;


public class PlayerSpec {
    @Test
    void givenPlayerHasStartingTilesWhenEqualsThenTrue() {
        Player p1 = new Player();
        ArrayList<Hive.Tile> startingTiles = new ArrayList<Hive.Tile>();

        Tile queenBeeTile = new Tile(Hive.Player.BLACK, Hive.Tile.QUEEN_BEE);
        Tile spiderTile = new Tile(Hive.Player.BLACK, Hive.Tile.SPIDER);
        Tile soldierAntTile = new Tile(Hive.Player.BLACK, Hive.Tile.SOLDIER_ANT);
        Tile grasshopperTile = new Tile(Hive.Player.BLACK, Hive.Tile.GRASSHOPPER);

        startingTiles.add(queenBeeTile.getTile());
        startingTiles.add(spiderTile.getTile());
        startingTiles.add(spiderTile.getTile());
        startingTiles.add(soldierAntTile.getTile());
        startingTiles.add(soldierAntTile.getTile());
        startingTiles.add(soldierAntTile.getTile());
        startingTiles.add(grasshopperTile.getTile());
        startingTiles.add(grasshopperTile.getTile());
        startingTiles.add(grasshopperTile.getTile());

        assertEquals(startingTiles, p1.getStartingTiles());
    }
}
