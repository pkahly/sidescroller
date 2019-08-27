package sidescroller;

import javax.swing.*;
import java.awt.*;

abstract class LevelLoader
{
   
   ///////////////////////////////////////////////////////////////
   public static Level loadLevel( int levelNum )
   {
      Level level;
      
      level = new Level();
      
      if ( levelNum == 1 )
         levelOne( level );
      else if ( levelNum == 2 )
         levelTwo( level );
      else if ( levelNum == 3 )
         levelThree( level );
      else if ( levelNum == 4 )
         levelFour( level );
      else if ( levelNum == 5 )
         levelFive( level );
      else if ( levelNum == 6 )
         levelSix( level );
      
      return level;
   }
   
   
   /////////////////////////////////////////////////////////////////////////////
   public static Image getImage( String filename )
   {
      Toolkit tk;
      MediaTracker mt;
      Image image;
      
      tk = Toolkit.getDefaultToolkit();
      mt = new MediaTracker( new Component(){} );
      
      //image = tk.getImage( "sidescroller/images/" + filename );
      image = tk.createImage( LevelLoader.class.getResource("images/" + filename) );
      mt.addImage(image, 1);
      
      try
      {
         mt.waitForAll();
      }
      catch ( InterruptedException ex )
      {
         ex.printStackTrace();
      }
      
      if ( mt.isErrorAny() )
      {
         JOptionPane.showMessageDialog( null, "Failed to load necessary images for this level!" );
         System.exit(1);
      }
      
      return image;
   }
   
   
   ////////////////////////////////////////////////////////////////////////////
   public static Image scale( Image image, int width, int height )
   {
      MediaTracker mt;
      
      mt = new MediaTracker( new Component(){} );
      
      image = image.getScaledInstance( width, height, Image.SCALE_DEFAULT );
      mt.addImage(image, 1);
      
      try
      {
         mt.waitForAll();
      }
      catch ( InterruptedException ex )
      {
         ex.printStackTrace();
      }
      
      if ( mt.isErrorAny() )
      {
         JOptionPane.showMessageDialog( null, "Failed to load necessary images for this level!" );
         System.exit(1);
      }
      
      return image;
   }
   
   
   ///////////////////////////////////////////////////////////////////////////////
   private static void levelOne( Level level )
   {
      level.addSprite( new Ground( 10, 500, 100, 10000 ) );
      
      // Add enemies //
      level.addSprite( new Cyclops( 1100, 400 ) );
      level.addSprite( new Cyclops( 1500, 400 ) );
      level.addSprite( new Cyclops( 2500, 400 ) );
      level.addSprite( new Cyclops( 3500, 400 ) );
      level.addSprite( new Cyclops( 4500, 400 ) );
      level.addSprite( new Cyclops( 5500, 400 ) );
      level.addSprite( new Cyclops( 6500, 400 ) );
      level.addSprite( new Cyclops( 7500, 400 ) );
      level.addSprite( new Cyclops( 8500, 400 ) );
      level.addSprite( new Cyclops( 9500, 400 ) );
      
      // Add boxes //
      level.addSprite( new Box( 1200, 475, 25, 25 ) );
      level.addSprite( new Box( 2000, 450, 50, 50) );
      level.addSprite( new Box( 3000, 400, 100, 100 ) );
      level.addSprite( new Box( 4000, 300, 200, 200 ) );
      level.addSprite( new Box( 9800, 300, 200, 200 ) );
   }
   
   
   //////////////////////////////////////////////////////
   private static void levelTwo( Level level )
   {
      // caterpillars //
      level.addSprite( new Caterpillar( 500, 325 ) );
      level.addSprite( new Caterpillar( 1000, 325 ) );
      level.addSprite( new Caterpillar( 2200, 475 ) );
      level.addSprite( new Caterpillar( 4400, 475 ) );
      level.addSprite( new Caterpillar( 6500, 475 ) );
      level.addSprite( new Caterpillar( 9400, 475 ) );
      level.addSprite( new Caterpillar( 9800, 475 ) );
      
      // Fireballs //
      level.addSprite( new Cannon( 7500, 400, level.WEST, 200 ) );
      level.addSprite( new Cannon( 8700, 400, level.EAST, 200 ) );
      
      // Boxes //
      level.addSprite( new Box( 200, 350, 150, 1000 ) );
      level.addSprite( new Box( 1600, 500, 80, 200 ) );
      level.addSprite( new Box( 2100, 500, 80, 200 ) );
      level.addSprite( new Box( 2600, 500, 80, 200 ) );
      level.addSprite( new Box( 3100, 500, 80, 200 ) );
      level.addSprite( new Box( 3700, 500, 80, 200 ) );
      level.addSprite( new Box( 4300, 500, 80, 200 ) );
      level.addSprite( new Box( 5000, 500, 80, 200 ) );
      level.addSprite( new Box( 5700, 500, 80, 200 ) );
      level.addSprite( new Box( 6400, 500, 80, 500 ) );
      level.addSprite( new Box( 7400, 500, 80, 600 ) );
      level.addSprite( new Box( 8600, 500, 80, 500 ) );
      level.addSprite( new Box( 9300, 500, 80, 650 ) );      
   }
   
   
   ///////////////////////////////////////////////////////////////
   public static void levelThree( Level level )
   {
      level.addSprite( new Ground( 100, 400, 100, 400  ) );
      level.addSprite( new Ground( 9600, 400, 100, 500  ) );
      
      level.addSprite( new Caterpillar( 110, 375 ) );
      
      // add falling blocks //
      level.addSprite( new FallingBlock( 600, 450, 400, 100) );
      level.addSprite( new FallingBlock( 1200, 450, 400, 100) );
      level.addSprite( new FallingBlock( 1800, 450, 400, 100) );
      level.addSprite( new FallingBlock( 2400, 450, 400, 100) );
      level.addSprite( new FallingBlock( 3000, 450, 400, 100) );
      level.addSprite( new FallingBlock( 3600, 450, 400, 100) );
      level.addSprite( new FallingBlock( 4200, 450, 400, 100) );
      level.addSprite( new FallingBlock( 4800, 450, 400, 100) );
      level.addSprite( new FallingBlock( 5400, 450, 400, 100) );
      level.addSprite( new FallingBlock( 6000, 450, 400, 100) );
      level.addSprite( new FallingBlock( 6600, 450, 400, 100) );
      level.addSprite( new FallingBlock( 7200, 450, 400, 100) );
      level.addSprite( new FallingBlock( 7800, 450, 400, 100) );
      level.addSprite( new FallingBlock( 8400, 450, 400, 100) );
      level.addSprite( new FallingBlock( 9000, 450, 400, 100) );
      
      // Fireballs //
      level.addSprite( new Cannon( 400, 300, level.EAST, 100 ) );
      level.addSprite( new Cannon( 9600, 300, level.WEST, 100 ) );
   }
   
   
   public static void levelFour( Level level )
   {
      level.addSprite( new Ground( 0, 400, 200, 9950 ) );
      level.addSprite( new Ground( 200, 150, 50, 200) );
      
      level.addSprite( new BossCyclops( 500, 200 ) );

      level.addSprite( new Cyclops( 2000, 300 ) );
      level.addSprite( new Cyclops( 3100, 300 ) );
      level.addSprite( new Cyclops( 4200, 300 ) );
      level.addSprite( new Cyclops( 5300, 300 ) );
      level.addSprite( new Cyclops( 6400, 300 ) );
      level.addSprite( new Cyclops( 7500, 300 ) );
      level.addSprite( new Cyclops( 8600, 300 ) );

      level.addSprite( new Cannon( 0, 300, level.EAST, 30  ) );
      level.addSprite( new Cannon( 9000, 300, level.WEST, 190 ) );
      
      level.addSprite( new Caterpillar( 9300, 375 ) );
      level.addSprite( new Caterpillar( 9500, 375 ) );
      level.addSprite( new Caterpillar( 9700, 375 ) );
      level.addSprite( new Caterpillar( 9900, 375 ) );
   }
   
   
   /////////////////////////////////////////////////////////////
   public static void levelFive( Level level )
   {
      level.addSprite( new Ground( 200, 500, 100, 1000 ) );
      level.addSprite( new Ground( 9300, 300, 300, 700 ) );
      
      // upper boxes //
      level.addSprite( new Box( 1300, 300, 100, 500 ) );
      level.addSprite( new Box( 2900, 300, 100, 500 ) );
      level.addSprite( new Box( 4500, 300, 100, 500 ) );
      level.addSprite( new Box( 6100, 300, 100, 500 ) );
      level.addSprite( new Box( 7700, 300, 100, 500 ) );
      
      // lower boxes //
      level.addSprite( new Box( 2100, 500, 100, 500 ) );      
      level.addSprite( new Box( 3700, 500, 100, 500 ) );
      level.addSprite( new Box( 5300, 500, 100, 500 ) );
      level.addSprite( new Box( 6900, 500, 100, 500 ) );
      level.addSprite( new Box( 8500, 400, 100, 500 ) );
      
      // Cannons //
      level.addSprite( new Cannon( 200, 400, level.EAST, 50 ) );
      level.addSprite( new Cannon( 9300, 200, level.WEST, 200 ) );
      
      // Caterpillars //
      level.addSprite( new Caterpillar( 1320, 275 ) );
      level.addSprite( new Caterpillar( 3800, 475 ) );
      level.addSprite( new Caterpillar( 8700, 375 ) );
      level.addSprite( new Caterpillar( 9500, 275 ) );
      
      // Cyclops //
      level.addSprite( new Cyclops( 300, 400 ) );
      level.addSprite( new Cyclops( 9400, 200 ) );
      
      // Boxes to hold cyclops //
      level.addSprite( new Box( 1180, 480, 20, 20 ) );
      level.addSprite( new Box( 9900, 280, 20, 20 ) );
   }


