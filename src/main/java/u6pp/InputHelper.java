package u6pp;

import java.util.Scanner;

public class InputHelper 
{
	private Scanner scanner;
	public InputHelper(Scanner scanner)
	{
		this.scanner = scanner;
	}
	// String in takes in and stores the input so that it can be used later
	// Use an if statement to verify the input and ensure that the initial letters
	// ,stored in doop, is y Y n or N
	public boolean getYesNoInput(String prompt, String checked, String pilk)
	{
		String in;
		String doop;
		boolean done = false;
		while (done == false)
		{
			System.out.print(prompt);
			in = this.scanner.nextLine();
			if (in.length() > 0)
			{
				doop = in.substring(0, 1);
				if (doop.equalsIgnoreCase(checked))
				{
					done = true;
					return true;
				}
				else if (doop.equalsIgnoreCase(pilk))
				{
					done = true;
					return false;
				}
				else
				{
					System.out.println("Invalid input, try again");
					done = false;
					
				}
			}
			else
			{
				System.out.println("Invalid input, try again");
				done = false;
			}
		}
		return false;
	}
	// Getintegerinput should repeat the patterns seen in getyesorno
	// utilize parse and a while loop to create the loop needed to repeat the 
	// prompt check if empty via .equals("") or == ""
	public int getIntegerInput (String prompt, int min, int max)
	{
		boolean done = false;
		String pro;
		while (done == false)
		{
			System.out.println(prompt);
			pro = this.scanner.next();
			if (pro != "")
			{
				if (checker(pro))
				{
					if (Integer.parseInt(pro) < min)
					{
						System.out.print("Invalid input - too low. Please try again.");
						done = false;
					}
					else
					{
						if (Integer.parseInt(pro) > max)
						{
							System.out.print("Invalid input - too high. Please try again.");
							done = false;
						}
						else
						{
							return Integer.parseInt(pro);
						}
					}
				}
				else
				{
					System.out.print("Invalid input - not a number. Please try again.");
					done = false;
				}
			}
			else
			{
				System.out.print("Invalid input - empty line. Please try again.");
				done = false;
			}
		}
		return 0;
	}
	// Private method to make sure that the input at least has
	// an int so that it wont create an issue when it has to be parsed;
	private boolean checker(String s)
	{
		int count = 0;
		String c;
		for (int p = 0; p < 10; p++)
		{
			c = "" + p;
			if (s.contains(c))
			{
				count++;
			}
		}
		if (count >= 1)
		{
			return true;
		}
		else 
		{
			return false;
		}
	}
}
