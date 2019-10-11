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
    // private ArrayList<ArrayList> board;
    private HashMap<ArrayList<Integer>, Stack<Tile>> board;

    public Board() {
        board = new HashMap<ArrayList<Integer>, Stack<Tile>>();
    }

    public HashMap<ArrayList<Integer>, Stack<Tile>> getBoard() {
        return board;
    }

    public ArrayList getSurroundingTiles(int q, int r) {
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

    public void setTile(int q, int r, Tile tile) {
        ArrayList<Integer> coords = new ArrayList<>();
        coords.add(q);
        coords.add(r);
        Stack<Tile> tiles = new Stack<>();
        if (board.get(coords) != null){
            tiles = board.get(coords);
        }

        tiles.push(tile);
        board.put(coords, tiles);
    }

    public Stack getTilesOnSpot(int q, int r) {
        ArrayList<Integer> coords = new ArrayList<>();
        coords.add(q);
        coords.add(r);
        Stack<Tile> tiles = board.get(coords);
        System.out.println(tiles);
        return tiles;
    }

    public void moveTile(int fromQ, int fromR, int toQ, int toR) {
        ArrayList<Integer> fromCoords = new ArrayList<>();
        fromCoords.add(fromQ);
        fromCoords.add(fromR);
        Stack<Tile> tilesOnSpot = board.get(fromCoords);
        System.out.println(tilesOnSpot);
        Tile tileToMove = tilesOnSpot.pop();
        setTile(toQ, toR, tileToMove);
        if (tilesOnSpot.empty()) {
            System.out.println(tilesOnSpot);
            board.remove(fromCoords);
        }
    }
}
