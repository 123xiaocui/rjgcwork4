public class ThreadManager {

    public static void stopAllNonMainThreads() {
        Thread[] threads = new Thread[Thread.activeCount()];
        int count = Thread.enumerate(threads);

        for (int i = 0; i < count; i++) {
            Thread thread = threads[i];
            if (thread != null && !thread.isInterrupted() && thread != Thread.currentThread()) {
                thread.interrupt();
                System.out.println("Interrupted thread: " + thread.getName());
            }
        }
    }


}
