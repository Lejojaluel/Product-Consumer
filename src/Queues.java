/** */
import java.util.HashMap;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/** The type Queues. */
public class Queues {
  /** The Hash map. */
  public HashMap<BurgerType, BlockingQueue<Burger>> hashMap =
      new HashMap<BurgerType, BlockingQueue<Burger>>();

  /** Instantiates a new Queues. */
  public Queues() {
    for (BurgerType bt : BurgerType.values()) {
      hashMap.put(bt, new LinkedBlockingQueue<Burger>());
    }
  }

  /**
   * Get blocking queue.
   *
   * @param bt the bt
   * @return the blocking queue
   */
  public BlockingQueue<Burger> get(BurgerType bt) {
    return hashMap.get(bt);
  }

  /**
   * Gets queues size.
   *
   * @param name the name
   * @return the queues size
   */
  public int getQueuesSize(String name) {
    int sum = 0;
      for (BurgerType bt : BurgerType.values()) {
          sum += hashMap.get(bt).size();
      }
      System.err.println(name+ " is checking queue size = " + sum);
    return sum;
  }
  /** List queue size. */
  public void listQueueSize() {
      for (BurgerType bt : BurgerType.values()) {
          System.out.println(bt+" Queue = " + hashMap.get(bt).size());
      }
  }
  /**
   * Get least type burger type.
   *
   * @return the burger type
   */
  public BurgerType getLeastType() {
      int minValue = hashMap.get(BurgerType.BACON).size();
      BurgerType minType = BurgerType.BACON;
      for (BurgerType bt : BurgerType.values()) {
          if(hashMap.get(bt).size() < minValue){
            minValue = hashMap.get(bt).size();
            minType = bt;
          }

      }
      return minType;
  }
}
