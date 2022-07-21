package src.main.java;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class Principal {
    public static void main(String[] args) throws IOException, InterruptedException {
        //////////////// CONSUMINDO UMA API - REQUISIÇÃO VIA HTTP NA API DO IMDB //////////////////////
        String url = "https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY&start_date=2022-06-10&end_date=2022-06-14";
        ClienteHttp http = new ClienteHttp();
        String json = http.buscarDados(url);

        IExtratorDeConteudo extrator = new ExtratorDeConteudoDaNasa();
        //IExtratorDeConteudo extrator = new ExtratorDeConteudoDoIMDB();
        List<Conteudo> conteudos = extrator.extrairConteudos(json);


 /*      //Impressão da lista de filmes
        listaDeConteudos
                .stream()
                .map(m -> Stream.of("image","title")
                        .filter(m::containsKey)
                        .collect(Collectors.toMap(key -> key, m::get)))
                .forEach(System.out::println);
 */

        GeradorSticker geradorSticker = new GeradorSticker();
        for (int i= 0; i <5 ; i++){
            Conteudo conteudo = conteudos.get(i);
            InputStream arquivo = new URL(conteudo.getUrlImagem()).openStream();
            geradorSticker.criar(arquivo, conteudo.getTitulo());

        }
        /*Leitura por arquivo
         FileInputStream arquivo = new FileInputStream(new File("entrada/filme.jpg")); */

    }
}
