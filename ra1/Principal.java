// Demonstra a fila circular e o merge do enunciado.
public class Principal {
    public static void main(String[] args) {
        FilaCircular filaA = new FilaCircular(10);
        FilaCircular filaB = new FilaCircular(10);

        filaA.insere(12);
        filaA.insere(35);
        filaA.insere(52);
        filaA.insere(64);

        filaB.insere(5);
        filaB.insere(15);
        filaB.insere(23);
        filaB.insere(55);
        filaB.insere(75);

        System.out.println("Fila A");
        System.out.println("Vazia: " + filaA.vazia());
        System.out.println("Cheia: " + filaA.cheia());
        System.out.println("Primeiro: " + texto(filaA.primeiro()));
        System.out.println("Ultimo: " + texto(filaA.ultimo()));
        System.out.print("Elementos: ");
        mostrar(filaA);

        System.out.println("Fila B");
        System.out.println("Vazia: " + filaB.vazia());
        System.out.println("Cheia: " + filaB.cheia());
        System.out.println("Primeiro: " + texto(filaB.primeiro()));
        System.out.println("Ultimo: " + texto(filaB.ultimo()));
        System.out.print("Elementos: ");
        mostrar(filaB);

        // Depois do merge, A e B ficam vazias e R fica ordenada.
        FilaCircular filaR = FilaCircular.merge(filaA, filaB);

        System.out.println("Depois do merge");
        System.out.println("Fila A vazia: " + filaA.vazia());
        System.out.println("Fila B vazia: " + filaB.vazia());
        System.out.println("Fila R");
        System.out.println("Vazia: " + filaR.vazia());
        System.out.println("Primeiro: " + texto(filaR.primeiro()));
        System.out.println("Ultimo: " + texto(filaR.ultimo()));
        System.out.print("Elementos: ");
        mostrar(filaR);

        // Capacidade 2: o terceiro insere nao entra, pois a fila ja esta cheia.
        FilaCircular pequena = new FilaCircular(2);
        pequena.insere(1);
        pequena.insere(2);
        pequena.insere(3);
        System.out.println("Fila pequena");
        System.out.println("Cheia: " + pequena.cheia());
        System.out.println("Primeiro: " + texto(pequena.primeiro()));
        System.out.println("Ultimo: " + texto(pequena.ultimo()));
        int saiu = pequena.remove();
        System.out.println("Removeu: " + texto(saiu));
        System.out.println("Primeiro depois do remove: " + texto(pequena.primeiro()));
        System.out.println("Vazia: " + pequena.vazia());
    }

    // Remove so para imprimir e devolve os valores para a mesma fila.
    static void mostrar(FilaCircular fila) {
        FilaCircular auxiliar = new FilaCircular(1000);
        while (!fila.vazia()) {
            int valor = fila.remove();
            System.out.print(texto(valor));
            System.out.print(" ");
            auxiliar.insere(valor);
        }
        System.out.println();
        while (!auxiliar.vazia()) {
            fila.insere(auxiliar.remove());
        }
    }

    static String texto(int valor) {
        if (valor < 10) {
            return "0" + valor;
        }
        return "" + valor;
    }
}
