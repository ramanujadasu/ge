package com.ge.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ge.dto.ResponseDTO;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class TokenController {
	private final static Logger logger = LoggerFactory.getLogger(TokenController.class);

	@ApiOperation(value = "Generate Token", notes = "This API used for generating token")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 400, message = "Bad Request"), @ApiResponse(code = 405, message = "Method Not Allowed"),
			@ApiResponse(code = 401, message = "Unauthorized"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@RequestMapping(method = RequestMethod.GET, value = "/token")
	public ResponseEntity<ResponseDTO<String>> getToken() {
		logger.info("getToken");
		ResponseDTO<String> responeDto = null;
		Date exp = null;
		Integer tokenExpirationHours = 2;
		// hours logic
		Date currentDate = new Date(); // oldDate == current time
		logger.info("before : {}, tokenExpirationHours:{}", currentDate.getTime(), tokenExpirationHours);
		final long hoursInMillis = 60L * 60L * 1000L;
		exp = new Date(currentDate.getTime() + (Long.valueOf(tokenExpirationHours) * hoursInMillis));
		logger.info("tokenExpirationHours: after :{} ", exp.getTime());

		String jwtToken = Jwts.builder().setSubject("ge").setExpiration(exp).claim("roles", "user")
				.setIssuedAt(new Date()).signWith(SignatureAlgorithm.HS256, "secretkey").compact();

		logger.info("responeDto: {}", responeDto);
		responeDto = new ResponseDTO<String>();
		responeDto.setMessage("Token generated successfully.");
		responeDto.setResponseData(jwtToken);
		return new ResponseEntity<ResponseDTO<String>>(responeDto, HttpStatus.OK);
	}
}
