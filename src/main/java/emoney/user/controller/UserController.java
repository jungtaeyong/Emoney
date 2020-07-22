package emoney.user.controller;


import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.crypto.Cipher;
import javax.inject.Inject;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.scribejava.core.model.OAuth2AccessToken;

import emoney.user.controller.UserController;
import emoney.user.domain.LoginHistoryVO;
import emoney.user.domain.UserVO;
import emoney.user.dto.LoginDTO;
import emoney.user.service.UserService;

import org.springframework.security.crypto.password.StandardPasswordEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequestMapping("/user/**")
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Inject UserService service;
	@Inject StandardPasswordEncoder standardPasswordEncoder;
	
	private NaverLoginBO naverLoginBO;
    private String apiResult = null;
    
    @Inject
    private void setNaverLoginBO(NaverLoginBO naverLoginBO) {
        this.naverLoginBO = naverLoginBO;
    }
	
	
	@RequestMapping(value = "/naverLogin", method = RequestMethod.GET)
	public String naverLogin(Model model, HttpSession session) throws Exception {
		
		logger.info("get method!");
		System.out.println("get method!!");
		
		/* 네이버아이디로 인증 URL을 생성하기 위하여 naverLoginBO클래스의 getAuthorizationUrl메소드 호출 */
        String naverAuthUrl = naverLoginBO.getAuthorizationUrl(session);
        
        //https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=sE***************&
        //redirect_uri=http%3A%2F%2F211.63.89.90%3A8090%2Flogin_project%2Fcallback&state=e68c269c-5ba9-4c31-85da-54c16c658125
        System.out.println("네이버:" + naverAuthUrl);
        
        //네이버 
        model.addAttribute("url", naverAuthUrl);

        /* 생성한 인증 URL을 View로 전달 */
        return "naver_login";
	}
	
	public static String convertString(String val) {
		// 변환할 문자를 저장할 버퍼 선언
		StringBuffer sb = new StringBuffer();
		// 글자를 하나하나 탐색한다.
		for (int i = 0; i < val.length(); i++) {
		
			if ('\\' == val.charAt(i) && 'u' == val.charAt(i + 1)) {
				// 그 뒤 네글자는 유니코드의 16진수 코드이다. int형으로 바꾸어서 다시 char 타입으로 강제 변환한다.
				Character r = (char) Integer.parseInt(val.substring(i + 2, i + 6), 16);
				// 변환된 글자를 버퍼에 넣는다.
				sb.append(r);
				// for의 증가 값 1과 5를 합해 6글자를 점프
				i += 5;
			} else {
			// ascii코드면 그대로 버퍼에 넣는다.
			sb.append(val.charAt(i));
			}
		}
		// 결과 리턴
		return sb.toString();
	}
	
	@RequestMapping(value = "/naverCallback", method = { RequestMethod.GET, RequestMethod.POST })
    public String callback(HttpServletRequest request, Model model, @RequestParam String code, @RequestParam String state, HttpSession session)
            throws Exception {
        System.out.println("여기는 callback");
        OAuth2AccessToken oauthToken;
        oauthToken = naverLoginBO.getAccessToken(session, code, state);
        //로그인 사용자 정보를 읽어온다.
        apiResult = naverLoginBO.getUserProfile(oauthToken);
        System.out.println(naverLoginBO.getUserProfile(oauthToken).toString());
        model.addAttribute("result", apiResult);
        System.out.println("result"+apiResult);
        session.setAttribute("oauth", apiResult);
        
        JSONParser parsing = new JSONParser();
        Object obj = parsing.parse(apiResult);
        JSONObject jsonObj = (JSONObject)obj;
        JSONObject apiResultObj = (JSONObject)jsonObj.get("response");
        String naverId = (String)apiResultObj.get("id");
        
        System.out.println("naverId:"+naverId);
        
        UserVO uvo = service.login(naverId);
        if(uvo!=null) {
        	String ip = getIp(request);
    		String browser = getBrowser(request);
    		String isMobile = getMobile(request);
    		String os = getOS(request);

    		Date now = new Date();
    		
    		uvo.setLastLogin(now);
    		LoginHistoryVO lvo= new LoginHistoryVO(); 
    		lvo.setAccntId(uvo.getAccntId());
    		lvo.setBrowser(browser);
    		lvo.setIp(ip);
    		lvo.setIsMobile(isMobile);
    		lvo.setLoginDate(now);
    		lvo.setOs(os);
    		System.out.println(uvo);
    		System.out.println(lvo);
    		//uvo.setLastLogin(null);
    		service.loginLog(lvo, uvo);
        	session.setAttribute("login", uvo);
        } 
        
        //UserVO uvo=service()
        //session.setAttribute("login", value);

        /* 네이버 로그인 성공 페이지 View 호출 */
//      JSONObject jsonobj = jsonparse.stringToJson(apiResult, "response");
//      String snsId = jsonparse.JsonToString(jsonobj, "id");
//      String name = jsonparse.JsonToString(jsonobj, "name");
//
//      UserVO vo = new UserVO();
//      vo.setUser_snsId(snsId);
//      vo.setUser_name(name);
//
//      System.out.println(name);
//      try {
//          vo = service.naverLogin(vo);
//      } catch (Exception e) {
//          // TODO Auto-generated catch block
//          e.printStackTrace();
//      }


//      session.setAttribute("login",vo);
//      return new ModelAndView("user/loginPos", "result", vo);

        
        return "naver_success";
    }
	
	@RequestMapping(value = "/viewAll", method = RequestMethod.GET)
	public String home(Locale locale, Model model) throws Exception {
		
		logger.info("get method!");
		System.out.println("get method!!");
		List<UserVO> uvo=service.viewAll();

		int num=service.viewId("정태용");
		System.out.println(num);
		model.addAttribute("viewAll", uvo);
		
		return "viewAll";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET) 
	public String loginGet(Model model, HttpServletRequest request,HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		System.out.println("session-oauth:");
		System.out.println(session.getAttribute("oauth"));
		
		if(session.getAttribute("login") != null ) {
			Object dest=session.getAttribute("dest");
			session.removeAttribute("dest");
			String redirectUrl=(dest!=null? (String)dest:"/user/main");
			
				
				
			System.out.println("session is not null");
			return "redirect:"+redirectUrl;
		}
		
		try {
			String naverAuthUrl = naverLoginBO.getAuthorizationUrl(session);
	        
	        //https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=sE***************&
	        //redirect_uri=http%3A%2F%2F211.63.89.90%3A8090%2Flogin_project%2Fcallback&state=e68c269c-5ba9-4c31-85da-54c16c658125
	        System.out.println("네이버:" + naverAuthUrl);
	        
	        //네이버 
	        model.addAttribute("url", naverAuthUrl);
	        
			KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
			generator.initialize(1024);
			KeyPair keyPair = generator.genKeyPair();
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			PublicKey publicKey = keyPair.getPublic();
			PrivateKey privateKey = keyPair.getPrivate();
			
			session.setAttribute("RSA_WEB_Key", privateKey);
			RSAPublicKeySpec publicSpec = (RSAPublicKeySpec) keyFactory.getKeySpec(publicKey, RSAPublicKeySpec.class);
			String publicKeyModulus = publicSpec.getModulus().toString(16);
			String publicKeyExponent = publicSpec.getPublicExponent().toString(16);
			System.out.println(publicKeyModulus);
			System.out.println(publicKeyExponent);
			model.addAttribute("RSAModulus",publicKeyModulus);
			model.addAttribute("RSAExponent", publicKeyExponent);
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		logger.info("get login!");
		System.out.println("get login!!");

		return "login";
	}

	public String decryptRsa(PrivateKey privateKey, String securedValue) {
		String decryptedValue = "";
		 try{
			Cipher cipher = Cipher.getInstance("RSA");
		   /**
			* 암호화 된 값은 byte 배열이다.
			* 이를 문자열 폼으로 전송하기 위해 16진 문자열(hex)로 변경한다. 
			* 서버측에서도 값을 받을 때 hex 문자열을 받아서 이를 다시 byte 배열로 바꾼 뒤에 복호화 과정을 수행한다.
			*/
			byte[] encryptedBytes = hexToByteArray(securedValue);
			cipher.init(Cipher.DECRYPT_MODE, privateKey);
			byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
			decryptedValue = new String(decryptedBytes, "utf-8"); // 문자 인코딩 주의.
		 }catch(Exception e)
		 {
			 logger.info("decryptRsa Exception Error : "+e.getMessage());
		 }
			return decryptedValue;
	}
	public static byte[] hexToByteArray(String hex) {
		if (hex == null || hex.length() % 2 != 0) {
			return new byte[]{};
		}
	 
		byte[] bytes = new byte[hex.length() / 2];
		for (int i = 0; i < hex.length(); i += 2) {
			byte value = (byte)Integer.parseInt(hex.substring(i, i + 2), 16);
			bytes[(int) Math.floor(i / 2)] = value;
		}
		return bytes;
	}
	
	private String getIp(HttpServletRequest request) {
		 
        String ip = request.getHeader("X-Forwarded-For");
 
        logger.info(">>>> X-FORWARDED-FOR : " + ip);
 
        if (ip == null) {
            ip = request.getHeader("Proxy-Client-IP");
            logger.info(">>>> Proxy-Client-IP : " + ip);
        }
        if (ip == null) {
            ip = request.getHeader("WL-Proxy-Client-IP"); // 웹로직
            logger.info(">>>> WL-Proxy-Client-IP : " + ip);
        }
        if (ip == null) {
            ip = request.getHeader("HTTP_CLIENT_IP");
            logger.info(">>>> HTTP_CLIENT_IP : " + ip);
        }
        if (ip == null) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
            logger.info(">>>> HTTP_X_FORWARDED_FOR : " + ip);
        }
        if (ip == null) {
            ip = request.getRemoteAddr();
        }
        
        logger.info(">>>> Result : IP Address : "+ip);
 
        return ip;
 
    }
	
	private String getBrowser(HttpServletRequest request) {
		 
		String agent = request.getHeader("User-Agent");
		String browser="";
		if (agent != null) {
		   if (agent.indexOf("Trident") > -1) {
			   browser = "MSIE";
		   } else if (agent.indexOf("Chrome") > -1) {
			   browser = "Chrome";
		   } else if (agent.indexOf("Opera") > -1) {
			   browser = "Opera";
		   }
		}
		
		return browser;
    }
	
	private String getMobile(HttpServletRequest request) {
		 
		String agent = request.getHeader("User-Agent");
		String browser="";
		if (agent != null) {
			if (agent.indexOf("iPhone") > -1 && agent.indexOf("Mobile") > -1) {
				browser = "iPhone";
		   } else if (agent.indexOf("Android") > -1 && agent.indexOf("Mobile") > -1) {
			   browser = "Android";
		   }
		}
		
		return browser;
    }
	
	private String getOS(HttpServletRequest request) {
		 
		String agent = request.getHeader("User-Agent");

		String os="";
		if(agent.indexOf("NT 6.0") != -1) os = "Win0dows Vista/Server 2008";
		else if(agent.indexOf("NT 5.2") != -1) os = "Windows Server 2003";
		else if(agent.indexOf("NT 5.1") != -1) os = "Windows XP";
		else if(agent.indexOf("NT 5.0") != -1) os = "Windows 2000";
		else if(agent.indexOf("NT 6.2") != -1) os = "Windows 8";
		else if(agent.indexOf("NT 10.0") != -1) os = "Windows 10";
		else if(agent.indexOf("NT") != -1) os = "Windows NT";
		else if(agent.indexOf("9x 4.90") != -1) os = "Windows Me";
		else if(agent.indexOf("98") != -1) os = "Windows 98";
		else if(agent.indexOf("95") != -1) os = "Windows 95";
		else if(agent.indexOf("Win16") != -1) os = "Windows 3.x";
		else if(agent.indexOf("Windows") != -1) os = "Windows";
		else if(agent.indexOf("Linux") != -1) os = "Linux";
		else if(agent.indexOf("Macintosh") != -1) os = "Macintosh";
		else os = ""; 
		return os;
    }
	
	@RequestMapping(value = "/loginPost", method = RequestMethod.POST)
	public String loginPost(LoginDTO dto, HttpServletRequest request, Model model)throws Exception {
		
		logger.info("post login!");
		System.out.println("post login!!");
		System.out.println(dto);
		
		String rsaId = dto.getId();
		String rsaPw = dto.getsPasswd();
		HttpSession session = request.getSession();
		
		PrivateKey privateKey = (PrivateKey) session.getAttribute("RSA_WEB_Key");
		
		if(privateKey==null) {
			session.setAttribute("privateKey", "false");
		}
		
		//암호화처리된 사용자계정정보를 복호화 처리한다.
		String decrypId = decryptRsa(privateKey, rsaId);
		String decrypPw = decryptRsa(privateKey, rsaPw);
		dto.setId(decrypId);
		dto.setsPasswd(decrypPw);
		System.out.println(dto);
		UserVO uvo=service.login(dto.getId());
		System.out.println("uvo");
		System.out.println(uvo);
		System.out.println(dto);
		if(uvo!=null) {
			boolean pwCheck = standardPasswordEncoder.matches(dto.getsPasswd(), uvo.getsPasswd());
			if(pwCheck) {
				System.out.println("user-agent:");
				System.out.println(request.getHeader("User-Agent"));
				String ip = getIp(request);
				String browser = getBrowser(request);
				String isMobile = getMobile(request);
				String os = getOS(request);
				System.out.println("ip:");
				System.out.println(ip);
				System.out.println("browser:");
				System.out.println(browser);
				System.out.println("isMobile:");
				System.out.println(isMobile);
				System.out.println("os:");
				System.out.println(os);
				
				Date now = new Date();
				
				uvo.setLastLogin(now);
				LoginHistoryVO lvo= new LoginHistoryVO(); 
				lvo.setAccntId(uvo.getAccntId());
				lvo.setBrowser(browser);
				lvo.setIp(ip);
				lvo.setIsMobile(isMobile);
				lvo.setLoginDate(now);
				lvo.setOs(os);
				System.out.println(uvo);
				System.out.println(lvo);
				//uvo.setLastLogin(null);
				service.loginLog(lvo, uvo);
				
				model.addAttribute("userVO",uvo);
				//request.setAttribute("userVO", data);
			}
		}
		return "login_success";
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request,HttpServletResponse response, HttpSession session) throws Exception {
		
		logger.info("get method!");
		System.out.println("get logout!!");
		
		Object obj = session.getAttribute("login");
		
		if(obj != null) {
			System.out.println("세션종료");
			//UserVO vo = (UserVO) obj;
			session.removeAttribute("login");
			session.invalidate();
		}
		return "redirect:/user/main";
	}
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(Model model) throws Exception {
		
		logger.info("get index!");
		
		return "index";
	}
	
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String main(Locale locale, Model model) throws Exception {
		
		logger.info("get main!");
		return "main";
	}
	
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signup(Locale locale, Model model) throws Exception {
		
		logger.info("get signup!");
		
		// rsa를 인터셉터에 처리할 지 컨트롤러에 처리할지 고민해보자.
		return "signup";
	}
	
	@ResponseBody
	@RequestMapping(value = "/idCheck/{id}", method = RequestMethod.GET)
	public Integer idCheck(@PathVariable("id") String id) throws Exception {
		System.out.println("get idcheck");
		int cnt=service.idCheck(id);
		return cnt;
	}
	
	@ResponseBody
	@RequestMapping(value = "/nicknameCheck/{nickname}", method = RequestMethod.GET)
	public Integer nicknameCheck(@PathVariable("nickname") String nickname) throws Exception {
		System.out.println("get nicknamecheck");
		int cnt=service.nicknameCheck(nickname);
		System.out.println(nickname);
		return cnt;
	}
	
	@ResponseBody
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public int signup(@RequestBody UserVO uvo, HttpServletResponse response, Model model) throws Exception {
		System.out.println("post signup");
		System.out.println(uvo);
		
		
		String encodedPw = standardPasswordEncoder.encode(uvo.getsPasswd());
		uvo.setsPasswd(encodedPw);
		System.out.println("encodedPw: "+encodedPw);
		int ret = service.signup(uvo);
		System.out.println("ret: "+ret);
		return ret;

	}
	
	@ResponseBody
	@RequestMapping(value = "/naverSignup", method = RequestMethod.POST)
	public int naverSignup(@RequestBody UserVO uvo,HttpServletRequest request, HttpSession session, Model model) throws Exception {
		
		System.out.println("post naverSignup");
		System.out.println(uvo);
	
		Date now = new Date();
		uvo.setLastLogin(now);
		
		LoginHistoryVO lvo= new LoginHistoryVO(); 
		String ip = getIp(request);
		String browser = getBrowser(request);
		String isMobile = getMobile(request);
		String os = getOS(request);
		lvo.setBrowser(browser);
		lvo.setIp(ip);
		lvo.setIsMobile(isMobile);
		lvo.setLoginDate(now);
		lvo.setOs(os);
		
		int ret =service.naverSignup(lvo, uvo);
		request.setAttribute("userVO", uvo);

		return ret;
		
	}
	
}
