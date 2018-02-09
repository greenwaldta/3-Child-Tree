package csci204;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;
/**
 * Driver class reads in values from a text file and calls the Tree 
 * class for each value to place each value into the tree
 * 
 * @author Tobin Greenwald
 * 
 *
 */
public class Driver {

	public static void main(String[] args) throws FileNotFoundException {
		// Scanner for file input of values.
		Scanner sFile = new Scanner(System.in);

		System.out.println("Enter the name of a file.");
		
		String inputFile = sFile.nextLine();

		// Try to open file.
		sFile = new Scanner(new File(inputFile));
		// Holds values read
		int maxChild = sFile.nextInt();
		sFile.nextLine();
		Tree<Integer> values = new Tree<Integer>(maxChild);
		// Read in all value in file.
		while (sFile.hasNextInt()) {
			Object value = sFile.nextInt();
			System.out.println("Adding: " + value);
			if (values.add((Integer) value)) {
				System.out.println("Succeeded adding " + value);
			}

		}
		System.out.println("\n" + "Begin Testing..." + "\n");
		System.out.println("Printing tree...");
		System.out.println(values.toString());
		System.out.println("Was value 45 added?");
		System.out.println(values.add(45) + "\n");
		System.out.println("Printing tree with added index 45...");
		System.out.println(values.toString());
		System.out.println("Finding value 20 in tree...");
		System.out.println("Value " + values.find(20) + " was found.");

	}
}
