import java.io.BufferedWriter;
import java.io.IOException;


public class HashColisaoExterior {

    public ArvoreBinariaBusca vetor[];
    public int nElementos;

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


    private int funcaoHash(String palavra) {
        if (palavra == null || palavra.isEmpty()) {
            return 0;
        }

        char c = Character.toLowerCase(palavra.charAt(0));


        if (c < 'a' || c > 'z') {
            return 0;
        }
        return c - 'a'; // 0 = 'a', 1 = 'b', ...
    }


    public void insere(String palavra) {
        int endereco = funcaoHash(palavra);

        ArvoreBinariaBusca arvore = this.vetor[endereco];


        EntradaIndice existente = arvore.busca(palavra);
        if (existente == null) {
            EntradaIndice nova = new EntradaIndice(palavra);
            arvore.insere(nova);
            this.nElementos++;
        }

    }


    public EntradaIndice buscar(String palavra) {
        int endereco = funcaoHash(palavra);
        return this.vetor[endereco].busca(palavra);
    }

    public boolean contem(String palavra) {
        return buscar(palavra) != null;
    }


    public void imprimeIndice() {
        for (int i = 0; i < vetor.length; i++) {
            if (!vetor[i].estaVazia()) {

                vetor[i].imprimeIndice();
            }
        }
    }


    public void escreveIndice(BufferedWriter bw) throws IOException {
        for (int i = 0; i < vetor.length; i++) {
            if (!vetor[i].estaVazia()) {
                vetor[i].escreveIndice(bw);
            }
        }
    }


}
