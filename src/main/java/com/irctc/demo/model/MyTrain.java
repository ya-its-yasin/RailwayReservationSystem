package com.irctc.demo.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import org.springframework.stereotype.Component;

import com.irctc.demo.enums.Berth;

import lombok.Data;


@Data
@Component
public class MyTrain {

	private static int
			availableTickets = 3,
			avRACCnt = 1,
			avwaitingListCnt = 1;

	private Map<Seat,Passenger> confirmedSeats = new LinkedHashMap<>(3);
	private Queue<Passenger> RACList = new LinkedList<>();
	private Queue<Passenger> waitingList = new LinkedList<>();
	
	
	public MyTrain(){
		confirmedSeats.put(new Seat(1,Berth.UPPER), null);
		confirmedSeats.put(new Seat(2,Berth.MIDDLE), null);
		confirmedSeats.put(new Seat(3,Berth.LOWER), null);
		/*
		 * confirmedSeats.put(new Seat(4,Berth.UPPER), null); confirmedSeats.put(new
		 * Seat(5,Berth.MIDDLE), null); confirmedSeats.put(new Seat(6,Berth.LOWER),
		 * null);
		 */
	}
	 
	public String bookTicket(Passenger passenger) {
		String res = "Success";
		if(availableTickets>0) {
			List<Seat> availableSeats = getAvailableSeats();
			Seat seat = getPreferredOrDefaultSeat(availableSeats, passenger.getPreferredBerth());
			confirmedSeats.put(seat, passenger);
			availableTickets--;
		}else if(avRACCnt>0) {
			RACList.add(passenger);
			avRACCnt--;
		}else if(avwaitingListCnt>0) {
			waitingList.add(passenger);
			avwaitingListCnt--;
		}else {
			res = "No tickets available";
		}
		return res;
	}
	
	private Seat getPreferredOrDefaultSeat(List<Seat> availableSeats, Berth preferredBerth) {
		Seat defaultSeat = availableSeats.get(0);
		for(Seat seat : availableSeats) {
			if(seat.getBerthType()==preferredBerth) {
				return seat;
			}
		}
		return defaultSeat;
	}

	private List<Seat> getAvailableSeats(){
		List<Seat> seats = new ArrayList<>();
		for(var entry : confirmedSeats.entrySet()) {
			if(entry.getValue()==null) {
				seats.add(entry.getKey());
			}
		}
		return seats;
	}

	public String cancelTicket(Passenger passenger) {
		String success = "Ticket cancelled";
		for(var entry : confirmedSeats.entrySet()) {
			if(entry.getValue()!=null && entry.getValue().getId()==passenger.getId()) {
				if(RACList.peek()!=null) {
					confirmedSeats.put(entry.getKey(),RACList.poll());
					avRACCnt++;
				}
				if(waitingList.peek()!=null) {
					RACList.add(waitingList.poll());
					avRACCnt--;
					avwaitingListCnt++;
				}
				return success;
			}
		}
		for(Passenger pass : RACList) {
			if(pass.getId()==passenger.getId()) {
				RACList.remove(pass);
				if(waitingList.peek()!=null) {
					RACList.add(waitingList.poll());
					avwaitingListCnt++;
				}
				avRACCnt++;
				return success;
			}
		}
		for(Passenger pass : waitingList) {
			if(pass.getId()==passenger.getId()) {
				waitingList.remove(pass);
				avwaitingListCnt++;
				return success;
			}
		}
		return "Failed";
	}	 	
}
