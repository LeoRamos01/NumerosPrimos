import java.math.BigDecimal;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 
 * Classe criada com o objetivo de encontrar números primos e também para tirar
 * outras conclusões matemáticas acerca dos mesmos.
 * <p>
 * Utilizei este website do link abaixo para verificar que os resultados que
 * este código produz estão corretos.
 * 
 * @see http://primes.utm.edu/lists/small/10000.txt
 * 
 * @author leonardorm
 *
 */
public class EncontraPrimos {

	/**
	 * 
	 * Lista que contém números primos em ordem.
	 * 
	 */
	private List<BigDecimal> numerosPrimosCalculados = new ArrayList<>();
	
	/**
	 * 
	 * Lista que contém em ordem a diferença entre dois primos consecutivos.
	 * <p>
	 * Interessante essa lista, pois podemos tentar encontrar alguma lógica.
	 * 
	 * @see https://en.wikipedia.org/wiki/Prime_gap
	 * 
	 */
	private List<BigDecimal> primeGap = new ArrayList<>();
	
	private Map<BigDecimal, Entry<BigDecimal, BigDecimal>> paresIntervalo = new HashMap<>();

	/**
	 * 
	 * Inicializa nossa lista com alguns números primos.
	 * 
	 */
	public EncontraPrimos() {
		numerosPrimosCalculados.add(new BigDecimal(2));
		numerosPrimosCalculados.add(new BigDecimal(3));
		numerosPrimosCalculados.add(new BigDecimal(5));
		numerosPrimosCalculados.add(new BigDecimal(7));
		numerosPrimosCalculados.add(new BigDecimal(11));
		numerosPrimosCalculados.add(new BigDecimal(13));
		numerosPrimosCalculados.add(new BigDecimal(17));
		numerosPrimosCalculados.add(new BigDecimal(19));
		numerosPrimosCalculados.add(new BigDecimal(23));
		numerosPrimosCalculados.add(new BigDecimal(29));
		numerosPrimosCalculados.add(new BigDecimal(31));
		numerosPrimosCalculados.add(new BigDecimal(37));
		numerosPrimosCalculados.add(new BigDecimal(41));
		numerosPrimosCalculados.add(new BigDecimal(43));
		numerosPrimosCalculados.add(new BigDecimal(47));
		numerosPrimosCalculados.add(new BigDecimal(53));
		numerosPrimosCalculados.add(new BigDecimal(59));
		numerosPrimosCalculados.add(new BigDecimal(61));
		numerosPrimosCalculados.add(new BigDecimal(67));
		numerosPrimosCalculados.add(new BigDecimal(71));
		numerosPrimosCalculados.add(new BigDecimal(73));
		numerosPrimosCalculados.add(new BigDecimal(79));
		numerosPrimosCalculados.add(new BigDecimal(83));
		numerosPrimosCalculados.add(new BigDecimal(89));
		numerosPrimosCalculados.add(new BigDecimal(97));
		numerosPrimosCalculados.add(new BigDecimal(101));
		numerosPrimosCalculados.add(new BigDecimal(103));
		numerosPrimosCalculados.add(new BigDecimal(107));
		numerosPrimosCalculados.add(new BigDecimal(109));
		numerosPrimosCalculados.add(new BigDecimal(113));
		numerosPrimosCalculados.add(new BigDecimal(127));
		numerosPrimosCalculados.add(new BigDecimal(131));
		numerosPrimosCalculados.add(new BigDecimal(137));
		numerosPrimosCalculados.add(new BigDecimal(139));
		numerosPrimosCalculados.add(new BigDecimal(149));
		numerosPrimosCalculados.add(new BigDecimal(151));
		numerosPrimosCalculados.add(new BigDecimal(157));
		numerosPrimosCalculados.add(new BigDecimal(163));
		numerosPrimosCalculados.add(new BigDecimal(167));
		numerosPrimosCalculados.add(new BigDecimal(173));
		numerosPrimosCalculados.add(new BigDecimal(179));
		numerosPrimosCalculados.add(new BigDecimal(181));
		numerosPrimosCalculados.add(new BigDecimal(191));
		numerosPrimosCalculados.add(new BigDecimal(193));
		numerosPrimosCalculados.add(new BigDecimal(197));
		numerosPrimosCalculados.add(new BigDecimal(199));
		numerosPrimosCalculados.add(new BigDecimal(211));
		numerosPrimosCalculados.add(new BigDecimal(223));
		numerosPrimosCalculados.add(new BigDecimal(227));
		numerosPrimosCalculados.add(new BigDecimal(229));
		numerosPrimosCalculados.add(new BigDecimal(233));
		numerosPrimosCalculados.add(new BigDecimal(239));
		numerosPrimosCalculados.add(new BigDecimal(241));
		numerosPrimosCalculados.add(new BigDecimal(251));
		numerosPrimosCalculados.add(new BigDecimal(257));
		numerosPrimosCalculados.add(new BigDecimal(263));
		numerosPrimosCalculados.add(new BigDecimal(269));
		numerosPrimosCalculados.add(new BigDecimal(271));
		numerosPrimosCalculados.add(new BigDecimal(277));
		numerosPrimosCalculados.add(new BigDecimal(281));
		numerosPrimosCalculados.add(new BigDecimal(283));
		numerosPrimosCalculados.add(new BigDecimal(293));
		numerosPrimosCalculados.add(new BigDecimal(307));
		numerosPrimosCalculados.add(new BigDecimal(311));
		numerosPrimosCalculados.add(new BigDecimal(313));
		numerosPrimosCalculados.add(new BigDecimal(317));
		numerosPrimosCalculados.add(new BigDecimal(331));
		numerosPrimosCalculados.add(new BigDecimal(337));
		numerosPrimosCalculados.add(new BigDecimal(347));
		numerosPrimosCalculados.add(new BigDecimal(349));
		numerosPrimosCalculados.add(new BigDecimal(353));
	}

