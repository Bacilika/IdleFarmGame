package MainGame;

import BlockTypes.*;
import BlockTypes.CropTypes.BlockType;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static BlockTypes.FarmBlock.BLOCK_SIZE;

public class FarmArea {
    private final List<List<Block>> farmArea;
    private final List<FarmBlock> farmBlocks;
    private final Player player;
    private float offset = 0;
    private final List<Crop> harvestedCrops = new ArrayList<>();
    private Block heldBlock = null;

    public FarmArea(int height, int width) {
        farmArea = new ArrayList<>();
        farmBlocks = new ArrayList<>();
        player = new Player();
        for (int i = 0; i < height; i++) {
            List<Block> row = new ArrayList<>();
            for (int j = 0; j < width; j++) {
                row.add(new Grass(j, i));
            }
            farmArea.add(row);
        }
    }

    public List<List<Block>> getFarmArea() {
        return farmArea;
    }

    public Point pixelToBlock(int x, int y) {
        return new Point(x / BLOCK_SIZE, (int) ((y - offset) / BLOCK_SIZE));
    }

    public void tick() {
        for (FarmBlock farmBlock : farmBlocks) {
            farmBlock.performAction();
        }
    }

    public Player getPlayer() {
        return player;
    }

    public void setOffset(float offset) {
        this.offset = offset;
    }

    public void trackBlock(FarmBlock newBlock) {
        if (player.buy(newBlock.getPrice())) {
            farmBlocks.add(newBlock);
        }
    }

    public Block getVisibleBlock(int y, int x) {
        for (FarmBlock farmBlock : farmBlocks) {
            if (farmBlock.getX() == x && farmBlock.getY() == y) {
                return farmBlock;
            }
        }
        return farmArea.get(y).get(x);
    }

    public void hoe(Point p) {
        Block block = getVisibleBlock(p.y, p.x);
        if (block.getName() == BlockType.GRASS) {
            Block newBlock = new Soil(p.x, p.y);
            farmArea.get(p.y).set(p.x, newBlock);
            updateSoilTextures();

        }
    }

    public Block getFarmBlock(int y, int x) {
        return farmArea.get(y).get(x);
    }

    public List<FarmBlock> getFarmBlocks() {
        return farmBlocks;
    }

    public void harvestCrop(Point p) {
        for (FarmBlock crop : farmBlocks) {
            if (crop.getX() == p.x && crop.getY() == p.y) {
                if (crop.onHarvest()) {
                    farmBlocks.remove(crop);
                    harvestedCrops.add((Crop) crop);
                }
                break;
            }
        }
    }

    public List<Crop> getHarvestedCrops() {
        return harvestedCrops;
    }

    public void setHeldBlock(Block block) {
        heldBlock = block;
    }

    public Block getHeldBlock() {
        return heldBlock;
    }

    public void updateSoilTextures() {
        int height = farmArea.size();
        int width = farmArea.getFirst().size();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (getFarmBlock(y, x).getName() == BlockType.SOIL) {
                    FarmBlock soil = (FarmBlock) getFarmBlock(y, x);
                    int[][] surroundingBlocks = new int[3][3];
                    for (int i = 0; i < 3; i++) {
                        for (int j = 0; j < 3; j++) {
                            if (y + i - 1 >= 0 && y + i - 1 < height &&
                                    x + j - 1 >= 0 && x + j - 1 < width) {
                                surroundingBlocks[i][j] = getFarmBlock(y + i - 1, x + j - 1).getName()
                                        == BlockType.SOIL ? 1 : 0;
                            } else {
                                surroundingBlocks[i][j] = 0;
                            }
                        }
                    }
                    soil.setIndex(surroundingBlocks);
                }
            }
        }
    }
}
