package nl.hanze.hive;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SpiderSpec {
    @Test
    void givenSpiderCanSlideThreeTilesThenTrue() {
        Board board = new Board();
        Spider spider = new Spider();
        int fromQ = 0;
        int fromR = 0;
        int toQ = 2;
        int toR = -2;
        Stone stoneSpider1 = new Stone(Hive.Player.WHITE, Hive.Tile.BEETLE);
        Stone stoneBeetle2 = new Stone(Hive.Player.WHITE, Hive.Tile.BEETLE);
        board.setTile(fromQ, fromR, stoneSpider1);
        board.setTile(1,-1, stoneBeetle2);
        boolean canSpiderMoveThreeTiles = spider.isMoveAllowed(fromQ, fromR, toQ, toR, board);
        assertTrue(canSpiderMoveThreeTiles);
    }

    @Test
    void givenSpiderCantSlideOneTileThenTrue() {
        Board board = new Board();
        Spider spider = new Spider();
        int fromQ = 0;
        int fromR = 0;
        int toQ = 1;
        int toR = 0;
        Stone stoneSpider1 = new Stone(Hive.Player.WHITE, Hive.Tile.BEETLE);
        Stone stoneBeetle2 = new Stone(Hive.Player.WHITE, Hive.Tile.BEETLE);
        board.setTile(fromQ, fromR, stoneSpider1);
        board.setTile(1,-1, stoneBeetle2);
        boolean canSpiderMoveOneTile = spider.isMoveAllowed(fromQ, fromR, toQ, toR, board);
        assertFalse(canSpiderMoveOneTile);
    }
}
