package emoney.board.dao;

import java.util.Date;
import java.util.List;

import emoney.board.domain.BoardFileVO;
import emoney.board.domain.BoardVO;
import emoney.board.domain.Criteria;

public interface BoardDAO {
	public int writeBoard(BoardVO bvo) throws Exception;
	public List<BoardVO> listPage(Criteria cri) throws Exception;
	public int listCount() throws Exception;
	public BoardVO getBoard(int brdId) throws Exception;
	public void removeBoard(int brdId) throws Exception;
	public int modifyBoard(BoardVO bvo) throws Exception;
	public void updateViewCnt(int brdId) throws Exception;
	public void uploadImg(BoardFileVO bfvo) throws Exception;
	public void updateFileBrdId(int brdId, int[] flId) throws Exception;
	public List<BoardFileVO> listFile(int brdId) throws Exception;
	public void removeBoardFile(List<BoardFileVO> bfvo) throws Exception;
	public void removeTemp(Date nowDate) throws Exception;
	public List<BoardFileVO> tempFile(Date nowDate) throws Exception;
}
