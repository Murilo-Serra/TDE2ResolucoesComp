import java.util.Scanner;

// Monta a arvore, mostra os exemplos do enunciado e abre o menu.
public class Principal {
    public static void main(String[] args) {
        ArvoreBinariaMorse arvore = new ArvoreBinariaMorse();
        arvore.inicializar();
        arvore.inserirAlfabeto();

        System.out.println(arvore.buscar("..."));
        System.out.println(arvore.buscar("---"));
        System.out.println(arvore.buscar("... --- ..."));

        String exemplo = "160020051252";
        String morseExemplo = arvore.codificar(exemplo);
        System.out.println(exemplo);
        System.out.println(morseExemplo);
        System.out.println(arvore.buscar(morseExemplo));

        System.out.println(arvore.codigoDo('A'));
        System.out.println(arvore.codigoDo('Z'));
        System.out.println(arvore.codigoDo('5'));

        arvore.exibir();

        Scanner leitor = new Scanner(System.in);
        boolean continuar = true;
        while (continuar) {
            System.out.println("1 - Decodificar Morse");
            System.out.println("2 - Codificar texto");
            System.out.println("3 - Exibir arvore");
            System.out.println("4 - Sair");
            if (!leitor.hasNextLine()) {
                continuar = false;
            } else {
                String opcao = leitor.nextLine();
                if (opcao.length() > 0 && opcao.charAt(0) == '1') {
                    System.out.println("Digite o Morse:");
                    if (leitor.hasNextLine()) {
                        String entrada = leitor.nextLine();
                        System.out.println(arvore.buscar(entrada));
                    }
                } else if (opcao.length() > 0 && opcao.charAt(0) == '2') {
                    System.out.println("Digite o texto:");
                    if (leitor.hasNextLine()) {
                        String entrada = leitor.nextLine();
                        System.out.println(arvore.codificar(entrada));
                    }
                } else if (opcao.length() > 0 && opcao.charAt(0) == '3') {
                    arvore.exibir();
                } else if (opcao.length() > 0 && opcao.charAt(0) == '4') {
                    continuar = false;
                }
            }
        }
        leitor.close();
    }
}
