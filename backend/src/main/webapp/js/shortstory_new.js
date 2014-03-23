(function(Readily){

	Readily.Navigation.mkRoute("shortstory_new", "#shortstory_new", "shortstory_new_tmpl.html", function(domId, template) {
		$(domId).html(template());
	});

})(window.Readily);