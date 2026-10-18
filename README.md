# Estrutura de Dados — Murilo Serra

Este repositório contém duas atividades: uma fila circular com a operação de merge e uma árvore binária do código Morse.

## RA1 — Fila circular

A fila é um tipo abstrato de dados guardado em um array de inteiros. A inserção acontece no fim e a remoção acontece no início. Os índices `inicio` e `fim` andam dentro do array e, ao passar da última posição, voltam para a posição 0. Por isso a representação se chama circular.

O array é criado com uma posição a mais do que a quantidade máxima de elementos. Essa casa fica livre de propósito. Sem ela, `inicio` igual a `fim` poderia significar tanto fila vazia quanto fila cheia. Com a casa livre, os dois casos ficam diferentes:

- a fila está vazia quando `inicio` e `fim` apontam para a mesma posição;
- a fila está cheia quando a próxima posição de `fim` é exatamente o `inicio`.

O avanço usa o resto da divisão pelo tamanho do array. Se o índice passa do final, o resto o traz de volta para o começo.

Cada fila guarda o próprio array e os próprios índices dentro do objeto. Não há variável global: duas filas não compartilham estado.

### Operações

- `cheia`: verifica se ainda existe casa livre para inserir.
- `vazia`: verifica se não há elementos.
- `insere`: se houver espaço, grava o valor na posição `fim` e avança `fim`.
- `remove`: lê o valor da posição `inicio`, avança `inicio` e devolve esse valor.
- `primeiro`: devolve o elemento do início sem remover e sem mudar os índices.
- `ultimo`: devolve o elemento do fim sem remover. Como `fim` aponta para a próxima casa livre, o último elemento está uma posição antes de `fim`, também em círculo.

### Merge

O merge recebe duas filas já ordenadas, A e B, e produz uma terceira fila R, também ordenada. O método não mexe no array por dentro. Ele só usa `vazia`, `primeiro`, `remove` e `insere`.

Enquanto as duas filas tiverem elementos, o método compara o primeiro de A com o primeiro de B, remove o menor e insere esse valor em R. Quando uma das filas esvazia, o que restou na outra é transferido para R na ordem em que já estava. Como as duas entradas estavam ordenadas e sempre sai o menor da frente, R termina ordenada. A e B ficam vazias, porque cada elemento foi removido para entrar em R.

No exemplo do enunciado:

- Fila A começa com 12, 35, 52 e 64.
- Fila B começa com 05, 15, 23, 55 e 75.

A comparação segue assim:

1. 05 é menor que 12, então R recebe 05.
2. 12 é menor que 15, então R recebe 12.
3. 15 é menor que 35, então R recebe 15.
4. 23 é menor que 35, então R recebe 23.
5. 35 é menor que 55, então R recebe 35.
6. 52 é menor que 55, então R recebe 52.
7. 55 é menor que 64, então R recebe 55.
8. 64 é menor que 75, então R recebe 64.
9. A esvaziou. O 75 que restou em B vai para R.

Fila R: 05, 12, 15, 23, 35, 52, 55, 64, 75.

O programa também testa uma fila de capacidade 2. O terceiro valor não entra, porque a fila já está cheia. Isso mostra `cheia`, `remove`, `primeiro` e `ultimo` em separado do merge.

### Como executar a RA1

Na pasta `ra1`:

```
javac *.java
java Principal
```

## RA2 — Árvore binária do código Morse

O código Morse representa cada letra e cada dígito por uma sequência de pontos e traços. Nesta atividade, essa sequência vira um caminho em uma árvore binária.

A raiz não guarda símbolo. A partir dela:

- ponto (`.`) desce para o filho esquerdo;
- traço (`-`) desce para o filho direito.

O caractere fica no nó em que o caminho termina. Um código curto pode ser ancestral de um código mais longo. A letra E, por exemplo, é só um ponto, então fica no primeiro nó à esquerda. A letra I é ponto-ponto, então fica no filho esquerdo de E. A letra S é três pontos e fica um nível abaixo de I. O mesmo vale para o traço: T é um traço, M são dois traços e O são três traços.

Alguns nós intermediários não têm caractere. Eles existem só porque um código mais longo passa por ali. Na exibição, esses nós aparecem apenas com `.` ou `-`.

### Classes e métodos

`Nodo` guarda o caractere e as referências para o filho esquerdo e o filho direito. Um nó novo nasce com caractere em branco e sem filhos.

`ArvoreBinariaMorse` guarda a raiz e oferece:

- `inicializar`: cria a raiz vazia.
- `inserir`: percorre o código, cria os filhos que ainda não existem e grava o caractere no nó final.
- `inserirAlfabeto`: chama `inserir` para as letras de A a Z e para os dígitos de 0 a 9.
- `buscar`: recebe Morse e devolve o texto. Um código isolado devolve uma letra, como `...` para S e `---` para O. Vários códigos separados por espaço formam uma palavra, então `... --- ...` devolve SOS. A barra `/` separa palavras e vira um espaço no texto.
- `codigoDo`: faz o caminho inverso. Procura a letra na árvore e monta o Morse, colocando ponto quando desce à esquerda e traço quando desce à direita.
- `codificar`: percorre um texto e troca cada caractere pelo Morse obtido em `codigoDo`.
- `exibir`: imprime a árvore de cima para baixo. O ramo do ponto aparece antes do ramo do traço, e a indentação mostra quem é filho de quem.

A busca de um código desce a partir da raiz, um sinal por vez. Se o caminho não existe, ou se o nó final não tem caractere, o resultado daquele trecho é `?`.

### Exemplo de uso

Ao iniciar, o programa faz o que o enunciado pede nos testes:

- `buscar("...")` imprime S;
- `buscar("---")` imprime O;
- `buscar("... --- ...")` imprime SOS.

Em seguida codifica `160020051252`, imprime o Morse e decodifica esse Morse de volta para o mesmo número. Também mostra o Morse de A (`. -`), de Z (`--..`) e de 5 (`.....`), e desenha a árvore.

Depois disso abre um menu:

1. decodificar um Morse digitado;
2. codificar um texto digitado;
3. exibir a árvore outra vez;
4. sair.

### Como executar a RA2

Na pasta `ra2`:

```
javac *.java
java Principal
```

No menu, digite 4 para encerrar.

## Arquivos

- `ra1/FilaCircular.java`: fila circular e merge.
- `ra1/Principal.java`: exemplo da figura e teste de fila cheia.
- `ra2/Nodo.java`: nó da árvore.
- `ra2/ArvoreBinariaMorse.java`: inserção, busca, codificação e exibição.
- `ra2/Principal.java`: exemplos do enunciado e menu.
