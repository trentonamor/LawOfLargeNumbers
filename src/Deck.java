import java.util.LinkedList;
import java.util.Random;

public class Deck {
	private LinkedList<Card> deck;

	public Deck() {
		// create Deck
		deck = new LinkedList<Card>();
		createDeck();
	}

	public void printDeck() {
		if (deck.isEmpty()) {
			System.out.println("\n~~~~~~Empty Deck~~~~~~\n");
		}
		for (int i = 51; i >= 0; i--) {
			System.out.println(deck.get(i).printCard());	
		}
	}

	public Card drawCard() {
		return deck.remove();
	}
	
	public Card peek() {
		return deck.peekFirst();
	}

	public void shuffleDeck() {
		LinkedList<Card> shuffled = new LinkedList<Card>();
		Random rng = new Random();
		int rand;
		int counter = 51;
		while(counter > 0) {
			rand = rng.nextInt(counter + 1);
			shuffled.add(deck.remove(rand));
			counter--;
		}
		shuffled.add(deck.remove());
		deck = shuffled;
	}

	public int size() {
		return deck.size();
	}
	
	public void newDeck() {
		deck.clear();
		createDeck();
	}

	private void createDeck() {
		if (!deck.isEmpty()) {
			System.err.println("Deck already exists!\n");
			return;
		}
		byte whichSuit = -1;
		int value;
		String suit = "";
		for (int i = 0; i < 52; i++) {
			value = (i % 13);
			// Check which suit we are doing
			if (value == 0) {
				whichSuit++;
				switch (whichSuit) {
				case 0:
					suit = "Spades";
					break;
				case 1:
					suit = "Diamonds";
					break;
				case 2:
					suit = "Clubs";
					break;
				case 3:
					suit = "Hearts";
					break;
				default:
					System.err.println("Index out of bounds");
					System.exit(0);
				}
			}
			// Add the card to the deck
			Card c = new Card(suit, value + 1);
			deck.add(c);
		}
	}
}
