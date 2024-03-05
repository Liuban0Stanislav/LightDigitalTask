package com._lightdigitaltask.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

/**
 * Класс содержащий настройки безопасности.
 * @Версия: 1.0
 * @Дата: 03.03.2024
 * @Автор: Станислав Любань
 */
@EnableMethodSecurity
@Configuration
public class WebSecurityConfig {

    /**
     * Поле - константа, содаржащая список URL, для доступа к которым не требуется аутентификация.
     */
    private static final String[] AUTH_WHITELIST = {
            "/swagger-resources/**",
            "/swagger-ui.html",
            "/v3/api-docs",
            "/webjars/**",
            "/login",
            "/register"
//            "/photo/**","/application/**"
    };

    /**
     * Метод {@link WebSecurityConfig#filterChain(HttpSecurity)} описывает цепочку фильтров безопасности.
     * <br>В данном случае, это конфигурация цепочки фильтров безопасности для HTTP запросов.</br>
     * <p><br>Процесс настройки безопасности начинается с вызова метода `http.csrf().disable()`,
     * который отключает защиту от CSRF-атак, что может быть полезным в некоторых случаях.</br>
     * <br>Затем происходит вызов `authorizeHttpRequests`, в котором определяются правила авторизации:
     * <ul>
     *     <li> Первый `mvcMatchers(AUTH_WHITELIST).permitAll()` позволяет всем пользователям доступ к URL,
     *     указанным в `AUTH_WHITELIST`.
     *     <li> Второй `mvcMatchers("/ads/", "/users/").authenticated()` требует,
     *     чтобы пользователи были аутентифицированы для доступа к URL "/ads/" и "/users/".</li>
     * </ul></p>
     * <p>Вызов `http.cors().and()` настраивает политику обработки Cross-Origin Resource Sharing (CORS) дла запросов.</p>
     * <p>Так же, вызов `http.httpBasic(withDefaults())` настраивает HTTP Basic Authentication с использованием
     * значений по умолчанию.</p>
     * <p>Наконец, метод `return http.build()` возвращает объект, представляющий цепочку фильтров безопасности
     * для HTTP запросов.</p>
     *
     * @param http - запрос
     * @return цепочка фильтров безопастности для http-запросов
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf()
                .disable()
                .authorizeHttpRequests(
                        authorization ->
                                authorization
                                        .mvcMatchers(AUTH_WHITELIST)
                                        .permitAll()
                                        .mvcMatchers("/application/**", "/user/**")
                                        .authenticated())
                .cors()
                .and()
                .httpBasic(withDefaults());
        return http.build();
    }

    /**
     * Метод кодирует пароль используя {@link BCryptPasswordEncoder}
     * @return объект {@link PasswordEncoder} - хеш пароля
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
