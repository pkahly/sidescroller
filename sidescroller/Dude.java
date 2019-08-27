package sidescroller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class Dude extends Sprite implements KeyListener, ActionListener
{
   Image dudeWestImage;
   Image dudeEastImage;
   Image dudeCrouchEastImage;
   Image dudeCrouchWestImage;
   Image dudeDeadImage;
   
   boolean a_keyOn;
   boolean d_keyOn;
   boolean s_keyOn;
   
   javax.swing.Timer crouchTimer;
   
   
   //////////////////////////////////////////////////////////
   Dude( int x_pos, int y_pos )
   {
      dudeWestImage = LevelLoader.getImage("sprite_west.png");
      dudeEastImage = LevelLoader.getImage("sprite_east.png");
      dudeCrouchEastImage = LevelLoader.getImage("sprite_crouch_east.png");
      dudeCrouchWestImage = LevelLoader.getImage("sprite_crouch_west.png");
      dudeDeadImage = LevelLoader.getImage("sprite_dead.png");
      
      this.x_pos = x_pos;
      this.y_pos = y_pos;
      
      height = 100;
      width = 50;
      
      x_direction = 0;
      y_direction = 0;
      
      x_speed = 0;
      y_speed = 0;
      
      a_keyOn = false;
      d_keyOn = false;
      s_keyOn = false;
      
      type = "CHARACTER";

      crouchTimer = new javax.swing.Timer(60, this);
      crouchTimer.setRepeats( false );
      crouchTimer.setActionCommand("CROUCH_OFF");
   }
   
   
   ///////////////////////////////////////////////////////
   public Image getImage()
   {
      if ( s_keyOn && x_direction == WEST && !isDead )
         return dudeCrouchWestImage;
      else if ( s_keyOn && !isDead )
         return dudeCrouchEastImage;
      else if ( x_direction == WEST && !isDead )
         return dudeWestImage;
      else if ( !isDead )
         return dudeEastImage;
      else if ( isDead )
         return dudeDeadImage;
      
      return null;
   }
   
   
   ///////////////////////////////////////////////////////
   public void update( Level level )
   {
      // Update Coordinates //
      x_pos += x_direction * x_speed;
      y_pos += y_direction * y_speed;
      
      // Process falling //
      if ( !isOnGround && !isDead() )
      {
         if ( y_direction != SOUTH && y_speed > 0)
         {
            y_speed -= 1;
         }
         else if ( y_direction != SOUTH && y_speed <= 0 )
         {
            y_direction = SOUTH;
            y_speed = 1;
         }
         else if ( y_direction == SOUTH )
            y_speed += 1;
         else
            System.out.println("Direction = " + y_direction + "    Speed = " + y_speed );
      }
      else if ( !isOnGround && isDead() )
      {
         y_speed = 0;
         x_speed = 0;
         
         y_pos += 6;
      }
          
      // Process a or d key being held down //
      if ( a_keyOn || d_keyOn )
      {
         x_speed += 1;
      }
      // Slow down forward or backward movement //
      else if ( x_speed > 1 )
         x_speed -= 2;
      else 
         x_speed = 0;
      
      // Limit speed //
      if ( x_speed > 15 )
         x_speed = 15;

      if ( y_speed > 30 )
         y_speed = 30;
      
      level.moveXOffset( this );
      isOnGround = false;
   }
   
   
   //////////////////////////////////////////////////////
   public void keyPressed( KeyEvent e )
   {
      char key;
      
      key = e.getKeyChar();
      
      if (key == 'w' || key == 'W') // jump
      {
         if ( isOnGround ) // if dude is on the ground
         {
            y_direction = NORTH;
            y_speed = x_speed + 15;
            
            if ( y_speed > 20 )
            {
               y_speed = 20;
            }
         }
      }
      else if ( (key == 'd' || key == 'D') && !d_keyOn && !a_keyOn && !s_keyOn ) // move forward
      {
         x_direction = EAST;
         x_speed += 3;
         d_keyOn = true;         

         if ( x_speed > 15 )
            x_speed = 15;
      }
      else if ( (key == 'a' || key == 'A') && !a_keyOn && !d_keyOn && !s_keyOn ) // move backward
      {
         x_direction = WEST;
         x_speed += 3;
         a_keyOn = true;

         if ( x_speed > 15 )
            x_speed = 15;
      }
      else if ( (key == 's' || key == 'S') && !s_keyOn ) // crouch
      {
         //System.out.println("S_keyON");
         s_keyOn = true;
         a_keyOn = false;
         d_keyOn = false;
         y_pos += 50;
         crouchTimer.stop();
      }
      else if ( key == 's' || key == 'S' )
         crouchTimer.stop();
   }
   
   
   ////////////////////////////////////////////////////////
   public void keyReleased( KeyEvent e )
   {
      char key;
      
      key = e.getKeyChar();
      
      if ( key == 'a' || key == 'A' )
         a_keyOn = false;
      else if ( key == 'd' || key == 'D' )
         d_keyOn = false;
      else if ( key == 's' || key == 'S')
      {
         crouchTimer.start();
      }
   }
   
   
   ////////////////////////////////////////////////////////
   public void keyTyped( KeyEvent e )
   {
   }
   
   
   ///////////////////////////////////////////////////////
   public int getHeight()
   {
      if ( s_keyOn )
         return height - 50;
      else
         return height;
   }
   
   
   /////////////////////////////////////////////////////////
   public void actionPerformed( ActionEvent e )
   {
      String cmd;
      cmd = e.getActionCommand();
      
      if ( cmd.equals( "CROUCH_OFF" ) )
      {
         //System.out.println("S_keyOFF");
         s_keyOn = false;
         y_pos -= 50;
      }
      else
         System.out.println( "Unrecognized ActionCommand in Dude: " + cmd );
   }
   
   
   //////////////////////////////////////////////////////////////////
   public void collidedWith( Level level, Sprite sprite, String collisionType )
   {
      if ( isDead )
      {
         // do nothing
      }
      else if ( sprite.getType().equals("TERRAIN") )
      {
         if ( collisionType.equals("NORTH") )
         {
            y_speed = 0;
            y_pos = sprite.getBottomY() + 1;
            y_direction = SOUTH;
         }
         else if ( collisionType.equals("SOUTH") )
         {
            y_speed = 0;
            isOnGround = true;
            y_pos = sprite.getY() - getHeight();
         }
         else if ( collisionType.equals("WEST") )
         {
            x_speed = 0;
            x_pos = sprite.getRightX();
         }
         else if ( collisionType.equals("EAST") )
         {
            x_speed = 0;
            x_pos = sprite.getX() - width;
         }
      }
      else if ( sprite.getType().equals("HAZARD") )
      {
         isDead = true;
      }
      else if ( sprite.getType().equals("ENEMY") )
      {
         if ( !collisionType.equals("SOUTH") )
         {
            isDead = true;
         }
      }
      else if ( sprite.getType().equals("TRANSPARENT") )
      {
         // Don't do anything
      }
      else
      {
         System.out.println( "### Unknown type: " + sprite.getType() + " ###" );
      }
   }
   
   
   /////////////////////////////////////////////////////
   public boolean isDead()
   {
      return isDead;
   }
   
   
   //////////////////////////////////////////////////
   public String getType()
   {
      if ( isDead )
         return "TRANSPARENT";
      else
         return type;
   }
}
