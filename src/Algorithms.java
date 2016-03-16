
public class Algorithms {

	public static void main(String[] args) {
		
		/*** MERGE SORT ***/
		
		MergeSort.initializeArrayList();
		MergeSort mergeSortObj = new MergeSort( 0, MergeSort.inversionList );
		MergeSort finalAns = MergeSort.mergeSort( mergeSortObj );
		
		System.out.println( finalAns.getArray().toString() );

	}

}
