package com.example.domain.command.dto.download;

public class BlueprintImageDownloadIn implements DownloadIn {

  private final String guideUrl;
  private final String imageUrl;

  public BlueprintImageDownloadIn(String guideUrl, String imageUrl) {
    this.guideUrl = guideUrl;
    this.imageUrl = imageUrl;
  }

  public String getGuideUrl() {
    return guideUrl;
  }

  @Override
  public String getUserImageUrl() {
    return imageUrl;
  }
}
