package com.ablive.boot.rest;

import java.time.LocalDate;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ablive.boot.converter.ReservRoomRespConverter;
import com.ablive.boot.entity.ReservationEntity;
import com.ablive.boot.entity.RoomEntity;
import com.ablive.boot.model.request.ReservationRequest;
import com.ablive.boot.model.response.ResRoomResp;
import com.ablive.boot.model.response.ResvResp;
import com.ablive.boot.repository.PageableRoomRepository;
import com.ablive.boot.repository.ReservationRepository;
import com.ablive.boot.repository.RoomRepository;

@RestController
@RequestMapping(ResourceConstants.ROOM_RESERVATION_V1)
@CrossOrigin
public class ReservationResource {

	@Autowired
	PageableRoomRepository pageableRoomRepository;
	
	@Autowired
	RoomRepository roomRepository;
	
	@Autowired
	ReservationRepository reservationRepository;
	
	@Autowired
	ConversionService conversionService;
	
	@RequestMapping(path = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Page<ResRoomResp> getAvailableRooms(
			@RequestParam(value = "checkin") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkin,
			@RequestParam(value = "checkout") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkout,
			Pageable pageable) {

		Page<RoomEntity> roomEntityList = pageableRoomRepository.findAll(pageable);
		
		return roomEntityList.map(room -> new ReservRoomRespConverter().convert(room));
	}

	@RequestMapping(path = "/{roomId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<RoomEntity> getRoomById(@PathVariable Long roomId) {
		
		Optional<RoomEntity> roomEntity = roomRepository.findById(roomId);
		
		if (roomEntity.isPresent()) {
			RoomEntity room = roomEntity.get();
			return new ResponseEntity<>(room, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(path = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ResvResp> createReservation(@RequestBody ReservationRequest reservationRequest) {
		
		ReservationEntity reservationEntity = conversionService.convert(reservationRequest, ReservationEntity.class);
		reservationRepository.save(reservationEntity);
		
		Optional<RoomEntity> roomEntity = roomRepository.findById(reservationRequest.getRoomId());
		
		if (roomEntity.isPresent()) {
			RoomEntity room = roomEntity.get();
			room.addReservationEntity(reservationEntity);
			roomRepository.save(room);
			
			reservationEntity.setRoomEntity(room);
			
			ResvResp reservationResponse = conversionService.convert(reservationEntity, ResvResp.class);
		
			return new ResponseEntity<>(reservationResponse, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} 
	}
	
	@RequestMapping(path = "", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ResRoomResp> updateReservation(@RequestBody ReservationRequest reservationRequest) {
		return new ResponseEntity<>(new ResRoomResp(), HttpStatus.OK);
	}

	
	@RequestMapping(path = "/{reservationId}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteReservation(@PathVariable long reservationId) {
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
}