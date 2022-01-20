class DashBoard {

    private val employeeOperation : EmployeeOperation = EmployeeHandler()
    private val dashboardHandler = DashboardHandler()
    fun hrDashboard(currentEmployee : Employee){
        val hrOperation : HROperation = EmployeeHandler()
        val employeeHandler = EmployeeHandler()
        Display.message("Hey ${currentEmployee.firstName}")
        var isContinue='y'
        do{
        when(displayAndGetChoice("1.Register and add new employee\n2.Remove Employee\n3.Search\n4.Request Leave\n5.Delete Leave Request\n6.My Requests\n7.Assigned Requests\n8.MyProfile\n9.Exit",9)) {
            1 -> {
                val firstname : String = getInput("Firstname: ")
                val lastname : String = getInput("Lastname: ")
                var designation = ""
                do {
                    var error = 0
                    try {
                        designation = getInput("Designation: ").uppercase()
                        Designation.valueOf(designation)
                        error=1

                    } catch (e: Exception) {
                        Display.message("Please enter a valid designation")
                    }
                }while(error==0)
                val reportingTo : Employee = employeeOperation.getEmployee(getInput("Reporting To( Employee Id ): ").toInt())
                val password : String = employeeHandler.generatePassword()
                val newEmployee = hrOperation.registerEmployee(firstname, lastname, password, Designation.valueOf(designation), reportingTo)
                Display.message("employee Added!!\nusername: ${newEmployee.employeeId}\npassword: ${newEmployee.password}")
            }
            2 -> {
                val employeeId = getInput("Employee Id: ").toInt()
                val result = hrOperation.removeEmployee(employeeId)
                if(result == 0)
                    Display.message("Employee with Employee Id $employeeId not found")
                else
                    Display.message("Employee Removed Successfully!!")
            }
            3 -> dashboardHandler.search()
            4 -> dashboardHandler.requestLeave(currentEmployee)
            5 -> dashboardHandler.deleteLeaveRequest(currentEmployee)
            6 -> dashboardHandler.requestHistory(currentEmployee)
            7 -> dashboardHandler.assignedRequest(currentEmployee)
            8 -> dashboardHandler.myProfile(currentEmployee)
            9 -> isContinue='n'
        }
        }while(isContinue=='y')
    }
    fun employeeDashboard(currentEmployee: Employee){
        Display.message("Hey ${currentEmployee.firstName}")
        do {
            var isContinue = 'y'
            when (displayAndGetChoice("1.Search\n2.Request Leave\n3.Delete Leave Request\n4.My Requests\n5.Assigned Requests\n6.MyProfile\n7.Exit", 7)) {
                1 -> dashboardHandler.search()
                2 -> dashboardHandler.requestLeave(currentEmployee)
                3 -> dashboardHandler.deleteLeaveRequest(currentEmployee)
                4 -> dashboardHandler.requestHistory(currentEmployee)
                5 -> dashboardHandler.assignedRequest(currentEmployee)
                6 -> dashboardHandler.myProfile(currentEmployee)
                7 -> isContinue='n'
            }
        }while(isContinue=='y')
    }
}