package nl.hanze.hive;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class QueenBeeSpec {
    @Test
    void givenBeetleCanSlideOneTileThenTrue() {
        Board board = new Board();
        QueenBee queenBee = new QueenBee();
        int fromQ = 0;
        int fromR = 0;
        int toQ = 1;
        int toR = 0;
        Stone stoneQueenBee = new Stone(Hive.Player.WHITE, Hive.Tile.QUEEN_BEE);
        Stone stoneBeetle = new Stone(Hive.Player.WHITE, Hive.Tile.BEETLE);
        board.setTile(fromQ, fromR, stoneQueenBee);
        board.setTile(1,-1, stoneBeetle);
        boolean canQueenBeeMoveOneTile = queenBee.isMoveAllowed(fromQ, fromR, toQ, toR, board);
        assertTrue(canQueenBeeMoveOneTile);
    }

    @Test
    void givenBeetleCanSlideTwoTilesThenFalse() {
        Board board = new Board();
        QueenBee queenBee = new QueenBee();
        int fromQ = 0;
        int fromR = 0;
        int toQ = 2;
        int toR = 0;
        Stone stoneQueenBee = new Stone(Hive.Player.WHITE, Hive.Tile.QUEEN_BEE);
        Stone stoneBeetle = new Stone(Hive.Player.WHITE, Hive.Tile.BEETLE);
        board.setTile(fromQ, fromR, stoneQueenBee);
        board.setTile(1,-1, stoneBeetle);
        boolean canQueenBeeMoveTwoTiles = queenBee.isMoveAllowed(fromQ, fromR, toQ, toR, board);
        assertFalse(canQueenBeeMoveTwoTiles);
    }

    @Test
    void givenQueenBeeMovesOnTopOfStoneThenFalse() {
        Board board = new Board();
        QueenBee queenBee = new QueenBee();
        int fromQ = 0;
        int fromR = 0;
        int toQ = 1;
        int toR = 0;
        Stone stoneQueenBee = new Stone(Hive.Player.WHITE, Hive.Tile.QUEEN_BEE);
        Stone stoneBeetle = new Stone(Hive.Player.WHITE, Hive.Tile.BEETLE);
        board.setTile(fromQ, fromR, stoneQueenBee);
        board.setTile(toQ, toR, stoneBeetle);
        boolean canQueenBeeMoveOnTopOfTile = queenBee.isMoveAllowed(fromQ, fromR, toQ, toR, board);
        assertFalse(canQueenBeeMoveOnTopOfTile);
    }
}
