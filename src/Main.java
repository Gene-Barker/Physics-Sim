//Copyright Gene Barker 2026
import javax.swing.JFrame;
import java.awt.*;


public class Main{

    public static void main(String[] args){

        //Makes the program us open GL
        System.setProperty("sun.java2d.opengl", "true");

        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(true);
        window.setTitle("Physics Sim");

        Panel panel = new Panel();
        window.add(panel);

        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        panel.startGameThread();
    }
}