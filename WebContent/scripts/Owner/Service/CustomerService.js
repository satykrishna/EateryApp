(function(){
	
	'use strict';
	angular.module('eateryApp').service('CustomerService', CustomerService);
	
	CustomerService.$inject = ['$http', '$q'];
	
	function CustomerService($http, $q){
		var cs = this;
		
		cs.get = fnGet;
		
		
		function fnGet(dataObject){
			
			var defer = $q.defer();
			var reqObject  = {
					method : 'GET',
					url : 'eatery/reserve/customer/'+dataObject

			};			
			$http(reqObject).success(function(data, status, headers, config){
				defer.resolve(data);
				
			}).error(function(error, status, headers, config){
				defer.reject(error);
			})				
			return defer.promise;			
			
		}
		
		
	}
	
	
	
})();