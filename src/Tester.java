import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

//tester class for assembly line program
public class Tester {
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		
		//read file data
		Reader fr = new Reader();
		fr.read_file("data");		//enter file path in argument
		
		//create core object
		final FastestWay obj = new FastestWay();
		//set values received from file
		obj.setter(fr);
		//solve using fastest way algorithm
		obj.solver();
		//print results on console
		obj.dump();
		
		//GUI here
		SwingUtilities.invokeLater(new Runnable(){
			@Override
			public void run() {
				// create JFrame window
				JFrame window;
				try {
					//set window properties
					window = new MainFrame("Assembly Line", obj);
					window.setSize(800, 600);
					window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					window.setVisible(true);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
	}

}
