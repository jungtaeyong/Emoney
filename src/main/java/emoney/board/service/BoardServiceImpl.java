package emoney.board.service;

import java.io.File;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import emoney.board.dao.BoardDAO;
import emoney.board.domain.BoardFileVO;
import emoney.board.domain.BoardVO;
import emoney.board.domain.Criteria;



@Service
public class BoardServiceImpl implements BoardService {
	
	@Inject BoardDAO dao;
	
	
	@Override
	public int writeBoard(BoardVO bvo) throws Exception{
		return dao.writeBoard(bvo);
	}
	
	@Transactional(isolation=Isolation.READ_COMMITTED)
	@Override
	public void writeBoard(BoardVO bvo, int[] flId) throws Exception{
		dao.writeBoard(bvo);
		int brdId=bvo.getBrdId();
		dao.updateFileBrdId(brdId, flId);
		return;
	}
	
	@Override
	public List<BoardVO> listPage(Criteria cri) throws Exception{
		return dao.listPage(cri);
	}
	
	@Override
	public int listCount() throws Exception{
		return dao.listCount();
	}
	
	@Transactional(isolation=Isolation.READ_COMMITTED)
	@Override
	public BoardVO readBoard(int brdId) throws Exception{
		dao.updateViewCnt(brdId);
		return dao.getBoard(brdId);
	}
	
	@Transactional(isolation=Isolation.READ_COMMITTED)
	@Override
	public void removeBoard(int brdId) throws Exception{

		List<BoardFileVO> bfvo = dao.listFile(brdId);
		if(bfvo.isEmpty()) {
			dao.removeBoard(brdId);
		}else {
			dao.removeBoard(brdId);
			dao.removeBoardFile(bfvo);
			for(int i=0;i<bfvo.size();i++) {
				File targetOrigin = new File(bfvo.get(i).getPath()+bfvo.get(i).getUuidName());
				File targetThumbnail = new File(bfvo.get(i).getPath()+"s_"+bfvo.get(i).getUuidName());
				if(targetOrigin.exists() && targetThumbnail.exists()) {
					targetOrigin.delete();
					targetThumbnail.delete();
				}
			}
		}
		return;
	}
	
	@Override
	public int modifyBoard(BoardVO bvo) throws Exception{
		return dao.modifyBoard(bvo);
	}
	
	@Transactional(isolation=Isolation.READ_COMMITTED)
	@Override
	public void modifyBoard(BoardVO bvo, int[] flId) throws Exception{
		dao.modifyBoard(bvo);
		dao.updateFileBrdId(bvo.getBrdId(), flId);
		return;
	}
	
	@Override
	public BoardVO getBoard(int brdId) throws Exception{
		return dao.getBoard(brdId);
	}
	
	@Override
	public void uploadImg(BoardFileVO bfvo) throws Exception{
		dao.uploadImg(bfvo);
		return;
	}
	
	@Override
	public void removeTemp() throws Exception{
		Date nowDate = new Date();
		List<BoardFileVO> bfvo = dao.tempFile(nowDate);
		System.out.println(bfvo);
		if(!bfvo.isEmpty()) {
			dao.removeTemp(nowDate);
			for(int i=0;i<bfvo.size();i++) {
				File targetOrigin = new File(bfvo.get(i).getPath()+bfvo.get(i).getUuidName());
				File targetThumbnail = new File(bfvo.get(i).getPath()+"s_"+bfvo.get(i).getUuidName());
				if(targetOrigin.exists() && targetThumbnail.exists()) {
					targetOrigin.delete();
					targetThumbnail.delete();
				}
			}
		}
		return;
	}
	
}
