package emoney.user.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginInterceptor extends HandlerInterceptorAdapter {
	
	private static final String LOGIN = "login";
	private static final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception{
		
		HttpSession session = request.getSession();
		System.out.println("post handle");
		ModelMap modelMap = modelAndView.getModelMap(); 
		Object userVO = modelMap.get("userVO");
		
		System.out.println("userVO:");
		System.out.println(userVO);
		
		if(userVO != null) {
			System.out.println("userVO not null");
			logger.info("new login success");
			session.setAttribute(LOGIN, userVO);
			Object dest=session.getAttribute("dest");
			session.removeAttribute("dest");
			response.sendRedirect(dest!=null? (String)dest:"/user/main");
			return;
		}else {
			System.out.println("userVO null !!");
			response.sendRedirect("/user/login");
		}
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
		
		HttpSession session = request.getSession();
		System.out.println("pre handle");
		System.out.println("session-----");
		System.out.println(session);
		
		if(session.getAttribute(LOGIN) != null) {
			logger.info("clear login data before");
			session.removeAttribute(LOGIN);
		}
		return true;
	}
	
	
}
