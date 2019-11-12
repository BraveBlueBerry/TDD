package nl.hanze.hive;

import java.util.ArrayList;

public class QueenBee {
    public ArrayList<ArrayList<Integer>> getPossibleMoves(int fromQ, int fromR, Board board) {
        ArrayList<ArrayList<Integer>> possibleMoves = new ArrayList<>();
        ArrayList<ArrayList<Integer>> maybeMoves = board.getSurroundingTiles(fromQ, fromR);
        for (ArrayList<Integer> maybeMove : maybeMoves) {
            if (board.canStoneSlide(fromQ, fromR, maybeMove.get(0), maybeMove.get(1))
                    && board.getHeightStoneStackOnSpot(maybeMove.get(0), maybeMove.get(1)) == 0) {
                possibleMoves.add(maybeMove);
            }
        }
        return possibleMoves;
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

