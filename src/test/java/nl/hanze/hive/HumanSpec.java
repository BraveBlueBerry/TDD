package nl.hanze.hive;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

import static org.junit.jupiter.api.Assertions.*;


public class HumanSpec {
    @Test
    void givenPlayerHasStartingTilesWhenEqualsThenTrue() {
        Hive.Player color = Hive.Player.WHITE;
        Human p1 = new Human(color);
        ArrayList<Hive.Tile> startingTiles = new ArrayList<Hive.Tile>();

        Stone queenBeeStone = new Stone(color, Hive.Tile.QUEEN_BEE);
        Stone spiderStone = new Stone(color, Hive.Tile.SPIDER);
        Stone soldierAntStone = new Stone(color, Hive.Tile.SOLDIER_ANT);
        Stone grasshopperStone = new Stone(color, Hive.Tile.GRASSHOPPER);
        Stone beetleStone = new Stone(color, Hive.Tile.BEETLE);

        startingTiles.add(queenBeeStone.getTile());
        startingTiles.add(spiderStone.getTile());
        startingTiles.add(spiderStone.getTile());
        startingTiles.add(soldierAntStone.getTile());
        startingTiles.add(soldierAntStone.getTile());
        startingTiles.add(soldierAntStone.getTile());
        startingTiles.add(grasshopperStone.getTile());
        startingTiles.add(grasshopperStone.getTile());
        startingTiles.add(grasshopperStone.getTile());
        startingTiles.add(beetleStone.getTile());
        startingTiles.add(beetleStone.getTile());

        ArrayList<Hive.Tile> playerStartingTiles = new ArrayList<>();
        ArrayList<Stone> p1ST = p1.getStones();
        for(int i = 0; i < p1ST.size(); i ++) {
            Stone t = p1ST.get(i);
            playerStartingTiles.add(t.getTile());
        }

        assertEquals(startingTiles, playerStartingTiles);
    }

    @Test
    void givenPlayerCanPlayTileThenTrue() {
        Human pWhite = new Human(Hive.Player.WHITE);
        Human pBlack = new Human(Hive.Player.BLACK);
        Board b = new Board();
        Game g = new Game(pWhite, pBlack, b);
        ArrayList<Stone> stones = pWhite.getStones();
//        Stone stoneToPlay = stones.get(0);
        Stone stoneToPlay = new Stone(Hive.Player.WHITE, Hive.Tile.QUEEN_BEE);
        ArrayList<Integer> coords = new ArrayList<>();
        coords.add(0);
        coords.add(0);
        try {
            pWhite.playTile(g, stoneToPlay, b, coords.get(0), coords.get(1));
        } catch (Hive.IllegalMove illegalMove) {
            illegalMove.printStackTrace();
            assertTrue(false);
        }
        ArrayList<Stone> afterPlayStones = pWhite.getStones();

        HashMap<ArrayList<Integer>, Stack<Stone>> expecBoard = new HashMap<ArrayList<Integer>, Stack<Stone>>();
        Stack<Stone> stoneStack = new Stack<Stone>();
        stoneStack.push(stoneToPlay);
        expecBoard.put(coords, stoneStack);
        ArrayList<Stone> expecStones = new ArrayList<>();
        expecStones.addAll(stones);
        expecStones.remove(stoneToPlay);

        assertEquals(expecStones, afterPlayStones);
        assertEquals(expecBoard, b.getBoard());
    }

    @Test
    void givenPlayerCanMoveTileThenTrue() {
        Human pWhite = new Human(Hive.Player.WHITE);
        Human pBlack = new Human(Hive.Player.BLACK);
        Board b = new Board();
        Game g = new Game(pWhite, pBlack, b);
        ArrayList<Stone> tilesP1 = pWhite.getStones();
        Stone stoneToPlayAndMove = tilesP1.get(0);
        ArrayList<Integer> fromCoords = new ArrayList<>();
        fromCoords.add(0);
        fromCoords.add(0);
        ArrayList<Integer> toCoords = new ArrayList<>();
        toCoords.add(1);
        toCoords.add(0);
        ArrayList<Integer> extraCoords = new ArrayList<>();
        extraCoords.add(0);
        extraCoords.add(1);
        Stone extraStone = new Stone(Hive.Player.BLACK, Hive.Tile.BEETLE);
        b.setTile(0,1, extraStone);
        try {
            pWhite.playTile(g, stoneToPlayAndMove, b, fromCoords.get(0), fromCoords.get(1));
        } catch (Hive.IllegalMove illegalMove) {
            illegalMove.printStackTrace();
            assertTrue(false);
        }
        try {
            pWhite.moveTile(g, b, fromCoords.get(0), fromCoords.get(1), toCoords.get(0), toCoords.get(1));
        } catch (Hive.IllegalMove illegalMove) {
            illegalMove.printStackTrace();
        }

        HashMap<ArrayList<Integer>, Stack<Stone>> expecBoard = new HashMap<ArrayList<Integer>, Stack<Stone>>();
        Stack<Stone> stoneStack = new Stack<Stone>();
        Stack<Stone> extraStoneStack = new Stack<Stone>();
        stoneStack.push(stoneToPlayAndMove);
        extraStoneStack.push(extraStone);
        expecBoard.put(toCoords, stoneStack);
        expecBoard.put(extraCoords, extraStoneStack);


        assertEquals(expecBoard, b.getBoard());
    }

