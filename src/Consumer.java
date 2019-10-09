/**
 * Consumer demo of threads in producer-consumer example.
 *
 * @author Kenneth Ingham
 */

import java.util.Random;
import java.util.concurrent.BlockingQueue;

class Consumer extends Thread {

    private BlockingQueue<String> inputQueue;
    private final int minEatTime = 1000;  // 1 second
    private final int maxEatTime = 10000; // 10 seconds
    private static BurgerType burgerType = BurgerType.values()[new Random().nextInt(BurgerType.values().length)];
    ;

    /**
     * Constructor
     *
     * @param q the queue that is shared with the producer
     */
    public Consumer(BlockingQueue<String> q) {
        super(burgerType + " eater");
        this.inputQueue = q;
    }

    /**
     * Do the work of comsuming the burgers.
     */
    public void run() {
        Random r = new Random();
        int eatTime;
        String burger = "No burger";

        while (true) {
            try {
                burger = inputQueue.take();
            } catch (InterruptedException e) {
                System.err.println("Consumer got an InterruptedException; message: "
                    + e.getMessage());
            }
            System.out.printf("%s got burger '%s' from cook.\n", getName(), burger);
            eatTime = minEatTime + r.nextInt(maxEatTime - minEatTime);

            try {
                Thread.sleep(eatTime);
            } catch (InterruptedException e) {
                System.err.println("Consumer got an InterruptedException; message: "
                    + e.getMessage());
            }
            System.out.printf("%s took %.2f sec to eat the burger '%s'\n",
                getName(), eatTime / 1000.0f, burger);
        }
    }
}