public class PerformanceTest {

    private static class SampleObject {
        private final int value;
        private final byte[] data = new byte[64];

        SampleObject(int value) {
            this.value = value;
        }
    }

    public static void main(String[] args) {
        System.out.println("===== Performance Measurement =====");
        MemoryMonitor.printMemoryReport("Start");

        int[] objectCounts = {10, 100, 1_000, 100_000, 1_000_000};

        System.out.println();
        System.out.printf("%-12s %-14s %-18s%n", "Objects", "Used Memory", "Execution Time");
        System.out.println("--------------------------------------------------");

        for (int count : objectCounts) {
            runAllocationTest(count);
        }

        System.out.println();
        System.out.println("Additional measurements:");
        measureLoopExecution();
        measureArrayAllocation();
        measureLargeByteArray();
    }

    private static void runAllocationTest(int count) {
        MemoryMonitor.triggerGarbageCollection();
        long memoryBefore = MemoryMonitor.getUsedMemoryBytes();
        long start = System.nanoTime();

        // TODO: allocate SampleObject[count], fill each slot

        SampleObject[] sample = new SampleObject[count];

        int i;
        for (i = 0; i < count; i++) {
            sample[i] = new SampleObject(i);
        }
        double elapsedTime = (System.nanoTime() - start) / 1_000_000.0;
        long memoryAfter = MemoryMonitor.getUsedMemoryBytes();
        long memoryUsed = memoryAfter - memoryBefore;

        System.out.printf("%-15d | %-15.2f | %-20d%n", count, elapsedTime, memoryUsed);
        // TODO: measure elapsed ms + memoryUsed; printf row; null array + GC

        sample = null;
        System.gc();
        // throw new UnsupportedOperationException("TODO");
    }

    private static void measureLoopExecution() {
        // TODO: loop 10_000_000 iterations summing i into sum; print elapsed ms

        long start = System.nanoTime();
        int sum = 0;
        for (int i = 0; i < 10_000_000; i++) {
            sum += i;
        }
        System.out.println("Time Elapsed Loop: " + (System.nanoTime() - start) / 1_000_000.0);
        // throw new UnsupportedOperationException("TODO");
    }

    private static void measureArrayAllocation() {
        // TODO: allocate int[1_000_000], fill with i, print elapsed ms
        long start = System.nanoTime();
        int[] sum = new int[1_000_000];
        for (int i = 0; i < 1_000_000; i++) {
            sum[i] = i;
        }
        System.out.println("Time Elapsed Allocation: " + (System.nanoTime() - start) / 1_000_000.0);
        // throw new UnsupportedOperationException("TODO");
    }

    private static void measureLargeByteArray() {
        MemoryMonitor.printMemoryReport("Before Large byte[]");
        // TODO: allocate 10 MB byte[]; print After report; null + GC; print After Releasing

        byte[] b = new byte[10_000_000];

        MemoryMonitor.printMemoryReport("After Large byte[]");

        b = null;
        System.gc();

        MemoryMonitor.printMemoryReport("After GC");
        // throw new UnsupportedOperationException("TODO");
    }
}
