package org.elasticsearch.plugin.ingest.occurrences;

import static org.elasticsearch.ingest.ConfigurationUtils.readStringProperty;

import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;
import org.elasticsearch.ingest.AbstractProcessor;
import org.elasticsearch.ingest.IngestDocument;
import org.elasticsearch.ingest.Processor;

/**
 * 
 * @author mathaus.ulbrich
 *
 */
public final class OccurrenceProcessor extends AbstractProcessor {

	/**
	 * 
	 */
    public static final String TYPE = "occurrence";

    /**
     * 
     */
    private final String field;
    
    /**
     * 
     */
    private final String targetField;
    
    /**
     * 
     */
    private final String matchField;
    
    /**
     * 
     * @param tag
     * @param field
     * @param targetField
     * @param regex
     */
    OccurrenceProcessor(String tag, String field, String targetField, String matchField) {
        super(tag);
        this.field = field;
        this.targetField = targetField;
        this.matchField = matchField;
    }

    @Override
    public IngestDocument execute(IngestDocument ingestDocument) throws Exception {
        
        if (ingestDocument.hasField(matchField)) {
            
            String fieldValue = ingestDocument.getFieldValue(field, String.class);
            String matchFieldValue = ingestDocument.getFieldValue(matchField, String.class);
            
            OccurrenceMatcher matcher = new OccurrenceMatcher();
            Set<String> matches = matcher.findMatches(fieldValue, Pattern.compile(matchFieldValue));

            ingestDocument.setFieldValue(targetField, matches);
            
        }
        
        return ingestDocument;
    }

    @Override
    public String getType() {
        return TYPE;
    }
    
    /**
     * 
     * @author mathaus.ulbrich
     *
     */
    public static final class Factory implements Processor.Factory {

		@Override
		public Processor create(Map<String, Processor.Factory> processorFactories, String tag, Map<String, Object> config) throws Exception {
			String field = readStringProperty(OccurrenceProcessor.TYPE, tag, config, "field");
	        String targetField = readStringProperty(OccurrenceProcessor.TYPE, tag, config, "target_field");
	        String matchField = readStringProperty(OccurrenceProcessor.TYPE, tag, config, "match_field");
	        return new OccurrenceProcessor(tag, field, targetField, matchField);
		}
    	
    }
    

}    
