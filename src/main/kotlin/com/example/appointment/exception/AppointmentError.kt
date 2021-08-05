package com.example.appointment.exception

import com.fasterxml.jackson.annotation.JsonFormat
import org.springframework.http.HttpStatus
import java.util.*

class AppointmentError(
    httpStatus: HttpStatus,
    val message: String,
    var stackTrace: String? = null
) {

    val code: Int
    var status: String = ""

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy hh:mm:ss")
    val timestamp: Date = Date()

    init {
        code = httpStatus.value()
        status = httpStatus.name
    }
}