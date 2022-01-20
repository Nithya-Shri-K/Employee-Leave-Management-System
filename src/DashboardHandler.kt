import java.lang.IndexOutOfBoundsException

class DashboardHandler {

    private var employeeOperation : EmployeeOperation = EmployeeHandler()
    private var requestHandler = RequestHandler()

    fun search(){
        when(displayAndGetChoice("1. Search by Name\n2. Search by Employee Id",2)){
            1-> {
                val employeeName : String = getInput("Employee Name: ")
                val result : List<Employee> = employeeOperation.searchByName(employeeName)
                if(result.isNotEmpty()){
                    Display.displayAllEmployeeDetails(result)
                }
                else{
                    Display.message("Employee not found!!")
                }

            }
            2-> {
                val employeeId : String = getInput("Employee Id")
                val result = employeeOperation.searchByEmployeeId(employeeId)
                if(result.isNotEmpty())
                    Display.displayAllEmployeeDetails(result)
                else
                    Display.message("Employee not found!!")
            }
        }

    }

    fun requestLeave(currentEmployee: Employee){
        val fromDate = getDateInput("From Date(DD-MM-YYYY): ")
        val toDate = getDateInput("To Date (DD-MM-YYYY): ")
        var typeOfLeave = ""
        do {
            var error =0
            try {
                typeOfLeave = getInput("Type Of Leave( Sickleave/Casualleave ): ").uppercase()
                TypeOfLeave.valueOf(typeOfLeave)
                error = 1
            } catch (e: Exception) {
                Display.message("Please enter a valid Leave Type")
            }
        }while(error==0)
        val reason = getInput("Leave Reason: ")
        requestHandler.createRequest(currentEmployee, fromDate, toDate, TypeOfLeave.valueOf(typeOfLeave),reason)
        Display.message("Leave Applied Successfully!!")
    }

    fun requestHistory(currentEmployee: Employee){
        val myRequest = employeeOperation.getMyRequestHistory(currentEmployee)
        if(myRequest.isNotEmpty())
            Display.displayRequestDetails(myRequest)
        else
            Display.message("No Requests Found!!")
    }

    fun assignedRequest(currentEmployee: Employee){
        val assignedRequests = employeeOperation.getAssignedRequest(currentEmployee)
        if(assignedRequests.isNotEmpty()) {
            Display.displayRequestDetails(assignedRequests)
            val isEdit = getInput("do you want to edit leave request status?(y/n)")[0]
            if (isEdit == 'y') {
                var requestIndex = 0
                do {
                    var error = 0
                    try {
                        requestIndex = getInput("Index: ").toInt()
                        assignedRequests[requestIndex - 1]
                        error=1
                    } catch (e: IndexOutOfBoundsException) {
                        Display.message("Please enter a valid input")
                    }
                }while(error == 0)
                var status = ""
                do {
                    var error = 0
                    try {
                        status = getInput("Approved/Rejected").uppercase()
                        Status.valueOf(status)
                        error = 1
                    } catch (e: Exception) {
                        println("Please enter a valid Status")
                    }
                }while(error==0)
                requestHandler.changeStatus(assignedRequests[requestIndex-1], Status.valueOf(status))
            }
        }
        else{
            Display.message("No Request Assigned")
        }
    }

    fun myProfile(currentEmployee: Employee){
        Display.displayEmployeeProfile(currentEmployee)
        val isEdit = getInput("edit---(Y / N)")[0]
        if(isEdit=='y') {
            editProfile(currentEmployee)
            Display.displayEmployeeProfile(currentEmployee)
        }
     }

    private fun editProfile(currentEmployee: Employee){
        var isContinue = 'y'
        do {
            val choice = displayAndGetChoice(
                "1.Edit Firstname\n2.Edit Lastname\n3.Edit Dob\n4.Edit Phone Number\n5.Edit Password\n6.Exit",
                6
            )
            when (choice) {
                1 -> {
                    val firstname = getInput("Firstname: ")
                    employeeOperation.editEmployeeDetails(currentEmployee,firstname,choice)
                }
                2 -> {
                    val lastname = getInput("Lastname: ")
                    employeeOperation.editEmployeeDetails(currentEmployee,lastname,choice)
                }
                3 -> {
                    val dob =  getInput("DOB: ")
                    employeeOperation.editEmployeeDetails(currentEmployee,dob,choice)
                }
                4 -> {
                    val phoneNumber = getInput("PhoneNumber: ")
                    employeeOperation.editEmployeeDetails(currentEmployee,phoneNumber,choice)
                }
                5 -> {
                    val password = getInput("Password: ")
                    employeeOperation.editEmployeeDetails(currentEmployee,password,choice)
                }
                6 -> isContinue = 'n'
            }
        } while (isContinue == 'y')
    }

    fun deleteLeaveRequest(currentEmployee: Employee){
        val requests = employeeOperation.getMyRequestHistory(currentEmployee).filter { it.status == Status.PENDING }
        if(requests.isNotEmpty()) {
            Display.displayRequestDetails(requests)
            do {
                var error = 0
                try {
                    val index = getInput("Index: ").toInt()
                    requestHandler.deleteRequest(requests[index - 1])
                    Display.message("Request Deleted!!")
                    error= 1
                } catch (e: IndexOutOfBoundsException) {
                    Display.message("Please enter a valid index")
                }
            }while(error==0)
        }
        else
            Display.message("There is no request with status pending!!")
    }
}