package BlockTypes;

import BlockTypes.CropTypes.BlockType;
import GameComponents.FarmComponent;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

public abstract class FarmBlock implements Block {
    protected int x;
    protected int y;
    public final static int BLOCK_SIZE = 50;
    protected final List<BufferedImage> blockTextures;
    protected final BlockType name;
    protected final int price;
    protected final int sellPrice;
    protected int index = 1;


    public FarmBlock( int x, int y,BlockType name, int price, int sellPrice) {
        this.x = x;
        this.y = y;
        this.blockTextures = FarmComponent.BLOCK_TEXTURES.get(name);
        this.name = name;
        this.price = price;
        this.sellPrice = sellPrice;

    }

    public void water() {
        // TODO Auto-generated method stub
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void draw(Graphics2D g2d) {

        g2d.drawImage(getTexture(), x*BLOCK_SIZE, y*BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE, null);
    }
    public BufferedImage getTexture() {
        return blockTextures.get(index-1);
    }
    public BlockType getName() {
        return name;
    }

    public int getPrice() {
        return sellPrice;
    }
    public int getSellPrice() {
        return sellPrice;
    }

    public void onCreate() {

        // TODO Auto-generated method stub
    }
    public void performAction() {
        // TODO Auto-generated method stub
    }

    @Override
    public boolean onHarvest() {
        return false;
    }

}
