package nl.hanze.hive;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

import static org.junit.jupiter.api.Assertions.*;


public class PlayerSpec {
    @Test
    void givenPlayerHasStartingTilesWhenEqualsThenTrue() {
        Hive.Player color = Hive.Player.WHITE;
        Player p1 = new Player(color);
        ArrayList<Hive.Tile> startingTiles = new ArrayList<Hive.Tile>();

        Tile queenBeeTile = new Tile(color, Hive.Tile.QUEEN_BEE);
        Tile spiderTile = new Tile(color, Hive.Tile.SPIDER);
        Tile soldierAntTile = new Tile(color, Hive.Tile.SOLDIER_ANT);
        Tile grasshopperTile = new Tile(color, Hive.Tile.GRASSHOPPER);
        Tile beetleTile = new Tile(color, Hive.Tile.BEETLE);

        startingTiles.add(queenBeeTile.getTile());
        startingTiles.add(spiderTile.getTile());
        startingTiles.add(spiderTile.getTile());
        startingTiles.add(soldierAntTile.getTile());
        startingTiles.add(soldierAntTile.getTile());
        startingTiles.add(soldierAntTile.getTile());
        startingTiles.add(grasshopperTile.getTile());
        startingTiles.add(grasshopperTile.getTile());
        startingTiles.add(grasshopperTile.getTile());
        startingTiles.add(beetleTile.getTile());
        startingTiles.add(beetleTile.getTile());

        ArrayList<Hive.Tile> playerStartingTiles = new ArrayList<>();
        ArrayList<Tile> p1ST = p1.getTiles();
        for(int i = 0; i < p1ST.size(); i ++) {
            Tile t = p1ST.get(i);
            playerStartingTiles.add(t.getTile());
        }

        assertEquals(startingTiles, playerStartingTiles);
    }

    @Test
    void givenPlayerCanPlayTileThenTrue() {
        Player pWhite = new Player(Hive.Player.WHITE);
        Player pBlack = new Player(Hive.Player.BLACK);
        Board b = new Board();
        Game g = new Game(pWhite, pBlack, b);
        ArrayList<Tile> tiles = pWhite.getTiles();
        Tile tileToPlay = tiles.get(0);
        ArrayList<Integer> coords = new ArrayList<>();
        coords.add(0);
        coords.add(0);
        try {
            pWhite.playTile(g, tileToPlay, b, coords.get(0), coords.get(1));
        } catch (Hive.IllegalMove illegalMove) {
            illegalMove.printStackTrace();
            assertTrue(false);
        }
        ArrayList<Tile> afterPlayTiles = pWhite.getTiles();

        HashMap<ArrayList<Integer>, Stack<Tile>> expecBoard = new HashMap<ArrayList<Integer>, Stack<Tile>>();
        Stack<Tile> tileStack = new Stack<Tile>();
        tileStack.push(tileToPlay);
        expecBoard.put(coords, tileStack);
        ArrayList<Tile> expecTiles = new ArrayList<>();
        expecTiles.addAll(tiles);
        expecTiles.remove(tileToPlay);

        assertEquals(expecTiles, afterPlayTiles);
        assertEquals(expecBoard, b.getBoard());
    }

