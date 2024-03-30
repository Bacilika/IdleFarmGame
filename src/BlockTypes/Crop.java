package BlockTypes;
import BlockTypes.CropTypes.BlockType;

import java.awt.image.BufferedImage;
import java.util.List;

public abstract class Crop extends FarmBlock {
    protected final int growthTime;
    protected final int waterNeeded;
    protected int timeAlive = 0;
    protected final List<BufferedImage> growStages;
    protected int timesWatered = 0;
    protected boolean needsWater = false;
    protected boolean isHarvested = false;
    protected Soil soil;



    protected Crop(int x, int y, int growthTime, int waterNeeded,
                   int price, int sellPrice, BlockType name, Soil soil) {
        super(x,y, name,price,sellPrice);
        this.growthTime = growthTime;
        this.waterNeeded = waterNeeded;
        this.growStages = blockTextures;
        this.soil = soil;
    }
    @Override
    public BufferedImage getTexture() {
        double percentageLife = (double) timeAlive / growthTime;
        if (isHarvested) {
            return growStages.getLast();
        }
        if (percentageLife >= 1) {
            return growStages.get(growStages.size()-2);
        }
        return growStages.get(Math.max(0,(int) Math.floor(percentageLife * growStages.size())-1));
    }

    @Override
    public void performAction() {
        double percentageLife = (double) timeAlive / growthTime;
        needsWater = percentageLife >= (double) timesWatered / waterNeeded;

        if (!needsWater) {
            timeAlive++;
        }
        else{
            soil.setWatered(false);
        }
    }

    public void water() {
        if (needsWater) {
            soil.water();
            timesWatered++;
            needsWater = false;
        }
    }

    public boolean onHarvest() {
        double percentageLife = (double) timeAlive / growthTime;
        if (percentageLife < 1) {
            return false;
        }
        isHarvested = true;
        return true;
    }

    public void setSoil(Block soil){
        this.soil = (Soil) soil;
    }

    public void onCreate() {
        isHarvested = false;
        if(soil.isWatered()) {
            timesWatered++;}
    }

    public void setHarvested(boolean harvested){
        isHarvested = harvested;
    }
}
