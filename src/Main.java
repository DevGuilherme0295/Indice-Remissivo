import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;


public class Main {

    public static void main(String[] args) {

        String caminhoPalavras = "arquivos/palavras.txt";
        String caminhoTexto    = "arquivos/texto.txt";

        HashColisaoExterior hash = new HashColisaoExterior(26);

        try {
            carregarPalavrasChave(caminhoPalavras, hash);
            processarTexto(caminhoTexto, hash);

            System.out.println("ÍNDICE REMISSIVO:");
            hash.imprimeIndice();

            // >>> gravação em arquivo
            String caminhoSaida = "arquivos/indice.txt";
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(caminhoSaida))) {
                hash.escreveIndice(bw);
            }
            System.out.println("Arquivo gerado em: " + caminhoSaida);

        } catch (IOException e) {
            System.out.println("Erro de leitura/escrita: " + e.getMessage());
        }
    }


    private static void carregarPalavrasChave(String caminho,
                                              HashColisaoExterior hash) throws IOException {

        try (BufferedReader br = new BufferedReader(new FileReader(caminho))) {
            String linha;
            while ((linha = br.readLine()) != null) {

                linha = linha.trim().toLowerCase();
                if (linha.isEmpty()) continue;


                String[] tokens = linha.split("[^A-Za-z0-9áéíóúàèìòùãõâêîôûçÁÉÍÓÚÀÈÌÒÙÃÕÂÊÎÔÛÇ-]+");

                for (String token : tokens) {
                    String palavra = token.trim();
                    if (palavra.isEmpty()) continue;

                    hash.insere(palavra);
                }
            }
        }
    }



    private static void processarTexto(String caminho,
                                       HashColisaoExterior hash) throws IOException {

        try (BufferedReader br = new BufferedReader(new FileReader(caminho))) {
            String linha;
            int numeroLinha = 1;

            while ((linha = br.readLine()) != null) {

                String[] tokens = linha.split("[^A-Za-z0-9áéíóúàèìòùãõâêîôûçÁÉÍÓÚÀÈÌÒÙÃÕÂÊÎÔÛÇ-]+");


                for (String token : tokens) {
                    String palavra = token.trim().toLowerCase();
                    if (palavra.isEmpty()) continue;

                    EntradaIndice entrada = hash.buscar(palavra);
                    if (entrada != null) {
                        entrada.adicionarLinha(numeroLinha);
                    }
                }

                numeroLinha++;
            }
        }
    }
}
