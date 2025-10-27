package org.tommap.stdio.config;

import org.springframework.ai.support.ToolCallbacks;
import org.springframework.ai.tool.ToolCallback;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.tommap.stdio.tool.HelpdeskTools;

import java.util.List;

@Configuration
public class McpServerConfig {
    @Bean //expose tools from MCP servers
    public List<ToolCallback> toolCallbacks(HelpdeskTools helpdeskTools) {
        return List.of(ToolCallbacks.from(helpdeskTools));
    }
}
