import java.io.BufferedWriter;
import java.io.IOException;


public class HashColisaoExterior {

    public ArvoreBinariaBusca vetor[];
    public int nElementos; // quantidade de palavras distintas

    public HashColisaoExterior(int capacidade) {
        this.vetor = new ArvoreBinariaBusca[capacidade];
        for (int i = 0; i < vetor.length; i++) {
            this.vetor[i] = new ArvoreBinariaBusca();
        }
        this.nElementos = 0;
    }

    public int tamanho() {
        return this.nElementos;
    }

    // hash pela primeira letra da palavra
    private int funcaoHash(String palavra) {
        if (palavra == null || palavra.isEmpty()) {
            return 0;
        }

        char c = Character.toLowerCase(palavra.charAt(0));

        // garante que está entre 'a' e 'z'
        if (c < 'a' || c > 'z') {
            return 0;
        }
        return c - 'a'; // 0 = 'a', 1 = 'b', ...
    }

    // insere uma palavra-chave na ABB do bucket correspondente
    public void insere(String palavra) {
        int endereco = funcaoHash(palavra);

        ArvoreBinariaBusca arvore = this.vetor[endereco];

        // já existe na ABB dessa letra?
        EntradaIndice existente = arvore.busca(palavra);
        if (existente == null) {
            EntradaIndice nova = new EntradaIndice(palavra);
            arvore.insere(nova);
            this.nElementos++;
        }
        // se já existe, não faz nada (as linhas serão preenchidas depois)
    }

    // retorna a EntradaIndice da palavra, se existir, senão null
    public EntradaIndice buscar(String palavra) {
        int endereco = funcaoHash(palavra);
        return this.vetor[endereco].busca(palavra);
    }

    public boolean contem(String palavra) {
        return buscar(palavra) != null;
    }

    // imprime índice completo, bucket por bucket (letra por letra)
    public void imprimeIndice() {
        for (int i = 0; i < vetor.length; i++) {
            if (!vetor[i].estaVazia()) {
                // só imprime as palavras desse bucket, sem mostrar a letra
                vetor[i].imprimeIndice();
            }
        }
    }

    // Escreve o índice remissivo completo em um arquivo
    public void escreveIndice(BufferedWriter bw) throws IOException {
        for (int i = 0; i < vetor.length; i++) {
            if (!vetor[i].estaVazia()) {
                vetor[i].escreveIndice(bw);
            }
        }
    }


}
