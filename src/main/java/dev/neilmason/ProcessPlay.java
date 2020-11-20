package dev.neilmason;

/**
 * Hello world!
 */
public class ProcessPlay {
    public static void main(String[] args) throws InterruptedException {
        int outputCount = 1;
        int errorCount = 1;

        while (true) {
            System.out.println("Output:" + outputCount++);
            Thread.sleep(1000);
            System.out.println("Error:" + errorCount++);
            Thread.sleep(1000);
        }
    }
}
