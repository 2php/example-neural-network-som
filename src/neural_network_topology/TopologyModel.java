package neural_network_topology;

import java.util.ArrayList;
import java.util.TreeMap;

public interface TopologyModel {

    public int getColNumber();

    public Coords getNeuronCoordinate(int neuronNumber);

    public int getNeuronNumber(Coords coords);

    public int getNumbersOfNeurons();

    public int getRadius();

    public double getDistance(int neuronNumber, int bestNeuron);

    public int getRowNumber();

    public void setColNumber(int colNumber);

    public void setRowNumber(int rowNumber);

    public void setRadius(int radius);

    public double gaussNeighbourhoodFunction(double distance);

    public int getLeftNeuronNumber(int neuronNumber);
    
    public int getTopNeuronNumber(int neuronNumber);

}