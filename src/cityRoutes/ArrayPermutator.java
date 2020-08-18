package cityRoutes;
//modification of Permutations to be used in CityRoutes

public class ArrayPermutator
{
	private int[][]result;
	private int[]arr;
	private int counter=0, start = 0;
	
	public ArrayPermutator(int[]arr, int start)
	{
		this.arr=arr;
		this.start=start;
		result = new int [getRows(arr.length)][arr.length];
	
	}
	//returns result as 2D array
	public int[][] ReturnResultsAs2DArray ()
	{
		permute(arr, start);
		return result;
	}
	/*
	 * Credits go to Mshnik for posting this method in StackOverflow.
	 * Here it is slightly modified to be used in CityRoutes class.
	 */
	private void permute(int[] arr, int index){
    
	if(index >= arr.length - 1)
    { //If we are at the last element - nothing left to permute
       
        
        
        for(int i = 0; i < arr.length; i++)
        {
            result[counter][i]=arr[i];
        }
        
        counter+=1;
        return;// = does not execute for loop if array is printed
    }

    for(int i = index; i < arr.length; i++)
    { //For each index in the sub array arr[index...end]

        //Swap the elements at indices index and i
        int t = arr[index]; //<saves value at [index] index as int
        arr[index] = arr[i]; //<swaps current index with a new one (+0, then +1, then +2, then +3 etc)
        arr[i] = t;// <value which was swapped now takes new value (which was saved)
        
        //Recurse on the sub array arr[index+1...end]
        permute(arr, index+1);
      

        //Swap the elements back
        t = arr[index];
        arr[index] = arr[i];
        arr[i] = t;
    }
}
	public int getRows(int memberCount)
	{
		
		if (memberCount == 1)
			return 1;
		else return memberCount*getRows(memberCount-1);
		
	}
}
