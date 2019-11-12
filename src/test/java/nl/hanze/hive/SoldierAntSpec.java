package nl.hanze.hive;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

public class SoldierAntSpec {
    @Test
    void givenSpiderCanSlideThreeTilesThenTrue() {
        Board board = new Board();
        SoldierAnt soldierAnt = new SoldierAnt();
        int fromQ = 0;
        int fromR = 0;
        int toQ = 0;
        int toR = 0;
        Stone stoneSoldierAnt1 = new Stone(Hive.Player.WHITE, Hive.Tile.SOLDIER_ANT);
        Stone stoneBeetle2 = new Stone(Hive.Player.WHITE, Hive.Tile.BEETLE);
        board.setTile(fromQ, fromR, stoneSoldierAnt1);
        board.setTile(1,-1, stoneBeetle2);
        ArrayList<ArrayList<Integer>> surroundingTheBeetle = board.getSurroundingTiles(1,-1);
        for(ArrayList<Integer> x: surroundingTheBeetle) {
            if(fromQ != x.get(0) && fromR != x.get(1)) {
                toQ = x.get(0);
                toR = x.get(1);
            }
            boolean canAntMove = soldierAnt.isMoveAllowed(fromQ, fromR, toQ, toR, board);
            assertTrue(canAntMove);
        }
    }
}
