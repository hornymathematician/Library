import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class LoginPanel extends JPanel {
    public int width;
    public int height;
    public Image image;

    public LoginPanel() {
        super();
        URL url = getClass().getResource("u=4089722501,3245086098&fm=26&gp=0.jpg");
        image = new ImageIcon(url).getImage();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);
    }
}
