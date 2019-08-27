package sidescroller;


class Fireball extends Sprite
{
   /////////////////////////////////////////////
   Fireball( int x_pos, int y_pos, int x_speed, int x_direction )
   {
      this.x_pos = x_pos;
      this.y_pos = y_pos;
      this.x_speed = x_speed;
      this.x_direction = x_direction;
      
      y_speed = 0;
      y_direction = 0;
      
      type = "HAZARD";

      image = LevelLoader.getImage( "fireball.png" );
   }
   
   
   ///////////////////////////////////////////////
   public void update( Level level )
   {
      if ( x_pos < -10 )
         level.removeSprite( this );
      
      x_pos += x_speed * x_direction;
   }
   
   
   ///////////////////////////////////////////////
   public void collidedWith( Level level, Sprite sprite, String collisionType )
   {}
}
