package cardDealer;

public class DeckOfCards {

private Card[][] deck;	
private Card[] tempDeck, tempDeck2; //<--- array of single cards
private int[][]randomCards; //2D array for randomizing cards (to make sure only 1 of each is picked)
private int counter = 0, counter2=0;	

public DeckOfCards()
	{
		
		
		deck = new Card[4][13];
		
		// first loop cycles through suit, second - through face of card
		// cards are set in order by creating new card object, using setCard method
		// and adding to 2D deck array
		for (int x=0; x<deck.length;x++)
			{
			for(int y=0; y<deck[0].length;y++)
				{
				Card xx = new Card ();
				xx.setCard(x+1, y+1); //<--- lowest value must be 1, not 0, so +1
				deck[x][y]=xx;	//card is added to deck
				//System.out.println(deck[x][y]); //<-- for error checking
				}
			}
		
		
		
	}
	public void shuffle()
	{
		counter2=0; //to reset "deal" method if cards are reshuffled
		
	//***************************************************************
		//***firstly 2D array is sorted into normal array [0-51];
	//***************************************************************
		tempDeck = new Card[52];
		
		for(int x=0;x<deck.length;x++)
		{
			for(int y=0;y<deck[0].length;y++)
			{
				tempDeck[y+x*13]=deck[x][y];
	//****************************************************************			
				//**** long version with switch: ****
				/*switch(x)
				{
				case 0:
					tempDeck[y]=deck[x][y];
					break;
				case 1:
					tempDeck[y+13*x]=deck[x][y];
					break;
				case 2:
					tempDeck[y+13*x]=deck[x][y];
					break;
				case 3:
					tempDeck[y+13*x]=deck[x][y];
					break;
				default:
					System.out.println("Error");
				}*/
			}
		}
		//for(Card temp: tempDeck)  //<--- can check if temp deck is correct
		//	System.out.println(temp);
		
		//another deck is created, which will be filled with random cards from tempDeck (in which, cards are unshuffled)
		//***********************************
		tempDeck2 = new Card[52];
		//***********************************
		
		
		randomCards = new int[52][2];
		
		//***loads first row with values 0-51 (for visual reference and testing)***
		//for(int x=0; x<randomCards.length;x++)
		//	{
		//	randomCards[x][0]=x;
		//	System.out.print(randomCards[x][0]+"\t");
		//	}
		
		System.out.println();
		//******TESTING if 2nd row is 0 before the cycle*******
		//for(int x=0; x<randomCards.length;x++)
		//	System.out.print(randomCards[x][1]+"\t");
		//System.out.println();
		
		//***repeats cycle till every number is rolled once
		for(int y=0; y<randomCards.length;y++) 	//less cycles would be enough for every number to occur once
			{									//but less cycles = more errors (although 10 is almost always enough)
				while(randomCards[y][1]==0)		
					{
					int randomizer = (int)(Math.random()*52);
						if(randomCards[randomizer][1]==0)	//While loop is returned if random number is not Y
							{	
								randomCards[randomizer][1]+=1;	//but chosen number is saved in randomCards array once (marked as 1)
								tempDeck2[counter]=tempDeck[randomizer];
								counter++;
							}
							
					}
				
			}
		//***checks if all numbers in randomCards[][1] is 1 (occurred once).
		//meaning the most inner loop (up from this text) is executed 52 times (once for every number/card).
		//so to update tempDeck2 from 0 to 51 linearly, we have to introduce counter!!! 
		//for(int x=0; x<randomCards.length;x++)
		//	System.out.print(randomCards[x][1]+"\t");
		
		
		//System.out.println();
		//for(Card n: tempDeck2)
		//	System.out.println(n);
		
		tempDeck = tempDeck2;
		
		//********************reverses array back to original 2D array (as defined by Card class)
		for (int x=0; x<deck.length;x++)
		{
			for(int y=0; y<deck[0].length;y++)
				deck[x][y]=tempDeck[y+x*13];
		}
		
		
	}
	
	public void unshuffle()
	{
		counter2=0;
		
		//**** method copied from constructor****
		for (int x=0; x<deck.length;x++)
		{
		for(int y=0; y<deck[0].length;y++)
			{
			Card xx = new Card ();
			xx.setCard(x+1, y+1); //<--- lowest value must be 1, not 0, so +1
			deck[x][y]=xx;	//card is added to deck
			//System.out.println(deck[x][y]); //<-- for error checking
			}
		}
		//***************************************************************
				//***also sorts into normal array [0-51] for dealing method to work on unshuffled deck;
			//***************************************************************
				tempDeck = new Card[52];
				
				for(int x=0;x<deck.length;x++)
				{
					for(int y=0;y<deck[0].length;y++)
					{
						tempDeck[y+x*13]=deck[x][y];
					}
				}
			//****************************************************************
	}
	
	public String deal() //deals cards one by one from the top
	{
		if(counter2<52)
		{	counter2++;
			return tempDeck[(counter2)-1].getCard()+"     \t - \t"+(52-counter2)+" cards left in the deck";
		}
		else
			return "Error. No cards left in the deck. Please reshuffle";
	}
	
	public String toString()
	{
		String allCards = "";
		for(int x=0;x<deck.length;x++)
		{
			for(int y=0;y<deck[x].length;y++) //<--- if lengths in all arrays are the same, any valid number can be used [0]
			allCards = allCards +"\n"+". "+ deck[x][y];		// if lengths are different - x
		}
			
		
		return allCards;
	}
}
