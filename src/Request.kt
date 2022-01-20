import java.time.LocalDate
import java.time.Period
import java.util.*
import java.time.temporal.ChronoUnit
import javax.xml.datatype.DatatypeConstants.DAYS


data class Request(val employee : Employee, val from : LocalDate, val to : LocalDate, val typeOfLeave : TypeOfLeave, val reason : String,val requestId : Int = ++requestIdCreator) {

    var status : Status? = Status.PENDING
    val noOfDays = ChronoUnit.DAYS.between(this.from, this.to) + 1
    val approver : Employee? = this.employee.reportingTo

    companion object{
       var requestIdCreator = 0
    }

}