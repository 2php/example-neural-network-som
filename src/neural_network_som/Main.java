package neural_network_som;

import javax.swing.JFrame;

public class Main {

    public static Framework frame = new Framework();

    public static void main(String[] args) {
        frame.setSize(1000, 650);
        frame.setLocationRelativeTo(null);
        frame.setTitle("Neural_Network_SOM");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

}
