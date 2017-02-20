/*
 *
 * Classe que implementa l'algoritme MergeSort amb threads
 *
 */
public class MergeSortThread extends Thread{
	public int[] array;
	public int indexInici;
	public int indexFinal;
	
	/*
 	 *
	 * Constructor
	 *
	 */
	public MergeSortThread (int[] array, int indexInici, int indexFinal){
		this.array = array;
		this.indexInici = indexInici;
		this.indexFinal = indexFinal;
		
	}
	
	/*
 	 *
	 * Sobreescriu el mètode Run de la classe Thread
	 *
	 */
	@Override
	public void run(){
		int mySize = this.indexFinal - this.indexInici;
		if ( mySize > 2 ) {
			int SP1 = this.indexInici;
			int EP1 = this.indexInici + (mySize / 2);
			int SP2 = EP1;
			int EP2 = this.indexFinal;
			MergeSortThread th1 = new MergeSortThread(this.array, SP1, EP1);
			MergeSortThread th2 = new MergeSortThread(this.array, SP2, EP2);
			th1.start();	 
			th2.start();	
			try {
				th1.join();
				th2.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			this.mergeArrays(SP1, EP1, SP2, EP2);
		} else {
			this.merge();
		}
	}
	
	/*
 	 *
	 * Mètode que organitza valors
	 *
	 */
	private void merge() {
		if ( this.array[this.indexInici] > this.array[(this.indexFinal-1)]) {
			this.swap(this.indexInici, (this.indexFinal-1));
		}		
	}

	/*
 	 *
	 * Mètode que organitza arrays
	 *
	 */
	private void mergeArrays(int SP1, int EP1, int SP2, int EP2) {
		int lIndex = SP1;
		int rIndex = SP2;
		int tmpIdx = 0;
		int retArray[] = new int[(EP2-SP1)];
		while ( lIndex < EP1 && rIndex < EP2 ) {
			if ( this.array[lIndex] < this.array[rIndex] ) {
				retArray[tmpIdx] = this.array[lIndex];
				lIndex++;
			} else {
				retArray[tmpIdx] = this.array[rIndex];
				rIndex++;
			}
			tmpIdx++;
		}
		while( rIndex < EP2 ) {
			retArray[tmpIdx] = this.array[rIndex];
			rIndex++; tmpIdx++;
		}
		while( lIndex < EP1 ) {
			retArray[tmpIdx] = this.array[lIndex];
			lIndex++; tmpIdx++;
		}
		tmpIdx = 0;
		for (int i = SP1; i<EP2; i++) {
			this.array[i] = retArray[tmpIdx];
			tmpIdx++;
		}
		
	}

	/*
 	 *
	 * Canvia els valors de lloc en cas d'estar desordenats
	 *
	 */
	private void swap (int num1, int num2) {
		int tmp = this.array[num1];
		this.array[num1] = this.array[num2];
		this.array[num2] = tmp;
	}
}
