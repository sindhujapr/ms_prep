package interview.google;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * some initial thoughts:
 * if we sort the biddings, it will take O(nlgn) time.
 * we could use DP and for each 
 */
class Bid implements Comparable<Bid> {
    int time;
    double total;
    double average;

    public Bid(int total, int time) {
	this.total = total;
	this.time = time;
	average = total / time;
    }

    @Override
    public int compareTo(Bid o) {
	if (average > o.average)
	    return -1;
	else if (average == o.average) {
	    /*
	     * this block is necessary to avoid the below situation: two
	     * permutations: 8/2=12/3, 6/3=4/2 however, 8+6 < 12+4
	     */
	    if (total > o.total)
		return -1;
	    else if (total == o.total)
		return 0;
	    else
		return 1;
	} else {
	    return 1;
	}
    }

    @Override
    public String toString() {
	StringBuilder sb = new StringBuilder();
	sb.append(average + "\t");
	sb.append(total + "\t");
	sb.append(time + "\t");
	return sb.toString();
    }
}

public class AdBid {
    private final int timeLimit;
    private static final List<Bid> bids = new ArrayList<>();

    static {
	bids.add(new Bid(10, 2));
	bids.add(new Bid(8, 2));
	bids.add(new Bid(9, 3));
	bids.add(new Bid(4, 2));
	bids.add(new Bid(6, 3));
	bids.add(new Bid(7, 7));
	bids.add(new Bid(4, 4));
	bids.add(new Bid(1, 1));
	Collections.sort(bids);

	System.out.println("average\ttotal\ttime");
	for (Bid bid : bids)
	    System.out.println(bid);
    }

    public AdBid(int timeLimit) {
	this.timeLimit = timeLimit;
    }

    /*
     * each advertisement must be played completely. brute force algorithm to
     * find all combination. check KnapSack
     */
    public double find1() {
	if (timeLimit == 0)
	    return 0;

	List<Bid> stack = new ArrayList<>();
	List<Integer> indices = new ArrayList<>();
	int index = 0;
	int time = timeLimit;
	double max = 0;
	int cnt = 0;

	do {
	    while (time > 0 && index < bids.size()) {
		if (time - bids.get(index).time >= 0) {
		    stack.add(bids.get(index));
		    time -= bids.get(index).time;
		    indices.add(index);
		}
		index++;
	    }

	    double sum = 0;
	    StringBuilder sb = new StringBuilder();
	    sb.append("#" + cnt + ": ");
	    for (Bid bid : stack) {
		sum += bid.total;
		sb.append(bid.time + " ");
	    }
	    if (sum > max) {
		max = sum;
		// System.out.format("%-14s", sb.toString());
		// System.out.format("%10s", "sum(" + sum + ")\n");
	    }
	    cnt++;

	    /*
	     * here we need to pop the last element in the stack
	     */
	    time += stack.remove(stack.size() - 1).time;
	    /* very important to increase by 1 */
	    index = indices.remove(indices.size() - 1) + 1;
	} while (stack.size() != 0 || index != bids.size());

	// System.out.println("the best choice: " + max);
	return max;
    }

    /*
     * each advertisement must be played completely. DP: the optimal solution to
     * the problem consists of the optimal solution to the subproblem
     */
    public double find2() {
	if (timeLimit == 0)
	    return 0;

	List<Bid> result = new ArrayList<>();
	int time = 0;
	int index = 0;

	while (time < timeLimit && index < bids.size()) {
	    if (time + bids.get(index).time <= timeLimit) {
		time += bids.get(index).time;
		result.add(bids.get(index));
	    }

	    index++;
	}

	double max = 0;
	for (Bid bid : result) {
	    max += bid.total;
	}

	return max;
    }

    /*
     * each advertisement must be played at least half
     */
    public void find3() {
	/*
	 * we don't have enough room for this bid. Here are two situations to
	 * consider: 1) the selected bids just fit timeLimit. 2) there are room
	 * less than needed, say 1 isn't enough for 3. In this case, we can
	 * still continue
	 */
	Map<Bid, Integer> result = new HashMap<>();
	List<Integer> indices = new ArrayList<>();

	int time = 0;
	int index = 0;
	/*
	 * this is the longest time available, given that all bids are given
	 * half the durations they need
	 */
	int longestTimeAvailable = timeLimit;

	while (time < timeLimit && index < bids.size()) {
	    Bid bid = bids.get(index);
	    if (time + bid.time <= timeLimit) {
		time += bid.time;
		result.put(bid, bid.time);
		indices.add(index);
		longestTimeAvailable -= Math.round((float) bid.time / 2);
	    } else {
		/*
		 * we'll see if we can reduce the time of previously selected
		 * bids and insert at least half of this bid
		 */
		int timeNeeded = (int) Math.round((float) bid.time / 2);
		/*
		 * even with our best effort, this bid needs too long:(
		 */
		if (timeNeeded > longestTimeAvailable) {
		    index++;
		    continue;
		}

		if (isDeserveInsert(result, indices, bid, timeLimit-time)) {

		}
		/*
		 * Evaluate starting from the last selected bid: 1) reduce the
		 * play time of previously selected bids 2) make sure the
		 * benefit is optimal (win>lost)
		 */
		int i = indices.get(indices.size() - 1);
		Bid latestBid = bids.get(i);
		while (timeNeeded > (timeLimit - time) && i >= 0) {
		    int timeSaved = latestBid.time
			    - (int) Math.round((float) latestBid.time / 2);
		    int t = result.get(bids.get(i));
		    result.put(latestBid, t - timeSaved);

		    time -= timeSaved;
		    timeNeeded -= timeSaved;

		    i = indices.get(indices.size() - 1);
		    latestBid = bids.get(i);
		    timeSaved = latestBid.time
			    - Math.round((float) latestBid.time / 2);
		}
		/*
		 * here we're ready to add this bid
		 */
		result.put(null, null);

		if (time == timeLimit) {
		    break;
		}
	    }

	    index++;
	}
    }

    private boolean isDeserveInsert(Map<Bid, Integer> result,
	    List<Integer> indices, Bid bid, int timeAvailable) {
	int timeNeeded = (int) Math.round((float) bid.time / 2);
	double benefit = bid.average * timeAvailable;
	double lost = 0;
	
	int lastIndex = indices.get(indices.size()-1);
	
	return false;
    }

    public static void main(String[] args) {
	System.out.println("===========================================");
	// for (int i = 8; i < 20; i++) {
	// AdBid instance = new AdBid(i);
	// double v1 = instance.find1();
	// double v2 = instance.find2();
	// System.out.format("%d\t%g\t%g\t%b\n", i, v1, v2, v1 == v2);
	// }
	new AdBid(15).find3();
    }
}