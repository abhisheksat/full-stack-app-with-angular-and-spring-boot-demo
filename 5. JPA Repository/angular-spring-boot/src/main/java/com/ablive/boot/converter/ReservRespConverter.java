package com.ablive.boot.converter;

import org.springframework.core.convert.converter.Converter;

import com.ablive.boot.entity.ReservationEntity;
import com.ablive.boot.model.response.ResvResp;

public class ReservRespConverter implements Converter<ReservationEntity, ResvResp> {

	@Override
	public ResvResp convert(ReservationEntity source) {
	
		ResvResp reservationResponse = new ResvResp();
		reservationResponse.setCheckin(source.getCheckin());
		reservationResponse.setCheckout(source.getCheckout());
		
		return reservationResponse;
	}
	
}