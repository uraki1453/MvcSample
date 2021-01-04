package MvcSample.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    @Override
    public void configure(WebSecurity web) throws Exception {
        //静的リソースをセキュリティ対象外に設定
        web.ignoring().antMatchers("/webjars/**", "/css/**", "/resources/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                //「login.html」はログイン不要でアクセス可能に設定
                .antMatchers("/login").permitAll()
                //上記以外は直リンク禁止
                .anyRequest().authenticated()
            .and()
            .formLogin()
                //ログイン処理のパス
                .loginProcessingUrl("/login")
                //ログインページ
                .loginPage("/login")
                //ログインエラー時の遷移先 ※パラメーターに「error」を付与
                .failureUrl("/login?error")
                //ログイン成功時の遷移先
                .defaultSuccessUrl("/TDB1000", true)
                //ログイン時のキー：名前
                .usernameParameter("loginid")
                //ログイン時のパスワード
                .passwordParameter("password")

            .and()
            .logout()
                //ログアウト時の遷移先 POSTでアクセス
                .logoutSuccessUrl("/login")
            .and()
                .sessionManagement().maximumSessions(1).maxSessionsPreventsLogin(true);
    }

    //パスワードのアルゴリズムをBCryptに設定
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
