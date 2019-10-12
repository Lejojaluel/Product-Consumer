/**
 * Producer Consumer example using BlockingQueue.
 *
 * @author Leroy Valencia
 */

import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class PCDriver {

    public static void main(String[] args) {
        BlockingQueue<Burger> connectionPC = new LinkedBlockingQueue<Burger>();

        Producer p0 = new Producer(connectionPC, "Burger cook 0");
        Producer p1 = new Producer(connectionPC, "Burger cook 1");
        Producer p2 = new Producer(connectionPC, "Burger cook 2");

        ArrayList<Consumer> consArr = new ArrayList<Consumer>();
//        for (BurgerType b : BurgerType.values()) {
//            System.out.println(b);
//            consArr.add(new Consumer(connectionPC, (b +" eater"), b));
//        }
        consArr.add(new Consumer(connectionPC, (BurgerType.TOFU + " eater"), BurgerType.TOFU));
        consArr.add(new Consumer(connectionPC, (BurgerType.BACON + " eater"), BurgerType.BACON));
        System.out.println("Queue: " + connectionPC);
        System.out.println(consArr.get(0));
        System.out.println(consArr.get(1));
        consArr.get(0).start();
        //p0.start();
        //p1.start();
        //p2.start();
//
//        for (Consumer c : consArr) {
//            System.out.println(c);
//            c.start();
//        }
    }
}