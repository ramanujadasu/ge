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

import com.ge.model.Ticket;
import com.ge.repository.TBRepository;

public class TicketBooking implements Runnable {

	private static final Logger logger = LoggerFactory.getLogger(TicketBooking.class);

	@Autowired
	TBRepository tBRepository;

	@Override
	public void run() {

		TicketThread ticketThread = (TicketThread) Thread.currentThread();
		String bookingTicketId = this.bookingTicket(ticketThread.getTicket());
		if (bookingTicketId != null) {
			logger.info("CONGRATS" + Thread.currentThread().getName() + " .. The number of seats requested("
					+ ticketThread.getTicket().getSeats().size() + ")  are BOOKED");
		} else {
			logger.info("Sorry Mr." + Thread.currentThread().getName() + " .. The number of seats requested("
					+ ticketThread.getTicket().getSeats().size() + ")  are not available");
		}
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

}
