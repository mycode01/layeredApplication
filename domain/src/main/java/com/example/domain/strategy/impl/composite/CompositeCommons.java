package com.example.domain.strategy.impl.composite;

import java.awt.Color;
import java.awt.image.BufferedImage;
import org.imgscalr.Scalr;

public class CompositeCommons {

  public static Color getColor(String hexColor) {
    if (hexColor == null || hexColor.trim().length() == 0) {
      return null;
    }
    return Color.decode(hexColor);
  }

  public static BufferedImage makeImageArea(BufferedImage img, int posX, int posY,
      int canvasWidth, int canvasHeight) {
    int srcX = posX < 0 ? -posX : 0;
    int srcY = posY < 0 ? -posY : 0;
    int srcW = Math.min(img.getWidth() - srcX, canvasWidth);
    int srcH = Math.min(img.getHeight() - srcY, canvasHeight);

    if (srcX == 0 && srcY == 0 && srcW == img.getWidth() && srcH == img.getHeight()) {
      return img;
    }
    return Scalr.crop(img, srcX, srcY, srcW, srcH);
  }

  public static BufferedImage makeFilledBg(BufferedImage bg, Color bgColor) {
    for (int x = 0; x < bg.getWidth(); x++) {
      for (int y = 0; y < bg.getHeight(); y++) {
        int pixel = bg.getRGB(x, y);
        Color color = new Color(pixel, true);
        if (color.equals(Color.WHITE)) {
          bg.setRGB(x, y, bgColor.getRGB());
        }
      }
    }
    return bg;
  }
}
