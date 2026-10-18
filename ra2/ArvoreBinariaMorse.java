// Arvore binaria em que o caminho da raiz ate o no e o codigo Morse.
public class ArvoreBinariaMorse {
    private Nodo raiz;

    // A raiz comeca vazia. Nenhum simbolo fica nela.
    public void inicializar() {
        raiz = new Nodo();
    }

    // Ponto desce a esquerda e traco desce a direita.
    // O caractere e gravado no no em que o codigo termina.
    public void inserir(String codigo, char simbolo) {
        if (raiz == null) {
            inicializar();
        }
        Nodo atual = raiz;
        int i = 0;
        while (i < codigo.length()) {
            char sinal = codigo.charAt(i);
            if (sinal == '.') {
                if (atual.esquerdo == null) {
                    atual.esquerdo = new Nodo();
                }
                atual = atual.esquerdo;
            } else if (sinal == '-') {
                if (atual.direito == null) {
                    atual.direito = new Nodo();
                }
                atual = atual.direito;
            }
            i = i + 1;
        }
        atual.caractere = simbolo;
    }

    // Insere letras e digitos pelo codigo Morse de cada um.
    public void inserirAlfabeto() {
        inserir(".-", 'A');
        inserir("-...", 'B');
        inserir("-.-.", 'C');
        inserir("-..", 'D');
        inserir(".", 'E');
        inserir("..-.", 'F');
        inserir("--.", 'G');
        inserir("....", 'H');
        inserir("..", 'I');
        inserir(".---", 'J');
        inserir("-.-", 'K');
        inserir(".-..", 'L');
        inserir("--", 'M');
        inserir("-.", 'N');
        inserir("---", 'O');
        inserir(".--.", 'P');
        inserir("--.-", 'Q');
        inserir(".-.", 'R');
        inserir("...", 'S');
        inserir("-", 'T');
        inserir("..-", 'U');
        inserir("...-", 'V');
        inserir(".--", 'W');
        inserir("-..-", 'X');
        inserir("-.--", 'Y');
        inserir("--..", 'Z');
        inserir("-----", '0');
        inserir(".----", '1');
        inserir("..---", '2');
        inserir("...--", '3');
        inserir("....-", '4');
        inserir(".....", '5');
        inserir("-....", '6');
        inserir("--...", '7');
        inserir("---..", '8');
        inserir("----.", '9');
    }

    // Aceita um codigo ou varios, separados por espaco.
    // A barra separa palavras e vira espaco no texto.
    public String buscar(String codigo) {
        String resultado = "";
        String trecho = "";
        int i = 0;
        while (i <= codigo.length()) {
            char sinal = ' ';
            if (i < codigo.length()) {
                sinal = codigo.charAt(i);
            }
            if (i == codigo.length() || sinal == ' ' || sinal == '/') {
                if (trecho.length() > 0) {
                    resultado = resultado + buscarLetra(trecho);
                    trecho = "";
                }
                if (sinal == '/') {
                    resultado = resultado + " ";
                }
            } else {
                trecho = trecho + sinal;
            }
            i = i + 1;
        }
        return resultado;
    }

    // Desce na arvore: ponto vai ao filho esquerdo e traco ao direito.
    private char buscarLetra(String codigo) {
        Nodo atual = raiz;
        int i = 0;
        while (i < codigo.length()) {
            if (atual == null) {
                return '?';
            }
            char sinal = codigo.charAt(i);
            if (sinal == '.') {
                atual = atual.esquerdo;
            } else if (sinal == '-') {
                atual = atual.direito;
            }
            i = i + 1;
        }
        if (atual == null || atual.caractere == ' ') {
            return '?';
        }
        return atual.caractere;
    }

    // Procura a letra na arvore e devolve o Morse correspondente.
    public String codigoDo(char simbolo) {
        if (simbolo >= 'a' && simbolo <= 'z') {
            simbolo = (char) (simbolo - 32);
        }
        return procurar(raiz, simbolo, "");
    }

    // Esquerda acrescenta ponto ao caminho. Direita acrescenta traco.
    private String procurar(Nodo no, char simbolo, String caminho) {
        if (no == null) {
            return "";
        }
        if (no.caractere == simbolo && caminho.length() > 0) {
            return caminho;
        }
        String achou = procurar(no.esquerdo, simbolo, caminho + ".");
        if (achou.length() > 0) {
            return achou;
        }
        return procurar(no.direito, simbolo, caminho + "-");
    }

    // Troca cada letra pelo Morse. Espaco do texto vira barra.
    public String codificar(String texto) {
        String morse = "";
        int i = 0;
        while (i < texto.length()) {
            char simbolo = texto.charAt(i);
            if (simbolo >= 'a' && simbolo <= 'z') {
                simbolo = (char) (simbolo - 32);
            }
            if (simbolo == ' ') {
                if (morse.length() > 0) {
                    morse = morse + " ";
                }
                morse = morse + "/";
            } else {
                String codigo = codigoDo(simbolo);
                if (codigo.length() > 0) {
                    if (morse.length() > 0) {
                        morse = morse + " ";
                    }
                    morse = morse + codigo;
                }
            }
            i = i + 1;
        }
        return morse;
    }

    // Mostra primeiro o ramo do ponto e depois o ramo do traco.
    public void exibir() {
        System.out.println("Raiz");
        mostrar(raiz.esquerdo, ".", "");
        mostrar(raiz.direito, "-", "");
    }

    private void mostrar(Nodo no, String ramo, String espacos) {
        if (no == null) {
            return;
        }
        if (no.caractere == ' ') {
            System.out.println(espacos + ramo);
        } else {
            System.out.println(espacos + ramo + " " + no.caractere);
        }
        mostrar(no.esquerdo, ".", espacos + "  ");
        mostrar(no.direito, "-", espacos + "  ");
    }
}
