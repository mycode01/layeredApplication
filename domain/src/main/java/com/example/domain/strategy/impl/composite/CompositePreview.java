package com.example.domain.strategy.impl.composite;

import com.example.domain.strategy.CompositeStrategy;
import com.example.domain.strategy.dto.ComposeResult;
import com.example.domain.strategy.dto.PreviewComposeParam;
import com.example.domain.strategy.dto.StandardComposeResult;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Objects;

public class CompositePreview implements CompositeStrategy<PreviewComposeParam, ComposeResult> {

  @Override
  public ComposeResult compose(PreviewComposeParam param) {
    return new StandardComposeResult(makePreview(param.getGuideImage(), param.getBackgroundImage(),
        param.getMockImage(), param.getTargetImage(),
        param.getLeft(), param.getTop(), CompositeCommons.getColor(param.getBackgroundColor())));
  }

  private BufferedImage makePreview(BufferedImage guide, BufferedImage bg,
      BufferedImage mock, BufferedImage img, int posX, int posY, Color bgColor) {
    BufferedImage origin = CompositeCommons
        .makeImageArea(img, posX, posY, guide.getWidth(), guide.getHeight());
    BufferedImage canvas = new BufferedImage(guide.getWidth(), guide.getHeight(),
        BufferedImage.TYPE_INT_ARGB);
    BufferedImage colorBg = bg;
    if (Objects.nonNull(bgColor)) {
      colorBg = CompositeCommons.makeFilledBg(bg, bgColor);
    }
    int targetX = Math.max(posX, 0);
    int targetY = Math.max(posY, 0);

    Graphics2D g = canvas.createGraphics();
    g.drawImage(colorBg, 0, 0, colorBg.getWidth(), guide.getHeight(), null);
    g.drawImage(origin, targetX, targetY, origin.getWidth(), origin.getHeight(), null);
    g.drawImage(guide, 0, 0, guide.getWidth(), guide.getHeight(), null);
    g.drawImage(mock, 0, 0, mock.getWidth(), mock.getHeight(), null);
    g.dispose();
    return canvas;
  }
}


