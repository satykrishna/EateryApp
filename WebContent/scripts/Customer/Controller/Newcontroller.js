(function(){
	
	'use strict';
	angular.module('eateryApp').controller('Newcontroller', Newcontroller);
	 
	
	Newcontroller.$inject = ['NewReservationService'];
	 
	function Newcontroller(NewReservationService){
		var nc = this;		
		nc.create = fncreate;
	    nc.ok = fnOk;
	    
	     function fncreate(){
	    	 nc.promise = NewReservationService.create(nc.res);
	         nc.promise.then(function(resp){
	        	 nc.wait = NewReservationService.wait;
	        	 nc.confirm = NewReservationService.confirm;
	        	 nc.res = resp.payLoad;
	        	 nc.code = resp.payLoad.confirmcode;
	         },
	         function(resp){
	        	 console.dir("ERROR");
	        	 console.dir(resp);
	         });	 
	    }
	    
	     
	      function fnOk(){
	         if(nc.option == 'no'){
	        	 nc.promise = NewReservationService.del(nc.res);
	        	 nc.promise.then(function(resp){
	        		 console.log("Your record is deleted");
	        	 }, function(resp){
	        		 console.dir('Your record is not deleted');
	        	 })
	         }
	    }
	    
	    
	}
	
})();