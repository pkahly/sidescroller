package sidescroller;

import java.awt.*;

class Caterpillar extends Sprite
{
   Image eastImage;
   Image westImage;
   
   int old_x;
   
   
   //////////////////////////////////////////////////////
   Caterpillar( int x_pos, int y_pos )
   {
      westImage = LevelLoader.getImage( "caterpiller_west.png" );
      eastImage = LevelLoader.getImage( "caterpiller_east.png" );
      
      this.x_pos = x_pos;
      this.y_pos = y_pos;
      
      x_speed = 1;
      y_speed = 0;
      old_x = x_pos;
      
      width = 50;
      height = 25;
      
      x_direction = -1;
      y_direction = 0;
      
      type = "HAZARD";
   }
   
   
   //////////////////////////////////////////////////////
   public void update( Level level )
   {
      if ( !isOnGround )
      {
         x_pos = old_x;   // move back
         
         if ( x_direction == level.EAST )
            x_direction = level.WEST;
         else
            x_direction = level.EAST;
      }
      else
      {
         old_x = x_pos;
         x_pos += x_direction * x_speed;
      }
      
      isOnGround = false;
   }
   
   
   ////////////////////////////////////////////////////
   public Image getImage()
   {
      if ( x_direction >= 0 )
         return eastImage;
      else
         return westImage;
   }
   
   
   ////////////////////////////////////////////////////
   public void collidedWith( Level level, Sprite sprite, String collisionType )
   {
      if ( sprite.getType().equals("TERRAIN") )
      {
         if ( collisionType.equals("SOUTH") )
            isOnGround = true;
         else if ( collisionType.equals("WEST") )
         {
            x_pos = sprite.getRightX();
            x_direction = EAST;
         }
         else if ( collisionType.equals("EAST") )
         {
            x_pos = sprite.getX() - width;
            x_direction = WEST;
         }
      }
   }
}