    @Test
    void givenPlayerCanPassThenTrue() {
        Human pWhite = new Human(Hive.Player.WHITE);
        Human pBlack = new Human(Hive.Player.BLACK);
        Board board = new Board();
        Game g = new Game(pWhite, pBlack, board);

        Human currentHuman = g.getCurrentHuman();
        currentHuman.pass(g);
        currentHuman = g.getCurrentHuman();

        assertEquals(pBlack, currentHuman);
    }

    @Test
    void givenPlayerTilesContainsTileToPlayThenTrue() {
        Human pWhite = new Human(Hive.Player.WHITE);
        Human pBlack = new Human(Hive.Player.BLACK);
        Board board = new Board();
        Game game = new Game(pWhite, pBlack, board);

        ArrayList<Stone> whitesStones = pWhite.getStones();
        Stone queenBee = whitesStones.get(0);

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
        Human pWhite = new Human(Hive.Player.WHITE);
        Human pBlack = new Human(Hive.Player.BLACK);
        Board board = new Board();
        Game game = new Game(pWhite, pBlack, board);

        ArrayList<Stone> whitesStones = pWhite.getStones();
        Stone queenBee = whitesStones.get(0);
        Stone spiderStone = whitesStones.get(1);

        try {
            pWhite.playTile(game, queenBee, board, 0, 0);
        } catch (Hive.IllegalMove illegalMove) {
            assertTrue(false, "Shouldn't throw exception yet!");
        }

        assertThrows(Hive.IllegalMove.class, () -> {
            pWhite.playTile(game, spiderStone, board, 0, 0);
        });
    }

    @Test
    void givenTilesOnBoardWhenPlayerPlaysTileThenShouldBeNextToOtherTileThenTrue() {
        Human pWhite = new Human(Hive.Player.WHITE);
        Human pBlack = new Human(Hive.Player.BLACK);
        Board board = new Board();
        Game game = new Game(pWhite, pBlack, board);

        Stone stone = new Stone(Hive.Player.WHITE, Hive.Tile.BEETLE);
        board.setTile(0,0, stone);

        ArrayList<Stone> whitesStones = pWhite.getStones();
        try {
            pWhite.playTile(game, whitesStones.get(0), board, 0, 1);
        } catch (Hive.IllegalMove illegalMove) {
            assertTrue(false, "Shouldn't throw exception yet!");
        }

        assertThrows(Hive.IllegalMove.class, () -> {
            pWhite.playTile(game, whitesStones.get(5), board, 3, 3);
        });
    }

    @Test
    void givenBothPlayersHaveTilesOnBoardNewlyPlayedTilesAreNotNextToEnemyTilesThenTrue() {
        Human pWhite = new Human(Hive.Player.WHITE);
        Human pBlack = new Human(Hive.Player.BLACK);
        Board board = new Board();
        Game game = new Game(pWhite, pBlack, board);

        try {
            pWhite.playTile(game, pWhite.getStones().get(0), board, 0, 0);
            pBlack.playTile(game, pBlack.getStones().get(0), board, 0, 1);
            pWhite.playTile(game, pWhite.getStones().get(0), board, 1, -1);
        } catch (Hive.IllegalMove illegalMove) {
            assertTrue(false, "Shouldn't throw exception yet!");
        }

        assertThrows(Hive.IllegalMove.class, () -> {
            pBlack.playTile(game, pBlack.getStones().get(0), board, 1, 0);
        });
    }

    @Test
    void givenPlayerPlayedThreeTilesWhenNoQueenBeeIsPlayedThenShouldPlayQueenBee() {
        Human pWhite = new Human(Hive.Player.WHITE);
        Human pBlack = new Human(Hive.Player.BLACK);
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
            Human currentHuman = game.getCurrentHuman();
            ArrayList<Stone> tilesCurrentPlayer = currentHuman.getStones();
            Stone stoneToPlay = null;
            for (Stone stone : tilesCurrentPlayer) {
                if (stone.getTile() == Hive.Tile.SOLDIER_ANT) {
                    stoneToPlay = stone;
                    break;
                }
            }
            try {
                currentHuman.playTile(game, stoneToPlay, board, coords.get(q), coords.get(r));
            } catch (Hive.IllegalMove illegalMove) {
                illegalMove.printStackTrace();
            }
            q += 2;
            r += 2;
        }
        // White plays the queen bee, which should be fine
        try {
            pWhite.playTile(game, pWhite.getStones().get(0), board, 3, 0);
        } catch (Hive.IllegalMove illegalMove) {
            assertTrue(false, "Shouldn't throw exception yet!");
        }

