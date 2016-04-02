package data_processing;

import org.apache.commons.math3.distribution.ChiSquaredDistribution;

/**
 * Created by josh on 4/2/16.
 */
public class ChiSquareDistribution extends Distribution {
    public ChiSquareDistribution(double highBound, double lowBound, int numOfIndices) {
        super(highBound, lowBound, numOfIndices);
    }

    @Override
    public void calculateIndices() {
        org.apache.commons.math3.distribution.ChiSquaredDistribution chiSquaredDistribution = new org.apache.commons.math3.distribution.ChiSquaredDistribution(getNumOfIndices()-1);
        double step = (getHighBound()-getLowBound())/(double)getNumOfIndices();
        double range = (getHighBound()-getLowBound());
        for(double d = getLowBound(); d<getHighBound(); d+= step){
            DistributionIndex distributionIndex = new DistributionIndex(chiSquaredDistribution.inverseCumulativeProbability(d/range),chiSquaredDistribution.inverseCumulativeProbability((d+step)/range));
            this.getIndices().add(distributionIndex);
        }
    }
}
