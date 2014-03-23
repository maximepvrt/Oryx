(function(Readily){
	
	Readily.Navigation.mkRoute("signup", "#signup", "signup_tmpl.html", function(domId, template) {
		$(domId).html(template());
		
		var html = $(domId).html();
		
		
		function setError(errmsg) {
			$(domId).find(".formAlerts").removeClass("hidden");
			$(domId).find(".formAlerts").find(".alertContent").html(errmsg);
		}
		
		$(domId).find("form").submit(function(event) {
			$(domId).find(".formAlerts").addClass("hidden");
			var error = "";
			
			var login = $(domId).find("input[name$='login']").val();
			var pwd = $(domId).find("input[name$='password']").val();
			var repwd = $(domId).find("input[name$='repassword']").val();
			var byear = $(domId).find("select[name$='birthyear']").val();
			var gender = $(domId).find("input[name$='gender']").val();
			
			if (login == "") {
				setError("Vous devez donner un nom d'utilisateur");
				return false;
			}
			
			if (pwd.length < 6) {
				setError("Votre mot de passe doit faire au moins 6 caractères.");
				return false;				
			}
			
			if (pwd != repwd) {
				setError("Le mot de passe n'est pas correctement retapé.");
				return false;								
			}
			
			Readily.Connection.newAccount(login, pwd, parseInt(byear), gender, function(res) {
				console.log("new Account res", res);
				if (res.status == 'OK') {
					console.log("OK");
					if (Readily.Navigation.postLogin) {
						var target = Readily.Navigation.postLogin;
						Readily.Navigation.postLogin = undefined;
						Readily.Navigation.go(target);
					} else {
						Readily.Navigation.go("#home");						
					}
				} else {
					setError("Votre nom d'utilisateur est déjà pris. Merci d'en choisir un autre.")
				}
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