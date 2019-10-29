package nl.hanze.hive;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class Player {
    private final Hive.Player color;
    private ArrayList<Tile> tiles;
    private int amountOfStartingTiles;

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
        Tile beetleTile1 = new Tile(color, Hive.Tile.BEETLE);
        Tile beetleTile2 = new Tile(color, Hive.Tile.BEETLE);

        tiles.add(queenBeeTile);
        tiles.add(spiderTile1);
        tiles.add(spiderTile2);
        tiles.add(soldierAntTile1);
        tiles.add(soldierAntTile2);
        tiles.add(soldierAntTile3);
        tiles.add(grasshopperTile1);
        tiles.add(grasshopperTile2);
        tiles.add(grasshopperTile3);
        tiles.add(beetleTile1);
        tiles.add(beetleTile2);

        this.amountOfStartingTiles = tiles.size();
        this.color = color;
    }

    public Hive.Player getColor() {
        return color;
    }

    public ArrayList<Tile> getTiles() { return tiles; }

    public void playTile(Game g, Tile tileToPlay, Board b, Integer q, Integer r) throws Hive.IllegalMove {
        if (!tiles.contains(tileToPlay)) { throw new Hive.IllegalMove("Player must play a tile he owns"); }
        if (!b.getTilesOnSpot(q, r).empty()) { throw new Hive.IllegalMove("Spot already taken !!!"); }
        boolean neighbourFound = false;
        boolean neighbouringEnemy = false;
        ArrayList<ArrayList<Integer>> surroundingCoords = b.getSurroundingTiles(q, r);
        for(ArrayList<Integer> coords : surroundingCoords) {
            if (!b.getTilesOnSpot(coords.get(0), coords.get(1)).empty()) {
                neighbourFound = true;
                if (!b.getTilesOnSpot(coords.get(0), coords.get(1)).peek().getColor().equals(getColor())) {
                    neighbouringEnemy = true;
                }
            }
        }

        if (!neighbourFound && !b.getBoard().isEmpty()) { throw new Hive.IllegalMove("Tile needs neighbour"); }
        if (neighbouringEnemy && b.hasTilesOfPlayer(this)) { throw new Hive.IllegalMove("Placed tile is neighbouring an enemy tile"); }
        boolean queenbeeTileAvailable = false;
        for (Tile tile : tiles) {
            if (tile.getTile() == Hive.Tile.QUEEN_BEE) {
                queenbeeTileAvailable = true;
                break;
            }
        }
        if (queenbeeTileAvailable &&
                tiles.size() <= (amountOfStartingTiles - 3) &&
                tileToPlay.getTile() != Hive.Tile.QUEEN_BEE) { throw new Hive.IllegalMove("Player should play the queen bee tile"); }
        b.setTile(q, r, tileToPlay);
        tiles.remove(tileToPlay);
        g.nextTurn();
    }

    public void moveTile(Game g, Board b, Integer fromQ, Integer fromR, Integer toQ, Integer toR) {
        b.moveTile(fromQ, fromR, toQ, toR);
        g.nextTurn();
    }

    public void pass(Game g) {
        g.nextTurn();
    }
}
