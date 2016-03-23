import java.util.ArrayList;

// Req's: - Count number of inversions
// Inversion: a pair of items in an array such that Ai preceeds Aj, and i < j, such that Ai > Aj


public class MergeSort {
	
	public static final boolean VERBOSE = true;
	
	public static final ArrayList<Integer> inversionList = new ArrayList<Integer>();
		
	/*
	 * MergeSort Class
	 */
	private int numInversions;
	private ArrayList<Integer> currentArray;
	
	public MergeSort( int numInversions, ArrayList<Integer> currentArray ) {
		this.numInversions = numInversions;
		this.currentArray = currentArray;
	}
	
	public int getNumInversions() {
		return this.numInversions;
	}
	
	public ArrayList<Integer> getArray() {
		return this.currentArray;
	}
	
	public void increaseNumInversions( int num ) {
		this.numInversions = num;
	}
	
	public static void initializeArrayList () {
		inversionList.add( 2 ); 
		inversionList.add( 4 );
		inversionList.add( 1 );
		inversionList.add( 3 );
		inversionList.add( 5 );
		inversionList.add( 0 );
		
		if(VERBOSE)
			System.out.println("Inital Array: " + inversionList.toString());
	}
	
	/*
	 * MergeSort Algorithm
	 */
	public static MergeSort mergeSort ( MergeSort mergeSortObj ) {
		
		int leftNumInversions = 0;
		int rightNumInversions = 0;
		
		ArrayList<Integer> array = mergeSortObj.getArray();
		
		MergeSort leftMergeSort;
		MergeSort rightMergeSort;
		MergeSort totalMergeSort;
		
		ArrayList<Integer> leftSide = new ArrayList<Integer>();
		ArrayList<Integer> rightSide = new ArrayList<Integer>();
		
		// If length is 1, the array is considered sorted
		// There are no inversions (need a pair)
		if ( array.size() == 1 )
			return new MergeSort( 0, array );
		
		// Split Array
		for ( int i = 0; i < array.size()/2; i++ ) {
			leftSide.add(array.get(i)); 
		}
		
		for ( int j = array.size()/2; j < array.size(); j++ ) {			
			rightSide.add(array.get(j));
		}
				
		// Recursively sort both Lists
		leftMergeSort = new MergeSort( leftNumInversions, leftSide );
		rightMergeSort = new MergeSort( rightNumInversions, rightSide );
		
		leftMergeSort = mergeSort(leftMergeSort);
		rightMergeSort = mergeSort(rightMergeSort);
		
		totalMergeSort = mergeSortedLists(leftMergeSort, rightMergeSort);
		
		// Merge Lists
		return new MergeSort( totalMergeSort.getNumInversions() , totalMergeSort.getArray() );
	}
	
	
	private static MergeSort mergeSortedLists ( MergeSort listOne, MergeSort listTwo ) {
		
		ArrayList<Integer> sortedList = new ArrayList<Integer>();
		ArrayList<Integer> listOneArray = listOne.getArray();
		ArrayList<Integer> listTwoArray = listTwo.getArray();

		int count = 0;
		
		count += listOne.getNumInversions();
		count += listTwo.getNumInversions();
		
		while ( listOneArray.size() > 0 && listTwoArray.size() > 0 ) {
			
			if ( listOneArray.get(0) < listTwoArray.get(0)) {
				sortedList.add(listOneArray.get(0));
				listOneArray.remove(0);
			} else {
				count += listOneArray.size();
							
				sortedList.add(listTwoArray.get(0));
				listTwoArray.remove(0);
			}
		}
		
		while ( listOneArray.size() > 0 ) {
			sortedList.add(listOneArray.get(0));
			listOneArray.remove(0);
		}
		
		while ( listTwoArray.size() > 0 ) {
			sortedList.add(listTwoArray.get(0));
			listTwoArray.remove(0);
		}
		
		return new MergeSort( count, sortedList );
	}
}
