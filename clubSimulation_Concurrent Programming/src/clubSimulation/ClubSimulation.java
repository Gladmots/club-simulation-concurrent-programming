
package clubSimulation;
// the main class, starts all threads
import javax.swing.*;
import java.lang.reflect.Method;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class ClubSimulation {
   static int noClubgoers=20;
   static int frameX=400;
   static int frameY=500;
   static int yLimit=400;
   static int gridX=10; //number of x grids in club - default value if not provided on command line
   static int gridY=10; //number of y grids in club - default value if not provided on command line
   static int max=5; //max number of customers - default value if not provided on command line
	
   static Clubgoer[] patrons; // array for customer threads
   static PeopleLocation [] peopleLocations;  //array to keep track of where customers are
	
   static PeopleCounter tallys; //counters for number of people inside and outside club

   static ClubView clubView; //threaded panel to display terrain
   static ClubGrid clubGrid;// club grid
   static CounterDisplay counterDisplay ; //threaded display of counters

   private static int maxWait=1200; //for the slowest customer
   private static int minWait=500; //for the fastest customer
    
   private static AtomicBoolean pauseFlag = new AtomicBoolean(false);

   public static void setupGUI(int frameX,int frameY,int [] exits, final Object startLock) {
   	// Frame initialize and dimensions
      JFrame frame = new JFrame("club animation"); 
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setSize(frameX, frameY);
    	
      JPanel g = new JPanel();
      g.setLayout(new BoxLayout(g, BoxLayout.PAGE_AXIS)); 
      g.setSize(frameX,frameY);
       
      clubView = new ClubView(peopleLocations, clubGrid, exits);
      clubView.setSize(frameX,frameY);
      g.add(clubView);
       
       //add all the counters to the panel
      JPanel txt = new JPanel();
      txt.setLayout(new BoxLayout(txt, BoxLayout.LINE_AXIS)); 
      JLabel maxAllowed =new JLabel("Max: " + tallys.getMax() + "    ");
      JLabel caught =new JLabel("Inside: " + tallys.getInside() + "    ");
      JLabel missed =new JLabel("Waiting:" + tallys.getWaiting()+ "    ");
      JLabel scr =new JLabel("Left club:" + tallys.getLeft()+ "    ");    
      txt.add(maxAllowed);
      txt.add(caught);
      txt.add(missed);
      txt.add(scr);
      g.add(txt);
      counterDisplay = new CounterDisplay(caught, missed,scr,tallys);      //thread to update score
       
       //Add start, pause and exit buttons
      JPanel b = new JPanel();
      b.setLayout(new BoxLayout(b, BoxLayout.LINE_AXIS)); 
      final JButton startB = new JButton("Start");
        
        //instantiate clubgoer
      
      
        
        
   	// add the listener to the jbutton to handle the "pressed" event
      startB.addActionListener(
         new ActionListener() {
            public void actionPerformed(ActionEvent e)  {
             	  	// THIS DOES NOTHING - MUST BE FIXED  
            
               synchronized (startLock) {
                  startLock.notifyAll();
               }
            
                   // Enable other UI components or buttons
               startB.setEnabled(false);
                   //pauseB.setEnabled(true);
                   // ... (enable other components)
            
                   // Start the simulation for each thread
                 
               for (Clubgoer patron : patrons) {
                  try {
                     patron.startSim();
                  } catch (InterruptedException e1) {
                  	// TODO Auto-generated catch block
                     e1.printStackTrace();
                  }
               	
               }
                   
            }
         }
         
         	);
   	
      final JButton pauseB = new JButton("Pause ");;
   		
   		// add the listener to the jbutton to handle the "pressed" event
      pauseB.addActionListener(
         new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               synchronized (pauseFlag) {
                  pauseFlag.set(!pauseFlag.get()); // Toggle pauseFlag
                  if (!pauseFlag.get()) {
                     pauseFlag.notifyAll(); // Notify waiting threads
                  }
               }
            }
         });
   		
   		
      JButton endB = new JButton("Quit");
   			// add the listener to the jbutton to handle the "pressed" event
      endB.addActionListener(
         new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               System.exit(0);
            }
         });
   
      b.add(startB);
      b.add(pauseB);
      b.add(endB);
   	
      g.add(b);
    	
      frame.setLocationRelativeTo(null);  // Center window on screen.
      frame.add(g); //add contents to window
      frame.setContentPane(g);     
      frame.setVisible(true);	
   }
	
	

   public static void main(String[] args) throws InterruptedException {
   	
   	//deal with command line arguments if provided
      if (args.length==4) {
         noClubgoers=Integer.parseInt(args[0]);  //total people to enter room
         gridX=Integer.parseInt(args[1]); // No. of X grid cells  
         gridY=Integer.parseInt(args[2]); // No. of Y grid cells  
         max=Integer.parseInt(args[3]); // max people allowed in club
      }
   	
   	//hardcoded exit doors
      int [] exit = {0,(int) gridY/2-1};  //once-cell wide door on left
   			
      tallys = new PeopleCounter(max); //counters for people inside and outside club
      int maxCapacity = max;
      clubGrid = new ClubGrid(gridX, gridY, exit,tallys, maxCapacity); //setup club with size and exits and maximum limit for people    
   
      Clubgoer.club = clubGrid; //grid shared with class
      
      peopleLocations = new PeopleLocation[noClubgoers];
       //PeopleLocation[] barpersonLocation = new PeopleLocation[noClubgoers];
      patrons = new Clubgoer[noClubgoers];
   	
      Random rand = new Random();
   	///---------
      final Object startLock = new Object();
   
           // Create an array of Clubgoer instances
      Clubgoer[] patrons = new Clubgoer[noClubgoers];
           
      int numPeople = 0;
   		// Create and start Clubgoer threads
      for (int i = 0; i < numPeople; i++) {
         int movingSpeed = 0;
         PeopleLocation peoplelocations = null;
      		// ... (other initialization)
         Clubgoer patron = new Clubgoer(i, peoplelocations, movingSpeed, startLock, clubGrid);
         patron.start();
      }
           
      for (int i=0;i<noClubgoers;i++) {
         peopleLocations[i]=new PeopleLocation(i);
         int movingSpeed=(int)(Math.random() * (maxWait-minWait)+minWait); //range of speeds for customers
         patrons[i] = new Clubgoer(i,peopleLocations[i],movingSpeed,startLock, pauseFlag);
      }
    
        
        
      setupGUI(frameX, frameY,exit,startLock);  //Start Panel thread - for drawing animation
        //start all the threads
   	
      Thread t = new Thread(clubView); 
      t.start();
      	//Start counter thread - for updating counters
      Thread s = new Thread(counterDisplay);  
      s.start();
      	
      for (int i=0;i<noClubgoers;i++) {
         patrons[i].start();
      }
   }

}
