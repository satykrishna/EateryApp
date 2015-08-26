(function(){
	
	'use strict';
	
	angular.module('eateryApp').service("ProfileService", ProfileService);
	
	ProfileService.$inject = ['$q', '$http'];
	
	function ProfileService($q, $http){
		
		var ps = this;
		
		ps.getDetails = fnGet;
		
		ps.update = fnUpdate;
		
		ps.updateSettings = fnUpdateSettings;
		
		function fnGet(){
			var defer = $q.defer();
			var reqObject  = {
					method : 'GET',
					url : 'restaurant/fetch/details'

			};			
			$http(reqObject).success(function(data, status, headers, config){
				defer.resolve(data);
			}).error(function(error, status, headers, config){
				defer.reject(error);
			})				
			return defer.promise;			
			
		}
		
		
		function fnUpdate(dataObject){
			
			var defer = $q.defer();
			var reqObject  = {
					method : 'POST',
					url : 'restaurant/fetch/update',
					data : dataObject
			};			
			
			$http(reqObject).success(function(data, status, headers, config){
				defer.resolve(data);
			}).error(function(error, status, headers, config){
				defer.reject(error);
			})				
			return defer.promise;			
		}
		
		
		function fnUpdateSettings(dataObject){
			var defer = $q.defer();
			var reqObject  = {
					method : 'POST',
					url : 'restaurant/fetch/updateAuto',
					data : dataObject
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