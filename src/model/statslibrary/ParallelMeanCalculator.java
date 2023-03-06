package model.statslibrary;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ParallelMeanCalculator extends RecursiveTask<Double> {
    private static final int SEQUENTIAL_THRESHOLD = 10000;
    private final double[] data;
    private final int startIndex;
    private final int endIndex;

    public ParallelMeanCalculator(double[] data) {
        this(data, 0, data.length);
    }

    private ParallelMeanCalculator(double[] data, int startIndex, int endIndex) {
        this.data = data;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
    }

    @Override
    protected Double compute() {
        int length = endIndex - startIndex;

        if (length <= SEQUENTIAL_THRESHOLD) {
            // Compute the mean sequentially
            double sum = 0.0;
            for (int i = startIndex; i < endIndex; i++) {
                sum += data[i];
            }
            return sum / length;
        } else {
            // Divide the task into two subtasks and fork them
            int mid = startIndex + length / 2;
            ParallelMeanCalculator leftTask = new ParallelMeanCalculator(data, startIndex, mid);
            ParallelMeanCalculator rightTask = new ParallelMeanCalculator(data, mid, endIndex);
            leftTask.fork();
            double rightResult = rightTask.compute();
            double leftResult = leftTask.join();

            // Combine the results
            return (leftResult * leftTask.length() + rightResult * rightTask.length()) / length;
        }
    }

    private int length() {
        return endIndex - startIndex;
    }

    public static double calculate(double[] data) {
        ForkJoinPool pool = new ForkJoinPool();
        return pool.invoke(new ParallelMeanCalculator(data));
    }
}
