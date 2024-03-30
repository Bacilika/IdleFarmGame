package MainGame;

import Tools.Tool;

public class Player {
    int money;

    private Tool currentTool = Tool.NONE;
    public Player() {
        money = 50;
    }
    public boolean buy(int cost) {
        if (money >= cost) {
            money -= cost;
            return true;
        }
        return false;
    }
    public void sell(int cost) {
        money += cost;
    }
    public int getMoney() {
        return money;
    }

    public void setCurrentTool(Tool tool) {
        currentTool = tool;
    }

    public Tool getCurrentTool() {
        return currentTool;
    }
}
