package crc_1;
// import java.io.*;
import java.util.Scanner;

public class crc {
	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);

		//Taking input
		System.out.println("Enter a message in bits: ");
		String message = in.nextLine();
		System.out.println("Enter the generator bits: ");
		String generator = in.nextLine();
		int data[] = new int[message.length() + generator.length() - 1]; //data is length of message + (generator -1) 0's added to it
		int divisor[] = new int[generator.length()];

		//Initialisation of data and divisor
		for (int i = 0; i < message.length(); i++) {
			//charAt returns a character, parseInt only accepts Strings, so we convert a character to a string by appending it with ""
			data[i] = Integer.parseInt(message.charAt(i) + "");
		}

		for (int i = 0; i < generator.length(); i++) {
			divisor[i] = Integer.parseInt(generator.charAt(i) + "");
		}

		//Calculation of CRC
		//Note we use message.length() instead of data.length, because doing so would cause the array to go out of bounds
		//Also lookup how division occurs, we dont loop for every bit in the number, just (length of number - length of divisor + 1)times
		for (int i = 0; i < message.length(); i++) {
			if (data[i] == 1) {
				for (int j = 0; j < divisor.length; j++) {
					data[i + j] ^= divisor[j];
				}
			}
		}

		//Display checksum
		for (int i = 0; i < message.length(); i++) {
			data[i] = Integer.parseInt(message.charAt(i) + "");
		}
		
		System.out.println("The checksum is: ");
		for (int i = 0; i < data.length; i++) {
			System.out.print(data[i]);
		}
		System.out.println();
		//Reciever side
		System.out.println("Enter the checksum recieved: ");
		String checksum = in.nextLine();

		System.out.println("Enter the generator bits: ");
		generator = in.nextLine();

		//Initialise data and divisor again
		for (int i = 0; i < data.length; i++) {
			data[i] = Integer.parseInt(checksum.charAt(i) + "");
		}
		
		for (int i = 0; i < generator.length(); i++) {
			divisor[i] = Integer.parseInt(generator.charAt(i) + "");
		}

		//Check validity of CRC
		boolean valid = true;
		for (int i = 0; i < message.length(); i++) {
			if (data[i] == 1) {
				for (int j = 0; j < generator.length(); j++) {
					data[i + j] ^= divisor[j];
				}
			}
		}

		for (int i = 0; i < data.length; i++) {
			if (data[i] == 1) {
				valid = false;
			}
		}

		if (valid) {
			System.out.println("The given data is correct");
		} else {
			System.out.println("CRC error has occured");
		}

		in.close();
	}
}