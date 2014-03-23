(function(Readily){

	Readily.Navigation.mkRoute("shortstory_search", "#shortstory_search", "shortstory_search_tmpl.html", function(domId, template) {
		$(domId).html(template());
		
		$(domId).find("form").submit(function(event) {
			var timetoread = $(domId).find("input[name$='timetoread']").val();
			var categ = $(domId).find("input[name$='categorie']").val();
			Readily.Connection.findlist(timetoread, categ, function(res) {
				console.log("publish res", res);
			})
			
			return false;
		});

		
	});

})(window.Readily);