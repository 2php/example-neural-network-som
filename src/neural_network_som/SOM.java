package neural_network_som;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JTextArea;
import javax.swing.Timer;
import neural_network_metric.MetricModel;
import neural_network_network.NetworkModel;
import neural_network_network.NeuronModel;

public class SOM {

    protected MetricModel metrics;
    protected NetworkModel network;

    protected Graph datagraph;
    
    protected int maxIteration;

    protected double radius = 1;
    protected double leardingRate = 0.1;

    protected int[] winnerCount;

    private JTextArea consoleOutput;
    private String console_message = "";

    public SOM(NetworkModel networkModel, MetricModel metrics, Graph datagraph, int maxIteration, JTextArea consoleOutput) {
        this.network = networkModel;
        this.metrics = metrics;
        this.datagraph = datagraph;
        this.maxIteration = maxIteration;
        this.consoleOutput = consoleOutput;
        this.datagraph.setNetwork(this.network);
    }

    protected int getBestNeuron(double[] vector) {
        NeuronModel tempNeuron;
        double distance, bestDistance = -1;
        int networkSize = network.getNumbersOfNeurons();
        int bestNeuron = 0;

        for (int i = 0 ; i < networkSize ; i++) {
            tempNeuron = network.getNeuron(i);
            distance = metrics.getDistance(tempNeuron.getWeight(), vector);
            if ( (distance < bestDistance) || (bestDistance == -1) ) {
                bestDistance = distance;
                bestNeuron = i;
            }
        }

        return bestNeuron;
    }

    protected void changeNeuronWeight(int bestNeuron, double[] vector, int iteration) {
        int neuronNumbers = network.getTopology().getNumbersOfNeurons();

        for (int i = 0 ; i < neuronNumbers ; i++) {
            double[] weightList = network.getNeuron(i).getWeight();
            int weightNumbers = weightList.length;
            double weight;
    
            for (int j = 0 ; j < weightNumbers ; j++) {
                double distance = network.getTopology().getDistance(i, bestNeuron);

                weight = weightList[j];
                weightList[j] += getLearningRate(iteration) * network.getTopology().gaussNeighbourhoodFunction(distance) * (vector[j] - weight);
            }

            network.getNeuron(i).setWeight(weightList);
        }
    }
    
    public void learn(ArrayList<double[]> data) {
        int bestNeuron = 0;
        double[] vector;

        for (int i = 0 ; i< maxIteration ; i++) {
            System.out.println("Iteration number: " + (i + 1));

            for (int j = 0 ; j < data.size() ; j++) {
                vector = data.get(j);
                bestNeuron = getBestNeuron(vector);

                System.out.println("Best neuron number: " + (bestNeuron + 1));

                changeNeuronWeight(bestNeuron, vector, i);
            }

            datagraph.setType(neural_network_som.Graph.TYPE_AFTER_TRAINING);
        }
    }

    public void test(ArrayList<double[]> data) {
        int bestNeuron = 0;
        double[] vector;
        console_message = "";
        
        winnerCount = new int[network.getNumbersOfNeurons()];

        for (int i = 0 ; i < winnerCount.length ; i++) {
            winnerCount[i] = 0;
        }

        for (int i = 0 ; i< maxIteration ; i++) {
            System.out.println("Iteration number: " + (i + 1));

            for (int j = 0 ; j < data.size() ; j++) {
                vector = data.get(j);
                bestNeuron = getBestNeuron(vector);
                ++winnerCount[bestNeuron];

                System.out.println("Best neuron number: " + (bestNeuron + 1));
            }
        }

        console_message += "神經元得勝次數\n";
        console_message += "=============\n";
        
        for (int i = 0 ; i < winnerCount.length ; i++) {
            console_message += "neuron " + (i+1) + ": " + winnerCount[i] + "次\n";
        }

        consoleOutput.setText(console_message);
    }

    public void setLearningRate(double leardingRate) {
        this.leardingRate = leardingRate;
    }

    protected double getLearningRate(int iteration) {
        // TODO
        return leardingRate;
    }

}
