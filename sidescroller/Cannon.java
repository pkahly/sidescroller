package sidescroller;

import java.awt.*;

class Cannon extends Sprite
{
   Image imageWest;
   Image imageEast;
   
   int interval;
   int numTicks;
   
   
   ////////////////////////////////////////////////////////
   Cannon( int x_pos, int y_pos, int x_direction, int interval )
   {
      imageWest = LevelLoader.getImage("cannon_west.png");
      imageEast = LevelLoader.getImage("cannon_east.png");
      
      this.x_pos = x_pos;
      this.y_pos = y_pos;
      this.x_direction = x_direction;
      
      width = 150;
      height = 100;
      
      this.interval = interval;
      numTicks = interval / 2;

      type = "TERRAIN";
   }
   
   
   /////////////////////////////////////////////////////////////
   public Image getImage()
   {
      if ( x_direction == EAST )
         return imageEast;
      else
         return imageWest;
   }
   
   
   /////////////////////////////////////////////////////////////////
   public void update( Level level )
   {
      numTicks++;
      
      if ( numTicks == interval )
      {
         numTicks = 0;

         if ( x_direction == level.WEST )
            level.addSprite( new Fireball( x_pos, y_pos + 10, 5, level.WEST ) );
         else
            level.addSprite( new Fireball( getRightX(), y_pos + 10, 5, level.EAST ) );
      }
   }
   
   
   ////////////////////////////////////////////////////////////////
   public void collidedWith( Level level, Sprite sprite, String type ){}
}
