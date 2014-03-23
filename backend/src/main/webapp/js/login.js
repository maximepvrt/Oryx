(function(Readily){

	Readily.Navigation.mkRoute("/login", "#login", "login_tmpl.html", function(domId, template) {
		$(domId).html(template());

		$(domId).find("form").submit(function(event) {
			var login = $(domId).find("input[name$='_username']").val();
			var pwd = $(domId).find("input[name$='_password']").val();
			console.log("login:", login);
			
			Readily.Connection.login(login, pwd, function(res) {
				console.log("response:", res);
				
				if (Readily.Navigation.postLogin) {
					var target = Readily.Navigation.postLogin;
					Readily.Navigation.postLogin = undefined;
					Readily.Navigation.go(target);
				}
			});				
			
			return false;
		});
		
	});

})(window.Readily);
