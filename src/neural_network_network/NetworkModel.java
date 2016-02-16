package neural_network_network;

import java.util.ArrayList;
import neural_network_topology.TopologyModel;

public interface NetworkModel {

    public NeuronModel getNeuron(int neuronNumber);

    public int getNumbersOfNeurons();

    public TopologyModel getTopology();

    public void setTopology(TopologyModel topology);

    public ArrayList<double[]> getNeuronWeights();
}
