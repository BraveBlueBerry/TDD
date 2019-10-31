package nl.hanze.hive;

public class Stone {
    private Hive.Player color;
    private Hive.Tile tile;

    public Stone(Hive.Player color, Hive.Tile tile) {
        this.color = color;
        this.tile = tile;
    }

    public Hive.Player getColor() {
        return color;
    }

    public Hive.Tile getTile() {
        return tile;
    }

    public boolean equals(Object otherStone) {
        if (! (otherStone instanceof Stone)) {
            return false;
        }
        if (((Stone) otherStone).getTile() != getTile()) {
            return false;
        }
        if (((Stone) otherStone).getColor() != getColor()) {
            return false;
        }
        return true;
    }

}
