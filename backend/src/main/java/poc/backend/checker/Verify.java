package poc.backend.checker;

import poc.backend.entity.Account;

public class Verify {

	public static Account verifyAccount(Account account){
		if(!account.login.isEmpty() && account.login.length() > 1){
			account.login = account.login.trim();
		} else {return null;}
		if(!account.password.isEmpty() && account.password.length() > 1 && account.password.length() < 17){
			account.password = account.password.trim();
		} else {return null;}
		if(account.birthyear.isEmpty() && account.birthyear.trim().length() != 4){
			return null;
		}
		if(""+account.gender == null ){
			return null;
		}
		return account;
	}
}
