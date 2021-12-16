/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spaghettibridge.Components;

import java.util.HashMap;
import java.util.Map;
import spaghettibridge.Utilities.Vector2D;

/**
 *
 * @author Matheus Markies
 */
public class Node {
  
     private Map<String,BridgeBar> Bars = new HashMap<>();
     private Vector2D Position;
     private boolean CargoReceiver;
     
     private String[] ReactionResults;

    public Node(Vector2D Position, boolean CargoReceiver) {
        this.Position = Position;
        this.CargoReceiver = CargoReceiver;
    }

    public boolean isCargoReceiver() {
        return CargoReceiver;
    }

    public void setCargoReceiver(boolean CargoReceiver) {
        this.CargoReceiver = CargoReceiver;
    }

    public Map<String, BridgeBar> getBars() {
        return Bars;
    }

    public void setBars(Map<String, BridgeBar> Bars) {
        this.Bars = Bars;
    }

    public Vector2D getPosition() {
        return Position;
    }

    public void setPosition(Vector2D Position) {
        this.Position = Position;
    }

    public String[] getReactionResults() {
        return ReactionResults;
    }

    public void setReactionResults(String[] ReactionResults) {
        this.ReactionResults = ReactionResults;
    }

}
