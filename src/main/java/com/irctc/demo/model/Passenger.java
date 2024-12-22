package com.irctc.demo.model;

import com.irctc.demo.enums.Berth;

import lombok.Data;

@Data
public class Passenger {

	private int id;
	private String name;
	private Berth preferredBerth;

}
