import java.util.List;

public class HrNamesDemo {
    public static void main(String[] args) {
        // TODO: compose filter → map → sorted → toList
        List<String> hrNames = EmployeeData.sample().stream()
                // TODO: .filter(employee -> employee.department().equals("HR"))
                .filter(employee -> employee.department().equals("HR"))
                // TODO: .map(Employee::name)
                .map(Employee::name)
                // TODO: .sorted()
                .sorted()
                // TODO: .toList()
                .toList()
                ;

        System.out.println("HR names: " + hrNames);
    }
}