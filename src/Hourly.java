public class Hourly extends Employee
{
    private double hourlyPay;
    private double hoursWorked;
    private int numOfWeeksWorked;


    /**
     * default constructor to reset things to default,
     * which calls the default constructor in the super class
     */
    Hourly() {
        super();
    }

    Hourly(String empName, String empId, Department department, double hourlyPay,
           double hoursWorked, int numOfWeeksWorked) {
        super(empName, empId, department, (hourlyPay*hoursWorked) );

        this.hourlyPay = hourlyPay;
        this.hoursWorked = hoursWorked;
        this.numOfWeeksWorked = numOfWeeksWorked;
    }

    //accessors
    public int getNumOfWeeksWorked() {
        return numOfWeeksWorked;
    }

    //mutators
    public void setHoursWorked(double hoursWorked) {
        this.hoursWorked = hoursWorked;
    }

    public void setNumOfWeeksWorked(int numOfWeeksWorked) {
        this.numOfWeeksWorked = numOfWeeksWorked;
    }

    @Override
    /**
     * calculates the weekly salary for an hourly employee,
     * multiply the hourly pay rate by the hours the employee has
     * worked this week
     *
     * @return the weekly salary
     */
    public double calculateWeeklySal() {
        return hourlyPay*hoursWorked;
    }

    public String toString() {
        return super.toString() +"\n"+
                "Hourly Pay: " + hourlyPay +"\n"+
                "Hours Worked: " + hoursWorked +"\n"+
                "Number of Weeks Worked: " + numOfWeeksWorked +"\n";
    }


    /**
     * Checking if the employee that's passed in is
     * under the list of an hourly employee
     *
     * @param obj employee that's passed in
     * @return true if this employee is under the list vice versa
     */
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Hourly)) {
            return false;
        }

        Hourly hourlyEmp = (Hourly) obj;

        return obj.equals(hourlyEmp);
    }

}
