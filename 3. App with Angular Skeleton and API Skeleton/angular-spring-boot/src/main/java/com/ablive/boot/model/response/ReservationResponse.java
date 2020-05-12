package com.ablive.boot.model.response;

import com.ablive.boot.model.Links;

public class ReservationResponse {

	private Long id;
	private Integer roomNumber;
	private Integer price;
	private Links links;

	public ReservationResponse() {
		super();
	}

	public ReservationResponse(Integer roomNumber, Integer price) {
		super();
		this.roomNumber = roomNumber;
		this.price = price;
	}

	public Long getId() {
		return id;
	}

	public Links getLinks() {
		return links;
	}

	public Integer getPrice() {
		return price;
	}

	public Integer getRoomNumber() {
		return roomNumber;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setLinks(Links links) {
		this.links = links;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public void setRoomNumber(Integer roomNumber) {
		this.roomNumber = roomNumber;
	}

}