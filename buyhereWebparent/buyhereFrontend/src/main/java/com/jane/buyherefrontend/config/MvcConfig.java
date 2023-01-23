package com.jane.buyherefrontend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.extras.springsecurity5.dialect.SpringSecurityDialect;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class MvcConfig implements WebMvcConfigurer{

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		exposeDirectory("buyhereWebparent/category-images", registry);
		exposeDirectory("buyhereWebparent/brand-logos", registry);
		exposeDirectory("buyhereWebparent/product-images", registry);
		exposeDirectory("buyhereWebparent/site-logo", registry);
	}
//
	private void exposeDirectory(String pathPattern, ResourceHandlerRegistry registry) {
		Path path = Paths.get(pathPattern);
		String absolutePath = path.toFile().getAbsolutePath();

		String logicalPath = pathPattern.replace("../", "") + "/**";

		registry.addResourceHandler(logicalPath)
			.addResourceLocations("file:/" + absolutePath + "/");
	}
	



	@Bean
	public SpringTemplateEngine templateEngine() {
		SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		templateEngine.setTemplateResolver(templateResolver());
		templateEngine.addDialect(new SpringSecurityDialect());
		templateEngine.setEnableSpringELCompiler(true);
		return templateEngine;
	}

	@Bean
	@Description("Thymeleaf view resolver")
	public ViewResolver viewResolver() {

	  var viewResolver = new ThymeleafViewResolver();

	  viewResolver.setTemplateEngine(templateEngine());
	  viewResolver.setCharacterEncoding("UTF-8");

	  return viewResolver;
	}

	@Bean
	@Description("Thymeleaf template resolver serving HTML 5")
	public ClassLoaderTemplateResolver templateResolver() {

	  var templateResolver = new ClassLoaderTemplateResolver();

	  templateResolver.setPrefix("templates/");
	  templateResolver.setCacheable(false); templateResolver.setSuffix(".html");
	  templateResolver.setTemplateMode("HTML5");
	  templateResolver.setCharacterEncoding("UTF-8");

	  return templateResolver;
	}

}
