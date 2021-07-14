package com.example.adapter.controller.dto;

import com.example.presenter.dto.ComposeRes;


public class ResPreviewModel implements ComposeRes {

  private final ComposeResponse resp;

  public ResPreviewModel(String message, String location) {
    this.resp = new ComposeResponse(message, location);
  }

  public String getMessage() {
    return resp.getMessage();
  }

  @Override
  public String getLocation() {
    return resp.getLocation();
  }
}
