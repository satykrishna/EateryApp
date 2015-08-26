(function(){

	'use strict';

	angular.module('eateryApp').controller('Ownercontroller', Ownercontroller);

	Ownercontroller.$inject = ['OwnerService', '$window'];
	
	function Ownercontroller(OwnerService, $window){
		var oc = this;
		oc.login = fnCheck;
		oc.success = false;
		
		
		function fnCheck(){
			var promise = OwnerService.login(oc.cred);
			promise.then(function(resp){
			
				oc.success = OwnerService.success;
				if(oc.success){
					console.log("You are about to login");
					$window.open("eateryowner.html");
				}
				
			},
			function(err){
				console.log(err);
			});	
		}
	}

})();