package nl.hanze.hive;

import java.util.*;

public class Human {
    private final Hive.Player color;
    private ArrayList<Stone> stones;
    private int amountOfStartingTiles;

    public Human(Hive.Player color) {
        stones = new ArrayList<Stone>();
        Stone queenBeeStone = new Stone(color, Hive.Tile.QUEEN_BEE);
        Stone spiderStone1 = new Stone(color, Hive.Tile.SPIDER);
        Stone spiderStone2 = new Stone(color, Hive.Tile.SPIDER);
        Stone soldierAntStone1 = new Stone(color, Hive.Tile.SOLDIER_ANT);
        Stone soldierAntStone2 = new Stone(color, Hive.Tile.SOLDIER_ANT);
        Stone soldierAntStone3 = new Stone(color, Hive.Tile.SOLDIER_ANT);
        Stone grasshopperStone1 = new Stone(color, Hive.Tile.GRASSHOPPER);
        Stone grasshopperStone2 = new Stone(color, Hive.Tile.GRASSHOPPER);
        Stone grasshopperStone3 = new Stone(color, Hive.Tile.GRASSHOPPER);
        Stone beetleStone1 = new Stone(color, Hive.Tile.BEETLE);
        Stone beetleStone2 = new Stone(color, Hive.Tile.BEETLE);

        stones.add(queenBeeStone);
        stones.add(spiderStone1);
        stones.add(spiderStone2);
        stones.add(soldierAntStone1);
        stones.add(soldierAntStone2);
        stones.add(soldierAntStone3);
        stones.add(grasshopperStone1);
        stones.add(grasshopperStone2);
        stones.add(grasshopperStone3);
        stones.add(beetleStone1);
        stones.add(beetleStone2);

        this.amountOfStartingTiles = stones.size();
        this.color = color;
    }

    public Hive.Player getColor() {
        return color;
    }

    public ArrayList<Stone> getStones() { return stones; }

    public void playTile(Game g, Stone stoneToPlay, Board b, Integer q, Integer r) throws Hive.IllegalMove {
        if (!stones.contains(stoneToPlay)) { throw new Hive.IllegalMove("Player must play a tile he owns"); }
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
        for (Stone stone : stones) {
            if (stone.getTile() == Hive.Tile.QUEEN_BEE) {
                queenbeeTileAvailable = true;
                break;
            }
        }
        if (queenbeeTileAvailable &&
                stones.size() <= (amountOfStartingTiles - 3) &&
                stoneToPlay.getTile() != Hive.Tile.QUEEN_BEE) { throw new Hive.IllegalMove("Player should play the queen bee tile"); }
        b.setTile(q, r, stoneToPlay);
        stones.remove(stoneToPlay);
        g.nextTurn();
    }

    public void moveTile(Game g, Board b, Integer fromQ, Integer fromR, Integer toQ, Integer toR) throws Hive.IllegalMove {
        // Check if Queen Bee has been played
        for(Stone t : this.stones) {
            if(t.getTile() == Hive.Tile.QUEEN_BEE) { throw new Hive.IllegalMove("Player should play Queen Bee first before moving any tiles"); }
        }

        // Check if the tile to be moved is from this player
        HashMap<ArrayList<Integer>, Stack<Stone>> boardMap = b.getBoard();
        ArrayList<Integer> fromCoords = new ArrayList<>();
        fromCoords.add(fromQ);
        fromCoords.add(fromR);
        Stack<Stone> stones = boardMap.get(fromCoords);
        Stone stoneToMove = stones.peek();
        if (stoneToMove.getColor() != this.color) { throw new Hive.IllegalMove("Can only move tiles of your own color"); }

        // Check if the moved tile will land next to a tile
        boolean foundNeighbour = false;
        ArrayList<ArrayList<Integer>> surroundingToCoords = b.getSurroundingTiles(toQ, toR);
        for (ArrayList<Integer> coords : surroundingToCoords) {
            if (boardMap.containsKey(coords)) {
                Stack<Stone> st = boardMap.get(coords);
                if (!st.empty()) { foundNeighbour = true; }
            }
        }
        if (!foundNeighbour) { throw new Hive.IllegalMove("Tile should move next to a tile"); }

        // Check if moved tile doesn't make separate groups
        int lengthBoardMap = boardMap.size();
        ArrayList<Integer> toCoords = new ArrayList<>();
        toCoords.add(toQ);
        toCoords.add(toR);
        Set<ArrayList<Integer>> weirdkeys = boardMap.keySet();
        HashSet<ArrayList<Integer>> keys = new HashSet<>(weirdkeys);
        keys.remove(fromCoords);
        keys.add(toCoords);
        ArrayList<ArrayList<Integer>> visited = new ArrayList<>();
        Stack<ArrayList<Integer>> checking = new Stack<>();
        checking.push(toCoords);
        while(!checking.isEmpty()) {
            ArrayList<Integer> current = checking.pop();
            if(visited.contains(current)) { continue; }
            visited.add(current);
            ArrayList<ArrayList<Integer>> surroundingCoords = b.getSurroundingTiles(current.get(0), current.get(1));
            for (ArrayList<Integer> coords : surroundingCoords) {
                if(keys.contains(coords)) { checking.push(coords); }
            }
        }
        if(visited.size() != lengthBoardMap) { throw new Hive.IllegalMove("There are separate groups"); }

        // Is this move allowed for the tile to move
        switch(stoneToMove.getTile()) {
            case BEETLE:
                Beetle beetle = new Beetle();
                if(!beetle.isMoveAllowed(fromQ, fromR, toQ, toR, b)) { throw new Hive.IllegalMove("Beetle can't make this move"); }
                break;
            case GRASSHOPPER:
                // code block
                break;
            case QUEEN_BEE:
                QueenBee queenBee = new QueenBee();
                if(!queenBee.isMoveAllowed(fromQ, fromR, toQ, toR, b)) { throw new Hive.IllegalMove("QueenBee can't make this move"); }
                break;
            case SOLDIER_ANT:
                SoldierAnt soldierAnt = new SoldierAnt();
                if(!soldierAnt.isMoveAllowed(fromQ, fromR, toQ, toR, b)) { throw new Hive.IllegalMove("QueenBee can't make this move"); }
                break;
            case SPIDER:
                Spider spider = new Spider();
                if(!spider.isMoveAllowed(fromQ, fromR, toQ, toR, b)) { throw new Hive.IllegalMove("QueenBee can't make this move"); }
                break;
            default:
                throw new Hive.IllegalMove("The game does not recognize this type of stone");
        }
        b.moveTile(fromQ, fromR, toQ, toR);
        g.nextTurn();
    }

    public void pass(Game g) {
        g.nextTurn();
    }
}
