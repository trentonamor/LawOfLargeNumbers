import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Random;

public class Main {

	public static void main(String[] args) {
		Deck deck = new Deck();

		// Code used to output data to Excel

		PrintWriter outputStream = null;
		try {
			outputStream = new PrintWriter(new FileOutputStream("Data.csv"));
		} catch (FileNotFoundException e) {
			System.out.println("Error opening the file Data.csv");
			System.exit(0);
		}

		outputStream.println("n," + "Sequence");

		int prev = -1;
		int percent = 0;
		int aceCounter = 0;
		int maxAce = 0;
		Card current = deck.peek();
		Card previous = deck.peek();
		Random rng = new Random();
		int status = -1;
		int previousStatus = -1;
		int headCounter = 0;
		int maxHead = 0;
		// Excel has a max of 1 million rows and 16000 columns but the file was too big
		// to read
		// So I scaled down by a factor of 100
		// This loop draws 100000 sequences of cards/coins
		long startTime = System.currentTimeMillis();
		for (int i = 1; i <= 100000; i++) {
			percent = (int) (((double) i / 100000) * 100);
			if (percent != prev) {
				System.out.println(percent + "%");
				prev = percent;
			}
			if (i == 1) {
				outputStream.print(i + ",");
			} else {
				outputStream.print("\n");
				outputStream.print(i + ",");
			}
			// This loop draws a sequence of 1600 cards or 1600 coin flips
			for (int j = 0; j < 1600; j++) {
				// Uncomment for Deck shuffle/ comment for coin flip
				previous = current;

				deck.newDeck();
				deck.shuffleDeck();
				current = deck.drawCard();
				outputStream.print(current.printCard() + ",");
				if (current.getValue() == previous.getValue() && current.getSuit().equals(previous.getSuit())) {
					if (current.getSuit().equals("Spades") && current.getValue() == 1) {
						aceCounter++;
					} else {
						aceCounter = 0;
					}
				}
				if (aceCounter > maxAce) {
					maxAce = aceCounter;
				}

				// Comment for Deck Shuffle/ Uncomment for coin flip
				/*
				 * previousStatus = status; status = rng.nextInt(2); //Produces either 0 or 1
				 * outputStream.print(status + ","); if(previousStatus == status && status == 1)
				 * { headCounter++; } else { headCounter = 0; } } if(headCounter > maxHead) {
				 * maxHead = headCounter; }
				 */
			}
		}
		long endTime = System.currentTimeMillis();

		outputStream.close();
		System.out.println(maxAce);
		System.out.println("Run time: " + ((endTime - startTime) * 0.001) / 60 + " minutes");

		// deck.printDeck();
	}

}
