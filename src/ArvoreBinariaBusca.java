public class ArvoreBinariaBusca {

    class Nodo {

        public EntradaIndice elemento;
        public Nodo esquerdo;
        public Nodo direito;

        public Nodo(EntradaIndice elemento) {
            this.elemento = elemento;
            this.esquerdo = null;
            this.direito = null;
        }
    }

    public Nodo raiz;
    public int nElementos;

    public ArvoreBinariaBusca() {
        this.raiz = null;
        this.nElementos = 0;
    }

    public int tamanho() {
        return this.nElementos;
    }

    public boolean estaVazia() {
        return this.raiz == null;
    }

    // -------- INSERÇÃO POR PALAVRA (ORDENAÇÃO ALFABÉTICA) --------

    public void insere(EntradaIndice entrada) {
        this.raiz = insereRec(entrada, this.raiz);
    }

    private Nodo insereRec(EntradaIndice entrada, Nodo nodo) {

        if (nodo == null) {
            this.nElementos++;
            return new Nodo(entrada);
        }

        String nova = entrada.getPalavra();
        String atual = nodo.elemento.getPalavra();

        int cmp = nova.compareToIgnoreCase(atual);

        if (cmp < 0) {
            nodo.esquerdo = insereRec(entrada, nodo.esquerdo);
        } else if (cmp > 0) {
            nodo.direito = insereRec(entrada, nodo.direito);
        } else {
            // mesma palavra: não insere outra vez, quem muda é a lista de linhas
        }

        return nodo;
    }

    // -------- BUSCA POR PALAVRA --------

    public EntradaIndice busca(String palavra) {
        return buscaRec(palavra, this.raiz);
    }

    private EntradaIndice buscaRec(String palavra, Nodo nodo) {

        if (nodo == null) {
            return null;
        }

        int cmp = palavra.compareToIgnoreCase(nodo.elemento.getPalavra());

        if (cmp < 0) {
            return buscaRec(palavra, nodo.esquerdo);
        } else if (cmp > 0) {
            return buscaRec(palavra, nodo.direito);
        } else {
            return nodo.elemento;
        }
    }

    // -------- IMPRESSÃO EM ORDEM (ÍNDICE REMISSIVO) --------

    public void imprimeIndice() {
        imprimeIndiceRec(this.raiz);
    }

    private void imprimeIndiceRec(Nodo nodo) {
        if (nodo == null) return;

        imprimeIndiceRec(nodo.esquerdo);

        // palavra + espaços + linhas
        System.out.print(nodo.elemento.getPalavra() + " ");
        nodo.elemento.getLinhas().imprimeSemColchetes();

        imprimeIndiceRec(nodo.direito);
    }


    // se quiser só ver as palavras em ordem (debug)
    public void imprimeEmOrdem() {
        emOrdem(this.raiz);
        System.out.println();
    }

    private void emOrdem(Nodo nodo) {
        if (nodo == null) return;

        emOrdem(nodo.esquerdo);
        System.out.print(nodo.elemento.getPalavra() + " ");
        emOrdem(nodo.direito);
    }
}
