import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

//file reader class
public class Reader {
    
    //class attributes
    int total_lines; //lines in file
    int size;		 //file size in bytes
    ArrayList<String> instructions; //operations derived from expression(if any)
    
    //read file data
    public int read_file(String path) throws FileNotFoundException, IOException {
        try{
        	FileReader fr = new FileReader(path+".txt");
        	File f = new File(path+".txt");
        	size = (int)f.length();
        	String line;
        	//get each line
        	BufferedReader buffer = new BufferedReader(fr); 
    		total_lines = 0;
    		instructions = new ArrayList<String>();
        
    		while( (line = buffer.readLine()) != null){
    			instructions.add(line);
    			total_lines++;
    		}
        	buffer.close();	
        }catch(FileNotFoundException e){
        	return -1;
        }
        return 1;
    }
    
    //utility dump method to check read data
    public void dump_file() {
    	for(int i=0;i<total_lines;i++) {
            System.out.println(instructions.get(i));
        }
    }
}