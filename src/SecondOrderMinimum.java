import java.util.ArrayList;

public class SecondOrderMinimum {

	private static final boolean VERBOSE = true;
	private static int[] testArray = { 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17};
	
	
	/*** SecondOderMinimum Class ***/
	
	private int left;
	private int right;
	private int min;
	
	private SecondOrderMinimum next;
	private SecondOrderMinimum previousLeft;
	private SecondOrderMinimum previousRight;

	
	public SecondOrderMinimum( int left ) {
		this.left = left;
		this.next = null;
		this.min = -1;
		this.previousLeft = null;
		this.previousRight = null;

	}
	
	public SecondOrderMinimum ( int left, int right ) {
		this.left = left;
		this.right = right;
		this.min = min( left, right );
		this.next = null;
		this.previousRight = null;
		this.previousLeft = null;
	}
	
	public void setRight( int right ) {
		this.right = right;
		this.min = min( left, right );
	}
	
	public void setMin ( SecondOrderMinimum min ) {
		this.next = min;
	}
	
	public int getMin() {
		return ( this.min != -1 ) ? this.min : min( this.left, this.right );
	}
	
	public static int[] getTestArray() {
		return testArray;
	}
	
	public boolean hasNext() {
		return ( this.next != null ) ? true : false ;
	}
	
	public boolean hasPrevious() {
		return ( this.previousLeft != null || this.previousRight != null ) ? true : false;
	}
	
	public void setPrevious( SecondOrderMinimum prevLeft, SecondOrderMinimum prevRight ) {
		this.previousLeft = prevLeft;
		this.previousRight = prevRight;
	}
	
	public boolean hasValue( int a ) {
		return (this.left == a || this.right == a) ? true : false;
	}
	
	public boolean minIsLeft() {
		return (this.getMin() == this.left) ? true : false;
	}
	
	public SecondOrderMinimum getPreviousLeft() {
		return this.previousLeft;
	}

	public SecondOrderMinimum getPreviousRight() {
		return this.previousRight;
	}
	
	public SecondOrderMinimum getMinPrevious() {
		return (this.getMin() == this.left) ? this.previousLeft : this.previousRight;
	}

	public SecondOrderMinimum getNonMinPrevious() {
		return (this.getMin() == this.left) ? this.previousRight : this.previousLeft;
	}
	
	public int getNonMinimum() {
		return (this.getMin() == this.left) ? this.right : this.left;
	}
	
	
	/*** Algorithm ***/
	
	public static void secondOrderMinimumAlgorithm( int[] array ) {
		// Tournament Tree to find min
		SecondOrderMinimum min;
		int secondMin = -1;
		
		int n = array.length;
		int k = (int) (Math.log(n)/Math.log(2));
		
		ArrayList<ArrayList<SecondOrderMinimum>> list = new ArrayList<ArrayList<SecondOrderMinimum>>();
		
		// Populate Tournament Level 1
		for( int i = 0; i < k; i++ ) {
			list.add(new ArrayList<SecondOrderMinimum>()); 
		}
		
		// Initialize k=1
		for( int i = 0; i < n; i=i+2) {
			list.get(0).add(new SecondOrderMinimum( array[i], array[i+1] ));
		}
		
		/*** Find minimum in (n-1) comparisons ***/
		// Run tournament for Minimum
		for( int i = 0; i < (k-1); i++ ) {
			ArrayList<SecondOrderMinimum> currentLevel = list.get(i);
			
			for( int j = 0; j < Math.pow(2.0, (double)(k-i-1)); j=j+2 ) {
				SecondOrderMinimum currentNodeL = currentLevel.get(j);
				SecondOrderMinimum currentNodeR = currentLevel.get(j+1);
				
				if( !currentNodeL.hasNext() ) {  
					SecondOrderMinimum newNode = new SecondOrderMinimum(currentNodeL.getMin(), currentNodeR.getMin());
					currentNodeL.setMin(newNode);
					currentNodeR.setMin(newNode);
					
					newNode.setPrevious(currentNodeL, currentNodeR);
					
					list.get(i+1).add(newNode);
				}
			}
		}
		
		// Find the highest level node containing the absolute minimum
		min = list.get(k-1).get(0);
		
		if(VERBOSE) 
			System.out.println("Min: " + min.getMin());
		
		/*** Calculate Second Minimum in (k-1) comparisons***/
		secondMin = min.getNonMinimum();
		
		while( min.hasPrevious() ) {
			if (secondMin > min.getMinPrevious().getNonMinimum() )
				secondMin = min.getMinPrevious().getNonMinimum();
			
			min = min.getMinPrevious(); 
		}
		
		if(VERBOSE) 
			System.out.println("Second Min: " + secondMin );
		
		// Total Comparisons = (n - 1) + (k - 1) = n - 1 + k - 1 = n + k - 2
	}
	
	private static int min( int a, int b ) {
		return ( a < b) ? a : b;
	}
	
}
