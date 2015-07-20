import java.util.ArrayList;
import java.util.List;

public final class NeuralNetUtils {
	
	public static final int DEFAULT_NUM_PERCEPTRONS = 5;
	public static final int DEFAULT_NUM_LAYERS = 5;

	public static List<Double> feedForward(List<Double> inputs,
											NeuralNetwork neuralNetwork) {

		List<Double> curInput = inputs;
		// for (int i = 1; i < neuralNetwork.getNumLayers(); i++) {
		for (PerceptronLayer layer : neuralNetwork) {
			curInput = layer.getOutput(curInput);
		}
		return curInput;
	}

	public static NeuralNetwork backPropagationLearning(List<Example> examples,
												NeuralNetwork neuralNetwork) {
		return backPropagationLearning(examples,
										neuralNetwork,
										DEFAULT_NUM_LAYERS,
										DEFAULT_NUM_PERCEPTRONS);
	}

	public static NeuralNetwork backPropagationLearning(List<Example> examples,
												NeuralNetwork neuralNetwork,
												int numLayers,
												int numPerceptrons) {

		List<List<Double>> deltas = new ArrayList<>(numLayers);
		for (int i = 0; i < numLayers; i++) {
			deltas.add(new ArrayList<>(numPerceptrons));
		}

		// for each example x, y in examples propagate the inputs forward to calculate the outputs
		for (Example example : examples) {
			List<Double> x = example.getInput();
			List<Double> y = example.getOutput();

			// feed inputs forward through the neural net with randomly
			// instantiated weights
			List<Double> a = feedForward(x, neuralNetwork);

			// minimize errors in output layer
			for (int i = 0; i < y.size(); i++) {

			}
		}



		return null;
	}



}