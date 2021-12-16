/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spaghettibridge.Calculator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import spaghettibridge.Components.Node;
import spaghettibridge.Utilities.Vector2D;

/**
 *
 * @author Matheus Markies
 */
public class ReactionResults {

    private Node ReactionNode;
    private String NodeKey;

    public enum ForceMode {
        X, Y
    }

    private ForceMode forceMode;

    public ReactionResults(String NodeKey, Node ReactionNode, ForceMode forceMode) {
        this.ReactionNode = ReactionNode;
        this.NodeKey = NodeKey;
        this.forceMode = forceMode;
    }

    public Node getReactionNode() {
        return ReactionNode;
    }

    public void setReactionNode(Node ReactionNode) {
        this.ReactionNode = ReactionNode;
    }

    public ForceMode getForceMode() {
        return forceMode;
    }

    public void setForceMode(ForceMode forceMode) {
        this.forceMode = forceMode;
    }

    public String getNodeKey() {
        return NodeKey;
    }

    public void setNodeKey(String NodeKey) {
        this.NodeKey = NodeKey;
    }

    public static List<ReactionResults> calculateResultPoints(Map<String, Node> nodes) {
        List<ReactionResults> results = new ArrayList<>();

        Node MaxX = new Node(new Vector2D(0, 0), true);
        Node MinX = new Node(new Vector2D(0, 0), true);

        String MaxXKey = "";
        String MinXKey = "";

        int i = 0;
        for (Map.Entry<String, Node> node : nodes.entrySet()) {
            if (i == 0) {
                MaxX = node.getValue();
                MinX = node.getValue();
                MaxXKey = node.getKey();
                MinXKey = node.getKey();
                i++;
            } else {
                if (node.getValue().getPosition().x() > MaxX.getPosition().x()) {
                    MaxX = node.getValue();
                    MaxXKey = node.getKey();
                }
                if (node.getValue().getPosition().x() < MinX.getPosition().x()) {
                    MinX = node.getValue();
                    MinXKey = node.getKey();
                }
            }
        }

        results.add(new ReactionResults(MinXKey, MinX, ForceMode.X));
        results.add(new ReactionResults(MinXKey, MinX, ForceMode.Y));
        results.add(new ReactionResults(MaxXKey, MaxX, ForceMode.Y));

        return results;
    }

    public static String[] getReactionResultsPerNode(Map<String, Node> nodes, Node node) {
        String[] results = new String[]{"", ""};

        List<ReactionResults> reactionResults = calculateResultPoints(nodes);
        for (ReactionResults results1 : reactionResults) {
            if (results1.getReactionNode() == node) {
                if (results1.getForceMode() == ReactionResults.ForceMode.X) {
                    results[0] = " -1" + results1.getNodeKey() + "X";
                } else {
                    results[1] = " -1" + results1.getNodeKey() + "Y";
                }
            }
        }

        return results;
    }

}
