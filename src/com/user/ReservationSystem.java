package com.user;

import java.util.HashMap;
import java.util.Map;

import com.model.Ticket;

public class ReservationSystem {

    private Map<String, String> users = new HashMap<>();
    private Map<String, Ticket> tickets = new HashMap<>();
    private int pnrCounter = 1;

    public ReservationSystem() {
        users.put("admin", "password");
    }

    public boolean isValidUser(String loginId, String password) {
        String storedPassword = users.get(loginId);
        return storedPassword != null && storedPassword.equals(password);
    }

    	public String bookTicket(String name, int trainNumber, String classType, String dateOfJourney, String sourceStation, String destinationStation) {
        String pnrNumber = generatePNRNumber();
        Ticket ticket = new Ticket();
        tickets.put(pnrNumber, ticket);
        return pnrNumber;
    }

    public boolean cancelTicket(String pnrNumber) {
        Ticket ticket = tickets.get(pnrNumber);
        if (ticket == null) {
            return false;
        }
        tickets.remove(pnrNumber);
        return true;
    }

    private String generatePNRNumber() {
        return String.format("%06d", pnrCounter++);
    }

	public void addUserCredentials(String username, String password) {
		// TODO Auto-generated method stub
		
	}

	public boolean isUsernameTaken(String username) {
		// TODO Auto-generated method stub
		return false;
	}
}
