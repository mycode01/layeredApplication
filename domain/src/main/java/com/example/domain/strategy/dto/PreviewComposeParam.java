package com.example.domain.strategy.dto;

import java.awt.image.BufferedImage;

public class PreviewComposeParam implements ComposeParam {

  private final BufferedImage guideImage;
  private final BufferedImage backgroundImage;
  private final BufferedImage mockImage;
  private final BufferedImage userImage;
  private final int left;
  private final int top;
  private final String backgroundColor;

  public PreviewComposeParam(BufferedImage guideImage, BufferedImage backgroundImage,
      BufferedImage mockImage, BufferedImage userImage, int left, int top,
      String backgroundColor) {
    this.guideImage = guideImage;
    this.backgroundImage = backgroundImage;
    this.mockImage = mockImage;
    this.userImage = userImage;
    this.left = left;
    this.top = top;
    this.backgroundColor = backgroundColor;
  }

  public BufferedImage getGuideImage() {
    return guideImage;
  }

  public BufferedImage getBackgroundImage() {
    return backgroundImage;
  }

  public BufferedImage getMockImage() {
    return mockImage;
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
