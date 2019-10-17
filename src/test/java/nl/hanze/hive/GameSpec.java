package nl.hanze.hive;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameSpec {

    @Test
    void givenWhiteStartsWhenNewGameThenTrue() {
        Game game = new Game();
        Player currentPlayer = game.getCurrentPlayer();
        Player playerWhite = new Player(Hive.Player.WHITE);

        assertEquals(currentPlayer.getColor(), playerWhite.getColor());
    }
}
