(function(Readily){

	Readily.Navigation.mkRoute("shortstory_search", "#shortstory_search", "shortstory_search_tmpl.html", function(domId, template) {
		$(domId).html(template());
		
		$(domId).find("form").submit(function(event) {
			var error = "";
			
			var title = $(domId).find("input[name$='titre']").val();
			var categ = $(domId).find("input[name$='categorie']").val();
			var summary = $(domId).find("input[name$='summary']").val();
			var content = $(domId).find("select[name$='content']").val();
			
//			if (titre == "") {
//				$(domId).find(".error-alert").html("Vous devez avoir un nom d'utilisateur");
//				return false;
//			}
//			
//			if (pwd.length < 6) {
//				$(domId).find(".error-alert").html("Votre mot de passe doit faire au moins 6 caractères.");
//				return false;				
//			}
//			
//			if (pwd != repwd) {
//				$(domId).find(".error-alert").html("Le mot de passe n'est pas correctement retapé.");
//				return false;								
//			}
			
			Readily.Connection.publish(title, categ, "", summary, content, function(res) {
				console.log("publish res", res);
			})
			
			return false;
		});

		
	});

})(window.Readily);