public class Grid {

    private static final int WIDTH = 10;
    private static final int HEIGHT = 10;

    private final Piece[][] grid = new Piece[HEIGHT][WIDTH];

    public Grid() {
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                grid[i][j] = Piece.WATER;
            }
        }
    }

    private boolean gridFree(Coordinate c) {
        int row = c.getRow();
        int col = c.getColumn();
        return row >= 0 && row < HEIGHT && col >= 0 && col < WIDTH && grid[row][col] == Piece.WATER;
    }

    public boolean canPlace(Coordinate c, int size, boolean isDown) {
        if (isDown) {
            for (int i = 0; i < size; i++) {
                if (!gridFree(c.down(i))) return false;
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (!gridFree(c.right(i))) return false;
            }
        }
        return true;
    }

    private void setShip(Coordinate c) {
        grid[c.getRow()][c.getColumn()] = Piece.SHIP;
    }

    public void placeShip(Coordinate c, int size, boolean isDown) {
        if (isDown) {
            for (int i = 0; i < size; i++) {
                setShip(c.down(i));
            }
        } else {
            for (int i = 0; i < size; i++) {
                setShip(c.right(i));
            }
        }
    }

    public boolean wouldAttackSucceed(Coordinate c) {
        return grid[c.getRow()][c.getColumn()] == Piece.SHIP;
    }

    public void attackCell(Coordinate c) {
        Piece piece = grid[c.getRow()][c.getColumn()];
        if (piece == Piece.SHIP) {
            grid[c.getRow()][c.getColumn()] = Piece.DAMAGED_SHIP;
        } else if (piece == Piece.WATER) {
            grid[c.getRow()][c.getColumn()] = Piece.MISS;
        }
    }

    public boolean areAllSunk() {
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                if (grid[i][j] == Piece.SHIP) {
                    return false;
                }
            }
        }
        return true;
    }

    public String toPlayerString() {
        Piece[][] copy = Util.deepClone(grid);
        Util.hideShips(copy);
        return renderGrid(copy);
    }

    @Override
    public String toString() {
        return renderGrid(grid);
    }

    private static String renderGrid(Piece[][] grid) {
        StringBuilder sb = new StringBuilder();
        sb.append(" 0123456789\n");
        for (int i = 0; i < grid.length; i++) {
            sb.append((char) ('A' + i));
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == null) {
                    return "!";
                }
                switch (grid[i][j]) {
                case SHIP:
                    sb.append('#');
                    break;
                case DAMAGED_SHIP:
                    sb.append('*');
                    break;
                case MISS:
                    sb.append('o');
                    break;
                case WATER:
                    sb.append('.');
                    break;
                }

            }
            sb.append('\n');
        }

        return sb.toString();
    }
}
