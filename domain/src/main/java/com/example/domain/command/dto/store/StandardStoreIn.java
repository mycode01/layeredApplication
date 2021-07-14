package com.example.domain.command.dto.store;

import java.awt.image.BufferedImage;

public class StandardStoreIn {

  private final BufferedImage image;
  // add some more properties for path

  public StandardStoreIn(BufferedImage image) {
    this.image = image;
  }

  public BufferedImage getImage() {
    return image;
  }
}
