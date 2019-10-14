/**
 * Producer for lab.
 *
 * @author Leroy Valencia
 */
import java.util.Random;

/** The type Producer. */
class Producer extends Thread {
  private final int minCookTime = 1000; // 1 second
  private final int maxCookTime = 10000; // 10 seconds
  private int minBurgers = 100; // Value at which the producers retire from making burgers
  private Integer id = 0;
  /** The constant burger. */
  public static Burger burger = new Burger("00000");

  /**
   * Constructor
   *
   * @param tg the tg
   * @param name the name
   * @param minBurgers the min burgers
   */
  public Producer(ThreadGroup tg, String name, int minBurgers) {
    super(tg, name);
    Random r = new Random();
    this.minBurgers = minBurgers;
    this.id = Math.toIntExact(getId());
  }

  /**
   * Create new burger.
   *
   * @param id the id
   */
  public void createNewBurger(String id) {
    // Create a burger for a queue that is zero make that a priority
    burger = new Burger(PCDriver.queues.getLeastType(), id);
  }

  /** Do the production work. */
  public void run() {
    Random r = new Random();
    int cookTime;
    float cookSec;
    String burgerID;
    int sum = 0;

    System.out.println(getName() + " starting");
    // System.out.println(Producer.burger.getType() + " in producer class");

    while (true) {
      cookTime = minCookTime + r.nextInt(maxCookTime - minCookTime);
      cookSec = cookTime / 1000.0f;
      burgerID = Integer.toUnsignedString(r.nextInt());
      createNewBurger(burgerID);
      System.out.println(
          getName()
              + " is about to cook a "
              + BurgerType.toString(burger.getBurgerType())
              + " burger "
              + burgerID
              + " for "
              + cookSec
              + " sec");
      try {
        Thread.sleep(cookTime);
        System.out.println(
            getName()
                + " cooked a "
                + BurgerType.toString(burger.getBurgerType())
                + " burger with "
                + burgerID);

        // outputQueue.put(this.burger);
        // in the queue of the hashmap key that matches the burgertype
        PCDriver.queues.get(burger.getBurgerType()).put(this.burger);

        // Check sum of queues if, if the the queue is backed up with burgers then you can retire
        // from cooking
        //

        sum = PCDriver.queues.getQueuesSize(getName());

      } catch (InterruptedException e) {
        System.err.println("Producer got an InterruptedException; message: " + e.getMessage());
      }
      if (sum >= minBurgers) {
        System.err.println(getName() + " is tired of making burgers...");
        break; // Retire the producer
      }
    }
  }
}
