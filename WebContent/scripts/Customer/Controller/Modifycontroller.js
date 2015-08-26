(function(){
	'use strict';

	angular.module('eateryApp').controller('Modifycontroller', Modifycontroller);

	Modifycontroller.$inject = ['ModifyReservationService'];

	function Modifycontroller(ModifyReservationService){
		var mc = this;
		mc.check = fnCheck;
		mc.del = fnDelete;
		mc.mod = fnModify;
		mc.choose = fnChoose;
		mc.ok = fnChange;

		function fnCheck(){
			mc.promise = ModifyReservationService.check(mc.code);
			mc.promise.then(function(resp){
				mc.res = resp.payLoad;
				mc.old = mc.res;
				if(mc.res == null){
					mc.error = true;
				}else{
					mc.success = true;
				}
				console.log(mc.res);

			},
			function(err){
				console.log("error", err);
			});

		}

		function fnDelete(){
			mc.promise = ModifyReservationService.del(mc.res);
			mc.promise.then(function(resp){
				mc.deleted = true;
				console.log("Your record is deleted");
			}, function(err){
				console.dir('Your record is not deleted');
			})
		}


		function fnModify(){
			mc.res.reserveDate = mc.ndate;
			mc.res.reserveTime = mc.ntime;
			mc.res.partySize = mc.partysize;
			mc.promise = ModifyReservationService.update(mc.res);
			mc.promise.then(function(resp){
				mc.wait = ModifyReservationService.wait;
				mc.confirm = ModifyReservationService.confirm;
				console.dir( mc.wait);
				console.dir( mc.confirm);
				mc.res = resp.payLoad;
				mc.code = resp.payLoad.confirmcode;
			}, function(err){
				console.log("Your record is not updated")
			})
		}


		function fnChoose(){
			if(mc.option === 'd'){
				mc.del();
			}
			else{
				mc.mod();
			}
		}

		function fnChange(){
			if(mc.option == 'no'){
				mc.promise = ModifyReservationService.del(mc.res);
				mc.promise.then(function(resp){
					console.log("Your record is deleted");
				}, function(resp){
					console.dir('Your record is not deleted');
				})
			}
		}
	}





})();