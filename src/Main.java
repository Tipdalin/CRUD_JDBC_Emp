import jdbc.Company;
import jdbc.DatabaseUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Main {
    static Company company = new Company();
    static void main() {
        //Create table
//        String createTable = """
//                    create table employees(
//                    id serial primary key,
//                    name varchar(200),
//                    salary numeric(12,2)
//                )
//                """;
//        try (Connection connection = DatabaseUtils.getConnection();
//             Statement statement =
//                     connection.createStatement();
//        ) {
//            statement.execute(createTable);
//        }
//        catch (SQLException e) {
//            e.getMessage();
//        }
        Scanner input = new Scanner(System.in);
        String option;
        while (true) {
            System.out.println("""
                    1.Add Employee
                    2.Update Employee
                    3.Show Employee
                    4.Delete Employee
                    5.Exit
                    """);
            System.out.print("Enter your options: ");
            option = input.nextLine();

            switch (option) {
                case "1": {
                    company.addEmployee();
                }
                break;
                case "2": {


                }
                break;
                case "3": {
                    company.displayAllEmployee();
                }
                break;
                case "4": {
                    company.deleteEmployee();
                }
                break;
                case "5": {
                    System.out.println("Do you want to exit the program?");
                    input.nextLine();
                    if (input.nextLine().equals("yes")) {
                        System.out.println("Thank you for using our application!");
                        System.exit(0);
                    }else {
                        System.out.println("Sorry, please try again!");
                    }
                }
                break;
                default: {
                    System.out.println("Invalid option");
                }
            }



    }
}
}
