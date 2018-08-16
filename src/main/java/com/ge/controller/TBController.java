package com.ge.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ge.dto.MovieDTO;
import com.ge.dto.ResponseDTO;
import com.ge.dto.TicketDTO;
import com.ge.service.TBService;

@RestController
@RequestMapping("tb")
public class TBController {

	private final static Logger logger = LoggerFactory.getLogger(TBController.class);

	@Autowired
	TBService tbService;

	@RequestMapping(method = RequestMethod.POST, value = "/book-ticket", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDTO<TicketDTO>> bookTicket(@RequestBody(required = true) TicketDTO ticketDTO) {

		logger.info("bookTicket");
		String userId = ticketDTO.getUserId();
		ResponseDTO<TicketDTO> responseTicketDTO = null;
		if (null == userId) {
			logger.info("User is null");
			responseTicketDTO = new ResponseDTO<TicketDTO>("UserId is required!!", null);
			return new ResponseEntity<ResponseDTO<TicketDTO>>(responseTicketDTO, HttpStatus.BAD_REQUEST);
		}
		logger.info("responseTicketDTO: {}", responseTicketDTO);
		responseTicketDTO = tbService.bookTicket(ticketDTO);
		responseTicketDTO = new ResponseDTO<TicketDTO>(responseTicketDTO.getMessage(),
				responseTicketDTO.getResponseData());
		return new ResponseEntity<ResponseDTO<TicketDTO>>(responseTicketDTO, HttpStatus.OK);

	}

	@RequestMapping(method = RequestMethod.POST, value = "/add-movie", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDTO<MovieDTO>> addMovie(@RequestBody MovieDTO movieDTO) {

		ResponseDTO<MovieDTO> responseMovieDTO = null;
		logger.info("movieDTO: {}", movieDTO);
		if (movieDTO == null || StringUtils.isEmpty(movieDTO.getMovieName())) {
			responseMovieDTO = new ResponseDTO<MovieDTO>("Please provide movie name.", null);
			return new ResponseEntity<ResponseDTO<MovieDTO>>(responseMovieDTO, HttpStatus.BAD_REQUEST);
		}

		MovieDTO existMovieDTO = tbService.findByMovieName(movieDTO.getMovieName());
		if (null == existMovieDTO) {
			tbService.addMovie(movieDTO);
			responseMovieDTO = new ResponseDTO<MovieDTO>("Given movie added successfully.", movieDTO);
			return new ResponseEntity<ResponseDTO<MovieDTO>>(responseMovieDTO, HttpStatus.OK);
		}
		responseMovieDTO = new ResponseDTO<MovieDTO>("Given movie already exist..", null);
		return new ResponseEntity<ResponseDTO<MovieDTO>>(responseMovieDTO, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/all-movies")
	public ResponseEntity<List<MovieDTO>> getAllMoives() {
		logger.info("getAllMoives:");
		return new ResponseEntity<List<MovieDTO>>(tbService.getMovies(), HttpStatus.OK);
	}

}
