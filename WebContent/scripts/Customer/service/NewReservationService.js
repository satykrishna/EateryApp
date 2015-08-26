(function(){
	
	'use strict';

	angular.module('eateryApp').service('NewReservationService', NewReservationService);
	NewReservationService.$inject = ['$http' , '$q'];

	function NewReservationService($http, $q){

		var nrs  = this;

		nrs.create = function(dataObject){
			var defer = $q.defer();
			var reqObject  = {
					method : 'POST',
					url : 'eatery/reserve/create',
					data: dataObject
			};			

			$http(reqObject).success(function(data, status, headers, config){
				if(data.payLoad.reservestatus == 'C'){
					nrs.confirm = true;
					defer.resolve(data);
				}
				else{
					nrs.wait = true;
					defer.resolve(data);
				}
			}).error(function(error, status, headers, config){
				defer.reject(error);
			})				
			return defer.promise;
		}	


		nrs.del = function(dataObject){
			var defer = $q.defer();
			var reqObject  = {
					method : 'POST',
					url : 'eatery/reserve/delete',
					data: dataObject
			};			
			$http(reqObject).success(function(data, status, headers, config){
				console.log("SUCCESSFULLY DELETED");
				console.dir(data);
				defer.resolve(data);
			}).error(function(error, status, headers, config){
				console.log("FAILURE");
				defer.reject(error);
			})				
			return defer.promise;
		}	





		return nrs;
	}

})();


