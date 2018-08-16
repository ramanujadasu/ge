package com.ge.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.ge.dto.MovieDTO;
import com.ge.dto.TicketDTO;
import com.ge.model.Movie;
import com.ge.model.Ticket;

public class TBUtils {

	public static Movie getMovie(MovieDTO movieDTO) {
		Movie movie = new Movie();
		BeanUtils.copyProperties(movieDTO, movie);
		return movie;
	}

	public static List<MovieDTO> getMovieDTOs(List<Movie> movies) {
		List<MovieDTO> movieDTOs = new ArrayList<>();
		for (Movie movie : movies) {
			MovieDTO movieDTO = new MovieDTO();
			BeanUtils.copyProperties(movie, movieDTO);
			movieDTOs.add(movieDTO);
		}
		return movieDTOs;
	}

	public static Ticket getTicket(TicketDTO ticketDTO) {
		Ticket ticket = new Ticket();
		BeanUtils.copyProperties(ticketDTO, ticket);
		return ticket;
	}

	public static TicketDTO getTicketDTO(Ticket ticketInfo) {
		TicketDTO ticketDTO = new TicketDTO();
		BeanUtils.copyProperties(ticketInfo, ticketDTO);
		return ticketDTO;
	}

	public static MovieDTO getMovieDTO(Movie movie) {
		MovieDTO movieDTO = new MovieDTO();
		BeanUtils.copyProperties(movie, movieDTO);
		return movieDTO;
	}

	public static List<TicketDTO> getTicketDTOs(List<Ticket> tickets) {
		List<TicketDTO> ticketDTOs = new ArrayList<>();
		for (Ticket ticket : tickets) {
			TicketDTO ticketDTO = new TicketDTO();
			BeanUtils.copyProperties(ticket, ticketDTO);
			ticketDTOs.add(ticketDTO);
		}
		return ticketDTOs;
	}

	public static boolean isValidSeatNo(List<Integer> seats) {

		for (Integer seatNo : seats) {
			if (seatNo < 0 || seatNo > 100) {
				return false;
			}
		}
		return true;
	}

}
