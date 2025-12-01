public class ListaDuplamenteEncadeada {

    class Nodo {

        public String elemento;
        public Nodo proximo;
        public Nodo anterior;

        public Nodo(String elemento) {
            this.elemento = elemento;
            this.proximo = null;
            this.anterior = null;
        }
    }

    private Nodo inicio;
    private Nodo fim;
    private int nElementos;

    public ListaDuplamenteEncadeada() {
        this.inicio = null;
        this.fim = null;
        this.nElementos = 0;
    }

    public boolean estaVazia() {
        return this.nElementos == 0;
    }

    public int tamanho() {
        return this.nElementos;
    }

    public void imprime() {
        System.out.print("[ ");
        Nodo cursor = this.inicio;
        for (int i = 0; i < this.nElementos; i++) {
            System.out.print(cursor.elemento + " ");
            cursor = cursor.proximo;
        }
        System.out.println("]");
    }

    public void imprimeSemColchetes() {
        Nodo cursor = this.inicio;
        for (int i = 0; i < this.nElementos; i++) {
            System.out.print(cursor.elemento);
            if (i < this.nElementos - 1) {
                System.out.print(" ");
            }
            cursor = cursor.proximo;
        }
        System.out.println();
    }

    public void imprimeInverso() {
        System.out.print("[");
        Nodo cursor = this.fim;
        for (int i = 0; i < this.nElementos; i++) {
            System.out.print(cursor.elemento + " ");
            cursor = cursor.anterior;
        }
        System.out.println("]");
    }

    public void insereInicio(String elemento) {

        Nodo novo = new Nodo(elemento);

        if (this.estaVazia()) {
            this.fim = novo;
        } else {
            novo.proximo = this.inicio;
            this.inicio.anterior = novo;
        }

        this.inicio = novo;
        this.nElementos++;

    }

    public String removeInicio() {

        if (this.estaVazia()) {
            System.out.println("Lista vazia. Não é possível remover.");
            return null;
        }

        Nodo nodoRemovido = this.inicio;

        if (this.nElementos == 1) {
            this.inicio = null;
            this.fim = null;
        } else {
            this.inicio = nodoRemovido.proximo;
            this.inicio.anterior = null;

            nodoRemovido.proximo = null;
        }

        this.nElementos--;

        return nodoRemovido.elemento;
    }

    public void insereFinal(String elemento) {

        Nodo novo = new Nodo(elemento);

        if (this.estaVazia()) {
            this.inicio = novo;
        } else {
            this.fim.proximo = novo;
            novo.anterior = this.fim;
        }

        this.fim = novo;

        this.nElementos++;
    }



    public void inserePosicao(String elemento, int pos) {
        if (pos < 0) {
            System.out.println("Posição negativa. Não é possível inserir.");
            return;
        } else if (pos > this.nElementos) {
            System.out.println("Posição inválida. Não é possível inserir.");
            return;
        }

        if (pos == 0) {
            this.insereInicio(elemento);
            return;
        }

        if (pos == this.nElementos) {
            this.insereFinal(elemento);
            return;
        }

        Nodo novo = new Nodo(elemento);

        Nodo cursor = this.inicio;
        for (int i = 1; i <= pos; i++) {
            cursor = cursor.proximo;
        }

        novo.anterior = cursor.anterior;
        novo.proximo = cursor;

        cursor.anterior.proximo = novo;
        cursor.anterior = novo;
        this.nElementos++;
    }

    public boolean contem(String elemento) {

        Nodo cursor = this.inicio;
        for (int i = 0; i < this.nElementos; i++) {
            if (cursor.elemento.equals(elemento) ) {
                return true;
            }
            cursor = cursor.proximo;
        }

        return false;
    }

}