/**
 * Producer Consumer example using BlockingQueue.
 *
 * @author Leroy Valencia
 */

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class PCDriver {

    public static void main(String[] args) {
        BlockingQueue<String> connectionPC = new LinkedBlockingQueue<String>();

        Producer p = new Producer(connectionPC);
        Consumer c = new Consumer(connectionPC);

        System.out.println("Queue: " + connectionPC);

        p.start();
        c.start();
    }
}