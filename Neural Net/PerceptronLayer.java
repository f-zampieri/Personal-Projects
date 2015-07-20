import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PerceptronLayer implements Iterable<Perceptron> {

	private List<Perceptron> layer;

	public PerceptronLayer(int size) {

		this.layer = new ArrayList<>(size);
		for (int i = 0; i < size; i++) {
			this.layer.add(new SigPerceptron(NeuralNetUtils.DEFAULT_NUM_PERCEPTRONS));
		}
	}

	public List<Double> getOutput(List<Double> inputs) {

		List<Double> outputs;
		outputs = new ArrayList<Double>(layer.size());
		for (Perceptron perceptron : this.layer) {
			outputs.add(perceptron.activate(inputs));
		}
		return outputs;
	}

	public Iterator<Perceptron> iterator() {
		return layer.iterator();
	}

}