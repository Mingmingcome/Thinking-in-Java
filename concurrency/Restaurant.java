// 2017-12-12 20:39:21
// The producer-consumer approach to task cooperation
import java.util.concurrent.*;

class Meal {
    private final int orderNum;
    public Meal(int orderNum) {
        this.orderNum = orderNum;
    }
    public String toString() {
        return "Meal " + orderNum;
    }
}

class WaitPerson implements Runnable {
    private Restaurant restaurant;
    public WaitPerson(Restaurant r) {
        restaurant = r;
    }
    public void run() {
        try {
            while (!Thread.interrupted()) {
                synchronized(this) {
                    while (restaurant.meal == null) {
                        wait();
                    }
                }
                System.out.println("waitperson got " + restaurant.meal);
                synchronized(restaurant.chef) {
                    restaurant.meal = null;
                    restaurant.chef.notifyAll();
                }
            }
        } catch(InterruptedException e) {
            System.out.println("waitperson interrupted");
        }
    }
}

class Chef implements Runnable {
    private Restaurant restaurant;
    private int count = 0;
    public Chef(Restaurant r) {
        restaurant = r;
    }
    public void run() {
        try {
            while (!Thread.interrupted()) {
                synchronized(this) {
                    while (restaurant.meal != null) {
                        wait();
                    }
                }
                if (++count == 10) {
                    System.out.println("Out of food, closing");
                    restaurant.exec.shutdownNow();
                }
                System.out.println("Order up!");
                synchronized(restaurant.waitperson) {
                    restaurant.meal = new Meal(count);
                    restaurant.waitperson.notifyAll();
                }
                TimeUnit.MILLISECONDS.sleep(100);
            }
            
        } catch(InterruptedException e) {
            System.out.println("Chef interrupted");
        }
    }
}

public class Restaurant {
    Meal meal;
    ExecutorService exec = Executors.newCachedThreadPool();
    WaitPerson waitperson = new WaitPerson(this);
    Chef chef = new Chef(this);
    public Restaurant() {
        exec.execute(chef);
        exec.execute(waitperson);
    }
    public static void main(String[] args) {
        new Restaurant();
    }
}