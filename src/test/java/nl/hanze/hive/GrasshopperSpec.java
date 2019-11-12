package nl.hanze.hive;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GrasshopperSpec {
    @Test
    void givenGrassHopperCanMoveOverTileInStraightLineThenTrue() {
        Board board = new Board();
        Grasshopper grasshopper = new Grasshopper();
        int fromQ = 0;
        int fromR = 0;
        int toQ = 2;
        int toR = 0;
        Stone stoneGrasshopper1 = new Stone(Hive.Player.WHITE, Hive.Tile.GRASSHOPPER);
        Stone stoneBeetle1 = new Stone(Hive.Player.WHITE, Hive.Tile.BEETLE);
        board.setTile(fromQ, fromR, stoneGrasshopper1);
        board.setTile(1,0, stoneBeetle1);
        boolean canGrasshopperMove = grasshopper.isMoveAllowed(fromQ, fromR, toQ, toR, board);
        assertTrue(canGrasshopperMove);
    }

    @Test
    void givenGrassHopperCanMoveOverTileAndEmptySpotThenFalse() {
        Board board = new Board();
        Grasshopper grasshopper = new Grasshopper();
        int fromQ = 0;
        int fromR = 0;
        int toQ = 2;
        int toR = -1;
        Stone stoneGrasshopper1 = new Stone(Hive.Player.WHITE, Hive.Tile.GRASSHOPPER);
        Stone stoneBeetle1 = new Stone(Hive.Player.WHITE, Hive.Tile.BEETLE);
        board.setTile(fromQ, fromR, stoneGrasshopper1);
        board.setTile(1,0, stoneBeetle1);
        boolean canGrasshopperMove = grasshopper.isMoveAllowed(fromQ, fromR, toQ, toR, board);
        assertFalse(canGrasshopperMove);
    }

    @Test
    void givenGrassHopperCanMoveOverTileNotInStraightLineThenFalse() {
        Board board = new Board();
        Grasshopper grasshopper = new Grasshopper();
        int fromQ = 0;
        int fromR = 0;
        int toQ = 3;
        int toR = 0;
        Stone stoneGrasshopper1 = new Stone(Hive.Player.WHITE, Hive.Tile.GRASSHOPPER);
        Stone stoneBeetle1 = new Stone(Hive.Player.WHITE, Hive.Tile.BEETLE);
        Stone stoneBeetle2 = new Stone(Hive.Player.WHITE, Hive.Tile.BEETLE);
        Stone stoneBeetle3 = new Stone(Hive.Player.WHITE, Hive.Tile.BEETLE);
        board.setTile(fromQ, fromR, stoneGrasshopper1);
        board.setTile(1,0, stoneBeetle1);
        board.setTile(1,1, stoneBeetle2);
        board.setTile(2,1, stoneBeetle3);
        boolean canGrasshopperMove = grasshopper.isMoveAllowed(fromQ, fromR, toQ, toR, board);
        assertFalse(canGrasshopperMove);
    }

    @Test
    void givenGrasshoperCanMoveOnTopOffItselfThenFalse() {
        Board board = new Board();
        Grasshopper grasshopper = new Grasshopper();
        int fromQ = 0;
        int fromR = 0;
        int toQ = 0;
        int toR = 0;
        Stone stoneGrasshopper1 = new Stone(Hive.Player.WHITE, Hive.Tile.GRASSHOPPER);
        Stone stoneBeetle1 = new Stone(Hive.Player.WHITE, Hive.Tile.BEETLE);
        board.setTile(fromQ, fromR, stoneGrasshopper1);
        board.setTile(1,0, stoneBeetle1);
        boolean canGrasshopperMove = grasshopper.isMoveAllowed(fromQ, fromR, toQ, toR, board);
        assertFalse(canGrasshopperMove);
    }

    @Test
    void givenGrassHopperCanMoveOverLessThanOneTileThenFalse() {
        Board board = new Board();
        Grasshopper grasshopper = new Grasshopper();
        int fromQ = 0;
        int fromR = 0;
        int toQ = 1;
        int toR = -1;
        Stone stoneGrasshopper1 = new Stone(Hive.Player.WHITE, Hive.Tile.GRASSHOPPER);
        Stone stoneBeetle1 = new Stone(Hive.Player.WHITE, Hive.Tile.BEETLE);
        board.setTile(fromQ, fromR, stoneGrasshopper1);
        board.setTile(1,0, stoneBeetle1);
        boolean canGrasshopperMove = grasshopper.isMoveAllowed(fromQ, fromR, toQ, toR, board);
        assertFalse(canGrasshopperMove);
    }

    @Test
    void givenGrassHopperCanMoveToOnTopOfOtherTileThenFalse() {
        Board board = new Board();
        Grasshopper grasshopper = new Grasshopper();
        int fromQ = 0;
        int fromR = 0;
        int toQ = 2;
        int toR = 0;
        Stone stoneGrasshopper1 = new Stone(Hive.Player.WHITE, Hive.Tile.GRASSHOPPER);
        Stone stoneBeetle1 = new Stone(Hive.Player.WHITE, Hive.Tile.BEETLE);
        Stone stoneBeetle2 = new Stone(Hive.Player.WHITE, Hive.Tile.BEETLE);
        board.setTile(fromQ, fromR, stoneGrasshopper1);
        board.setTile(1,0, stoneBeetle1);
        board.setTile(toQ, toR, stoneBeetle2);
        boolean canGrasshopperMove = grasshopper.isMoveAllowed(fromQ, fromR, toQ, toR, board);
        assertFalse(canGrasshopperMove);
    }

    @Test
    void givenGrassHopperCanMoveOverEmptySpotsThenFalse() {
        Board board = new Board();
        Grasshopper grasshopper = new Grasshopper();
        int fromQ = -1;
        int fromR = 1;
        int toQ = 1;
        int toR = -1;
        Stone stoneGrasshopper1 = new Stone(Hive.Player.WHITE, Hive.Tile.GRASSHOPPER);
        Stone stoneBeetle1 = new Stone(Hive.Player.WHITE, Hive.Tile.BEETLE);
        Stone stoneBeetle2 = new Stone(Hive.Player.WHITE, Hive.Tile.BEETLE);
        board.setTile(fromQ, fromR, stoneGrasshopper1);
        board.setTile(1,0, stoneBeetle1);
        board.setTile(0,1, stoneBeetle2);
        boolean canGrasshopperMove = grasshopper.isMoveAllowed(fromQ, fromR, toQ, toR, board);
        assertFalse(canGrasshopperMove);
    }
}
