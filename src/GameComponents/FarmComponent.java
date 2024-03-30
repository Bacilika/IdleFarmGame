package GameComponents;

import BlockTypes.Block;
import BlockTypes.Crop;
import BlockTypes.CropTypes.BlockType;
import BlockTypes.FarmBlock;
import BlockTypes.Soil;
import MainGame.FarmArea;
import Tools.Tool;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;

public class FarmComponent extends JComponent  {
    private final FarmArea farmArea;
    private final int blockSize = FarmBlock.BLOCK_SIZE;

    public Point mouseLocation = null;

    public static EnumMap<BlockType,List<BufferedImage>> blockTextures = loadTextures(STR."resources/Blocks/");
    public static EnumMap<BlockType,List<BufferedImage>>   alternativeBlockTextures = loadTextures(STR."resources/Blocks/alt");
    public static EnumMap<BlockType,List<BufferedImage>> cornerTextures = getCornerTextures();
    public static EnumMap<Tool, Cursor> cursorTool = new EnumMap<>(Tool.class);
    public HashMap<Point, List<String>> activeCorners = new HashMap<>();

    public FarmComponent(FarmArea farmArea){
        this.farmArea = farmArea;
        setBorder(BorderFactory.createLineBorder(new Color(100, 55, 55), 5));
        for(Tool tool: Tool.values()) {
            if(tool == Tool.NONE) continue;
            try {
                cursorTool.put(tool, Toolkit.getDefaultToolkit().createCustomCursor(
                        ImageIO.read(new File(STR."resources/Blocks/\{tool.name()}.png")),
                        new Point(0, 0), tool.name()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static EnumMap<BlockType, List<BufferedImage>> loadTextures(String path){
        EnumMap<BlockType, List<BufferedImage>> newMap = new EnumMap<>(BlockType.class);
        for(BlockType type: BlockType.values()){
            List<BufferedImage> images = new ArrayList<>();
            for(int i = 1; i < 20; i++){
                try {
                    images.add(ImageIO.read(new File(path + STR."\{type.name()}\{i}.png")));
                }
                catch (Exception e){
                    break;
                }
            }
            newMap.put(type,images);
        }
        return newMap;

    }

    public static EnumMap<BlockType, List<BufferedImage>> getCornerTextures() {
        EnumMap<BlockType, List<BufferedImage>> grassCornerTextures = new EnumMap<>(BlockType.class);
        for(BlockType type: BlockType.values()){
            List<BufferedImage> images = new ArrayList<>();
            for(int i = 1; i < 20; i++){
                try {
                    images.add(ImageIO.read(new File(STR."resources/Blocks/\{type.name()},corner\{i}.png")));
                }
                catch (Exception e){
                    break;
                }
            }
            grassCornerTextures.put(type,images);
        }
        return grassCornerTextures;
    }
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(farmArea.getFarmArea().getFirst().size()* blockSize,
                            farmArea.getFarmArea().size()* blockSize);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int y = 0; y < farmArea.getFarmArea().size(); y++) {
            for (int x = 0; x < farmArea.getFarmArea().getFirst().size(); x++) {
                farmArea.getFarmBlock(y,x).draw(g);
            }
        }
        for (FarmBlock farmBlock: farmArea.getFarmBlocks()) {
            farmBlock.draw(g);
        }
        for(Crop crop: farmArea.getHarvestedCrops()) {
            crop.draw(g);
        }
        for (Point corner: activeCorners.keySet()) {
            for (String cornerType: activeCorners.get(corner)) {
                String name = cornerType.split(",")[0];
                g.drawImage(cornerTextures.get(BlockType.valueOf(name)).getFirst(),
                        corner.x*blockSize, corner.y*blockSize, blockSize, blockSize, null);
            }
        }
        if(mouseLocation != null) {
            g.setColor(Color.RED);
            g.drawRect(mouseLocation.x*blockSize, mouseLocation.y*blockSize,
                    blockSize, blockSize);
        }
    }
    public static EnumMap<BlockType, List<BufferedImage>> getBlockTextures() {
        return blockTextures;
    }

}
