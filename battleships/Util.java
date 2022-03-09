import java.util.Arrays;

public class Util {

    private static int letterToIndex(char letter) {
        return letter - 65;
    }

    private static int numberToIndex(char number) {
        return number - 48;
    }

    public static Coordinate parseCoordinate(String s) {
        int row = letterToIndex(s.charAt(0));
        int col = numberToIndex(s.charAt(1));
        return new Coordinate(row, col);
    }

    public static Piece hideShip(Piece piece) {
        if (piece == Piece.SHIP) {
            return Piece.WATER;
        } else {
            return piece;
        }
    }

    public static void hideShips(Piece[][] grid) {
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                grid[row][col] = hideShip(grid[row][col]);
            }
        }
    }

    public static Piece[][] deepClone(Piece[][] input) {
        Piece[][] output = new Piece[input.length][];
        for (int i = 0; i < input.length; i++) {
            output[i] = Arrays.copyOf(input[i], input[i].length);
        }
        return output;
    }
}
