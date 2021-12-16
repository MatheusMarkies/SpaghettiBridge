/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spaghettibridge.AutoBridges;

import java.util.Map;
import java.util.TreeMap;
import spaghettibridge.Components.BridgeBar;
import spaghettibridge.Components.Node;
import spaghettibridge.Utilities.Vector2D;

/**
 *
 * @author Matheus Markies
 */
public class StandardBridges {

    static String[] Letters = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

    public static class Warren {

        public static TreeMap<String, Node> Nodes(int width, int height) {
            TreeMap<String, Node> nodes = new TreeMap<>();

            nodes.put("A", new Node(new Vector2D(width / 2 - 150, height / 2), false));
            nodes.put("B", new Node(new Vector2D(width / 2 - 125, height / 2 - 50), false));
            nodes.put("C", new Node(new Vector2D(width / 2 - 100, height / 2), false));
            nodes.put("D", new Node(new Vector2D(width / 2 - 75, height / 2 - 50), false));
            nodes.put("E", new Node(new Vector2D(width / 2 - 50, height / 2), false));
            nodes.put("F", new Node(new Vector2D(width / 2 - 25, height / 2 - 50), false));
            nodes.put("G", new Node(new Vector2D(width / 2, height / 2), true));
            nodes.put("H", new Node(new Vector2D(width / 2 + 25, height / 2 - 50), false));
            nodes.put("I", new Node(new Vector2D(width / 2 + 50, height / 2), false));
            nodes.put("J", new Node(new Vector2D(width / 2 + 75, height / 2 - 50), false));
            nodes.put("K", new Node(new Vector2D(width / 2 + 100, height / 2), false));
            nodes.put("L", new Node(new Vector2D(width / 2 + 125, height / 2 - 50), false));
            nodes.put("M", new Node(new Vector2D(width / 2 + 150, height / 2), false));

            return nodes;
        }

        public static TreeMap<String, BridgeBar> Bars(TreeMap<String, Node> nodes) {
            TreeMap<String, BridgeBar> bars = new TreeMap<>();
            return autoConnect(nodes);
        }
    }

    public static class Simple {

        public static TreeMap<String, Node> Nodes(int width, int height) {
            TreeMap<String, Node> nodes = new TreeMap<>();

            nodes.put("A", new Node(new Vector2D(width / 2 + 120, height / 2), false));
            nodes.put("B", new Node(new Vector2D(width / 2 - 120, height / 2), false));
            nodes.put("C", new Node(new Vector2D(width / 2, height / 2), true));
            nodes.put("D", new Node(new Vector2D(width / 2 + 60, height / 2), false));
            nodes.put("E", new Node(new Vector2D(width / 2 - 60, height / 2), false));
            nodes.put("F", new Node(new Vector2D(width / 2, height / 2 - 60), false));
            nodes.put("G", new Node(new Vector2D(width / 2 - 60, height / 2 - 30), false));
            nodes.put("H", new Node(new Vector2D(width / 2 + 60, height / 2 - 30), false));

            return nodes;
        }

