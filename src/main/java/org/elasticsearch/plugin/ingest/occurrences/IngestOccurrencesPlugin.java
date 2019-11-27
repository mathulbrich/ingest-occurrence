package org.elasticsearch.plugin.ingest.occurrences;

import java.util.Collections;
import java.util.Map;
import org.elasticsearch.ingest.Processor.Factory;
import org.elasticsearch.ingest.Processor.Parameters;
import org.elasticsearch.plugins.IngestPlugin;
import org.elasticsearch.plugins.Plugin;

/**
 * 
 * @author mathaus.ulbrich
 *
 */
public class IngestOccurrencesPlugin extends Plugin implements IngestPlugin {

    @Override
    public Map<String, Factory> getProcessors(Parameters parameters) {
        return Collections.singletonMap(OccurrenceProcessor.TYPE, new OccurrenceProcessor.Factory());
    }
    
}
