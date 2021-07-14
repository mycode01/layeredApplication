package com.example.domain.service.imagedownloader;

import java.awt.image.BufferedImage;

public interface Downloader {

  BufferedImage download(String url);

}
