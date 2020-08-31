package work.aijiu.filter;

import work.aijiu.entity.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * @ClassName AuthFilter
 * @Description TODO
 * @Author Administrator
 * @Date 2020/3/28 21:03
 * @Version 1.0
 **/
//@WebFilter(urlPatterns = "/*", filterName = "Filter" )
public class AuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse resp = (HttpServletResponse)servletResponse;
        HttpServletRequest req = (HttpServletRequest)servletRequest;
        HttpSession session = req.getSession();
        List<User> user = (List<User>) session.getAttribute("users");
        String uri = req.getRequestURI();
        if(user == null){
            if(uri.endsWith("login.html")){
                filterChain.doFilter(req, resp);
            }else{
                resp.sendRedirect("http://localhost:8888/car/login.html");
            }
        }else {
            filterChain.doFilter(req, resp);
        }
        filterChain.doFilter(req, resp);
    }

    @Override
    public void destroy() {

    }
}
