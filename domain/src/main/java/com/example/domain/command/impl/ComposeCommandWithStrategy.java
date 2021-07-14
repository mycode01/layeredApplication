package com.example.domain.command.impl;

import com.example.domain.command.ComposeCommand;
import com.example.domain.command.dto.compose.ComposeIn;
import com.example.domain.strategy.CompositeStrategy;
import com.example.domain.strategy.ParamBuildStrategy;
import com.example.domain.strategy.dto.ComposeParam;
import com.example.domain.strategy.dto.ComposeResult;
import java.awt.image.BufferedImage;

public class ComposeCommandWithStrategy implements ComposeCommand {

  private final CompositeStrategy compositeStrategy;
  private final ParamBuildStrategy paramBuildStrategy;
  private final ComposeIn composeIn;

  public ComposeCommandWithStrategy(CompositeStrategy compositeStrategy,
      ParamBuildStrategy paramBuildStrategy, ComposeIn in) {
    this.compositeStrategy = compositeStrategy;
    this.paramBuildStrategy = paramBuildStrategy;
    this.composeIn = in;
  }

  @Override
  public BufferedImage doCompose() {
    final ComposeParam param = paramBuildStrategy.build(composeIn);
    final ComposeResult result = compositeStrategy.compose(param);
    return result.getResult();
  }
}
