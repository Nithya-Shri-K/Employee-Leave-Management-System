import java.time.LocalDate
import kotlin.random.Random

class EmployeeHandler : EmployeeOperation,HROperation {

    override fun registerEmployee(firstname: String, lastname: String, password: String, designation: Designation?, reportingTo: Employee?): Employee {

        val employee = Employee(firstname, lastname, password, designation, reportingTo)
        Database.listOfEmployees.add(employee)
        return employee
    }

    override fun removeEmployee(employeeId: Int) : Int {
        val removeEmployee : List<Employee> = Database.listOfEmployees.filter{it.employeeId == employeeId}
        if(removeEmployee.isNotEmpty()) {
            Database.listOfEmployees.remove(removeEmployee[0])
            return 1
        }
        return 0
    }

    override fun searchByName(employeeName: String): List<Employee> {
        return Database.listOfEmployees.filter { it.firstName.lowercase().contains(employeeName.lowercase()) }
    }

    override fun searchByEmployeeId(employeeId: String): List<Employee> {
        return Database.listOfEmployees.filter { it.employeeId.toString().contains(employeeId) }
    }

    override fun getMyRequestHistory(currentEmployee: Employee): List<Request> {
        val result = Database.listOfRequest.filter { it.employee.employeeId == currentEmployee.employeeId }
        return result.reversed()
    }

    override fun getAssignedRequest(currentEmployee: Employee): List<Request> {
        val result = Database.listOfRequest.filter { it.approver?.employeeId == currentEmployee.employeeId && it.status == Status.PENDING }
        return result.reversed()
    }

    override fun editEmployeeDetails(employee: Employee,editValue : String,choice : Int) {
        when(choice){
            1-> employee.firstName = editValue
            2-> employee.lastName = editValue
            3-> employee.dob = editValue
            4-> employee.phoneNumber = editValue
            5-> employee.password = editValue
        }

    }

   override fun getEmployee(employeeId: Int): Employee {
       val employee =  Database.listOfEmployees.filter{ it.employeeId == employeeId }
       return employee[0]
    }

    fun generatePassword(): String {
        val chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"
        val password = StringBuilder()
        for (i in 0 until 6) {
            val passwordIndex = Random.nextInt(chars.length)
            password.append(chars[passwordIndex])
        }
        return password.toString()
    }

    fun createDefaultEmployees() {
        val employee1 = Employee("nithya", "shri", "nithya", Designation.HR, null)
        Database.listOfEmployees.add(employee1)
        val employee2 = Employee("rosy", "isabella", "rosy", Designation.HR, employee1)
        Database.listOfEmployees.add(employee2)
        val employee3 = Employee("kalimuthu", "T", "kalimuthu", Designation.APPDEVELOPER, employee1)
        Database.listOfEmployees.add(employee3)
        val request1 = Request(employee2, LocalDate.parse("2021-03-25"), LocalDate.parse("2021-03-28"), TypeOfLeave.CASUALLEAVE, "personal")
        Database.listOfRequest.add(request1)
        val request2 = Request(employee3, LocalDate.parse("2021-02-15"), LocalDate.parse("2021-02-19"), TypeOfLeave.SICKLEAVE, "fever")
        Database.listOfRequest.add(request2)
    }
}