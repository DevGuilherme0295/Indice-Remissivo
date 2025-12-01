public class EntradaIndice {

    private String palavra;
    private ListaDuplamenteEncadeada linhas; // guarda os números de linha como String

    public EntradaIndice(String palavra) {
        this.palavra = palavra;
        this.linhas = new ListaDuplamenteEncadeada();
    }

    public String getPalavra() {
        return palavra;
    }

    public ListaDuplamenteEncadeada getLinhas() {
        return linhas;
    }

    public void adicionarLinha(int numeroLinha) {
        String s = String.valueOf(numeroLinha);
        // evita registrar a mesma linha mais de uma vez
        if (!linhas.contem(s)) {
            linhas.insereFinal(s);
        }
    }

    @Override
    public String toString() {
        return palavra;
    }
}
