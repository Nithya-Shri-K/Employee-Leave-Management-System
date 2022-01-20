class Authentication {
    fun login(username: Int, enteredPassword: String): Int {
        var employee = Database.listOfEmployees.filter { it.employeeId == username }
        if(employee.isNotEmpty()){
            if(employee[0].password == enteredPassword)
                return 1
        }
        return 0
    }
}