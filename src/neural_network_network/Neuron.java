package neural_network_network;

import java.util.Random;

public class Neuron implements NeuronModel {
    private double[] weight;

    public Neuron(int weightNumber) {
        Random rand = new Random();
        weight = new double[weightNumber];

        for (int i = 0 ; i < weightNumber ; i++) {
            weight[i] = rand.nextDouble();
        }
    }

    public double[] getWeight() {
        return weight.clone();
    }

    public void setWeight(double[] weight) {
        for (int i = 0; i < weight.length ; i++) {
            this.weight[i] = weight[i];
        }
    }

    public double activationFunction(double[] inputVector) {
        // activation Function
        return 0;
    }

    public double getValue(double[] inputVector) {
        // TODO Auto-generated method stub
        return 0;
    }

}