        public static TreeMap<String, BridgeBar> Bars(TreeMap<String, Node> nodes) {
            TreeMap<String, BridgeBar> bars = new TreeMap<>();

            BridgeBar BG = new BridgeBar(nodes.get("B").getPosition(), nodes.get("G").getPosition(),
                    new String[]{"B", "G"});

            nodes.get("B").getBars().put("BG", BG);
            nodes.get("G").getBars().put("BG", BG);

            BridgeBar GF = new BridgeBar(nodes.get("G").getPosition(), nodes.get("F").getPosition(),
                    new String[]{"G", "F"});

            nodes.get("G").getBars().put("GF", GF);
            nodes.get("F").getBars().put("GF", GF);

            BridgeBar FH = new BridgeBar(nodes.get("F").getPosition(), nodes.get("H").getPosition(),
                    new String[]{"F", "H"});

            nodes.get("F").getBars().put("FH", FH);
            nodes.get("H").getBars().put("FH", FH);

            BridgeBar HA = new BridgeBar(nodes.get("H").getPosition(), nodes.get("A").getPosition(),
                    new String[]{"H", "A"});

            nodes.get("H").getBars().put("HA", HA);
            nodes.get("A").getBars().put("HA", HA);

            BridgeBar DA = new BridgeBar(nodes.get("D").getPosition(), nodes.get("A").getPosition(),
                    new String[]{"D", "A"});

            nodes.get("D").getBars().put("DA", DA);
            nodes.get("A").getBars().put("DA", DA);

            BridgeBar CD = new BridgeBar(nodes.get("D").getPosition(), nodes.get("C").getPosition(),
                    new String[]{"D", "C"});

            nodes.get("C").getBars().put("CD", CD);
            nodes.get("D").getBars().put("CD", CD);

            BridgeBar CE = new BridgeBar(nodes.get("E").getPosition(), nodes.get("C").getPosition(),
                    new String[]{"E", "C"});

            nodes.get("C").getBars().put("CE", CE);
            nodes.get("E").getBars().put("CE", CE);

            BridgeBar EB = new BridgeBar(nodes.get("B").getPosition(), nodes.get("E").getPosition(),
                    new String[]{"E", "B"});

            nodes.get("B").getBars().put("EB", EB);
            nodes.get("E").getBars().put("EB", EB);

            BridgeBar GE = new BridgeBar(nodes.get("E").getPosition(), nodes.get("G").getPosition(),
                    new String[]{"E", "G"});

            nodes.get("C").getBars().put("GE", GE);
            nodes.get("E").getBars().put("GE", GE);

            BridgeBar HD = new BridgeBar(nodes.get("H").getPosition(), nodes.get("D").getPosition(),
                    new String[]{"H", "D"});

            nodes.get("H").getBars().put("HD", HD);
            nodes.get("D").getBars().put("HD", HD);

            BridgeBar FC = new BridgeBar(nodes.get("F").getPosition(), nodes.get("C").getPosition(),
                    new String[]{"F", "C"});

            nodes.get("F").getBars().put("FC", FC);
            nodes.get("C").getBars().put("FC", FC);

            BridgeBar GC = new BridgeBar(nodes.get("G").getPosition(), nodes.get("C").getPosition(),
                    new String[]{"G", "C"});

            nodes.get("C").getBars().put("GC", GC);
            nodes.get("G").getBars().put("GC", GC);

            BridgeBar HC = new BridgeBar(nodes.get("H").getPosition(), nodes.get("C").getPosition(),
                    new String[]{"H", "C"});

            nodes.get("H").getBars().put("HC", HC);
            nodes.get("C").getBars().put("HC", HC);

            bars.put("BG", BG);
            bars.put("GF", GF);
            bars.put("FH", FH);
            bars.put("HA", HA);
            bars.put("DA", DA);
            bars.put("CD", CD);
            bars.put("CE", CE);
            bars.put("EB", EB);
            bars.put("GE", GE);
            bars.put("HD", HD);
            bars.put("FC", FC);
            bars.put("GC", GC);
            bars.put("HC", HC);

            return bars;
        }
    }

    public static TreeMap<String, BridgeBar> autoConnect(TreeMap<String, Node> nodes) {
        TreeMap<String, BridgeBar> bars = new TreeMap<>();

        int g = 0;
        for (Map.Entry<String, Node> node : nodes.entrySet()) {
            int h = 0;
            for (Map.Entry<String, Node> node2 : nodes.entrySet()) {
                if (h > g) {
                    if (node2.getValue().getPosition().y() == node.getValue().getPosition().y()) {
                        BridgeBar bar = new BridgeBar(node.getValue().getPosition(), node2.getValue().getPosition(),
                                new String[]{node.getKey(), node2.getKey()});

                        node2.getValue().getBars().put(node.getKey() + node2.getKey(), bar);
                        node.getValue().getBars().put(node.getKey() + node2.getKey(), bar);

                        bars.put(node.getKey() + node2.getKey(), bar);
                        break;
                    }
                }
                h++;
            }
            h = 0;
            for (Map.Entry<String, Node> node2 : nodes.entrySet()) {
                if (h > g) {
                    if (node2.getValue().getPosition().y() != node.getValue().getPosition().y()) {
                        BridgeBar bar = new BridgeBar(node.getValue().getPosition(), node2.getValue().getPosition(),
                                new String[]{node.getKey(), node2.getKey()});

                        node2.getValue().getBars().put(node.getKey() + node2.getKey(), bar);
                        node.getValue().getBars().put(node.getKey() + node2.getKey(), bar);

                        bars.put(node.getKey() + node2.getKey(), bar);
                        break;
                    }
                }
                h++;
            }

            g++;
        }
        System.out.println(bars.size());
        return bars;
    }

}
