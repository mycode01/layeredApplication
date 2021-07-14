package com.example.domain.command.dto.download;

import java.awt.image.BufferedImage;

public class PreviewImageDownloadOut implements DownloadOut {

  private final BufferedImage guideImage;
  private final BufferedImage bgImage;
  private final BufferedImage mockImage;
  private final BufferedImage userImage;

  public PreviewImageDownloadOut(BufferedImage guideImage, BufferedImage bgImage,
      BufferedImage mockImage,
      BufferedImage userImage) {
    this.guideImage = guideImage;
    this.bgImage = bgImage;
    this.mockImage = mockImage;
    this.userImage = userImage;
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

  public BufferedImage getUserImage() {
    return userImage;
  }
}
