package org.tommap.remote.config;

import org.springframework.ai.support.ToolCallbacks;
import org.springframework.ai.tool.ToolCallback;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.tommap.remote.tools.FlightTools;

import java.util.List;

/*
    - as of today spring AI framework does not support streamable HTTP transport type - only supports STDIO & SSE
 */
@Configuration
public class McpServerConfig {
    @Bean //expose tools from MCP servers
    public List<ToolCallback> toolCallbacks(FlightTools flightTools) {
        return List.of(ToolCallbacks.from(flightTools));
    }
}
