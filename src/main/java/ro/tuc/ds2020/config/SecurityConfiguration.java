package ro.tuc.ds2020.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class SecurityConfiguration {

//    @Bean
//    CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.setAllowedOrigins(
//                Arrays.asList(
//                        "http://localhost:3000",
//                        "https://localhost:3000",
//                        "http://localhost:3001",
//                        "https://localhost:3001"
//                )
//        );
//        configuration.setAllowedHeaders(Arrays.asList("*"));//this took me 8 hours to figure out...
//        configuration.setAllowedMethods(Arrays.asList("*"));
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//
//        return source;
//    }

//    @Bean
//    public WebMvcConfigurer corsConfigurer() {
//        return new WebMvcConfigurerAdapter() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/**")
//                        .allowedMethods("*");
//            }
//        };
//    }
}

