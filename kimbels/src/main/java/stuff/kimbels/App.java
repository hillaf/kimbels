package stuff.kimbels;

import java.awt.Color;
import java.awt.FlowLayout;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import kayttoliittyma.Kayttoliittyma;
import kayttoliittyma.PyoreaNappi;
import sovelluslogiikka.*;

/**
 * Hello world!
 *
 */
public class App {

    public static void main(String[] args) {



        
        Kayttoliittyma kayttoliittyma = new Kayttoliittyma();
        SwingUtilities.invokeLater(kayttoliittyma);




    }
}
