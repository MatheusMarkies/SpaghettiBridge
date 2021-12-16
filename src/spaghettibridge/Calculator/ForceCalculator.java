/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spaghettibridge.Calculator;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import spaghettibridge.BridgeGraphics;
import spaghettibridge.Components.BridgeBar;
import spaghettibridge.Components.Node;
import spaghettibridge.Utilities.Matrix;
import spaghettibridge.Utilities.Vector2D;

/**
 *
 * @author Matheus Markies
 */
public class ForceCalculator {

    public static String[] getForcePerNode(Node node, String[] reactionResults, String nodeName) {
        String[] equation = new String[]{"", ""};

        for (Map.Entry<String, BridgeBar> bar : node.getBars().entrySet()) {

            String NodeA = bar.getValue().getNodes()[0];
            String NodeB = bar.getValue().getNodes()[1];

            if (NodeA != nodeName) {
                NodeA = bar.getValue().getNodes()[1];
                NodeB = bar.getValue().getNodes()[0];
            }

            String forceX = bar.getKey() + " cos(" + NodeA + "," + NodeB + ")";
            if (equation[0].length() > 0) {
                equation[0] += " + ";
            }
            equation[0] += forceX;

            String forceY = bar.getKey() + " sin(" + NodeA + "," + NodeB + ")";
            if (equation[1].length() > 0) {
                equation[1] += " + ";
            }
            equation[1] += forceY;
        }

        equation[0] += " +" + reactionResults[0];
        equation[1] += " +" + reactionResults[1];

        if (node.isCargoReceiver()) {
            equation[1] += " = P / 2";
        } else {
            equation[1] += " = 0";
        }
        equation[0] += " = 0";

        return equation;
    }

    public static String[] getForcePerNodeWithAngles(Node node, String[] reactionResults, String nodeName, TreeMap<String, Node> Nodes) {
        String[] equation = new String[]{"", ""};

        for (Map.Entry<String, BridgeBar> bar : node.getBars().entrySet()) {

            String NodeA = bar.getValue().getNodes()[0];
            String NodeB = bar.getValue().getNodes()[1];

            if (NodeA != nodeName) {
                NodeA = bar.getValue().getNodes()[1];
                NodeB = bar.getValue().getNodes()[0];
            }

            String forceX = bar.getKey() + " cos(" + (Nodes.get(NodeA).getPosition().x() - Nodes.get(NodeB).getPosition().x()) / Vector2D.distance(Nodes.get(NodeA).getPosition(), Nodes.get(NodeB).getPosition()) + ")";
            if (equation[0].length() > 0) {
                equation[0] += " + ";
            }
            equation[0] += forceX;

            String forceY = bar.getKey() + " sin(" + (Nodes.get(NodeA).getPosition().y() - Nodes.get(NodeB).getPosition().y()) / Vector2D.distance(Nodes.get(NodeA).getPosition(), Nodes.get(NodeB).getPosition()) + ")";
            if (equation[1].length() > 0) {
                equation[1] += " + ";
            }
            equation[1] += forceY;
        }

        equation[0] += " +" + reactionResults[0];
        equation[1] += " +" + reactionResults[1];

        if (node.isCargoReceiver()) {
            equation[1] += " = P / 2";
        } else {
            equation[1] += " = 0";
        }
        equation[0] += " = 0";

        return equation;
    }

    String[] barTemplate;

    public static Map<String, double[][]> calculateMatrix(String[] equations, List<ReactionResults> reactionList, BridgeGraphics bridgeGraphics, double P) {
        double[][] F = new double[equations.length][1];
        double[][] C = new double[bridgeGraphics.getBars().size() + 3][equations.length];

        Map<String, Integer> barTemplate = new HashMap<>();

        Map<String, double[][]> Matrixs = new HashMap<>();

        for (int i = 0; i < equations.length; i++) {
            String eq = equations[i].replace(" ", "");
            String[] equationParameter = eq.split("\\+");

            int j = 0;
            for (Map.Entry<String, BridgeBar> bar : bridgeGraphics.getBars().entrySet()) {
                for (String string : equationParameter) {
                    if (string.indexOf(bar.getKey()) != -1) {
                        barTemplate.put(bar.getKey(), j);
                        if (string.indexOf("cos") != -1) {

                            String cos = "";
                            if (string.split("\\-").length > 1) {
                                cos += "-";
                            }
                            for (int h = 0; h < string.replaceAll("\\D+", "").length(); h++) {

                                if (h == 0) {
                                    cos += string.replaceAll("\\D+", "").charAt(0) + ".";
                                } else {
                                    cos += string.replaceAll("\\D+", "").charAt(h);
                                }
                            }
                            C[i][j] = Double.valueOf(cos);

                        }
                        if (string.indexOf("sin") != -1) {

                            String sin = "";
                            if (string.split("\\-").length > 1) {
                                sin += "-";
                            }
                            for (int h = 0; h < string.replaceAll("\\D+", "").length(); h++) {

                                if (h == 0) {
                                    sin += string.replaceAll("\\D+", "").charAt(0) + ".";
                                } else {
                                    sin += string.replaceAll("\\D+", "").charAt(h);
                                }
                            }
                            C[i][j] = Double.valueOf(sin);

                        }
                    } else {
                        //C[i][j] = 0;
                    }
                }
                j++;
            }

            for (int k = 0; k < reactionList.size(); k++) {
                for (String string : equationParameter) {
                    if (k == 0) {
                        if (string.indexOf(reactionList.get(k).getNodeKey() + "X") != -1) {
                            C[i][bridgeGraphics.getBars().size() + k] = -1;
                            break;
                        }
                    }
                    if (k > 0) {
                        if (string.indexOf(reactionList.get(k).getNodeKey() + "Y") != -1) {
                            C[i][bridgeGraphics.getBars().size() + k] = -1;
                            break;
                        }
                    }
                }
            }

        }

        for (int i = 0; i < equations.length; i++) {
            String eq = equations[i].split("=")[1].replace(" ", "");
            try {
                F[i][0] = Integer.parseInt(eq);
            } catch (Exception e) {
                F[i][0] = P / 2;
            }
        }

        double[][] N = Matrix.multiply(Matrix.invert(C), F);

        Matrixs.put("F", F);
        Matrixs.put("N", N);
        Matrixs.put("C", C);

        setBarColor(N, bridgeGraphics, barTemplate);
        int[] wires = numberWiresPerBar(N, barTemplate, bridgeGraphics);

        return Matrixs;
    }

