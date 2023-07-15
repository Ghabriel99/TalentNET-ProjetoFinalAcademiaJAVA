package br.com.ghabriel.ProjetoFinalBackend.config;

import br.com.ghabriel.ProjetoFinalBackend.services.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@EnableWebSecurity // Habilita a configuração de segurança WEB do sistema.
@Configuration // Indica que a classe é uma configuração
@EnableGlobalMethodSecurity(prePostEnabled = true) // Habilita a segurança de método global com anotações Pre e PostAuthorize
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtEntryPoint unauthorizeHanler;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    /**
     * Configura o gerenciador de autenticação do Spring para ser usado na autenticação.
     * @return O gerenciador de autenticação configurado.
     * @throws Exception Se ocorrer algum erro ao configurar o gerenciador de autenticação.
     */
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * Configura o codificador de senha a ser usado para criptografar senhas.
     * @return O codificador de senha BCryptPasswordEncoder.
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder () {
        return new BCryptPasswordEncoder();
    }

    /**
     * Configura o gerenciador de autenticação para usar o serviço de detalhes do usuário personalizado
     * e o codificador de senha para autenticação.
     * @param auth O objeto AuthenticationManagerBuilder para configuração do gerenciador de autenticação.
     * @throws Exception Se ocorrer algum erro ao configurar o gerenciador de autenticação.
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(this.userDetailsService).passwordEncoder(passwordEncoder());
    }

    /**
     * Configura as regras de segurança HTTP.
     * @param http O objeto HttpSecurity para configuração das regras de segurança.
     * @throws Exception Se ocorrer algum erro ao configurar as regras de segurança.
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // Desabilita a proteção CSRF
                .cors().disable() // Desabilita a proteção CORS
                .authorizeRequests() // Configura as regras de autorização
                .antMatchers("/generate-token", "/usuarios/").permitAll() // Permite o acesso sem autenticação às rotas "/generate-token" e "/usuarios/"
                .antMatchers(HttpMethod.OPTIONS).permitAll() // Permite o acesso sem autenticação às requisições OPTIONS
                .anyRequest().authenticated() // Exige autenticação para qualquer outra rota
                .and()
                .exceptionHandling().authenticationEntryPoint(unauthorizeHanler) // Configura o manipulador de exceção para autenticação não autorizada
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); // Configura a política de criação de sessão como STATELESS (sem estado)

        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class); // Adiciona o filtro JwtAuthenticationFilter antes do filtro UsernamePasswordAuthenticationFilter
    }
}
