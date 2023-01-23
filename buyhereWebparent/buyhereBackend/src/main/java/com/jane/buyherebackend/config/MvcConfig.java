/**
 * FileName: MvcConfig
 * Author: jane
 * Date: 2023/1/9 16:00
 * Description:
 * Version:
 */

package com.jane.buyherebackend.config;

import com.jane.buyherebackend.paging.PagingAndSortingArgumentResolver;
import com.jane.buyherebackend.util.FileUploadUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Locale;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
	private static final Logger LOGGER = LoggerFactory.getLogger(MvcConfig.class);

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {

		// User
		exposeDirectory("buyhereWebparent/buyhereBackend/user-photos", registry);

		// Category
		exposeDirectory("buyhereWebparent/category-images", registry);

		// Brand
		exposeDirectory("buyhereWebparent/brand-logos", registry);

		// Product
		exposeDirectory("buyhereWebparent/product-images", registry);

		// Site Logo
		exposeDirectory("buyhereWebparent/site-logo", registry);

	}

	private void exposeDirectory(String pathPattern, ResourceHandlerRegistry registry) {

		Path path = Paths.get(pathPattern);
		String absolutePath = path.toFile().getAbsolutePath();
		LOGGER.info("!!!!!!!!!!!!!!!!!!!!!!!!!" + absolutePath);
		String logicalPath = pathPattern.replace("../", "") + "/**";

		registry.addResourceHandler(logicalPath)
			.addResourceLocations("file:/" + absolutePath + "/");
	}

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new PagingAndSortingArgumentResolver());
    }



    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver slr = new SessionLocaleResolver();
        slr.setDefaultLocale(Locale.US);
        return slr;
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");
        return lci;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }

}
