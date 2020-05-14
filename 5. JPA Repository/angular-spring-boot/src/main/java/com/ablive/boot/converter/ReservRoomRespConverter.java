package com.ablive.boot.converter;

import org.springframework.core.convert.converter.Converter;

import com.ablive.boot.entity.RoomEntity;
import com.ablive.boot.model.Links;
import com.ablive.boot.model.Self;
import com.ablive.boot.model.response.ResRoomResp;
import com.ablive.boot.rest.ResourceConstants;

public class ReservRoomRespConverter implements Converter<RoomEntity, ResRoomResp> {

	@Override
	public ResRoomResp convert(RoomEntity source) {
		
		ResRoomResp reservationResponse = new ResRoomResp();
		
		reservationResponse.setRoomNumber(source.getRoomNumber());
		reservationResponse.setPrice(Integer.valueOf(source.getPrice()));
	
		Links links = new Links();
		
		Self self = new Self();
		self.setRef(ResourceConstants.ROOM_RESERVATION_V1 + "/" + source.getId());
		links.setSelf(self);
		
		reservationResponse.setLinks(links);
		return reservationResponse;
	}
	
}