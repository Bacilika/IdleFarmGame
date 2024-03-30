package BlockTypes;

import BlockTypes.CropTypes.BlockType;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Grass extends FarmBlock {
    public Grass(int x, int y) {

        super(x, y,BlockType.GRASS,0,0);
    }

}

