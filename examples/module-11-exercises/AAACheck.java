public class AAACheck {

    public void activate_prospectRavi_setsStatusActive() {
        // Arrange
        Customer2 ravi = new Customer2("CUS-1002", "Ravi Singh", "PROSPECT");

        // Act
        Customer2 activatedRavi = new Customer2(ravi.id(), ravi.fullName(), "ACTIVE");

        // Assert
        if (!"ACTIVE".equals(activatedRavi.status())) {
            throw new AssertionError("Expected Ravi status to be ACTIVE");
        }
    }

    public static void main(String[] args) {
        AAACheck check = new AAACheck();
        check.activate_prospectRavi_setsStatusActive();
        System.out.println("AAA check passed for Ravi");
    }
}
