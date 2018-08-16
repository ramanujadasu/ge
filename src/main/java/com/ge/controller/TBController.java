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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ge.dto.MovieDTO;
import com.ge.dto.ResponseDTO;
import com.ge.dto.TicketDTO;
import com.ge.dto.TicketResponseDTO;
import com.ge.service.TBService;
import com.ge.util.TBUtils;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("tb")
public class TBController {

	private final static Logger logger = LoggerFactory.getLogger(TBController.class);

	@Autowired
	TBService tbService;

	@ApiOperation(value = "Ticket Booking", notes = "This API used for ticket booking")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 400, message = "Bad Request"), @ApiResponse(code = 405, message = "Method Not Allowed"),
			@ApiResponse(code = 401, message = "Unauthorized"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@RequestMapping(method = RequestMethod.POST, value = "/book-ticket", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDTO<TicketResponseDTO>> bookTicket(
			@ApiParam(value = "Valid access token should passed") @RequestHeader(value = "token") String accessToken,
			@RequestBody(required = true) TicketDTO ticketDTO) {

		logger.info("bookTicket");
		String userId = ticketDTO.getUserId();
		ResponseDTO<TicketResponseDTO> responseTicketDTO = null;
		if (null == userId) {
			logger.info("User is null");
			responseTicketDTO = new ResponseDTO<TicketResponseDTO>("UserId is required!!", null);
			return new ResponseEntity<ResponseDTO<TicketResponseDTO>>(responseTicketDTO, HttpStatus.BAD_REQUEST);
		}
		List<Integer> seats = ticketDTO.getSeats();
		if (seats.isEmpty()) {
			logger.info("Seat numbers are required");
			responseTicketDTO = new ResponseDTO<TicketResponseDTO>("Seat number are required!!", null);
			return new ResponseEntity<ResponseDTO<TicketResponseDTO>>(responseTicketDTO, HttpStatus.BAD_REQUEST);
		} else if (!TBUtils.isValidSeatNo(seats)) {
			logger.info("Seat numbers are not valid");
			responseTicketDTO = new ResponseDTO<TicketResponseDTO>("Seat numbers are not valid!!", null);
			return new ResponseEntity<ResponseDTO<TicketResponseDTO>>(responseTicketDTO, HttpStatus.BAD_REQUEST);
		}
		logger.info("responseTicketDTO: {}", responseTicketDTO);
		responseTicketDTO = tbService.bookTicket(ticketDTO);
		responseTicketDTO = new ResponseDTO<TicketResponseDTO>(responseTicketDTO.getMessage(),
				responseTicketDTO.getResponseData());
		return new ResponseEntity<ResponseDTO<TicketResponseDTO>>(responseTicketDTO, HttpStatus.OK);

	}

	@ApiOperation(value = "Add Movie", notes = "This API used for adding new movie")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 400, message = "Bad Request"), @ApiResponse(code = 405, message = "Method Not Allowed"),
			@ApiResponse(code = 401, message = "Unauthorized"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@RequestMapping(method = RequestMethod.POST, value = "/add-movie", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDTO<MovieDTO>> addMovie(
			@ApiParam(value = "Valid access token should passed") @RequestHeader(value = "token") String accessToken,
			@RequestBody MovieDTO movieDTO) {

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

	@ApiOperation(value = "Get All Movies", notes = "This API used for get all the movies in the system")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 400, message = "Bad Request"), @ApiResponse(code = 405, message = "Method Not Allowed"),
			@ApiResponse(code = 401, message = "Unauthorized"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@RequestMapping(method = RequestMethod.GET, value = "/all-movies")
	public ResponseEntity<List<MovieDTO>> getAllMoives(
			@ApiParam(value = "Valid access token should passed") @RequestHeader(value = "token") String accessToken) {
		logger.info("getAllMoives:");
		return new ResponseEntity<List<MovieDTO>>(tbService.getMovies(), HttpStatus.OK);
	}

	@ApiOperation(value = "Search booking tickets by userId", notes = "This API used for search booking ticekts")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 400, message = "Bad Request"), @ApiResponse(code = 405, message = "Method Not Allowed"),
			@ApiResponse(code = 401, message = "Unauthorized"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@RequestMapping(method = RequestMethod.GET, value = "/user-tickets")
	public ResponseEntity<List<TicketDTO>> getTicketByUserId(
			@ApiParam(value = "Valid access token should passed") @RequestHeader(value = "token") String accessToken,
			@RequestParam(value = "userId") String userId) {
		logger.info("getTicketByUserId:");
		return new ResponseEntity<List<TicketDTO>>(tbService.getTicketByUserId(userId), HttpStatus.OK);
	}

}
