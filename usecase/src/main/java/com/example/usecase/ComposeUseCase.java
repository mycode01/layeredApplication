package com.example.usecase;

import com.example.usecase.msg.ComposeInMsg;

public interface ComposeUseCase {

  <O> O doWork(ComposeInMsg inMsg);
}
