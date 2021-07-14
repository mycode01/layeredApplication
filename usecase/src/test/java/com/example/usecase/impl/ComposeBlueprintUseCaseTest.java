package com.example.usecase.impl;

import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.when;

import com.example.domain.service.imagedownloader.Downloader;
import com.example.domain.service.imagestorage.Storage;
import com.example.presenter.ComposePresenter;
import com.example.usecase.ComposeUseCase;
import com.example.usecase.msg.ComposeInMsg;
import com.example.usecase.msg.ComposeOutMsg;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import javax.imageio.ImageIO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class ComposeBlueprintUseCaseTest {

  @Mock
  Downloader downloader;
  @Mock
  Storage s3;

  @Mock
  ComposePresenter<ComposeOutMsg, ComposeOutMsg> presenter;

  ComposeUseCase useCase;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    useCase = new BlueprintUseCase(downloader, s3, presenter);
  }

  @Test
  void testDoWork() throws IOException {
    ClassLoader classLoader = getClass().getClassLoader();

    BufferedImage guideImage = ImageIO.read(new File(classLoader.getResource("cut.png").getFile()));
    BufferedImage userImage = ImageIO
        .read(new File(classLoader.getResource("image.png").getFile()));

    when(downloader.download(eq("guideUrl"))).thenReturn(guideImage);
    when(downloader.download(eq("imageUrl"))).thenReturn(userImage);
    when(s3.store(any()))
        .thenReturn("https://s3.ap-northeast-2.amazonaws.com/somebucketname/sub/sub/blueprint.png");

    when(presenter.success(any())).thenAnswer(returnsFirstArg());

    ComposeOutMsg result = useCase.doWork(
        new ComposeInMsg("guideUrl", null, null, "imageUrl", 1100, 1100, "#000000"));
    Assertions.assertNotNull(result);
    Assertions.assertTrue(result.getPath().contains("blueprint"));
    ImageIO
        .write(result.getResult(), "png",
            Paths.get("src", "test", "resources", "blueprint.png").toFile());
  }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme