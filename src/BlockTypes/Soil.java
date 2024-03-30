package BlockTypes;

import BlockTypes.CropTypes.BlockType;

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
}
