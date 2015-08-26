(function(){

	angular.module('eateryApp').controller('ViewReservation', ViewReservation);

	ViewReservation.$inject = ['ViewReservationService', '$q']

	function ViewReservation(ViewReservationService, $q,  $scope){
		var vr = this;
		vr.reserve = [];
		vr.assigned = false;
		vr.allreservations = fngetAll;
		vr.config = {
				itemsPerPage : 5,
				fillLastPage : true

		}

		vr.allreservations();

		function fngetAll(){
			vr.promise = ViewReservationService.getAllReserve();
			vr.promise.then(function(resp){
				vr.reserve = resp.payLoad;
			},
			function(err){
				console.log(err);
			})



		}

		vr.tableList =  function fngetNonReserved(date){
			console.log("DATE IS " , date);	
			vr.promise = ViewReservationService.getUnreserved(date);
			vr.promise.then(function(resp){
				vr.tableNo = resp.payLoad;
				console.log(vr.tableNo);	
			},
			function(err){
				console.log(err);
			})
		}


		vr.assignTable = function fnAssignTable(){
			vr.selected.tableNo = vr.selectedTable;
			vr.promise = ViewReservationService.assignTable(vr.selected);
			vr.promise.then(function(resp){
				vr.assigned = true;
				console.log(vr.tableNo);	
			},
			function(err){
				console.log(err);
			})
		}



	}


})();