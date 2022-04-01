package homework1;

import java.util.*;

public class Buyer {
    private static final int CARS = 4;
    List<Car> list = new ArrayList<>(9);


    public void buy()  {
        try {
            System.out.printf("%s хочет купить авто\n", Thread.currentThread().getName());
            synchronized (this) {
                while(list.isEmpty()) {
                    System.out.println("Автомобилей нет.");
                    wait();
                }
                    System.out.printf("%s купил машину\n", Thread.currentThread().getName());
                    Thread.sleep(200);
                    list.remove(0);

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void receive() {
        for (int i = 0; i < CARS; i++) {
            synchronized (this) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                list.add(new Car());
                System.out.printf("%s привез\n", Thread.currentThread().getName());
                notify();
            }
        }
    }
}
