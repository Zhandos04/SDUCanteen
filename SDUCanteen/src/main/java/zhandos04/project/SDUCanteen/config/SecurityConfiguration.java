package zhandos04.project.SDUCanteen.config;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import zhandos04.project.SDUCanteen.jwt.JwtFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration {

    private final JwtFilter jwtFilter;
    private final CustomAuthenticationProvider customAuthenticationProvider;


    @Autowired
    public SecurityConfiguration(JwtFilter jwtFilter, CustomAuthenticationProvider customAuthenticationProvider) {
        this.jwtFilter = jwtFilter;
        this.customAuthenticationProvider = customAuthenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);
        http.authorizeHttpRequests(request ->
                request.requestMatchers("/auth/**").permitAll().requestMatchers("/food/**").permitAll()
                        .requestMatchers("/basket/**").permitAll()
                        .requestMatchers("/api-docs").permitAll()
                        .requestMatchers("/swagger-ui/**").permitAll().anyRequest().authenticated());
        http.sessionManagement(req -> req.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.authenticationProvider(customAuthenticationProvider);
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}