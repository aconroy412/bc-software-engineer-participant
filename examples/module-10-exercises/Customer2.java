public record Customer2(String id, String fullName, String status) {
    // Correlation IDs belong in logs/headers later, not as a Customer field.

    public static void main(String[] args) {
        Customer2 customer = new Customer2("CUS-1001", "Amina Khan", "ACTIVE");
        System.out.println(customer);
    }
}
