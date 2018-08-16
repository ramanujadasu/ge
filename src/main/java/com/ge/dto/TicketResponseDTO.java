package com.ge.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value = Include.NON_NULL)
public class TicketResponseDTO {

	private String ticketId;
	private String movieId;
	private List<Integer> seats;
	private String confirmationStatus;
	private String userId;

	public TicketResponseDTO() {
		super();
		
	}
	public TicketResponseDTO(String ticketId, String movieId, List<Integer> seats, String confirmationStatus,
			String userId) {
		super();
		this.ticketId = ticketId;
		this.movieId = movieId;
		this.seats = seats;
		this.confirmationStatus = confirmationStatus;
		this.userId = userId;
	}
	
	public TicketResponseDTO(List<Integer> seats, String confirmationStatus,
			String userId) {
		super();
		this.seats = seats;
		this.confirmationStatus = confirmationStatus;
		this.userId = userId;
	}
	
	public String getTicketId() {
		return ticketId;
	}

	public void setTicketId(String ticketId) {
		this.ticketId = ticketId;
	}



	public String getMovieId() {
		return movieId;
	}

	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}

	public String getConfirmationStatus() {
		return confirmationStatus;
	}

	public void setConfirmationStatus(String confirmationStatus) {
		this.confirmationStatus = confirmationStatus;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public List<Integer> getSeats() {
		return seats;
	}

	public void setSeats(List<Integer> seats) {
		this.seats = seats;
	}

}
