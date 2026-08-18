package com.northstar.crm.config;

@Configuration
public class WebConfig implements WebMvcConfigurer {
@Override
public void addCorsMappings(CorsRegistry registry) {
registry.addMapping("/api/**")
.allowedOrigins("http://localhost:5173")
.allowedMethods("GET","POST","PUT","DELETE")
.allowedHeaders("Content-Type","X-Correlation-Id");
}
}
