package nl.hanze.hive;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Stack;

import static org.junit.jupiter.api.Assertions.*;

public class GameSpec {

    @Test
    void givenWhiteStartsWhenNewGameThenTrue() {
        Player pWhite = new Player(Hive.Player.WHITE);
        Player pBlack = new Player(Hive.Player.BLACK);
        Board board = new Board();
        Game game = new Game(pWhite, pBlack, board);
        Player currentPlayer = game.getCurrentPlayer();

        assertEquals(currentPlayer, pWhite);
    }

    @Test
    void givenPlayerWonWhenQueenBeeIsSurroundedThenTrue() {
        Player pWhite = new Player(Hive.Player.WHITE);
        Player pBlack = new Player(Hive.Player.BLACK);
        Board board = new Board();
        Tile queenBeeTile = new Tile(Hive.Player.WHITE, Hive.Tile.QUEEN_BEE);
        ArrayList<Tile> tilesToSurroundQueenBee = new ArrayList<>();
        Tile spiderTile1 = new Tile(Hive.Player.BLACK, Hive.Tile.SPIDER);
        Tile spiderTile2 = new Tile(Hive.Player.BLACK, Hive.Tile.SPIDER);
        Tile spiderTile3 = new Tile(Hive.Player.BLACK, Hive.Tile.SPIDER);
        Tile spiderTile4 = new Tile(Hive.Player.BLACK, Hive.Tile.SPIDER);
        Tile spiderTile5 = new Tile(Hive.Player.BLACK, Hive.Tile.SPIDER);
        tilesToSurroundQueenBee.add(spiderTile1);
        tilesToSurroundQueenBee.add(spiderTile2);
        tilesToSurroundQueenBee.add(spiderTile3);
        tilesToSurroundQueenBee.add(spiderTile4);
        tilesToSurroundQueenBee.add(spiderTile5);
        board.setTile(0, 0, queenBeeTile);
        ArrayList<ArrayList<Integer>> surroundingTiles = board.getSurroundingTiles(0, 0);
        ArrayList<Integer> playCoords = null;
        for(int i = 0; i < surroundingTiles.size(); i ++) {
            if (i == surroundingTiles.size() - 1) {
                playCoords = surroundingTiles.get(i);
            } else {
                ArrayList<Integer> coords = surroundingTiles.get(i);
                board.setTile(coords.get(0), coords.get(1), tilesToSurroundQueenBee.get(i));
            }
        }
        Game game = new Game(pWhite, pBlack, board);
        Boolean isWinner = game.isThereAWinner();
        assertFalse(isWinner);

        board.setTile(playCoords.get(0), playCoords.get(1), new Tile(Hive.Player.BLACK, Hive.Tile.BEETLE));
        game = new Game (pWhite, pBlack, board);

        isWinner = game.isThereAWinner();
        assertTrue(isWinner);
    }

    @Test
    void givenItsADrawWhenBothPlayersWonThenTrue() {
        Player pWhite = new Player(Hive.Player.WHITE);
        Player pBlack = new Player(Hive.Player.BLACK);
        Board board = new Board();
        ArrayList<Tile> whiteTilesForBoard = new ArrayList<>();
        ArrayList<Tile> blackTilesForBoard = new ArrayList<>();
        Tile whiteQueen = new Tile(Hive.Player.WHITE, Hive.Tile.QUEEN_BEE);
        Tile blackQueen = new Tile(Hive.Player.BLACK, Hive.Tile.QUEEN_BEE);
        for (int i = 0; i < 5; i ++) {
            whiteTilesForBoard.add(new Tile(Hive.Player.WHITE, Hive.Tile.BEETLE));
            blackTilesForBoard.add(new Tile(Hive.Player.BLACK, Hive.Tile.BEETLE));
        }

        board.setTile(-1, 0, whiteQueen);
        board.setTile(1,0, blackQueen);


        ArrayList<ArrayList<Integer>> surroundingWhite = board.getSurroundingTiles(-1, 0);
        ArrayList<ArrayList<Integer>> surroundingBlack = board.getSurroundingTiles(1, 0);

        // Leave coords 0, 0 for last to make it a draw
        ArrayList<Integer> removeDrawSpot = new ArrayList<>();
        removeDrawSpot.add(0);
        removeDrawSpot.add(0);
        surroundingWhite.remove(removeDrawSpot);
        surroundingBlack.remove(removeDrawSpot);

        for (int i = 0; i < whiteTilesForBoard.size(); i ++) {
            ArrayList<Integer> coords = surroundingWhite.get(i);
            board.setTile(coords.get(0), coords.get(1), whiteTilesForBoard.get(i));
            coords = surroundingBlack.get(i);
            board.setTile(coords.get(0), coords.get(1), blackTilesForBoard.get(i));
        }

        // Play the last tile that should make it a draw
        Game game = new Game(pWhite, pBlack, board);
        assertFalse(game.getIsDraw());

        board.setTile(0, 0, new Tile(Hive.Player.WHITE, Hive.Tile.BEETLE));
        game = new Game(pWhite, pBlack, board);
        assertTrue(game.getIsDraw());
    }
}
