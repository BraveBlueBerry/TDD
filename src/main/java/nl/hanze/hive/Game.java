package nl.hanze.hive;

public class Game {

    private Player currentPlayer;

    public Game() {
        this.currentPlayer = new Player(Hive.Player.WHITE);
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }
}
