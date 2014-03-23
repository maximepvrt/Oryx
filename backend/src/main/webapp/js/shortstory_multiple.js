(function(Readily){

	Readily.Navigation.mkRoute("shortstory_multiple", "#shortstory_multiple", "shortstory_multiple_tmpl.html", function(domId, template) {
		$(domId).html(template());
		
		$(domId).find("a").click(function(event) {
			var idBook = $(this).attr("idbook");
			console.log("idBook:", idBook);
			
			Readily.Connection.findtext(idBook, function(res) {
				console.log("response:", res);
			});				
			
			return false;
		});
		
	});

})(window.Readily);