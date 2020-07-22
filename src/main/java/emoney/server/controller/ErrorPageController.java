package emoney.server.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/error/**")
public class ErrorPageController {
	@RequestMapping(value="/{error_code}")
	public ModelAndView error(HttpServletRequest request, @PathVariable String error_code) {
		ModelAndView mav = new ModelAndView("error");
		String msg = (String) request.getAttribute("javax.servlet.error.message"); 
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("STATUS_CODE", request.getAttribute("javax.servlet.error.status_code"));
        map.put("REQUEST_URI", request.getAttribute("javax.servlet.error.request_uri"));
        map.put("EXCEPTION_TYPE", request.getAttribute("javax.servlet.error.exception_type"));
        map.put("EXCEPTION", request.getAttribute("javax.servlet.error.exception"));
        map.put("SERVLET_NAME", request.getAttribute("javax.servlet.error.servlet_name"));
        System.out.println("error_code : "+error_code);
        if(error_code.equals("404")) {
        	map.put("imgUrl","/img/404image.png");
        }else if(error_code.equals("405")) {
        	map.put("imgUrl","/img/405image.jpg");
        }else if(error_code.equals("500")) {
        	map.put("imgUrl","/img/500image.jpg");
        }
        
        
        
        
        try {
            int status_code = Integer.parseInt(error_code);
            switch (status_code) {
            case 400: msg = "400에러 입니다."; break;
            case 403: msg = "403에러 입니다."; break;
            case 404: msg = "404에러 입니다."; break;
            case 405: msg = "405에러 입니다."; break;
            case 500: msg = "500에러 입니다."; break;
            case 503: msg = "503에러 입니다."; break;
            default: msg = "dafaultaaaaa"; break;
            }
        } catch(Exception e) {
            msg = "exception error msg";
            e.printStackTrace();
        } finally {
            map.put("MESSAGE", msg);
        }
         
        mav.addObject("error", map);
        
		return mav;
	}
}
