package BlockTypes.CropTypes;

import BlockTypes.Crop;
import BlockTypes.Soil;

public class Carrot extends Crop {

    public Carrot(int x, int y, Soil soil) {
        super(x, y, 500, 2, 7, 10, BlockType.CARROT,soil );
    }
}
