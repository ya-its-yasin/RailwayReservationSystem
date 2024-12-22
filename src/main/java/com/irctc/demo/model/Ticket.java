package com.irctc.demo.model;

import lombok.Data;

@Data
public class Ticket {

	private int id;
	private Passenger passenger;
	private Seat seat;
}
