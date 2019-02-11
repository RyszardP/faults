package com.ryszard.filter;

import javax.servlet.*;
import java.io.IOException;

public class CharsetFilter implements Filter {
    private String encoding;
    private ServletContext context;
    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding(encoding);
        response.setCharacterEncoding(encoding);

        chain.doFilter(request, response);
    }


    @Override
    public void init(FilterConfig config) throws ServletException {
        encoding = config.getInitParameter("characterEncoding");
        context = config.getServletContext();
    }
}
