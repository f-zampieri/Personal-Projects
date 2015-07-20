import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class NeuralNetwork implements Iterable<PerceptronLayer> {
	
	private PerceptronLayer inputLayer;
	private PerceptronLayer outputLayer;
	private List<PerceptronLayer> layers;

	/**
	 * @param size This number does not include the input layer.
	 */
	public NeuralNetwork(int size) {
		this.layers = new ArrayList<PerceptronLayer>(size - 1);
		for (int i = 0; i < size - 1; i++) {
			this.layers.add(new PerceptronLayer(NeuralNetUtils.DEFAULT_NUM_PERCEPTRONS));
		}
		this.inputLayer  = this.layers.get(0);
		this.outputLayer = this.layers.get(this.layers.size() - 1);
	}

	public NeuralNetwork() {
		this(NeuralNetUtils.DEFAULT_NUM_LAYERS);
	}

	public int getNumLayers() {
		return layers.size();
	}

	public PerceptronLayer getInputLayer() {
		return inputLayer;
	}

	public PerceptronLayer getOutputLayer() {
		return outputLayer;
	}

	public Iterator<PerceptronLayer> iterator() {
		return layers.iterator();
	}

}