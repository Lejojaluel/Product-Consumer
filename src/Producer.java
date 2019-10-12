/**
 * Producer for lab.
 *
 * @author Leroy Valencia
 */
import java.util.Random;
import java.util.concurrent.BlockingQueue;

class Producer extends Thread {

  private BlockingQueue<Burger> outputQueue;
  private final int minCookTime = 1000; // 1 second
  private final int maxCookTime = 10000; // 10 seconds
  private final int maxCookers = 2;
  private final int minCookers = 0;
  private static int serial = 0;
  public static Burger burger = new Burger("00000");

  /**
   * Constructor
   *
   * @param q the queue shared with the consumer
   */
  public Producer(BlockingQueue<Burger> q, String name) {
    super(name);
    this.outputQueue = q;
  }

  public void createNew(String id) {
    burger = new Burger(id);
  }

  /**
   * Do the production work.
   */
  public void run() {
    Random r = new Random();
    int cookTime;
    float cookSec;
    String burgerID;
    BurgerType burgerType = BurgerType.BEEF;

    System.out.println(getName() + " starting");
    // System.out.println(Producer.burger.getType() + " in producer class");

    while (true) {
      cookTime = minCookTime + r.nextInt(maxCookTime - minCookTime);
      cookSec = cookTime / 1000.0f;
      burgerID = Integer.toUnsignedString(r.nextInt());
      createNew(burgerID);
      System.out.println(
          getName()
              + " is about to cook a "
              + BurgerType.toString(burger.getType())
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
                + BurgerType.toString(burger.getType())
                + " burger with "
                + burgerID);

        outputQueue.put(this.burger);
      } catch (InterruptedException e) {
        System.err.println("Producer got an InterruptedException; message: " + e.getMessage());
      }
    }
  }
}
