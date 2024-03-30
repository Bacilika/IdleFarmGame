package MainGame;

import GameComponents.FarmComponent;
import GameComponents.Shop;
import GameComponents.StatComponent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class GameFrame extends JFrame implements MouseListener, MouseMotionListener {
    private final FarmArea farmArea;
    private final FarmComponent farmComponent;
    private final Shop shop;
    private final StatComponent statComponent;
    private final Player player;
    private final MouseHandler mouseHandler;
    private MouseEvent currentMouseEvent;

    public GameFrame() {
        this.setTitle("Idle farm");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        farmArea = new FarmArea(10, 20);
        farmComponent = new FarmComponent(farmArea);
        player = farmArea.getPlayer();
        statComponent = new StatComponent(player);
        shop = new Shop();
        mouseHandler = new MouseHandler(farmArea, shop,farmComponent);



        this.setLocationRelativeTo(null);
        this.setVisible(true);

        this.setPreferredSize(new Dimension(farmComponent.getPreferredSize().width + shop.getPreferredSize().width+100,
                farmComponent.getPreferredSize().height + shop.getPreferredSize().height + 100));

        this.add(farmComponent, BorderLayout.WEST);
        this.add(shop, BorderLayout.CENTER);
        this.add(statComponent, BorderLayout.NORTH);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.pack();
        Timer timer = new Timer(10, _ -> run());
        timer.start();
        farmArea.setOffset(61);
    }
    public void run() {
            farmArea.tick();
            statComponent.updateStats();
            farmArea.setOffset(61);
            mouseHandler.onLeftMouseDown(currentMouseEvent);
            repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        mouseHandler.mousePressed(e);
    }
    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mouseHandler.mouseReleased(e);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
        farmComponent.mouseLocation = null;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        currentMouseEvent = e;
        mouseHandler.mouseDragged(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        currentMouseEvent = e;
        if(farmComponent.contains(e.getPoint())){
            if(shop.getActiveBlock() != null) {
                farmComponent.mouseLocation = farmArea.pixelToBlock(e.getX(), e.getY());
            }
            if(player.getCurrentTool() != null){
                setCursor(FarmComponent.CURSOR_TOOL.get(player.getCurrentTool()));
            }
            else {
                setCursor(Cursor.getDefaultCursor());
            }
        }
    }
}
