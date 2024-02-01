package u6pp;
class Deck
{
	// establish all of the cards induvidually and then put them into an array labeleed deck to store for use in methods :D
	private int length = 0;
	private int state = 0;
	private static Card[] deck = new Card[52];
	public Deck()
	{
		int lep = 0;
		for (int x = 0; x<= Card.SUITS.length -1; x++)
		{
			for (int y = 0; y <= Card.VALUES.length -1; y++)
			{
				deck[lep] = new Card(Card.SUITS[x], Card.VALUES[y]);
				lep += 1;
			}
		}
		this.length = 52;
		this.state = 0;
	}
	// REturn the variable length to represent the deck's length rather than actually changing the array itself 
	public int numLeft() 
	{
		return length;
	}
	// make sure to keep state within the possibilities to avoid errors and bugs
	public Card deal()
	{
		int pop = state;
		if (state < 51)
		{
			state += 1;
		}
		if (length > 0)
		{
			length -= 1;
		}
		return deck[pop];
	}
	// randomize via a random switching method which just swaps pairs of cards via temp variables which hold them.
	public void shuffle()
	{
		this.state = 0;
		this.length = 52;
		int pool = 0;
		for (int x = 0; x < deck.length; x ++)
		{
			Card temp = deck[x];
			pool = (int) (Math.random() * 52);
			Card gek = deck[pool];
			deck[x] = gek;
			deck[pool] = temp;
		}
	}
}