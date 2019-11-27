package org.elasticsearch.plugin.ingest.occurrences;

import java.util.Set;
import java.util.regex.Pattern;
import org.junit.Before;
import org.junit.Test;

public class OccurrenceMatcherTest {

	private OccurrenceMatcher matcher;
	
	@Before
	public void setUp() {
		matcher = new OccurrenceMatcher();
	}
	
	@Test
	public void testCTRPatternMatches() {
		Set<String> matches = matcher.findMatches(textForPatterns(), Pattern.compile("CTR"));
		
		assert (matches.size() == 1);
		assert (matches.toArray()[0].equals("CTR"));
	}
	
	@Test
	public void testCodePatternMatches() {
		Set<String> matches = matcher.findMatches(textForPatterns(), Pattern.compile("CT-\\d+"));
		
		assert (matches.size() == 2);
		assert (matches.toArray()[0].equals("CT-2020"));
		assert (matches.toArray()[1].equals("CT-1929"));
	}
	
	private String textForPatterns() {
		return "Para conseguir uma moeda CTR, deve-se escolher o modo CTR Challenge e coletar as "
				+ "letras C-T-R, CT-2020 que estão espalhadas pelo nível e terminar a corrida em primeiro "
				+ "lugar. Se o jogador conseguir, receberá uma moeda CTR da cor do nível. Existem "
				+ " moedas CTR de cada cor (vermelho, verde, azul, amarelo e roxo). Para conseguir"
				+ "a roxa, deve-se entrar nos níveis especias e coletar 20 cristais espalhados pela "
				+ "tela. As moedas CTR, CT-1929 servem para habilitar cinco campeonatos onde pode-se conseguir "
				+ "Gemas coloridas, isso se o jogador conseguir ficar em primeiro lugar nos cinco "
				+ "campeonatos. Essas Gemas habilitam o nível secreto Turbo Track.";
	}
	
}
