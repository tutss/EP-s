/******************************************************************************
 *
 * MAC0121 - Algoritmos e Estruturas de Dados I
 * Aluno: Pedro Paulo de Queiroz Bambace
 * Numero USP: 10297668
 * Tarefa: Embaralhando listas ligadas
 * Data: 27/10/2017
 * 
 *
 * DECLARO QUE SOU O UNICO AUTOR E RESPONSAVEL POR ESTE PROGRAMA.  TODAS AS 
 * PARTES DO PROGRAMA, EXCETO AS QUE SAO BASEADAS EM MATERIAL FORNECIDO  
 * PELO PROFESSOR OU COPIADAS DO LIVRO OU DAS BIBLIOTECAS DE SEDGEWICK & WAYNE, 
 * FORAM DESENVOLVIDAS POR MIM.  DECLARO TAMBEM QUE SOU RESPONSAVEL POR TODAS 
 * AS COPIAS DESTE PROGRAMA E QUE NÃO DISTRIBUI NEM FACILITEI A DISTRIBUICAO
 * DE COPIAS DESTA PROGRAMA.
 *
 ******************************************************************************/
import java.util.Map;
import java.util.HashMap;
public class TestShuffle
{
	public static Queue<String> nova(Queue<String> q)
	{
		Queue<String> r = new Queue<String>();
		for(String item : q)
			r.enqueue(item);
		return r;
	}
	public static void main(String[] args)
	{
		int n =Integer.parseInt(args[0]);
		Queue<String> t = new Queue<String>();
		while(!StdIn.isEmpty())
		{
			t.enqueue(StdIn.readString());
		}
		Map<String, Integer> mapa = new HashMap<>();
		for(int i = 0; i < n; i++)
		{
			Queue teste = nova(t);
			teste.shuffle();
			String s = teste.toString();
			if(!mapa.containsKey(s))
			{
                mapa.put(s, 1);
            }
            else
            {
                mapa.put(s, mapa.get(s)+1);
            }
		}
		if(args.length > 1)
		{
			String inicial = t.toString();
			System.out.println(inicial + ": " + mapa.get(inicial));
		}
		else
		{
			for(String key : mapa.keySet())
				System.out.println(key + ": " + mapa.get(key));
		}
	}
}