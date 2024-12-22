package com.irctc.demo.model;

import com.irctc.demo.enums.Berth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Seat {

	private int seatNumber;
	private Berth berthType;
}
