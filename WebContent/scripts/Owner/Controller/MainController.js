(function (){
	'use strict';
	angular.module('eateryApp', ['ngRoute', 'ngMessages']).config(configureRoutes);



	function configureRoutes($routeProvider){

		$routeProvider
		.when('/view', {
			templateUrl: 'viewreservation.html', 
			controller: 'ViewReservation',  
			controllerAs: 'vr' 
		})
		.when('/seatingarea', {
			templateUrl: 'viewseatingarea.html', 
			controller: 'seatingarea',  
			controllerAs: 'vsa' 
		})
		.when('/new', {
			 templateUrl: 'createreservation.html', 
	          controller: 'Newcontroller',  
	          controllerAs: 'nc' 
		})
		.when('/edit', {
			  templateUrl: 'modifyreservation.html', 
	          controller: 'Modifycontroller',  
	          controllerAs: 'mc' 
		})
		
		.when('/profile', {
			templateUrl: 'profile.html', 
	          controller: 'Profilecontroller',  
	          controllerAs: 'pc' 
		})
		.when('/customer', {
			templateUrl: 'viewcontact.html', 
	          controller: 'CustomerDetail',  
	          controllerAs: 'cd' 
		})
		
		
		
		.otherwise({
			redirectTo: '/view'
		});
	}


})();