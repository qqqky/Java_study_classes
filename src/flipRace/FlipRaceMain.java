package flipRace;

/**
 * --------------------------------------------------------------------
 * Program flips 2 coins till one of them flips HEADS x times in a row 
 * --------------------------------------------------------------------
 *
 */

public class FlipRaceMain
{
/**
 * @param GAME - number of flips in a row to win
 */

 public static void main (String[] args)
 {
 
	 int GAME = 6;					//final number for times in a row
	 Coin coin1 = new Coin();		//do not set higher than 20
	 Coin coin2 = new Coin();		//GAME = 20 takes about 1mil flips to complete
	 int flipCount = 0;
	 int Coin1isHeads = 0;
	 int Coin2isHeads = 0;
	 
	 
 
 while (Coin1isHeads<GAME && Coin2isHeads<GAME)
	 {
	 	coin1.flip();
	 	coin2.flip();
	 
	 	System.out.println ("Coin1: "+coin1);
	 	System.out.println ("Coin2: "+coin2);
	 	System.out.println ("");
	 		if (coin1.isHeads())
	 			Coin1isHeads = Coin1isHeads+1;
	 			else 
	 				Coin1isHeads=0;				//resets number to 0 if HEADS aren't flipped in a row
	 		if (coin2.isHeads())
	 			Coin2isHeads = Coin2isHeads+1;
	 			else 
	 				Coin2isHeads=0;
	 	
	 		flipCount = flipCount+1;
	 }
 System.out.println ("Total flips for each coin: " + (flipCount));
 
 if (Coin1isHeads==GAME && Coin2isHeads!=GAME)
	 System.out.println ("Coin1 won!");
 else
 	{if (Coin1isHeads!=GAME && Coin2isHeads==GAME)
	 System.out.println ("Coin2 won!");
 	else
	 System.out.println ("It's a draw!!!!!!!!!!!");
 	}
 }
}