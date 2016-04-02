package data_processing;

/**
 * Created by josh on 4/2/16.
 */
public class Driver {
    public static void main(String[] args) {
        DefaultDistribution defaultDistribution = new DefaultDistribution(10,0,10);
        defaultDistribution.calculateIndices();
        for (DistributionIndex distributionIndex:defaultDistribution.getIndices()){
            System.out.printf("Low: %f  High: %f\n", distributionIndex.getLow(),distributionIndex.getHigh());
        }
    }
}
