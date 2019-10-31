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
        Stone stone = new Stone(Hive.Player.BLACK, Hive.Tile.BEETLE);
        board.setTile(q, r, stone);
        Stack<Stone> tilesOnSpotQAndR = board.getTilesOnSpot(q, r);
        Stone stoneOnSpotQAndR = tilesOnSpotQAndR.peek();
        assertEquals(stone, stoneOnSpotQAndR);
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
        HashMap<ArrayList<Integer>, Stack<Stone>> boardArray = board.getBoard();
        assertTrue(boardArray.isEmpty());
    }

    /**
     * In sommige gevallen mogen stenen verplaatst worden.
     */
    @Test
    void givenNewSpotForTileWhenMovedThenTrue() {
        HashMap<ArrayList<Integer>, Stack<Stone>> expecBoard = new HashMap<ArrayList<Integer>, Stack<Stone>>();
        ArrayList<Integer> newCoords = new ArrayList();
        Stack<Stone> movedStone = new Stack<>();
        int fromQ = 0;
        int fromR = 0;
        int toQ = 1;
        int toR = 0;
        Board board = new Board();
        Stone stone = new Stone(Hive.Player.BLACK, Hive.Tile.BEETLE);
        movedStone.push(stone);
        newCoords.add(toQ);
        newCoords.add(toR);
        expecBoard.put(newCoords, movedStone);
        board.setTile(0, 0, stone);
        board.moveTile(fromQ, fromR, toQ, toR);
        HashMap<ArrayList<Integer>, Stack<Stone>> realBoard = board.getBoard();
        assertEquals(expecBoard, realBoard);
    }

    /**
     * In sommige gevallen mogen stenen op andere stenen liggen
     */
    @Test
    void givenSpotWithTileWhenPutAnotherStoneThenTrue() {
        Stack<Stone> tilesShouldBe = new Stack<>();
        Board board = new Board();
        Stone t1 = new Stone(Hive.Player.BLACK, Hive.Tile.BEETLE);
        Stone t2 = new Stone(Hive.Player.BLACK, Hive.Tile.SPIDER);
        board.setTile(0, 0, t1);
        board.setTile(0,0, t2);
        tilesShouldBe.push(t1);
        tilesShouldBe.push(t2);
        Stack<Stone> tilesAre = board.getTilesOnSpot(0, 0);
        assertEquals(tilesShouldBe, tilesAre);
    }

    /**
     * In dat geval mag alleen de bovenste steen van een stapel verplaatst worden
     */
    @Test
    void givenStackedTilesOnlyMoveTopThenTrue() {
        HashMap<ArrayList<Integer>, Stack<Stone>> expecBoard = new HashMap<ArrayList<Integer>, Stack<Stone>>();
        ArrayList<Integer> stayedCoords = new ArrayList();
        ArrayList<Integer> movedCoords = new ArrayList();
        Stack<Stone> stayedStone = new Stack<>();
        Stack<Stone> movedStone = new Stack<>();
        Stone t1 = new Stone(Hive.Player.BLACK, Hive.Tile.BEETLE);
        Stone t2 = new Stone(Hive.Player.BLACK, Hive.Tile.SPIDER);
        int fromQ = 0;
        int fromR = 0;
        int toQ = 0;
        int toR = 1;
        stayedCoords.add(fromQ);
        stayedCoords.add(fromR);
        movedCoords.add(toQ);
        movedCoords.add(toR);
        stayedStone.push(t1);
        movedStone.push(t2);
        expecBoard.put(stayedCoords, stayedStone);
        expecBoard.put(movedCoords, movedStone);

        Board board = new Board();
        board.setTile(0, 0, t1);
        board.setTile(0,0, t2);
        board.moveTile(fromQ, fromR, toQ, toR);
        HashMap<ArrayList<Integer>, Stack<Stone>> realBoard = board.getBoard();

        assertEquals(expecBoard, realBoard);
    }
}
