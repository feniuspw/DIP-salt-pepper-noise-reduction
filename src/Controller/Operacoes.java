package Controller;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.IndexColorModel;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.swing.text.html.ListView;


public class Operacoes {
    BufferedImage imagem = null;
	int [][] matriz ;
    public Operacoes() throws IOException {
        try{
            File imagemInicio = new File("src/View/Original.png");
            imagem = ImageIO.read(imagemInicio);;
            matriz = new int[imagem.getWidth()][imagem.getHeight()];
        }
       catch(IOException e){
           throw new IOException(e);
       }
    }
    public BufferedImage aplicaPimenta(BufferedImage bi,int pimenta){
        WritableRaster rasterizar = bi.getRaster();
        int x,y;
        int porcento = pimenta*(imagem.getWidth()*imagem.getHeight())/100;
        
        while(porcento>0){
          x= (int) (Math.random() * imagem.getWidth());
          y= (int) (Math.random() * imagem.getHeight());
          rasterizar.setSample(x,y,0,0);
          porcento--;
        }
        return bi;
    }
        public BufferedImage aplicaSal(BufferedImage bi,int sal){
        WritableRaster rasterizar = bi.getRaster();
        int x,y;
        int porcento = sal*(imagem.getWidth()*imagem.getHeight())/100;
        while(porcento>0){
          x= (int) (Math.random() * imagem.getWidth());
          y= (int) (Math.random() * imagem.getHeight());
          rasterizar.setSample(x,y,0,255);
          porcento--;
        }
        return bi;
    }
    
	
	
	
	public BufferedImage NivelCinza() throws IOException{
        BufferedImage bi = new BufferedImage(imagem.getWidth(),imagem.getHeight(),BufferedImage.TYPE_BYTE_GRAY);
        WritableRaster rasterizar = bi.getRaster();
        for(int x = 0; x<imagem.getWidth();x++)
            for(int y = 0; y < imagem.getHeight();y++){
                rasterizar.setSample(x, y, 0, imagem.getRGB(x, y));
            }
        return bi;
        
    }
	public void salvaImagem(BufferedImage bi,String nome) throws IOException{
		ImageIO.write(bi, "png", new File("src/View/"+nome+".png"));
	}
	
	
	
