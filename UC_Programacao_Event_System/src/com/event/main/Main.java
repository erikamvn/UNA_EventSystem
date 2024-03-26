package com.event.main;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import com.event.classes.Event;
import com.event.classes.Events;
import com.event.classes.User;
import com.event.enuns.EventType;
import com.event.fileSystem.FileManipulator;

public class Main {

	private static Scanner scanner = new Scanner(System.in);
	private static User user;
	private static Events eventsList;

	public static void main(String[] args) {
		
		initialize();
		
		if(user == null) {
			user = new User();
			registerUser();
		}
		
		if(eventsList == null) {
			eventsList = new Events();
		}
		
		executeSystem();
	}

	private static void executeSystem() {

		int opcao = 0;

		do {
			System.out.println("Bem-vindo(a) " + user.getName());
			System.out.println();
			System.out.println("-----------------------------------------------");
			System.out.println("                   Menu                        ");
			System.out.println("-----------------------------------------------");
			System.out.println(" 1 - Cadastrar Eventos");
			System.out.println(" 2 - Listar Eventos");
			System.out.println(" 3 - Listar Eventos com presença confirmada");
			System.out.println(" 4 - Listar Eventos que estão se aproximando");
			System.out.println(" 5 - Listar Eventos passados");
			System.out.println(" 6 - Sair");
			System.out.println("-----------------------------------------------");
			System.out.println("Opção:");
			opcao = Integer.parseInt(scanner.nextLine());

			switch (opcao) {
			case 1:
				addEvent();
				break;
			case 2:
				listAllEvents();
				break;
			case 3:
				listConfirmedEvents();
				break;
				
			case 4:
				listNextEvents();
				break;

			case 5:
				listPassedEvents();
				break;
				
			case 6:
				exitSystem();
				break;

			default:
				break;
			}

		} while (opcao != 6);

	}

	private static void registerUser() {
		System.out.println("O sistema não foi incializado. Favor informar as informações:");

		System.out.println("Nome:");
		user.setName(scanner.nextLine());

		System.out.println("E-mail:");
		user.setName(scanner.nextLine());

		System.out.println("Telefone:");
		user.setName(scanner.nextLine());

	}

	private static void addEvent() {

		Event event = new Event();

		System.out.println("Favor informar os dados do evento:");

		System.out.println("Nome:");
		event.setName(scanner.nextLine());

		System.out.println("Endereço:");
		event.setAddress(scanner.nextLine());

		System.out.println("Descrição:");
		event.setDescription(scanner.nextLine());

		System.out.println("Dia (formado yyyy-MM-dd):");
		String date = scanner.nextLine();

		System.out.println("Dia (formado HH:mm:ss):");
		String hour = scanner.nextLine();

		event.setDate(LocalDateTime.parse(date + " " + hour, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

		System.out.println("Informe a categoria:");

		for (EventType type : EventType.values()) {
			System.out.println(type.getType() + " - " + type);
		}

		event.setCategory(scanner.nextLine());

		eventsList.getEvents().add(event);
	}

	private static void listAllEvents() {

		if (eventsList.getEvents().size() > 0) {
			System.out.println("Lista de eventos");

			int count = 1;

			for (Event item : eventsList.getEvents()) {
				System.out.println(count + " - " + item.getDate() + " - " + item.getName() + " - "
						+ (item.isConfirmated() ? "Confirmado" : "Não confirmado"));
				count++;
			}

			System.out.println("Deseja Confirmar algum evento? (s/n)");
			String option = scanner.nextLine().toLowerCase();

			if (option.equals("s")) {
				System.out.println("Informe o código do evento:");
				int code = Integer.parseInt(scanner.nextLine().toLowerCase()) - 1;
				Event newEvent = eventsList.getEvents().get(code);
				newEvent.setConfirmated(true);
				eventsList.getEvents().set(code, newEvent);
			}

		} else {
			System.out.println("Não há eventos cadastrados");
		}

		System.out.println();
		System.out.println();
	}
	
	private static void listConfirmedEvents() {

		if (eventsList.getEvents().size() > 0) {
			System.out.println("Lista de eventos confirmados");

			int count = 1;

			for (Event item : eventsList.getEvents()) {
				if(item.isConfirmated()) {
					System.out.println(count + " - " + item.getDate() + " - " + item.getName());					
				}
				count++;

			}

			System.out.println("Deseja Cancelar algum evento? (s/n)");
			String option = scanner.nextLine().toLowerCase();

			if (option.equals("s")) {
				System.out.println("Informe o código do evento:");
				int code = Integer.parseInt(scanner.nextLine().toLowerCase()) - 1;
				Event newEvent = eventsList.getEvents().get(code);
				newEvent.setConfirmated(false);
				eventsList.getEvents().set(code, newEvent);
			}

		} else {
			System.out.println("Não há eventos confirmados");
		}

		System.out.println();
		System.out.println();
	}
	
	private static void listNextEvents() {
		LocalDateTime now = LocalDateTime.now(); 

		if (eventsList.getEvents().size() > 0) {
			System.out.println("Lista de eventos passados");

			int count = 1;

			for (Event item : eventsList.getEvents()) {
				if(item.getDate().isAfter(now) ) {
					System.out.println(count + " - " + item.getDate() + " - " + item.getName());					
				}
				count++;
			}

		} else {
			System.out.println("Não há eventos passados");
		}

		System.out.println();
		System.out.println();
	}
	
	private static void listPassedEvents() {
		
		LocalDateTime now = LocalDateTime.now(); 

		if (eventsList.getEvents().size() > 0) {
			System.out.println("Lista de eventos passados");

			int count = 1;

			for (Event item : eventsList.getEvents()) {
				if(item.getDate().isBefore(now) ) {
					System.out.println(count + " - " + item.getDate() + " - " + item.getName());					
				}
				count++;
			}

		} else {
			System.out.println("Não há eventos passados");
		}

		System.out.println();
		System.out.println();
	}
	
	public static void exitSystem() {
		FileManipulator.save(user, "c://eventsystem//user.txt");
		FileManipulator.save(eventsList, "c://eventsystem//eventsList.txt");

	}
	
	public static void initialize() {
		user = (User) FileManipulator.read("c://eventsystem//user.txt");
		eventsList = (Events) FileManipulator.read("c://eventsystem//eventsList.txt");

	}

}
