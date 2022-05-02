package videogame;

import java.util.HashSet;
import java.util.Set;

public class TransportUnit extends Entity {

  private Set<Entity> contained;

  public TransportUnit(String name, int lifePoints) {
    super(name, lifePoints);
    contained = new HashSet<>();
  }

  public void add(Entity entity) {
    contained.add(entity);
  }

  @Override
  protected int propagateDamage(int damageAmount) {
    int damage;
    if (damageAmount >= lifePoints) {
      damage = lifePoints;
      lifePoints = 0;
    } else {
      lifePoints -= damageAmount;
      damage = damageAmount;
    }
    for (Entity e : contained) {
      damage += e.propagateDamage(damageAmount/2);
    }
    return damage;
  }

  @Override
  public int minimumStrikeToDestroy() {
    int required = lifePoints;
    for (Entity e : contained) {
      required = Integer.max(required, e.minimumStrikeToDestroy() * 2);
    }
    return required;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(name + '(' + lifePoints + ')' + " transporting: [");
    for (Entity e : contained) {
      sb.append(e + ", ");
    }
    if (sb.charAt(sb.length() - 2) == ',') {
      sb.delete(sb.length() - 2, sb.length());
    }
    sb.append(']');
    return sb.toString();
  }
}
