package dev.neilmason;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Hello world!
 */
public class ProcessPlay {
    public static final int OUTPUT_PAUSE_TIME_MILLIS = 1000;
    public static final int THREAD_TERMINATION_TIMEOUT_MILLIS = 3000;
    private static volatile boolean SHOULD_I_RUN = true;

    public static void main(String[] args) {

        ExecutorService service = Executors.newSingleThreadExecutor();
        Thread hook = new Thread(() -> {
            System.out.println("Shutting down");
            SHOULD_I_RUN = false;
            service.shutdown();
            try {
                service.awaitTermination(THREAD_TERMINATION_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        });
        Runtime.getRuntime().addShutdownHook(hook);

        service.execute(new OutputPrinter());
        System.out.println("Made it here");

    }

    public static class OutputPrinter implements Runnable {
        int outputCount = 1;
        int errorCount = 1;

        @Override
        public void run() {
            while (SHOULD_I_RUN) {
                System.out.println("Output:" + outputCount++);
                try {
                    Thread.sleep(OUTPUT_PAUSE_TIME_MILLIS);
                    System.out.println("Error:" + errorCount++);
                    Thread.sleep(OUTPUT_PAUSE_TIME_MILLIS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

        }
    }


}
