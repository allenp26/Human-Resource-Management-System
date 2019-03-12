import java.util.Scanner;
import java.io.*;

public class UserInterface
{
    private static Scanner kb = new Scanner(System.in);
    private static PayrollProcessor processor = new PayrollProcessor();

    public static void main(String args[]) throws IOException
    {
        // define necessary variables here

        // place here the code for the processing requirements
        String usrInput;
        String fName = "data/employee.data";
        processor.loadData(fName);
        do {
            showMenu();
            String usrIn = kb.nextLine();
            usrInput = usrIn.toUpperCase();
            handleUsrSelection(usrInput);
        }while (!usrChooseToCont(usrInput));


        System.out.println ("Thank you for using the Payroll Processing System");
    }

    /**
     * call different methods to handle tasks based on what the
     * user has entered
     */
    private static void handleUsrSelection(String usrChoice) throws IOException
    {
        if (usrChoice.equals("A")) {
            addEmp();
        }
        else if (usrChoice.equals("UH")) {
            weeklyHourEntry();
        }
        else if (usrChoice.equals("UC")) {
            weeklySalesEntry();
        }
        else if (usrChoice.equals("D")) {
            deleteEmp();
        }
        else if (usrChoice.equals("P")) {
            printAllEmpData();
        }
        else if (usrChoice.equals("I")) {
            printEmpData();
        }
        else if (usrChoice.equals("T")) {
            printTopThreeCommissionEmp();
        }
        else if (usrChoice.equals("W")) {
            weeklyUpdater();
        }
        else if (usrChoice.equals("Q")) {
            System.out.println ("Thank you for using the Inventory Processing System");
            System.exit(0);
        }
        else {
            System.out.println("Invalid choice. Please enter a valid option");
        }
    }

    /**
     * helper method to check if the user still wants to continue
     */
    private static boolean usrChooseToCont(String usrInp) {
        if ( usrInp.equals("Q"))
        {
            return true;
        }

        return false;
    }

    //method to handle the addiition of an employee which calls other methods to assist
    private static void addEmp() {
        String empType = requestEmpType();

        if (empType.equals("S")) {
            requestSalaryEmpInfo();
        }
        else if (empType.equals("H")) {
            requestHourlyEmpInfo();
        }
        else if (empType.equals("C")) {
            requestCommissionEmpInfo();
        }
    }

    //request employee type from the user
    private static String requestEmpType() {
        System.out.println("Please Enter Desired Employee Type: ");
        String empType = kb.nextLine();
        empType = empType.toUpperCase();
        boolean empTypeValidator = processor.empTypeValidator(empType);

        if (empTypeValidator) {
            return empType;
        }

        return "";

    }

    //request employee id from the user
    private static String requestEmpId() {
        System.out.println("Please Enter Employee's ID Number: ");
        String empId = kb.nextLine();

        return empId;

    }

    //request "Yes" or "No" for removal of an employee from the payroll list
    private static boolean requestUsrAction() {
        kb.nextLine();
        System.out.println("Enter Yes to Delete This Employee: ");
        String usrChoice = kb.nextLine();

        if (usrChoice.equals("Yes")) {
            return true;
        }
        else {
            return false;
        }
    }

    //check if the employee id that the user entered is valid
    private static boolean empIdValidator(String empId) {
        boolean validEmp = processor.empIdValidator(empId);

        if (validEmp) {
            return true;
        }
        else {
            System.out.println("Invalid Employee ID Number");

            return false;
        }
    }

    //request salary employee data fro the user
    private static void requestSalaryEmpInfo() {
        String empName;
        String empId;
        String empDepartment;
        double annualSalary;

        System.out.println("Please Enter Employee's Name: ");
        empName = kb.nextLine();
        System.out.println("Please Enter Employee's ID: ");
        empId = kb.nextLine();
        System.out.println("Please Enter Employee's Department: ");
        empDepartment = processor.findEmpDepartment(kb.nextLine());
        System.out.println("Please Enter Employee's Annual Salary: ");
        annualSalary = kb.nextDouble();



        //add employee
        processor.addSalaryEmp(empName, empId, empDepartment, annualSalary);
    }

    //request hourly employee data from the user
    private static void requestHourlyEmpInfo() {
        String empName;
        String empId;
        String empDepartment;
        double hourlyPay;
        double hoursWorked;
        int numOfWeeksWorked;

        System.out.println("Please Enter Employee's Name: ");
        empName = kb.nextLine();
        System.out.println("Please Enter Employee's ID: ");
        empId = kb.nextLine();
        System.out.println("Please Enter Employee's Department: ");
        empDepartment = processor.findEmpDepartment(kb.nextLine());
        System.out.println("Please Enter Employee's Hourly Pay: ");
        hourlyPay = kb.nextDouble();
        System.out.println("Please Enter Employee's Hours Worked This Week: ");
        hoursWorked = kb.nextDouble();
        System.out.println("Please Enter Employee's Number of Weeks Worked: ");
        numOfWeeksWorked = kb.nextInt();


        //add employee
        processor.addHourlyEmp(empName, empId, empDepartment,
                hourlyPay, hoursWorked, numOfWeeksWorked);
    }

