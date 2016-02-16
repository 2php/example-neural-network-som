package neural_network_network;

import java.util.ArrayList;
import neural_network_topology.MatrixTopology;
import neural_network_topology.TopologyModel;

public class Network implements NetworkModel {

    private NeuronModel[] neuronList;

    private TopologyModel topology;

    public Network(TopologyModel topology, int weightNumber) {
        this.topology = topology;
        int numberOfNeurons = topology.getNumbersOfNeurons();

        neuronList = new Neuron[numberOfNeurons];

        for (int i = 0 ; i < numberOfNeurons ; i++){
            neuronList[i] = new Neuron(weightNumber);
        }
    }
    
    public int getNumbersOfNeurons() {
        return neuronList.length;
    }

    public NeuronModel getNeuron(int neuronNumber) {
        return neuronList[neuronNumber];
    }

    public void setTopology(TopologyModel topology) {
        this.topology = topology;
    }
   
    public TopologyModel getTopology() {
        return topology;
    }

    public ArrayList<double[]> getNeuronWeights() {
        ArrayList<double[]> weights = new ArrayList<double[]>();

        for (int i = 0 ; i < neuronList.length ; i++) {
            weights.add(neuronList[i].getWeight());
        }

        return weights;
    }

}
