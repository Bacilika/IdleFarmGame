package BlockTypes;

import BlockTypes.CropTypes.BlockType;
import GameComponents.FarmComponent;

import java.awt.*;
import java.util.HashSet;

public class Soil extends FarmBlock{
    private final HashSet<Integer> trims;
    public Soil(int x, int y) {
        super(x, y, BlockType.SOIL, 0, 0);
        trims = new HashSet<>();
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
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
            g2d.fillRect(x*BLOCK_SIZE, y*BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        }
        for (int trim : trims) {
            g2d.drawImage(FarmComponent.getTrim(trim), x*BLOCK_SIZE, y*BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE, null);
        }
    }
    public void removeTrim(int trim) {
        trims.remove(trim);
    }
    public void fillTrims() {
        for (int i = 0; i <= 8; i++) {
            trims.add(i);
        }
    }

}
