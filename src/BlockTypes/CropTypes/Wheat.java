package BlockTypes.CropTypes;

import BlockTypes.Crop;
import BlockTypes.FarmBlock;
import BlockTypes.Soil;

import java.awt.*;
import java.util.List;

public class Wheat extends Crop {

    public Wheat(int x, int y, Soil soil) {
        super(x, y, 500, 1, 5,7, BlockType.WHEAT, soil);
    }

}
