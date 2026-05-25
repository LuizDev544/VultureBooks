package BookDonation.demo.infrastructure.Configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    private final AutorizacaoInterceptor autorizacaoInterceptor;

    public WebMvcConfig(AutorizacaoInterceptor autorizacaoInterceptor) {
        this.autorizacaoInterceptor = autorizacaoInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(autorizacaoInterceptor)
                .addPathPatterns("/livros/**", "/admin/painel");
    }
}