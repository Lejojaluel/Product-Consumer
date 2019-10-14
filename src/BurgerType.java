/** Burger Type Enumeration Class @Author Leroy Valencia */
public enum BurgerType {
  /** Tofu burger type. */
  TOFU,
  /** Beef burger type. */
  BEEF,
  /** Chicken burger type. */
  CHICKEN,
  /** Fish burger type. */
  FISH,
  /** Cheeseburger burger type. */
  CHEESEBURGER,
  /** Bacon burger type. */
  BACON;

  /**
   * To string string.
   *
   * @param b the b
   * @return the string
   */
  public static String toString(BurgerType b) {
    switch (b) {
      case TOFU:
        return "Tofu";
      case BEEF:
        return "Beef";
      case CHICKEN:
        return "Chicken";
      case FISH:
        return "Fish";
      case CHEESEBURGER:
        return "Cheeseburger";
      case BACON:
        return "Bacon";
      default:
        System.err.println("This is an impossible burger type");
        System.exit(1);
    }
    return "Impossible burger type";
  }
}
