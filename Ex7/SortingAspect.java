package sortingClean;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import java.util.HashMap;
import java.util.Map;

@Aspect
public class SortingAspect {

    private long overallStartTime;
    private long overallEndTime;

    private int mergeSortCounter = 0;
    private long mergeSortTotalTime = 0;

    private int quickSortCounter = 0;
    private long quickSortTotalTime = 0;

    private int bubbleSortCounter = 0;
    private long bubbleSortTotalTime = 0;

    private int insertionSortCounter = 0;
    private long insertionSortTotalTime = 0;

    private Map<JoinPoint, Long> startTimeMap = new HashMap<>();

    @Pointcut("execution(* *.sort(..))")
    public void sortMethods() {}

    @Before("execution(* AlgorithmRunner.runAlgorithms(..))")
    public void beforeAllSortMethods() {
        overallStartTime = System.currentTimeMillis();
    }

    @After("execution(* AlgorithmRunner.runAlgorithms(..))")
    public void afterAllSortMethods() {
        overallEndTime = System.currentTimeMillis();
        long totalTime = overallEndTime - overallStartTime;
        System.out.println("Total time of running all sort functions was " + totalTime + " ms");
        System.out.println("In detail:");

        if (mergeSortCounter > 0) {
            System.out.println("Function sort in MergeSort ran " + mergeSortCounter + " times and took in total " + mergeSortTotalTime + " ms");
        }
        if (quickSortCounter > 0) {
            System.out.println("Function sort in QuickSort ran " + quickSortCounter + " times and took in total " + quickSortTotalTime + " ms");
        }
        if (bubbleSortCounter > 0) {
            System.out.println("Function sort in BubbleSort ran " + bubbleSortCounter + " times and took in total " + bubbleSortTotalTime + " ms");
        }
        if (insertionSortCounter > 0) {
            System.out.println("Function sort in InsertionSort ran " + insertionSortCounter + " times and took in total " + insertionSortTotalTime + " ms");
        }
    }

    @Before("sortMethods()")
    public void beforeSortMethod(JoinPoint joinPoint) {
        startTimeMap.put(joinPoint, System.currentTimeMillis());
    }

    @After("sortMethods() && target(obj)")
    public void afterSortMethod(JoinPoint joinPoint, Object obj) {
        long endTime = System.currentTimeMillis();
        long startTime = startTimeMap.get(joinPoint);
        long duration = endTime - startTime;


        String algorithmName = joinPoint.getTarget().getClass().getSimpleName();
        switch (algorithmName) {
            case "MergeSort":
                mergeSortCounter++;
                mergeSortTotalTime += duration;
                break;
            case "QuickSort":
                quickSortCounter++;
                quickSortTotalTime += duration;
                break;
            case "BubbleSort":
                bubbleSortCounter++;
                bubbleSortTotalTime += duration;
                break;
            case "InsertionSort":
                insertionSortCounter++;
                insertionSortTotalTime += duration;
                break;
        }

    }
}

