package com.example.domain.command.impl;

import com.example.domain.command.StoreCommand;
import com.example.domain.command.dto.store.StandardStoreIn;
import com.example.domain.command.dto.store.StandardStoreOut;
import com.example.domain.service.imagestorage.Storage;
import java.awt.image.BufferedImage;

public class ImageStore implements StoreCommand {

  private final Storage storage;
  private final BufferedImage image;

  public ImageStore(Storage storage, StandardStoreIn in) {
    this.storage = storage;
    this.image = in.getImage();
  }

  @Override
  public StandardStoreOut store() {
    return new StandardStoreOut(storage.store(image));
  }
}
