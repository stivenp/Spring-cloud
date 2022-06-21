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
public class PreTiempoTranscurridoFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return "pre";
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
        Long initTime = System.currentTimeMillis();
        request.setAttribute("initTime", initTime);
        log.info("{} request enrutado a {}", request.getMethod(), request.getRequestURI().toString());
        return null;
    }

}
