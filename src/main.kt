import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

fun main(args : Array<String>){

    val dashboard = DashBoard()
    val employeeOperation = EmployeeHandler()
    val auth = Authentication()
    employeeOperation.createDefaultEmployees()
    Display.message("Welcome")
    do {
        val isContinue ='y'
        Display.message("Login")
        val username : Int = getInput("Username: ").toInt()
        val password : String = getInput("Password")
        if (auth.login(username, password) == 1) {
            val currentEmployee : Employee = employeeOperation.getEmployee(username)
            when (currentEmployee.designation) {
                Designation.HR ->dashboard.hrDashboard(currentEmployee)
                else -> dashboard.employeeDashboard(currentEmployee)
            }
        }
        else {
            Display.message("Username or Password Incorrect!")
        }
    }while(isContinue == 'y')
}

fun displayAndGetChoice(prompt : String, maxLimit : Int) : Int {
    var error = 1
    var choice = 0
    do {
        try {
            choice = getInput(prompt).toInt()
            validateChoice(choice, maxLimit)
            error = 0
        } catch (e: InvalidChoiceException) {
            println(e.message)
        }
    }while(error == 1)
    return choice
}

fun validateChoice(choice : Int, maxLimit : Int ){
    if(choice > maxLimit){
        throw InvalidChoiceException("enter a valid choice from the menu")
    }
}

fun getInput(prompt: String): String {
    Display.message(prompt)
    return readLine()!!
}
fun getDateInput(prompt : String) : LocalDate{
    var formattedDate = LocalDate.parse("2000-01-01")
    do {
        var error =0
        val date: String = getInput(prompt)
        val fromDatePattern = DateTimeFormatter.ofPattern("d-MM-yyyy")
        try {
            formattedDate = LocalDate.parse(date, fromDatePattern)
            error=1
        } catch (e: DateTimeParseException) {
            Display.message("Please enter a valid date!")
        }
    }while(error==0)
    return formattedDate
}


