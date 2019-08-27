package sidescroller;

import javax.swing.*;
import java.awt.*;
import java.util.*;

class GraphicsPanel extends JPanel
{
   Image backgroundImage;
   Level level;
   
   
   ////////////////////////////////////////////////
   GraphicsPanel( Level level )
   {
      this.level = level;
      backgroundImage = LevelLoader.getImage("background2.png");
   }
   
   
   ///////////////////////////////////////////////
   public void paintComponent( Graphics g )
   {
      Graphics2D graphics;
      Enumeration<Sprite> spriteEnum;
      Sprite sprite;
      int x_offset;
      
      graphics = (Graphics2D)g;

      // Draw background //
      x_offset = level.getXOffset();
      graphics.drawImage( backgroundImage, 0 - x_offset, 0, this);
      
      // Draw Sprites //
      spriteEnum = level.getSprites();
      
      while ( spriteEnum.hasMoreElements() )
      {
         sprite = spriteEnum.nextElement();
         graphics.drawImage( sprite.getImage(), sprite.getX() - x_offset, sprite.getY(), this );
      }
   }
}
