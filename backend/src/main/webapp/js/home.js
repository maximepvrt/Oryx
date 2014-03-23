(function(Readily){

	Readily.Navigation.mkRoute("home", "#home", "home_tmpl.html", function(domId, template) {
		$(domId).html(template());
	});

})(window.Readily);