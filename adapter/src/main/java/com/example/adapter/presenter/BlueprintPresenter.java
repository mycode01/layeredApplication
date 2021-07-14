package com.example.adapter.presenter;

import com.example.adapter.controller.dto.ResBlueprintModel;
import com.example.presenter.ComposePresenter;
import com.example.usecase.msg.ComposeOutMsg;

public class BlueprintPresenter implements ComposePresenter<ComposeOutMsg, ResBlueprintModel> {

  @Override
  public ResBlueprintModel success(ComposeOutMsg res) {
    return new ResBlueprintModel("ok", res.getPath());
  }// dummy
}
