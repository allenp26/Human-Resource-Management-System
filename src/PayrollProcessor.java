import java.util.*;
import java.io.*;


public class PayrollProcessor
{
    List <Employee> empList = new ArrayList<>();

    /**
     * load in the employee data from the file, which
     * then stores them into the List.
     * Differentiate by the type of employee and add them to
     * different classes
     *
     * @param fileName file path that contains the data
     * @throws IOException if file is not found
     */
    public void loadData(String fileName) throws IOException {
        Scanner inFile = new Scanner(new File(fileName));

        while (inFile.hasNextLine()) {
            String emp = inFile.nextLine();
            String[] empData = emp.split(", ");
            String empDepartment = findEmpDepartment(empData[2]);


            if (empData[3].equals("S")) {
                Employee salary = new Salary(empData[0], empData[1],
                        Employee.Department.valueOf(empDepartment), Double.parseDouble(empData[4]));
                empList.add(salary);
            }
            else if (empData[3].equals("H")) {
                Employee hourly = new Hourly(empData[0], empData[1],
                        Employee.Department.valueOf(empDepartment), Double.parseDouble(empData[4]),
                        Double.parseDouble(empData[5]), Integer.parseInt(empData[6]));
                empList.add(hourly);
            }

            else if (empData[3].equals("C")) {
                Employee commission = new Commission(empData[0], empData[1],
                        Employee.Department.valueOf(empDepartment), Double.parseDouble(empData[4]),
                        Double.parseDouble(empData[5]), Double.parseDouble(empData[6]),
                        Double.parseDouble(empData[7]), Integer.parseInt(empData[8]));
                empList.add(commission);
            }



        }

    }

    /**
     * find the matching enum variable for the employee's department
     *
     * @param empDepartment a string of employee's department
     * @return enum variable name
     */
    public String findEmpDepartment(String empDepartment) {

        if (empDepartment.equals(Employee.Department.FINANCE.getDepartmentName())) {
            return "FINANCE";
        }
        else if (empDepartment.equals(Employee.Department.APPLIANCES.getDepartmentName())) {
            return "APPLIANCES";
        }
        else if (empDepartment.equals(Employee.Department.HUMAN_RESOURCES.getDepartmentName())) {
            return "HUMAN_RESOURCES";
        }

        return "";
    }

    /**
     * add a new salary employee which takes in all the
     * necessary informations for a salary employee
     *
     * @param empName employee's name
     * @param empId employee's id number
     * @param empDepartment employee's department
     * @param annualSal employee's annual salary
     */
    public void addSalaryEmp(String empName, String empId, String empDepartment, double annualSal) {
        Employee newSalaryEmp = new Salary(empName, empId,
                Employee.Department.valueOf(empDepartment), annualSal);
        empList.add(newSalaryEmp);
    }

    /**
     * add a new hourly employee which takes in all the
     * necessary information for an hourly employee
     *
     * @param empName employee's name
     * @param empId employee's id number
     * @param empDepartment employee's department
     * @param hourlyPay employee's hourly pay rate
     * @param hoursWorked employee's total hours worked this week
     * @param numOfWeeksWorked employee's total number of weeks worked
     */
    public void addHourlyEmp(String empName, String empId, String empDepartment,
                             double hourlyPay, double hoursWorked, int numOfWeeksWorked) {
        Employee newHourlyEmp = new Hourly(empName, empId, Employee.Department.valueOf(empDepartment),
                hourlyPay, hoursWorked, numOfWeeksWorked);
        empList.add(newHourlyEmp);
    }

    /**
     * add a new commission employee which takes in all the
     * necessary information of a commission employee
     *
     * @param empName employee's name
     * @param empId employee's id number
     * @param empDepartment employee's department
     * @param baseWeeklySal employee's base weekly salary
     * @param salesThisWeeek employee's sales this week
     * @param yearTotalSales employee's year total sales
     * @param commissionRate employee's commission rate
     * @param numOfWeeksWorked employee's total number of weeks worked
     */
    public void addCommissionEmp(String empName, String empId, String empDepartment,
                                 double baseWeeklySal, double salesThisWeeek, double yearTotalSales,
                                 double commissionRate, int numOfWeeksWorked) {
        Employee newCommissionEmp = new Commission(empName, empId,
                Employee.Department.valueOf(empDepartment), baseWeeklySal, salesThisWeeek,
                yearTotalSales, commissionRate, numOfWeeksWorked);
        empList.add(newCommissionEmp);
    }

    /**
     * method to validate if the employee type that the user has
     * entered is valid
     *
     * @param empType employee type that the user has entered
     * @return true if it is H,S, or C
     */
    public boolean empTypeValidator(String empType) {

        if (empType.equals("S")) {
            return true;
        }
        else if (empType.equals("H")) {
            return true;
        }
        else if (empType.equals("C")) {
            return true;
        }
        else {
            System.out.println("Invalid Employee Type");
            return false;
        }

    }

