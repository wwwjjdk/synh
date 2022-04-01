package homework1;

public class Main {

    public static void main(String[] args) {
        Buyer buyer= new Buyer();
        for(int i = 0;i <3 ;i++){
            new Thread(null, buyer::buy, "покупатель "+ i).start();
        }
        new Thread(null, buyer::receive,"продавец").start();
    }
}
