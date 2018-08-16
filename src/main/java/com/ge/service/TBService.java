package com.ge.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ge.dto.MovieDTO;
import com.ge.dto.ResponseDTO;
import com.ge.dto.TicketDTO;
import com.ge.model.Movie;

@Service
public interface TBService {

	void addMovie(MovieDTO movieDTO);

	List<MovieDTO> getMovies();

	ResponseDTO<TicketDTO> bookTicket(TicketDTO ticketDTO);

	TicketDTO getTicketInfo(String ticketId);

	MovieDTO findByMovieName(String movieName);
	
}
	