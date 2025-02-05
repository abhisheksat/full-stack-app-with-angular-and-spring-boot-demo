package com.ablive.boot;

import static io.restassured.RestAssured.given;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import com.ablive.boot.rest.ResourceConstants;

import io.restassured.RestAssured;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AngularSpringBootApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReservationResource {

	@LocalServerPort
	private int port;
	
	@Before
	public void setUp() throws Exception {
		RestAssured.port = Integer.valueOf(port);
		RestAssured.basePath = ResourceConstants.ROOM_RESERVATION_V1;
		RestAssured.baseURI = "http://localhost";
	}

	@Test
	public void test() {
		given().when().get("/" + 1).then()
			.statusCode(200);
	}

}