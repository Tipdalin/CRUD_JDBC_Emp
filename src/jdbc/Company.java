package jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Company {
    Scanner input = new Scanner(System.in);
    List<Employee> employees = new ArrayList<>();

    public void addEmployee() {
        System.out.println("-----------Add Employee -----------");
        System.out.println("Enter name : ");
        String name = input.nextLine();
        System.out.println("Enter salary : ");
        double salary = input.nextDouble();

        String insertEmployee = """
               insert into employees (name, salary) values (?, ?)
               """;


        try(Connection connection = DatabaseUtils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(insertEmployee)) {
            preparedStatement.setString(1, name);
            preparedStatement.setDouble(2, salary);
            preparedStatement.executeUpdate();


        } catch (SQLException e) {

                e.getMessage();
        }
    }

    public void displayAllEmployee() {
        System.out.println("-----------Display All Employee -----------");

        String selectEmployee = """
                select * from employees;
        """;

        try(Connection connection = DatabaseUtils.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(selectEmployee)
            ) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                double salary = resultSet.getDouble("salary");
                System.out.println(id + " " + name + " " + salary);

                employees.add(new Employee(id, name, salary));

            }
        }catch (SQLException e){
            e.getMessage();
        }


    }

    public void deleteEmployee() {
        int id;
        System.out.println("-----------Delete Employee -----------");

        String deleteEmployee = """
                delete from employees where id = ?;
        """;

        try(Connection connection = DatabaseUtils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(deleteEmployee);
        ){
            System.out.print("Input ID : ");
            id = input.nextInt();
            input.nextLine();

            preparedStatement.setInt(1, id);

            int rowsDeleted = preparedStatement.executeUpdate();
            if (rowsDeleted == 0) {
                System.out.println("Failed to delete employee.!");
            }else {
                System.out.println("Employee is deleted!");
            }

            Iterator<Employee> iterator = employees.iterator();
            while(iterator.hasNext()){
                Employee employee = iterator.next();
                if(employee.getId() == id){
                    iterator.remove();
                    break;
                }
            }

        }catch (SQLException e){
            e.getMessage();
        }
    }
}
