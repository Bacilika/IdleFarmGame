package GameComponents;

import BlockTypes.Block;
import BlockTypes.CropTypes.BlockType;
import BlockTypes.CropTypes.Carrot;
import BlockTypes.CropTypes.Wheat;
import BlockTypes.FarmBlock;
import BlockTypes.Soil;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;

public class Shop extends JComponent {
    private final List<Block> shopItems;
    private final EnumMap<BlockType,List<BufferedImage>> blocks;

    private Block activeBlock = null;
    public Shop() {
        setLayout(new FlowLayout());
        blocks = FarmComponent.getBlockTextures();

        shopItems = new ArrayList<>();


        stockShop();
        setBorder(BorderFactory.createLineBorder(new Color(100, 55, 55), 5));


    }
    public void stockShop() {
        Soil _soil = new Soil(0, 0);
        shopItems.add(new Wheat(0, 0, _soil));
        shopItems.add(new Carrot(0, 0, _soil));
        for(Block block: shopItems) {
            JButton button = new JButton( new ImageIcon(blocks.get(block.getName()).getLast()));
            button.setText(STR."\{block.getSellPrice()} coins");
            button.setMargin(new Insets(0, 0, 0, 0));
            button.setBorder(null);
            button.setBackground(Color.WHITE);
            button.addActionListener(_ -> buyItem(block));
            add(button);
        }
    }
    private void buyItem(Block block) {
        activeBlock = block;
        }

    public Dimension getPreferredSize() {
        return new Dimension(150, 0);
    }
    public Block getActiveBlock() {
        return activeBlock;
    }

    public void setActiveBlock(Block activeBlock) {
        this.activeBlock = activeBlock;
    }
}
