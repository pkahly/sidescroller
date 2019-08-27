package sidescroller;


class BossCyclops extends Sprite
{
   
   ////////////////////////////////////////////////////////
   BossCyclops( int x_pos, int y_pos )
   {
      image = LevelLoader.getImage( "cyclops_boss.png" );
      
      this.x_pos = x_pos;
      this.y_pos = y_pos;
      
      width = 100;
      height = 200;
      
      x_speed = 15;
      y_speed = 0;
      
      x_direction = WEST;
      y_direction = 0;
      
      isDead = false;
      type = "HAZARD";
   }
   
   
   ////////////////////////////////////////////////////////////
   public void update( Level level )
   {
      x_pos += x_speed * x_direction;
   }
   
   
   ////////////////////////////////////////////////////////////
   public void collidedWith( Level level, Sprite sprite, String collisionType )
   {
      if ( sprite.getType().equals("TERRAIN") )
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
}
