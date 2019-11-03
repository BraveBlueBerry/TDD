package nl.hanze.hive;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BeetleSpec {
    @Test
    void givenBeetleCanSlideOneTileThenTrue() {
        Board board = new Board();
        Beetle beetle = new Beetle();
        int fromQ = 0;
        int fromR = 0;
        int toQ = 1;
        int toR = 0;
        Stone stoneBeetle1 = new Stone(Hive.Player.WHITE, Hive.Tile.BEETLE);
        Stone stoneBeetle2 = new Stone(Hive.Player.WHITE, Hive.Tile.BEETLE);
        board.setTile(fromQ, fromR, stoneBeetle1);
        board.setTile(1,-1, stoneBeetle2);
        boolean canBeetleMoveOneTile = beetle.isMoveAllowed(fromQ, fromR, toQ, toR, board);
        assertTrue(canBeetleMoveOneTile);
    }

    @Test
    void givenBeetleCanSlideTwoTilesThenFalse() {
        Board board = new Board();
        Beetle beetle = new Beetle();
        int fromQ = 0;
        int fromR = 0;
        int toQ = 2;
        int toR = 0;
        Stone stoneBeetle1 = new Stone(Hive.Player.WHITE, Hive.Tile.BEETLE);
        Stone stoneBeetle2 = new Stone(Hive.Player.WHITE, Hive.Tile.BEETLE);
        board.setTile(fromQ, fromR, stoneBeetle1);
        board.setTile(1,-1, stoneBeetle2);
        boolean canBeetleMoveTwoTiles = beetle.isMoveAllowed(fromQ, fromR, toQ, toR, board);
        assertFalse(canBeetleMoveTwoTiles);
    }
}
