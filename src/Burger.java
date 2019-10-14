/**
 * Burger Object Class
 *
 * @author Leroy Valencia
 */
import java.util.Random;

/** The type Burger. */
public class Burger {
  private String id;
  private BurgerType type;

  /**
   * Instantiates a new Burger.
   *
   * @param id the id
   */
  /*
  Default Burger constructor for the object.
   */
  public Burger(String id) {
    this.id = id;
    type = BurgerType.values()[new Random().nextInt(BurgerType.values().length)];
    // Leaves the type as a random type
  }
  /**
   * Instantiates a new Burger.
   *
   * @param bt the bt
   * @param id the id
   */
  public Burger(BurgerType bt, String id) {
    this.id = id;
    type = bt;
    // Leaves the type as a random type
  }

  public String toString() {
    return "Burger has id: " + id + " this type " + type;
  }

  /**
   * Gets burger type.
   *
   * @return the burger type
   */
  // Getters
  public BurgerType getBurgerType() {
    return type;
  }

  /**
   * Gets burger id.
   *
   * @return the burger id
   */
  public String getBurgerId() {
    return id;
  }
}