    public static void setBarColor(double[][] forceMatrix, BridgeGraphics bridgeGraphics, Map<String, Integer> barTemplate) {
        double forceAverage = 0;
        for (int i = 0; i < forceMatrix.length; i++) {
            forceAverage += forceMatrix[i][0];
        }
        forceAverage /= forceMatrix.length;

        System.out.println("forceAverage " + forceAverage);
        for (Map.Entry<String, Integer> barTemp : barTemplate.entrySet()) {
            for (Map.Entry<String, BridgeBar> bar : bridgeGraphics.getBars().entrySet()) {
                if (bar.getKey() == barTemp.getKey()) {

                    double ColorMultiply = Math.abs(forceMatrix[barTemp.getValue()][0] / forceAverage);

                    if (ColorMultiply > 1.5) {
                        bar.getValue().setColor(Color.RED);
                    } else if (ColorMultiply > 1 && ColorMultiply < 1.5) {
                        bar.getValue().setColor(Color.YELLOW);
                    } else {
                        bar.getValue().setColor(Color.GREEN);
                    }
                    bar.getValue().setWires((int) forceMatrix[barTemp.getValue()][0]);
                }
            }
        }

        bridgeGraphics.repaint();
    }

    public static int[] numberWiresPerBar(double[][] forceMatrix, Map<String, Integer> barTemplate, BridgeGraphics bridgeGraphics) {
        int[] WiresPerBar = new int[forceMatrix.length];

        System.out.println("");
        System.out.println("-------------------------------------------------");
        System.out.println("Numero de fios seguindos as constantes de macarrao Barilla n7!");

        //Barilla n7
        double Tr = 42.5;
        long Me = 3600 * 1000000;
        double l = Math.sqrt(0.02545 / Math.PI) * 2;
        double Cs = 4;
        //Barilla n7

        for (Map.Entry<String, Integer> barTemp : barTemplate.entrySet()) {
            for (Map.Entry<String, BridgeBar> bar : bridgeGraphics.getBars().entrySet()) {
                if (bar.getKey() == barTemp.getKey()) {
                    if (forceMatrix[barTemp.getValue()][0] > 0)//tração
                    {
                        WiresPerBar[barTemp.getValue()] = (int) Math.round((forceMatrix[barTemp.getValue()][0] * Cs) / Tr);
                    } else {//flambagem
                        double barSize = Vector2D.distance(bar.getValue().getA(), bar.getValue().getB());
                        WiresPerBar[barTemp.getValue()] = (int) Math.round((8 * Math.sqrt(Math.abs(forceMatrix[barTemp.getValue()][0]) * Cs * barSize * barSize)) / ((Math.PI * Math.PI * Math.PI) * Me * l * l * l * l));
                    }
                    if (WiresPerBar[barTemp.getValue()] == 0) {
                        WiresPerBar[barTemp.getValue()] = 3;
                    }

                    System.out.println("");
                    System.out.println("Numero de fios (" + bar.getKey() + "): " + WiresPerBar[barTemp.getValue()] + " | Forca na barra: " + Math.round(forceMatrix[barTemp.getValue()][0] * 100) / 100 + "N");

                    bar.getValue().setWires(WiresPerBar[barTemp.getValue()]);
                }
            }
        }
        System.out.println("-------------------------------------------------");
        bridgeGraphics.repaint();
        return WiresPerBar;
    }

}