	/**
	 * 
	 * Encontra todos os números primos no intervalo entre 0 e o parâmetro recebido.
	 * 
	 * @param numero
	 */
	public void acharPrimos (BigDecimal numero) {
		
		BigDecimal ultimo = retornaUltimo();
		Boolean candidatoNaoEPrimo = true;
		
		// Enquanto o número passado for maior que o último primo calculado:
		while (numero.compareTo(ultimo) == 1) {
			
			BigDecimal candidato;
			
			do {
				
				candidato = retornaNovoCandidato(ultimo);
				
				for (int i = 3; i < numerosPrimosCalculados.size(); i++) {
					
					BigDecimal primo = numerosPrimosCalculados.get(i);
					
					// Se o número primo for maior que à metade do candidato, o candidato é primo.
					if (primo.compareTo(candidato.divide(new BigDecimal(2))) == 1) {
						numerosPrimosCalculados.add(candidato);
						candidatoNaoEPrimo = false;
						break;
					}
					
					BigDecimal remainder = candidato.remainder(primo);
					if (BigDecimal.ZERO.compareTo(remainder) == 0) {
						break;
					}
				}
				
				ultimo = candidato;
				
			} while (candidatoNaoEPrimo);
			
			
		}
		
	}
	
	/**
	 * 
	 * Imprime a lista de números primos encontrados com a paginação pedida.
	 * <p>
	 * <ul><b><u>Exemplo:</u></b>
	 * 
	 * <li>20 em 20</li>
	 * <li>50 em 50</li>
	 * 
	 * </ul>
	 * 
	 * @param paginacao
	 */
	public void imprimePrimosEncontrados (Integer paginacao) {
		imprimeLista(paginacao, numerosPrimosCalculados);
	}
	
	/**
	 * 
	 * Imprime a lista de números primos encontrados com a paginação pedida.
	 * <p>
	 * <ul><b><u>Exemplo:</u></b>
	 * 
	 * <li>20 em 20</li>
	 * <li>50 em 50</li>
	 * 
	 * </ul>
	 * 
	 * @param paginacao
	 */
	public void imprimePrimeGap (Integer paginacao) {
		imprimeLista(paginacao, primeGap);
	}
	
	/**
	 * 
	 * Imprime a lista de números primos encontrados com a paginação pedida.
	 * <p>
	 * <ul><b><u>Exemplo:</u></b>
	 * 
	 * <li>20 em 20</li>
	 * <li>50 em 50</li>
	 * 
	 * </ul>
	 * 
	 * @param paginacao
	 */
	public void imprimePrimeGapFirstOcurrences (Integer paginacao) {
		List<String> paginada = new ArrayList<>();
		
		paresIntervalo.forEach((primeiraOcorrenciaIntervalo, par) -> {
			
			String texto = "[" + primeiraOcorrenciaIntervalo + ": (" + par.getKey() + ", " + par.getValue() + ")]";
			paginada.add(texto);
			
		});
		
		System.out.println(paginada);
	}
	
	/**
	 * 
	 * Recebe a lista pedida e imprime ela paginada.
	 * 
	 * @param paginacao
	 * @param lista
	 */
	private void imprimeLista (Integer paginacao, List<BigDecimal> lista) {
		
		Integer counter = 0;
		List<BigDecimal> paginada = new ArrayList<>();
		
		for (BigDecimal valor : lista) {
			
			paginada.add(valor);
			counter++;
			
			if (counter % paginacao == 0) {
				
				System.out.println(paginada);
				
				counter = 0;
				paginada = new ArrayList<>();
				
			}
			
		}
		
	}
	
