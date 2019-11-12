package nl.hanze.hive;

import java.util.ArrayList;

public class Spider {
    private int steps = 3;
    private Board board;
    private ArrayList<Integer> startPosition;

    private ArrayList<ArrayList<Integer>> getPossibleMoves(int fromQ, int fromR, Board board) {
        ArrayList<Integer> fromCoords = new ArrayList<>();
        fromCoords.add(fromQ);
        fromCoords.add(fromR);

        ArrayList<ArrayList<Integer>> possibleMoves = findGoodMoves(fromCoords);
        return possibleMoves;
    }

    private void checkPathDFS(ArrayList<ArrayList<Integer>> visited, ArrayList<ArrayList<Integer>> goodMoves, ArrayList<Integer> position, int depth) {
        visited.add(position);
        if(depth == steps) {
            System.out.println(position);
            goodMoves.add(position);
            return;
        }
        ArrayList<ArrayList<Integer>> neighbours = board.getSurroundingTiles(position.get(0), position.get(1));
        ArrayList<ArrayList<Integer>> duplicateMine = board.getSurroundingTiles(position.get(0), position.get(1));
        for(ArrayList<Integer> maybeMove: neighbours) {
            if(!visited.contains(maybeMove)) {
                if(board.canStoneSlide(position.get(0), position.get(1), maybeMove.get(0), maybeMove.get(1), (depth == 0) ? 1 : 0) &&
                   board.getHeightStoneStackOnSpot(maybeMove.get(0), maybeMove.get(1)) == 0) {
                    ArrayList<ArrayList<Integer>> duplicateMaybe = board.getSurroundingTiles(maybeMove.get(0), maybeMove.get(1));
                    ArrayList<ArrayList<Integer>> commonDuplicates = new ArrayList<>();
                    int stonesFound = 0;
                    for(ArrayList<Integer> maybeDuplicate: duplicateMine) {
                        if(duplicateMaybe.contains(maybeDuplicate)) {
                            commonDuplicates.add(maybeDuplicate);
                            stonesFound += board.getHeightStoneStackOnSpot(maybeDuplicate.get(0), maybeDuplicate.get(1));
                            if(maybeDuplicate.equals(startPosition)) {
                                stonesFound -= 1;
                            }
                        }
                    }
                    if(commonDuplicates.size() != 2) {
                        continue;
                    }
                    if(stonesFound == 0) {
                        continue;
                    }
                    checkPathDFS(visited, goodMoves, maybeMove, depth + 1);
                }
            }
        }
    }

    private ArrayList<ArrayList<Integer>> findGoodMoves(ArrayList<Integer> position) {
        ArrayList<ArrayList<Integer>> goodMoves = new ArrayList<>();
        checkPathDFS(new ArrayList<>(), goodMoves, position, 0);
        return goodMoves;
    }

    public boolean isMoveAllowed(int fromQ, int fromR, int toQ, int toR, Board board) {
        this.board = board;
        ArrayList<Integer> toCoords = new ArrayList<>();
        toCoords.add(toQ);
        toCoords.add(toR);

        ArrayList<Integer> fromCoords = new ArrayList<>();
        fromCoords.add(fromQ);
        fromCoords.add(fromR);
        this.startPosition = fromCoords;
        if(getPossibleMoves(fromQ, fromR, board).contains(toCoords)) {
            return true;
        }
        return false;
    }
}
