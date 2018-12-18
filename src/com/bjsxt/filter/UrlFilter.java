package com.bjsxt.filter;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bjsxt.pojo.Url;
import com.bjsxt.pojo.Users;
@WebFilter("/*")
public class UrlFilter implements Filter{

	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest request=(HttpServletRequest) req;
		String uri=request.getRequestURI();
		if(uri.endsWith(".js")||uri.endsWith(".css")||uri.endsWith(".html")||uri.endsWith(".jpg")||uri.endsWith(".png")||uri.endsWith(".gif")){
			filterChain.doFilter(req, res);
		}else{
			if(uri.equals("/rbac/login")||uri.equals("/rbac/login.jsp")){
				filterChain.doFilter(req, res);
			}else{
				HttpSession session=request.getSession();
				//user
				Object obj=session.getAttribute("user");
				if(obj!=null){
					List<Url> listUrl=(List<Url>) session.getAttribute("allurl");
					boolean isExists=false;
					for(Url url:listUrl){
						if(url.getName().equals(uri)){
							isExists=true;
						}
					}
					//如果url需要权限控制
					if(isExists){
						Users users=(Users) obj;
						boolean isRight=false;
						for(Url url:users.getUrls()){
							if(url.getName().equals(uri)){
								isRight=true;
							}
						}
						if(isRight){//用户对改url有访问权限
							filterChain.doFilter(req, res);
						}else{
							session.removeAttribute("user");
							session.removeAttribute("allurl");
							((HttpServletResponse)res).sendRedirect("/rbac/login.jsp");
						}
					}else {//url不需要进行权限控制
						filterChain.doFilter(req, res);
					}
				}else{
					//用户没有登陆
					((HttpServletResponse)res).sendRedirect("/rbac/login.jsp");
				}
			}
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}

}
