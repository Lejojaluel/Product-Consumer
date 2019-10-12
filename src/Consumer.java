/**
 * Consumer thread
 *
 * @author Leroy Valencia
 */
import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class Consumer extends Thread {
  private BlockingQueue<Burger> inputQueue;
  private final int minEatTime = 1000; // 1 second
  private final int maxEatTime = 10000; // 10 seconds
  private final BurgerType
      burgerPref; // This needed to be final otherwise each consumer would change
  private Burger burger; // Instance holder for the burger that will be tested.

  /**
   * Constructor for a consumer
   *
   * @param q          The queue that is in place to hold the burgers
   * @param name       The name of the consumer is already determined by the loop before
   * @param burgerPref The burger preferance of the consumer
   */
  public Consumer(BlockingQueue<Burger> q, String name, BurgerType burgerPref) {
    super(name); // Puts the name of the extended thread class
    this.inputQueue = q;
    this.burgerPref = burgerPref;
  }

  /**
   * Do the work of consuming the burgers.
   */
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
        if (inputQueue.peek() == null) {
          // System.err.println("Queue is null");
          System.out.println(getName() + " peeked at Queue but it's empty");
        } else if (inputQueue.peek().getType() == burgerPref) {
          System.out.println(getName() + " peeked at Queue");
          System.out.println(
              getName()
                  + " got a "
                  + BurgerType.toString(inputQueue.peek().getType())
                  + " burger "
                  + inputQueue.peek().getId()
                  + " from the queue.");
          // Grain eater got Grain burger 4238239770 from queue.
          burger = inputQueue.take();
        } else {
          System.out.println(getName() + " peeked at Queue");
          System.out.println(
              getName()
                  + ": "
                  + BurgerType.toString(inputQueue.peek().getType())
                  + " "
                  + inputQueue.peek().getId()
                  + " is the wrong type");
        }
      } catch (InterruptedException e) {
        System.err.println("Consumer got an InterruptedException; message: " + e.getMessage());
      }
      //This calculates the time to eat so we know how long to sleep the thred.
      eatTime = minEatTime + r.nextInt(maxEatTime - minEatTime);
      //This logic makes sures to only eat a burger if it
      if (inputQueue.peek() != null) {
        try {
          Thread.sleep(eatTime);
          System.out.println(
              getName() + " took " + eatTime / 1000.0f + " sec to eat the burger " + burger);
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
  }
}
