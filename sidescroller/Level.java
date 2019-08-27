package sidescroller;

import java.util.*;

class Level
{
   public static final int NORTH = -1;
   public static final int SOUTH = 1;
   public static final int EAST = 1;
   public static final int WEST = -1;
   
   public static final int X_LIMIT = 10000;
   public static final int Y_LIMIT = 600;

   Vector<Sprite> sprites;
   int x_offset;
   
   
   /////////////////////////////////////////////////
   Level()
   {
      sprites = new Vector<Sprite>();
      x_offset = 0;
   }
   
   
   /////////////////////////////////////////////////
   int getXOffset()
   {
      return x_offset;
   }
   
   
   /////////////////////////////////////////////////
   void update()
   {
      Enumeration<Sprite> enumeration;
      Sprite sprite;
      
      enumeration = sprites.elements();
      
      while ( enumeration.hasMoreElements() )
      {
         sprite = enumeration.nextElement();
         sprite.update( this );
         checkCollision( sprite );
      }
   }
   
   
   //////////////////////////////////////////////////
   void addSprite( Sprite sprite )
   {
      sprites.add( sprite );
   }
   
   
   //////////////////////////////////////////////////
   void removeSprite( Sprite sprite )
   {
      sprites.remove( sprite );
   }
   
   
   //////////////////////////////////////////////////
   void checkCollision( Sprite sprite )
   {
      Enumeration<Sprite> spriteEnum;
      Sprite otherSprite;
      String collisionType;
      
      spriteEnum = sprites.elements();
      
      while ( spriteEnum.hasMoreElements() )
      { 
         otherSprite = spriteEnum.nextElement();
         collisionType = getCollisionType( sprite, otherSprite );
         
         if ( sprite != otherSprite && !collisionType.equals("NONE") )
         {
            sprite.collidedWith( this, otherSprite, collisionType );
         }
      }
   }
   
   
   ///////////////////////////////////////////////////
   void moveXOffset( Sprite sprite )
   {
      int char_pos;
      
      char_pos = sprite.getX();
      x_offset = char_pos - 400;
      
      if ( x_offset < 0 )
         x_offset = 0;
      else if ( x_offset > 9200 )
         x_offset = 9190;
   }
   
   
   /////////////////////////////////////////////////////
   Enumeration<Sprite> getSprites()
   {
      return sprites.elements();
   }
   
   
   //////////////////////////////////////////////////////////////
   private String getCollisionType( Sprite obj1, Sprite obj2 )
   {
      String type;
      
      float north_dist;
      float south_dist;
      float east_dist;
      float west_dist;
      float minimum;
      
      type = "NONE";
      
      if ( !( obj1.getBottomY() < obj2.getY() || obj1.getY() > obj2.getBottomY() || obj1.getRightX() < obj2.getX() || obj1.getX() > obj2.getRightX() ) )
      {
         north_dist = Math.abs( obj1.getY() - obj2.getBottomY() );
         south_dist = Math.abs( obj1.getBottomY() - obj2.getY() );
         east_dist = Math.abs( obj1.getRightX() - obj2.getX() );
         west_dist = Math.abs( obj1.getX() - obj2.getRightX() );
         
         // get minimum //
         minimum = north_dist;
         if ( minimum > south_dist )
            minimum = south_dist;
         if ( minimum > east_dist )
            minimum = east_dist;
         if ( minimum > west_dist )
            minimum = west_dist;
         
         // check minimum //
         if ( minimum == north_dist )
            type = "NORTH";
         else if ( minimum == south_dist )
            type = "SOUTH";
         else if ( minimum == east_dist )
            type = "EAST";
         else
            type = "WEST";
         
      }
      
      return type;
   }
}
