public record Customer(String id, String fullName, String status) {
    // correlation note: lab-request-001

    public static void main(String[] args) {
        Customer amina = new Customer("CUS-1001", "Amina Khan", "ACTIVE");
        Customer ravi = new Customer("CUS-1002", "Ravi Singh", "PROSPECT");

        System.out.println(amina);
        System.out.println(ravi);
    }
}
