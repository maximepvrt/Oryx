(function(Readily){
	
	Readily.Navigation.mkRoute("signup", "#signup", "signup_tmpl.html", function(domId, template) {
		$(domId).html(template());
		
		var html = $(domId).html();

		$(domId).find("form").submit(function(event) {
			var error = "";
			
			var login = $(domId).find("input[name$='login']").val();
			var pwd = $(domId).find("input[name$='password']").val();
			var repwd = $(domId).find("input[name$='repassword']").val();
			var byear = $(domId).find("select[name$='birthyear']").val();
			var gender = $(domId).find("select[name$='gender']").val();
			
			if (login == "") {
				$(domId).find(".error-alert").html("Vous devez avoir un nom d'utilisateur");
				return false;
			}
			
			if (pwd.length < 6) {
				$(domId).find(".error-alert").html("Votre mot de passe doit faire au moins 6 caractères.");
				return false;				
			}
			
			if (pwd != repwd) {
				$(domId).find(".error-alert").html("Le mot de passe n'est pas correctement retapé.");
				return false;								
			}
			
			Readily.Connection.newAccount(login, pwd, parseInt(byear), gender, function(res) {
				console.log("new Account res", res);
			})
			
			return false;
		});
		
//		$(domId).find("button").click(function(event) {
//			event.preventDefault();
//			// Submit en ajax/json
//			console.log("SUBMIT");
//		});
	});

})(window.Readily);