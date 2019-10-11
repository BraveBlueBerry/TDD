package nl.hanze.hive;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

import static org.junit.jupiter.api.Assertions.*;


public class BoardSpec {

    /**
     * Elk vlak wordt aangeduid met
     * twee integercoördinaten, q en r, zoals te zien in onderstaande figuur.
     */
    @Test
    void givenQAndRForSpotWhenTileThenTrue() {
        int q = 0;
        int r = 0;
        Board board = new Board();
        Tile tile = new Tile(Hive.Player.BLACK, Hive.Tile.BEETLE);
        board.setTile(q, r, tile);
        Stack<Tile> tilesOnSpotQAndR = board.getTilesOnSpot(q, r);
        Tile tileOnSpotQAndR = tilesOnSpotQAndR.peek();
        assertEquals(tile, tileOnSpotQAndR);
    }

    /**
     * Elk van de velden heeft zes aangrenzende velden.
     */
    @Test
    void givenTileHasSixSurroundingTilesWhenEqualsThenTrue() {
        int amountSurroundingTiles = 6;
        Board board = new Board();
        ArrayList surroundingTiles = board.getSurroundingTiles(0, 0);
        int arrayLen = surroundingTiles.size();

        assertEquals(arrayLen, amountSurroundingTiles);
    }

    /**
     * Aan het begin van het spel is het speelveld leeg.
     */
    @Test
    void givenNewBoardWhenEmptyThenTrue() {
        Board board = new Board();
        HashMap<ArrayList<Integer>, Stack<Tile>> boardArray = board.getBoard();
        assertTrue(boardArray.isEmpty());
    }

    /**
     * In sommige gevallen mogen stenen verplaatst worden.
     */
    @Test
    void givenNewSpotForTileWhenMovedThenTrue() {
        HashMap<ArrayList<Integer>, Stack<Tile>> expecBoard = new HashMap<ArrayList<Integer>, Stack<Tile>>();
        ArrayList<Integer> newCoords = new ArrayList();
        Stack<Tile> movedTile = new Stack<>();
        int fromQ = 0;
        int fromR = 0;
        int toQ = 1;
        int toR = 0;
        Board board = new Board();
        Tile tile = new Tile(Hive.Player.BLACK, Hive.Tile.BEETLE);
        movedTile.push(tile);
        newCoords.add(toQ);
        newCoords.add(toR);
        expecBoard.put(newCoords, movedTile);
        board.setTile(0, 0, tile);
        board.moveTile(fromQ, fromR, toQ, toR);
        HashMap<ArrayList<Integer>, Stack<Tile>> realBoard = board.getBoard();
        assertEquals(expecBoard, realBoard);
    }

    /**
     * In sommige gevallen mogen stenen op andere stenen liggen
     */
    @Test
    void givenSpotWithTileWhenPutAnotherStoneThenTrue() {
        Stack<Tile> tilesShouldBe = new Stack<>();
        Board board = new Board();
        Tile t1 = new Tile(Hive.Player.BLACK, Hive.Tile.BEETLE);
        Tile t2 = new Tile(Hive.Player.BLACK, Hive.Tile.SPIDER);
        board.setTile(0, 0, t1);
        board.setTile(0,0, t2);
        tilesShouldBe.push(t1);
        tilesShouldBe.push(t2);
        Stack<Tile> tilesAre = board.getTilesOnSpot(0, 0);
        assertEquals(tilesShouldBe, tilesAre);
    }

    /**
     * In dat geval mag alleen de bovenste steen van een stapel verplaatst worden
     */
    @Test
    void givenStackedTilesOnlyMoveTopThenTrue() {
        HashMap<ArrayList<Integer>, Stack<Tile>> expecBoard = new HashMap<ArrayList<Integer>, Stack<Tile>>();
        ArrayList<Integer> stayedCoords = new ArrayList();
        ArrayList<Integer> movedCoords = new ArrayList();
        Stack<Tile> stayedTile = new Stack<>();
        Stack<Tile> movedTile = new Stack<>();
        Tile t1 = new Tile(Hive.Player.BLACK, Hive.Tile.BEETLE);
        Tile t2 = new Tile(Hive.Player.BLACK, Hive.Tile.SPIDER);
        int fromQ = 0;
        int fromR = 0;
        int toQ = 0;
        int toR = 1;
        stayedCoords.add(fromQ);
        stayedCoords.add(fromR);
        movedCoords.add(toQ);
        movedCoords.add(toR);
        stayedTile.push(t1);
        movedTile.push(t2);
        expecBoard.put(stayedCoords, stayedTile);
        expecBoard.put(movedCoords, movedTile);

        Board board = new Board();
        board.setTile(0, 0, t1);
        board.setTile(0,0, t2);
        board.moveTile(fromQ, fromR, toQ, toR);
        HashMap<ArrayList<Integer>, Stack<Tile>> realBoard = board.getBoard();

        assertEquals(expecBoard, realBoard);
    }
}
