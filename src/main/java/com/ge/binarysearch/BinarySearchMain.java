package com.ge.binarysearch;

import java.util.Scanner;

public class BinarySearchMain {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		BinarySearchTreeOperations binarySearchTreeOperations = new BinarySearchTreeOperations();
		System.out.println("Binary Search Tree Opearations\n");
		char ch;
		do {
			System.out.println("\nBinary Search Tree Operations\n");
			System.out.println("1. insert ");
			System.out.println("2. search");

			int choice = scan.nextInt();
			switch (choice) {
			case 1:
				System.out.println("Enter element to insert");
				binarySearchTreeOperations.insert(scan.nextInt());
				break;
			case 2:
				System.out.println("Enter element to search");
				System.out.println("Search result : " + binarySearchTreeOperations.search(scan.nextInt()));
				break;
			default:
				System.out.println("Wrong Entry \n ");
				break;
			}
			System.out.print("\nExisting elements are : ");
			binarySearchTreeOperations.display();
			System.out.println("\n Do you want to continue (Type y or n)\n");
			ch = scan.next().charAt(0);
		} while (ch == 'Y' || ch == 'y');
	}
}