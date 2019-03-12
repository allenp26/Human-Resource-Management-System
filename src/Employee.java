public abstract class Employee implements Comparable<Employee>
{

    /**
     * class that contains the department of all employees
     *
     */
    public enum Department {
        FINANCE("Finance"),
        HUMAN_RESOURCES("Human Resources"),
        APPLIANCES("Appliances");

        private String departmentName;

        Department(String departmentName) {
            this.departmentName = departmentName;
        }

        //accessor
        public String getDepartmentName() {
            return departmentName;
        }

        //return the department name in human readable format
        public String toString() {
            return departmentName;
        }
    }

    private String empName;
    private String empId;
    private Department department;
    private double weeklySal;

    /**
     * default constructor that sets an employee to default
     */
    public Employee() {
        empName = "Employee";
        department = Department.FINANCE;
    }

    /**
     * takes in basic employee data and store them
     *
     * @param empName employee's name
     * @param empId employee's id number
     * @param department employee's department
     */
    public Employee(String empName, String empId, Department department) {
        this.empName = empName;
        this.empId = empId;
        this.department = department;
    }

    /**
     * takes in basic employee data and store them, including weekly salary
     *
     * @param empName employee's name
     * @param empId employee's id number
     * @param department employee's department
     * @param weeklySal employee's weekly salary
     */
    public Employee(String empName, String empId, Department department,
                    double weeklySal) {
        this.empName = empName;
        this.empId = empId;
        this.department = department;
        this.weeklySal = weeklySal;
    }

    /**
     * cocpy constructor
     *
     * @param otherEmployee clone employee
     */
    public Employee (Employee otherEmployee) {
        this.empName = otherEmployee.getEmpName();
        this.empId = otherEmployee.getEmpId();
        this.department = otherEmployee.getDepartment();
        this.weeklySal = otherEmployee.getWeeklySal();
    }

    //accessors
    public String getEmpName() {
        return empName;
    }

    public String getEmpId() {
        return empId;
    }

    public Department getDepartment() {
        return department;
    }

    public double getWeeklySal() {
        return weeklySal;
    }

    //mutator
    public void setWeeklySal(double weeklySalary) {
        this.weeklySal = weeklySalary;
    }

    //abstract method for calculating weekly salary
    public abstract double calculateWeeklySal();

    /**
     * basic toString method to return employee's data
     *
     * @return string of employee's data
     */
    public String toString() {
        return "Employee Name: " + empName +"\n"+
                "Employee ID: " + empId +"\n"+
                "Department: " + department +"\n"+
                "Weekly Salary: " + weeklySal;
    }

    /**
     * check if the object is under the employee class
     *
     * @param o object that's passed in
     * @return true if the object is under the employee class
     */
    public boolean equals(Object o) {
        if (o == this)
        {
            return true;
        }
        if (!(o instanceof Employee)) {
            return false;
        }

        Employee e = (Employee) o;

        return o.equals(e);
    }

    /**
     * basic compareTo method to compare the weekly salary
     * between 2 employees
     *
     * @param emp employee object
     * @return 0 if they are the same
     */
    public int compareTo(Employee emp) {
        if (emp != null) {
            if ( emp.getWeeklySal() == this.getWeeklySal()) {
                return 0;
            }
        }

        return 0;
    }

}