    @Test
    void givenPlayerCanMoveTileThenTrue() {
        Player pWhite = new Player(Hive.Player.WHITE);
        Player pBlack = new Player(Hive.Player.BLACK);
        Board b = new Board();
        Game g = new Game(pWhite, pBlack, b);
        ArrayList<Tile> tilesP1 = pWhite.getTiles();
        Tile tileToPlayAndMove = tilesP1.get(0);
        ArrayList<Integer> fromCoords = new ArrayList<>();
        fromCoords.add(0);
        fromCoords.add(0);
        ArrayList<Integer> toCoords = new ArrayList<>();
        toCoords.add(1);
        toCoords.add(0);
        try {
            pWhite.playTile(g, tileToPlayAndMove, b, fromCoords.get(0), fromCoords.get(1));
        } catch (Hive.IllegalMove illegalMove) {
            illegalMove.printStackTrace();
            assertTrue(false);
        }
        try {
            pWhite.moveTile(g, b, fromCoords.get(0), fromCoords.get(1), toCoords.get(0), toCoords.get(1));
        } catch (Hive.IllegalMove illegalMove) {
            illegalMove.printStackTrace();
        }

        HashMap<ArrayList<Integer>, Stack<Tile>> expecBoard = new HashMap<ArrayList<Integer>, Stack<Tile>>();
        Stack<Tile> tileStack = new Stack<Tile>();
        tileStack.push(tileToPlayAndMove);
        expecBoard.put(toCoords, tileStack);

        assertEquals(expecBoard, b.getBoard());
    }

    @Test
    void givenPlayerCanPassThenTrue() {
        Player pWhite = new Player(Hive.Player.WHITE);
        Player pBlack = new Player(Hive.Player.BLACK);
        Board board = new Board();
        Game g = new Game(pWhite, pBlack, board);

        Player currentPlayer = g.getCurrentPlayer();
        currentPlayer.pass(g);
        currentPlayer = g.getCurrentPlayer();

        assertEquals(pBlack, currentPlayer);
    }

    @Test
    void givenPlayerTilesContainsTileToPlayThenTrue() {
        Player pWhite = new Player(Hive.Player.WHITE);
        Player pBlack = new Player(Hive.Player.BLACK);
        Board board = new Board();
        Game game = new Game(pWhite, pBlack, board);

        ArrayList<Tile> whitesTiles = pWhite.getTiles();
        Tile queenBee = whitesTiles.get(0);

        try {
            pWhite.playTile(game, queenBee, board, 0, 0);
        } catch (Hive.IllegalMove illegalMove) {
            assertTrue(false, "Shouldn't throw exception yet!");
        }

        assertThrows(Hive.IllegalMove.class, () -> {
            pWhite.playTile(game, queenBee, board, 0, 0);
        });
    }

    @Test
    void givenTileOnEmptySpotWhenPlayerPlaysTileThenTrue() {
        Player pWhite = new Player(Hive.Player.WHITE);
        Player pBlack = new Player(Hive.Player.BLACK);
        Board board = new Board();
        Game game = new Game(pWhite, pBlack, board);

        ArrayList<Tile> whitesTiles = pWhite.getTiles();
        Tile queenBee = whitesTiles.get(0);
        Tile spiderTile = whitesTiles.get(1);

        try {
            pWhite.playTile(game, queenBee, board, 0, 0);
        } catch (Hive.IllegalMove illegalMove) {
            assertTrue(false, "Shouldn't throw exception yet!");
        }

        assertThrows(Hive.IllegalMove.class, () -> {
            pWhite.playTile(game, spiderTile, board, 0, 0);
        });
    }

    @Test
    void givenTilesOnBoardWhenPlayerPlaysTileThenShouldBeNextToOtherTileThenTrue() {
        Player pWhite = new Player(Hive.Player.WHITE);
        Player pBlack = new Player(Hive.Player.BLACK);
        Board board = new Board();
        Game game = new Game(pWhite, pBlack, board);

        Tile tile = new Tile(Hive.Player.WHITE, Hive.Tile.BEETLE);
        board.setTile(0,0, tile);

        ArrayList<Tile> whitesTiles = pWhite.getTiles();
        try {
            pWhite.playTile(game, whitesTiles.get(0), board, 0, 1);
        } catch (Hive.IllegalMove illegalMove) {
            assertTrue(false, "Shouldn't throw exception yet!");
        }

        assertThrows(Hive.IllegalMove.class, () -> {
            pWhite.playTile(game, whitesTiles.get(5), board, 3, 3);
        });
    }

