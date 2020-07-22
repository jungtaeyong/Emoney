package emoney.board.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import emoney.board.domain.BoardFileVO;
import emoney.board.domain.BoardVO;
import emoney.board.domain.Criteria;
import emoney.board.domain.PageMaker;
import emoney.board.service.BoardService;
import emoney.board.util.UploadFileUtils;
import emoney.user.domain.UserVO;

@Controller
@RequestMapping("/board/**")
public class BoardController {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Inject BoardService service;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Criteria cri, Model model) throws Exception {
		
		logger.info("get list!");
		System.out.println("get list!!");
		
		List<BoardVO> list = service.listPage(cri);
		model.addAttribute("list",list);
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(service.listCount());
		model.addAttribute("pageMaker", pageMaker);
		
		return "board_list";
	}
	
	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public String writeBoard(Locale locale, Model model) {
		
		logger.info("get write!");
		System.out.println("get write!!");
	
		return "board_write";
	}
	
	@RequestMapping(value = "/readPage", method = RequestMethod.GET)
	public String readPage(@RequestParam("brdId") int brdId, @ModelAttribute("cri") Criteria cri, Model model) throws Exception {
		
		logger.info("get readPage!");
		System.out.println("get readPage!!");
		System.out.println(cri);
		
		// �ּ�ó���ص� cri�� ���� ������?
		// -> �Ķ���Ϳ� ModelAttribute("cri") Critera cri�� �־ �ε�.
		// model.addAttribute("cri",cri);
		
		//�ƹ��� �̸� ���� ������ ��ȯ�ϴ� ��ü�� ù���� �ҹ����ؼ� ��ȯ��. ���⼭�� boardVO
		model.addAttribute(service.readBoard(brdId));
		
		return "board_read";
	}
	
	@RequestMapping(value = "/modifyPage", method = RequestMethod.GET)
	public String modifyPage(@RequestParam("brdId") int brdId, @ModelAttribute("cri") Criteria cri, Model model) throws Exception {
		
		logger.info("get modifyPage!");
		System.out.println("get modifyPage!!");
		System.out.println(cri);
		
		model.addAttribute(service.getBoard(brdId));
		
		return "board_modify";
	}
	
	
	@RequestMapping(value = "/writePost", method = RequestMethod.POST)
	public String writePost(BoardVO bvo,@RequestParam(value="flId", required=false) int[] flId, RedirectAttributes rttr, HttpSession session)throws Exception {
		
		logger.info("post writePost!");
		System.out.println("write post!!");
		System.out.println(session.getAttribute("login"));
		UserVO uvo = (UserVO) session.getAttribute("login");
		
		Date now = new Date();
		bvo.setWriter(uvo.getNickname());
		bvo.setViewCnt(0);
		bvo.setRegDate(now);
		System.out.println(uvo);
		System.out.println(bvo);
		
		if(flId!=null) {
			service.writeBoard(bvo, flId);
			System.out.println("done");
		}else {
			service.writeBoard(bvo);
		}
		
		rttr.addFlashAttribute("msg","success");
		
		return "redirect:/board/list";
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/removePost", method = RequestMethod.POST)
	public ResponseEntity<String> removePage(@RequestBody BoardVO bvo, Criteria cri, Model model)throws Exception {
		
		
		logger.info("post removePage!");
		System.out.println("post removePage!!");
		int brdId= bvo.getBrdId();
		System.out.println("brdid: "+brdId);
		
		service.removeBoard(brdId);
		
		
		
//		List<BoardFileVO> listbfvo = service.listFile(brdId);
//		
//		System.out.println("listbfvo:");
//		if(!listbfvo.isEmpty()) {
//			service.removeBoard(brdId, listbfvo);
//		}else {
//			service.removeBoard(brdId);
//		}
//		System.out.println(listbfvo);
		
		
		return new ResponseEntity<String>("deleted", HttpStatus.OK);
	}
	
	@RequestMapping(value = "/modifyPost", method = RequestMethod.POST)
	public String modifyPost(BoardVO bvo, @RequestParam(value="flId", required=false) int[] flId, Criteria cri, RedirectAttributes rttr)throws Exception {
		
		logger.info("post modifyPost!");
		System.out.println("post modifyPost!!");
		Date now = new Date();
		System.out.println(bvo);
		bvo.setModDate(now);
		
		if(flId!=null) {
			service.modifyBoard(bvo, flId);
		}else {
			service.modifyBoard(bvo);
		}
		
		rttr.addAttribute("page", cri.getPage());
		rttr.addAttribute("perPageNum", cri.getPerPageNum());
		rttr.addAttribute("brdId", bvo.getBrdId());
		rttr.addFlashAttribute("msg","success");
		
		return "redirect:/board/readPage";
	}
	
	@ResponseBody
	@RequestMapping(value="/naverMultiImgUploader", method = RequestMethod.POST)
	public JsonNode naverMultiImgUploader(HttpServletRequest request, HttpServletResponse response)throws Exception{
		 
		 BoardFileVO bfvo = new BoardFileVO(); 
		 
		 //��������
		 String sFileInfo = "";
		 //���ϸ��� �޴´� - �Ϲ� �������ϸ�
		 String filename = request.getHeader("file-name");
		 bfvo.setOriginName(filename);
		 //���� Ȯ����
		 String filename_ext = filename.substring(filename.lastIndexOf(".")+1);
		 //Ȯ���ڸ��ҹ��ڷ� ����
		 filename_ext = filename_ext.toLowerCase();
		 bfvo.setExtension(filename_ext);
		 //�̹��� ���� �迭����
		 String[] allow_file = {"jpg","png","bmp","gif"};
	
		 //�����鼭 Ȯ���ڰ� �̹������� 
		 int cnt = 0;
		 for(int i=0; i<allow_file.length; i++) {
		 	if(filename_ext.equals(allow_file[i])){
		 		cnt++;
		 	}
		 }
		 //�̹����� �ƴ�
		 if(cnt == 0) {
			 PrintWriter print = response.getWriter();
			 print.print("NOTALLOW_"+filename);
			 print.flush();
			 print.close();
		 } else {
			 //�̹����̹Ƿ� �ű� ���Ϸ� ���丮 ���� �� ���ε�	
			 //���� �⺻���
			 String dftFilePath = request.getSession().getServletContext().getRealPath("/");
			 
			 Calendar cal = Calendar.getInstance();
			 String yearPath = File.separator+cal.get(Calendar.YEAR);
			 String monthPath = yearPath+ File.separator + new DecimalFormat("00").format(cal.get(Calendar.MONTH)+1); 
			 String datePath = monthPath+ File.separator + new DecimalFormat("00").format(cal.get(Calendar.DATE)); 
			 
			 System.out.println("datepath:");
			 System.out.println(datePath);
			 
			 //���� �⺻��� _ �󼼰��
			 String filePath = dftFilePath + "resources" + File.separator + "editor" + File.separator +"multiupload" + datePath + File.separator;
			 bfvo.setPath(filePath);
			 File file = new File(filePath);
			 if(!file.exists()) {
			 	file.mkdirs();
			 }
			 System.out.println("filepath:");
			 System.out.println(filePath);
			 String realFileNm ="";
			 SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
			 String today= formatter.format(new java.util.Date());
			 realFileNm = today+ UUID.randomUUID().toString() + filename.substring(filename.lastIndexOf("."));
			 bfvo.setUuidName(realFileNm);
			 String rlFileNm = filePath + realFileNm;
			 
			 ///////////////// ������ ���Ͼ��� ///////////////// 
			 InputStream is = request.getInputStream();
			 OutputStream os=new FileOutputStream(rlFileNm);
			 int numRead;
			 System.out.println("file-size:");
			 System.out.println(request.getHeader("file-size"));
			 bfvo.setByteSize(Integer.parseInt(request.getHeader("file-size")));
			 byte b[] = new byte[Integer.parseInt(request.getHeader("file-size"))];
			 while((numRead = is.read(b,0,b.length)) != -1){
			 os.write(b,0,numRead);
		 }
		 if(is != null) {
		 	is.close();
		 }
		 os.flush();
		 os.close();
		 ///////////////// ������ ���Ͼ��� /////////////////
		 System.out.println("sFileInfo:");
		 // ���� ���
		 System.out.println(sFileInfo);
		 sFileInfo += "&bNewLine=true";
		 // img �±��� title �Ӽ��� �������ϸ����� ��������ֱ� ����
		 System.out.println(sFileInfo);
		 sFileInfo += "&sFileName="+ filename;
		 
		 System.out.println(sFileInfo);
		 
		 yearPath="/"+cal.get(Calendar.YEAR);
		 monthPath=yearPath+"/"+new DecimalFormat("00").format(cal.get(Calendar.MONTH)+1);
		 datePath=monthPath+"/"+new DecimalFormat("00").format(cal.get(Calendar.DATE)); 
		 
		 String tempstr=UploadFileUtils.makeThumbnail(filePath, realFileNm);
		 
		 sFileInfo += "&sFileURL="+"/resources/editor/multiupload"+datePath+"/"+tempstr;
		 
		 Date now = new Date();
		 bfvo.setRegDate(now);
		 service.uploadImg(bfvo);
		 
		 System.out.println(sFileInfo);
		 System.out.println("tempstr");
		 System.out.println(tempstr);
		 System.out.println(sFileInfo);
		 
		 }
		 // **����
		 Map<String, String> ret = new HashMap();
		 ObjectMapper mapper = new ObjectMapper();
		 ret.put("sFileInfo", sFileInfo);
		 ret.put("fileId", ""+bfvo.getFlId());
		 JsonNode node = mapper.convertValue(ret, JsonNode.class);
		 
		 return node;
	 }
}
