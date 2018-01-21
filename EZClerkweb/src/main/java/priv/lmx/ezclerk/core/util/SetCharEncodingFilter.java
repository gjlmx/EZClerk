package priv.lmx.ezclerk.core.util;

import com.zjut.ssm.controller.HomeController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import java.io.IOException;

public class SetCharEncodingFilter implements Filter {
    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding("UTF-8");
        servletResponse.setCharacterEncoding("UTF-8");
        filterChain.doFilter(servletRequest,servletResponse);



    }

    public void destroy() {

    }
}
