/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spaghettibridge.Components;

import java.awt.Color;
import static javafx.scene.paint.Color.color;
import spaghettibridge.Utilities.Vector2D;

/**
 *
 * @author Matheus Markies
 */
public class BridgeBar {
    private Vector2D A;
    private Vector2D B;
    private String[] Nodes;
    private Color color = new Color(0,0,0);

    private int Wires = 0;
    
    public BridgeBar(Vector2D A, Vector2D B,String[] Nodes) {
        this.A = A;
        this.B = B;
        this.Nodes = Nodes;
        color = new Color(0,0,0);
    }

    public Vector2D getA() {
        return A;
    }

    public void setA(Vector2D A) {
        this.A = A;
    }

    public Vector2D getB() {
        return B;
    }

    public void setB(Vector2D B) {
        this.B = B;
    }

    public String[] getNodes() {
        return Nodes;
    }

    public void setNodes(String[] Nodes) {
        this.Nodes = Nodes;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getWires() {
        return Wires;
    }

    public void setWires(int Wires) {
        this.Wires = Wires;
    }
    
    @Override
    public String toString() {
        return "BridgeBar{" + "A = " + A.toString() + ", B = " + B.toString() + '}';
    }   
    
}
