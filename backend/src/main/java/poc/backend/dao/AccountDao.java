package poc.backend.dao;

import poc.backend.dto.Account;

public class AccountDao {

	public static class AlreadyExists extends Exception{
		
	}
	
	static private GenericDAO<Account> delegate = new GenericDAO<Account>("account", Account.class){
		public void initialize() throws Exception{
			ensureIndex("login", SortOrder.ASCENDING);
		}
	};
	
	public static boolean create(Account account) throws AlreadyExists{
		Account exist = delegate.findOne("{login:#}", account.login);
		if(exist != null){
			throw new AlreadyExists();
		}
	
		return delegate.saveOrUpdate(account);
	}
	
	public static Account get(String login, String password){
		return delegate.findOne("{login:#, password:#}", login, password);
	}
}
