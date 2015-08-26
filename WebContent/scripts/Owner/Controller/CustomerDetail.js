(function(){

	'use strict';

	angular.module('eateryApp').controller('CustomerDetail', CustomerDetail);

	CustomerDetail.$inject = ['CustomerService'];

	function CustomerDetail(CustomerService){

		var cd = this;

		cd.get = fnGet;

		
		
		function fnGet(){

			cd.promise = CustomerService.get(cd.searchstr);
			cd.promise.then(function(resp){
				console.dir(resp.payLoad);
				
				cd.cus = resp.payLoad;
				console.dir(cd.cus);

			},

			function(err){
				console.dir(err);
			});

		}


	}


})();