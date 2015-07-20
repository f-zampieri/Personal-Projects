import java.util.List;

/**
 * A Perceptron is a basic model of a neuron. Given inputs it yields a result
 * based on the Perceptron's activation function. The activation function is 
 * either the threshold function or the logistic function.
 */
public interface Perceptron {
	
	/**
	 * Weights the inputs and passes them through the activation function.
	 */ 
	public double activate(List<Double> inputs);

	/**
	 * Returns the derivative of the node's activation function.
	 */ 
	public double gPrime(double x);

	/**
	 * Returns the Perceptron's weights.
	 */
	public List<Double> getWeights();

}