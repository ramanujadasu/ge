package com.ge.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ge.model.Movie;
import com.ge.model.Ticket;

@Service
public class TBRepositoryImpl implements TBRepository {

	private static final Logger logger = LoggerFactory.getLogger(TBRepositoryImpl.class);

	List<Movie> movies = new ArrayList<>();
	List<Ticket> tickets = new ArrayList<>();
	List<Integer> totalNumberOfSeats = new ArrayList<>();
	

	@Override
	public void addMovie(Movie movie) {
		movies.add(movie);
	}

	@Override
	public List<Movie> getMovies() {
		logger.info("getMovies");
		return movies;
	}

	@Override
	public String bookTicket(Ticket ticket) {
		String ticketId = UUID.randomUUID().toString();
		ticket.setTicketId(ticketId);
		for(Integer seat:ticket.getSeats()) {
			totalNumberOfSeats.remove(seat);
			}
		
		tickets.add(ticket);
		return ticketId;
	}

	@Override
	public Ticket getTicketInfo(String ticketId) {
		List<Ticket> resultTickets = tickets.stream().filter(ticket -> ticket.getTicketId().equals(ticketId)).collect(Collectors.toList());
		if (!resultTickets.isEmpty()) {
			return resultTickets.get(0);
		}
		return null;
	}

	@Override
	public Movie getMovieByName(String movieName) {
		List<Movie> resultMovies = movies.stream().filter(movie -> movie.getMovieName().equals(movieName))
				.collect(Collectors.toList());
		if (!resultMovies.isEmpty()) {
			return resultMovies.get(0);
		}
		return null;
	}

	@Override
	public List<Ticket> getTicketByUserId(String userId) {
		List<Ticket> resultTickets = tickets.stream().filter(ticket -> ticket.getUserId().equals(userId))
				.collect(Collectors.toList());
		if (!resultTickets.isEmpty()) {
			return resultTickets;
		}
		return null;
	}
	@Override
	public void setNotAllocatedSeats() {
		for(int i=1;i<=100;i++) {
			totalNumberOfSeats.add(i);
		}
		logger.info("totalNumberOfSeats: {}", totalNumberOfSeats.size());
	}
	@Override
	public List<Integer> getNotAllocatedSeats() {
		return totalNumberOfSeats;
	}

	@Override
	public List<Ticket> getTickets() {
		List<Ticket> resultTickets = tickets.stream().collect(Collectors.toList());
		if (!resultTickets.isEmpty()) {
			return resultTickets;
		}
		return null;
	}

}
