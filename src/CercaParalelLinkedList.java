import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


public class CercaParalelLinkedList {

	public List<Integer> q;
	public AtomicInteger index;
	
	
	public CercaParalelLinkedList(List<Integer> q){
		this.q = q;
		index= new AtomicInteger();
		index.set(-1);
	}
	
	
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
	        		//System.out.println(q.get(i));
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
	        		//System.out.println(q.get(i));
	        	}
	        }
	    };
	    
	    startThread.start();
	    endThread.start();
		
	    try {
			startThread.join();
			endThread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return index.get();
	}
}
