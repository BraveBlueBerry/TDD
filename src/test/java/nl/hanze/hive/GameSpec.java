package nl.hanze.hive;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class GameSpec {

    @Test
    void givenWhiteStartsWhenNewGameThenTrue() {
        Human pWhite = new Human(Hive.Player.WHITE);
        Human pBlack = new Human(Hive.Player.BLACK);
        Board board = new Board();
        Game game = new Game(pWhite, pBlack, board);
        Human currentHuman = game.getCurrentHuman();

        assertEquals(currentHuman, pWhite);
    }

    @Test
    void givenPlayerWonWhenQueenBeeIsSurroundedThenTrue() {
        Human pWhite = new Human(Hive.Player.WHITE);
        Human pBlack = new Human(Hive.Player.BLACK);
        Board board = new Board();
        Stone queenBeeStone = new Stone(Hive.Player.WHITE, Hive.Tile.QUEEN_BEE);
        ArrayList<Stone> tilesToSurroundQueenBee = new ArrayList<>();
        Stone spiderStone1 = new Stone(Hive.Player.BLACK, Hive.Tile.SPIDER);
        Stone spiderStone2 = new Stone(Hive.Player.BLACK, Hive.Tile.SPIDER);
        Stone spiderStone3 = new Stone(Hive.Player.BLACK, Hive.Tile.SPIDER);
        Stone spiderStone4 = new Stone(Hive.Player.BLACK, Hive.Tile.SPIDER);
        Stone spiderStone5 = new Stone(Hive.Player.BLACK, Hive.Tile.SPIDER);
        tilesToSurroundQueenBee.add(spiderStone1);
        tilesToSurroundQueenBee.add(spiderStone2);
        tilesToSurroundQueenBee.add(spiderStone3);
        tilesToSurroundQueenBee.add(spiderStone4);
        tilesToSurroundQueenBee.add(spiderStone5);
        board.setTile(0, 0, queenBeeStone);
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

        board.setTile(playCoords.get(0), playCoords.get(1), new Stone(Hive.Player.BLACK, Hive.Tile.BEETLE));
        game = new Game (pWhite, pBlack, board);

        isWinner = game.isThereAWinner();
        assertTrue(isWinner);
    }

    @Test
    void givenItsADrawWhenBothPlayersWonThenTrue() {
        Human pWhite = new Human(Hive.Player.WHITE);
        Human pBlack = new Human(Hive.Player.BLACK);
        Board board = new Board();
        ArrayList<Stone> whiteTilesForBoard = new ArrayList<>();
        ArrayList<Stone> blackTilesForBoard = new ArrayList<>();
        Stone whiteQueen = new Stone(Hive.Player.WHITE, Hive.Tile.QUEEN_BEE);
        Stone blackQueen = new Stone(Hive.Player.BLACK, Hive.Tile.QUEEN_BEE);
        for (int i = 0; i < 5; i ++) {
            whiteTilesForBoard.add(new Stone(Hive.Player.WHITE, Hive.Tile.BEETLE));
            blackTilesForBoard.add(new Stone(Hive.Player.BLACK, Hive.Tile.BEETLE));
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

        board.setTile(0, 0, new Stone(Hive.Player.WHITE, Hive.Tile.BEETLE));
        game = new Game(pWhite, pBlack, board);
        assertTrue(game.getIsDraw());
    }
}
