package fullBinaryTree;
/*PP 13.9 Design and implement a class named StringTree, a binary
tree for storing String objects in alphabetic order. Each node
in the tree should be represented by a Node class, which stores
the string value and pointers to the right and left child nodes.
For any node value in the tree, the value of its left child should
come before that value, and the value of its right child should
come after that value. The StringTree class should contain
both a method for adding strings to the tree and a method for
printing the tree’s value in alphabetic order. Write a driver
program that prompts the user for strings and adds them to
the tree. After processing the input, print the tree values.*/

/* For binary trees there are a few traversing methods: 
 * Pre-Order (root, left, right), Post-Order (left-right-root) and In-Order (left-root-right).
 * They are all called DEPTH-FIRST traversals.
 * 
 * The other kind of traversal is BREADTH-FIRST traversal (checks nodes by levels):
 * 
 * 		Traversing starts from the root node. It is marked as checked. Adjacent nodes are checked,
 * 		compared and put onto Queue from lower to higher (or lower to higher). They are marked as checked. 
 * 		Then pointer transfers to the first node in Queue. Queue is dequeued. Again, adjacent 
 * 		left/right nodes are compared and put into Queue first lower value, then higher (same chosen order). 
 * 		Afterwards, pointer transfers to next node in Queue, queue is dequeued again and the process 
 * 		repeats until Queue is empty. 
 * 		When node is marked as checked, its value is printed to the result!
 * 
 *  					!!!	Traversing a tree is different from traversing a graph !!!
 *  	In breadth-first traversal of a graph, any node can be chosen as root and traversed from there.
 */

// NOTES:
//			1.  sorting words, one of which fully contains the other (eg. Water and Watermelon)
//				causes out of bounds error, if not taken care of.
//
//			2.	for balanced binary tree to be as symmetric as possible, the list provided has to be sorted
//				root is the middle value. Every starting node in another level can be considered as another root
//				therefore, middle value of the sub-list (one on each side) has to be added first.
//				(creating a balanced tree can simply be seen as reversal of binary search)
//
//
//			* 	Program takes input from user to an ArrayList, sorts it in descending order, creates a balanced 
//				Binary Tree and prints out the result by traversing it using in-order traversal.
//				The result is original list of words, printed out alphabetically (in ascending order).
//
//			**	Exercise asks to immediately write user input to a tree.
//				In such case, another tree would be needed to store the Strings and later add them
//				to some list after traversing.
//				
import java.util.ArrayList;
import java.util.Scanner;

/**
 * 
 * Program takes a list of entries, sorts them in descending order and creates
 * a symmetrical binary tree. 
 * Can later traverse the tree and produce a sorted list (ascending or descending).
 *
 * Example of entries can be found in the ListOfFoodsForBinaryTree.txt
 * Can copy-paste them here.
 *
 * @author qqqky
 *
 */

public class FullBinaryTree {

	
	public static void main(String[]args)
	{
		
		StringTree myTree = new StringTree();
		ArrayList<String> data = new ArrayList<String>();
		Scanner scan = new Scanner(System.in);
		String input;
		System.out.println("Please enter a list of words. Each starting from a new line (enter empty line to put the list to tree): ");
		
		do
		{
			
			input = scan.nextLine();
			if(!input.equals(""))
				data.add(input);
			
		
		}while(!input.equals(""));
		
		scan.close();
		myTree.createSymmetricalTree(data);
	}
}

