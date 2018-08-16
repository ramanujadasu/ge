package com.ge.service;

import com.ge.model.Ticket;

public class TicketThread extends Thread {

	private Ticket ticket;

	public TicketThread(Ticket ticket, Runnable target, String name) {
		super(target, name);
		this.ticket = ticket;
	}

	public Ticket getTicket() {
		return ticket;
	}

}
