package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.Set;

import entities.Sale;

public class Program {

	public static void main(String[] args) {

		Locale.setDefault(Locale.US);
		Scanner scanner = new Scanner(System.in);

		System.out.print("Enter full file path: ");
		String path = scanner.nextLine();

		try (BufferedReader br = new BufferedReader(new FileReader(path))) {

			List<Sale> listSale = new ArrayList<>();

			String line = br.readLine();

			while (line != null) {
				String[] fields = line.split(",");
				listSale.add(new Sale(Integer.parseInt(fields[0]), Integer.parseInt(fields[1]), fields[2],
						Integer.parseInt(fields[3]), Double.parseDouble(fields[4])));
				line = br.readLine();
			}

			Set<String> set = new HashSet<>();

			System.out.println();
			System.out.println("Total de vendas por vendedor: ");

			listSale.forEach(list -> set.add(list.getSeller()));

			for (String names : set) {
				Double total = listSale.stream().filter(sale -> sale.getSeller().equals(names))
						.map(sale -> sale.getTotal()).reduce(0.0, (x, y) -> x + y);
				System.out.println(names + " - R$ " + String.format("%.2f", total));
			}
			
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
		scanner.close();

	}

}