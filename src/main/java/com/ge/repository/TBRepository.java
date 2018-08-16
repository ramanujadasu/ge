package com.ge.repository;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ge.model.Movie;
import com.ge.model.Ticket;

@Service
public interface TBRepository {

	void addMovie(Movie movie);

	List<Movie> getMovies();

	String bookTicket(Ticket ticket);

	Ticket getTicketInfo(String ticketId);

	Movie getMovieByName(String movieName);

	List<Ticket> getTicketByUserId(String userId);

	List<Integer>  getNotAllocatedSeats();

	void setNotAllocatedSeats();

	List<Ticket> getTickets();
	
}
	