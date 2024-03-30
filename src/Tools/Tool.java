package Tools;

public enum Tool {
    HOE("resources/Blocks/tool/hoe.png"),
    WATERING_CAN("resources/Blocks/tool/watering_can.png"),
    NONE("");
    private final String path;
    Tool(String path){
        this.path = path;
    }
    public String getPath(){
        return path;
    }
}
