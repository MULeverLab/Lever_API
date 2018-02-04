package application.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

import java.util.Collections;
import java.util.List;

@Configuration
public class ResourceServerConfiguration {

    @Bean
    protected org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfiguration userResources() {
        org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfiguration resource = new org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfiguration() {
            public void setConfigurers(List<ResourceServerConfigurer> configurers) {
                super.setConfigurers(configurers);
            }
        };
        resource.setConfigurers(Collections.singletonList(new ResourceServerConfigurerAdapter() {
            private static final String RESOURCE_ID = "users";

            @Override
            public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
                resources.resourceId(RESOURCE_ID).stateless(false);
            }

            @Override
            public void configure(HttpSecurity http) throws Exception {
                http.antMatcher("/**")
                        .authorizeRequests()
                        .antMatchers("/oauth/token").permitAll()
                        .antMatchers("/init").permitAll()
                        .antMatchers("/save/**").authenticated()
                        .antMatchers("/get/**").authenticated();

                http.csrf().disable();
            }
        }));
        resource.setOrder(0);
        return resource;
    }
}
