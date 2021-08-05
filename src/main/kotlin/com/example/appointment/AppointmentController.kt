package com.example.appointment

import com.example.appointment.exception.AppointmentException
import org.springframework.http.HttpStatus.*
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/api")
class AppointmentController(private val appointmentRepository: AppointmentClientRepository, private val clientRepository: ClientRepository,
							private val holidayRepository: HolidayRepository) {

	@GetMapping("/appointment")
	fun findAll() = appointmentRepository.findAllByOrderByAddedAtDesc()

	@GetMapping("/appointment/{id}")
	fun findOne(@PathVariable id: String) = appointmentRepository.findById(id) ?: throw ResponseStatusException(NOT_FOUND, "This appointment does not exist")

	@PostMapping("/appointment")
	suspend fun createAppointment(@RequestBody appointmentClient: AppointmentClient){
		if(holidayRepository.existsHolidayByDate(appointmentClient.date)){
			throw AppointmentException("Selected date is holiday")
		}else {
			var client = clientRepository.save(
				Client(
					appointmentClient.client?.firstname!!,
					appointmentClient.client?.lastname!!,
					appointmentClient.client?.description
				)
			)
			appointmentRepository.save(
				AppointmentClient(
					appointmentClient.date,
					appointmentClient.start,
					appointmentClient.end,
					client
				)
			)
		}
	}

	@PutMapping("/appointment/{id}")
	suspend fun updateAppointment(@PathVariable("id") appointmentId: Long, @RequestBody appointmentClient: AppointmentClient) =
		if(appointmentRepository.existsById(appointmentId)){
			if(holidayRepository.existsHolidayByDate(appointmentClient.date)){
				throw AppointmentException("Selected date is holiday")
			}else{
				appointmentRepository.save(
					AppointmentClient(
						appointmentClient.date,
						appointmentClient.start,
						appointmentClient.end,
						appointmentClient.client,
						appointmentClient.addedAt,
						appointmentClient.id
					)
				)
			}
		}else throw AppointmentException("This appointment does not exist")

	@DeleteMapping("/appointment/{id}")
	fun deleteAppointment(@PathVariable("id") appointmentId: Long) =
		if(appointmentRepository.existsById(appointmentId)){
			appointmentRepository.deleteById(appointmentId)

		}else throw AppointmentException("This appointment does not exist")
}
