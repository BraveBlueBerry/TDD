package nl.hanze.hive;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

/**
 * Rechtsonder = + 1
 * Rechtsboven = + 9
 * Rechts = + 10
 * Linksboven = - 1
 * Linksonder = - 9
 * Links = - 10
 */
public class Board {
    private HashMap<ArrayList<Integer>, Stack<Stone>> board;

    public Board() {
        board = new HashMap<ArrayList<Integer>, Stack<Stone>>();
    }

    public HashMap<ArrayList<Integer>, Stack<Stone>> getBoard() {
        return board;
    }

    public ArrayList<ArrayList<Integer>> getSurroundingTiles(int q, int r) {
        ArrayList<ArrayList<Integer>> surroundingTiles = new ArrayList<>(6);
        for(int i=0; i < 6; i++) {
            surroundingTiles.add(new ArrayList());
        }
        // Rechtsonder
        surroundingTiles.get(0).add(q);
        surroundingTiles.get(0).add(r +1);
        // Rechtsboven
        surroundingTiles.get(1).add(q + 1);
        surroundingTiles.get(1).add(r - 1);
        // Rechts
        surroundingTiles.get(2).add(q + 1);
        surroundingTiles.get(2).add(r);
        // Linksboven
        surroundingTiles.get(3).add(q);
        surroundingTiles.get(3).add(r - 1);
        // Linksonder
        surroundingTiles.get(4).add(q - 1);
        surroundingTiles.get(4).add(r + 1);
        // Links
        surroundingTiles.get(5).add(q - 1);
        surroundingTiles.get(5).add(r);
        return surroundingTiles;
    }

    public void setTile(int q, int r, Stone stone) {
        ArrayList<Integer> coords = new ArrayList<>();
        coords.add(q);
        coords.add(r);
        Stack<Stone> stones = new Stack<>();
        if (board.get(coords) != null){
            stones = board.get(coords);
        }

        stones.push(stone);
        board.put(coords, stones);
    }

    public Stack<Stone> getTilesOnSpot(int q, int r) {
        ArrayList<Integer> coords = new ArrayList<>();
        coords.add(q);
        coords.add(r);
        Stack<Stone> stones = board.get(coords);
        if (stones == null) {
            stones = new Stack<Stone>();
        }
        return stones;
    }

    public void moveTile(int fromQ, int fromR, int toQ, int toR) {
        ArrayList<Integer> fromCoords = new ArrayList<>();
        fromCoords.add(fromQ);
        fromCoords.add(fromR);
        Stack<Stone> tilesOnSpot = board.get(fromCoords);
        Stone stoneToMove = tilesOnSpot.pop();
        setTile(toQ, toR, stoneToMove);
        if (tilesOnSpot.empty()) {
            board.remove(fromCoords);
        }
    }

    public boolean hasTilesOfPlayer(Human human) {
        Hive.Player color = human.getColor();
        for(Stack<Stone> stoneStack : board.values()) {
            if(stoneStack.peek().getColor().equals(color)) {
                return true;
            }
        }
        return false;
    }

    public int getHeightStoneStackOnSpot(int q, int r) {
        ArrayList<Integer> coords = new ArrayList<>();
        coords.add(q);
        coords.add(r);
        int amountOfTilesOnSpot = 0;
        if (board.containsKey(coords)) {
            Stack tilesOnSpot = board.get(coords);
            amountOfTilesOnSpot = tilesOnSpot.size();
        }
        return amountOfTilesOnSpot;
    }

    public boolean canStoneSlide(int fromQ, int fromR, int toQ, int toR) {
        ArrayList<Integer> fromCoords = new ArrayList<>();
        fromCoords.add(fromQ);
        fromCoords.add(fromR);
        ArrayList<Integer> toCoords = new ArrayList<>();
        toCoords.add(toQ);
        toCoords.add(toR);
        ArrayList<ArrayList<Integer>> surroundingFromTile = getSurroundingTiles(fromQ, fromR);
        ArrayList<ArrayList<Integer>> surroundingToTile = getSurroundingTiles(toQ, toR);
        ArrayList<ArrayList<Integer>> duplicateCoords = new ArrayList<>();
        for (ArrayList<Integer> coords : surroundingFromTile) {
            if (surroundingToTile.contains(coords)) { duplicateCoords.add(coords); }
        }
        if (duplicateCoords.size() != 2) { return false; } // From and to are not next to each other
        int heightN1 = getHeightStoneStackOnSpot(duplicateCoords.get(0).get(0), duplicateCoords.get(0).get(1));
        int heightN2 = getHeightStoneStackOnSpot(duplicateCoords.get(1).get(0), duplicateCoords.get(1).get(1));
        int heightFrom = getHeightStoneStackOnSpot(fromQ, fromR);
        int heightTo = getHeightStoneStackOnSpot(toQ, toR);
        if (heightN1 == 0 && heightN2 == 0 && heightTo == 0 && heightFrom > 1) { return false; } // requirement 6c
        boolean nWouldBeTooHigh = false;
        if (heightN1 > heightFrom - 1 || heightN1 > heightTo || heightN2 > heightFrom - 1 || heightN2 > heightTo) {
            nWouldBeTooHigh = true;
        };
        int amountTrappingStones = 0;
        for (ArrayList<Integer> coords : duplicateCoords) {
            if (board.containsKey(coords)) {
                amountTrappingStones += 1;
            }
        }
        if (amountTrappingStones == 2 && nWouldBeTooHigh) { return false; }

        return true;
    }
}
