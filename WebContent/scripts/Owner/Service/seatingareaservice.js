(function(){

	'use strict';
	angular.module('eateryApp').service('seatingareaservice', seatingareaservice);
	seatingareaservice.$inject = ['$q', '$http'];

	function seatingareaservice($q, $http){

		var srs = this;

		srs.getDetails = function(){

							
			
			
			var defer = $q.defer();
			var reqObject  = {
					method : 'GET',
					url : 'eatery/reserve/tables/all'

			};			
			$http(reqObject).success(function(data, status, headers, config){
				console.log("SUCCESSFULLY FETCHED ALL TABLE RECORDS");
				console.dir(data);
				defer.resolve(data);
			}).error(function(error, status, headers, config){
				console.log("FAILURE TO FETCH ALL RECORDS");
				defer.reject(error);
			})				
			return defer.promise;
		}
	}	


})();