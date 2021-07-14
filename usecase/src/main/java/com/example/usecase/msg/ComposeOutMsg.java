package com.example.usecase.msg;

import java.awt.image.BufferedImage;

public class ComposeOutMsg {

  private final BufferedImage result;
  private final String path;

  public ComposeOutMsg(BufferedImage result, String path) {
    this.result = result;
    this.path = path;
  }

  public BufferedImage getResult() {
    return result;
  }

  public String getPath() {
    return path;
  }
}
