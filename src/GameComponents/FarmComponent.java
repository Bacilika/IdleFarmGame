package GameComponents;

import BlockTypes.Crop;
import BlockTypes.CropTypes.BlockType;
import BlockTypes.FarmBlock;
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
    private final static String BASE_PATH = "resources/Blocks/";
    public Point mouseLocation = null;

    public static EnumMap<BlockType,List<BufferedImage>> blockTextures = loadTextures(BASE_PATH);
    public static EnumMap<Tool, Cursor> cursorTool = new EnumMap<>(Tool.class);
    public static HashMap<Integer, BufferedImage> grassTrims = getGrassTrims();

    public FarmComponent(FarmArea farmArea){
        this.farmArea = farmArea;
        setBorder(BorderFactory.createLineBorder(new Color(100, 55, 55), 5));
        for(Tool tool: Tool.values()) {
            if(tool == Tool.NONE) continue;
            try {
                cursorTool.put(tool, Toolkit.getDefaultToolkit().createCustomCursor(
                        ImageIO.read(new File(tool.getPath())),
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
                    images.add(ImageIO.read(new File(path + STR."\{type.name()}/\{type.name()}\{i}.png")));
                }
                catch (Exception e){
                    break;
                }
            }
            newMap.put(type,images);
        }
        return newMap;

    }

    public static HashMap<Integer, BufferedImage> getGrassTrims() {
        HashMap<Integer, BufferedImage> grassTrims = new HashMap<>();
        for(int i = 1; i < 8; i++){
            try {
                grassTrims.put(i, ImageIO.read(new File(STR."\{BASE_PATH}grasstrim/grasstrim\{i}.png")));
            }
            catch (Exception e){
                break;
            }
        }
        return grassTrims;
    }
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(farmArea.getFarmArea().getFirst().size()* blockSize,
                            farmArea.getFarmArea().size()* blockSize);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        for (int y = 0; y < farmArea.getFarmArea().size(); y++) {
            for (int x = 0; x < farmArea.getFarmArea().getFirst().size(); x++) {
                farmArea.getFarmBlock(y,x).draw(g2d);
            }
        }
        for (FarmBlock farmBlock: farmArea.getFarmBlocks()) {
            farmBlock.draw(g2d);
        }
        for(Crop crop: farmArea.getHarvestedCrops()) {
            crop.draw(g2d);
        }



        if(mouseLocation != null) {
            g.setColor(Color.RED);
            g.drawRect(mouseLocation.x*blockSize, mouseLocation.y*blockSize,
                    blockSize, blockSize);
        }
    }

}
