package com.suhas.springboot.datetime;

import java.util.Scanner;

public class NumberDivision {

	public int divide(int numerator, int denominator) {
		boolean negResult = false;

		// Handling negative numbers
		if (numerator < 0) {
			numerator = -numerator;
			if (denominator < 0)
				denominator = -denominator;
			else
				negResult = true;
		} else if (denominator < 0) {
			denominator = -denominator;
			negResult = true;
		}

		int quotient = 0;
		while (numerator >= denominator) {
			numerator = numerator - denominator;
			quotient++;
		}

		if (negResult)
			quotient = -quotient;
		System.out.println("Reminder is :" + numerator);
		return quotient;
	}

	public static void main(String args[]) {
		Scanner sc=new Scanner(System.in); 
		NumberDivision numberDivision = new NumberDivision();
		System.out.println("Enter numerator ");
		int numerator = sc.nextInt();
		System.out.println("Enter denominator ");
		int denominator = sc.nextInt();
		System.out.println("Result...! ");
		int quotient = numberDivision.divide(numerator, denominator);
		System.out.println("Quotient is " + quotient);
	}

}
