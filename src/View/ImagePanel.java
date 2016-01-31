package View;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.net.URL;

import javax.swing.JPanel;

class ImagePanel extends JPanel {

  private BufferedImage displayImage;

  public BufferedImage originalImage;

  private Image image;

  public ImagePanel(URL imageURL) {
    image = Toolkit.getDefaultToolkit().createImage(imageURL);
    MediaTracker mediaTracker = new MediaTracker(this);
    mediaTracker.addImage(image, 0);

    try {
      mediaTracker.waitForAll();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    originalImage = new BufferedImage(image.getWidth(null), image.getHeight(null),
        BufferedImage.TYPE_INT_RGB);
    displayImage = originalImage;
    Graphics2D graphics = displayImage.createGraphics();
    graphics.drawImage(image, null, null);

  }

  
  public void mostraImagem(BufferedImage bi) {
	    displayImage = new BufferedImage(image.getWidth(null), image.getHeight(null),
	        BufferedImage.TYPE_INT_RGB);

	    Graphics2D graphics = displayImage.createGraphics();
	    graphics.drawImage(bi, null, null);
	    repaint();
	  }
  
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D graphics = (Graphics2D) g;
    graphics.drawImage(displayImage, 0, 0, null);
  }
  public Dimension getPreferredSize() {
    return new Dimension(displayImage.getWidth(), displayImage.getHeight());
  }
  public Dimension getMinimumSize() {
    return getPreferredSize();
  }
}