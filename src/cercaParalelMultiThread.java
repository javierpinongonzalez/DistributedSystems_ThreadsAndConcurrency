import java.util.concurrent.atomic.AtomicInteger;

/*
 *
 * Classe que realitza una cerca en paral·lel multithread d'una llista
 *
 */
public class cercaParalelMultiThread {
	public AtomicInteger index;
	public int[] array;
	
	/*
 	 *
	 * Constructor
	 *
	 */
	public cercaParalelMultiThread (int[] array){
		this.array = array;
		index = new AtomicInteger();
		index.set(-1);
	}

	/*
 	 *
	 * Realitza la cerca en paral·lel multithread
	 *
	 */
	public int doCerca(final int aBuscar, final int numThreads) {
		Thread [] threads = new Thread[numThreads];
		final int [] limits = new int[numThreads];
		
		for (int i = 0; i<numThreads;i++){
			limits[i]  = array.length*(i+1) / numThreads;
		}
		
		
		for (int i =0; i<numThreads ; i++){
			final int j = i;
			threads[i] = new Thread(){
		        public void run(){
		        	if (j == 0){
		        		for (int k = 0; k < limits[j] ; k++){
		        			if (array[k] == aBuscar){
			        			synchronized (index) {
			    	        		index.set(k);
			    	        	}
			        			return;
			        		}
			        	}
		        	}else{
		        		for (int k = limits[j-1]; k < limits[j] ; k++){
		        			if (array[k] == aBuscar){
			        			synchronized (index) {
			    	        		index.set(k);
			    	        	}
			        			return;
			        		}
			        	}
		        	}
		        	
		        }
		    };
		    threads[i].start();
		}
		
		for (int i =0; i<numThreads ; i++){
			try {
				threads[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		return index.get();
	}
	
	public int doCercaSharedMem(final int aBuscar, final int numThreads) {
		return -1;
	}
}
