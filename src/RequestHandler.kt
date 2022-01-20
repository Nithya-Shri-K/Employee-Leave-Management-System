import java.time.LocalDate

class RequestHandler {

    fun createRequest(employee : Employee, fromDate : LocalDate, toDate : LocalDate,typeOfLeave : TypeOfLeave, reason : String){
          var request = Request(employee, fromDate, toDate, typeOfLeave, reason)
        Database.listOfRequest.add(request)
    }
    fun deleteRequest(request : Request){
        Database.listOfRequest.remove(request)
    }
    fun changeStatus(request : Request, status: Status?){
        request.status = status
    }

}