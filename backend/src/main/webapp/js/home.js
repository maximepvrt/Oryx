(function(Readily){

	function home(domId, template) {
		$(domId).html(template());		
	}
	
	Readily.Navigation.mkRoute("", "#home", "home_tmpl.html", home);
	Readily.Navigation.mkRoute("/home", "#home", "home_tmpl.html", home);
	Readily.Navigation.mkRoute("home", "#home", "home_tmpl.html", home);

})(window.Readily);