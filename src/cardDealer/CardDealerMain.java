package cardDealer;
/* Create a class called DeckOfCards that stores 52 objects of the Card class. 
 * Include methods to shuffle the deck, deal a card, and report the number of 
 * cards left in the deck. The shuffle method should assume a full deck. 
 * Create a driver class with a main method that deals each card from a 
 * shuffled deck, printing each card as it is dealt.*/

/* Program first shuffles a new deck of cards and deals all of them,
 * then unshuffles the deck and deals 10 cards from unshuffled deck. */

public class CardDealerMain
{
	

	public static void main(String[]args)
	{
		
		DeckOfCards myDeck = new DeckOfCards();
		System.out.println("A new deck of cards consists of: \n"+myDeck);
		System.out.println();
		
		myDeck.shuffle();
		System.out.println("After shuffling: \n"+myDeck);
		System.out.println();
		
		//myDeck.unshuffle();
		//System.out.println("After unshuffling again: \n"+myDeck);
		
		//tries dealing cards from shuffled deck from top (more than total amount)
		System.out.println("Dealing cards from shuffled deck from top: ");
		System.out.println();
		for (int x=0;x<53;x++)
			System.out.println(myDeck.deal());
		
		System.out.println();
		//deals some cards from unshuffled deck
		myDeck.unshuffle();
		System.out.println("Unshuffles deck again and deals 10 cards from top: ");
		System.out.println();
		for (int x=0;x<10;x++)
			System.out.println(myDeck.deal());
	}
}



