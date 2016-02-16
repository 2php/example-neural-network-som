![2ring@480](http://imgur.com/hDL6lH8.png)

Example-Neural-Network-SOM
=========

Example-Neural-Network-SOM is an implementation of self-organising feature map (SOFM, SOM), which is a type of artificial neural network that is trained using unsupervised learning to produce a low-dimensional map, written in Java.

Junior project in class "Neural Network". Last updated 11/25/2014.

How to use
-----------------------

- Select input file from `dataSet` directory.
- Field `學習率 (learning rate)` and `學習次數 (count of learning loop)` columns
or Field blank to use the default value.
- Field `神經元數目: (row*column)` columns to set matrix topology's size
or Field blank to use the default value.
- Click `訓練` button to train.
- Click `測驗` button to test.

Feature
-----------------------

### Graphics ###

Black dots for data set.

Blue dots for weight vectors of neurons.

### Console log ###

The leftmost field in gui is console log output.

It will show iteration number, best neuron number, winning number for each neuron.

    // console log example

    Iteration number: 1
    ...
    Best neuron number: 51
    ...
    神經元得勝次數
    =============
    neuron 1: 140000次
    neuron 2: 60000次
    neuron 3: 30000次
    ...

### DataSet (Trainging Set, Testing Set) ###

    // src/neural_network_pattern/Pattern.java
    ...
    int num_training = 2*dataSize/3 + 1;
    int num_testing = dataSize - num_training;

### Default Setting ###

    // src/neural_netowrk_som/Framework.java
    ...
    int learningCount = (!jt_learningCount.getText().isEmpty()) ? Integer.parseInt(jt_learningCount.getText()) : 10000;
    double learningRate = (!jt_learningRate.getText().isEmpty()) ? Double.parseDouble(jt_learningRate.getText()) : 0.1;
    int neuron_row_Count = (!jt_neuron_row_Count.getText().isEmpty()) ? Integer.parseInt(jt_neuron_row_Count.getText()) : 10;
    int neuron_column_Count = (!jt_neuron_column_Count.getText().isEmpty()) ? Integer.parseInt(jt_neuron_column_Count.getText()) : 10;

### Topology ###

    // src/neural_netowrk_topology/MatrixTopology.java
    public MatrixTopology(MetricModel metric, int row, int col) {
        this.metric = metric;
        this.rowNumber = row;
        this.colNumber = col;
    }
    ...

    // src/neural_netowrk_topology/MatrixTopology.java
    public Coords getNeuronCoordinate(int neuronNumber) {
        int x = ((neuronNumber - 1) / colNumber) + 1;
        int y = neuronNumber - ((x - 1) * colNumber);

        return new Coords(x, y);
    }
    ...

    // src/neural_netowrk_topology/MatrixTopology.java
    public int getNeuronNumber(Coords coords) {
        if (coords.x < 1 || coords.y < 1) return -1;
        
        if ((coords.x < rowNumber) && (coords.y < colNumber)) {
            return (coords.x - 1) * colNumber + coords.y;
        }

        return -1;
    }
    ...

### Neighbourhood Function ###

    // src/neural_netowrk_topology/MatrixTopology.java
    public double gaussNeighbourhoodFunction(double distance) {
        return java.lang.Math.exp(-(java.lang.Math.pow(distance, 2)) / (2 * radius * radius));
    }

Dependency
-----------------------

JDK

    $ java -version

    java version "1.8.0_66"
    Java(TM) SE Runtime Environment (build 1.8.0_66-b17)
    Java HotSpot(TM) 64-Bit Server VM (build 25.66-b17, mixed mode)

Build and run
-----------------------

### Compile ###

    $ javac -d bin -sourcepath src src/neural_network_som/*.java

### Run ###

    $ java -cp bin neural_network_som.Main

### Create jar file ###

    $ jar cfe neural_network_som.jar neural_network_som.Main -C bin/ .

    $ jar tf neural_network_som.jar # list table of contents for archive

    META-INF/
    META-INF/MANIFEST.MF
    .gitkeep
    neural_network_metric/
    neural_network_metric/EuclidesMetric.class
    neural_network_metric/MetricModel.class
    neural_network_network/
    neural_network_network/Network.class
    neural_network_network/NetworkModel.class
    neural_network_network/Neuron.class
    neural_network_network/NeuronModel.class
    neural_network_pattern/
    neural_network_pattern/FileData.class
    neural_network_pattern/Pattern.class
    neural_network_som/
    neural_network_som/Framework$CustomActionListener.class
    neural_network_som/Framework.class
    neural_network_som/Graph$TimerListener.class
    neural_network_som/Graph.class
    neural_network_som/Main.class
    neural_network_som/SOM.class
    neural_network_topology/
    neural_network_topology/Coords.class
    neural_network_topology/MatrixTopology.class
    neural_network_topology/TopologyModel.class

Screenshot
-----------------------

![2CS@480](http://imgur.com/S83te2P.png)

![5CloseS1@480](http://imgur.com/IIfS3j5.png)

![C3D@480](http://imgur.com/PKw02Ms.png)

More details in [http://imgur.com/a/3DeTU](http://imgur.com/a/3DeTU)

Reference
-----------------------

[1]. Janusz Rybarski, Seweryn Habdank-Wojewódzki, "[Java Kohonen Neural Network Library (JKNNL)](http://jknnl.sourceforge.net/)", 2013

[2]. Jochen Fröhlich, "[Kohonen Feature Map](http://www.nnwj.de/kohonen-feature-map.html)", 2004

[3]. MathWorks, "[Cluster with Self-Organizing Map Neural Network](http://www.mathworks.com/help/nnet/ug/cluster-with-self-organizing-map-neural-network.html)", 2015

[4]. ifs.tuwien.ac.at, "[SOM Visualisations available in the Java SOMToolbox](http://www.ifs.tuwien.ac.at/dm/somtoolbox/visualisations.html)", 2010

[5]. Jaakko Hollmen, "[U-matrix](http://users.ics.aalto.fi/jhollmen/dippa/node24.html)", 1996

[6]. Bashir Magomedov, "[Self-Organizing Feature Maps (Kohonen maps)](http://www.codeproject.com/Articles/16273/Self-Organizing-Feature-Maps-Kohonen-maps)", 2006

[7]. Sergiy Kovalchuk, "[How to Compile and Run Java Code from a Command Line](http://www.sergiy.ca/how-to-compile-and-launch-java-code-from-command-line/)", 2011

[8]. StackOverFlow, "[Create jar file from command line](http://stackoverflow.com/questions/11243442/create-jar-file-from-command-line)", 2012
