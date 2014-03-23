window.Readily = window.Readily || {};

(function(Readily){
	
	var base = "/webapi";
	
	
	function Callback(status, data) {
		this.status = status;
		this.data = data;
	}
	
	function httpCall(verb, url, param, callback) {
		var params = {
				dataType: "json",
				type: verb,
	            url: base + url,
	            data: param,
	            contentType: "application/json",
	            success: function(data) {
	            	if (data.status == "OK")
	            		callback(new Callback("OK",data.message));
					else
						callback(new Callback("KO",data));
	            },
	            error: function(jqXHR, status, error) {
	            	console.log("Error:", status, error);
	            	 callback(new Callback("KO",status));
	            }				
		};
		$.ajax(params);
	}
	
	function Connection() {
		
		this.login = function(login, password, callback) {
			// httpCall("POST", "/account/login", {login:login, password:password}, callback);
			httpCall("GET", "/account/find", {login:login, password:password}, callback);
		};
		
		this.newAccount = function(login, password, birthyear, gender, callback) {
			var data = {login:login, password:password, birthyear:birthyear, gender:gender};
			httpCall("PUT", "/account/newaccount", JSON.stringify(data) ,callback);
		};
		
		this.publish = function(title, categ, type, summary, content, callback) {
			var data = {title:title, category:categ, type:type, summary:summary, content:content};
			httpCall("PUT", "/shortstory/putstory", JSON.stringify(data), callback);
		};
		
		this.findtext = function(id, callback) {
			httpCall("GET", "/shortstory/findtext", {id:id}, callback);
		};
		
		this.findlist = function(timetoRead, categ, callback) {
			var data = {timetoRead:timetoRead, category:categ};
			httpCall("POST", "/shortstory/findlist", JSON.stringify(data), callback);
		};
	}

	Readily.Connection = new Connection();
	
})(window.Readily);
