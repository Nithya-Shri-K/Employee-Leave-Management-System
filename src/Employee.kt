import java.time.LocalDate
import java.util.*

data class Employee(var firstName : String, var lastName : String, var password : String, var designation : Designation?, val reportingTo : Employee?, val employeeId : Int = ++employeeIdCreator) {
    var dob : String? = null
    var phoneNumber : String = ""

    companion object{
        var employeeIdCreator = 0
    }
}