package emoney.board.service;

import java.util.Date;
import java.util.List;

import emoney.board.domain.BoardFileVO;
import emoney.board.domain.BoardVO;
import emoney.board.domain.Criteria;

public interface BoardService {
	public int writeBoard(BoardVO bvo) throws Exception;
	public void writeBoard(BoardVO bvo, int[] flId) throws Exception;
	public List<BoardVO> listPage(Criteria cri) throws Exception;
	public int listCount() throws Exception;
	public BoardVO readBoard(int brdId) throws Exception;
	public void removeBoard(int brdId) throws Exception;
	public int modifyBoard(BoardVO bvo) throws Exception;
	public void modifyBoard(BoardVO bvo, int[] flId) throws Exception;
	public BoardVO getBoard(int brdId) throws Exception;
	public void uploadImg(BoardFileVO bfvo) throws Exception;
	public void removeTemp() throws Exception;
}
