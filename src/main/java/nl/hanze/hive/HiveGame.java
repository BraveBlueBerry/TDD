package nl.hanze.hive;

public class HiveGame implements Hive {

    int turns = 0;
    @Override
    public void play(Tile tile, int q, int r) throws IllegalMove {
    }

    @Override
    public void move(int fromQ, int fromR, int toQ, int toR) throws IllegalMove {

    }

    @Override
    public void pass() throws IllegalMove {

    }

    @Override
    public boolean isWinner(Player player) {
        return false;
    }

    @Override
    public boolean isDraw() {
        return false;
    }
}
