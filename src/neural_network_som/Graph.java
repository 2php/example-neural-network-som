package neural_network_som;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.LinkedList;
import javax.swing.JPanel;
import javax.swing.Timer;
import neural_network_network.NetworkModel;
import neural_network_pattern.Pattern;

public class Graph extends JPanel {
    public int WIDTH;
    public int HEIGHT;

    public static final int INIT = 0;
    public static final int TYPE_BEFORE_TRAINING = 1;
    public static final int TYPE_AFTER_TRAINING = 2;
    public static final int TYPE_TESTING = 3;

    protected int type;

    protected Pattern dataSet;

    // topology
    protected NetworkModel network;

    protected int delay = 125;
    private Timer timer = new Timer(delay, new TimerListener());

    public Graph(int width, int height) {
        WIDTH = width;
        HEIGHT = height;
        type = INIT;
        this.setBackground(Color.white);
        timer.start();
    }

    public void setCollection(Pattern set) {
        this.dataSet = set;
    }

    public void setNetwork(NetworkModel network) {
        this.network = network;
    }

    public void setType(int type) {
        this.type = type;
    }

    protected void drawSet(Graphics2D g2, ArrayList<double[]> set, Color color) {
        double[] data = null;
        Ellipse2D e;

        for (int i = 0 ; i < set.size() ; i++) {
            data = set.get(i);

            e = addPoint(data[0], data[1]);

            g2.setPaint(color);
            g2.fill(e);    
        }
    }

    protected void drawTopology(Graphics2D g2, Color color) {
        ArrayList<double[]> set = network.getNeuronWeights();
        double[] data = null;
        Ellipse2D e;
        
        for (int i = 0 ; i < set.size() ; i++) {
            data = set.get(i);

            int neuron = i+1;
            int leftNeuron = network.getTopology().getLeftNeuronNumber(neuron);
            int topNeuron = network.getTopology().getTopNeuronNumber(neuron);

            // line to left            
            if (leftNeuron != -1) {
                leftNeuron -= 1;
                Ellipse2D point1 = addPoint(set.get(leftNeuron)[0], set.get(leftNeuron)[1]);
                Ellipse2D point2 = addPoint(data[0], data[1]);

                g2.draw(new Line2D.Double(point1.getX(), point1.getY(), point2.getX(), point2.getY()));
            }
            // line to top
            if (topNeuron != -1) {
                topNeuron -= 1;
                Ellipse2D point1 = addPoint(set.get(topNeuron)[0], set.get(topNeuron)[1]);
                Ellipse2D point2 = addPoint(data[0], data[1]);

                g2.draw(new Line2D.Double(point1.getX(), point1.getY(), point2.getX(), point2.getY()));
            }

            e = addPoint(data[0], data[1]);
            g2.setPaint(color);
            g2.fill(e);    
        }
    }
    
    protected Ellipse2D addPoint(double x, double y) {
        double point_x = WIDTH/2 + x*10;
        double point_y = HEIGHT/2 - y*10;
        
        Ellipse2D e = new Ellipse2D.Double(point_x, point_y, 3, 3);

        return e;
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        //draw Axis
        double width = getWidth();
        double height = getHeight();

        g2.draw(new Line2D.Double(0, height/2, width, height/2));
        g2.draw(new Line2D.Double(width/2, 0, width/2, height));

        switch (type) {
            case TYPE_BEFORE_TRAINING:
                drawSet(g2, dataSet.getExamples(), Color.black);
                break;
            case TYPE_AFTER_TRAINING:
                drawSet(g2, dataSet.getExamples(), Color.black);
                drawTopology(g2, Color.blue);
                break;
            default:
                break;
        }
    }

    class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            repaint();
        }
    }

}
