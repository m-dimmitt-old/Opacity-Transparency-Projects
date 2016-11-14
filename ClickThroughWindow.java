import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagLayout;
import java.awt.Rectangle;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
public class ClickThroughWindow {
    public static void main(String[] args) {
        LowerRightFrame r = new LowerRightFrame();
        r.display();
    	new ClickThroughWindow();
    }
    public ClickThroughWindow() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                    ex.printStackTrace();
                }
                JFrame frame = new JFrame("Testing");
                frame.setUndecorated(true);
                frame.setBackground(new Color(0, 0, 0, 0));
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.add(new TestPane());
                frame.setAlwaysOnTop(true);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }
    public class TestPane extends JPanel {
        public TestPane() {

              setSize(800, 600);		//photo
              setVisible(true);			//photo
              add(new JLabel(new ImageIcon("puppy2.png"))); 
               
        	
        	
            setOpaque(false);
            setLayout(new GridBagLayout());
//            add(new JLabel("Hello"));
//            setVisible(true);
//            add(new JLabel(new ImageIcon("puppy2.png")));
            
        }
        @Override
        public Dimension getPreferredSize() {
            return new Dimension(1920, 1200);
        }
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setColor(getBackground());//deletion doesnt seem to make dif
//          g2d.setComposite(AlphaComposite.SrcOver.derive(0.5f));
//          g2d.fillRect(0, 0, getWidth(), getHeight());
            g2d.setColor(Color.WHITE);//influences border of outline color
            g2d.setStroke(new BasicStroke(100));
            g2d.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
            System.out.println(getWidth());//1920
            System.out.println(getHeight());//1200
            g2d.dispose();
        }

    }

}


 class LowerRightFrame {

    void display() {
        JFrame f = new JFrame("LowerRightFrame");
        f.add(new JPanel() {

            @Override // placeholder for actual content
            public Dimension getPreferredSize() {
                return new Dimension(320, 240);
            }

        });
        f.pack();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice defaultScreen = ge.getDefaultScreenDevice();
        Rectangle rect = defaultScreen.getDefaultConfiguration().getBounds();
        int x = (int) rect.getMaxX() - f.getWidth();
        int y = (int) rect.getMaxY() - f.getHeight();

        System.out.println(rect.getMaxY());//1800
        System.out.println(rect.getMaxX()); //3200
        System.out.println(f.getWidth());//352
        System.out.println(f.getHeight());//328
        f.setLocation(x, y);
        f.setVisible(true);
        
    }

//positioning  is centered
    //I need to look at centered rectangle  and draw something in its lower right corner.
}
