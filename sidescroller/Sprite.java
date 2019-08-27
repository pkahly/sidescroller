package sidescroller;

import java.awt.*;

abstract class Sprite
{
   public static final int NORTH = -1;
   public static final int SOUTH = 1;
   public static final int EAST = 1;
   public static final int WEST = -1;
   
   int x_pos;
   int y_pos;
   
   int width;
   int height;
   
   Image image;
   
   int x_direction;
   int y_direction;
   
   int x_speed;
   int y_speed;
   
   boolean isOnGround;
   boolean isDead;
   
   String type;
   
   
   ////////////////////////////////////////////
   public int getX()
   {
      return x_pos;
   }
   
   
   ///////////////////////////////////////////
   public int getY()
   {
      return y_pos;
   }
   
   
   ///////////////////////////////////////////
   public int getHeight()
   {
      return height;
   }
   
   
   ////////////////////////////////////////////
   public int getWidth()
   {
      return width;
   }
   
   
   ////////////////////////////////////////////
   public Image getImage()
   {
      return image;
   }
   
   
   /////////////////////////////////////////////////
   public int getRightX()
   {
      return x_pos + width;
   }
   
   
   /////////////////////////////////////////////////
   public int getBottomY()
   {
      return y_pos + height;
   }
   
   
   ///////////////////////////////////////////
   public int getXDirection()
   {
      return x_direction;
   }
   
   
   ////////////////////////////////////////////
   public int getYDirection()
   {
      return y_direction;
   }
   
   
   public String getType()
   {
      return type;
   }
   
   
   /////////////////////////////////////////
   abstract public void update( Level level );
   abstract public void collidedWith( Level level, Sprite sprite, String collisionType );
}
