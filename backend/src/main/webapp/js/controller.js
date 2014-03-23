window.Readily = window.Readily || {};

(function(Readily){

	Handlebars.registerHelper('list', function(items, options) {
		  var out = "<ul>";

		  for(var i=0, l=items.length; i<l; i++) {
		    out = out + "<li>" + options.fn(items[i]) + "</li>";
		  }

		  return out + "</ul>";
		});
	
	
	function installHashOnLinks($el) {
		$el.find("a").each(function(i) {
			var self = $(this);
			var nav = self.attr("href");
			if (nav && nav.substr(0,1) == '#') {
				nav = nav.substr(1);
			} 
			var external = self.attr("external");
			if (nav && !external) {
				self.click(function(event) {
					event.preventDefault();
					hasher.setHash(nav);
				});
			}
		});		
	}
	
	var externalTemplateCache = {};
	function loadExternalTemplate(file, callback) {
		if (externalTemplateCache[file]) {
			return callback(externalTemplateCache[file]);
		}
		$.ajax({
            url: file,
            success: function(data) {
            	externalTemplateCache[file] = Handlebars.compile(data);
            	callback(externalTemplateCache[file]);
            },
            error: function(data) {
            	callback();
            }
        });
	}
	
	var currentDomId = undefined;
	
	function mkRoute(route, domId, externalTemplate, callback) {
		crossroads.addRoute(route, function() {
			
			var args = Array.prototype.slice.apply(arguments);
			
			$(currentDomId).hide();
			$(currentDomId).addClass('hidden');
			
			currentDomId = domId;
			console.log("route: ", route, domId);
			if (externalTemplate) {
				loadExternalTemplate(externalTemplate, function(template) {
					callback(domId, template, args);
					installHashOnLinks($(domId));
					$(domId).show();
					$(domId).removeClass("hidden");
				});
			} else if (callback) { 
				callback(domId);
				$(domId).show();
				$(domId).removeClass("hidden");
			}
		});
	}
	
//	mkRoute("", "#home");
	

	//	mkRoute("login", "#login");
//	mkRoute("single", "#single");

	//setup crossroads
//	crossroads.addRoute('foo');
//	crossroads.addRoute('lorem/ipsum');
//	crossroads.routed.add(console.log, console); //log all routes
	 
//	mkRoute("home", "#home", "home_tmpl.html", function(domId, template) {
//		$(domId).html(template());
//	});
	
	Readily.Navigation = {
		go: function(route) {
			hasher.setHash(route);
		},
		mkRoute: function(route, domId, template, callback) {
			mkRoute(route,domId,template,callback);
		},
		loadTemplate: function(file, callback) {
			return loadExternalTemplate(file, callback)
		}
	};
	
	installHashOnLinks($("body"));
	
})(window.Readily);