    @Test
    void givenBothPlayersHaveTilesOnBoardNewlyPlayedTilesAreNotNextToEnemyTilesThenTrue() {
        Player pWhite = new Player(Hive.Player.WHITE);
        Player pBlack = new Player(Hive.Player.BLACK);
        Board board = new Board();
        Game game = new Game(pWhite, pBlack, board);

        try {
            pWhite.playTile(game, pWhite.getTiles().get(0), board, 0, 0);
            pBlack.playTile(game, pBlack.getTiles().get(0), board, 0, 1);
            pWhite.playTile(game, pWhite.getTiles().get(0), board, 1, -1);
        } catch (Hive.IllegalMove illegalMove) {
            assertTrue(false, "Shouldn't throw exception yet!");
        }

        assertThrows(Hive.IllegalMove.class, () -> {
            pBlack.playTile(game, pBlack.getTiles().get(0), board, 1, 0);
        });
    }

    @Test
    void givenPlayerPlayedThreeTilesWhenNoQueenBeeIsPlayedThenShouldPlayQueenBee() {
        Player pWhite = new Player(Hive.Player.WHITE);
        Player pBlack = new Player(Hive.Player.BLACK);
        Board board = new Board();
        Game game = new Game(pWhite, pBlack, board);

        ArrayList<Integer> coords = new ArrayList<>();
        coords.add(1); // White
        coords.add(0);
        coords.add(0); // Black
        coords.add(0);
        coords.add(2); // White
        coords.add(0);
        coords.add(-1); // Black
        coords.add(1);
        coords.add(1); // White
        coords.add(1);
        coords.add(0); // Black
        coords.add(-1);
        // After this it's white's turn and he should play the queen bee.
        int q = 0;
        int r = 1;
        for (int i = 0; i < 6; i++) {
            Player currentPlayer = game.getCurrentPlayer();
            ArrayList<Tile> tilesCurrentPlayer = currentPlayer.getTiles();
            Tile tileToPlay = null;
            for (Tile tile : tilesCurrentPlayer) {
                if (tile.getTile() == Hive.Tile.SOLDIER_ANT) {
                    tileToPlay = tile;
                    break;
                }
            }
            try {
                currentPlayer.playTile(game, tileToPlay, board, coords.get(q), coords.get(r));
            } catch (Hive.IllegalMove illegalMove) {
                illegalMove.printStackTrace();
            }
            q += 2;
            r += 2;
        }
        // White plays the queen bee, which should be fine
        try {
            pWhite.playTile(game, pWhite.getTiles().get(0), board, 3, 0);
        } catch (Hive.IllegalMove illegalMove) {
            assertTrue(false, "Shouldn't throw exception yet!");
        }

        // Black tries to play a spider which shouldnt be allowed.
        assertThrows(Hive.IllegalMove.class, () -> {
            pBlack.playTile(game, pBlack.getTiles().get(1), board, -1, 0);
        });
    }

    @Test
    void givenPlayerCanOnlyMovesOwnPlacedTilesThenTrue() {
        Player pWhite = new Player(Hive.Player.WHITE);
        Player pBlack = new Player(Hive.Player.BLACK);
        Board board = new Board();
        Game game = new Game(pWhite, pBlack, board);

        // Play Queen Bee Tile first
        ArrayList<Tile> tilesWhite = pWhite.getTiles();
        Tile queenBeeTile = tilesWhite.get(0); // Queen Bee
        try {
            pWhite.playTile(game, queenBeeTile, board, 2, 0);
        } catch (Hive.IllegalMove illegalMove) {
            illegalMove.printStackTrace();
        }

        Tile tile = new Tile(Hive.Player.WHITE, Hive.Tile.BEETLE);
        int fromQWhiteTile = 0;
        int fromRWhiteTile = 0;
        int fromQBlackTile = 1;
        int fromRBlackTile = 1;
        board.setTile(fromQWhiteTile,fromRWhiteTile, tile);
        tile = new Tile(Hive.Player.BLACK, Hive.Tile.BEETLE);
        board.setTile(fromQBlackTile,fromRBlackTile, tile);

        try {
            pWhite.moveTile(game, board, fromQWhiteTile, fromRWhiteTile, 0,1);
        } catch (Hive.IllegalMove illegalMove) {
            assertTrue(false, "Shouldn't throw exception yet!");
        }

        assertThrows(Hive.IllegalMove.class, () -> {
            pWhite.moveTile(game, board, fromQBlackTile, fromRBlackTile, 1,2);
        });
    }

