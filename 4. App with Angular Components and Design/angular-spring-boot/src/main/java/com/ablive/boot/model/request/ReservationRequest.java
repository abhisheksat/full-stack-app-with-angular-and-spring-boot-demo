package com.ablive.boot.model.request;

import java.time.LocalDate;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

public class ReservationRequest {

	private Long id;

	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	private LocalDate checkin;

	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	private LocalDate checkout;

	public ReservationRequest() {
		super();
	}

	public ReservationRequest(Long id, LocalDate checkin, LocalDate checkout) {
		super();
		this.id = id;
		this.checkin = checkin;
		this.checkout = checkout;
	}

	public LocalDate getCheckin() {
		return checkin;
	}

	public LocalDate getCheckout() {
		return checkout;
	}

	public Long getId() {
		return id;
	}

	public void setCheckin(LocalDate checkin) {
		this.checkin = checkin;
	}

	public void setCheckout(LocalDate checkout) {
		this.checkout = checkout;
	}

	public void setId(Long id) {
		this.id = id;
	}

}