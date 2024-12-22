package com.irctc.demo.service;

import java.util.Map;
import java.util.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.irctc.demo.model.MyTrain;
import com.irctc.demo.model.Passenger;
import com.irctc.demo.model.Seat;

@Service
public class TrainServiceImpl implements TrainService {
	
	@Autowired
	private MyTrain myTrain;

	@Override
	public Map<Seat, Passenger> getConfirmedList() {
		return myTrain.getConfirmedSeats();
	}

	@Override
	public String bookTicket(Passenger passenger) {
		return myTrain.bookTicket(passenger);
	}

	@Override
	public Queue<Passenger> getRACList() {
		return myTrain.getRACList();
	}
	
	@Override
	public Queue<Passenger> getWaitingList() {
		return myTrain.getWaitingList();
	}

	@Override
	public String cancelTicket(Passenger passenger) {
		return myTrain.cancelTicket(passenger);
	}

}
