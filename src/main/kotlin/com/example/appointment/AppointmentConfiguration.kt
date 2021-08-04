package com.example.appointment

import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.util.*

@Configuration
class AppointmentConfiguration {

    @Bean
    fun databaseInitializer(holidayRepository: HolidayRepository,
							appointmentClientRepository: AppointmentClientRepository) = ApplicationRunner {

		for (i in 1..8) {
			appointmentClientRepository.save(AppointmentClient(LocalDate.now(), LocalTime.now().minusHours((10-i).toLong()), LocalTime.now().minusHours(9-i.toLong()),null))
		}

		for (i in 1..8) {
			holidayRepository.save(Holiday(LocalDate.now().plusDays(i.toLong())))
		}

    }
}
