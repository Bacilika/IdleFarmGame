package MainGame;

import BlockTypes.Block;
import BlockTypes.Crop;
import BlockTypes.CropTypes.BlockType;
import BlockTypes.FarmBlock;
import BlockTypes.Soil;
import GameComponents.FarmComponent;
import GameComponents.Shop;

import java.awt.*;
import java.awt.event.MouseEvent;

public class MouseHandler{
    private final FarmArea farmArea;
    private final Player player;
    private final Shop shop;
    private final FarmComponent farmComponent;
    private boolean dragging = false;

    public MouseHandler(FarmArea farmArea, Shop shop, FarmComponent farmComponent){
        this.farmArea = farmArea;
        this.shop = shop;
        this.farmComponent = farmComponent;
        player = farmArea.getPlayer();
    }

    public void mousePressed(MouseEvent e){
        Point p = farmArea.pixelToBlock(e.getX(), e.getY());

        if (e.getButton() == MouseEvent.BUTTON3) {
            shop.setActiveBlock(null);
            farmComponent.mouseLocation = null;
            player.setCurrentTool(null);

        } else if (e.getButton() == MouseEvent.BUTTON1) {
            if (farmComponent.contains(e.getPoint())) {
                Block farmBlock = farmArea.getVisibleBlock(p.y, p.x);

                if(!tryToPlaceBlock(p, farmBlock)){
                    if (player.getCurrentTool() != null) {

                        switch (player.getCurrentTool()) {
                            case WATERING_CAN -> farmBlock.water();
                            case HOE -> farmArea.hoe(p);
                        }
                    }
                    else{
                        farmArea.harvestCrop(p);
                    }
                }
            }
        }
        farmComponent.repaint();

    }
    public void mouseDragged(MouseEvent e){

        dragging = true;
        Point p = farmArea.pixelToBlock(e.getX(), e.getY());
        if(farmArea.getHeldBlock() == null){
            for(Crop farmBlock: farmArea.getHarvestedCrops()){
                if(farmBlock.getX() == p.x && farmBlock.getY() == p.y){
                    farmBlock.setX(p.x);
                    farmBlock.setY(p.y);
                    farmArea.setHeldBlock(farmBlock);
                }
            }
        }
        else{
            farmArea.getHeldBlock().setX(p.x);
            farmArea.getHeldBlock().setY(p.y);
        }
    }
    public void mouseReleased( ){
        if (dragging){
            dragging = false;
            farmArea.setHeldBlock(null);
        }
    }
    boolean tryToPlaceBlock(Point p, Block farmBlock){
        if (farmBlock.getName() != BlockType.SOIL) { // Square is occupied

            shop.setActiveBlock(null);
            farmComponent.mouseLocation = null;
            return false;
        }
        if (shop.getActiveBlock() != null) {

            player.setCurrentTool(null);
            FarmBlock block = (FarmBlock) shop.getActiveBlock();
            Class<? extends FarmBlock> cropType = block.getClass();
            try {
                Crop newBlock = (Crop) cropType.getConstructor(int.class, int.class, Soil.class).newInstance(p.x, p.y, null);
                newBlock.onCreate(p.x, p.y);
                newBlock.setSoil(farmArea.getFarmBlock(p.y, p.x));
                farmArea.trackBlock(newBlock);
                return true;
            } catch (Exception ignored) {

            }
        }

        return false;
    }

}
