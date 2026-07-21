import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("First number: ");
        double a = Double.parseDouble(scanner.nextLine());

        System.out.print("Second number: ");
        double b = Double.parseDouble(scanner.nextLine());

        // added a choice feature
        System.out.print("What would you like to do? (1:add  2:subtract 3:multiply 4:divide) : ");
        int choice = Integer.parseInt(scanner.nextLine());

        switch(choice) {
            case 1:
                System.out.printf("Sum: %.2f%n", a + b);          // addition
                break;
            case 2:
                System.out.printf("Difference: %.2f%n", a - b);   // subtraction
                break;
            case 3:
                System.out.printf("Product: %.2f%n", a * b);      // multiplication
                break;
            case 4:
                System.out.printf("Quotient: %.2f%n", a / b);     // division (double ÷ double)
                break;
            default:
                System.out.println("Invalid number, exiting");
                break;

        }
        scanner.close();
    }
}