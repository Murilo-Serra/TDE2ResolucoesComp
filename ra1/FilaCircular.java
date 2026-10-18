// TAD Fila em array circular de inteiros.
// O estado fica no objeto. Nao existe variavel global.
public class FilaCircular {
    private int[] elementos;
    private int inicio;
    private int fim;
    private int capacidade;

    // O array tem uma posicao a mais que o limite.
    // Essa posicao livre separa fila cheia de fila vazia.
    public FilaCircular(int limite) {
        capacidade = limite + 1;
        elementos = new int[capacidade];
        inicio = 0;
        fim = 0;
    }

    // Cheia quando a proxima casa do fim e o inicio.
    public boolean cheia() {
        return (fim + 1) % capacidade == inicio;
    }

    // Vazia quando inicio e fim estao na mesma posicao.
    public boolean vazia() {
        return inicio == fim;
    }

    // Grava no fim e avanca o fim. O resto faz a volta ao inicio do array.
    public void insere(int elemento) {
        if (cheia()) {
            return;
        }
        elementos[fim] = elemento;
        fim = (fim + 1) % capacidade;
    }

    // Le o inicio, avanca o inicio e devolve o valor removido.
    public int remove() {
        if (vazia()) {
            return 0;
        }
        int valor = elementos[inicio];
        inicio = (inicio + 1) % capacidade;
        return valor;
    }

    // So consulta o primeiro elemento.
    public int primeiro() {
        if (vazia()) {
            return 0;
        }
        return elementos[inicio];
    }

    // O fim aponta para a proxima casa livre, entao o ultimo esta uma casa antes.
    public int ultimo() {
        if (vazia()) {
            return 0;
        }
        int indice = (fim - 1 + capacidade) % capacidade;
        return elementos[indice];
    }

    // Junta duas filas ordenadas em uma terceira, tambem ordenada.
    // Usa apenas vazia, primeiro, remove e insere.
    public static FilaCircular merge(FilaCircular a, FilaCircular b) {
        FilaCircular r = new FilaCircular(1000);
        while (!a.vazia() && !b.vazia()) {
            if (a.primeiro() <= b.primeiro()) {
                r.insere(a.remove());
            } else {
                r.insere(b.remove());
            }
        }
        while (!a.vazia()) {
            r.insere(a.remove());
        }
        while (!b.vazia()) {
            r.insere(b.remove());
        }
        return r;
    }
}
