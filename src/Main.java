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

    // Lê o arquivo de palavras-chave e preenche a hash (ABB por letra)
    private static void carregarPalavrasChave(String caminho,
                                              HashColisaoExterior hash) throws IOException {

        try (BufferedReader br = new BufferedReader(new FileReader(caminho))) {
            String linha;
            while ((linha = br.readLine()) != null) {

                linha = linha.trim().toLowerCase();
                if (linha.isEmpty()) continue;

                // aqui eu QUEBRO a linha em várias palavras
                // separando por qualquer coisa que não seja letra, número ou hífen
                String[] tokens = linha.split("[^A-Za-z0-9áéíóúàèìòùãõâêîôûçÁÉÍÓÚÀÈÌÒÙÃÕÂÊÎÔÛÇ-]+");

                for (String token : tokens) {
                    String palavra = token.trim();
                    if (palavra.isEmpty()) continue;

                    // cada palavra vai pra hash (que joga na ABB da letra correspondente)
                    hash.insere(palavra);
                }
            }
        }
    }


    // Lê o texto linha a linha e marca em quais linhas cada palavra-chave aparece
    private static void processarTexto(String caminho,
                                       HashColisaoExterior hash) throws IOException {

        try (BufferedReader br = new BufferedReader(new FileReader(caminho))) {
            String linha;
            int numeroLinha = 1;

            while ((linha = br.readLine()) != null) {

                // separa por não-letras/números (pode ajustar depois)
                String[] tokens = linha.split("[^A-Za-z0-9áéíóúàèìòùãõâêîôûçÁÉÍÓÚÀÈÌÒÙÃÕÂÊÎÔÛÇ-]+");


                for (String token : tokens) {
                    String palavra = token.trim().toLowerCase();
                    if (palavra.isEmpty()) continue;

                    // busca direto na hash (que já sabe qual ABB olhar)
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
