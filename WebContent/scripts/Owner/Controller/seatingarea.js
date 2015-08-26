(function(){
	'use strict';
	angular.module('eateryApp').controller('seatingarea', seatingarea);
	seatingarea.$inject = ['seatingareaservice'];
	
	function seatingarea(seatingareaservice){
		
		var vsa = this;
		vsa.details = [];
		vsa.seatingDetails = fngetSeatingDetails;
		vsa.seatingDetails();
		
		function fngetSeatingDetails(){
			vsa.promise = seatingareaservice.getDetails();
			vsa.promise.then(function(resp){
		        vsa.details = resp.payLoad;
				console.log(resp);
			},
			function (err){
				console.log(err);
			});
			
		}
	}
	
	
})();