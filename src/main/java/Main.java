import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try
        {
            // create a FileReader object connected to the File
            FileReader filereader = new FileReader("sample_data.txt");

            // create a BufferedReader to manage input stream
            BufferedReader bufReader = new BufferedReader(filereader);

            String input;
            String[] data;
            List<Employee> employeeList = new ArrayList<>();

            // employee data variables
            int employeeId;
            String name;
            double hoursWorked, payRate;

            // read until there is no more data
            while ((input = bufReader.readLine()) != null) {
                data = input.split("\\|");
                employeeId = Integer.parseInt(data[0]);
                name = data[1];
                hoursWorked = Double.parseDouble(data[2]);
                payRate = Double.parseDouble(data[3]);
                employeeList.add(new Employee(employeeId, name, hoursWorked, payRate));
            }

            for (Employee employee : employeeList) {
                System.out.printf("%d | %s | $%.2f%n",
                        employee.getEmployeeId(), employee.getName(), employee.getGrossPay());
            }

            // close the stream and release the resources
            bufReader.close();
        }
        catch (IOException e) {
            // display stack trace if there was an error
            e.printStackTrace();
        }
    }
}
