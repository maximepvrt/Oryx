(function(Readily){

	Readily.Navigation.mkRoute("shortstory_single", "#shortstory_single", "shortstory_single_tmpl.html", function(domId, template) {
		$(domId).html(template());
		
		$(domId).find("form").submit(function(event) {
			var idBook = $(domId).find("input[name$='_idBook']").val();
			console.log("idBook:", idBook);
			
			Readily.Connection.findtext(idBook, function(res) {
				var context = res.message;
				console.log("message:", context);
				var html    = template(context);
				console.log("response:", res);
			});				
			
			return false;
		});
		
	});

})(window.Readily);