package videogame;

public class Magician extends Entity implements SpellCaster {

  public Magician(String name, int lifePoints) {
    super(name, lifePoints);
  }

  @Override
  protected int propagateDamage(int damageAmount) {
    if (damageAmount >= lifePoints) {
      int damage = lifePoints;
      lifePoints = 0;
      return damage;
    } else {
      lifePoints -= damageAmount;
      return damageAmount;
    }
  }

  @Override
  public int minimumStrikeToDestroy() {
    return lifePoints;
  }

  @Override
  public int getStrength() {
    return lifePoints * 2;
  }

  @Override
  public String toString() {
    return name + '(' + lifePoints + ')';
  }
}
