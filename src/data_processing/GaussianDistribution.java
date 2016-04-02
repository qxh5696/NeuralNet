package data_processing;

import org.apache.commons.math3.distribution.NormalDistribution;

/**
 * Created by josh on 4/2/16.
 */
public class GaussianDistribution extends Distribution {
    public GaussianDistribution(double highBound, double lowBound, int numOfIndices) {
        super(highBound, lowBound, numOfIndices);
    }

    @Override
    public void calculateIndices() {
        double sigma = (getHighBound()-getLowBound())/4.0;
        double mean = (getHighBound()-getLowBound())/2.0;
        double step = (getHighBound()-getLowBound())/(double)getNumOfIndices();
        double range = (getHighBound()-getLowBound());
        NormalDistribution a = new NormalDistribution(mean,sigma);
        for(double d = getLowBound(); d<getHighBound(); d += step){
            DistributionIndex distributionIndex = new DistributionIndex(a.inverseCumulativeProbability(d/range),a.inverseCumulativeProbability((d+step)/range));
            this.getIndices().add(distributionIndex);
        }

    }
}
