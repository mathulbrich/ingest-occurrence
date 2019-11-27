package org.elasticsearch.plugin.ingest.occurrences;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @author mathaus.ulbrich
 *
 */
public class OccurrenceMatcher {

	/**
	 * Return some pieces of text based in regular expression received in parameter.
	 * @param text text to extract matches 
	 * @param pattern pattern of regular expression
	 * @return set of words that matches the regular expression
	 */
	public Set<String> findMatches(String text, Pattern pattern) {
		Set<String> results = null;
		Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            results = new HashSet<>();
            results.add(matcher.group(0));
            while (matcher.find()) {
                results.add(matcher.group(0));
            }
        }
        return results;
	}
	
}
