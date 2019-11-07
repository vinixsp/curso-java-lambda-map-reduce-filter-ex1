package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

import entities.Employee;

public class Program {

	public static void main(String[] args) {

		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);

		System.out.print("Enter full file path: ");
		String path = sc.nextLine();
		List<Employee> employees = new ArrayList<>();
		
		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
			
			String line = br.readLine();
			
			while (line != null) {
				
				String[] fields = line.split(",");
				String name = fields[0];
				String email = fields[1];
				Double salary = Double.parseDouble(fields[2]);
				
				employees.add(new Employee(name, email, salary));
				
				line = br.readLine();
			}
			
			System.out.print("Enter salary: ");
			Double sal = sc.nextDouble();
			
			System.out.printf("Email of people whose salary is more than %.2f:%n", sal);
			
			employees.stream()
					.filter(e -> e.getSalary() > sal)
					.sorted((e1, e2) -> e1.getEmail().toUpperCase().compareTo(e2.getEmail().toUpperCase()))
					.map(e -> e.getEmail())
					.collect(Collectors.toList())
					.forEach(System.out::println);
			
			System.out.print("Type the initial letter to filter: ");
			sc.nextLine();
			char initial = sc.nextLine().charAt(0);
				
			Double sum = employees.stream()
					.filter(e -> e.getName().charAt(0) == initial)
					.map(e -> e.getSalary())
					.reduce(0d, (a, b) -> a + b);
			
			System.out.printf("Sum of salary of people whose name starts with '%s': %.2f", initial, sum);
				
		}
		catch (IOException e) {
			System.out.println("Erro: " + e.getMessage());
		}
		finally {
			sc.close();
		}
	}

}
