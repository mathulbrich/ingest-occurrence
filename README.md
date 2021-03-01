# Elasticsearch Ingest Occurrence Plugin
Elasticsearch ingest plugin for match occurrences of words and index them in another field.

## Installation of plugin

```bash
$ sh $ES_HOME/bin/elasticsearch-plugin install file:///<path_of_plugin_zip_file>
```

## Example of processor pipeline template

```json
{

  "description" : "Pipeline to extract words from text based in a regex expression",
  "processors" : [ 
  
    {
      
      "occurrence": {
        "field": "my_text_field",
        "target_field": "matches_founded",
        "match_field": "regexp"
      }
    
    }
  
  ]

}
```

## Example of use

### Input (before processor)

```json
{
    "my_text_field": "This is just an example of text field with test-01. And with test-02.",
    "regexp": "test-\d+"
}
```

### Output (after processor)

```json
{
    "matches_founded" : [
        "test-01",
        "test-02"
    ]
}
```
