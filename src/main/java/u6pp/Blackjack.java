package u6pp;
import java.util.Scanner;
class Blackjack
{
	// create new arrays for dealer and player to hold copies of their cards which they were "dealt"
	private Card[] Playerhand;
	private Card[] Dealerhand;
	private Deck fleat;
	private String username;
	private Scanner sc = new Scanner(System.in);
	private InputHelper IH = new InputHelper(sc);
	// standard constructor, just set up all the initially needed variables like the deck and hands
	public Blackjack()
	{
		fleat = new Deck();
		Playerhand = new Card[8];
		Dealerhand = new Card[8];
	}
	public void play()
	{
		boolean playing = true;
		boolean won = false;
		boolean stayed = false;
		System.out.print("Welcome to Blackjack! What is your name? ");
		username = sc.nextLine();
		System.out.println("Hello " + username + "! I am Gambletron 5000! Let's play some cards.");
		fleat.shuffle();
		addCard(Playerhand, fleat.deal());
		addCard(Dealerhand, fleat.deal());
		while (playing)
		{
			if (!won)
			{
				System.out.println("Your hand:" + writed(Playerhand));
				System.out.println("Dealer's hand:" + writed(Dealerhand));
				if (isBlackjack(Playerhand))
				{
					System.out.println("Congrats " + username + "you got a Blackjack!");
					System.out.println("Result: " + determineResult(Playerhand, Dealerhand));
					won = true;
				}
				else
				{
					if (!stayed)
					{
						if (IH.getYesNoInput("Would you like to (H)it or (S)tay: ", "h", "s"))
						{
							addCard(Playerhand, fleat.deal());
							if (isBust(Playerhand))
							{
								System.out.println(username + " I'm so sorry you busted!");
								System.out.println("Result: " + determineResult(Playerhand, Dealerhand));
								won = true;
							}
							else
							{
								if (dealerKeepHitting(Dealerhand))
								{
									addCard(Dealerhand, fleat.deal());
									won = false;
								}
							}
						}
						else
						{
							stayed = true;
						}
					}
					else
					{
						if (dealerKeepHitting(Dealerhand))
						{
							addCard(Dealerhand, fleat.deal());
							won = false;
						}
						else
						{
							won = true;
							System.out.println("Result: " + determineResult(Playerhand, Dealerhand));
						}
					}
				}
			} 
			else
			{
				if (IH.getYesNoInput("Would you like to play again? (Y)es/(N)o: ", "y", "n"))
				{
					won = false;
					stayed = false;
					fleat.shuffle();
					Playerhand = new Card[8];
					Dealerhand = new Card[8];
					addCard(Playerhand, fleat.deal());
					addCard(Dealerhand, fleat.deal());
				}
				else
				{
					won = true;
					System.out.print("Thanks for playing " + username + "! Have a great day!");
					playing = false;
				}
			}
		}
		
	}
	// use string.contains to check the value of the card via its tostring method OR use the get vlaue method to check via a for loop or something idk
	public static int calcPoints(Card[] hand)
	{
		int player = 0;
		for (int x = 0; x <= hand.length - 1; x++)
		{
			if (hand[x] == null)
			{
				player +=0;
			}
			else if (hand[x].getValue() == "Ace")
			{
				player += 11;
			}
			else if (hand[x].getValue() == "King" || hand[x].getValue() == "Queen" || hand[x].getValue() == "Jack")
			{
				player += 10;
			}
			else
			{
				player += Integer.valueOf(hand[x].getValue());
			}
		}
		return player;
	}
	// Determine utilizes all of the previously set static variables to actually decide the game, utilize if statements for it
	public static String determineResult(Card[] userHand, Card[] dealerHand)
	{
		int up = calcPoints(userHand);
		int cp = calcPoints(dealerHand);
		if (up == cp)
		{
			return "User Pushes";
		}
		else
		{
			if (up < 21 & cp < 21)
			{
				if (up > cp)
				{
					return "User Wins";
				}
				else
				{
					return "User Loses";
				}
			}
			else
			{
				if (up == 21 || cp == 21)
				{
					if (cp == 21)
					{
						return "User Loses";
					}
					else
					{
						return "User Wins";
					}
				}
				else
				{
					if (up < cp)
					{
						return "User Wins";
					}
					else
					{
						return "User Loses";
					}
				}
			}
		}
	}
	// Utilize the check count to add up all the card values and determine whether they are bust (meaning a hit went over 21)
	public static boolean isBust(Card[] hand)
	{
		int pelp = calcPoints(hand);
		if (pelp > 21)
		{
			return true;
		}
		else 
		{
			return false;
		}
	}
	// Same situation as isbust but checking for if the cards equate to 21, with the special condition that only two cards are in the hand
	public static boolean isBlackjack(Card[] hand)
	{
		int ppp = calcPoints(hand);
		if (ppp == 21 & hand.length == 2)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	// a simple piece of logic like is bust or isblackjack which dictates wether the dealer is to keep hitting
	public static boolean dealerKeepHitting(Card[] hand)
	{
		int pop = calcPoints(hand);
		if (pop <= 16)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	// helper method to simplify and less convolute the system of adding cards to hands
	private static void addCard(Card[] hand, Card c)
	{
		for (int y = 0; y < hand.length -1; y++)
		{
			if (hand[y] == null)
			{
				hand[y] = c;
				y = hand.length + 5;
			}
		}
	}
	// writed is just a tostring method but made to be able to go around null objects in an array.
	private static String writed(Card[] hand)
	{
		String kelp = "";
		for (int x = 0; x <= hand.length -1; x++)
		{
			if (hand[x] != null)
			{
				kelp += " " + hand[x].toString();
			}
		}
		return kelp;
	}
}