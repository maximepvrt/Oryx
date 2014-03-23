(function(Readily){

	Readily.Navigation.mkRoute("shortstory_search", "#shortstory_search", "shortstory_search_tmpl.html", function(domId, template) {
		$(domId).html(template());
		
		$(domId).find("form").submit(function(event) {
			var timetoread = $(domId).find("select[name$='timetoread']").val();
			var categ = $(domId).find("select[name$='categorie']").val();
			Readily.Connection.findlist(timetoread, categ, function(res) {
				console.log("publish res", res);
				
				Readily.Navigation.loadTemplate("shortstory_result_tmpl.html", function(template) {
					var pageContent = template({list:res.data});
					$(domId).html(pageContent);
				});
				
			})
			
			return false;
		});

		
	});

})(window.Readily);