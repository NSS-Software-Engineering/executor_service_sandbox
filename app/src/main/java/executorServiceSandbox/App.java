package executorServiceSandbox;

import java.util.concurrent.*;
import java.util.*;
import org.apache.commons.cli.*;

public class App {
    public static void main(String[] args) {
        Options options = new Options();
        options.addOption("t", "type", true, "Executor type (cached or fixed)");
        options.addOption("fs", "fixed-pool-size", true, "Fixed pool size");
        options.addOption("sd", "sleep-delay", true, "Sleep delay in milliseconds");
        options.addOption("sbd", "submission-delay", true, "Submission delay in milliseconds");
        options.addOption("n", "num-tasks", true, "Number of tasks");

        CommandLineParser parser = new DefaultParser();

        try {
            CommandLine cmd = parser.parse(options, args);

            String executorType = cmd.getOptionValue("t", "cached").toLowerCase();
            int taskSleepDelay = Integer.parseInt(cmd.getOptionValue("sd", "2000"));
            int submissionDelay = Integer.parseInt(cmd.getOptionValue("sbd", "1000"));
            int numTasks = Integer.parseInt(cmd.getOptionValue("n", "5"));
            int fixedPoolSize = Integer.parseInt(cmd.getOptionValue("fs", "3"));

        ExecutorService executorService;

        if (executorType.equals("cached")) {
            executorService = Executors.newCachedThreadPool();
            System.out.println("Using newCachedThreadPool.");
        } else if (executorType.equals("fixed")) {
            executorService = Executors.newFixedThreadPool(fixedPoolSize);
            System.out.println("Using newFixedThreadPool of size "+fixedPoolSize);
        } else {
            System.out.println("Invalid executor type. Use 'cached' or 'fixed'.");
            System.exit(1);
            return;
        }

        ConcurrentHashMap<String, List<String>> threadTaskMap = new ConcurrentHashMap<>();

        for (int i = 0; i < numTasks; i++) {
            final int taskId = i + 1;
            try {
                Thread.sleep(submissionDelay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            executorService.submit(() -> {
                String threadName = Thread.currentThread().getName();
                System.out.println("Task " + taskId + " is being executed by Thread: " + threadName);
                try {
                    Thread.sleep(taskSleepDelay);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Task " + taskId + " has completed.");

                threadTaskMap.computeIfAbsent(threadName, k -> new ArrayList<>()).add("Task " + taskId);
            });
        }

        executorService.shutdown();

        try {
            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (Map.Entry<String, List<String>> entry : threadTaskMap.entrySet()) {
            String threadName = entry.getKey();
            List<String> tasks = entry.getValue();
            System.out.println("Thread " + threadName + " ran tasks: " + tasks);
        }
        } catch (ParseException e) {
            System.err.println("Error parsing command line options: " + e.getMessage());
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("ExecutorTypeDemo", options);
        }
    }
}