        // Black tries to play a spider which shouldnt be allowed.
        assertThrows(Hive.IllegalMove.class, () -> {
            pBlack.playTile(game, pBlack.getStones().get(1), board, -1, 0);
        });
    }

    @Test
    void givenPlayerCanOnlyMovesOwnPlacedTilesThenTrue() {
        Human pWhite = new Human(Hive.Player.WHITE);
        Human pBlack = new Human(Hive.Player.BLACK);
        Board board = new Board();
        Game game = new Game(pWhite, pBlack, board);

        // Play Queen Bee Tile first
        ArrayList<Stone> tilesWhite = pWhite.getStones();
        Stone queenBeeStone = tilesWhite.get(0); // Queen Bee
        try {
            pWhite.playTile(game, queenBeeStone, board, 2, 0);
        } catch (Hive.IllegalMove illegalMove) {
            illegalMove.printStackTrace();
        }

        Stone stone = new Stone(Hive.Player.WHITE, Hive.Tile.BEETLE);
        int fromQWhiteTile = 1;
        int fromRWhiteTile = 0;
        int fromQBlackTile = 1;
        int fromRBlackTile = 1;
        board.setTile(fromQWhiteTile,fromRWhiteTile, stone);
        stone = new Stone(Hive.Player.BLACK, Hive.Tile.BEETLE);
        board.setTile(fromQBlackTile,fromRBlackTile, stone);

        try {
            pWhite.moveTile(game, board, fromQWhiteTile, fromRWhiteTile, 0,1);
        } catch (Hive.IllegalMove illegalMove) {
            System.out.println(illegalMove.getMessage());
            assertTrue(false, "Shouldn't throw exception yet!");
        }

        assertThrows(Hive.IllegalMove.class, () -> {
            pWhite.moveTile(game, board, fromQBlackTile, fromRBlackTile, 1,2);
        });
    }

    @Test
    void givenPlayerCanOnlyMoveTilesWhenQueenBeePlayedThenTrue() {
        Human pWhite = new Human(Hive.Player.WHITE);
        Human pBlack = new Human(Hive.Player.BLACK);
        Board board = new Board();
        Stone stone = new Stone(Hive.Player.WHITE, Hive.Tile.BEETLE);
        int fromQWhiteTile = 0;
        int fromRWhiteTile = 0;
        board.setTile(fromQWhiteTile,fromRWhiteTile, stone);
        Game game = new Game(pWhite, pBlack, board);
        // No queen bee has been played
        assertThrows(Hive.IllegalMove.class, () -> {
            pWhite.moveTile(game, board, fromQWhiteTile, fromQWhiteTile, 0,1);
        });

        // Play Queen Bee Tile
        ArrayList<Stone> tilesWhite = pWhite.getStones();
        Stone queenBeeStone = tilesWhite.get(0); // Queen Bee
        try {
            pWhite.playTile(game, queenBeeStone, board, fromQWhiteTile + 1, fromQWhiteTile);
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
        Human pWhite = new Human(Hive.Player.WHITE);
        Human pBlack = new Human(Hive.Player.BLACK);
        Board board = new Board();
        Game game = new Game(pWhite, pBlack, board);

        // Play Queen Bee Tile
        ArrayList<Stone> tilesWhite = pWhite.getStones();
        Stone queenBeeStone = tilesWhite.get(0); // Queen Bee
        try {
            pWhite.playTile(game, queenBeeStone, board,  0, 0);
        } catch (Hive.IllegalMove illegalMove) {
            illegalMove.printStackTrace();
        }

        Stone stone = new Stone(Hive.Player.WHITE, Hive.Tile.BEETLE);
        board.setTile(1,0, stone);

        assertThrows(Hive.IllegalMove.class, () -> {
            pWhite.moveTile(game, board, 1, 0, 3, 0);
        });
    }

    @Test
    void givenNoSeparateGroupsWhenTileMovedThenTrue() {
        Human pWhite = new Human(Hive.Player.WHITE);
        Human pBlack = new Human(Hive.Player.BLACK);
        Board board = new Board();
        Game game = new Game(pWhite, pBlack, board);

        // Play Queen Bee Tile
        ArrayList<Stone> tilesWhite = pWhite.getStones();
        Stone queenBeeStone = tilesWhite.get(0); // Queen Bee
        try {
            pWhite.playTile(game, queenBeeStone, board,  0, 0);
        } catch (Hive.IllegalMove illegalMove) {
            illegalMove.printStackTrace();
        }

        Stone stone = new Stone(Hive.Player.WHITE, Hive.Tile.BEETLE);
        board.setTile(1, 0, stone);
        stone = new Stone(Hive.Player.WHITE, Hive.Tile.BEETLE);
        board.setTile(-1, 0, stone);

        assertThrows(Hive.IllegalMove.class, () -> {
            pWhite.moveTile(game, board, 0, 0, 2, 0);
        });
    }
}
