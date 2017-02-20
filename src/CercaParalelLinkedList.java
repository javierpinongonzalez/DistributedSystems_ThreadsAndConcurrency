import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/*
 *
 * Classe que realitza una cerca en paral·lel d'una llista
 *
 */
public class CercaParalelLinkedList {

	public List<Integer> q;
	public AtomicInteger index;
	
	/*
 	 *
	 * Constructor
	 *
	 */
	public CercaParalelLinkedList(List<Integer> q){
		this.q = q;
		index= new AtomicInteger();
		index.set(-1);
	}
	
	/*
 	 *
	 * Realitza la cerca en paral·lel
	 *
	 */
	public int doCerca(final int aCercar){	
		
		Thread startThread = new Thread(){
	        public void run(){ 
	        	int limit;
	        	
	        	if (q.size() % 2 == 0){
	        		limit = q.size()/2 - 1;
	        	}else{
	        		limit = q.size()/2;
	        	}   	
	        	for (int i = 0; i<=limit ; i++){
	        		if (q.get(i) == aCercar){
	        			synchronized (index) {
	    	        		index.set(i);
	    	        	}
	        			return;
	        		}
	        	}
	        	
	        }
	    };
		
	    Thread endThread = new Thread(){
	        public void run(){
	        	int limit;
	        	
	        	if (q.size() % 2 == 0){
	        		limit = q.size()/2;
	        	}else{
	        		limit = q.size()/2+1;
	        	}
	        	
	        	for (int i = limit; i<q.size(); i++){
	        		if (q.get(i) == aCercar){
	        			synchronized (index) {
	    	        		index.set(i);
	    	        	}
	        			return;
	        		}
	        	}
	        }
	    };
	    
	    startThread.start();
	    endThread.start();
		
	    try {
			startThread.join();
			endThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		return index.get();
	}
}
