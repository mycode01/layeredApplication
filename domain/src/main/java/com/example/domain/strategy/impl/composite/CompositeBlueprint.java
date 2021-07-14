package com.example.domain.strategy.impl.composite;

import com.example.domain.strategy.CompositeStrategy;
import com.example.domain.strategy.dto.BlueprintComposeParam;
import com.example.domain.strategy.dto.ComposeResult;
import com.example.domain.strategy.dto.StandardComposeResult;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Objects;

public class CompositeBlueprint implements CompositeStrategy<BlueprintComposeParam, ComposeResult> {

  public ComposeResult compose(BlueprintComposeParam param) {
    return new StandardComposeResult(
        makeDesign(param.getGuideImage(), param.getTargetImage(), param.getLeft(), param.getTop(),
            CompositeCommons.getColor(param.getBackgroundColor())));
  }

  private BufferedImage makeDesign(BufferedImage guide, BufferedImage img,
      int posX, int posY, Color bgColor) {
    BufferedImage origin = CompositeCommons
        .makeImageArea(img, posX, posY, guide.getWidth(), guide.getHeight());
    BufferedImage canvas = new BufferedImage(guide.getWidth(), guide.getHeight(),
        BufferedImage.TYPE_INT_ARGB);

    Graphics2D g = canvas.createGraphics();
    if (Objects.nonNull(bgColor)) {
      g.setColor(bgColor);
      g.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    int targetX = Math.max(posX, 0);
    int targetY = Math.max(posY, 0);

    g.drawImage(origin, targetX, targetY, origin.getWidth(), origin.getHeight(), null);

    g.dispose();
    return canvas;
  }

}
