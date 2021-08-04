package com.example.appointment

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import javax.persistence.*

@Entity
class Client(
	var firstname: String,
	var lastname: String,
	var description: String? = null,
	var addedAt: LocalDateTime = LocalDateTime.now(),
	@Id @GeneratedValue var id: Long? = null);

@Entity
class AppointmentClient(
	var date: LocalDate,
	var start: LocalTime,
	var end: LocalTime,
	@ManyToOne var client: Client? = null,
	var addedAt: LocalDateTime = LocalDateTime.now(),
	@Id @GeneratedValue var id: Long? = null);

@Entity
class Holiday(
	var date: LocalDate,
	var addedAt: LocalDateTime = LocalDateTime.now(),
	@Id @GeneratedValue var id: Long? = null);