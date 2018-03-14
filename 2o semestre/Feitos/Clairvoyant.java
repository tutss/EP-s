
/******************************************************************************
  MAC0121 - Algoritmos e Estruturas de Dados I
  Aluno: Artur Magalhães Rodrigues dos Santos
  Numero USP: 10297734
  Tarefa: Clairvoyant.java - Caching ótimo
  Data: 28/11/2017
  Dependencies: IndexMaxPQ.java, Queue.java, ST.java

  DECLARO QUE SOU O ÚNICO AUTOR E RESPONSÁVEL POR ESTE PROGRAMA.  TODAS AS 
  PARTES DO PROGRAMA, EXCETO AS QUE SÃO BASEADAS EM MATERIAL FORNECIDO  
  PELO PROFESSOR OU COPIADAS DO LIVRO OU DAS BIBLIOTECAS DE SEDGEWICK & WAYNE, 
  FORAM DESENVOLVIDAS POR MIM.  DECLARO TAMBÉM QUE SOU RESPONSÁVEL POR TODAS 
  AS CÓPIAS DESTE PROGRAMA E QUE NÃO DISTRIBUÍ NEM FACILITEI A DISTRIBUIÇÃO
  DE CÓPIAS DESTA PROGRAMA. 
  _____________________
  Argumentação sucinta: Consegui chegar a complexidade devido a:
  --------------------- - Ler a entrada apenas uma vez (N)
                        - Utilizar apenas as operações na ST (log n)
                        - As operações na Fila de Prioridade Indexada (log m)
  Tais fatores fazem com que a complexidade seja a desejada. As operações as
  ST's e PQ's possibilitam um acesso e uso mais rápido do dados que estão con-
  tidos nas chaves.
 ******************************************************************************/ 


public class Clairvoyant {

  public static void main(String[] args) {
    // Inicialização
    int pqsize = Integer.parseInt(args[0]);
    int cachem = 0;
    
    // cache = PQ com os valores do cache
    IndexMaxPQ<String> cache = new IndexMaxPQ<String>(pqsize);
    // distancia = valores de prioridade para retirar do cache
    // quando necessário
    IndexMaxPQ<Integer> distancia = new IndexMaxPQ<Integer>(pqsize);

    String[] input = StdIn.readAllStrings();

    // 
    ST<String, Queue<Integer>> occurWhere = new ST<String, Queue<Integer>>();
    ST<String, Integer> cacheCheck = new ST<String, Integer>();
    
    // Flag
    boolean arg = false;
    if (args.length > 1) arg = true;
    
    // Inicializa a posição no vetor
    // em que ocorrem os respectivos números
    for (int i = 0; i < input.length; i++) {
        if (!occurWhere.contains(input[i])) {
            Queue<Integer> q = new Queue<Integer>();
            occurWhere.put(input[i], q);
        }
        else {
            occurWhere.get(input[i]).enqueue(i);
        }
    }
    
    /*
    * Observação: Analisei os 3 casos possíveis dentro do cache.
    * Em cada caso, foi verificado se era possível ou não tirar
    * da fila, e assim, toma-se uma decisão. O caso de ser nulo,
    * implica que o elemento não ocorre mais na entrada, ou seja,
    * tem a maior prioridade.
    */
    int c = 0;
    for (int i = 0; i < input.length; i++) {
        // Caso em que o elemento está no cache
        if (cacheCheck.contains(input[i])) {            
            if (arg) System.out.println(input[i] + ": in cache");

            Integer in = cacheCheck.get(input[i]);
            Integer check;

            if (!occurWhere.get(input[i]).isEmpty()) {
               check = occurWhere.get(input[i]).dequeue();
            }
            else {
                check = null;
            }

            if (check != null)
                distancia.changeKey(in, check);
            else 
                distancia.changeKey(in, input.length+2);
        }
        // Caso em que o cache possui lugares vazios
        else if (pqsize > cache.size()) {
            cache.insert(c, input[i]);
            Integer k;
            if (!occurWhere.get(input[i]).isEmpty()) {
                k = occurWhere.get(input[i]).dequeue();
            }
            else {
                k = null;
            }

            if (k != null) {
                distancia.insert(c, k);
            }
            else {
                distancia.insert(c, input.length+2);
            }
            cacheCheck.put(input[i], c);
            cachem++;
            if (arg) System.out.println(input[i]+ ": +" + input[i]);
            c++;
        }
        // Caso em que o cache está cheio e precisamos retirar um elemento
        else {
            int r = distancia.maxIndex();
            String s = cache.keyOf(r);            
            cachem++;
            if (arg) System.out.println(input[i] + ": -" + s + "/+" + input[i]);
            cache.changeKey(r, input[i]);
            cacheCheck.delete(s);
            cacheCheck.put(input[i], r);
            Integer in;
            if (!occurWhere.get(input[i]).isEmpty()) {
               in = occurWhere.get(input[i]).dequeue();
            }
            else {
                in = null;
            }

            if (in != null)
                distancia.changeKey(r, in);
            else
                distancia.changeKey(r, input.length+2);
        }


    }

    System.out.println("Number of cache misses: " + cachem);
  }
}
