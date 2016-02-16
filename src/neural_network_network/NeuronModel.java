package neural_network_network;

public interface NeuronModel{

    public double[] getWeight();

    public void setWeight(double[] weight);

    public double getValue(double[] inputVector);

}
