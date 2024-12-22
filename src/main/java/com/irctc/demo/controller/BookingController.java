package com.irctc.demo.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.irctc.demo.model.Passenger;
import com.irctc.demo.model.Seat;
import com.irctc.demo.service.TrainService;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class BookingController {
	
	@Autowired
	private TrainService trainService;
	
	@GetMapping("/")
	public String greet() {
		return "Hello world";
	}
	
	@GetMapping("/confList")
	public ResponseEntity<?> displayConfirmedList(){
		Map<Seat,Passenger> confList = trainService.getConfirmedList();
		if(confList==null || confList.size()==0) {
			Map<String,String> empty = new HashMap<>();
			empty.put("Result", "No Tickets are confirmed");
			return new ResponseEntity<>(empty, HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<>(confList, HttpStatus.OK);
		}
	}
	
	@GetMapping("/racList")
	public ResponseEntity<?> displayRACList(){
		Queue<Passenger> racList = trainService.getRACList();
		if(racList==null || racList.size()==0) {
			Map<String,String> empty = new HashMap<>();
			empty.put("Result", "No passengers are in RAC list");
			return new ResponseEntity<>(empty, HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<>(racList, HttpStatus.OK);
		}
	}
	
	@GetMapping("/waitList")
	public ResponseEntity<?> displayWaitingList(){
		Queue<Passenger> waitingList = trainService.getWaitingList();
		if(waitingList==null || waitingList.size()==0) {
			Map<String,String> empty = new HashMap<>();
			empty.put("Result", "No passengers are in waiting list");
			return new ResponseEntity<>(empty, HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<>(waitingList, HttpStatus.OK);
		}
	}
	
	
	@PostMapping("/book")
	public ResponseEntity<?> bookTicket(@RequestBody Passenger passenger){
		String result = "";
		if(passenger!=null && passenger.getId()!=0) {
			result = trainService.bookTicket(passenger);
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@PostMapping("/cancel")
	public ResponseEntity<?> cancelTicket(@RequestBody Passenger passenger){
		String result = "";
		if(passenger!=null && passenger.getId()!=0) {
			result = trainService.cancelTicket(passenger);
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
}























