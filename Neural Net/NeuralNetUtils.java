import java.util.ArrayList;
import java.util.List;

public final class NeuralNetUtils {
    
    public static final int DEFAULT_NUM_PERCEPTRONS = 5;
    public static final int DEFAULT_NUM_LAYERS = 5;
    public static final double ALPHA = 0.1;
    public static final double MAX_THRESHOLD = 0.01;
    
    public static List<List<Double>> feedForward(List<Double> inputs,
                                            NeuralNetwork neuralNetwork) {

        List<List<Double>> values = new ArrayList<List<Double>>(neuralNetwork.getNumLayers());
        values.add(inputs);
        List<Double> curInput = inputs;
        // for (int i = 1; i < neuralNetwork.getNumLayers(); i++) {
        for (PerceptronLayer layer : neuralNetwork) {
            curInput = layer.getOutput(curInput);
            values.add(curInput);
        }
        return values;
    }

    public static NeuralNetwork backPropagationLearning(List<Example> examples,
                                                NeuralNetwork neuralNetwork) {

        List<List<Double>> deltas = new ArrayList<>(neuralNetwork.getNumLayers());
        for (int i = 0; i < neuralNetwork.getNumLayers(); i++) {
            deltas.add(new ArrayList<>(neuralNetwork.getOutputLayer().getSize()));
        }
        boolean unsatisfied = true;
        while (unsatisfied) {
            // for each example x, y in examples propagate the inputs forward to calculate the outputs
            for (Example example : examples) {
                List<Double> x = example.getInput();
                List<Double> y = example.getOutput();

                // feed inputs forward through the neural net with randomly
                // instantiated weights
                List<List<Double>> values = feedForward(x, neuralNetwork);
                List<Double> a = values.get(0);

                // minimize errors in output layer
                // note that the function for error minimization is different for 
                // the output layer from the other layers 
                unsatisfied = false;
                for (int i = 0; i < y.size(); i++) {
                    double in = values.get(values.size() - 1).get(i);
                    double gPrime = neuralNetwork.getOutputLayer().get(i).gPrime(in);
                    double difference = y.get(i) - a.get(i);
                    unsatisfied = difference > MAX_THRESHOLD;
                    deltas.get(deltas.size() - 1).add(difference);
                }
                // minimize errors in remaining layers
                for (int l = neuralNetwork.getNumLayers() - 2; l >= 0; l++) {
                    // for (Perceptron inbound : neuralNetwork.get(i)) {
                    for (int i = 0; i < neuralNetwork.get(l).getSize(); i++) {
                        Perceptron inbound = neuralNetwork.get(l).get(i);
                        // value going into inbound node, to be used as parameter 
                        // in node's gPrime function
                        double in = values.get(l).get(i);
                        // call inbound node's gPrime method
                        double gPrime = inbound.gPrime(in);
                        // iterate over outbound nodes, getting sum(weights i -> j * delta[i])
                        double summand = 0.0;
                        for (int j = 0; j < neuralNetwork.get(l + 1).getSize(); j++) {
                            Perceptron outbound = neuralNetwork.get(l + 1).get(j);
                            summand += outbound.sumWeights() * deltas.get(l + 1).get(j);
                        }
                        deltas.get(l).add(gPrime * summand);
                    }
                }
                // iterate over all nodes and update weights
                // start by iterating over input values
                for (int l = 0; l < values.size(); l++) {
                    List<Double> curValues = values.get(l);
                    PerceptronLayer curLayer = neuralNetwork.get(l);
                    for (int i = 0; i < curLayer.getSize(); i++) {
                        Perceptron curPerceptron = curLayer.get(i);
                        List<Double> newWeights = new ArrayList<>(curValues.size());
                        for (int j = 0; j < curPerceptron.getWeights().size(); j++) {
                            newWeights.add(curPerceptron.getWeights().get(j) +
                                ALPHA * curValues.get(j) * deltas.get(l).get(j));
                        }
                        curPerceptron.setWeights(newWeights);
                    }
                }
            }
        }
        return neuralNetwork;
    }

}