class StringTree
{
	private TreeNode root;
	private ArrayList<String> list;
	private int indexOfRoot;
	
	
	public StringTree()
	{
		root = null;
	}
	//----------------------------------------------------------
	//				Adds a node containing chosen String
	//----------------------------------------------------------
	public void addString(String title)
	{
		if(root==null)
			root = new TreeNode (title);
		else
			addNode(root, new TreeNode(title));
	}
	//---------------------------------------------------------------------------------------------------
	//				Adds all Strings from ArrayList to the binary tree (first one is chosen as root) 
	//---------------------------------------------------------------------------------------------------
	public void addAll(ArrayList<String> list)
	{
		this.list = list;
		
		if(root==null)
		{
			root = new TreeNode(list.get(0));
		}
		
		
		for(int x=1;x<list.size();x++)
			{
			System.out.println(x);
			TreeNode node = new TreeNode(list.get(x)); 
			addNode(root, node);
			}
		
		printValuesInOrder(root);
	}
	//---------------------------------------------------------------------------------------------------
	//				Sorts the ArrayList, determines correct root and creates symmetrical binary tree
	//---------------------------------------------------------------------------------------------------
	public void createSymmetricalTree(ArrayList<String> list)
	{
		this.list=list;
		
						
		sortArrayAndAddRoot(list);//sorts array in descending order and inserts the root accordingly (middle value)
		
		for(String a: list)			//prints this list
			System.out.println(a);
		
		System.out.println();
		System.out.println("Root: "+list.get(indexOfRoot)+"\tIndex of root on the ArrayList: "+indexOfRoot+ "\tTotal items: "+list.size());
		System.out.println();
		
		binaryAddElements(list, 0, indexOfRoot, list.size()); //adds elements to tree symmetrically
		
		System.out.println("After traversing the tree: ");
		printValuesInOrder(root); //prints the result using in-order traversal ( =in alphabetical order(ascending))
	
	}
	public void binaryAddElements (ArrayList<String> list, int start,int middle, int end)
	{
		
		//middle = (end+start)/2; <--- somewhere was suggested to always use "middle = start+(end-start)/2"
		
		if(start==middle) // if bottom is reached (no other node to choose), return to previous iteration
		{
			return;
		}
		
		if(start<middle)
		{
					
				if(middle!=indexOfRoot) //(the root node was already added, ignore it now)
				{
				
					addNode(root, new TreeNode(list.get(middle))); //always add "middle" node
					
					if(middle == 1) //if 1st node is reached, add node 0 as well (otherwise node 0 will be ignored; last node is ok, though)
						{
						addNode(root, new TreeNode(list.get(0)));
						}
				}
				
			binaryAddElements(list, start, (start+middle)/2, middle);//recursively choose items on the left side first 	(from start to middle)	
			binaryAddElements(list, middle, (middle+end)/2, end);	 //then choose everything on the right side			(from middle to end)
				
			
		}
		
			
	}
	public void sortArrayAndAddRoot(ArrayList<String> list)
	{
		sort(list, 0, 1, 0); //Parameters: (arraylist, start index, current index, index of current character being checked)
			
		indexOfRoot = list.size()/2;
		root = new TreeNode(list.get(indexOfRoot));
			
		
	}
	//-------------------------------------------------------------------------------------------
	//	Adds a node to the tree (lower nodes go to the left side, higher ones go to right side
	//-------------------------------------------------------------------------------------------
	public void addNode(TreeNode current, TreeNode node) //adds a node to a tree
	{
			
		if(compareNodes(node, current) == node)
		{
			if(current.left==null)
				current.left=node;
			else
			{
				addNode(current.left, node);		
			}
		}
		if(compareNodes(node, current) == current)
		{
			if(current.right==null)
				current.right=node;
			else
			{
				addNode(current.right, node);
			}
		}
	}
	//----------------------------------------------------------
	//	Sorts an array using insertion sort (descending order
	//----------------------------------------------------------
	public void sort(ArrayList<String> list, int start, int current, int index) //sorts a list in descending order (insertion sort)
	{
		
		String temp;
		
		
		if(list.get(start).charAt(index) > list.get(current).charAt(index)) // if first item is higher, do nothing
			{
				
				if(current<list.size()-1) // if next item exists, check next
					{
					sort(list, start, current+1, 0);
					return;
					}
			}
		else
			{	//if characters are same, check index+1 (but only if index+1 won't be out of bounds). Example: comparing Water and Watermelon
				if(list.get(start).charAt(index) == list.get(current).charAt(index))
					{
					if(index==list.get(start).length()-1 || index==list.get(current).length()-1)//<--- if either one will be out of bounds, do nothing
						{
							if(current<list.size()-1)// if next item exists, check next
								{
								sort(list, start, current+1, 0);
								return;
								}
						}
					else // <---- if index+1 is in bounds, check Strings at index+1
						{
						sort(list, start, current, index+1); //check index+1
						return;
						}
					}
				else // <--- if second item is higher, swap them
					{
					//System.out.println("Switching.... "+list.get(start)+" with "+list.get(current));
					temp = list.get(current); //saves current string to temp
					list.remove(current);
					list.add(current, list.get(start));
					list.remove(start);
					list.add(start, temp);
					if(current<list.size()-1) // if next exists, check next
						{
						sort(list, start, current+1, 0);
						return;
						}
					}
				
					
			}
		//--------------------------------------------------//
		//		Recurse again, starting from next item:		//
		//--------------------------------------------------//
		if(start<list.size()-2) //repeats until every field is sorted
			sort(list, start+1, start+2, 0);
			
		
	}
	//----------------------------------------------------
	//	Compares two nodes based on their String value
	//----------------------------------------------------
	public TreeNode compareNodes(TreeNode first, TreeNode second)
	{
		if(first.getValue().compareTo(second.getValue())<=0)
			return first;
		else
			return second;
	}
	//----------------------------------------------------
	//		Pre-order traversal (root comes first)
	//----------------------------------------------------
	public void printValuesPreOrder(TreeNode current) 
	{
	
		System.out.print(current.value+" "); //<--- prints before recursing all branches
		
		if(current.left!=null)	// <--- recurses left branch
		{	
			printValuesPreOrder(current.left);
		}
		
		if(current.right!=null) // <--- recurses right branch
		{
			printValuesPreOrder(current.right);
		}
		
	}
	//----------------------------------------------------
	//		Post-order traversal (root comes last)
	//----------------------------------------------------
	public void printValuesPostOrder(TreeNode current) 
	{
	
		
		
		if(current.left!=null)
		{	
			printValuesPostOrder(current.left);
		}
		
		if(current.right!=null)
		{
			printValuesPostOrder(current.right);
		}
		
		System.out.print(current.value+" ");	//<---- prints after recursing both branches
		
	}
	//----------------------------------------------------
	//		In-order traversal (root comes after left branch)
	//----------------------------------------------------
	public void printValuesInOrder(TreeNode current) 
	{
		
		if(current.left!=null)
		{	
			printValuesInOrder(current.left);
		}
		
		System.out.print(current.value+" "); //<------ prints between recursing each branch
		
		if(current.right!=null)
		{
			printValuesInOrder(current.right);
		}
		
		
	}
	//---------------------------------------------------------------
	//		Defines an inner TreeNode class, containing a String
	//---------------------------------------------------------------
	private class TreeNode
	{
		private TreeNode left;
		private TreeNode right;
		private String value;
		
		
		public TreeNode(String value)
		{
			this.value = value;
			left = null;
			right = null;
		}
		public String getValue()
		{
			return value;
		}
		public void insertLeft(TreeNode node) //methods to insert nodes (if TreeNode was not an inner class)
		{
			left = node;
		}
		public void insertRight(TreeNode node)
		{
			right = node;
		}

	}
}

