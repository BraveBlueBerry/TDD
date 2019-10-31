package nl.hanze.hive;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class Game {

    private Human currentHuman;
    private Human pWhite;
    private Human pBlack;
    private Board board;
    private Hive.Player winner;
    private boolean isDraw;

    public Game(Human pWhite, Human pBlack, Board board) {
        this.currentHuman = pWhite;
        this.pWhite = pWhite;
        this.pBlack = pBlack;
        this.board = board;
        this.isDraw = false;
    }

    public Human getCurrentHuman() {
        return currentHuman;
    }

    public Board getBoard() {
        return board;
    }

    public void nextTurn() {
        if (this.currentHuman == pWhite) {
            if (isThereAWinner()) {
                // Game is over
                // winner = pWhite.getColor();
            } else {
                currentHuman = pBlack;
            }
        } else {
            if (isThereAWinner()) {
                // Game is over
                // winner = pBlack.getColor();
            } else {
                currentHuman = pWhite;
            }

        }
    }

    public Boolean isThereAWinner() {
        winner = null;
        boolean isThereAWinner = true;
        HashMap<ArrayList<Integer>, Stack<Stone>> b = board.getBoard();
        if (b.isEmpty()) { return isThereAWinner = false; }
        ArrayList<ArrayList<Integer>> queens = new ArrayList<>();
        for (ArrayList<Integer> i : b.keySet()) {
            // Check for a queen bee tile if its surrounded
            Hive.Player colorOfQueenBee = b.get(i).peek().getColor();
            if(b.get(i).peek().getTile() == Hive.Tile.QUEEN_BEE) {
                queens.add(i);
            }
//                System.out.println("Found queenbee: " + b.get(i).peek().getColor() + " " + b.get(i).peek().getTile());
//                ArrayList<ArrayList<Integer>> surroundingTilesOfQueenBee =
//                        board.getSurroundingTiles(i.get(0), i.get(1));
//                System.out.println(surroundingTilesOfQueenBee);
//                for (ArrayList<Integer> coords : surroundingTilesOfQueenBee) {
//                    System.out.println(coords);
//                    Stack<Tile> tilesOnSpot = board.getTilesOnSpot(coords.get(0), coords.get(1));
//                    if (tilesOnSpot.empty()) {
//                        System.out.println("There is a tile empty!");
//                        isThereAWinner = false;
//                        winner = null;
//                        isDraw = false;
//                    }
//                }
//            } else { isThereAWinner = false; }
//            System.out.println("Is there a winner? " + isThereAWinner);
//            if (isThereAWinner) {
//                System.out.println("There's a winner");
//                if (winner == null) {
//                    System.out.println("setting winner");
//                    winner = (colorOfQueenBee == Hive.Player.BLACK ? Hive.Player.BLACK : Hive.Player.WHITE);
//                } else {
//                    System.out.println("setting it on trueeee");
//                    isDraw = true;
//                }
//            }
        }
        ArrayList<Stone> lostQueens = new ArrayList<>();
        for(ArrayList<Integer> queen: queens) {
            int q = queen.get(0);
            int r = queen.get(1);
            Stone queenStone = board.getTilesOnSpot(q, r).peek();
            boolean queenlost = true;

            for(ArrayList<Integer> surroundingTile: board.getSurroundingTiles(q, r)){
                Stack surroundingUnits = board.getTilesOnSpot(surroundingTile.get(0), surroundingTile.get(1));
                if(surroundingUnits.empty()) {
                    // Queen is not surrounded
                    queenlost = false;
                }
            }
            if (queenlost) {
                lostQueens.add(queenStone);
            }
        }
        if(lostQueens.size() > 1) {
            isDraw = true;
            isThereAWinner = false;
        } else if (lostQueens.size() == 1) {
            isThereAWinner = true;
            winner = (lostQueens.get(0).getColor() == Hive.Player.WHITE) ? Hive.Player.BLACK : Hive.Player.WHITE;
        } else {
            isThereAWinner = false;
        }
        return isThereAWinner;
    }

    public boolean getIsDraw() { isThereAWinner(); return isDraw; }

    public Hive.Player getWinner() {
        return winner;
    }
}
