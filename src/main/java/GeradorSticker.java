package src.main.java;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class GeradorSticker {
    public void criar(InputStream arquivo, String titulo) throws IOException {

        BufferedImage imagemOriginal = ImageIO.read(arquivo);

        //Redimensionamento da imagem
        int largura = imagemOriginal.getWidth();
        int altura = imagemOriginal.getHeight();
        int novaAltura = altura + 200;
        BufferedImage novaImagem = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);

        Graphics2D graphics = (Graphics2D) novaImagem.getGraphics();
        graphics.drawImage(imagemOriginal, 0, 0, null);

        //Editando o arquivo
        Font fonte = new Font(Font.SANS_SERIF, Font.BOLD, 150);
        graphics.setFont(fonte);
        graphics.setColor(Color.MAGENTA);
        graphics.drawString("NASA", 0, novaAltura-100);

        String nomeArquivo = "saida/" + titulo + ".png";
        ImageIO.write(novaImagem, "png", new File(nomeArquivo));
    }
}
