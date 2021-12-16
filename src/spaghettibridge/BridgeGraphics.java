/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spaghettibridge;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.sql.Struct;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import spaghettibridge.Calculator.ReactionResults;
import spaghettibridge.Components.BridgeBar;
import spaghettibridge.Components.Node;
import spaghettibridge.Utilities.Vector2D;

/**
 *
 * @author Matheus Markies
 */
public class BridgeGraphics extends Canvas {

    private TreeMap<String, Node> Nodes = new TreeMap<>();
    private TreeMap<String, BridgeBar> Bars = new TreeMap<>();

    public TreeMap<String, Node> getNodes() {
        return Nodes;
    }

    public TreeMap<String, BridgeBar> getBars() {
        return Bars;
    }

    public void setNodes(TreeMap<String, Node> Nodes) {
        this.Nodes = Nodes;
    }

    public void setBars(TreeMap<String, BridgeBar> Bars) {
        this.Bars = Bars;
    }

    public String[] getNodesKeys() {
        String[] keys = new String[Nodes.size()];
        int i = 0;
        for (String key : Nodes.keySet()) {
            keys[i] = key;
            i++;
        }
        return keys;
    }

    public void paint(Graphics g) {
        for (Map.Entry<String, Node> node : Nodes.entrySet()) {
            g.setColor(Color.BLACK);
            g.drawString(node.getKey(), (int) node.getValue().getPosition().x(), (int) node.getValue().getPosition().y() - 5);
            if (!node.getValue().isCargoReceiver()) {
                g.setColor(Color.RED);
            } else {
                g.setColor(Color.GREEN);
            }
            g.drawOval((int) node.getValue().getPosition().x(), (int) node.getValue().getPosition().y(), 2, 2);
        }
        for (Map.Entry<String, BridgeBar> bar : Bars.entrySet()) {
            g.setColor(Color.BLUE);
            
            String barName = bar.getKey();if(bar.getValue().getWires() > 0)
                barName += " "+bar.getValue().getWires();
            
            
            g.drawString(barName, (int) Vector2D.getCenter(bar.getValue().getA(), bar.getValue().getB()).x(),
                    (int) Vector2D.getCenter(bar.getValue().getA(), bar.getValue().getB()).y());
            g.setColor(bar.getValue().getColor());
            g.drawLine((int) bar.getValue().getA().x(), (int) bar.getValue().getA().y(), (int) bar.getValue().getB().x(), (int) bar.getValue().getB().y());
        
        }

        for (ReactionResults reactionResults : ReactionResults.calculateResultPoints(Nodes)) {
            Vector2D position = reactionResults.getReactionNode().getPosition();
            g.setColor(Color.MAGENTA);
            if (reactionResults.getForceMode() == ReactionResults.ForceMode.Y) {
                Vector2D center = Vector2D.getCenter(new Vector2D((int) position.x(), (int) position.y()), new Vector2D((int) position.x(), (int) position.y() - 25));
                g.drawString(reactionResults.getNodeKey() + "Y", (int) center.x(), (int) center.y());
                g.drawLine((int) position.x(), (int) position.y(), (int) position.x(), (int) position.y() - 25);
            } else {
                Vector2D center = Vector2D.getCenter(new Vector2D((int) position.x(), (int) position.y()), new Vector2D((int) position.x() - 25, (int) position.y()));
                g.drawString(reactionResults.getNodeKey() + "X", (int) center.x(), (int) center.y());
                g.drawLine((int) position.x(), (int) position.y(), (int) position.x() - 25, (int) position.y());
            }
        }

    }

}
