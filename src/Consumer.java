/**
 * Consumer demo of threads in producer-consumer example.
 *
 * @author Kenneth Ingham
 */

import java.util.Random;
import java.util.concurrent.BlockingQueue;

class Consumer extends Thread {

    private BlockingQueue<Burger> inputQueue;
    private final int minEatTime = 1000;  // 1 second
    private final int maxEatTime = 10000; // 10 seconds
    private static BurgerType burgerPref;
    private Burger burger;

    /**
     * Constructor
     *
     * @param q the queue that is shared with the producer
     */
    public Consumer(BlockingQueue<Burger> q, String name, BurgerType burgerPref) {
        super(name);
        this.inputQueue = q;
        this.burgerPref = burgerPref;
    }

    /**
     * Do the work of consuming the burgers.
     */
    public void run() {
        Random r = new Random();
        int eatTime;
        System.out.println(burgerPref + " starting");
        //System.out.println(Producer.burger.getType());

        while (true) {
            try {
                burger = inputQueue.take();
            } catch (InterruptedException e) {
                System.err.println("Consumer got an InterruptedException; message: "
                    + e.getMessage());
            }
            System.out.println(getName() + " got a burger " + burger.getType() + " from cook.");
            eatTime = minEatTime + r.nextInt(maxEatTime - minEatTime);

            try {
                Thread.sleep(eatTime);
            } catch (InterruptedException e) {
                System.err.println("Consumer got an InterruptedException; message: "
                    + e.getMessage());
            }
            System.out.println(getName() + " took " + eatTime / 1000.0f + " sec to eat the burger " + burger);
        }
    }
}