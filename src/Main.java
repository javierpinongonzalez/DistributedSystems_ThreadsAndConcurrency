import java.util.Collections;
import java.util.LinkedList;
import java.util.List;


public class Main {
		
	public static void main(String [ ] args)
	{
		//2:
		System.out.println(cercaParalelaLinkedList(734, 1000));
		
		//3:
		System.out.println(cercaParallelaMultiThread(13, 7, 3));
		
		//6.1:
		mergeSortThread();
		
		//6.2:
		mergeSortSquential();
		
		
	}
	
	/*
 	 *
	 * Realitza una cerca en paral·lel 
	 *
	 */
	public static int cercaParalelaLinkedList(int aCercar,int listLen){
		List<Integer> q = Collections.synchronizedList(new LinkedList<Integer>());
		
		for (int i=0; i<listLen ; i++){
			q.add(i*2);
		}
		
		CercaParalelLinkedList cerca = new CercaParalelLinkedList(q);
		return cerca.doCerca(aCercar);
	}
	
	/*
 	 *
	 * Realitza una cerca en paral·lel multithreading
	 *
	 */
	public static int cercaParallelaMultiThread(int aBuscar, int arrayLen, int numThreads){
		
		int[] array = new int[arrayLen];
		
		for (int i = 0 ; i<arrayLen ; i++){
			array[i] = i*2; 
		}
		
		cercaParalelMultiThread cerca = new cercaParalelMultiThread(array);
		
		return cerca.doCerca(aBuscar, numThreads);
		
	}
	
	/*
 	 *
	 * Ordena un array utilitzan l'algoritme MergeSort multithreading
	 *
	 */
	public static void mergeSortThread (){
		int[] array = { 2, 6, 3, 5, 1, 423, 21, 142, 423,123,543,523,623,23,6223,425,2436243,524,6342,62,346,2345,2345,234,523,6234,623462346,457,3,82462 };
		
		MergeSortThread mergeSortThread = new MergeSortThread(array, 0, array.length);
		
		
		long startTime = System.nanoTime();
		
		mergeSortThread.run();

		
		try {
			mergeSortThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		long endTime = System.nanoTime();
		System.out.println("Took "+(endTime - startTime) + " ns"); 
	}
	
	/*
 	 *
	 * Ordena un array utilitzan l'algoritme MergeSort seqüencial
	 *
	 */
	public static void mergeSortSquential (){
		int[] array = { 2, 6, 3, 5, 1, 423, 21, 142, 423,123,543,523,623,23,6223,425,2436243,524,6342,62,346,2345,2345,234,523,6234,623462346,457,3,82462 };
		
		
		MergeSortSequential mergeSortSequential = new MergeSortSequential();
		
		
		long startTime = System.nanoTime();
		
		mergeSortSequential.sort(array);
		
		long endTime = System.nanoTime();
		System.out.println("Took "+(endTime - startTime) + " ns"); 
	}
}
