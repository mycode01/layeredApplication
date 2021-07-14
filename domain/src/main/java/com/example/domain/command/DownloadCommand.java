package com.example.domain.command;

import com.example.domain.command.dto.download.DownloadOut;

public interface DownloadCommand {

  <T extends DownloadOut> T download();

}
