package nl.hanze.hive;

public class Tile {
    private Hive.Player color;
    private Hive.Tile tile;

    public Tile(Hive.Player color, Hive.Tile tile) {
        this.color = color;
        this.tile = tile;
    }

    public Hive.Player getColor() {
        return color;
    }

    public Hive.Tile getTile() {
        return tile;
    }

}
