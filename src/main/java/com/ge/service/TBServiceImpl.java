package com.ge.service;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ge.dto.MovieDTO;
import com.ge.dto.ResponseDTO;
import com.ge.dto.TicketDTO;
import com.ge.dto.TicketResponseDTO;
import com.ge.model.Ticket;
import com.ge.repository.TBRepository;
import com.ge.util.TBStatus;
import com.ge.util.TBUtils;

@Service
public class TBServiceImpl implements TBService {

	private static final Logger logger = LoggerFactory.getLogger(TBServiceImpl.class);

	@Autowired
	TBRepository tBRepository;

	@Override
	public void addMovie(MovieDTO movieDTO) {
		logger.info("addMovie: movieName{}", movieDTO.getMovieName());
		tBRepository.addMovie(TBUtils.getMovie(movieDTO));
	}

	@Override
	public List<MovieDTO> getMovies() {
		if(tBRepository.getMovies() != null) {
			return TBUtils.getMovieDTOs(tBRepository.getMovies());	
		}
		return null;
	}

	@Override
	public ResponseDTO<TicketResponseDTO> bookTicket(TicketDTO ticketDTO) {
		ResponseDTO<TicketResponseDTO> responseDTO = new ResponseDTO<>();
		TicketResponseDTO ticketResponseDTO = new TicketResponseDTO();
		Ticket ticket = TBUtils.getTicket(ticketDTO);
		TicketBooking ticketBooking = new TicketBooking();
		
		TicketThread ticketThread = new TicketThread(ticket,ticketBooking, ticket.getUserId());
		logger.info("ticketThread: {}", ticketThread.getName());
		String ticketId = ticketThread.getTicket().getTicketId();
		logger.info("ticketId: {}", ticketId);
		if(ticketId != null) {
			responseDTO.setMessage("Booking is success for the moive Id:" + ticketDTO.getMovieId());
			ticketResponseDTO = new TicketResponseDTO(ticketId, ticketDTO.getMovieId(), ticketDTO.getSeats(), TBStatus.CONFIRMED.name(), ticketDTO.getUserId());
			responseDTO.setResponseData(ticketResponseDTO);
		}else {
			ticketResponseDTO = new TicketResponseDTO(ticketId, ticketDTO.getMovieId(), ticketDTO.getSeats(), TBStatus.FAILURE.name(), ticketDTO.getUserId());
			responseDTO.setResponseData(ticketResponseDTO);
			responseDTO.setMessage("Booking is failed due for the moive Id:" + ticketDTO.getMovieId());
		}
		return responseDTO;
	}

	
	@Override
	public TicketDTO getTicketInfo(String ticketId) {
		if(tBRepository.getTicketInfo(ticketId) != null) {
			return TBUtils.getTicketDTO(tBRepository.getTicketInfo(ticketId));	
		}
		return null;
	}

	@Override
	public MovieDTO findByMovieName(String movieName) {
		if(tBRepository.getMovieByName(movieName) != null) {
			return TBUtils.getMovieDTO(tBRepository.getMovieByName(movieName));	
		}
		return null;
	}

	@Override
	public Integer getNotAllocatedSeats() {
			return tBRepository.getNotAllocatedSeats();	
	}

	
	@Override
	public List<TicketDTO> getTicketByUserId(String userId){
		if(tBRepository.getTicketByUserId(userId) != null) {
			return TBUtils.getTicketDTOs(tBRepository.getTicketByUserId(userId));	
		}
		return null;
	}
	

}
