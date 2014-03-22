package poc.backend.dao;

import java.util.Date;

import poc.backend.dao.GenericDAO.SortOrder;
import poc.backend.entity.Text;

public class TextDAO {

	static private GenericDAO<Text> delegate = new GenericDAO<Text>("account", Text.class){

		public void initialize() throws Exception{
			ensureIndex("accountId", SortOrder.ASCENDING);
		}

	};
	
	public static boolean saveOrUpdate(Text text) {
		if (text.id == null) {
			text.creationDate = new Date();
		} else {
			text.modificationDate = new Date();
		}
		return delegate.saveOrUpdate(text);
	}
	
}
