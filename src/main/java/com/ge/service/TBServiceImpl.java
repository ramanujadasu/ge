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
import com.ge.repository.TBRepository;
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
	public synchronized ResponseDTO<TicketDTO> bookTicket(TicketDTO ticketDTO) {
		ResponseDTO<TicketDTO> responseDTO = new ResponseDTO<TicketDTO>();
		String result = null;
		ExecutorService executor = Executors.newCachedThreadPool();
		Future<String> future = executor.submit(new Callable<String>() {
			public String call() throws Exception {
				tBRepository.bookTicket(TBUtils.getTicket(ticketDTO));
				return "Booking is success for the moive Id:" + ticketDTO.getMovieId();
			}
		});
		try {
			result = future.get(2, TimeUnit.MINUTES);
			responseDTO.setMessage(result);
		} catch (InterruptedException e) {
		} catch (ExecutionException e) {
		} catch (TimeoutException e) {
			future.cancel(true);
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

}
