package sidescroller;

import java.awt.*;

class SquashMonster extends Sprite
{
   Image normalImage;
   Image squashedImage;
   
   boolean isSquashed;
   int squashedTimer;

   int old_x;
   int timesSquashed;

   
   ////////////////////////////////////////////////////////////
   SquashMonster( int x_pos, int y_pos )
   {
      this.x_pos = x_pos;
      this.y_pos = y_pos;
      
      x_direction = EAST;
      x_speed = 15;

      old_x = x_pos;

      height = 200;
      width = 200;
      
      normalImage = LevelLoader.getImage( "squash_fullHealth.png" );
      squashedImage = LevelLoader.getImage( "squash_squashed.png" );
      
      isSquashed = false;
      timesSquashed = 0;
      
      type = "ENEMY";
   }
   
   
   /////////////////////////////////////////////////////////////
   public Image getImage()
   {
      if ( !isSquashed )
         return normalImage;
      else
         return squashedImage;
   }
   
   
   ///////////////////////////////////////////////////////////////
   public void collidedWith( Level level, Sprite sprite, String collisionType )
   {
      if ( sprite.getType().equals("CHARACTER") && collisionType.equals("NORTH") && !isSquashed && !isDead )
      {
         type = "TERRAIN";
   
         isSquashed = true;
         timesSquashed++;
         squashedTimer = 200;

         height = 100;
         
         x_speed = 30;
         
         y_pos += 100;
      }
      else if ( sprite.getType().equals("TERRAIN") && !isDead )
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
   
   
   ///////////////////////////////////////////////////////////////////
   public void update( Level level )
   {
      // keep from falling //
      if ( isDead )
      {
         y_pos += 4;
      }
      else if ( !isOnGround )
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
      
      // process unsquashing //
      if ( isSquashed )
      {
         squashedTimer--;
         
         if ( squashedTimer <= 0 )
         {
            type = "ENEMY";
            isSquashed = false;
            
            height = 200;

            x_speed = 15;
            y_pos -= 100;
         }
      }
      
      // process dying //
      if ( timesSquashed >= 3 )
      {
         isDead = true;
      }
      
      isOnGround = false;
   }
}
