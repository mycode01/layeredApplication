package com.example.domain.command.impl;

import com.example.domain.command.DownloadCommand;
import com.example.domain.command.dto.download.BlueprintImageDownloadIn;
import com.example.domain.command.dto.download.BlueprintImageDownloadOut;
import com.example.domain.service.imagedownloader.Downloader;

public class ImageForBlueprintDownload implements DownloadCommand {

  private final Downloader downloader;

  private final String guide;
  private final String userImage;

  public ImageForBlueprintDownload(Downloader downloader, BlueprintImageDownloadIn in) {
    this.downloader = downloader;
    this.guide = in.getGuideUrl();
    this.userImage = in.getUserImageUrl();
  }

  @Override
  public BlueprintImageDownloadOut download() {
    return new BlueprintImageDownloadOut(downloader.download(guide),
        downloader.download(userImage));
  }
}
