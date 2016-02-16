package neural_network_topology;

public class GaussNeighbourhoodFunction {

    private double r; // radius

    public GaussNeighbourhoodFunction(int radius) {
        this.r = radius;
    }

    public double[] getParameters(){
        double[] paremateres = new double[1];
        paremateres[0] = r;
        return paremateres;    
    }

    public void setParameters(double[] parameters){
        r = parameters[0];
    }

    public double getValue(int distance){
       return java.lang.Math.exp(-(java.lang.Math.pow(distance, 2))/ (2 * r * r ));
    }

}
