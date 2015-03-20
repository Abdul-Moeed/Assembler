import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

//Graphical User Interface class

public class MainFrame extends JFrame{
	
	//member variables
	private static final long serialVersionUID = 1L;
	int i=1; 		//global counter
	JLabel prev;	//previous station in path
	
	//constructor
	public MainFrame(String title, final FastestWay obj) throws IOException{
		super(title);
		getContentPane().setLayout(null);
		
		//start button component
		JButton btnStart = new JButton("Next");
		btnStart.setBounds(320, 470, 89, 23);
		getContentPane().add(btnStart);
		
		//action listener on button
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int x, x_axis,y_axis;
				JLabel station, parking, cross;
				BufferedImage img_car, img_cross;
				try {
					img_cross = ImageIO.read(new File("cross.png"));
					
					//if final station, exit
					if(i == obj.total_stations+1){
						cross = new JLabel(new ImageIcon(img_cross));
						prev.setVisible(false);
						cross.setBounds(prev.getX(), prev.getY(), 80, 80);
						getContentPane().add(cross);
						return;
					}
					//get image and calculate parking location for station
					img_car = ImageIO.read(new File("part"+i+".png"));
					x = (obj.path[i-1] - 1)*obj.total_stations;
					x = x+i;
					
					//get station details
					station = (JLabel)getContentPane().getComponent(x);
					station.getPreferredSize().getHeight();
					x_axis = station.getX();
					y_axis = station.getY();
					
					//add suitable parking component
					parking = new JLabel(new ImageIcon(img_car));
					parking.setBounds(x_axis,y_axis + 110, 80, 80);
					getContentPane().add(parking);
					
					//if not the first station, mark previous station in path
					if(i != 1){
						cross = new JLabel(new ImageIcon(img_cross));
						prev.setVisible(false);
						cross.setBounds(prev.getX(), prev.getY(), 80, 80);
						getContentPane().add(cross);
					}
					//store previous station
					prev = parking;
					//repaint window
					getContentPane().revalidate();
					getContentPane().repaint();
					i++;
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		//read assembler image
		BufferedImage img_station = ImageIO.read(new File("assembler.png"));
		
		//print stations of line 1
		JLabel lblS;
		int baseX = 62;
		for(int i=0; i<obj.total_stations; i++){
			lblS = new JLabel(new ImageIcon(img_station));
			lblS.setBounds(baseX, 27, 100, 80);			
			baseX += 124;
			getContentPane().add(lblS);
		}
		
		//print stations of line 2
		baseX = 62;
		for(int i=0; i<obj.total_stations; i++){
			lblS = new JLabel(new ImageIcon(img_station));
			lblS.setBounds(baseX, 260, 100, 80);
			baseX += 124;
			getContentPane().add(lblS);
		}
		
		//station times and switching times for line 1
		baseX = 62;
		JLabel times, transfer;
		for(int i=0; i<obj.total_stations; i++){
			times = new JLabel();
			times.setBounds(baseX, 110, 100, 20);
			times.setText("Service Time: "+Integer.toString(obj.a1[i]));
			transfer = new JLabel();
			transfer.setBounds(baseX, 130, 100, 20);
			if(i!=obj.total_stations-1)
				transfer.setText("Switch Time: "+Integer.toString(obj.t1[i]));
			else
				transfer.setText("Exit Time: "+Integer.toString(obj.x1));
			baseX += 124;
			getContentPane().add(transfer);
			getContentPane().add(times);
		}
		
		//station times and switching times for line 2
		baseX = 62;
		for(int i=0; i<obj.total_stations; i++){
			times = new JLabel();
			times.setBounds(baseX, 350, 100, 20);
			times.setText("Service Time: "+Integer.toString(obj.a2[i]));
			transfer = new JLabel();
			transfer.setBounds(baseX, 370, 100, 20);
			if(i!=obj.total_stations-1)
				transfer.setText("Switch Time: "+Integer.toString(obj.t2[i]));
			else
				transfer.setText("Exit Time: "+Integer.toString(obj.x2));
			baseX += 124;
			getContentPane().add(transfer);
			getContentPane().add(times);
		}
		
		//entrance labels and times for line 1 and line 2
		JLabel lblEntry = new JLabel("Entry");
		lblEntry.setBounds(0, 170, 100, 80);
		getContentPane().add(lblEntry);
		JLabel lble = new JLabel();
		lble.setBounds(5, 170, 100, 20);
		lble.setText("Line 1: "+Integer.toString(obj.e1));
		getContentPane().add(lble);
		lble = new JLabel();
		lble.setBounds(5, 230, 100, 20);
		lble.setText("Line 2: "+Integer.toString(obj.e2));
		getContentPane().add(lble);
		
		//exit label component
		JLabel lblExit = new JLabel("Exit");
		lblExit.setBounds(700, 170, 100, 80);
		getContentPane().add(lblExit);
		
		//total cost component
		JLabel lblcost = new JLabel();
		lblcost.setBounds(324, 495, 89, 23);
		lblcost.setText("Total Cost: "+Integer.toString(obj.f_star));
		getContentPane().add(lblcost);
	}
}
