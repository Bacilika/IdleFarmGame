package GameComponents;

import MainGame.Player;
import Tools.Tool;

import javax.swing.*;
import java.awt.*;
import java.util.EnumMap;

public class StatComponent extends JComponent {
    private final Player player;

    public static final EnumMap<Tool, ImageIcon> TOOL_TEXTURES = new EnumMap<>(Tool.class);


    public StatComponent(Player player) {
        setLayout(new GridLayout(1,5));
        setBorder(BorderFactory.createLineBorder(new Color(100, 55, 55), 5));
        this.player = player;
        addStats();
    }
    private void addStats() {
        //noinspection preview
        add(new JLabel(STR."Money: \{player.getMoney()}"));
        for(Tool tool: Tool.values()) {
            TOOL_TEXTURES.put(tool, new ImageIcon(tool.getPath()));
            JButton button = new JButton(new ImageIcon(tool.getPath()));
            button.setMargin(new Insets(0, 0, 0, 0));
            button.setBorder(BorderFactory.createLineBorder(new Color(100, 55, 55), 2));
            button.setBackground(Color.WHITE);
            button.addActionListener(_ -> player.setCurrentTool(tool));
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
