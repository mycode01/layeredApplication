package com.example.domain.command.impl;

import com.example.domain.command.DownloadCommand;
import com.example.domain.command.dto.download.PreviewImageDownloadIn;
import com.example.domain.command.dto.download.PreviewImageDownloadOut;
import com.example.domain.service.imagedownloader.Downloader;

public class ImageForPreviewDownload implements DownloadCommand {

  private final Downloader downloader;

  private final String guide;
  private final String background;
  private final String mock;
  private final String userImage;

  public ImageForPreviewDownload(Downloader downloader, PreviewImageDownloadIn in) {
    this.downloader = downloader;
    this.guide = in.getGuideUrl();
    this.background = in.getBgUrl();
    this.mock = in.getMockUrl();
    this.userImage = in.getUserImageUrl();
  }

  @Override
  public PreviewImageDownloadOut download() {
    return new PreviewImageDownloadOut(downloader.download(guide), downloader.download(background),
        downloader.download(mock), downloader.download(userImage));
  }
}
