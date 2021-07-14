package com.example.domain.command.dto.compose;

import java.awt.image.BufferedImage;

public class PreviewComposeIn implements ComposeIn {

  private final BufferedImage guideImage;
  private final BufferedImage bgImage;
  private final BufferedImage mockImage;
  private final BufferedImage userImage;
  private final int left;
  private final int top;
  private final String backgroundColor;

  public PreviewComposeIn(BufferedImage guideImage, BufferedImage bgImage,
      BufferedImage mockImage, BufferedImage userImage, int left, int top,
      String backgroundColor) {
    this.guideImage = guideImage;
    this.bgImage = bgImage;
    this.mockImage = mockImage;
    this.userImage = userImage;
    this.left = left;
    this.top = top;
    this.backgroundColor = backgroundColor;
  }

  public BufferedImage getGuideImage() {
    return guideImage;
  }

  public BufferedImage getBgImage() {
    return bgImage;
  }

  public BufferedImage getMockImage() {
    return mockImage;
  }

  @Override
  public BufferedImage getUserImage() {
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
