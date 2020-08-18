package cardDealer;


public class Card {


private int randomSuit, randomFace, suitSetter, faceSetter;
private String suit, face, card;
private final int maxFace=13, maxSuit=4;

	
public Card()
	{
	suitSetter=0;
	suitSetter=0;
		
		
	}
public String deal () //deals a random card. But assumes that deck is always full (cards can repeat)
{
	//************************************** calculates SUIT
	randomSuit = (int)(Math.random()*maxSuit+1);
	switch (randomSuit%4)
	{
	case 0:
		suit = "Hearts";
		break;
	case 1:
		suit = "Diamonds";
		break;
	case 2:
		suit = "Clubs";
		break;
	default:
		suit = "Spades";	
	}
	//***************************************** calculates FACE
	randomFace= (int)(Math.random()*maxFace+1);
	
	switch(randomFace%14)		//%14 will always give 1-13 because max is 13
	{
	case 1:
		face = "Ace";
		break;
	case 11:
		face = "Jack";
		break;
	case 12:
		face = "Queen";
		break;
	case 13:
		face = "King";
	default:
		if (randomFace==2)
			face = "Two";
		if (randomFace==3)
			face = "Three";
		if (randomFace==4)
			face = "Four";
		if (randomFace==5)
			face = "Five";
		if (randomFace==6)
			face = "Six";
		if (randomFace==7)
			face = "Seven";
		if (randomFace==8)
			face = "Eight";
		if (randomFace==9)
			face = "Nine";
		if (randomFace==10)
			face = "Ten";	
	}
	card = face+" of "+suit;
	return card;
}

public String getSuit ()
{
	return suit;
}
public String getFace ()
{
	return face;
}
public void setSuit(String suit)
{
	this.suit=suit;
}
public void setFace(String face)
{
	this.face=face;
}
public void setCard (int suit, int face)
{
	if(face<=maxFace && face>0 && suit>0 && suit<=maxSuit)
	{	faceSetter = face;
		suitSetter = suit;
	switch (suitSetter)
	{
	case 1:
		this.suit = "Hearts";
		break;
	case 2:
		this.suit = "Diamonds";
		break;
	case 3:
		this.suit = "Clubs";
		break;
	default:
		this.suit = "Spades";
	}
	
	switch(faceSetter)		//%14 will always give 1-13 because max is 13
	{
	case 1:
		this.face = "Ace";
		break;
	case 11:
		this.face = "Jack";
		break;
	case 12:
		this.face = "Queen";
		break;
	case 13:
		this.face = "King";
	default:
		if (faceSetter==2)
			this.face = "Two";
		if (faceSetter==3)
			this.face = "Three";
		if (faceSetter==4)
			this.face = "Four";
		if (faceSetter==5)
			this.face = "Five";
		if (faceSetter==6)
			this.face = "Six";
		if (faceSetter==7)
			this.face = "Seven";
		if (faceSetter==8)
			this.face = "Eight";
		if (faceSetter==9)
			this.face = "Nine";
		if (faceSetter==10)
			this.face = "Ten";	
	}
	}//<---end of first IF statement
}
public String getCard()
{
	return face+" of "+suit;
}
public String toString()
{
	return face+" of "+suit;
	//return card;
}
}
