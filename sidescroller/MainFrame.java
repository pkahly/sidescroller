package sidescroller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class MainFrame extends JFrame implements ActionListener
{
   int levelNumber;
   JComboBox<String> levelList;
   
   JButton selectButton;
   JButton cancelButton;
   
   
   ///////////////////////////////////////////////
   MainFrame()
   {
      Container cp;
      JPanel levelPane;
      JPanel buttonPane;
      
      levelList = new JComboBox<String>();
      levelList.addItem( "Level one: Cyclops Field" );
      levelList.addItem( "Level two: Sky hop" );
      levelList.addItem( "Level three: Falling sky" );
      levelList.addItem( "Level four: Cyclops Boss Chase" );
      levelList.addItem( "Level five: Fireball stairs" );
      levelList.addItem( "Level six: Boss Battle" );
      
      levelPane = new JPanel();
      levelPane.add( levelList );
      
      selectButton = new JButton( "Play!" );
      selectButton.setActionCommand("SELECT");
      selectButton.addActionListener(this);
      
      cancelButton = new JButton( "Quit" );
      cancelButton.setActionCommand("CANCEL");
      cancelButton.addActionListener(this);
      
      buttonPane = new JPanel();
      buttonPane.add( selectButton );
      buttonPane.add( cancelButton );
      
      cp = getContentPane();
      cp.add( levelPane, BorderLayout.CENTER );
      cp.add( buttonPane, BorderLayout.SOUTH );
      
      getRootPane().setDefaultButton(selectButton);
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
      setSize(d.width/4, d.height/4);
      setLocation(d.width/4, d.height/4);
      
      setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
      
      setTitle("Sidescroller");
      setResizable(false);
      setVisible(true);
   }
   
   
   /////////////////////////////////////////////////
   public void actionPerformed( ActionEvent e )
   {
      String cmd;
      
      cmd = e.getActionCommand();
      
      if ( cmd.equals("SELECT") )
      {
         levelNumber = levelList.getSelectedIndex();
         levelNumber += 1; // Make 1 based
         
         new GameScreen( levelNumber );
      }
      else if ( cmd.equals("CANCEL") )
      {
         System.exit(0);
      }
      else
         System.out.println( "Unrecognized ActionCommand: " + cmd );
   }
}
