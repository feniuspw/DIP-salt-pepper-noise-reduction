package View;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import Controller.Operacoes;

public class Interface extends JFrame {
  private JMenu filterMenu = new JMenu("Opções");
  private ImagePanel imagePanel;

  
  
  private Operacoes op = new Operacoes();
  BufferedImage imagemCinza = null;
  BufferedImage imagemFinal;
  BufferedImage filtromediana = null;
  BufferedImage filtromedia = null;
  private int pimenta = 5;
  private int sal = 5;
  
  
  public Interface() throws IOException {
	super("Aplicação de Ruido e Filtros em Imagens Digitais");
    
    
	imagePanel = new ImagePanel(Interface.class.getResource("Original.png"));

    JMenuBar menuBar = new JMenuBar();
    setJMenuBar(menuBar);
    filterMenu.setMnemonic('I');

    JMenuItem originalMenuItem = new JMenuItem("Mostrar Original");
    originalMenuItem.setMnemonic('O');

    originalMenuItem.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent action) {
    	imagemFinal = null;
        imagePanel.mostraImagem(imagePanel.originalImage);
      }

    });
    JMenuItem cinza = createMenuItem("Imagem Cinza");
    JMenuItem ruidoPimenta = createMenuItem("Ruído Pimenta");
    JMenuItem ruidoSal = createMenuItem("Ruído Sal");
    JMenuItem ruidoAmbos = createMenuItem("Ruído Ambos");
    JMenuItem filtroMedia = createMenuItem("Filtro Media");
    JMenuItem filtroMediana = createMenuItem("Filtro Mediana");
    JMenuItem filtroModa = createMenuItem("Filtro Moda");

    filterMenu.add(originalMenuItem);
    filterMenu.add(cinza);
    filterMenu.add(ruidoPimenta);
    filterMenu.add(ruidoSal);
    filterMenu.add(ruidoAmbos);
    filterMenu.add(filtroMedia);
    filterMenu.add(filtroMediana);
    filterMenu.add(filtroModa);
    menuBar.add(filterMenu);

    getContentPane().add(imagePanel, BorderLayout.CENTER);
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    this.pack();
    this.setVisible(true);

  }

  public JMenuItem createMenuItem(final String menuItemName) {
    JMenuItem menuItem = new JMenuItem(menuItemName);
    menuItem.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent action) {
      try {
    	
    	if(menuItemName == "Imagem Cinza"){
    		imagemFinal = op.NivelCinza();
            imagePanel.mostraImagem(imagemFinal);
            op.salvaImagem(imagemFinal, "Cinza");
    		
    	}else if(menuItemName == "Ruído Pimenta"){
            imagemCinza = op.NivelCinza();
            imagemFinal = op.aplicaPimenta(imagemCinza, pimenta);
            imagePanel.mostraImagem(imagemFinal);
            op.salvaImagem(imagemFinal, "Pimenta");
       
    	}else if(menuItemName == "Ruído Sal"){
            imagemCinza = op.NivelCinza();
            imagemFinal = op.aplicaSal(imagemCinza, sal);
            imagePanel.mostraImagem(imagemFinal);
            op.salvaImagem(imagemFinal, "Sal");
    	}else if(menuItemName == "Ruído Ambos"){
            imagemCinza = op.NivelCinza();
            imagemFinal = op.aplicaPimenta(imagemCinza, pimenta);
            imagemFinal= op.aplicaSal(imagemFinal, sal);
          imagePanel.mostraImagem(imagemFinal);
          op.salvaImagem(imagemFinal,"Pimenta_e_Sal");     	
        }else if(menuItemName == "Filtro Media"){
        	if(imagemFinal==null){
        		imagemFinal = op.NivelCinza();
        	}
                imagemFinal = op.filtroMedia(imagemFinal);
        		imagePanel.mostraImagem(imagemFinal);
        		op.salvaImagem(imagemFinal, "Media");
        	
        }else if(menuItemName=="Filtro Mediana"){
        	if(imagemFinal==null){
        		imagemFinal = op.NivelCinza();
        	}
            imagemFinal = op.filtroMediana(imagemFinal);
    		imagePanel.mostraImagem(imagemFinal);
    		op.salvaImagem(imagemFinal, "Mediana");
        	
        }else if(menuItemName=="Filtro Moda"){
        	if(imagemFinal==null){
        		imagemFinal = op.NivelCinza();
        	}
        //	int t=0;
        //	while(true){
            imagemFinal = op.filtroModa(imagemFinal);
    		imagePanel.mostraImagem(imagemFinal);
    		op.salvaImagem(imagemFinal, "Moda");//+t++);
       // }
        }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

      }

    });
    return menuItem;
  }


}


