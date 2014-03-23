(function(Readily){

	Readily.Navigation.mkRoute("shortstory_single/{id}", "#shortstory_single", "shortstory_single_tmpl.html", function(domId, template, args) {
		
		var id = args[0];
		console.log("id:", id);
		// $(domId).html("Please wait...");
		Readily.Connection.findtext(id, function(result) {
			console.log("data", result);
			$(domId).html(template(result.data));			
		});
		
		
		
	});

})(window.Readily);