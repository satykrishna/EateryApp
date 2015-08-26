(function(){
	
	'use strict';
	angular.module('eateryApp').controller('Profilecontroller', Profilecontroller);
	
	Profilecontroller.$inject = ['ProfileService', '$filter'];
	
	function Profilecontroller(ProfileService, $filter){
		
		var pc = this;
		pc.modify = false;
		
		pc.getInfo = fnGetInfo;	
		pc.getInfo();
		
		pc.updateOpen = fnUpdateOpen;
		
		pc.modify = fnModify;
		
		function fnGetInfo(){
			pc.promise = ProfileService.getDetails();		
			pc.promise.then(function(resp){
				console.dir(resp);
				pc.profile = resp.payLoad;
			},
			function(err){
				console.dir(err);
			});
		}
		
		
		function fnModify(){
			pc.promise = ProfileService.update(pc.profile);		
			pc.promise.then(function(resp){
				console.log("UPDATED");
				console.dir(resp);
				pc.profile = resp.payLoad;
			},
			function(err){
				console.dir(err);
			});
		}
		
		function fnUpdateOpen(){
		
			
			pc.profile.opendate = pc.open;
			pc.profile.opentime = pc.open;
			pc.profile.closedate = pc.close;
			pc.profile.closetime = pc.close;
			
			pc.promise =  ProfileService.updateSettings(pc.profile);
			pc.promise.then(function(resp){
				console.log(resp);
				console.log("Updated");
			},
			function(resp){
				console.log(resp);
			})
			
		
		}
	}
	
	
	
})();