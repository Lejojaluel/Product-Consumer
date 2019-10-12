/**
 * Burger Type Enumeration Class @Author Leroy Valencia
 */
public enum BurgerType {
  TOFU,
  BEEF,
  CHICKEN,
  FISH,
  CHEESEBURGER,
  BACON;

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
