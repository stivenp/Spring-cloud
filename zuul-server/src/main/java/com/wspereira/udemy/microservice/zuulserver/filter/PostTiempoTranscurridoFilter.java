/*
 * To change this license header, choose License Headers in Project Properties. To change this
 * template file, choose Tools | Templates and open the template in the editor.
 */
package com.wspereira.udemy.microservice.zuulserver.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 *
 * @author stive
 */
@Component
@Slf4j
public class PostTiempoTranscurridoFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return "post";
    }

    @Override
    public int filterOrder() {
        return 1; // generated methods, choose
        // Tools | Templates.
    }

    @Override
    // Validaciones si se ejecuta el filtro
    public boolean shouldFilter() {
        return true;
        // generated methods, choose
        // Tools | Templates.
    }

    @Override
    public Object run() throws ZuulException {

        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();
        
       log.info("entrando a post");
        Long initTime= (Long) request.getAttribute("initTime");
        Long endTime = System.currentTimeMillis();
        Long timeTranscu=endTime-initTime;
        log.info("timepo transcurrido  segundos{}", timeTranscu.longValue()/1000.00);
        log.info("timepo transcurrido  milisegundos{}", timeTranscu);
      
        return null;
    }

}
