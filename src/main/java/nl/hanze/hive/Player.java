package nl.hanze.hive;

import java.util.ArrayList;

public class Player {
    private final Hive.Player color;
    private ArrayList<Tile> tiles;

    public Player(Hive.Player color) {
        tiles = new ArrayList<Tile>();
        Tile queenBeeTile = new Tile(color, Hive.Tile.QUEEN_BEE);
        Tile spiderTile1 = new Tile(color, Hive.Tile.SPIDER);
        Tile spiderTile2 = new Tile(color, Hive.Tile.SPIDER);
        Tile soldierAntTile1 = new Tile(color, Hive.Tile.SOLDIER_ANT);
        Tile soldierAntTile2 = new Tile(color, Hive.Tile.SOLDIER_ANT);
        Tile soldierAntTile3 = new Tile(color, Hive.Tile.SOLDIER_ANT);
        Tile grasshopperTile1 = new Tile(color, Hive.Tile.GRASSHOPPER);
        Tile grasshopperTile2 = new Tile(color, Hive.Tile.GRASSHOPPER);
        Tile grasshopperTile3 = new Tile(color, Hive.Tile.GRASSHOPPER);

        tiles.add(queenBeeTile);
        tiles.add(spiderTile1);
        tiles.add(spiderTile2);
        tiles.add(soldierAntTile1);
        tiles.add(soldierAntTile2);
        tiles.add(soldierAntTile3);
        tiles.add(grasshopperTile1);
        tiles.add(grasshopperTile2);
        tiles.add(grasshopperTile3);

        this.color = color;
    }

    public Hive.Player getColor() {
        return color;
    }

    public ArrayList<Tile> getTiles() { return tiles; }

    public void playTile(Tile tileToPlay, Board b, Integer q, Integer r) {
        b.setTile(q, r, tileToPlay);
    }
}
