interface HROperation {
    fun registerEmployee(firstname : String, lastname : String, password : String, designation : Designation?, reportingTo : Employee? ) : Employee
    fun removeEmployee(employeeId : Int) : Int
}