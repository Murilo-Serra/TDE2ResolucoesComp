// Um no da arvore Morse: o caractere e os dois filhos.
// Esquerdo representa ponto. Direito representa traco.
public class Nodo {
    public char caractere;
    public Nodo esquerdo;
    public Nodo direito;

    public Nodo() {
        caractere = ' ';
        esquerdo = null;
        direito = null;
    }
}
