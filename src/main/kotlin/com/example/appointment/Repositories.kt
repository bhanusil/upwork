package com.example.appointment

import org.springframework.data.repository.CrudRepository
import java.time.LocalDate
import java.util.*

interface AppointmentClientRepository : CrudRepository<AppointmentClient, Long> {
	fun findById(id: String): AppointmentClient?
	fun findAllByOrderByAddedAtDesc(): Iterable<AppointmentClient>
}

interface ClientRepository : CrudRepository<Client, Long> {
}

interface HolidayRepository : CrudRepository<Holiday, Long> {
	fun existsHolidayByDate(id: LocalDate): Boolean
}

