/*
Author: Davis Haden
Date Last Edited: 12/8/2021
Desc: Main runner class and graphic class for the program. Cars in both directions stop when their respective light changes
to the color red.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

class CarAnimation extends JPanel implements ActionListener {
    //Creating the traffic light objects
    public static TrafficLight main1 = new TrafficLight(500/2,190, false);
    public static TrafficLight main2 = new TrafficLight(500/2,200, true);
    CarAnimation() {
        setSize(new Dimension(60, 60));
        //Creating timers for different actions (Refreshing GUI, Car Creation, and Light changes)
        new Timer(10, this).start();
        new Timer(800, new CarListener()).start();
        new Timer(2000, new LightListener()).start();
    }
    //Creates cars
    static class CarListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                new Car(250,-10, "down");
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            try {
                new Car(-10,200, "right");
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }

        }
    }
    //Changes the lights. Should never be the same except at the beginning.
    static class LightListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            main1.flip();
            main2.flip();
        }
    }
    //Refreshing the cars positions and checking for collisions
    public void actionPerformed(ActionEvent arg0) {
        for (Car c : Car.cars) {
                c.update();
                //System.out.println(c.checkBoundaries());
                c.checkBoundaries();
        }
        repaint();
    }
    //Painting the objects onto the GUI
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        for (Car c : Car.cars) {
            if(c.x < 500 && c.y < 400){
                try {
                    c.draw(g2d);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else{
                //Removes cars from the simulation if they pass the boundary. To reduce lag
                Car.cars.remove(c);
                Car.carsx.remove(c);
                Car.carsy.remove(c);
            }
        }
        main1.draw(g2d);
        main2.draw(g2d);
        g.setColor(Color.black);
        //Road lines
        g.drawLine(0,180,240,180);
        g.drawLine(0,220,240,220);
        g.drawLine(270,180,500,180);
        g.drawLine(270,220,500,220);
        g.drawLine(240,0,240,180);
        g.drawLine(270,0,270,180);
        g.drawLine(240,220,240,400);
        g.drawLine(270,220,270,400);
    }
    //Creating the GUI
    public static void main(String[] args) {
        int width, height;
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        width = 500;
        height = 400;
        frame.setSize(width,height);
        CarAnimation cars = new CarAnimation();
        cars.setLocation(100,100);
        frame.add(cars);
        frame.setVisible(true);
    }
}