	/**
	 * 
	 * Monta a {@link List sequência} de subtração entre dois primos consecutivos.
	 * 
	 */
	public void montaListaPrimeGap () {
		
		Integer penultimo = numerosPrimosCalculados.size() - 1;
		
		Set<BigDecimal> setPrimeGap = new HashSet<>();
		
		for (Integer atual = 0; atual < penultimo; atual++) {
			
			Integer proximo = atual + 1;
			
			BigDecimal subtracao = numerosPrimosCalculados.get(proximo).subtract(numerosPrimosCalculados.get(atual));
			
			primeGap.add(subtracao);
			Boolean primeiraOcorrenciaGap = setPrimeGap.add(subtracao);
			
			montaParIntervalo(atual, proximo, primeiraOcorrenciaGap, subtracao);
			
		}
		
		ordenaParIntervalo();
	}

	/**
	 * 
	 * Ordena o mapa das primeiras ocorrências dos intervalos em ordem crescente do intervalo.
	 * 
	 */
	private void ordenaParIntervalo() {
		paresIntervalo = paresIntervalo.entrySet().stream().sorted(Map.Entry.comparingByKey()).collect(Collectors
				.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldValue, newValue) -> oldValue, LinkedHashMap::new));
	}

	/**
	 * 
	 * Monta a lista das primeiras ocorrências dos intervalos.
	 * <p>
	 * Por exemplo o intervalo 4 ocorre pela primeira vez entre 7 e 11.
	 * 
	 * @param atual
	 * @param proximo
	 * @param primeiraOcorrenciaGap
	 * @param subtracao 
	 */
	private void montaParIntervalo(Integer atual, Integer proximo, Boolean primeiraOcorrenciaGap, BigDecimal subtracao) {
		if (primeiraOcorrenciaGap) {

			SimpleEntry<BigDecimal, BigDecimal> par = new SimpleEntry<BigDecimal, BigDecimal>(
					numerosPrimosCalculados.get(atual), numerosPrimosCalculados.get(proximo));

			paresIntervalo.put(subtracao, par);

		}
	}
	
	/**
	 * 
	 * Retorna um bom candidato ímpar a número primo que não termina em 5 e que com
	 * certeza não seja divisível por três.
	 * <p>
	 * Esse método serve como um pré filtro para determinar se um número é primo ou não.
	 * 
	 * @param ultimo
	 * @return
	 */
	private BigDecimal retornaNovoCandidato(BigDecimal ultimo) {
		
		BigDecimal candidato = ultimo;
		Boolean terminaComCinco = true;
		Boolean divisivel3 = true;
		
		do {
			
			candidato = candidato.add(new BigDecimal(2));
			
			String numeroString = candidato.toString();
			
			terminaComCinco = terminaComCinco(numeroString);
			
			if (!terminaComCinco) {

				divisivel3 = divisivelPor3(numeroString);
				
			}

		} while (terminaComCinco || divisivel3);
		
		return candidato;
	}
	
	/**
	 * 
	 * Recebe um número e vê se o seu último algarismo é 5.
	 * 
	 * @param numeroString
	 * @return
	 */
	private Boolean terminaComCinco (String numeroString) {
		
		Character ultimoChar = numeroString.charAt(numeroString.length() - 1);
		
		if (ultimoChar == '5') {
			return true;
		} else {
			return false;
		}
		
	}
	
	/**
	 * 
	 * Recebe um número e soma todos seus algarismos.
	 * <p>
	 * Se a soma for divisível por três, então o número também será divisível por três.
	 * 
	 * @param numeroString
	 * @return
	 */
	private Boolean divisivelPor3 (String numeroString) {
		
		Double soma = 0.0;

		for (Character ch : numeroString.toCharArray()) {
			soma += Double.parseDouble(ch.toString());
		}

		if (soma % 3.0 == 0.0) {
			return true;
		} else {
			return false;
		}
		
	}

	/**
	 * 
	 * Retorna o maior primo já encontrado.
	 * 
	 * @return
	 */
	private BigDecimal retornaUltimo() {
		return numerosPrimosCalculados.get(numerosPrimosCalculados.size() - 1);
	}
	
	public static void main(String[] args) {
		
		EncontraPrimos finder = new EncontraPrimos();
		finder.acharPrimos(new BigDecimal("100000"));
		finder.montaListaPrimeGap();
		finder.imprimePrimeGapFirstOcurrences(20);
		
	}
	
}
