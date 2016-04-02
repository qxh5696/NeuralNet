package data_processing;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by josh on 4/2/16.
 */
public abstract class Distribution {
    private List<DistributionIndex> indices;
    private double highBound, lowBound;
    private int numOfIndices;

    public Distribution(double highBound, double lowBound, int numOfIndices) {
        this.highBound = highBound;
        this.lowBound = lowBound;
        this.numOfIndices = numOfIndices;
        this.indices = new ArrayList<>();
    }



    public List<DistributionIndex> getIndices() {
        return indices;
    }

    public void setIndices(List<DistributionIndex> indices) {
        this.indices = indices;
    }

    public double getHighBound() {
        return highBound;
    }

    public void setHighBound(double highBound) {
        this.highBound = highBound;
    }

    public double getLowBound() {
        return lowBound;
    }

    public void setLowBound(double lowBound) {
        this.lowBound = lowBound;
    }

    public int getNumOfIndices() {
        return numOfIndices;
    }

    public void setNumOfIndices(int numOfIndices) {
        this.numOfIndices = numOfIndices;
    }

    public void calculateIndices(){
        double modifier = (highBound-lowBound)/numOfIndices;
        for (int i = 0; i<numOfIndices; i++){
            DistributionIndex distributionIndex = new DistributionIndex((i*modifier),modifier* (i+1));
            this.indices.add(distributionIndex);
        }
    }

    public boolean[] testIndices(double[] testItems){
        boolean[] results = new boolean[getIndices().size()];
        for(double d: testItems){
            for (int j = 0; j< indices.size(); j++){
                if(indices.get(j).test(d)){
                    results[j] = true;
                }
            }
        }
        return results;
    }

}
