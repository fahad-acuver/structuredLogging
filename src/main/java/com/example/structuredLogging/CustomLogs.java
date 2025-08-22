package com.example.structuredLogging;

import ch.qos.logback.classic.spi.ILoggingEvent;
import org.springframework.boot.json.JsonWriter;
import org.springframework.boot.logging.structured.StructuredLogFormatter;
import org.springframework.stereotype.Component;

@Component
public class CustomLogs implements StructuredLogFormatter<ILoggingEvent> {

    private final JsonWriter<ILoggingEvent> writer=JsonWriter.<ILoggingEvent>of((member)->{
       member.add("timestamp", ILoggingEvent::getTimeStamp);
       member.add("event", ILoggingEvent::getLevel);
       member.add("message", ILoggingEvent::getFormattedMessage);
       member.add("application").usingMembers((application)->{
           application.add("name","structuredLogging");
           application.add("version","some snapshot version");
       });
       member.add("node").usingMembers((node)->{
          node.add("hostname","someNode");
          node.add("ip","0.0.0.0");
       });

    }).withNewLineAtEnd();
    @Override
    public String format(ILoggingEvent event) {
        return writer.writeToString(event);
    }
}
