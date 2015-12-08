/**
 * 
 */
package bill.codility;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author nunocosta
 *
 */
public class SolutionTest  {

	Solution s = new Solution(); 
	
	@Test
	public void baseTest () {
		String bill = "00:01:07,400-234-090\n"
					+ "00:05:01,701-080-080\n"
					+ "00:05:00,400-234-090";
		
		int cost = s.solution(bill);
		
		assertEquals(900, cost);
	}
	
	@Test
	public void oneCallTest () {
		String bill = "00:01:07,400-234-090";
		
		int cost = s.solution(bill);
		
		assertEquals(0, cost);
	}
	
	@Test
	public void oneCallerTest () {
		String bill = "00:01:07,400-234-090\n"
					+ "00:05:01,400-234-090\n"
					+ "00:05:00,400-234-090";
		
		int cost = s.solution(bill);
		
		assertEquals(0, cost);
	}
	
	@Test
	public void emptyCallTest () {
		String bill = "00:00:01,400-234-090\n"
					+ "00:00:00,400-234-090\n"
					+ "00:00:00,400-234-091\n"
					+ "00:00:02,400-234-091";
					
		int cost = s.solution(bill);
		
		assertEquals(3, cost);
	}
	
	@Test
	public void singleCallTest () {
		String bill = "00:05:00,400-234-092\n"
					+ "00:05:00,400-234-090\n"
					+ "00:05:00,400-234-091";
		
		int cost = s.solution(bill);
		
		assertEquals(1500, cost);
	}
	
	@Test
	public void mixedCallTest () {
		String bill = "00:02:00,400-234-092\n"
					+ "00:05:00,400-234-091\n"
					+ "01:01:00,400-234-092\n"
					+ "00:02:00,400-234-091\n"
					+ "00:05:01,400-234-092\n"
					+ "01:01:00,400-234-091";
		
		int cost = s.solution(bill);
		
		assertEquals(10260, cost);
	}
	
	@Test
	public void shortCalls () {
		String bill = "00:01:07,400-234-090\n"
					+ "00:01:07,400-234-091\n"
					+ "00:04:59,400-234-092";
		
		int cost = s.solution(bill);
		
		assertEquals(402, cost);
	}
	
	@Test
	public void longCalls () {
		String bill = "99:00:00,400-234-090\n"
					+ "99:99:59,400-234-091\n"
					+ "99:99:59,400-234-092\n"
					+ "00:05:00,400-234-092";
		
		int cost = s.solution(bill);
		
		assertEquals(1797000, cost);
	}

}
