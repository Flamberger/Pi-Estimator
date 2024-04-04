import java.util.Random;

public class PiEstimator {
    // Number of iterations the algorithm, a larger number here should result in a more accurate estimate
    public static int numberOfDarts = 1_000_000_000;

    public static void main(String[] args) {
        // Create an instance of TotalWithin to keep track of the number of darts within the dart board
        TotalWithin totalWithin = new TotalWithin();

        // Create an array to hold references to dart throwing threads
        Thread[] threads = new Thread[4];

        // Start 4 threads to throw darts
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new DartThrowingThread(totalWithin);
            threads[i].start();
        }

        // Wait for all threads to finish
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Estimate pi by getting proportion of darts in the quarter circle and multiplying by 4.
        double estimate = (double) totalWithin.getCount() / numberOfDarts * 4;
        System.out.println("Estimated value of Pi: " + estimate);
    }
}

class TotalWithin {
    private int count;

    public synchronized void incrementCount() {
        count++;
    }

    public synchronized int getCount() {
        return count;
    }
}

class DartThrowingThread extends Thread {
    private TotalWithin totalWithin;
    private Random r;

    public DartThrowingThread(TotalWithin totalWithin) {
        this.totalWithin = totalWithin;
        this.r = new Random();
    }

    @Override
    public void run() {
        for (int i = 0; i < PiEstimator.numberOfDarts / 4; i++) {
            // Get x and y coordinates for the darts
            double x = r.nextDouble();
            double y = r.nextDouble();
            // Calculate the distance from the origin (0, 0) darts with a distance less than 1 are within the
            // quarter circle so add these to within
            double dist = Math.sqrt((x * x) + (y * y));
            if (dist < 1)
                totalWithin.incrementCount();
        }
    }
}


