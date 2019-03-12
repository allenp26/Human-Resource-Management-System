public class Salary extends Employee
{
    private double annualSalary;


    /**
     * default constructor to set everything back to default
     */
    Salary() {
        super();
    }

    /**
     * takes in employee data to add it as a salary employee
     *
     * @param empName employee's name
     * @param empId employee's id number
     * @param department employee's department
     * @param annualSalary employee's annual salary
     */
    Salary(String empName, String empId, Department department, double annualSalary) {
        super(empName, empId, department, (annualSalary/52));

        this.annualSalary = annualSalary;
    }

    @Override
    /**
     * calculates the weekly salary for salary employee
     * which divides the annual salary by 52
     */
    public double calculateWeeklySal() {
        return annualSalary/52;
    }


    /**
     * organize the employee data in a nice format
     *
     * @return a string of employee's data
     */
    public String toString() {

        return super.toString() + "\n" +
                "Annual Salary: " + annualSalary +"\n";
    }


    /**
     * check if the employee is under the list of Salary employee
     *
     * @param obj employee object that's passed in
     * @return true if employee is under the list of salary employee vice versa
     */
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Salary)) {
            return false;
        }

        Salary salaryEmp = (Salary) obj;

        return obj.equals(salaryEmp);
    }

}
