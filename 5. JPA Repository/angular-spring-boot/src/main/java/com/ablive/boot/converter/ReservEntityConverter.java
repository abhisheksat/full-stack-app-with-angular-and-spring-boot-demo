package com.ablive.boot.converter;

import org.springframework.core.convert.converter.Converter;

import com.ablive.boot.entity.ReservationEntity;
import com.ablive.boot.model.request.ReservationRequest;

public class ReservEntityConverter implements Converter<ReservationRequest, ReservationEntity>{

	@Override
	public ReservationEntity convert(ReservationRequest source) {

		ReservationEntity reservationEntity = new ReservationEntity();
		reservationEntity.setCheckin(source.getCheckin());
		reservationEntity.setCheckout(source.getCheckout());
		
		if (null != source.getId()) {
			reservationEntity.setId(source.getId());
		}
		
		return reservationEntity;
	}
	
}