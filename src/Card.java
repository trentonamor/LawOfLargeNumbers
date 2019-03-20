
public class Card {
	private String suit;
	private int value;
	
	public Card(String suit, int v) {
		this.suit = suit;
		value = v;
	}
	
	public String getSuit() {
		return suit;
	}
	
	public int getValue() {
		return value;
	}
	
	public String printCard() {
		String v;
		switch(value) {
		case 1:
			v = "Ace";
			break;
		case 11:
			v = "Jack";
			break;
		case 12:
			v = "Queen";
			break;
		case 13:
			v = "King";
			break;
			default:
				v = Integer.toString(value);
		}
		return (v + " of " + suit);
	}

}
