package nl.hanze.hive;

import java.util.ArrayList;

public class Grasshopper {
    private ArrayList<ArrayList<Integer>> getPossibleMoves(int fromQ, int fromR, Board board) {
        ArrayList<ArrayList<Integer>> possibleMoves = new ArrayList<>();
        ArrayList<ArrayList<Integer>> surroundingCoords = board.getSurroundingTiles(fromQ, fromR);
        for (ArrayList<Integer> surroundingCoord : surroundingCoords) {
            if(board.getHeightStoneStackOnSpot(surroundingCoord.get(0), surroundingCoord.get(1)) > 0) {
                ArrayList<Integer> endCoord = getEndOfStraightLine(fromQ, fromR, surroundingCoord, board);
                possibleMoves.add(endCoord);
            }
        }
        return possibleMoves;
    }

    private ArrayList<Integer> getEndOfStraightLine(int fromQ, int fromR, ArrayList<Integer> surroundingCoordWithTile, Board board) {
        int adjustmentQ = surroundingCoordWithTile.get(0) - fromQ;
        int adjustmentR = surroundingCoordWithTile.get(1) - fromR;
        boolean jumping = true;
        int currentQ = fromQ;
        int currentR = fromR;
        while(jumping) {
            jumping = false;
            currentQ += adjustmentQ;
            currentR += adjustmentR;
            if(board.getHeightStoneStackOnSpot(currentQ, currentR) > 0) { jumping = true; }
        }
        ArrayList<Integer> endCoord = new ArrayList<>();
        endCoord.add(currentQ);
        endCoord.add(currentR);
        return endCoord;
    }

    public boolean isMoveAllowed(int fromQ, int fromR, int toQ, int toR, Board board) {
        ArrayList<Integer> toCoords = new ArrayList<>();
        toCoords.add(toQ);
        toCoords.add(toR);
        if(getPossibleMoves(fromQ, fromR, board).contains(toCoords)) {
            return true;
        }
        return false;
    }
}
