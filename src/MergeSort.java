import java.util.ArrayList;
import java.util.List;


// Req's: - Count number of inversions
// Inversion: a pair of items in an array such that Ai preceeds Aj, and i < j, such that Ai > Aj


public class MergeSort {
	
	public static final ArrayList<Integer> inversionList = new ArrayList<Integer>();
	
	public static final int NUM_INVERSION_TEST = 6;
		
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
	
	public static void initializeArrayList () {
		inversionList.add( 10 ); 
		inversionList.add( 9 );
		inversionList.add( 8 );
		inversionList.add( 7 );
		
		inversionList.add( 6 );
		inversionList.add( 2 );
		inversionList.add( 11 );
		inversionList.add( 3 );	
	}
	
	/*
	 * MergeSort Algorithm
	 */
	public static MergeSort mergeSort ( MergeSort mergeSortObj ) {
		
		int numInversions = mergeSortObj.getNumInversions();
		int leftNumInversions = 0;
		int rightNumInversions = 0;
		
		ArrayList<Integer> array = mergeSortObj.getArray();
		
		MergeSort leftMergeSort;
		MergeSort rightMergeSort;
		
		ArrayList<Integer> leftSide = new ArrayList<Integer>();
		ArrayList<Integer> rightSide = new ArrayList<Integer>();
		
		// If length is 1, the array is considered sorted
		// There are no inversions (need a pair)
		if ( array.size() == 1 )
			return new MergeSort( numInversions, array );
		
		// Split Array
		for ( int i = 0; i < array.size(); i++ ) {
			
			if( i % 2 == 0 ) 
				leftSide.add(array.get(i));
			else 
				rightSide.add(array.get(i));
		}
				
		// Recursively sort both Lists
		leftMergeSort = new MergeSort( leftNumInversions, leftSide );
		rightMergeSort = new MergeSort( rightNumInversions, rightSide );
		
		leftMergeSort = mergeSort(leftMergeSort);
		rightMergeSort = mergeSort(rightMergeSort);
		
		// Merge Lists
		return mergeSortedLists(leftMergeSort, rightMergeSort);
	}
	
	
	private static MergeSort mergeSortedLists ( MergeSort listOne, MergeSort listTwo ) {
		
		ArrayList<Integer> sortedList = new ArrayList<Integer>();
		ArrayList<Integer> listOneArray = listOne.getArray();
		ArrayList<Integer> listTwoArray = listTwo.getArray();
		
		while ( listOneArray.size() > 0 && listTwoArray.size() > 0 ) {
			
			if ( listOneArray.get(0) < listTwoArray.get(0)) {
				sortedList.add(listOneArray.get(0));
				listOneArray.remove(0);
			} else {
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
		
		return new MergeSort( listOne.getNumInversions() + listTwo.getNumInversions(), sortedList );
	}
}
