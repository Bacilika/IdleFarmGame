package BlockTypes;

import BlockTypes.CropTypes.BlockType;
import GameComponents.FarmComponent;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public abstract class FarmBlock implements Block {
    protected int x;
    protected int y;
    public final static int BLOCK_SIZE = 50;
    protected List<BufferedImage> blockTextures;
    protected List<BufferedImage> alterantiveBlockTextures;
    protected BlockType name;
    protected boolean alternative = false;

    protected int price;
    protected int sellPrice;
    protected int index = 1;


    public FarmBlock( int x, int y,BlockType name, int price, int sellPrice) {
        this.x = x;
        this.y = y;
        this.blockTextures = FarmComponent.blockTextures.get(name);
        this.name = name;
        this.price = price;
        this.sellPrice = sellPrice;

    }

    public void water() {
        // TODO Auto-generated method stub
    }
    public void hoe() {

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

    public void onCreate(int x, int y) {
        // TODO Auto-generated method stub
    }
    public void performAction() {
        // TODO Auto-generated method stub
    }

    @Override
    public boolean onHarvest() {
        return false;
    }

    public void setIndex(int[][] surrounding) {
        boolean top = surrounding[0][1] == 1;
        boolean down = surrounding[2][1] == 1;
        boolean left = surrounding[1][0] == 1;
        boolean right = surrounding[1][2] == 1;

        if(!top && !down && !left && !right){ //single
            index = 1;
        }
        else if(!top && !down && !left){ //right
            index = 2;
        }
        else if(!top && !down && !right){ //left
            index = 4;
        }
        else if(!top && down && !left && !right){ //down
            index = 3;
        }
        else if(top && !down && !left && !right){ //top
            index = 5;
        }
        else if(top && !down && !left){ //top
            index = 6;
        }
        else if(!top && down && !left){ //top
            index = 7;
        }
        else if(!top && down && !right){ //top
            index = 8;
        }
        else if(top && !down && !right){ //top
            index = 9;
        }
        else{
            index = 10;
        }
    }

}
