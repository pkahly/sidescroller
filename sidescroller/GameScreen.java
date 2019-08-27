package sidescroller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class GameScreen extends JDialog implements ActionListener
{
   GraphicsPanel playingArea;
   Dude dude;
   Level level;
   javax.swing.Timer timer;
   
   
   /////////////////////////////////////////////////////
   GameScreen( int levelNumber )
   {
      level = LevelLoader.loadLevel( levelNumber );
  
      dude = new Dude( 230, 100 );
      //dude = new Dude( 6200, 200 );
      level.addSprite( dude );
      
      playingArea = new GraphicsPanel(level);
      
      // Start timer //
      timer = new javax.swing.Timer(30, this);
      timer.setRepeats(true);
      timer.setCoalesce(true);
      timer.setActionCommand("TIMER");
      timer.start();

      this.addKeyListener(dude);
      
      // Draw window //
      getContentPane().add(playingArea);
      setupMainFrame();
   }
   
   
   /////////////////////////////////////////////////////
   void setupMainFrame()
   {
      Toolkit tk;
      Dimension d;
      
      tk = Toolkit.getDefaultToolkit();
      d = tk.getScreenSize();
      
      //set size and location
      setSize(810, 620);
      setLocation(d.width/8, d.height/8);
      
      setDefaultCloseOperation( JDialog.DISPOSE_ON_CLOSE );
      setModal(true);
      
      setTitle("Sidescroller");
      setResizable(false);
      setVisible(true);
   }
   
   
   ///////////////////////////////////////////////////////
   public void actionPerformed( ActionEvent e )
   {
      String cmd;
      
      cmd = e.getActionCommand();
      
      if ( cmd.equals("TIMER") )
      {
         level.update();
         playingArea.repaint();
         
         if ( dude.getX() > 10000 && !dude.isDead() ) // uses isDead variable instead of method
         {
            JOptionPane.showMessageDialog( this, "You won the game!" );
            timer.stop();
            dispose();
         }
         else if ( dude.getY() > 620 ) // if he has fallen out of the map
         {
            JOptionPane.showMessageDialog( this, getRandomDeathLine() );
            timer.stop();
            dispose();
         }
      }
      else
         System.out.println("Unrecognized ActionCommand: " + cmd );
   }
   
   
   /////////////////////////////////////////////////////////////
   String getRandomDeathLine()
   {
      return "LOLOLOLOLOLOLOLOLOL";
   }
}
