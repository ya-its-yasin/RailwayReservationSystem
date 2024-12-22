package com.irctc.demo.service;

import java.util.Map;
import java.util.Queue;

import com.irctc.demo.model.Passenger;
import com.irctc.demo.model.Seat;

public interface TrainService {

	Map<Seat, Passenger> getConfirmedList();

	String bookTicket(Passenger passenger);

	Queue<Passenger> getRACList();
	
	Queue<Passenger> getWaitingList();

	String cancelTicket(Passenger passenger);

}
