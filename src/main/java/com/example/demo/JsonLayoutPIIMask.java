package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.contrib.json.classic.JsonLayout;

public class JsonLayoutPIIMask extends JsonLayout{
	
	private Pattern multilinePattern;
    private List<String> maskPatterns = new ArrayList<>();

    public void addMaskPattern(String maskPattern) {
        maskPatterns.add(maskPattern);
        multilinePattern = Pattern.compile(maskPatterns.stream().collect(Collectors.joining("|")), Pattern.MULTILINE);
    }
	
	 @Override
	 protected void addCustomDataToJsonMap(Map<String, Object> map, ILoggingEvent event) {
		 if(multilinePattern != null) {
			 Object message = map.get(FORMATTED_MESSAGE_ATTR_NAME);
			 if(message instanceof String) {
				 
				 StringBuilder sb = new StringBuilder((String)message);
			        Matcher matcher = multilinePattern.matcher(sb);
			        while (matcher.find()) {
			            IntStream.rangeClosed(1, matcher.groupCount()).forEach(group -> {
			                if (matcher.group(group) != null) {
			                    IntStream.range(matcher.start(group), matcher.end(group)).forEach(i -> sb.setCharAt(i, '*'));
			                }
			            });
			        }
				 
				 map.put(FORMATTED_MESSAGE_ATTR_NAME, sb.toString());
				 
			 }
		 }
		 
		 
	    }
}
