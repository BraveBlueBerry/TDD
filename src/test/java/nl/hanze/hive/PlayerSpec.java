package nl.hanze.hive;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;


public class PlayerSpec {
    @Test
    void givenPlayerHasStartingTilesWhenEqualsThenTrue() {
        Hive.Player color = Hive.Player.WHITE;
        Player p1 = new Player(color);
        ArrayList<Hive.Tile> startingTiles = new ArrayList<Hive.Tile>();

        Tile queenBeeTile = new Tile(color, Hive.Tile.QUEEN_BEE);
        Tile spiderTile = new Tile(color, Hive.Tile.SPIDER);
        Tile soldierAntTile = new Tile(color, Hive.Tile.SOLDIER_ANT);
        Tile grasshopperTile = new Tile(color, Hive.Tile.GRASSHOPPER);

        startingTiles.add(queenBeeTile.getTile());
        startingTiles.add(spiderTile.getTile());
        startingTiles.add(spiderTile.getTile());
        startingTiles.add(soldierAntTile.getTile());
        startingTiles.add(soldierAntTile.getTile());
        startingTiles.add(soldierAntTile.getTile());
        startingTiles.add(grasshopperTile.getTile());
        startingTiles.add(grasshopperTile.getTile());
        startingTiles.add(grasshopperTile.getTile());

        ArrayList<Hive.Tile> playerStartingTiles = new ArrayList<>();
        ArrayList<Tile> p1ST = p1.getTiles();
        for(int i = 0; i < p1ST.size(); i ++) {
            Tile t = p1ST.get(i);
            playerStartingTiles.add(t.getTile());
        }

        assertEquals(startingTiles, playerStartingTiles);
    }

    @Test
    void givenPlayerCanPlayTileThenTrue() {
        Board b = new Board();
        Player p1 = new Player(Hive.Player.WHITE);
        ArrayList<Tile> tiles = p1.getTiles();
        Tile tileToPlay = tiles.get(0);
        ArrayList<Integer> coords = new ArrayList<>();
        coords.add(0);
        coords.add(0);
        p1.playTile(tileToPlay, b, coords.get(0), coords.get(1));

        HashMap<ArrayList<Integer>, Stack<Tile>> expecBoard = new HashMap<ArrayList<Integer>, Stack<Tile>>();
        Stack<Tile> tileStack = new Stack<Tile>();
        tileStack.push(tileToPlay);
        expecBoard.put(coords, tileStack);

        assertEquals(expecBoard, b.getBoard());
    }
}
