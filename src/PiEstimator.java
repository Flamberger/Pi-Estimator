import java.util.Random;

public class PiEstimator {
    //number of iterations the algorithm, a larger number here should result in a more accurate estimate
    public static int numberOfDarts = 100_000_000;
    public static void main(String[] args) {
        //how many darts lie within the quarter circle region
        int within = 0;
        Random r = new Random();
        for(int i = 0; i< numberOfDarts; i++){
            //get x and y coordinates for the darts
            double x = r.nextDouble();
            double y = r.nextDouble();
            //calculate the distance from the origin (0, 0) darts with a distance less than 1 are within the
            //quarter circle so add these to within
            double dist = Math.sqrt((x*x) + (y*y));
            if(dist < 1)
                within++;
        }

        //estimate pi by getting proportion of darts in the quarter circle and multiplying by 4.
        double estimate = (double)within/numberOfDarts *4;
        System.out.println(estimate);

    }
}


