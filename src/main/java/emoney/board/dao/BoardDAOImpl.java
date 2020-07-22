package emoney.board.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import emoney.board.domain.BoardFileVO;
import emoney.board.domain.BoardVO;
import emoney.board.domain.Criteria;

@Repository
public class BoardDAOImpl implements BoardDAO {
	
	@Inject SqlSession sql;
	
	private static final String ns = "emoney.board.mapper.boardMapper";
	
	@Override
	public int writeBoard(BoardVO bvo) throws Exception{
		return sql.insert(ns+".writeBoard", bvo);
	}
	
	@Override
	public List<BoardVO> listPage(Criteria cri) throws Exception{
		return sql.selectList(ns+".listPage", cri);
	}
	
	@Override
	public int listCount() throws Exception{
		return sql.selectOne(ns+".listCount");
	}
	
	@Override
	public BoardVO getBoard(int brdId) throws Exception{
		return sql.selectOne(ns+".getBoard", brdId);
	}
	
	@Override
	public void removeBoard(int brdId) throws Exception{
		sql.delete(ns+".removeBoard", brdId);
		return;
	}
	
	@Override
	public int modifyBoard(BoardVO bvo) throws Exception{
		return sql.update(ns+".modifyBoard",bvo);
	}
	
	@Override
	public void updateViewCnt(int brdId) throws Exception{
		sql.update(ns+".updateViewCnt", brdId);
		return;
	}
	
	@Override
	public void uploadImg(BoardFileVO bfvo) throws Exception{
		sql.update(ns+".uploadImg", bfvo);
		return;
	}
	
	@Override
	public void updateFileBrdId(int brdId, int[] flId) throws Exception{
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("flId", flId);
		paramMap.put("brdId", brdId);
		sql.update(ns+".updateFileBrdId", paramMap);
		return;
	}
	
	@Override
	public List<BoardFileVO> listFile(int brdId) throws Exception{
		return sql.selectList(ns+".listFile", brdId);
	}
	
	@Override
	public void removeBoardFile(List<BoardFileVO> bfvo) throws Exception{
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("bfvo", bfvo);
		sql.delete(ns+".removeBoardFile", paramMap);
		return;
	}
	
	@Override
	public void removeTemp(Date nowDate) throws Exception{
		sql.delete(ns+".removeTemp", nowDate);
	}
	
	@Override
	public List<BoardFileVO> tempFile(Date nowDate) throws Exception{
		return sql.selectList(ns+".tempFile", nowDate);
	}
}
