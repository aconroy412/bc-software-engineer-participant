public class MethodsDemo {
    // Takes an int parameter; returns an int
    public static int square(int n) {
        return n * n;
    }

    // Overload: same method name, different parameter type
    public static double square(double n) {
        return n * n;
    }

    // Takes int parameter
    public static int cube(int n) {
        return n * n * n;
    }

    // override this method with a double
    public static double cube(double n){
        return n * n * n;
    }

    public static void main(String[] args) {
        int intResult = square(4);          // calls the int version
        double doubleResult = square(2.5);  // calls the double version — compiler picks by argument type

        int intCube = cube(5);
        double doubleCube = cube(3.2);

        System.out.println("cube(5) = " + intCube);
        System.out.println("cube(3.2) = " + doubleCube);
    }
}