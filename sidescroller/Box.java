package sidescroller;


class Box extends Sprite
{
   
   ////////////////////////////////////////////
   Box( int x_pos, int y_pos, int height, int width )
   {
      image = LevelLoader.getImage( "box.png" );
      image = LevelLoader.scale( image, width, height );
      
      this.x_pos = x_pos;
      this.y_pos = y_pos;
      this.height = height;
      this.width = width;
      
      type = "TERRAIN";
   }
   
   
   //////////////////////////////////////////////////////////////////
   public void update( Level level ){}
   public void collidedWith( Level level, Sprite sprite, String collisionType ){}
}
