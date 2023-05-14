package com.user;

import java.util.Scanner;

import com.Impl.MethodServiceImpl;




public class Operation {

	
	private static Scanner sc = new Scanner(System.in);
	private static ReservationSystem  reservationsystem = new ReservationSystem();
	
	public static void main(String[] args) {
		MethodServiceImpl ms = new MethodServiceImpl();
		boolean exit = false;
		while (!exit) {
			System.out.println("Welcome to the Online Reservation System");
			System.out.println("1. Login");
			System.out.println("0. Exit");
			System.out.print("Enter your choice: ");
			int choice = sc.nextInt();
			switch (choice) {
			case 1:
				ms.login();
				reservationMenu();
				break;
			case 0:
				exit = true;
				break;
			default:
				System.out.println("Invalid choice");
				break;
		}
		}
	}

	private static void reservationMenu() {
		MethodServiceImpl ms = new MethodServiceImpl();

		boolean exit = false;
		while (!exit) {
			System.out.println("1. Book Ticket");
			System.out.println("2. Cancel  Ticket");
			System.out.println("3. Show Ticket");
			
			System.out.println("4. Logout");
			System.out.print("Enter your choice: ");
			int choice = sc.nextInt();
			switch (choice) {
			case 1:
				ms.bookticket();
				break;
			case 2:
				ms.cancelticket();
				break;
			case 3:
				ms.Showticket();
				break;
			case 4:
				exit = true;
				break;
			default:
				System.out.println("Invalid choice");
				break;
			}
		}
	}
}
        

