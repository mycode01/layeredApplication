package com.example.domain.command.dto.download;

import java.awt.image.BufferedImage;

public class BlueprintImageDownloadOut implements DownloadOut {

  private final BufferedImage guideImage;
  private final BufferedImage userImage;

  public BlueprintImageDownloadOut(BufferedImage guideImage, BufferedImage userImage) {
    this.guideImage = guideImage;
    this.userImage = userImage;
  }

  public BufferedImage getGuideImage() {
    return guideImage;
  }

  public BufferedImage getUserImage() {
    return userImage;
  }
}
