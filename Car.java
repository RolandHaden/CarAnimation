/*
Author: Davis Haden
Date Last Edited: 12/8/2021
Desc: Car object class. Handles the different LinkedLists and checks for potential collisions between cars.
 */
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

class Car {
    //Linked lists for different directions
    public static final LinkedList<Car> carsx = new LinkedList<>();
    public static final LinkedList<Car> carsy = new LinkedList<>();
    public static final LinkedList<Car> cars = new LinkedList<>();
    //Different double values. Ogx and ogy sustain the original speeds of the cars.
    public double  x, y, speedx, speedy, ogx,ogy;
    //Determines which direction the car is going in
    public String direction;
    //Identifies which car object is being observed in its LinkedList
    int id;
    //Creating images for the cars
    BufferedImage bufferedImagey = ImageIO.read(new File("car1.png"));
    Image cary = bufferedImagey.getScaledInstance(10, 20, Image.SCALE_DEFAULT);
    BufferedImage bufferedImagex = ImageIO.read(new File("car2.png"));
    Image carx = bufferedImagex.getScaledInstance(20, 20, Image.SCALE_DEFAULT);
    //Main Car Constructor Class
    public Car(double x, double y, String direction) throws IOException {
        this.x = x;
        this.y = y;
        int sx = 0;
        int sy = 0;
        this.direction = direction;
        //Determines a speed for the car
        if(direction.equalsIgnoreCase("right")){
            sx = (int) (Math.random()*3);
        }else if(direction.equalsIgnoreCase("down")){
            sy = (int) (Math.random()*3);
        }
        this.speedx = sx;
        this.speedy = sy;
        this.ogx = sx;
        this.ogy = sy;
        //Adding car to its respective class then to the main class.
        if(sx > 0){
            carsx.add(this);
            id = carsx.size();
        }else if(sy > 0){
            carsy.add(this);
            id = carsy.size();
        }
        cars.add(this);
    }
    //Updating the position of the car
    public void update() {
        x += speedx;
        y += speedy;
    }
    //Checks for potential collisions
    public void checkBoundaries(){
        if(this.ogx > 0) {
            //For cars going to the right -- Only looks at the car in front of it
            for (Car c : carsx) {
                if ((c.x - 40 < this.x && c.x - 10 > this.x)) {
                    this.speedx = 0;
                    break;
                }else{
                    this.speedx = ogx;

                }
                if (this.x > 230 && this.x < 240 && !CarAnimation.main2.switchLight) {
                    this.speedx = 0;
                }
            }

        }else {
            //For cars going down -- Only looks at the car below it
            for (Car c : carsy) {
                if ((c.y - 30 < this.y && c.y - 10 > this.y)) {
                    this.speedy = 0;
                    break;
                }else{
                    this.speedy = ogy;
                }
                if (this.y > 170 && this.y < 180 && !CarAnimation.main1.switchLight) {
                    this.speedy = 0;
                }
            }

        }
    }
    //Drawing the images of the cars -- different images for the direction the car is going in
    public void draw(Graphics2D g2d) throws IOException {
        if(direction.equalsIgnoreCase("down")) {
            g2d.drawImage(cary, (int)x, (int)y-10, null);
        }else if(direction.equalsIgnoreCase("right")){
            g2d.drawImage(carx, (int)x-10, (int)y-10, null);
        }

    }
}


