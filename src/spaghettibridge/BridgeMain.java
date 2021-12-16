package spaghettibridge;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 *
 * @author Matheus Markies
 */
public class BridgeMain {

    static BridgeGraphics bridgeGraphics = new BridgeGraphics();
    static BridgeDesigner bridgeDesigner = new BridgeDesigner(bridgeGraphics);

    public static void main(String[] args){
        bridgeDesigner.setVisible(true);
        Canvas canvas = configCanvas();
        bridgeDesigner.add(canvas);
        
//        while(true){
//            canvas = configCanvas();
//            
//            bridgeDesigner.repaint();
//            try {
//                Thread.sleep(10);
//            } catch (InterruptedException ex) {
//                Logger.getLogger(BridgeMain.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
    }

    public static Canvas configCanvas() {
        Canvas canvas = bridgeGraphics;
//        if (bridgeDesigner.getCanvas().getWidth() > 0 && bridgeDesigner.getCanvas().getHeight() > 0) {
//            canvas.setSize(bridgeDesigner.getCanvas().getWidth(), bridgeDesigner.getCanvas().getHeight());
//            canvas.setBackground(bridgeDesigner.getCanvas().getBackground());
//        } else {
            canvas.setSize(getBridgeDesigner().getSize().width, getBridgeDesigner().getSize().height);
        //}
        
        canvas.setBackground(Color.WHITE);
        return canvas;
    }

    public static BridgeDesigner getBridgeDesigner() {
        return bridgeDesigner;
    }

}
