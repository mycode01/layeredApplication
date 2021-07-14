package com.example.domain.strategy.dto;

import java.awt.image.BufferedImage;

public class BlueprintComposeParam implements ComposeParam {

  private final BufferedImage guideImage;
  private final BufferedImage userImage;
  private final int left;
  private final int top;
  private final String backgroundColor;

  public BlueprintComposeParam(BufferedImage guideImage, BufferedImage userImage, int left, int top,
      String backgroundColor) {
    this.guideImage = guideImage;
    this.userImage = userImage;
    this.left = left;
    this.top = top;
    this.backgroundColor = backgroundColor;
  }

  public BufferedImage getGuideImage() {
    return guideImage;
  }

  @Override
  public BufferedImage getTargetImage() {
    return userImage;
  }

  @Override
  public int getLeft() {
    return left;
  }

  @Override
  public int getTop() {
    return top;
  }

  @Override
  public String getBackgroundColor() {
    return backgroundColor;
  }
}
