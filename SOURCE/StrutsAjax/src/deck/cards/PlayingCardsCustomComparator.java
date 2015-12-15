package deck.cards;

public class PlayingCardsCustomComparator {
	    public boolean compare(PlayingCard object1, PlayingCard object2) {
	        return object1.getCardNumber() <= object2.getCardNumber();
	    }
	}

//	    Collections.sort(Database.arrayList, new customComparator);
	


