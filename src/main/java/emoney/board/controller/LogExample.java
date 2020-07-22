package emoney.board.controller;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class LogExample {
	
	final static Logger logger = LoggerFactory.getLogger(LogExample.class);
	 
 	@RequestMapping("/log")
	@ResponseBody
	public String logExam() {
		logger.debug( "#ex1 - debug log" );
		logger.info( "#ex1 - info log" );
		logger.warn( "#ex1 - warn log" );
		logger.error( "#ex1 - error log" );
		logger.debug("¾È³ç¾î¾î¾î¾û");
		
		return "main";
	}
}
