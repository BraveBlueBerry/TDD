package nl.hanze.hive;

import java.util.ArrayList;

public class Beetle {

    private ArrayList<ArrayList<Integer>> getPossibleMoves(int fromQ, int fromR, Board board) {
        ArrayList<ArrayList<Integer>> possibleMoves = new ArrayList<>();
        ArrayList<ArrayList<Integer>> maybeMoves = board.getSurroundingTiles(fromQ, fromR);
        System.out.println(maybeMoves);
        for (ArrayList<Integer> maybeMove : maybeMoves) {
            if (board.canStoneSlide(fromQ, fromR, maybeMove.get(0), maybeMove.get(1))) {
                possibleMoves.add(maybeMove);
            }
        }
        System.out.println(possibleMoves);
        return possibleMoves;
    }

    public boolean isMoveAllowed(int fromQ, int fromR, int toQ, int toR, Board board) {
        ArrayList<Integer> toCoords = new ArrayList<>();
        toCoords.add(toQ);
        toCoords.add(toR);
        System.out.println(toCoords);
        if(getPossibleMoves(fromQ, fromR, board).contains(toCoords)) {
            return true;
        }
        return false;
    }
}
