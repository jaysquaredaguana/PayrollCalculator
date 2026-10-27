import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);
        try {
            // create a FileReader object connected to the File
            System.out.print("Enter the name of the file employee file to process: ");
            String readFileName = read.nextLine().trim();
            FileReader filereader = new FileReader(readFileName);

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

            // create a FileWriter
            System.out.print("Enter the name of the payroll file to create: ");
            String fileName = read.nextLine().trim();
            FileWriter fileWriter = new FileWriter(fileName);

            // create a BufferedWriter
            BufferedWriter bufWriter = new BufferedWriter(fileWriter);

            // write to the file
            String text;

            // [
            // { "id": 111, "name" : "Cameron Tay", "grossPay" : 3277.65 },
            // { "id": 222, "name" : "James Tee", "grossPay" : 2150.00 }
            // ]
            if (fileName.endsWith(".json")) {
                int employeeCount = 0;
                bufWriter.write("[\n");
                for (Employee employee : employeeList) {
                    text = String.format("""
                                    { "id": %d, "name" : "%s", "grossPay" : %.2f },
                                    """,
                            employee.getEmployeeId(), employee.getName(), employee.getGrossPay());
                    employeeCount++;
                    if (employeeCount == employeeList.size()) {
                        text = text.replaceAll(".$", "\n]");
                    }
                    bufWriter.write(text);
                }

            } else {
                for (Employee employee : employeeList) {
                    // id|name|gross pay
                    text = String.format("%d|%s|%.2f%n",
                            employee.getEmployeeId(), employee.getName(), employee.getGrossPay());
                    bufWriter.write(text);
                }
            }

            // close the stream and release the resources
            bufReader.close();
            bufWriter.close();
        }
        catch (IOException e) {
            // display stack trace if there was an error
            e.printStackTrace();
        }
    }
}
