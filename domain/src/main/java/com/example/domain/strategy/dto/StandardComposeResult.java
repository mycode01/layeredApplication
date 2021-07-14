package com.example.domain.strategy.dto;

import java.awt.image.BufferedImage;

public class StandardComposeResult implements ComposeResult {

  private final BufferedImage result;

  public StandardComposeResult(BufferedImage result) {
    this.result = result;
  }

  @Override
  public BufferedImage getResult() {
    return result;
  }
}
