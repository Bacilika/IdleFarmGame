package BlockTypes;

import BlockTypes.CropTypes.BlockType;

import java.awt.*;
import java.awt.image.BufferedImage;

public interface Block {
    int getX();
    int getY();
    BufferedImage getTexture();

    BlockType getName();

    void draw(Graphics2D g2d);

    void onCreate();

    void performAction();

    int getPrice();
    int getSellPrice();

    void water();
    boolean onHarvest();

    void setX(int x);

    void setY(int y);
}
