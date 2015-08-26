(function(){

	'use strict';
	angular.module('eateryApp').service('ModifyReservationService', ModifyReservationService);
	ModifyReservationService.$inject = ['$http' , '$q'];

	function ModifyReservationService($http, $q){

		var mrs  = this;
        mrs.wait = false;
        mrs.confirm = false;
		
		
		mrs.check = function(dataObject){
			var defer = $q.defer();
			var reqObject  = {
					method : 'GET',
					url : 'eatery/reserve/check/' +dataObject,
			};			

			$http(reqObject).success(function(data, status, headers, config){
				defer.resolve(data);
			}).error(function(error, status, headers, config){
				defer.reject(error);
			})				
			return defer.promise;
		}	

		mrs.del = function(dataObject){
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

		mrs.update = function(dataObject){

			var defer = $q.defer();

			var reqObject  = {
					method : 'POST',
					url : 'eatery/reserve/modify',
					data: dataObject
			};			

			$http(reqObject).success(function(data, status, headers, config){
				if(data.payLoad.reservestatus == 'C'){
					console.log("UPDATED : CONFIRMED");
					mrs.confirm = true;
					defer.resolve(data);
				}else{
					console.log("UPDATED : Waiting");
					mrs.wait = true;
					defer.resolve(data);
				}
			}).error(function(error, status, headers, config){
				console.log("FAILURE IN MODIFICATION");
				defer.reject(error);
			})				

			return defer.promise;

		}






		return mrs;
	}

})();


