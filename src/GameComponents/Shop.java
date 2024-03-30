package GameComponents;

import BlockTypes.Block;
import BlockTypes.Crop;
import BlockTypes.CropTypes.Carrot;
import BlockTypes.CropTypes.Wheat;
import BlockTypes.Soil;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Shop extends JComponent {
    private final List<Block> shopItems;

    private Block activeBlock = null;
    public Shop() {
        setLayout(new FlowLayout());
        shopItems = new ArrayList<>();
        stockShop();
        setBorder(BorderFactory.createLineBorder(new Color(100, 55, 55), 5));
    }

    public void stockShop() {
        Soil _soil = new Soil(0, 0);
        shopItems.add(new Wheat(0, 0, _soil));
        shopItems.add(new Carrot(0, 0, _soil));
        for(Block block: shopItems) {
            if (block instanceof Crop crop) {
                crop.setHarvested(true);
            }
            JButton button = new JButton(new ImageIcon(block.getTexture()));
            //noinspection preview
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
