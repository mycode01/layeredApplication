package com.example.domain.command.dto.download;

public class PreviewImageDownloadIn implements DownloadIn {

  private final String guideUrl;
  private final String bgUrl;
  private final String mockUrl;
  private final String imageUrl;

  public PreviewImageDownloadIn(String guideUrl, String bgUrl, String mockUrl, String imageUrl) {
    this.guideUrl = guideUrl;
    this.bgUrl = bgUrl;
    this.mockUrl = mockUrl;
    this.imageUrl = imageUrl;
  }

  public String getGuideUrl() {
    return guideUrl;
  }

  public String getBgUrl() {
    return bgUrl;
  }

  public String getMockUrl() {
    return mockUrl;
  }

  @Override
  public String getUserImageUrl() {
    return imageUrl;
  }
}
