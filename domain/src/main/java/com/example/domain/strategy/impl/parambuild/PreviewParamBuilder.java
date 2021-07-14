package com.example.domain.strategy.impl.parambuild;

import com.example.domain.command.dto.compose.PreviewComposeIn;
import com.example.domain.strategy.ParamBuildStrategy;
import com.example.domain.strategy.dto.ComposeParam;
import com.example.domain.strategy.dto.PreviewComposeParam;

public class PreviewParamBuilder implements ParamBuildStrategy<PreviewComposeIn> {

  @Override
  public ComposeParam build(PreviewComposeIn in) {
    return new PreviewComposeParam(in.getGuideImage(), in.getBgImage(), in.getMockImage(),
        in.getUserImage(),
        in.getLeft(), in.getTop(), in.getBackgroundColor());
  }
}
