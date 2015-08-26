(function(){

	'use strict';
	angular.module('eateryApp').service('ViewReservationService', ViewReservationService );

	ViewReservationService.$inject = ['$http', '$q'];


	function ViewReservationService($http, $q){

		var vrs = this;



		vrs.getAllReserve = function(){
			var defer = $q.defer();
			var reqObject  = {
					method : 'GET',
					url : 'eatery/reserve/all',

			};			
			$http(reqObject).success(function(data, status, headers, config){
				console.log("SUCCESSFULLY FETCHED ALL RECORDS");
				console.dir(data);
				defer.resolve(data);
			}).error(function(error, status, headers, config){
				console.log("FAILURE TO FETCH ALL RECORDS");
				defer.reject(error);
			})				
			return defer.promise;
		}	



		vrs.getUnreserved = function(dataObj){
			var defer = $q.defer();
			var reqObject  = {
					method : 'GET',
					url : 'eatery/reserve/tables/'+dataObj,

			};			
			$http(reqObject).success(function(data, status, headers, config){
				console.log("SUCCESSFULLY FETCHED ALL RECORDS");
				console.dir(data);
				defer.resolve(data);
			}).error(function(error, status, headers, config){
				console.log("FAILURE TO FETCH ALL RECORDS");
				defer.reject(error);
			})				
			return defer.promise;
		}	



		vrs.assignTable = function(dataObj){
			var defer = $q.defer();
			var reqObject  = {
					method : 'POST',
					url : 'eatery/reserve/update/table',
					data: dataObj

			};			
			$http(reqObject).success(function(data, status, headers, config){
				console.log("SUCCESSFULLY FETCHED ALL RECORDS");
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