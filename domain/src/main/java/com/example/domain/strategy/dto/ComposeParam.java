package com.example.domain.strategy.dto;

import java.awt.image.BufferedImage;

public interface ComposeParam {

  BufferedImage getTargetImage();

  int getLeft();

  int getTop();

  String getBackgroundColor();

}
