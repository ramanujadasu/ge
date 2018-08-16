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
		if (tBRepository.getMovies() != null) {
			return TBUtils.getMovieDTOs(tBRepository.getMovies());
		}
		return null;
	}

	@Override
	public ResponseDTO<TicketResponseDTO> bookTicket(TicketDTO ticketDTO) {
		ResponseDTO<TicketResponseDTO> responseDTO = new ResponseDTO<>();
		TicketResponseDTO ticketResponseDTO = new TicketResponseDTO();
		List<Integer> notAllocatedSeats = tBRepository.getNotAllocatedSeats();
		if (!notAllocatedSeats.containsAll(ticketDTO.getSeats())) {
			ticketResponseDTO = new TicketResponseDTO(null, ticketDTO.getMovieId(), ticketDTO.getSeats(),
					TBStatus.FAILURE.name(), ticketDTO.getUserId());
			responseDTO.setResponseData(ticketResponseDTO);
			responseDTO.setMessage("Already given seats(one/more) are booked");
			return responseDTO;
		}
		Ticket ticket = TBUtils.getTicket(ticketDTO);
		// TicketBooking ticketBooking = new TicketBooking();
		String ticketId = null;
		/*
		 * TicketThread ticketThread = new TicketThread(ticket,ticketBooking,
		 * ticket.getUserId()); ticketThread.run(); logger.info("ticketThread: {}",
		 * ticketThread.getName()); ticketId = ticketThread.getTicket().getTicketId();
		 */

		ticketId = this.bookingTicket(ticket);
		logger.info("ticketId: {}", ticketId);
		if (ticketId != null) {
			responseDTO.setMessage("Booking is success for the moive Id:" + ticketDTO.getMovieId());
			ticketResponseDTO = new TicketResponseDTO(ticketId, ticketDTO.getMovieId(), ticketDTO.getSeats(),
					TBStatus.CONFIRMED.name(), ticketDTO.getUserId());
			responseDTO.setResponseData(ticketResponseDTO);
		} else {
			ticketResponseDTO = new TicketResponseDTO(ticketId, ticketDTO.getMovieId(), ticketDTO.getSeats(),
					TBStatus.FAILURE.name(), ticketDTO.getUserId());
			responseDTO.setResponseData(ticketResponseDTO);
			responseDTO.setMessage("Booking is failed due for the moive Id:" + ticketDTO.getMovieId());
		}
		return responseDTO;
	}

	public synchronized String bookingTicket(Ticket ticket) {
		logger.info("bookingTicket:");
		String ticketId = null;
		ExecutorService executor = Executors.newCachedThreadPool();
		Future<String> future = executor.submit(new Callable<String>() {
			public String call() throws Exception {
				return tBRepository.bookTicket(ticket);
			}
		});
		try {
			ticketId = future.get(2, TimeUnit.MINUTES);
		} catch (InterruptedException e) {
			logger.error("InterruptedException", e);
		} catch (ExecutionException e) {
			logger.error("ExecutionException", e);
		} catch (TimeoutException e) {
			logger.error("TimeoutException", e);
			ticketId = null;
			future.cancel(true);
		}
		logger.error("ticketId: {}", ticketId);
		return ticketId;
	}

	@Override
	public TicketDTO getTicketInfo(String ticketId) {
		if (tBRepository.getTicketInfo(ticketId) != null) {
			return TBUtils.getTicketDTO(tBRepository.getTicketInfo(ticketId));
		}
		return null;
	}

	@Override
	public MovieDTO findByMovieName(String movieName) {
		if (tBRepository.getMovieByName(movieName) != null) {
			return TBUtils.getMovieDTO(tBRepository.getMovieByName(movieName));
		}
		return null;
	}

	@Override
	public List<Integer> getNotAllocatedSeats() {
		return tBRepository.getNotAllocatedSeats();
	}

	@Override
	public List<TicketDTO> getTicketByUserId(String userId) {
		if (tBRepository.getTicketByUserId(userId) != null) {
			return TBUtils.getTicketDTOs(tBRepository.getTicketByUserId(userId));
		}
		return null;
	}

	@Override
	public void setNotAllocatedSeats() {
		tBRepository.setNotAllocatedSeats();
	}

	@Override
	public List<TicketDTO> getTickets() {
		if (tBRepository.getTickets() != null) {
			return TBUtils.getTicketDTOs(tBRepository.getTickets());
		}
		return null;
	}

}
