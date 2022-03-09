import static org.junit.Assert.assertEquals;
import org.junit.Test;
public class utilTest {
  @Test
  public void utilTest() {
    Coordinate c1 = Util.parseCoordinate("A0");
    Coordinate c2 = Util.parseCoordinate("B1");
    assertEquals(c1.getRow(), 0);
    assertEquals(c1.getColumn(), 0);
    assertEquals(c2.getRow(), 1);
    assertEquals(c2.getColumn(), 1);
    assertEquals(Util.hideShip(Piece.SHIP), Piece.WATER);
    assertEquals(Util.hideShip(Piece.DAMAGED_SHIP), Piece.DAMAGED_SHIP);
  }
}
