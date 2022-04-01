package homework2;

public class Main {
    public static final int COUNT = 4;

    public static void main(String[] args) {
        BuyerSecond buyerSecond = new BuyerSecond();

        for (int i = 0; i < COUNT; i++) {
            new Thread(null, buyerSecond::buyTheCar, "покупатель" + i).start();
        }
        new Thread(null, buyerSecond::receiveCar, "Volga").start();


    }
}
