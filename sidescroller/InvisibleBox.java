package sidescroller;

class InvisibleBox extends Sprite
{
   ////////////////////////////////////////////////////////////////
   InvisibleBox( int x_pos, int y_pos, int width, int height )
   {
      this.x_pos = x_pos;
      this.y_pos = y_pos;
      this.width = width;
      this.height = height;
      
      image = null;
      type = "TERRAIN";
   }
   
   
   ///////////////////////////////////////////////////////////////
   public void collidedWith( Level level, Sprite sprite, String collisionType ){}
   public void update( Level level ) {}
}
