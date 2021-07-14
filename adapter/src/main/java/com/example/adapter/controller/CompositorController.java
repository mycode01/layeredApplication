package com.example.adapter.controller;

import com.example.adapter.controller.dto.ReqBlueprintModel;
import com.example.adapter.controller.dto.ReqPreviewModel;
import com.example.adapter.controller.dto.ResBlueprintModel;
import com.example.adapter.controller.dto.ResPreviewModel;
import com.example.usecase.ComposeUseCase;
import com.example.usecase.msg.ComposeInMsg;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CompositorController {

  private final ComposeUseCase blueprintUseCase;
  private final ComposeUseCase previewUseCase;

  public CompositorController(ComposeUseCase blueprintUseCase,
      ComposeUseCase previewUseCase) {
    this.blueprintUseCase = blueprintUseCase;
    this.previewUseCase = previewUseCase;
  }

  @PostMapping("/preview")
  ResPreviewModel makePreview(@RequestBody ReqPreviewModel model) {
    final var inMsg = new ComposeInMsg(model.getGuideUrl(), model.getBgUrl(), model.getMockUrl(),
        model.getUserImageUrl(), model.getLeft(), model.getTop(), model.getBackgroundColor());
    return previewUseCase.doWork(inMsg);
  }

  @PostMapping("/blueprint")
  ResBlueprintModel makeBlueprint(@RequestBody ReqBlueprintModel model) {
    final var inMsg = new ComposeInMsg(model.getGuideUrl(), null, null, // todo be fixed
        model.getUserImageUrl(), model.getLeft(), model.getTop(), model.getBackgroundColor());
    return blueprintUseCase.doWork(inMsg);
  }

}
