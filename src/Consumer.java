/**
 * Consumer thread
 *
 * @author Leroy Valencia
 */
import java.util.Random;
import java.util.concurrent.BlockingQueue;

/** The type Consumer. */
public class Consumer extends Thread {
  private BlockingQueue<Burger> queuePref;
  private final int minEatTime = 1000; // 1 second
  private final int maxEatTime = 10000; // 10 seconds
  private final BurgerType
      burgerPref; // This needed to be final otherwise each consumer would change
  private Burger burger; // Instance holder for the burger that will be tested.
  private Integer id = 0;
  private boolean toLeave = true;

  /**
   * Constructor for a consumer
   *
   * @param tg the tg
   * @param name The name of the consumer is already determined by the loop before
   * @param burgerPref The burger preferance of the consumer
   */
  public Consumer(ThreadGroup tg, String name, BurgerType burgerPref) {
    super(tg, name); // Puts the name of the extended thread class
    Random r = new Random();
    this.id = Math.toIntExact(getId());
    this.burgerPref = burgerPref;
    this.queuePref =
        PCDriver.queues.get(burgerPref); // Get me the queue that is the same as my preference
  }

  /** Do the work of consuming the burgers. */
  public void run() {
    Random r = new Random();
    int eatTime = minEatTime + r.nextInt(maxEatTime - minEatTime);
    System.out.println(BurgerType.toString(burgerPref) + " eater starting");

    // The main while loop that keeps the thread running.
    while (true) {
      // Sleep when consumer starts
      try {
        Thread.sleep(eatTime);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

      /*
      This is the main block of logic to query the queue. If the queue is null then do nothing. This was
       necessary because you can't get a method of a null. So this catches the null first.
       Then if the getType method of the peek in the queue is equal to the preferance of the consumer then
       take the burger from the queue
       Then if the queue is anything else state that the consumer peeked at the queue and then print that it
       is not the right type of burger.
       */
      try {
        if (queuePref.peek() == null) {
          // System.err.println("Queue is null");
          System.out.println(getName() + " " + getId() + " peeked at Queue but it's empty");
        } else if (queuePref.peek().getBurgerType() == burgerPref) {
          System.out.println(getName() + " peeked at Queue");
          System.out.println(
              getName()
                  + " "
                  + getId()
                  + " got a "
                  + BurgerType.toString(queuePref.peek().getBurgerType())
                  + " burger "
                  + queuePref.peek().getBurgerId()
                  + " from the queue.");
          burger = queuePref.take();
        } else {
          System.out.println(getName() + " " + getId() + " peeked at Queue");
          System.out.println(
              getName()
                  + " "
                  + getId()
                  + ": "
                  + BurgerType.toString(queuePref.peek().getBurgerType())
                  + " "
                  + queuePref.peek().getBurgerId()
                  + " is the wrong type");
        }
      } catch (InterruptedException e) {
        System.err.println("Consumer got an InterruptedException; message: " + e.getMessage());
      }
      // This calculates the time to eat so we know how long to sleep the thred.
      eatTime = minEatTime + r.nextInt(maxEatTime - minEatTime);
      // This logic makes sures to only eat a burger if it
      if (queuePref.peek() != null) {
        try {
          Thread.sleep(eatTime);
          System.out.println(getName() + " took " + eatTime / 1000.0f + " sec to eat the burger ");
          // System.out.println("Done eating burger");
          if (toLeave) {
            break;
          }
        } catch (InterruptedException e) {
          System.err.println("Consumer got an InterruptedException; message: " + e.getMessage());
        }
      } else {
        try {
          Thread.sleep(eatTime);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }
    System.err.println(getName() + " id " + getId() + " has left...");
  }
}
