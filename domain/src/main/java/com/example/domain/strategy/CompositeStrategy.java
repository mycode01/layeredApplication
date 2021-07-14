package com.example.domain.strategy;

import com.example.domain.strategy.dto.ComposeParam;
import com.example.domain.strategy.dto.ComposeResult;

public interface CompositeStrategy<I extends ComposeParam, O extends ComposeResult> {

  O compose(I param);
}
