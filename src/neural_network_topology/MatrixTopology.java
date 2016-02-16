package neural_network_topology;

import java.util.ArrayList;
import java.util.TreeMap;

import neural_network_metric.MetricModel;

public class MatrixTopology implements TopologyModel {
    private int colNumber;
    private int rowNumber;
    private int radius = 1;

    private MetricModel metric;

    public MatrixTopology(MetricModel metric, int row, int col) {
        this.metric = metric;
        this.rowNumber = row;
        this.colNumber = col;
    }

    public int getColNumber() {
        return this.colNumber;
    }

    public Coords getNeuronCoordinate(int neuronNumber) {
        int x = ((neuronNumber - 1) / colNumber) + 1;
        int y = neuronNumber - ((x - 1) * colNumber);

        return new Coords(x, y);
    }

    public int getNeuronNumber(Coords coords) {
        if (coords.x < 1 || coords.y < 1) return -1;
        
        if ((coords.x < rowNumber) && (coords.y < colNumber)) {
            return (coords.x - 1) * colNumber + coords.y;
        }

        return -1;
    }

    public int getLeftNeuronNumber(int neuronNumber) {
        Coords coords = getNeuronCoordinate(neuronNumber);
        int targetNumber = getNeuronNumber(new Coords(coords.x, coords.y-1));

        return targetNumber;
    }

    public int getTopNeuronNumber(int neuronNumber) {
        Coords coords = getNeuronCoordinate(neuronNumber);
        int targetNumber = getNeuronNumber(new Coords(coords.x-1, coords.y));

        return targetNumber;
    }

    public int getNumbersOfNeurons() {
        return colNumber * rowNumber;
    }

    public int getRadius() {
        return radius;
    }

    public double getDistance(int neuronNumber, int bestNeuron) {
        Coords targetNeuronCoords = getNeuronCoordinate(neuronNumber);
        Coords bestNeuronCoords = getNeuronCoordinate(bestNeuron);

        double[] firstVector = {targetNeuronCoords.x, targetNeuronCoords.y};
        double[] secondVector = {bestNeuronCoords.x, bestNeuronCoords.y};
        
        return metric.getDistance(firstVector, secondVector);
    }

    public int getRowNumber() {
        return this.rowNumber;
    }

    public void setColNumber(int colNumber) {
        this.colNumber = colNumber;
    }

    public void setRowNumber(int rowNumber) {
        this.rowNumber = rowNumber;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public double gaussNeighbourhoodFunction(double distance) {
        return java.lang.Math.exp(-(java.lang.Math.pow(distance, 2)) / (2 * radius * radius));
    }

}

