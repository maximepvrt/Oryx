package poc.backend.dao;

import java.util.Date;

import poc.backend.dao.GenericDAO.SortOrder;
import poc.backend.dto.TextDto;
import poc.backend.entity.Text;

public class TextDao {

	static private GenericDAO<Text> delegate = new GenericDAO<Text>("account", Text.class){

		public void initialize() throws Exception{
			ensureIndex("accountId", SortOrder.ASCENDING);
		}
	};
	
	public static boolean saveOrUpdate(TextDto textDto, String account) {
		Text text = null;
		if (text.id == null) {
			text.creationDate = new Date();
		} else {
			text.modificationDate = new Date();
		}
		text.title = textDto.title;
		text.summary = textDto.summary;
		text.categorie = textDto.categorie;
		text.type = textDto.type;
		text.mood = textDto.mood;
		text.content = text.content;
		text.accountId = account;
		text.timeToRead = textDto.timeToRead;
		return delegate.saveOrUpdate(text);
	}
	
}
