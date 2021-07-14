package com.example.adapter.controller.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ComposeResponse {

  private final String message;
  private final String location;
}
