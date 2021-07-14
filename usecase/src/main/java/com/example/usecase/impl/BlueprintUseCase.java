package com.example.usecase.impl;

import com.example.domain.command.ComposeCommand;
import com.example.domain.command.DownloadCommand;
import com.example.domain.command.StoreCommand;
import com.example.domain.command.dto.compose.BlueprintComposeIn;
import com.example.domain.command.dto.download.BlueprintImageDownloadIn;
import com.example.domain.command.dto.download.BlueprintImageDownloadOut;
import com.example.domain.command.dto.store.StandardStoreIn;
import com.example.domain.command.dto.store.StandardStoreOut;
import com.example.domain.command.impl.ComposeCommandWithStrategy;
import com.example.domain.command.impl.ImageForBlueprintDownload;
import com.example.domain.command.impl.ImageStore;
import com.example.domain.service.imagedownloader.Downloader;
import com.example.domain.service.imagestorage.Storage;
import com.example.domain.strategy.impl.composite.CompositeBlueprint;
import com.example.domain.strategy.impl.parambuild.BlueprintParamBuilder;
import com.example.presenter.ComposePresenter;
import com.example.usecase.ComposeUseCase;
import com.example.usecase.msg.ComposeInMsg;
import com.example.usecase.msg.ComposeOutMsg;
import java.awt.image.BufferedImage;

public class BlueprintUseCase<O> implements ComposeUseCase {

  private final Downloader downloader;
  private final Storage storage;
  private final ComposePresenter<ComposeOutMsg, O> presenter;

  public BlueprintUseCase(Downloader downloader,
      Storage storage,
      ComposePresenter<ComposeOutMsg, O> presenter) {
    this.downloader = downloader;
    this.storage = storage;
    this.presenter = presenter;
  }

  @Override
  public O doWork(ComposeInMsg inMsg) {
    BlueprintImageDownloadIn downloadIn = mapToDownloadIn(inMsg);
    DownloadCommand downloadCommand = new ImageForBlueprintDownload(downloader, downloadIn);
    BlueprintImageDownloadOut downloadOut = downloadCommand.download();

    BlueprintComposeIn composeIn = mapToComposeIn(inMsg, downloadOut);
    ComposeCommand composeCommand = new ComposeCommandWithStrategy(new CompositeBlueprint(),
        new BlueprintParamBuilder(), composeIn);
    BufferedImage composed = composeCommand.doCompose();

    StandardStoreIn storeIn = mapToStoreIn(composed);
    StoreCommand storeCommand = new ImageStore(storage, storeIn);
    StandardStoreOut storeOut = storeCommand.store();

    return presenter.success(new ComposeOutMsg(composed, storeOut.getPath()));
  }

  private BlueprintImageDownloadIn mapToDownloadIn(ComposeInMsg inMsg) {
    return new BlueprintImageDownloadIn(inMsg.getGuideUrl(), inMsg.getImageUrl());
  }

  private BlueprintComposeIn mapToComposeIn(ComposeInMsg inMsg,
      BlueprintImageDownloadOut downloadOut) {
    return new BlueprintComposeIn(downloadOut.getGuideImage(), downloadOut.getUserImage(),
        inMsg.getLeft(), inMsg.getTop(), inMsg.getBackgroundColor());
  }

  private StandardStoreIn mapToStoreIn(BufferedImage image) {
    return new StandardStoreIn(image);
  }
}
