(function (){
  'use strict';
  angular.module('eateryApp', ['ngRoute', 'ngMessages']).config(configureRoutes);
    
  
  
  function configureRoutes($routeProvider){
	
	  $routeProvider
	  .when('/new', {
          templateUrl: 'createreservation.html', 
          controller: 'Newcontroller',  
          controllerAs: 'nc' 
      })
       .when('/modify', {
          templateUrl: 'modifyreservation.html', 
          controller: 'Modifycontroller',  
          controllerAs: 'mc' 
      })
        .when('/login', {
          templateUrl: 'ownerlogin.html', 
          controller: 'Ownercontroller',  
          controllerAs: 'oc' 
      })
      
      .otherwise({
          redirectTo: '/new'
      });
  }
  
  
  })();