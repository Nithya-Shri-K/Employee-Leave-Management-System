object Display {

    fun message(prompt : String){
        println(prompt)
    }
    fun displayAllEmployeeDetails(employeeList : List<Employee>){
        for(employee in employeeList){
            println("Id: ${employee.employeeId}, Firstname: ${employee.firstName}, Lastname: ${employee.lastName}, Dob: ${employee.dob ?: ""}, Phone Number: ${employee.phoneNumber},Designation: ${employee.designation},Reporting To: ${employee.reportingTo?.firstName}")
        }
    }
    fun displayEmployeeProfile(employee : Employee){
        println("Id: ${employee.employeeId}\nFirstname: ${employee.firstName}\nLastname: ${employee.lastName}\nDob: ${employee.dob ?: ""}\nPhone Number: ${employee.phoneNumber}\nDesignation: ${employee.designation}\nReporting To: ${employee.reportingTo?.firstName}\npassword: ${employee.password}")
    }

    fun displayRequestDetails(requests : List<Request>){
        var index = 1
        for (request in requests){
            println("${index++}) Request ID: ${request.requestId}, Requested Employee: ${request.employee.firstName}, From Date: ${request.from}, To Date: ${request.to}, No of days: ${request.noOfDays}, Leave type: ${request.typeOfLeave}, Reason: ${request.reason}, status: ${request.status}, Approver: ${request.approver?.firstName ?: "NA"}")
        }
    }
}