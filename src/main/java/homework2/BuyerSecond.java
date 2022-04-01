package homework2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BuyerSecond {
    Lock lock = new ReentrantLock(true);
    List<CarSecond> carSeconds = new ArrayList<>();
    Condition condition = lock.newCondition();

    public static final int TIME = 4;
    public static final int TIME_OUT = 1000;

    public void receiveCar() {
        try {
            lock.lock();
            for (int i = 0; i < TIME; i++) {
                Thread.sleep(1000);
                carSeconds.add(new CarSecond());
                System.out.printf("%s привез\n", Thread.currentThread().getName());
                condition.signal();
            }
            Thread.sleep(TIME_OUT);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


    public void buyTheCar() {
        try {
            lock.lock();
            System.out.printf("%s хочет купить авто\n", Thread.currentThread().getName());
            while (carSeconds.size() == 0) {
                System.out.printf("%s авто нет\n", Thread.currentThread().getName());
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            try {
                Thread.sleep(TIME_OUT);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            carSeconds.remove(0);
            System.out.printf("%s уехал на новой машина, на складе осталось %s\n",
                    Thread.currentThread().getName(), carSeconds.size());
        } finally {
            lock.unlock();
        }

    }

}
