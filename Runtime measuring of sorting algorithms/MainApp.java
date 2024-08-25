package sortingClean;

import jakarta.enterprise.inject.Produces;
import jakarta.inject.Named;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;
import java.util.Random;


public class MainApp {
    static WeldContainer container = new Weld().initialize();
    public static void main(String[] args) {
        // TODO: Change this line to initialize an injected AlgorithmRunner
        AlgorithmRunner algorithmRunner = container.select(AlgorithmRunner.class).get();

        algorithmRunner.runAlgorithms();
    }
    private SortingAlgorithm<Integer> makeRandomSortingAlgorithm() {
        Random random = new Random(System.currentTimeMillis());
        switch (random.nextInt(4)) {
            case 0:
                return container.select(QuickSort.class).get();
            case 1:
                return container.select(MergeSort.class).get();
            case 2:
                return container.select(BubbleSort.class).get();
            case 3:
                return container.select(InsertionSort.class).get();
            default:
                throw new IllegalStateException("Unexpected value: " + random.nextInt(4));
        }
    }
    // TODO: Add producers
    @Produces
    public @Named("quadraticAlgorithm") SortingAlgorithm<Integer> produceQuadraticAlgorithm() {
        return container.select(BubbleSort.class).get();
    }

    @Produces
    public @Named("nlognAlgorithm") SortingAlgorithm<Integer> produceNlognAlgorithm() {
        return container.select(QuickSort.class).get();
    }

    @Produces
    public @Named("randomAlgorithm1") SortingAlgorithm<Integer> produceRandomAlgorithm1() {
        return makeRandomSortingAlgorithm();
    }

    @Produces
    public @Named("randomAlgorithm2") SortingAlgorithm<Integer> produceRandomAlgorithm2() {
        return makeRandomSortingAlgorithm();
    }

    @Produces
    public @Named("NumberOfElements") int produceNumberOfElements() {
        return 10000;
    }

}
