package com.example.application.config;

import com.example.adapter.controller.dto.ResBlueprintModel;
import com.example.adapter.controller.dto.ResPreviewModel;
import com.example.adapter.presenter.BlueprintPresenter;
import com.example.adapter.presenter.PreviewPresenter;
import com.example.domain.service.imagedownloader.Downloader;
import com.example.domain.service.imagestorage.Storage;
import com.example.presenter.ComposePresenter;
import com.example.usecase.ComposeUseCase;
import com.example.usecase.impl.BlueprintUseCase;
import com.example.usecase.impl.PreviewUseCase;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javax.imageio.ImageIO;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServerRunConfig {


  @Bean
  public ComposeUseCase blueprintUseCase(Storage storage, Downloader downloader,
      ComposePresenter blueprintPresenter) {
    return new BlueprintUseCase<ResBlueprintModel>(downloader, storage, blueprintPresenter);
  }

  @Bean
  public ComposeUseCase previewUseCase(Storage storage, Downloader downloader,
      ComposePresenter previewPresenter) {
    return new PreviewUseCase<ResPreviewModel>(downloader, storage, previewPresenter);
  }

  @Bean
  public Storage storage() {
    return new Storage() {
      @SneakyThrows // for dummy
      @Override
      public String store(BufferedImage image) {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ImageIO.write(image, "png", os);
        String path = "/result/" + System.currentTimeMillis() + ".png";
        Files.copy(new ByteArrayInputStream(os.toByteArray()),
            Paths.get(path),
            StandardCopyOption.REPLACE_EXISTING);
        return path;
      }
    };
  } // NOT TESTED

  @Bean
  public Downloader downloader() {
    return new Downloader() {
      @SneakyThrows // god forgive me
      @Override
      public BufferedImage download(String url) {
        return ImageIO.read(new URL(url));
      }
    };
  } // NOT TESTED

  @Bean
  public PreviewPresenter previewPresenter() {
    return new PreviewPresenter();
  }

  @Bean
  public BlueprintPresenter blueprintPresenter() {
    return new BlueprintPresenter();
  }
}