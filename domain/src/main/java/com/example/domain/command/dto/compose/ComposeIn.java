package com.example.domain.command.dto.compose;

import java.awt.image.BufferedImage;

public interface ComposeIn {

  BufferedImage getUserImage();

  int getLeft();

  int getTop();

  String getBackgroundColor();

}
