/**
 * Producer Consumer example using BlockingQueue.
 *
 * @author Leroy Valencia
 */
import java.util.ArrayList;
import java.util.Random;

/** The type Pc driver. */
public class PCDriver {
  /** The constant producersTG. */
  public static ThreadGroup producersTG = new ThreadGroup("producers");

  /** The constant consumersTG. */
  public static ThreadGroup consumersTG = new ThreadGroup("consumers");

  /** The constant queues. */
  public static Queues queues = new Queues();

  /**
   * The entry point of application.
   *
   * @param args the input arguments
   */
  public static void main(String[] args) {
    // original(3); //Uncomment for original problem
    // original(0); //Uncomment for 0 producers
    extraCredit2(2000, 1000, 0, 0);
  }

  /**
   * Original.
   *
   * @param numOfProducers the num of producers
   */
  public static void original(int numOfProducers) {
    ArrayList<Consumer> consArr = new ArrayList<Consumer>();
    ArrayList<Producer> prodArr = new ArrayList<Producer>();

    int i = 0;
    // creates all consumers for all types of burgers
    for (BurgerType b : BurgerType.values()) {
      consArr.add(new Consumer(consumersTG, (BurgerType.toString(b) + " eater"), b));
      consArr.get(i).start();
      i++;
    }

    for (int j = 0; j < numOfProducers; j++) {
      prodArr.add(new Producer(producersTG, ("Burger cook " + j), 10));
      prodArr.get(j).start();
    }
  }

  /**
   * For this test you need to set the leave boolean to true in the consumer. I have them not
   * leaving automatically because the first tests wouldn't work right.
   *
   * @param maxDelay the max delay
   * @param minDelay the min delay
   * @param numOfProducers the num of producers
   * @param consumerLimit the consumer limit
   */
  public static void extraCredit2(
      int maxDelay, int minDelay, int numOfProducers, int consumerLimit) {
    BurgerType burgerType = BurgerType.values()[new Random().nextInt(BurgerType.values().length)];
    Random r = new Random();

    int i = 0;
    int delay = minDelay + r.nextInt(maxDelay - minDelay);

    ArrayList<Consumer> consArr =
        new ArrayList<Consumer>(); // Used arraylists to easily create objects instead of naming
    ArrayList<Producer> prodArr = new ArrayList<Producer>(); // each one.

    // Creates a manager object
    Manager manager = new Manager("manager");
    manager.setMinConsumers(2); // This sets the min consumers the manager needs to see in order to hire a producer
    manager.setCheckDelay(2000); // in milliseconds
    manager.setMaxBurgers(12);
    manager.start();

    while (true) {
      try {
        queues.listQueueSize();
        Thread.sleep(delay); // Delay to create consumers
        burgerType =
            BurgerType.values()[
                new Random().nextInt(BurgerType.values().length)]; // Random preferance
        consArr.add(
            new Consumer(consumersTG, (BurgerType.toString(burgerType) + " eater"), burgerType));
        System.out.println("Created a consumer");
        consArr.get(i).start(); // starts said customer
        i++;
        // System.out.println("Waiting.... for " + (delay/1000));
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    } // end of while
  } // end of extracredit2
}
