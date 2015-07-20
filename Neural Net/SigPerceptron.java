import java.util.ArrayList;
import java.util.List;

public class SigPerceptron implements Perceptron {
	
	private List<Double> weights;

	/**
	 * Initializes Perceptron with n random weights.
	 */ 
	public SigPerceptron(int numWeights) {
		initWeights(numWeights + 1);
	}

	/** 
	 * Initialize Perceptron given List of weights.
	 */ 
	public SigPerceptron(List<Double> weights) {
		initWeights(weights.size() + 1);
		this.weights.addAll(weights);
	}

	public double activate(List<Double> inputs) {
		return sigmoid(perceptronInput(inputs));
	}

	public double gPrime(double x) {
		double gP = sigmoid(x);
		return gP * (1 - gP);
	}

	public List<Double> getWeights() {
		return this.weights;
	}

	private double sigmoid(double x) {
		return 1.0 / (1.0 + Math.pow(Math.E, -x));
	}

	private Double perceptronInput(List<Double> inputs) {
		double counter = 0.0;
		for (int i = 0; i < inputs.size(); i++) {
			counter +=  this.weights.get(i+1) * inputs.get(i);
		}
		counter += weights.get(0);
		return counter;
	}

	private void initWeights(int n) {
		this.weights = new ArrayList<Double>(n);
		// adds bias
		this.weights.add(0, 1.0);
	}

}