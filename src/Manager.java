/** Manager Thread class for extra credit @Author Leroy Valencia */
public class Manager extends Thread {
  private int minConsumers = 3;
  private int serial = 0; // the default amount of producers is the start of any new producers
  private int delay = 6; // default delay
  private int maxBurgers = 12; // Default

  /**
   * Instantiates a new Manager.
   *
   * @param name the name
   */
  public Manager(String name) {
    super(name);
  }

  /**
   * Sets min consumers.
   *
   * @param min the min
   */
  public void setMinConsumers(int min) {
    this.minConsumers = min;
  }

  /**
   * Sets check delay.
   *
   * @param delay the delay
   */
  public void setCheckDelay(int delay) {
    this.delay = delay;
  }
  /**
   * Sets max burgers.
   *
   * @param maxBurgers the max burgers
   */
  public void setMaxBurgers(int maxBurgers) {
    this.maxBurgers = maxBurgers;
  }

  /**
   * Create producer.
   *
   * @param name the name
   */
  public void createProducer(String name) {
    Producer producer = new Producer(PCDriver.producersTG, name, maxBurgers);
    producer.start();
  }

  /**
   * Check consumer count int.
   *
   * @return the int
   */
  public int checkConsumerCount() {
    int count = PCDriver.consumersTG.activeCount();
    System.out.println("Number of Consumers seen by Manager: " + count);
    return count;
  }

  /**
   * Check producer count int.
   *
   * @return the int
   */
  public int checkProducerCount() {
    return PCDriver.producersTG.activeCount();
  }

  public void run() {
    while (true) {
      try {
        if (checkConsumerCount() > this.minConsumers) {
          System.out.println("Manager Hired new cook!");
          createProducer("[New Hire] Burger Cook " + serial++);
        }
        System.out.println("Number of Producers seen by Manager: " + (checkProducerCount()));

        PCDriver.queues.getQueuesSize("[Manager]");
        Thread.sleep(delay);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}