    @Test
    void givenPlayerCanOnlyMoveTilesWhenQueenBeePlayedThenTrue() {
        Player pWhite = new Player(Hive.Player.WHITE);
        Player pBlack = new Player(Hive.Player.BLACK);
        Board board = new Board();
        Tile tile = new Tile(Hive.Player.WHITE, Hive.Tile.BEETLE);
        int fromQWhiteTile = 0;
        int fromRWhiteTile = 0;
        board.setTile(fromQWhiteTile,fromRWhiteTile, tile);
        Game game = new Game(pWhite, pBlack, board);
        // No queen bee has been played
        assertThrows(Hive.IllegalMove.class, () -> {
            pWhite.moveTile(game, board, fromQWhiteTile, fromQWhiteTile, 0,1);
        });

        // Play Queen Bee Tile
        ArrayList<Tile> tilesWhite = pWhite.getTiles();
        Tile queenBeeTile = tilesWhite.get(0); // Queen Bee
        try {
            pWhite.playTile(game, queenBeeTile, board, fromQWhiteTile + 1, fromQWhiteTile);
        } catch (Hive.IllegalMove illegalMove) {
            illegalMove.printStackTrace();
        }

        try {
            pWhite.moveTile(game, board, fromQWhiteTile, fromQWhiteTile, 0,1);
        } catch (Hive.IllegalMove illegalMove) {
            assertTrue(false, "Shouldn't throw exception!");
        }
    }

    @Test
    void givenTileNeighboursATileWhenMovedThenTrue() {
        Player pWhite = new Player(Hive.Player.WHITE);
        Player pBlack = new Player(Hive.Player.BLACK);
        Board board = new Board();
        Game game = new Game(pWhite, pBlack, board);

        // Play Queen Bee Tile
        ArrayList<Tile> tilesWhite = pWhite.getTiles();
        Tile queenBeeTile = tilesWhite.get(0); // Queen Bee
        try {
            pWhite.playTile(game, queenBeeTile, board,  0, 0);
        } catch (Hive.IllegalMove illegalMove) {
            illegalMove.printStackTrace();
        }

        Tile tile = new Tile(Hive.Player.WHITE, Hive.Tile.BEETLE);
        board.setTile(1,0, tile);

        assertThrows(Hive.IllegalMove.class, () -> {
            pWhite.moveTile(game, board, 1, 0, 3, 0);
        });
    }

    @Test
    void givenNoSeparateGroupsWhenTileMovedThenTrue() {
        Player pWhite = new Player(Hive.Player.WHITE);
        Player pBlack = new Player(Hive.Player.BLACK);
        Board board = new Board();
        Game game = new Game(pWhite, pBlack, board);

        // Play Queen Bee Tile
        ArrayList<Tile> tilesWhite = pWhite.getTiles();
        Tile queenBeeTile = tilesWhite.get(0); // Queen Bee
        try {
            pWhite.playTile(game, queenBeeTile, board,  0, 0);
        } catch (Hive.IllegalMove illegalMove) {
            illegalMove.printStackTrace();
        }

        Tile tile = new Tile(Hive.Player.WHITE, Hive.Tile.BEETLE);
        board.setTile(1, 0, tile);
        tile = new Tile(Hive.Player.WHITE, Hive.Tile.BEETLE);
        board.setTile(-1, 0, tile);

        assertThrows(Hive.IllegalMove.class, () -> {
            pWhite.moveTile(game, board, 0, 0, 2, 0);
        });
    }
}
