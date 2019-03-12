import java.net.CookieHandler;

public class Commission extends Employee
{
    private double baseWeeklySal;
    private int numOfWeeksWorked;
    private double salesThisWeek;
    private double commissionRate;
    private double yearTotalSales;
	
	/*
	* Default Constructor which sets everything to null or zero.
	*/
    Commission() {
        super();
    }
	
	
	 /**
	 * Specific constructor to take in commission employee informations
	 *
	 *@param empName employee Name
	 *@param empId employee id number
     *@param department employee department
     *@param baseWeeklySal employee's base weekly salary
      *@param salesThisWeek employee's sales for this week
      *@param yearTotalSales employee's total sale for this year
      *@param commissionRate employee's commission rate
      *@param numOfWeeksWorked employee's total number of weeks worked
     */
    Commission(String empName, String empId, Department department, double baseWeeklySal,
               double salesThisWeek, double yearTotalSales,  double commissionRate, int numOfWeeksWorked) {
        super(empName, empId, department, (baseWeeklySal + (salesThisWeek*commissionRate)));

        this.baseWeeklySal = baseWeeklySal;
        this.numOfWeeksWorked = numOfWeeksWorked;
        this.salesThisWeek = salesThisWeek;
        this.commissionRate = commissionRate;
        this.yearTotalSales = yearTotalSales;
    }
    //accessors
    public double getBaseWeeklySal() {
        return baseWeeklySal;
    }

    public int getNumOfWeeksWorked() {
        return numOfWeeksWorked;
    }

    public double getSalesThisWeek() {
        return salesThisWeek;
    }

    public double getCommissionRate() {
        return commissionRate;
    }

    public double getYearTotalSales() {
        return yearTotalSales;
    }

    //mutators
    public void setSalesThisWeek(double salesThisWeek) {
        this.salesThisWeek = salesThisWeek;
    }

    public void setNumOfWeeksWorked(int numOfWeeksWorked) {
        this.numOfWeeksWorked = numOfWeeksWorked;
    }

    public void setYearTotalSales(double yearTotalSales) {
        this.yearTotalSales = yearTotalSales;
    }

    @Override
    public double getWeeklySal() {
        return calculateWeeklySal();
    }

    /**
     * calculates the weekly salary for commissioned employees which is different
     * from two other type of employees
     *
     *
     * @return employee's detail in a nice format
     */
    public double calculateWeeklySal() {
        return (baseWeeklySal+ (salesThisWeek*commissionRate));
    }

    public String toString() {
        return super.toString() +"\n"+
                "Base Weekly Salary: " + baseWeeklySal +"\n"+
                "Number of Weeks Worked: " + numOfWeeksWorked +"\n"+
                "Sales This Week: " + salesThisWeek +"\n"+
                "Commission Rate: " + commissionRate +"\n"+
                "Total Sales This Year: " +yearTotalSales +"\n";
    }

    /**
     * overwrites the .equals method in the super class
     * which checks if the obj is in the Commission class
     *
     * @param obj employee data to check
     * @return true if they are the same vice versa
     */
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Commission)) {
            return false;
        }

        Commission commissionEmp = (Commission) obj;

        return obj.equals(commissionEmp);
    }


    /**
     * compares the commissioned employee's year total sales
     * and return the differences for sorting
     *
     * @param commission an commission employee
     * @return diff difference between the employee's year total sales
     */
    public int compareTo(Employee commission) {
        if (commission instanceof Commission) {
            double diff = ((Commission) commission).getYearTotalSales() - this.getYearTotalSales();

            return (int) diff;
        }
        else {
            return super.compareTo(commission);
        }
    }

}
