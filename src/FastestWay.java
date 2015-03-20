import java.util.Arrays;


//core functionality class
public class FastestWay {
	//member variables
	int total_stations;
	//sub-problem solutions
	int[] f1;
	int[] f2;
	//times on each station
	int[] a1;
	int[] a2;
	//cross-over times
	int[] t1;
	int[] t2;
	//path taken for each line
	int[] l1;
	int[] l2;
	//entry times
	int e1;
	int e2;
	//exit times
	int x1;
	int x2;
	//final answers
	int f_star;
	int l_star;
	int[] path;
	
	//setter method
	public void setter(Reader fr) {
		String entry = fr.instructions.get(0);
		String exit = fr.instructions.get(1);
		String l1_times = fr.instructions.get(2);
		String l2_times = fr.instructions.get(3);
		String t1_times = fr.instructions.get(4);
		String t2_times = fr.instructions.get(5);
		
		//entry times
		String[] tokens = entry.split(" ");
		e1 = Integer.parseInt(tokens[0]);
		e2 = Integer.parseInt(tokens[1]);
		
		//exit times
		tokens = exit.split(" ");
		x1 = Integer.parseInt(tokens[0]);
		x2 = Integer.parseInt(tokens[1]);
		
		//station times
		tokens = l1_times.split(" ");
		a1 = new int[tokens.length];
		total_stations = tokens.length;
		for(int i=0; i<tokens.length; i++)
			a1[i] = Integer.parseInt(tokens[i]);
	
		tokens = l2_times.split(" ");
		a2 = new int[tokens.length];
		for(int i=0; i<tokens.length; i++)
			a2[i] = Integer.parseInt(tokens[i]);
		
		//line transfer times
		tokens = t1_times.split(" ");
		t1 = new int[tokens.length];
		for(int i=0; i<tokens.length; i++)
			t1[i] = Integer.parseInt(tokens[i]);
		
		tokens = t2_times.split(" ");
		t2 = new int[tokens.length];
		for(int i=0; i<tokens.length; i++)
			t2[i] = Integer.parseInt(tokens[i]);
		
		//sub-problem arrays
		f1 = new int[tokens.length + 1];
		f2 = new int[tokens.length + 1];
		l1 = new int[tokens.length + 1];
		l2 = new int[tokens.length + 1];
		
	}
	
	//utility print method
	public void dump() {
		System.out.println("Entry times: "+e1+" "+e2);
		System.out.println("Exit times: "+x1+" "+x2);
		System.out.println("Line 1 times: "+Arrays.toString(a1));
		System.out.println("Line 2 times: "+Arrays.toString(a2));
		System.out.println("Transfer 1 times: "+Arrays.toString(t1));
		System.out.println("Transfer 2 times: "+Arrays.toString(t2));
		System.out.println("Final Path: "+Arrays.toString(path));
		System.out.println("F*: "+f_star);
		System.out.println("L*: "+l_star);
	}

	//fastest way algorithm
	public void solver() {
		
		//init values of first stations
		f1[0] = e1 + a1[0];
		f2[0] = e2 + a2[0];
		
		//main loop, solve every sub-problem
		for(int j=1; j<total_stations; j++){
			
			if(f1[j-1] + a1[j] <= f2[j-1] + t2[j-1] + a1[j]){
				f1[j] = f1[j-1] + a1[j];
				l1[j-1] = 1;
			}
			else{
				f1[j] = f2[j-1] + t2[j-1] + a1[j];
				l1[j-1] = 2;
			}
			
			if(f2[j-1] + a2[j] <= f1[j-1] + t1[j-1] + a2[j]){
				f2[j] = f2[j-1] + a2[j];
				l2[j-1] = 2;
			}
			else{
				f2[j] = f1[j-1] + t1[j-1] + a2[j];
				l2[j-1] = 1;
			}
		}
		
		//if line 1 exit chosen
		if(f1[total_stations - 1] + x1 <= f2[total_stations - 1] + x2){
			f_star = f1[total_stations - 1] + x1;
			l_star = 1;
			path = l1;
		}
		//if line 2 exit chosen
		else{
			f_star = f2[total_stations - 1] + x2;
			l_star = 2;
			path = l2;
		}
		//contains final path from station to station
		path[total_stations - 1] = l_star;
	}
}
