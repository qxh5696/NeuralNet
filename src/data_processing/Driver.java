package data_processing;

/**
 * Created by josh on 4/2/16.
 */
public class Driver {
    public static void main(String[] args) {
        DefaultDistribution defaultDistribution = new DefaultDistribution(10,0,10);
        defaultDistribution.calculateIndices();
        System.out.println("STANDARD:");
        for (DistributionIndex distributionIndex:defaultDistribution.getIndices()){
            System.out.printf("Low: %f  High: %f\n", distributionIndex.getLow(),distributionIndex.getHigh());
        }
        System.out.println("GAUSSIAN:");
        GaussianDistribution gaussianDistribution = new GaussianDistribution(10,0,10);
        gaussianDistribution.calculateIndices();
        for (DistributionIndex distributionIndex: gaussianDistribution.getIndices()){
            System.out.printf("Low: %f  High: %f\n", distributionIndex.getLow(),distributionIndex.getHigh());
        }
    }
}
