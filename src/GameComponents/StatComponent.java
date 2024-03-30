package GameComponents;

import MainGame.Player;
import Tools.Tool;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.EnumMap;

public class StatComponent extends JComponent {
    private Player player;

    public static EnumMap<Tool, ImageIcon> toolTextures = new EnumMap<>(Tool.class);


    public StatComponent(Player player) {
        setLayout(new GridLayout(1,5));
        setBorder(BorderFactory.createLineBorder(new Color(100, 55, 55), 5));
        this.player = player;
        addStats();
    }
    private void addStats() {
        add(new JLabel("Money: " + player.getMoney()));
        for(Tool tool: Tool.values()) {
            toolTextures.put(tool, new ImageIcon("resources/Blocks/"+tool.name()+".png"));
            JButton button = new JButton(new ImageIcon("resources/Blocks/"+tool.name()+".png"));
            button.setMargin(new Insets(0, 0, 0, 0));
            button.setBorder(BorderFactory.createLineBorder(new Color(100, 55, 55), 2));
            button.setBackground(Color.WHITE);
            button.addActionListener(e -> player.setCurrentTool(tool));
            add(button);
        }


    }
    public void updateStats() {
        removeAll();
        addStats();
        revalidate();
        repaint();
    }

}
