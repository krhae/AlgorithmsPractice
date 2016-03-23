
public class Algorithms {

	public static void main(String[] args) {
		
		/*** MERGE SORT ***/
		System.out.println("MERGESORT:");
		
		MergeSort.initializeArrayList();
		MergeSort mergeSortObj = new MergeSort( 0, MergeSort.inversionList );
		MergeSort finalAns = MergeSort.mergeSort( mergeSortObj );
		
		System.out.println( "Sorted Array: " + finalAns.getArray().toString() );
		System.out.println( "Inversion Count: " + finalAns.getNumInversions() + "\n" );
		
		/*** MINIMUM & SECOND MINIMUM ***/
		System.out.println("MINIMUM & SECOND MINIMUM:");
		SecondOrderMinimum.secondOrderMinimumAlgorithm(SecondOrderMinimum.getTestArray());
	}

}
