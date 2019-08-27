package sidescroller;

import java.awt.*;

class Cyclops extends Sprite
{
   int deathCounter;
   Image deathImage;
   
   
   ////////////////////////////////////////////////////////
   Cyclops( int x_pos, int y_pos )
   {
      image = LevelLoader.getImage( "cyclops.png" );
      deathImage = LevelLoader.getImage( "cyclops_death.png" );
      
      this.x_pos = x_pos;
      this.y_pos = y_pos;
      
      width = 100;
      height = 100;
      
      x_speed = 10;
      y_speed = 0;
      
      x_direction = WEST;
      y_direction = 0;
      
      isDead = false;
      type = "ENEMY";
      
      deathCounter = 0;
   }
   
   
   ////////////////////////////////////////////////////////////
   public void update( Level level )
   {
      if ( !isDead )
      {
         x_pos += x_direction * x_speed;
         y_pos += y_direction * y_speed;
      }
      else
         y_pos += 10;
      
      if ( y_pos > 600 )
         level.removeSprite(this);
      
      // process falling //
      
      isOnGround = false;
   }
   
   
   ////////////////////////////////////////////////////////////
   public void collidedWith( Level level, Sprite sprite, String collisionType )
   {
      if ( sprite.getType().equals("CHARACTER") && collisionType.equals("NORTH") )
      {
         isDead = true;
         x_speed = 0;
      }
      else if ( sprite.getType().equals("TERRAIN") && !isDead )
      {
         if ( collisionType.equals("SOUTH") )
         {
            y_pos = sprite.getY() - getHeight();
            y_speed = 0;
            isOnGround = true;
         }
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
   
   
   /////////////////////////////////////////////////////////
   public String getType()
   {
      if ( isDead )
         return "TERRAIN";
      else
         return "ENEMY";
   }
   
   
   ////////////////////////////////////////////////////////////
   public Image getImage()
   {
      if ( !isDead )
         return image;
      else
         return deathImage;
   }
}
