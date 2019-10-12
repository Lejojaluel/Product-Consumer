/**
 * Producer Consumer example using BlockingQueue.
 *
 * @author Leroy Valencia
 */
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class PCDriver {
  public static void main(String[] args) {
    // original(3); //Uncomment for original problem
    // original(0); //Uncomment for 0 producers
    extraCredit2(10000, 1000, 3);
  }

  public static void original(int numOfProducers) {
    BlockingQueue<Burger> connectionPC = new LinkedBlockingQueue<Burger>();
    ArrayList<Consumer> consArr = new ArrayList<Consumer>();
    ArrayList<Producer> prodArr = new ArrayList<Producer>();

    int i = 0;
    for (BurgerType b : BurgerType.values()) {
      consArr.add(new Consumer(connectionPC, (BurgerType.toString(b) + " eater"), b));
      consArr.get(i).start();
      i++;
    }

    for (int j = 0; j < numOfProducers; j++) {
      prodArr.add(new Producer(connectionPC, ("Burger cook " + j)));
      prodArr.get(j).start();
    }
  }

  public static void extraCredit2(int maxDelay, int minDelay, int numOfProducers) {
    BurgerType burgerType = BurgerType.values()[new Random().nextInt(BurgerType.values().length)];
    BlockingQueue<Burger> connectionPC = new LinkedBlockingQueue<Burger>();
    Random r = new Random();
    int i = 0;
    int delay = minDelay + r.nextInt(maxDelay - minDelay);

    ArrayList<Consumer> consArr = new ArrayList<Consumer>();
    ArrayList<Producer> prodArr = new ArrayList<Producer>();
    for (int j = 0; j < numOfProducers; j++) {
      prodArr.add(new Producer(connectionPC, ("Burger cook " + j)));
      prodArr.get(j).start();
    }

    while (true) {
      try {
        Thread.sleep(delay);
        consArr.add(
            new Consumer(connectionPC, (BurgerType.toString(burgerType) + " eater"), burgerType));
        System.out.println("Created a consumer");
        consArr.get(i).start();
        i++;
        System.out.println("Waiting....");
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}
