package mathTest;
/*PP 11.9 Design and implement an application that performs flashcard
testing of simple mathematical problems. Allow the user to pick
the category. Repetitively display a problem and get the user’s
answer. Indicate whether the user’s answer is right or wrong for
each problem, and display an ongoing score.*/

import java.util.Scanner;
import java.util.ArrayList;

/**
 * 
 * Math flash cards with exception handling
 *
 */

public class MathFlashcards {

	public static void main (String[]args)
	{
		
		Scanner scan = new Scanner(System.in);
		MathTest test = new MathTest();
		System.out.println("Which test do you want? (1-3): ");
		System.out.println("1 = addition, 2 = multiplication, 3 = division");
		
		String a;
		a = scan.nextLine();
		int b;
		do
		{
			//catches exception if not a number is entered
			try
			{
				b = Integer.parseInt(a);
				// makes sure a correct number (1-3) is entered
				if (b>0 && b<4)
				{
					test.startTest(b);
				}
				else System.out.println("Wrong number. Please enter 1-3 to choose test.");
			}
			catch (NumberFormatException e)
			{
				System.out.println("Not a number. Please enter a number 1-3.");
			}
			
			
		
			a = scan.nextLine();
		}while (!a.equals("QUIT"));
		
		scan.close();
	}
	
	
}
class MathTest
{
	private Question addition, multiplication, division;
	private int testNumber, questionCounter, correctCounter;
	private String userAnswer;
	private Scanner answerScanner;
	
	public MathTest()
	{
		answerScanner = new Scanner (System.in);
		
		addition = new Question();
		multiplication = new Question();
		division = new Question();
		
		
		//add simple addition questions:
		addition.addQuestion("5 + 2 = ", "7");
		addition.addQuestion("23 + 14 = ", "37");
		addition.addQuestion("7 + 88 = ", "95");
		addition.addQuestion("51 + 77 = ", "128");
		addition.addQuestion("42 + 89 = ", "131");
		
		//add simple multiplication questions:
		multiplication.addQuestion("4 * 3 = ", "12");
		multiplication.addQuestion("7 * 8 = ", "56");
		multiplication.addQuestion("4 * 19 = ", "76");
		multiplication.addQuestion("12 * 14 = ", "168");
		multiplication.addQuestion("4 * 77 = ", "308");
		
		//add simple division questions:
		division.addQuestion("9 / 3 = ", "3");
		division.addQuestion("88 / 2 = ", "44");
		division.addQuestion("56 / 7 = ", "8");
		division.addQuestion("156 / 3 = ", "52");
		division.addQuestion("208 / 26 = ", "8");
	}
	public void startTest (int a)
	{
		testNumber = a;
		
		if (testNumber == 1)
		{
			while (questionCounter < addition.size())
				{
				
				System.out.println(addition.getQuestion(questionCounter));
				userAnswer = answerScanner.nextLine();
				if (addition.isCorrect(userAnswer, questionCounter))
					{
					correctCounter++;
					System.out.println("Correct! So far your score is: "+correctCounter);
					}
				else System.out.println("Wrong! So far your score is: "+ correctCounter);
				
				questionCounter++;
				
				}
			//prints out total correct answers:
			System.out.println("Addition test is finished. Correct answers: "+ correctCounter+"/5");
			//resets question and answer counters:
			questionCounter = 0;
			correctCounter = 0;
		}
		
		if (testNumber == 2)
		{
			while (questionCounter < multiplication.size())
				{
				
				System.out.println(multiplication.getQuestion(questionCounter));
				userAnswer = answerScanner.nextLine();
				if (multiplication.isCorrect(userAnswer, questionCounter))
					{
					correctCounter++;
					System.out.println("Correct! So far your score is: "+correctCounter);
					}
				else System.out.println("Wrong! So far your score is: "+ correctCounter);
				
				questionCounter++;
				
				}
			//prints out total correct answers:
			System.out.println("Multiplication test is finished. Correct answers: "+ correctCounter+"/5");
			//resets question and answer counters:
			questionCounter = 0;
			correctCounter = 0;
		}
		
		if (testNumber == 3)
		{
			while (questionCounter < division.size())
				{
				
				System.out.println(division.getQuestion(questionCounter));
				userAnswer = answerScanner.nextLine();
				if (division.isCorrect(userAnswer, questionCounter))
					{
					correctCounter++;
					System.out.println("Correct! So far your score is: "+correctCounter);
					}
				else System.out.println("Wrong! So far your score is: "+ correctCounter);
				
				questionCounter++;
				
				}
			//prints out total correct answers:
			System.out.println("Division test is finished. Correct answers: "+ correctCounter+"/5");
			//resets question and answer counters:
			questionCounter = 0;
			correctCounter = 0;
		}
		
		//play again?
		System.out.println();
		System.out.println("Enter 1-3 to take the test again. Or enter QUIT to quit.");
		

	}
}
class Question
{
	private String question, answer;
	private ArrayList<String> q, a;
	
	public Question ()
	{
		q = new ArrayList<String>();
		a = new ArrayList<String>();
	}
	public void addQuestion (String problem, String solution)
	{
		question = problem;
		answer = solution;
		
		q.add(question);
		a.add(answer);
	}
	public String getQuestion (int number)
	{
		return q.get(number);
	}
	public String getAnswer(int number)
	{
		return a.get(number);
	}
	public int size ()
	{
		return q.size();
	}
	public boolean isCorrect(String correct, int questionNumber)
	{
		if (a.get(questionNumber).equals(correct))
			return true;
		
		else 
			return false;
	}
}