    /**
     * method to validate if the employee id that the user has
     * entered is in the payroll list
     *
     * @param empId employee's id that the user has entered
     * @return true if it is under the list
     */
    public boolean empIdValidator(String empId) {

        for (int i = 0; i < empList.size(); i++) {
            if (empId.equals(empList.get(i).getEmpId())) {
                return true;
            }
        }

        return false;
    }

    /**
     * method to check if the employee is an hourly employee
     *
     * @param empId employee id that the user has entered
     * @return true if it is an hourly employee
     */
    public boolean hourlyEmpValidator(String empId) {

        for (int i = 0; i < empList.size(); i++) {
            if (empId.equals(empList.get(i).getEmpId())) {
                if (empList.get(i) instanceof Hourly) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * method to check if the employee is a commissioned employee
     *
     *
     * @param empId employee id that the user has entered
     * @return true if it is an commissioned employee
     */
    public boolean commissionEmpValidator(String empId) {

        for (int i = 0; i < empList.size(); i++) {
            if (empId.equals(empList.get(i). getEmpId())) {
                if (empList.get(i) instanceof Commission) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     *  method to remove an emploiyee which takes in
     *  the employee's id and then remove it from the
     *  payroll list
     *
     * @param empId employee id that the user has entered
     */
    public void removeEmp(String empId) {
        for (int i = 0; i < empList.size(); i++) {
            if (empId.equals(empList.get(i).getEmpId())) {
                empList.remove(i);
            }
        }

    }

    /**
     * prints an individual employee's data
     *
     * @param empId employee id that the user has entered
     */
    public void printEmpDetail(String empId) {

        for (int i = 0; i < empList.size(); i++) {
            if (empId.equals(empList.get(i).getEmpId())) {
                System.out.println(empList.get(i).toString());
            }
        }

    }

    /**
     * prints all employee's data in a nice format
     */
    public void printAllEmpDetail() {

        for (int i = 0; i < empList.size(); i++) {
            System.out.println(empList.get(i).toString());
        }

    }

    /**
     * set the hours worked this week for an appropriate employee
     *
     * @param empId employee's id number
     * @param empHour employee's hours worked from the user
     */
    public void setEmpHoursWorked(String empId, double empHour) {
        for (int i = 0; i < empList.size(); i++) {
            if (empId.equals(empList.get(i).getEmpId())) {
                ((Hourly) empList.get(i)).setHoursWorked(empHour);
                empList.get(i).setWeeklySal(empList.get(i).calculateWeeklySal());
            }
        }

    }

    /**
     * set the weekly sales entered by the user to an employee
     * if the employee is a commissioned employee
     *
     * @param empId employee's id entered by the user
     * @param empSales employee's sales entered by the user
     */
    public void setEmpWeeklySales(String empId, double empSales) {
        for (int i = 0; i < empList.size(); i++) {
            if (empId.equals(empList.get(i).getEmpId())) {
                ((Commission) empList.get(i)).setSalesThisWeek(empSales);
                empList.get(i).setWeeklySal(empList.get(i).calculateWeeklySal());
            }
        }
    }

    /**
     * print the top 3 commission employees' data
     * based on their year total sales
     */
    public void printTopCommissionEmp() {
        Collections.sort(empList);

        int topThreeCounter = 0;
        for (int i = 0 ; i < empList.size(); i++) {
            if (empList.get(i) instanceof Commission) {
                if (topThreeCounter < 3) {
                    System.out.println(empList.get(i));
                }
                topThreeCounter++;
            }

        }
    }

    /**
     * weekly update method which resets the hours that
     * an employee has worked and the sales this week. Add sales this week
     * to total sales, add another week to the number of weeks an employee has
     * worked
     */
    public void weeklyUpdate() {
        for (int i = 0; i <empList.size(); i++) {
            if (empList.get(i) instanceof Hourly) {
                ((Hourly) empList).setNumOfWeeksWorked(
                        ((Hourly) empList.get(i)).getNumOfWeeksWorked()+1);
                ((Hourly) empList).setHoursWorked(0);
            }
            if (empList.get(i) instanceof Commission) {
                ((Commission)empList).setNumOfWeeksWorked(
                        ((Commission) empList.get(i)).getNumOfWeeksWorked()+1);
                ((Commission) empList).setYearTotalSales(
                        ((Commission) empList).getYearTotalSales()+
                                ((Commission) empList).getSalesThisWeek());
                ((Commission) empList).setSalesThisWeek(0);
            }
        }
    }
}
