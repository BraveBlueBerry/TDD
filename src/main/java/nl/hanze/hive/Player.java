package nl.hanze.hive;

import java.util.ArrayList;

public class Player {
    private ArrayList<Hive.Tile> startingTiles;

    public Player() {
        startingTiles = new ArrayList<Hive.Tile>();
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
    }

    public ArrayList<Hive.Tile> getStartingTiles() {
        return startingTiles;
    }
}
