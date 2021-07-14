package com.example.application;

import com.example.adapter.controller.dto.ResBlueprintModel;
import com.example.adapter.controller.dto.ResPreviewModel;
import com.example.adapter.presenter.BlueprintPresenter;
import com.example.adapter.presenter.PreviewPresenter;
import com.example.application.config.ServerRunConfig;
import com.example.domain.service.imagedownloader.Downloader;
import com.example.domain.service.imagestorage.Storage;
import com.example.presenter.ComposePresenter;
import com.example.usecase.ComposeUseCase;
import com.example.usecase.impl.BlueprintUseCase;
import com.example.usecase.impl.PreviewUseCase;
import com.example.usecase.msg.ComposeInMsg;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@RequiredArgsConstructor
@SpringBootApplication
@ComponentScan(basePackages = {"com.example.application.config"},
    excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = {ServerRunConfig.class})})
public class CMRunner implements CommandLineRunner {

  public static void main(String[] args) {
    SpringApplication.run(CMRunner.class, args).close();
  }

  @Override
  public void run(String... args) throws Exception {
    final Storage storage = storage();
    final Downloader downloader = downloader();
    final ComposePresenter blueprintPresenter = blueprintPresenter();
    final ComposePresenter previewPresenter = previewPresenter();

    final ComposeUseCase blueprintUseCase = blueprintUseCase(storage, downloader,
        blueprintPresenter);
    final ComposeUseCase previewUseCase = previewUseCase(storage, downloader, previewPresenter);

    final ComposeInMsg in = new ComposeInMsg("guideUrl", "bgUrl", "mockUrl", "imageUrl", 1100, 1100,
        "#ffffff");
    ResBlueprintModel blueprint = blueprintUseCase.doWork(in);
    ResPreviewModel preview = previewUseCase.doWork(in);

    System.out.println(blueprint.getLocation());
    System.out.println(preview.getLocation());
  }

  private ComposeUseCase blueprintUseCase(Storage storage, Downloader downloader,
      ComposePresenter composePresenter) {
    return new BlueprintUseCase<ResBlueprintModel>(downloader, storage, composePresenter);
  }

  private ComposeUseCase previewUseCase(Storage storage, Downloader downloader,
      ComposePresenter composePresenter) {
    return new PreviewUseCase<ResPreviewModel>(downloader, storage, composePresenter);
  }

  private Storage storage() {
    return new Storage() {
      @SneakyThrows // for dummy
      @Override
      public String store(BufferedImage image) {
        Path path = Paths
            .get("application", "src", "main", "res", System.currentTimeMillis() + ".png");
        ImageIO.write(image, "png", path.toFile());
        System.out.println("stored! :: " + path.toAbsolutePath());
        return path.toString();
      }
    };
  } // NOT TESTED

  private Downloader downloader() {
    return new Downloader() {
      Map<String, String> lookupTable = new HashMap<>() {{
        put("guideUrl", "cut.png");
        put("mockUrl", "mask.png");
        put("bgUrl", "area.png");
        put("imageUrl", "image.png");
      }};

      @SneakyThrows // god forgive me
      @Override
      public BufferedImage download(String url) {
        ClassLoader classLoader = getClass().getClassLoader();
        return ImageIO.read(new File(classLoader.getResource(lookupTable.get(url)).getFile()));
      }
    };
  } // NOT TESTED


  private PreviewPresenter previewPresenter() {
    return new PreviewPresenter();
  }

  private BlueprintPresenter blueprintPresenter() {
    return new BlueprintPresenter();
  }
}
