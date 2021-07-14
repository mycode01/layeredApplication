package com.example.adapter.presenter;

import com.example.adapter.controller.dto.ResPreviewModel;
import com.example.presenter.ComposePresenter;
import com.example.usecase.msg.ComposeOutMsg;

public class PreviewPresenter implements ComposePresenter<ComposeOutMsg, ResPreviewModel> {

  @Override
  public ResPreviewModel success(ComposeOutMsg res) {
    return new ResPreviewModel("ok", res.getPath());
  }// dummy
}
