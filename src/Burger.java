/**
 * Burger Object Class
 *
 * @author Leroy Valencia
 */
import java.util.Random;

public class Burger {
  private String id;
  private BurgerType type;

  /*
  Default Burger constructor for the object.
   */
  public Burger(String id, BurgerType type) {
    this.id = id;
    this.type = type;
  }

  public Burger(String id) {
    this.id = id;
    type = BurgerType.values()[new Random().nextInt(BurgerType.values().length)];
    // Leaves the type as a random type
  }

  public String toString() {
    return "Burger has id: " + id + " this type " + type;
  }

  // Getters
  public BurgerType getType() {
    return type;
  }

  public String getId() {
    return id;
  }
}
