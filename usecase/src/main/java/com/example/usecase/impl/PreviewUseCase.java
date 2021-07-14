package com.example.usecase.impl;

import com.example.domain.command.ComposeCommand;
import com.example.domain.command.DownloadCommand;
import com.example.domain.command.StoreCommand;
import com.example.domain.command.dto.compose.PreviewComposeIn;
import com.example.domain.command.dto.download.PreviewImageDownloadIn;
import com.example.domain.command.dto.download.PreviewImageDownloadOut;
import com.example.domain.command.dto.store.StandardStoreIn;
import com.example.domain.command.dto.store.StandardStoreOut;
import com.example.domain.command.impl.ComposeCommandWithStrategy;
import com.example.domain.command.impl.ImageForPreviewDownload;
import com.example.domain.command.impl.ImageStore;
import com.example.domain.service.imagedownloader.Downloader;
import com.example.domain.service.imagestorage.Storage;
import com.example.domain.strategy.impl.composite.CompositePreview;
import com.example.domain.strategy.impl.parambuild.PreviewParamBuilder;
import com.example.presenter.ComposePresenter;
import com.example.usecase.ComposeUseCase;
import com.example.usecase.msg.ComposeInMsg;
import com.example.usecase.msg.ComposeOutMsg;
import java.awt.image.BufferedImage;

public class PreviewUseCase<O> implements ComposeUseCase {

  private final Downloader downloader;
  private final Storage storage;
  private final ComposePresenter<ComposeOutMsg, O> presenter;

  public PreviewUseCase(Downloader downloader,
      Storage storage,
      ComposePresenter<ComposeOutMsg, O> presenter) {
    this.downloader = downloader;
    this.storage = storage;
    this.presenter = presenter;
  }

  @Override
  public O doWork(ComposeInMsg inMsg) {
    PreviewImageDownloadIn previewImageDownloadIn = mapToDownloadIn(inMsg);
    DownloadCommand downloadCommand = new ImageForPreviewDownload(downloader,
        previewImageDownloadIn);
    PreviewImageDownloadOut previewImageDownloadOut = downloadCommand.download();

    PreviewComposeIn previewComposeIn = mapToComposeIn(inMsg, previewImageDownloadOut);
    ComposeCommand composeCommand = new ComposeCommandWithStrategy(new CompositePreview(),
        new PreviewParamBuilder(), previewComposeIn);
    BufferedImage composed = composeCommand.doCompose();

    StandardStoreIn storeIn = mapToStoreIn(composed);
    StoreCommand storeCommand = new ImageStore(storage, storeIn);
    StandardStoreOut storeOut = storeCommand.store();

    return presenter.success(new ComposeOutMsg(composed, storeOut.getPath()));
  }

  private PreviewImageDownloadIn mapToDownloadIn(ComposeInMsg inMsg) {
    return new PreviewImageDownloadIn(inMsg.getGuideUrl(), inMsg.getBgUrl(), inMsg.getMockUrl(),
        inMsg.getImageUrl());
  }

  private PreviewComposeIn mapToComposeIn(ComposeInMsg inMsg,
      PreviewImageDownloadOut previewImageDownloadOut) {
    return new PreviewComposeIn(previewImageDownloadOut.getGuideImage(),
        previewImageDownloadOut.getBgImage(),
        previewImageDownloadOut.getMockImage(), previewImageDownloadOut.getUserImage(),
        inMsg.getLeft(), inMsg.getTop(),
        inMsg.getBackgroundColor());
  }

  private StandardStoreIn mapToStoreIn(BufferedImage image) {
    return new StandardStoreIn(image);
  }
}
