package com.example.usecase.msg;

public class ComposeInMsg {

  private final String guideUrl;
  private final String bgUrl;
  private final String mockUrl;
  private final String imageUrl;
  private final int left;
  private final int top;
  private final String backgroundColor;


  public ComposeInMsg(String guideUrl, String bgUrl, String mockUrl,
      String imageUrl, int left, int top, String backgroundColor) {
    this.guideUrl = guideUrl;
    this.bgUrl = bgUrl;
    this.mockUrl = mockUrl;
    this.imageUrl = imageUrl;
    this.left = left;
    this.top = top;
    this.backgroundColor = backgroundColor;
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

  public String getImageUrl() {
    return imageUrl;
  }

  public int getLeft() {
    return left;
  }

  public int getTop() {
    return top;
  }

  public String getBackgroundColor() {
    return backgroundColor;
  }
}