   /////////////////////////////////////////////////////////////
   public static void levelSix( Level level )
   {
      Sprite boss;

      // Ground //
      level.addSprite( new Ground( 600, 500, 100, 1000 ) );
      level.addSprite( new Ground( 3800, 500, 100, 2100 ) );
      level.addSprite( new Ground( 6050, 500, 100, 350 ) );
      level.addSprite( new Ground( 9000, 500, 100, 1600 ) );
      
      // Cyclops & Cannons //
      level.addSprite( new Box( 600, 450, 50, 50 ) ); // left barrier
      level.addSprite( new Cannon( 1450, 400, level.WEST, 200 ) );
      level.addSprite( new Cannon( 1450, 300, level.WEST, 100 ) );
      level.addSprite( new Cyclops( 900, 400 ) );
      level.addSprite( new Cyclops( 1100, 400 ) );
      level.addSprite( new Cyclops( 1300, 400 ) );
      
      // falling boxes //
      level.addSprite( new FallingBlock( 1800, 400, 200, 100 ) );
      level.addSprite( new FallingBlock( 2200, 200, 200, 100 ) );
      level.addSprite( new FallingBlock( 2600, 400, 200, 100 ) );
      level.addSprite( new FallingBlock( 3000, 200, 200, 100 ) );
      level.addSprite( new FallingBlock( 3400, 100, 200, 100 ) );
      
      // boxes //
      level.addSprite( new Box( 200, 200, 100, 200 ) );
      level.addSprite( new Box( 6600, 300, 100, 200 ) );
      level.addSprite( new Box( 7000, 200, 100, 200 ) );
      level.addSprite( new Box( 7400, 300, 100, 200 ) );
      level.addSprite( new Box( 7800, 200, 100, 200 ) );
      level.addSprite( new Box( 8200, 300, 100, 200 ) );
      level.addSprite( new Box( 8600, 200, 100, 200 ) );
      
      // Boss Cyclops //
      level.addSprite( new Box( 3800, 450, 50, 50 ) );   // left   barrier
      level.addSprite( new Box( 5000, 400, 100, 100 ) ); // middle barrier
      level.addSprite( new Box( 6350, 450, 50, 50 ) );   // right  barrier
      level.addSprite( new Box( 5900, 590, 50, 150 ) );  // bottom of hole
      
      level.addSprite( new BossCyclops( 4500, 300 ) );
      level.addSprite( new BossCyclops( 5500, 300 ) );
      
      // Caterpillars //
      level.addSprite( new Caterpillar( 6700, 275 ) );
      level.addSprite( new Caterpillar( 7100, 175 ) );
      level.addSprite( new Caterpillar( 7500, 275 ) );
      level.addSprite( new Caterpillar( 7900, 175 ) );
      level.addSprite( new Caterpillar( 8300, 275 ) );
      level.addSprite( new Caterpillar( 8700, 175 ) );
      
      // Boss //
      boss = new SquashMonster( 9200, 300 );
      level.addSprite( boss );
      
      // boss blocks //
      level.addSprite( new BossBlock( 9900, 400, 100, 100, boss ) );
      level.addSprite( new BossBlock( 9900, 300, 100, 100, boss ) );
      level.addSprite( new BossBlock( 9900, 200, 100, 100, boss ) );
      level.addSprite( new BossBlock( 9900, 100, 100, 100, boss ) );
      level.addSprite( new BossBlock( 9900, 0, 100, 100, boss ) );
   }
}
