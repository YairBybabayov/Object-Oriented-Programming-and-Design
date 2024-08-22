package sortingClean;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.util.Random;

// TODO: Add dependency injection and annotations to this file
public class AlgorithmRunner {
    @Inject
    @Named("quadraticAlgorithm") sortingClean.SortingAlgorithm<Integer> quadraticAlgorithm;
    @Inject
    @Named("nlognAlgorithm") sortingClean.SortingAlgorithm<Integer> nlognAlgorithm;
    @Inject
    @Named("randomAlgorithm1") sortingClean.SortingAlgorithm<Integer> randomAlgorithm1;
    @Inject
    @Named("randomAlgorithm2") sortingClean.SortingAlgorithm<Integer> randomAlgorithm2;
    @Inject
    @Named("NumberOfElements") int numberOfElements;


    public void runAlgorithms(){
        Random rand = new Random();
        Integer[] ints = rand.ints(1,Integer.MAX_VALUE)
                .distinct()
                .limit(numberOfElements)
                .boxed()
                .toArray(Integer[]::new);
        Integer[] intsClone = ints.clone();
        quadraticAlgorithm.sort(intsClone);
        intsClone = ints.clone();
        nlognAlgorithm.sort(intsClone);
        intsClone = ints.clone();
        randomAlgorithm1.sort(intsClone);
        intsClone = ints.clone();
        randomAlgorithm2.sort(intsClone);
    }

}
