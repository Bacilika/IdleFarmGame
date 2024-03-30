package BlockTypes;

import BlockTypes.CropTypes.BlockType;

import java.awt.*;

public class Soil extends FarmBlock{
    public Soil(int x, int y) {
        super(x, y, BlockType.SOIL, 0, 0);
    }
    public void water() {
        alternative = true;
    }
    public void setAlternative(boolean b) {
        alternative = b;
    }

    @Override
    public void draw(Graphics2D g2d) {
        super.draw(g2d);
        if(alternative) {
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
            g2d.fillRect(x, y, BLOCK_SIZE, BLOCK_SIZE);
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        }
    }
}
