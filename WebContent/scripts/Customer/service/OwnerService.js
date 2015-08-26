(function(){

	angular.module('eateryApp').service('OwnerService', OwnerService);

	OwnerService.$inject  = ['$http', '$q'];

	function OwnerService($http, $q){
		var os = this;
		os.success = false;
		os.failed = false;


		os.login = function(dataObject){
			var defer = $q.defer();
			var reqObject  = {
					method : 'GET',
					url : 'head/owner/isvalid/' + dataObject.email + "/" +dataObject.password,
			};			

			$http(reqObject).success(function(data, status, headers, config){
                if(data.status == 'confirm')
                	os.success = true;
                else
                	os.success = false;
                defer.resolve(data);
                console.log(data);

			}).error(function(error, status, headers, config){
				os.failed = false;
				defer.reject(error);
			})				
			return defer.promise;














//			var defer  = $q.defer;
//			var reqObject = {
//			method : 'GET',
//			url : 'role/owner/login/' + dataObject
//			}

//			console.log("REQ OBJECT", reqObject.url);
//			$http(reqObject).success(function(data, status, headers, config){
//			console.log("SUCCESS", data);
//			defer.resolve(data);
//			}).error(function(error, status, headers, config){
//			console.log("FAIL", error);
//			defer.reject(error);
//			});

//			return defer.promise;

		}



	}



})();