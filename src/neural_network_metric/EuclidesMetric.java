package neural_network_metric;

public class EuclidesMetric implements MetricModel {

    public double getDistance(double[] firstVector, double[] secondVector) {
        double distance = 0;
        double x = 0;
        double w = 0;
        double sum = 0;
        int weightSize = firstVector.length;

        if (weightSize != secondVector.length) return -1;

        for (int i = 0 ; i < weightSize ; i++) {
            w = firstVector[i]; 
            x = secondVector[i];
            sum += (x - w) * (x - w);
        }

        distance = Math.sqrt(sum);
        return distance;
    }

}
