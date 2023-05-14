package com.service;

import com.model.Ticket;

public interface MethodService {
	
	static void login( ) {
		
		
	}
	void bookticket();
    void cancelticket();
	void Showticket();
	static Ticket getTicketByPNR(long pnrNumber) {
		Ticket t = new Ticket();
		
		return t;
	}

}
