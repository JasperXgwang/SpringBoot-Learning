package com.didispace;

import com.didispace.filter.AccessFilter;
import com.didispace.filter.ErrorFilter;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.filters.post.SendErrorFilter;
import org.springframework.context.annotation.Bean;

@EnableZuulProxy
@SpringCloudApplication
public class Application {

	public static void main(String[] args) {
		new SpringApplicationBuilder(Application.class).web(true).run(args);
	}

	@Bean
	public AccessFilter accessFilter() {
		return new AccessFilter();
	}

    @Bean
    public ErrorFilter errorFilter() {
        return new ErrorFilter();
    }
//    @Bean
//    public SendErrorFilter sendErrorFilter() {
//        return new SendErrorFilter();
//    }

}
