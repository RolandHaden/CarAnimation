/*
Author: Davis Haden
Date Last Edited: 12/8/2021
Desc: Traffic light class. Switches the color of the light.
 */
import java.awt.*;

public class TrafficLight {
    public int x,y;
    boolean switchLight;
    Color lightColor = Color.red;
    public TrafficLight(int x, int y, boolean initialLight){
        this.x = x;
        this.y = y;
        switchLight = initialLight;
    }
    public void flip(){
        switchLight = !switchLight;
        if(!switchLight){
            lightColor = Color.red;
        }else{
            lightColor = Color.green;
        }
    }
    public void draw(Graphics2D g2d) {
        g2d.setColor(lightColor);
        g2d.fillOval(x, y, 10,10);
    }
}
