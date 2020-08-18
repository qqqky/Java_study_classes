package bankQueueImitation;
/*PP 13.6 Modify the solution to the PP 13.5 so that it represents eight
		  tellers and therefore eight customer queues. Have new customers go to the shortest queue. 
		  Determine which queue had the shortest waiting time per customer on average.*/

import java.util.Random;
import java.util.Queue;
import java.util.LinkedList;
import javax.swing.Timer;
import java.awt.event.*;
import java.util.Scanner;

/* NOTES:	Tried to make program calculate more precisely by not counting time when a queue
 * 			is empty (except at the very beginning), but somehow calculations always fell through
 * 			and average time shown was not as correct as expected.
 * 			Although, since customers always choose the shortest queue (AND "timer" only start
 * 			when first customer enters each queue), this is not a big deal.
 * 
 * 			* Calculations are trimmed to milliseconds, but toString() further divides that number
 * 			  to make the example more real-life.
 * 
 * 			* Program supports any amount of queues.
 * 			* Chance of client coming is higher than chance of client leaving.
 */

/**
 * Simulation of multiple bank tellers servicing clients.
 * Note that client coming has a higher chance than client leaving, therefore
 * the queues quickly fill up.
 * 
 * Client always chooses the shortest queue.
 * If there are multiple shortest queues - the first (topmost) is chosen.
 * 
 * @author qqqky
 *
 */
public class BankQueueMultiple {

	
	public static void main (String[]args)
	{
		Scanner scan = new Scanner(System.in);
		
		
		multipleQueue bankSimulation = new multipleQueue(8);
		String input;
		
		bankSimulation.startSimulation();
		
		//waits for user input to close, otherwise main method would end and program would close without triggering ActionEvent
		do
		{
		input = scan.nextLine();
		}while (!input.equalsIgnoreCase("Q"));	//<--- this doesn't work as console is being constantly updated
		
		scan.close();
		
	}
}

class multipleQueue implements ActionListener
{
	public Timer timer;
	private Random generate;
	private Queue<customer>[] queueList;
	private int[] queueCustomerCount;
	private long[] queueFirstCustomerTime, queueAverageWaitingTime;
	private int numberOfQueues;
	private int shortest = 0;
	
	
	
		public multipleQueue(int number) // amount of queues
		{
		numberOfQueues = number;
		generate = new Random();
		
		queueList = new LinkedList[numberOfQueues];
		queueCustomerCount = new int [numberOfQueues]; //counts serviced customers (those who leave the counter)
		queueFirstCustomerTime = new long[numberOfQueues]; //marks entry time of the first customer
		queueAverageWaitingTime = new long[numberOfQueues];//contains average waiting time
		
		//
		for (int x=0; x<numberOfQueues; x++)
			{
			queueList[x]=new LinkedList<customer>();
			queueCustomerCount[x]=0;
			queueFirstCustomerTime[x]=0;
			queueAverageWaitingTime[x]=0;
			}
			
		
		timer = new Timer(1000, this);

	
		
	}
	public void startSimulation()
	{
		timer.start();
	}
	
	//--------------------------------------------------------------------------------------------------
	//		Adds a customer to the shortest queue and notes the time (of the very first customer)
	//--------------------------------------------------------------------------------------------------
	public void customerComes()
	{
				
		findShortest(0,1);
		queueList[shortest].add(new customer(generate.nextInt(50)+1));
		
		
		if(queueList[shortest].size()==1)
			queueFirstCustomerTime[shortest] = System.nanoTime()/1000000;
		
		
		
	}
	//----------------------------------------------------------------------------------------
	//			If bank is not empty, tries to remove a customer randomly.
	//			Calculates total customers serviced and average time taken per customer
	//----------------------------------------------------------------------------------------
	public void customerLeaves()
	{
		
		if(allAreEmpty(numberOfQueues))
			return;
		
		
		int randomLeave = findUnemptyQueue(generate.nextInt(numberOfQueues), 5);
		
		if(randomLeave>=0)
		{
			queueList[randomLeave].remove();
			queueCustomerCount[randomLeave]+=1;
			
			
			queueAverageWaitingTime[randomLeave] = (((System.nanoTime()/1000000) - queueFirstCustomerTime[randomLeave]))/queueCustomerCount[randomLeave];
			
			
		}
		
			
	}
	

	//---------------------------------------------------------------------------------------------
	//		Tries to find unempty queue by generating 2 different numbers (between 0 and number 
	//		of queues). Cycles through method "tries" amount of times
	//---------------------------------------------------------------------------------------------
	public int findUnemptyQueue(int current, int tries)
	{
		
		
		if(tries==0)
			return -1;
		
		if(!queueList[current].isEmpty())
			return current;
		
		else
			{
			int nextTry = generate.nextInt(numberOfQueues);
				while(nextTry==current)
					nextTry = generate.nextInt(numberOfQueues);
			return findUnemptyQueue(nextTry, tries-1);
			}
			
				
	}
	
	//------------------------------------------------------------
	//				Returns true if all queues are empty
	//------------------------------------------------------------
	public boolean allAreEmpty(int numberOfQueues)
	{
		
		if(numberOfQueues == 0)
			return true;
		
		else
			{
			  if (!queueList[numberOfQueues-1].isEmpty())
				  return false;
			  
			  else return allAreEmpty(numberOfQueues-1);
			  
			}
		
		
	}
	
	//----------------------------------------------------------------------------------------------------
	//		Finds the shortest queue (if queues are of similar length, chooses the first one checked)
	//----------------------------------------------------------------------------------------------------
	public int findShortest(int start, int current)
	{
		
		
		if(current<numberOfQueues)
				{
					if (queueList[start].size()<=queueList[current].size())
						{
							shortest = start;
							return findShortest(start, current+1);
						}
					
					else 
						{
							shortest = current;
							return findShortest(current, current+1);
						}
				}
		
		return shortest;
	}
	
	//------------------------------------------------------------------------------------------------------------------
	//				Randomly determines if a new customer enters or an existing customer leaves (60% new/ 40% leave)
	//------------------------------------------------------------------------------------------------------------------
	public void actionPerformed (ActionEvent e)
	{
		
		for (int x=0; x<3;x++)
			System.out.print("\n");
		
		int a = generate.nextInt(20)+1;
		if (a%5 == 0 || a%5 == 2 ||a%5 == 4)
			{
			timer.stop();
			customerComes();
			timer = new Timer (generate.nextInt(300)+500, this);
			timer.start();
			}
		if (a%5 == 1 || a%5 == 3)
			{
			timer.stop();
			customerLeaves();
			timer = new Timer (generate.nextInt(300)+500, this);
			timer.start();
			}
		
		System.out.print(toString());
		
		
	}
	public String toString()
	{
		String result = "";
		
		for (int x=0; x<queueList.length;x++)
			result = result +"Bank teller <--- "+ queueList[x]+" <--- end of queue \t Average waiting time: "+(queueAverageWaitingTime[x]/200)+" \"minutes\", customers serviced: "+queueCustomerCount[x]+"\n";
		
		
		return result;
	}


		private class customer
		{
			private Integer number;
	
			public customer(int number)
			{
				this.number = number;
			}
			public String toString()
			{
				return number + " ";
			}
		}
}
