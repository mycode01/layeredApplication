package com.example.domain.strategy.impl.parambuild;

import com.example.domain.command.dto.compose.BlueprintComposeIn;
import com.example.domain.strategy.ParamBuildStrategy;
import com.example.domain.strategy.dto.BlueprintComposeParam;
import com.example.domain.strategy.dto.ComposeParam;

public class BlueprintParamBuilder implements ParamBuildStrategy<BlueprintComposeIn> {

  @Override
  public ComposeParam build(BlueprintComposeIn in) {
    return new BlueprintComposeParam(in.getGuideImage(), in.getUserImage(),
        in.getLeft(), in.getTop(), in.getBackgroundColor());
  }
}
