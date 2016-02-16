package neural_network_som;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.Timer;
import neural_network_metric.EuclidesMetric;
import neural_network_metric.MetricModel;
import neural_network_network.Network;
import neural_network_network.NetworkModel;
import neural_network_topology.MatrixTopology;
import neural_network_topology.TopologyModel;

public class Framework extends JFrame {

    private neural_network_pattern.FileData read_file = new neural_network_pattern.FileData();
    private neural_network_pattern.Pattern dataPattern;
    private neural_network_som.Graph dataGraph;

    private TopologyModel topology;
    private MetricModel metric;
    private NetworkModel network;
    private SOM som;
    
    // the setting of GUI
    public JPanel inputPanel = null;
    public JTextField jt_learningRate = new JTextField(8);
    public JTextField jt_learningCount = new JTextField(8);
    public JTextField jt_neuron_row_Count = new JTextField(8);
    public JTextField jt_neuron_column_Count = new JTextField(8);

    public JButton jb_select_file = new JButton("選擇檔案");
    public JButton jb_training = new JButton("訓練");
    public JButton jb_testing = new JButton("測驗");

    public JPanel resultPanel = null;
    public JTextArea consoleOutput = null;

    public Framework() {
        setLayout(null);

        resultPanel = setResultPanel(0, 0, 200, 600);
        dataGraph = setDataGraph(200, 0, 600, 600);
        inputPanel = setInputPanel(800, 0, 200, 600);

        add(resultPanel);
        add(dataGraph);
        add(inputPanel);
    }

    protected neural_network_som.Graph setDataGraph(int init_x, int init_y, int width, int height) {
        dataGraph = new Graph(width, height);
        dataGraph.setBounds(init_x, init_y, width, height);
        return dataGraph;
    }
    
    protected JPanel setResultPanel(int init_x, int init_y, int width, int height) {
        resultPanel = new JPanel();
        resultPanel.setLayout(new BorderLayout());
        
        consoleOutput = new JTextArea(5, 20);
        consoleOutput.setMargin(new Insets(5, 5, 5, 5));
        consoleOutput.setEditable(false);

        resultPanel.add(new JScrollPane(consoleOutput), BorderLayout.CENTER);
        resultPanel.setBounds(init_x, init_y, width, height);

        return resultPanel;    
    }

    protected JPanel setInputPanel(int init_x, int init_y, int width, int height) {
        inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 20));

        inputPanel.add(jb_select_file);
        inputPanel.add(new JLabel("Input file"));
        inputPanel.add(new JLabel("學習率: "));
        inputPanel.add(jt_learningRate);
        inputPanel.add(new JLabel("學習次數: "));
        inputPanel.add(jt_learningCount);
        inputPanel.add(new JLabel("神經元數目: (row*column)     "));
        inputPanel.add(new JLabel("row: "));
        inputPanel.add(jt_neuron_row_Count);
        inputPanel.add(new JLabel("column: "));
        inputPanel.add(jt_neuron_column_Count);
        inputPanel.add(new JLabel("(欄位空白為套用預設值)"));
        inputPanel.add(jb_training);
        inputPanel.add(jb_testing);
        inputPanel.setBounds(init_x, init_y, width, height);

        jb_select_file.addActionListener(new CustomActionListener());
        jb_training.addActionListener(new CustomActionListener());
        jb_testing.addActionListener(new CustomActionListener());
        
        return inputPanel;
    }

    class CustomActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == jb_select_file) {
                try {
                    read_file.setDataSet();
                    dataPattern = new neural_network_pattern.Pattern();
                    dataPattern.set(read_file.getDataSet());
    
                    dataGraph.setCollection(dataPattern);
                    dataGraph.setType(neural_network_som.Graph.TYPE_BEFORE_TRAINING);
                    dataGraph.repaint();
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }
            } else if (e.getSource() == jb_training) {
                if (read_file == null) {
                    JOptionPane.showMessageDialog(null, "請選擇檔案");
                    return;
                }

                int weightNumber = dataPattern.getExamples().get(0).length;
                int learningCount = (!jt_learningCount.getText().isEmpty()) ? Integer.parseInt(jt_learningCount.getText()) : 10000;
                double learningRate = (!jt_learningRate.getText().isEmpty()) ? Double.parseDouble(jt_learningRate.getText()) : 0.1;
                int neuron_row_Count = (!jt_neuron_row_Count.getText().isEmpty()) ? Integer.parseInt(jt_neuron_row_Count.getText()) : 10;
                int neuron_column_Count = (!jt_neuron_column_Count.getText().isEmpty()) ? Integer.parseInt(jt_neuron_column_Count.getText()) : 10;

                metric = new EuclidesMetric();
                topology = new MatrixTopology(metric, neuron_row_Count, neuron_column_Count);
                network = new Network(topology, weightNumber);
                som = new SOM(network, metric, dataGraph, learningCount, consoleOutput);
                som.setLearningRate(learningRate);
                
                try {
                    som.learn(dataPattern.getExamples());
                } catch(Exception e1) {
                    e1.printStackTrace();
                }
            } else if (e.getSource() == jb_testing) {
                som.test(dataPattern.getExamples());
            }
        }
    }

}
