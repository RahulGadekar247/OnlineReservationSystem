package com.Impl;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.model.Ticket;
import com.service.MethodService;
import com.user.ReservationDatabase;
import com.user.ReservationSystem;

public class MethodServiceImpl implements MethodService {

    @Override
    public void bookticket() {
        Session session = ReservationDatabase.getSessionFactory().openSession();
        Scanner sc = new Scanner(System.in);

        Ticket ticket = new Ticket();
        Transaction tx= session.beginTransaction();

        System.out.print("Enter Your name : ");
        ticket.setName(sc.nextLine());

        int trainNumber = 0;
        boolean validInput = false;
        while (!validInput) {
            try {
                System.out.print("Enter Your trainNumber : ");
                trainNumber = sc.nextInt();
                validInput = true;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                sc.nextLine(); 
            }
        }
        ticket.setTrainNumber(trainNumber);
       
        System.out.print("Enter class type: ");
        ticket.setClassType(sc.next());

        System.out.print("Enter date of journey: ");
        ticket.setDateOfJourney(sc.next());

        System.out.print("Enter source station: ");
        ticket.setSourceStation(sc.next());

        System.out.print("Enter destination station: ");
        ticket.setDestinationStation(sc.next());

        Random random = new Random();
        long  pnrNumber = random.nextInt(900000) + 100000;
        ticket.setPnrNumber(pnrNumber);

        session.save(ticket);
        tx.commit();
       

        System.out.println("Ticket booked successfully. Your PNR number is " + pnrNumber);
    }

    @Override
    public void cancelticket() {
        Session session = ReservationDatabase.getSessionFactory().openSession();
        Scanner scanner = new Scanner(System.in);
        Transaction tx = session.beginTransaction();

        try {
            System.out.print("Enter your Ticket Number: ");
            int ticketNumber = scanner.nextInt();

            Ticket ticket = session.get(Ticket.class, ticketNumber);
            if (ticket != null) {
                System.out.println("Ticket details: ");
                System.out.println("Ticket Number: " + ticket.getTicketNumber());
                System.out.println("PNR number: " + ticket.getPnrNumber());
                System.out.println("Train number: " + ticket.getTrainNumber());
                System.out.println("Source station: " + ticket.getSourceStation());
                System.out.println("Destination station: " + ticket.getDestinationStation());
                System.out.println("Class type: " + ticket.getClassType());
                System.out.println("Date of journey: " + ticket.getDateOfJourney());
                System.out.println("Passenger name: " + ticket.getName());

                System.out.print("Are you sure you want to cancel this ticket? (Y/N): ");
                String choice = scanner.next();
                if (choice.equalsIgnoreCase("Y")) {
                    session.delete(ticket);
                    tx.commit();
                    System.out.println("Ticket cancelled successfully.");
                } else {
                    System.out.println("Ticket not cancelled.");
                }
            } else {
                System.out.println("No ticket found with Ticket Number " + ticketNumber);
            }
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }


    
    public void Showticket() {
        Session session = ReservationDatabase.getSessionFactory().openSession();
        Scanner sc2 = new Scanner(System.in);

        System.out.print("Enter your PNR number: ");
        long pnrNumber = sc2.nextLong();

        Query<Ticket> query = session.createQuery("FROM Ticket t WHERE t.pnrNumber = :pnrNumber", Ticket.class);
        query.setParameter("pnrNumber", pnrNumber);
        List<Ticket> tc = query.list();

        if (!tc.isEmpty()) {
            Ticket ticket = tc.get(0);
            System.out.println("Ticket details: ");
            System.out.println("Ticket Number: " + ticket.getTicketNumber());
            System.out.println("PNR number: " + ticket.getPnrNumber());
            System.out.println("Train number: " + ticket.getTrainNumber());
            System.out.println("Source station: " + ticket.getSourceStation());
            System.out.println("Destination station: " + ticket.getDestinationStation());
            System.out.println("Class type: " + ticket.getClassType());
            System.out.println("Date of journey: " + ticket.getDateOfJourney());
            System.out.println("Passenger name: " + ticket.getName());
        } else {
            System.out.println("No ticket found with PNR number " + pnrNumber);
        }
        session.close();
    }
    
    
    
    
    public void login() {
	    Scanner sc3 = new Scanner(System.in);
	    ReservationSystem reservationSystem = new ReservationSystem();
	    System.out.print("Enter login ID: ");
	    String loginId = sc3.next();
	    System.out.print("Enter password: ");
	    String password = sc3.next();
	    if (reservationSystem.isValidUser(loginId, password)) {
	        System.out.println("Login successful");
	        reservationMenu();
	    } else {
	        System.out.println(" Its a Testing Login ID & Password");
	    }
	}
	
	private void reservationMenu() {
	    Scanner sc4 = new Scanner(System.in);
	    MethodServiceImpl methodService = new MethodServiceImpl();
	    Ticket ticket = new Ticket();
	    System.out.println("1. Book Ticket");
	    System.out.println("2. Cancel Ticket");
	    System.out.println("3. Show Ticket");
	    System.out.println("4. Exit");
	    System.out.print("Enter your choice: ");
	    int choice = sc4.nextInt();
	    switch (choice) {
	        case 1:
	            methodService.bookticket();
	            reservationMenu();
	            break;
	        case 2:
	            methodService.cancelticket();
	            reservationMenu();
	            break;
	        case 3:
	            methodService.Showticket();
	            reservationMenu();
	            break;
	        case 4:
	            System.exit(0);
	            break;
	        default:
	            System.out.println("Invalid choice. Please enter a valid choice.");
	            reservationMenu();
	            break;
	    }
	}

}



