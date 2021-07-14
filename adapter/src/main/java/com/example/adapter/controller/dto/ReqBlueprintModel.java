package com.example.adapter.controller.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ReqBlueprintModel {

  private final String guideUrl;
  private final String userImageUrl;
  private final int left;
  private final int top;
  private final String backgroundColor;
}
