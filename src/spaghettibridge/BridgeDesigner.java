/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spaghettibridge;

import com.sun.scenario.effect.Brightpass;
import java.awt.Canvas;
import java.util.Map;
import spaghettibridge.AutoBridges.StandardBridges;
import spaghettibridge.Calculator.ForceCalculator;
import spaghettibridge.Calculator.ReactionResults;
import spaghettibridge.Components.Node;
import spaghettibridge.OtherInterfaces.AddBarInterface;
import spaghettibridge.OtherInterfaces.AddNodeInterface;
import spaghettibridge.OtherInterfaces.CargoSet;
import spaghettibridge.OtherInterfaces.StandardBridgesComponents;

/**
 *
 * @author Matheus Markies
 */
public class BridgeDesigner extends javax.swing.JFrame {

    BridgeGraphics bridgeGraphics;

    /**
     * Creates new form BridgeDesigner
     */
    public BridgeDesigner(BridgeGraphics bridgeGraphics) {
        initComponents();
        this.bridgeGraphics = bridgeGraphics;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        AddNode = new javax.swing.JButton();
        AddBar = new javax.swing.JButton();
        WarrenBridge = new javax.swing.JButton();
        CalculateForces = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setAutoRequestFocus(false);
        setLocationByPlatform(true);
        setMaximumSize(new java.awt.Dimension(800, 600));

        AddNode.setText("Adicionar No");
        AddNode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddNodeActionPerformed(evt);
            }
        });

        AddBar.setText("Adicionar Barra");
        AddBar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddBarActionPerformed(evt);
            }
        });

        WarrenBridge.setText("Ferramentas");
        WarrenBridge.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                WarrenBridgeActionPerformed(evt);
            }
        });

        CalculateForces.setText("Calcular Forcas");
        CalculateForces.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CalculateForcesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(AddNode)
                .addGap(32, 32, 32)
                .addComponent(WarrenBridge)
                .addGap(38, 38, 38)
                .addComponent(CalculateForces)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                .addComponent(AddBar)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AddNode)
                    .addComponent(AddBar)
                    .addComponent(WarrenBridge)
                    .addComponent(CalculateForces)))
        );

        setSize(new java.awt.Dimension(559, 359));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void AddNodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddNodeActionPerformed
        AddNodeInterface addNodeInterface = new AddNodeInterface(bridgeGraphics);
        addNodeInterface.setVisible(true);
    }//GEN-LAST:event_AddNodeActionPerformed

    private void AddBarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddBarActionPerformed
        if (bridgeGraphics.getNodes().size() > 1) {
            AddBarInterface addBarInterface = new AddBarInterface(bridgeGraphics);
            addBarInterface.setVisible(true);
        }
    }//GEN-LAST:event_AddBarActionPerformed

    private void WarrenBridgeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_WarrenBridgeActionPerformed
StandardBridgesComponents bridgesComponents = new StandardBridgesComponents(bridgeGraphics);
bridgesComponents.setVisible(true);
    }//GEN-LAST:event_WarrenBridgeActionPerformed

    private void CalculateForcesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CalculateForcesActionPerformed
        CargoSet cargoSet = new CargoSet(bridgeGraphics);
        cargoSet.setVisible(true);
    }//GEN-LAST:event_CalculateForcesActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddBar;
    private javax.swing.JButton AddNode;
    private javax.swing.JButton CalculateForces;
    private javax.swing.JButton WarrenBridge;
    // End of variables declaration//GEN-END:variables

//    public Canvas getCanvas() {
//        return canvas;
//    }
}