	public BufferedImage filtroMediana(BufferedImage bi){
	int x,y;

	WritableRaster rasterizar = bi.getRaster();
	ArrayList<Integer> arr = new ArrayList<Integer>();
	
	for( x = 0; x<imagem.getWidth();x++){
		for( y = 0; y < imagem.getHeight();y++){ 
			Raster r = bi.getData();
			matriz[x][y]= r.getSample(x, y, 0);	
		}
	}
	for( x = 0; x<imagem.getWidth();x++){
		for( y = 0; y < imagem.getHeight();y++){ 
			int [] vet = new int[8];
			int cont=0;
			  if (x - 1 >= 0 && y - 1 >= 0) {
                  vet[cont] = matriz[x - 1][y - 1];
                  cont++;
              } else {
                  vet[cont] = 255;
                  cont++;
              }
              // caso 2!
              if (y - 1 >= 0) {
                  vet[cont] = matriz[x][y - 1];
                  cont++;
              } else {
                  vet[cont] = 255;
                  cont++;
              }
              // caso 3!
              if (y - 1 >= 0 && x + 1 < imagem.getWidth()) {
                  vet[cont] = matriz[x + 1][y - 1];
                  cont++;
              } else {
                  vet[cont] = 255;
                  cont++;
              }
              // caso 4!
              if (x - 1 >= 0) {
                  vet[cont] = matriz[x - 1][y];
                  cont++;
              } else {
                  vet[cont] = 255;
                  cont++;
              }
              // caso 5!
              if (x + 1 < imagem.getWidth()) {
                  vet[cont] = matriz[x + 1][y];
                  cont++;
              } else {
                  vet[cont] = 255;
                  cont++;
              }
              // caso 6!
              if (y + 1 < imagem.getHeight() && x - 1 >= 0) {
                  vet[cont] = matriz[x - 1][y + 1];
                  cont++;
              } else {
                  vet[cont] = 255;
                  cont++;
              }
              // caso 7!
              if (y + 1 < imagem.getHeight()) {
                  vet[cont] = matriz[x][y + 1];
                  cont++;
              } else {
                  vet[cont] = 255;
                  cont++;
              }
              
              // caso 8!
              if (y + 1 < imagem.getHeight() && x + 1 < imagem.getWidth()) {
                  vet[cont] = matriz[x + 1][y + 1];
                  cont++;
              } else {
                  vet[cont] = 255;
                  cont++;
              }
			//faz calculos
			Arrays.sort(vet);
			rasterizar.setSample(x, y, 0, (vet[3]+vet[4])/2);
		}
	}
	return bi;
	}
	public BufferedImage filtroMedia(BufferedImage bi){
	int x,y;
	WritableRaster rasterizar = bi.getRaster();
	for( x = 0; x<imagem.getWidth();x++){
		for( y = 0; y < imagem.getHeight();y++){ 
			Raster r = bi.getData();
			matriz[x][y]= r.getSample(x, y, 0);	
		}
	}
	for( x = 0; x<imagem.getWidth();x++){
		for( y = 0; y < imagem.getHeight();y++){ 
			int [] vet = new int[8];
			int cont=0;
			  if (x - 1 >= 0 && y - 1 >= 0) {
                  vet[cont] = matriz[x - 1][y - 1];
                  cont++;
              } else {
                  vet[cont] = 255;
                  cont++;
              }
              // caso 2!
              if (y - 1 >= 0) {
                  vet[cont] = matriz[x][y - 1];
                  cont++;
              } else {
                  vet[cont] = 255;
                  cont++;
              }
              // caso 3!
              if (y - 1 >= 0 && x + 1 < imagem.getWidth()) {
                  vet[cont] = matriz[x + 1][y - 1];
                  cont++;
              } else {
                  vet[cont] = 255;
                  cont++;
              }
              // caso 4!
              if (x - 1 >= 0) {
                  vet[cont] = matriz[x - 1][y];
                  cont++;
              } else {
                  vet[cont] = 255;
                  cont++;
              }
              // caso 5!
              if (x + 1 < imagem.getWidth()) {
                  vet[cont] = matriz[x + 1][y];
                  cont++;
              } else {
                  vet[cont] = 255;
                  cont++;
              }
              // caso 6!
              if (y + 1 < imagem.getHeight() && x - 1 >= 0) {
                  vet[cont] = matriz[x - 1][y + 1];
                  cont++;
              } else {
                  vet[cont] = 255;
                  cont++;
              }
              // caso 7!
              if (y + 1 < imagem.getHeight()) {
                  vet[cont] = matriz[x][y + 1];
                  cont++;
              } else {
                  vet[cont] = 255;
                  cont++;
              }
              
              // caso 8!
              if (y + 1 < imagem.getHeight() && x + 1 < imagem.getWidth()) {
                  vet[cont] = matriz[x + 1][y + 1];
                  cont++;
              } else {
                  vet[cont] = 255;
                  cont++;
              }
			//faz calculos
			int resp=0;
			for(int i=0;i<8;i++) resp+= vet[i];
			rasterizar.setSample(x, y, 0, resp/8);
		}
	}
	return bi;
	}
	
	
	
	
	
	
	
	
	
	
	public BufferedImage filtroModa(BufferedImage bi){
	int x,y;
	WritableRaster rasterizar = bi.getRaster();
	for( x = 0; x<imagem.getWidth();x++){
		for( y = 0; y < imagem.getHeight();y++){ 
			Raster r = bi.getData();
			matriz[x][y]= r.getSample(x, y, 0);	
		}
	}
	for( x = 0; x<imagem.getWidth();x++){
		for( y = 0; y < imagem.getHeight();y++){ 
			int [] vet = new int[256];
		
			  if (x - 1 >= 0 && y - 1 >= 0) {
                  vet[ matriz[x - 1][y - 1]]++;
                  
              } else {
                 // vet[255]++;
              
              }
              // caso 2!
              if (y - 1 >= 0) {
                  vet[matriz[x][y - 1]]++;
                  
              } else {
                 // vet[255] ++;
                  
              }
              // caso 3!
              if (y - 1 >= 0 && x + 1 < imagem.getWidth()) {
                  vet[matriz[x + 1][y - 1]]++;
                 
              } else {
                 // vet[255]++;
                  
              }
              // caso 4!
              if (x - 1 >= 0) {
                  vet[matriz[x - 1][y]]++;
                  
              } else {
                // vet[255] ++;
                 
              }
              // caso 5!
              if (x + 1 < imagem.getWidth()) {
                  vet[matriz[x + 1][y]]++;
                  
              } else {
                 //vet[255] ++;
                  
              }
              // caso 6!
              if (y + 1 < imagem.getHeight() && x - 1 >= 0) {
                  vet[matriz[x - 1][y + 1]]++;
                  
              } else {
                 //vet[255] ++;
                  
              }
              // caso 7!
              if (y + 1 < imagem.getHeight()) {
                  vet[matriz[x][y + 1]]++;
                  
              } else {
                 // vet[255] ++;
                
              }
              
              // caso 8!
              if (y + 1 < imagem.getHeight() && x + 1 < imagem.getWidth()) {
                  vet[matriz[x + 1][y + 1]]++;
                 
              } else {
                //vet[255] ++;
                  
              }
			//pega o maior valor encontrado
			int resp=0;
			for(int i=0;i<256;i++){
				if(vet[i]>0){
					if(vet[i]>resp) resp = vet[i];
				}
			}
			//faz o rand pra pegar um valor aleatorio
				int cont =0;
				ArrayList<Integer> arr = new ArrayList<Integer>();
				for(int j=0;j<256;j++){
					if(vet[j]==resp){
						arr.add(j);
					}
				}
				cont = (int) (Math.random() * arr.size());
				rasterizar.setSample(x, y, 0, arr.get(cont));
			}

		}
	
	return bi;
}

}