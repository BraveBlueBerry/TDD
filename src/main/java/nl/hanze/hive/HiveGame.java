package nl.hanze.hive;

public class HiveGame implements Hive {

    private Game game;
    public HiveGame() {
        this.game = new Game(new Human(Player.WHITE), new Human(Player.BLACK), new Board());
    }

    int turns = 0;
    @Override
    public void play(Tile tile, int q, int r) throws IllegalMove {
        Human currentHuman = game.getCurrentHuman();
        Stone stoneToPlay = new Stone(currentHuman.getColor(), tile);
        currentHuman.playTile(game, stoneToPlay, game.getBoard(), q, r);
    }

    @Override
    public void move(int fromQ, int fromR, int toQ, int toR) throws IllegalMove {
        Human currentHuman = game.getCurrentHuman();
        currentHuman.moveTile(game, game.getBoard(), fromQ, fromR, toQ, toR);
    }

    @Override
    public void pass() throws IllegalMove {
        Human currentHuman = game.getCurrentHuman();
        currentHuman.pass(game);
    }

    @Override
    public boolean isWinner(Player player) {
        if(game.isThereAWinner()) {
            if(game.getWinner() == player) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isDraw() {
        return game.getIsDraw();
    }
}
