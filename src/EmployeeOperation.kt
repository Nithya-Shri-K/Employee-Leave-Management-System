interface EmployeeOperation {
    fun searchByEmployeeId(employeeId: String) : List<Employee>
    fun searchByName(employeeName : String) : List<Employee>
    fun getAssignedRequest(currentEmployee: Employee) : List<Request>
    fun getMyRequestHistory(currentEmployee : Employee) : List<Request>
    fun editEmployeeDetails(employee : Employee,editValue : String ,choice : Int)
    fun getEmployee(employeeId: Int) : Employee
}