public class StringBuilderComparison {
    private static final int ITERATIONS = 50_000;

    static String withString() {
        String result = "";
        for (int i = 0; i < ITERATIONS; i++) {
            // TODO: result += "x";  (each update creates another String)
            result += "x";
        }
        return result;
    }

    static String withBuilder() {
        // Initial capacity avoids repeated buffer growth.
        // TODO: StringBuilder result = new StringBuilder(ITERATIONS);
        StringBuilder result = new StringBuilder(ITERATIONS);
        for (int i = 0; i < ITERATIONS; i++) {
            // TODO: result.append('x');
            result.append('x');
        }
        // TODO: return result.toString();
        return result.toString();
    }

    public static void main(String[] args) {
        // TODO: time withString() with System.nanoTime()
        long wsts = System.nanoTime();
        String ws = withString();
        long wstf = System.nanoTime();
        // difference
        long wst = wstf - wsts;
        // TODO: time withBuilder() with System.nanoTime()
        
        long wbts = System.nanoTime();
        String wb = withBuilder();
        long wbtf = System.nanoTime();
        long wbt = wbtf - wbts;
        // TODO: printf both lengths and ms (stringNanos / 1_000_000.0)
        System.out.printf("Length with String: %d Time: %f ms%n", ws.length(), (wst / 1_000_000.0));
        System.out.printf("Length with Builder: %d Time: %f ms%n", wb.length(), (wbt / 1_000_000.0));


    }
}