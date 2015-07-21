import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NeuralNetDriver {
    
    public static void main(String[] args) {
        NeuralNetwork nn = new NeuralNetwork();

        Double[] i1arr = {0.0, 0.0, 1.0};
        List<Double> i1 = Arrays.asList(i1arr);
        Double[] i2arr = {0.0, 0.0, 0.0};
        List<Double> i2 = Arrays.asList(i2arr);
        Double[] i3arr = {0.0, 1.0, 0.0};
        List<Double> i3 = Arrays.asList(i3arr);
        Double[] i4arr = {1.0, 0.0, 1.0};
        List<Double> i4 = Arrays.asList(i4arr);
        Double[] i5arr = {1.0, 1.0, 1.0};
        List<Double> i5 = Arrays.asList(i5arr);

        Double[] o1Arr = {1.0, 1.0, 0.0, 0.0, 0.0};
        List<Double> o1 = Arrays.asList(o1Arr);

        List<Example> examples = new ArrayList<>(5);
        examples.add(new Example(i1, o1));
        examples.add(new Example(i2, o1));
        examples.add(new Example(i3, o1));
        examples.add(new Example(i4, o1));
        examples.add(new Example(i5, o1));

        NeuralNetwork result = NeuralNetUtils.backPropagationLearning(examples, nn);
    }

}