    //request commission employee data from the user
    private static void requestCommissionEmpInfo() {
        String empName;
        String empId;
        String empDepartment;
        double baseWeeklySal;
        double salesThisWeek;
        double yearTotalSales;
        double commissionRate;
        int numOfWeeksWorked;

        System.out.println("Please Enter Employee's Name: ");
        empName = kb.nextLine();
        System.out.println("Please Enter Employee's ID: ");
        empId = kb.nextLine();
        System.out.println("Please Enter Employee's Department: ");
        empDepartment = processor.findEmpDepartment(kb.nextLine());
        System.out.println("Please Enter Employee's Base Weekly Salary: ");
        baseWeeklySal = kb.nextDouble();
        System.out.println("Please Enter Employee's Sale This Week: ");
        salesThisWeek = kb.nextDouble();
        System.out.println("Please Enter Employee's Total Sale This Year: ");
        yearTotalSales = kb.nextDouble();
        System.out.println("Please Enter Employee's Commission Rate: ");
        commissionRate = kb.nextDouble();
        System.out.println("Please Enter Employee's Number of Weeks Worked: ");
        numOfWeeksWorked = kb.nextInt();



        //add employee
        processor.addCommissionEmp(empName, empId, empDepartment,
                baseWeeklySal, salesThisWeek, yearTotalSales,
                commissionRate, numOfWeeksWorked);
    }

    //method to handle the hours worked by an employee
    private static void weeklyHourEntry() {
        String empId = requestEmpId();

        if (empIdValidator(empId)) {
            if (hourlyEmpValidator(empId)) {
                kb.nextLine();
                System.out.println("Enter The Number of Hours Worked: ");
                processor.setEmpHoursWorked(empId, kb.nextDouble());
            }
        }

    }

    //method to check if an employee is an hourly employee
    private static boolean hourlyEmpValidator(String empId) {
        boolean validHourlyEmp = processor.hourlyEmpValidator(empId);

        if (validHourlyEmp) {
            return true;
        }
        else {
            System.out.println("This Employee Is Not An Hourly Employee.");
            return false;
        }
    }

    //method to check if an employee is an commissioned employee
    private static boolean commissionedEmpValidator(String empId) {
        boolean validCommissionedEmp = processor.commissionEmpValidator(empId);

        if (validCommissionedEmp) {
            return true;
        }
        else {
            System.out.println("This Employee Is Not A Commissioned Employee.");
            return false;
        }
    }

    //method to handle the weekly sales update for an hourly employee
    private static void weeklySalesEntry() {
        String empId = requestEmpId();

        if (empIdValidator(empId)) {
            if (commissionedEmpValidator(empId)) {
                kb.nextLine();
                System.out.println("Enter The Number Of Sales: ");
                processor.setEmpWeeklySales(empId, kb.nextDouble());
            }
        }

    }

    //method to handle the removal of an employee from the payroll list
    private static void deleteEmp() {
        String empId = requestEmpId();

        if (empIdValidator(empId)) {
            for (int i = 0; i < processor.empList.size(); i++) {
                if (empId.equals(processor.empList.get(i).getEmpId())) {
                    System.out.println(processor.empList.get(i).toString());
                }

            }

            boolean usrChoice = requestUsrAction();

            if (usrChoice) {
                processor.removeEmp(empId);
            }

        }

    }

    //method to handle the printing of all employees' data
    private static void printAllEmpData() {
        processor.printAllEmpDetail();
    }

    //method to handle the printing of an individual employee's data
    private static void printEmpData() {
        String empId = requestEmpId();

        if (empIdValidator(empId)) {
            processor.printEmpDetail(empId);
        }
    }

    //method to handle printing top three commission employees' data
    private static void printTopThreeCommissionEmp() {
        processor.printTopCommissionEmp();
    }

    //method to handle weekly update option in the menu
    private static void weeklyUpdater() {
        processor.weeklyUpdate();
    }
    
    //   The Payroll processing menu
    public static void showMenu()
    {
        System.out.println("\nMENU:");
        System.out.println("A - Add a new employee");
        System.out.println("UH - Update hourly employee");
        System.out.println("UC - Update comissioned employee");        
        System.out.println("D - Remove an employee from payroll");     
        System.out.println("P - Print all employees report");        
        System.out.println("I - Print individual employee information");
        System.out.println("T - Print the top comissioned employees by total sales");
        System.out.println();
        System.out.println("W - End of week processing");
        System.out.println();
        System.out.println("Q - Quit the system");
    }

}
