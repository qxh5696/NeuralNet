package data_processing;

/**
 * Created by josh on 4/2/16.
 */
public class DistributionIndex {
    private double high,low;

    public DistributionIndex(double high, double low) {
        this.high = high;
        this.low = low;
    }

    public double getHigh() {
        return high;
    }

    public void setHigh(double high) {
        this.high = high;
    }

    public double getLow() {
        return low;
    }

    public void setLow(double low) {
        this.low = low;
    }
}
