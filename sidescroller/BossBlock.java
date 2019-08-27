package sidescroller;


class BossBlock extends Sprite
{
   Sprite boss;
   
   
   //////////////////////////////////////////////////////////////////////////
   BossBlock( int x_pos, int y_pos, int width, int height, Sprite boss )
   {
      image = LevelLoader.getImage( "boss_block.png" );
      image = LevelLoader.scale( image, width, height );
      
      this.x_pos = x_pos;
      this.y_pos = y_pos;

      this.width = width;
      this.height = height;
      
      this.boss = boss;
      
      type = "TERRAIN";
   }
   
   
   /////////////////////////////////////////////////////////////////////////////
   public void update( Level level )
   {
      if ( boss.isDead )
         level.removeSprite( this );
   }
   
   
   ///////////////////////////////////////////////////////////////////////////
   public void collidedWith( Level level, Sprite sprite, String collisionType )
   {}
}
