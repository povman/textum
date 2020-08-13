package textum;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;


/**
 *
 * @author Fabio Moraes  
 * fabiomoraes@sisdigital.com.br
 * https://fabiomoraes.sisdigital.com.br
 * https://www.linkedin.com/in/fabio-moraes-63462915/
 * https://github.com/povman
 * https://www.hackerrank.com/fabiomoraes
 */

public class Textum {

    /**
     * @param args the command line arguments
     */ 
    
    static int mousex = 0;
    static int mousey = 0;
    static int mousex1 = 0;
    static int mousey1 = 0;
    static int qtde = 0;
    
    static JFrame janela01 = new JFrame("Textum - White Board v 0.1");

    public static void Salvar(){
        Robot robo = null;
    Dimension screenSize = janela01.getSize();//Toolkit.getDefaultToolkit().getScreenSize();
                                 Rectangle screenRectangle = new Rectangle(screenSize);
                                 try {
                                     robo = new Robot();
                                 } catch (AWTException ex) {
                                     Logger.getLogger(Textum.class.getName()).log(Level.SEVERE, null, ex);
                                 }
                                 BufferedImage image =  robo.createScreenCapture(screenRectangle);
                                 
                             JFileChooser fileChooser = new JFileChooser();
                             fileChooser.setDialogTitle("Specify a file to save");  
                             fileChooser.setFileFilter(new FileNameExtensionFilter("jpg file","jpg"));
                             int userSelection = fileChooser.showSaveDialog(janela01);
                             if (userSelection == JFileChooser.APPROVE_OPTION) {
                                 File fileToSave = fileChooser.getSelectedFile();
                                 String filename = fileChooser.getSelectedFile().toString();
                                                                 
                                 try {
                                     if(!filename.endsWith("*.jpg")){
                                         filename +=".jpg";
                                         fileToSave = new File(filename);
                                     }
                                     ImageIO.write(image, "jpg", fileToSave  );
                                 } catch (IOException ex) {
                                     Logger.getLogger(Textum.class.getName()).log(Level.SEVERE, null, ex);
                                 }
                                 
                             }
}
        
    public static void main(String[] args) {
                     
        janela01.setBounds(0,0,800,600);
        janela01.getContentPane().setBackground(Color.white);       
        janela01.setDefaultCloseOperation(0);       
 
        janela01.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getButton() == 1 ){
               mousex = e.getX();
               mousey = e.getY();
               JTextField texto = new JTextField("");
               texto.setFont(new Font("Arial", Font.BOLD , 15));
               texto.setBorder(null);
               texto.setBounds(mousex, mousey -30, 200, 30);
               texto.setFocusable(true);
               texto.setOpaque(false);
               janela01.add(texto);
               texto.requestFocusInWindow();
               texto.addKeyListener( new KeyAdapter() {
                        
                        public void keyPressed(KeyEvent e){
                            
                            if(texto.getText().length() >= 6){
                            texto.setSize(texto.getText().length() *11, 30);
                            }
                            if(e.getKeyCode() == Event.ESCAPE){ // save in file the screen work
                           Salvar();
                            }   
                            if(e.getKeyCode()== Event.ENTER){
                                texto.setFocusable(false);
                                janela01.requestFocus(); // send focus to screen
                            }
                            if(e.getKeyCode() == KeyEvent.VK_F1 ){
                                JOptionPane.showMessageDialog(janela01,"ESC: Save Screen\nEnter: Lock Text"
                                        + "\nLeft Click: Add Text\nMiddle Click: Clear Screen\nRight Click: Draw a Hightlighter  ");
                            } 
                        }
                        
});
                }   
                if(e.getButton() == 2 ){ // clear the screen
                    janela01.getContentPane().removeAll();
                    janela01.repaint();
                    janela01.revalidate();
                }       
            }
            
           
            @Override
            public void mousePressed(MouseEvent e) {
               mousex1 = e.getX();
               mousey1 = e.getY();   
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                 if(e.getButton() == 3 ){
              Grafico graf = new Grafico();  
              mousex = e.getX();
              mousey = e.getY();
              graf.x1 = mousex1;
              graf.y1 = mousey1;
              graf.x2 = e.getX();
              graf.y2 = e.getY();
              janela01.add(graf);
              janela01.repaint();
              janela01.revalidate();
                 }
            }

            @Override
            public void mouseEntered(MouseEvent e) {       
              //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseExited(MouseEvent e) {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
            
        });
        
        janela01.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e){
  int i  = JOptionPane.showConfirmDialog(null, "Deseja realmente Sair ?","Atencao", JOptionPane.YES_NO_OPTION);
                if (i == 0 ) {
                    System.exit(0);
                }    
            }
            
        });
        
        janela01.addKeyListener(new KeyAdapter() {
                   public void keyPressed(KeyEvent e){
                       if(e.getKeyCode() == Event.ESCAPE){ // save in file the screen work
                           Salvar();
                            }
                       if(e.getKeyCode() == KeyEvent.VK_F1 ){
                                JOptionPane.showMessageDialog(janela01, "ESC: Save Screen\nEnter: Lock Text"
                                        + "\nLeft Click: Add Text\nMiddle Click: Clear Screen\nRight Click: Draw a Hightlighter  ");
                            }
                   }
               });
                  
        janela01.setVisible(true);
        
    }
    
}
