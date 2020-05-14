package com.ablive.boot.repository;

import org.springframework.data.repository.CrudRepository;

import com.ablive.boot.entity.ReservationEntity;

public interface ReservationRepository extends CrudRepository<ReservationEntity, Long> {

	
	
}