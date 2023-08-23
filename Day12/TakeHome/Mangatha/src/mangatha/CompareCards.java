package mangatha;

import java.util.Comparator;

public class CompareCards implements Comparator<Card>{

	@Override
	public int compare(Card arg0, Card arg1) {
		if (arg0.getRank() == arg1.getRank() && (Integer.compare(arg0.getSuit(), arg1.getSuit()) == 0))
			return 1;
		else
			return 0;
	}

}
