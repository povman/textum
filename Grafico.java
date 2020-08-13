package textum;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JLabel;

/**
 *
 * @author Fabio Moraes  
 * fabiomoraes@sisdigital.com.br
 * https://fabiomoraes.sisdigital.com.br
 * https://www.linkedin.com/in/fabio-moraes-63462915/
 * https://github.com/povman
 * https://www.hackerrank.com/fabiomoraes
 */
public class Grafico extends JLabel{
    int x1;
    int y1;
    int x2;
    int y2;
    
    @Override
    public void paint(Graphics g) 
    { 
       
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.YELLOW);
        g2d.setFont(new Font("Arial", Font.BOLD , 12));
        g2d.setStroke(new BasicStroke(25));
        g2d.drawLine(x1, y1 - 30, x2, y2 -30); 
      
    }
}
