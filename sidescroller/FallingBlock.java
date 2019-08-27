package sidescroller;

class FallingBlock extends Sprite
{
   int interval;
   int numTicks;
   boolean isFalling;
   
   
   /////////////////////////////////////////////////
   FallingBlock( int x_pos, int y_pos, int width, int height )
   {
      this.x_pos = x_pos;
      this.y_pos = y_pos;
      this.width = width;
      this.height = height;
      
      interval = 20;
      isFalling = false;
      numTicks = 0;
      
      type = "TERRAIN";
      
      image = LevelLoader.getImage( "falling_block.png" );
      image = LevelLoader.scale( image, width, height );
   }
   
   
   ///////////////////////////////////////////////////////////
   public void update( Level level )
   {
      y_pos += y_direction * y_speed;
      
      if ( y_pos > 610 )
         level.removeSprite( this );
      
      if ( numTicks == interval )
      {
         y_direction = SOUTH;
         y_speed = 12;
      }
      else if ( isFalling )
      {
         numTicks++;
      }
   }
   
   
   ////////////////////////////////////////////////////////
   public void collidedWith( Level level, Sprite sprite, String collisionType )
   {
      if ( sprite.getType().equals("CHARACTER") && collisionType.equals("NORTH") )
      {
         isFalling = true;
      }
   }
}
