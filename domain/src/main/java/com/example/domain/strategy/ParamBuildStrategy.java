package com.example.domain.strategy;

import com.example.domain.command.dto.compose.ComposeIn;
import com.example.domain.strategy.dto.ComposeParam;

public interface ParamBuildStrategy<I extends ComposeIn> {

  ComposeParam build(I in);
}
