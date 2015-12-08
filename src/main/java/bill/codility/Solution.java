package bill.codility;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @author nunocosta
 *
 */

public class Solution {

	public int solution (String S) {

		String[] line = S.split("\n");
		int totalCost = 0;

		Hashtable<String, Integer> aggregatedBill = new Hashtable<String, Integer>();
		//hash that will have phone as key and sum of duration as value

		//1st step - check which phone number should be excluded from the bill
		for(int i = 0; i < line.length; i++) {

			String [] dividedLine = line[i].split(",");
			int duration = callDurationInSeconds(dividedLine[0]);
			String phoneNumber = dividedLine[1];

			if(aggregatedBill.containsKey(phoneNumber)) {
				int accumulated = aggregatedBill.get(phoneNumber);
				aggregatedBill.put(phoneNumber, accumulated + duration);
			} else {
				aggregatedBill.put(phoneNumber, duration);
			}
		}
		String freeNumber = findFreeNumber(aggregatedBill);
		
		//2nd step - add to the bill total cost, all the calls from all the numbers (excluding the free number)
		for(int i = 0; i < line.length; i++) {

			String [] dividedLine = line[i].split(",");
			int duration = callDurationInSeconds(dividedLine[0]);
			String phoneNumber = dividedLine[1];

			if(!phoneNumber.equals(freeNumber)) 
				totalCost += callCost(duration);
			
		}

		return totalCost;
	}

	/**
	 * From the hastable containing the accumulated value for callers, get the free number
	 * @param Hashtable with values
	 * @return String with free number
	 */
	private String findFreeNumber(Hashtable<String, Integer> t) {

		if(t.size() == 1)
			return t.keys().nextElement();  //if only one caller, get the caller number

		//Transfer as List and sort it - got from Stackoverflow due to lack of time
		ArrayList<Map.Entry<String, Integer>> list = new ArrayList(t.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<?, Integer>>(){

			public int compare(Map.Entry<?, Integer> o1, Map.Entry<?, Integer> o2) {
				return o1.getValue().compareTo(o2.getValue());
			}
		});

		int maxDuration = list.get(list.size() - 1).getValue();  //get the max duration
		
		Iterator<Entry<String, Integer>> iterator = list.iterator();
		list = new ArrayList<Map.Entry<String, Integer>>();

		//clean list and insert only the callers with max duration
		while ( iterator.hasNext()) {
			
		    Entry<String, Integer> call = iterator.next();
		    if (call.getValue() == maxDuration) {
		    	list.add(call);
		    }
		}
		
		if(list.size() == 1)
			return list.get(0).getKey();  //if only one caller with max duration, get the caller number

		//order the callers with max duration by "name"
		Collections.sort(list, new Comparator<Map.Entry<String, ?>>(){
			public int compare(Map.Entry<String, ?> o1, Map.Entry<String, ?> o2) {
				return o1.getKey().compareTo(o2.getKey());
			}
		});

		return list.get(0).getKey(); //if more than one with maxduration, return the one with "smaller" number

	}

	/**
	 * Get the duration of the call (in seconds) from a string with format hh:mm:ss
	 * @param duration with format hh:mm:ss
	 * @return integer with number of seconds
	 */
	private int callDurationInSeconds(String duration) {

		String[] dividedString = duration.split(":");
		int hours = Integer.parseInt(dividedString[0]);
		int minutes = Integer.parseInt(dividedString[1]);
		int seconds = Integer.parseInt(dividedString[2]);

		return ((hours * 3600) + (minutes * 60) + seconds);
	}

	/**
	 * Calculate the call cost from the amount of seconds
	 * @param seconds - Duration of the call
	 * @return cost of the call
	 */
	private int callCost(int seconds) {

		if(seconds < 300) {
			return seconds * 3;
		} else {
			if(seconds % 60 == 0) {
				return (seconds / 60) * 150;
			} else {
				return ((seconds / 60)+1) * 150;
			}
		}
	}

}
