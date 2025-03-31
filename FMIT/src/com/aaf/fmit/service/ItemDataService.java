/**
 * @author Chris McDermit
 */
package com.aaf.fmit.service;

import java.util.List;

import com.aaf.fmit.dao.CommentDAO;
import com.aaf.fmit.dao.CommentDO;
import com.aaf.fmit.dao.ItemDAO;
import com.aaf.fmit.dao.ItemDO;
import com.aaf.fmit.dao.MarketXRefDAO;
import com.aaf.fmit.dao.PhotoDAO;
import com.aaf.fmit.dao.PhotoDO;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The service layer for an item.
 * 
 * @author Chris McDermit
 */
public class ItemDataService extends GenericServiceImpl<ItemDO, Integer> {

	private static final Logger logger = LogManager.getLogger(ItemDataService.class);
	private final CommentDAO commentDAO;
	private final MarketXRefDAO marketXRefDAO;
	private final PhotoDAO photoDAO;
	
	/**
	 * Constructs an ItemDataService
	 *  
	 * @param dao the Item dao
	 * @param cDao the Comment dao
	 * @param xDao the MarketXRef dao
	 * @param pDao the Photo dao
	 */
	public ItemDataService(ItemDAO dao, CommentDAO cDao, MarketXRefDAO xDao, PhotoDAO pDao) {
		super(dao);
		logger.trace("Creating a new item data service.");
		this.commentDAO = cDao;
		this.marketXRefDAO = xDao;
		this.photoDAO = pDao;
	}
	
	
	public ItemDO retrieve(Integer id) {
		ItemDO item = super.retrieve(id);
		CommentDO comment = commentDAO.retrieve(item.getComment().getCommentRefId());
		List<PhotoDO> photos = null;
		PhotoDO photo = photoDAO.retrieve(id);
		item.setComment(comment);
		item.setPhotoList(photos);
		return item;
	}
}
