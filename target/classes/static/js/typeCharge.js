'use strict';
angular.module('newApp')
.controller("TypechargeController",function ($scope,$http, $q, $timeout,$location,$state) {
	

	
	
	$scope.listenature = [];
	
	$scope.savenature= function(valid) {
		$scope.errors=null;
		$scope.message=false;
		 if(valid){
			 $http.get(
						"/api/Savetypecharge?nom_charge="+ $scope.nom_charge+ "&Naturecharge.id_nature=" + $scope.id_nature).success(function(data) {
							$scope.Typecharge  = data;
							$scope.errors=null;
							$scope.message = "succès! Typecharge et biens saisie";
							$scope.getAll();
							 
							$('#myModaldee').modal('hide');
							$scope.getAll();
						});
			
			 
			 
		 }else
			 {
			 $scope.Typecharge  = null ;
				$scope.errors=data;
			 
			 console.log("invalid");
			 }
		
	    
	};
	
	
	$scope.updateTypecharge = function() {
		alert($scope.id_nature);
		var deferred = $q.defer();  
		$scope.load=false;
		$http.get(
				"/api/updatetypecharge?nom_charge="+ $scope.nom_charge+ "&naturecharge.id_nature=" + $scope.id_nature+ "&id_charge="
				+ $scope.id_charge).success(function(data, status, headers, config) {
			 
					
				
					   console.log("********* success *********");
					   $scope.Typecharge1  = data;
						$scope.message = "succès! nature et biens Modifier";
						$scope.load=true;
		       		   console.log(status,config); 
		        	   deferred.resolve(data);
		           }) .error(function(data, status, headers, config) { 
		        	   console.log("********* error *********");
		       		   console.log(data,status,config); 
		        	   deferred.reject();
		           });
		$scope.getAll();
		$('#myModaldeupdate').modal('hide');
		$scope.getAll();
	
	
	};
	
	
	$scope.edit = function() {
	$scope.getListenature();
		$('#myModaldee').modal('show');

	};
	
	
	$scope.Typecharge = [];
    $http.get("/api/Typecharge").success(function(data) {
			$scope.Typecharge = data;
		
		});
    
 


    $scope.getAll = function(){
    	$http.get("/api/Typecharge").success(function(data){
    		$scope.Typecharge = data;
    		
    	});
    } 
  
	
 
	$scope.getListenature = function() {
		$scope.listenature = [];
		$http.get("/api/Naturecharge").success(function(data) {
			$scope.listenature = data;
		 
		});
	};

	

	$scope.sort = function(keyname){
		$scope.sortKey = keyname;   //set the sortKey to the param passed
		$scope.reverse = !$scope.reverse; //if true make it false and vice versa
	}
	
	
	
	$scope.editnature = function(idOpp) {
		$scope.id_charge = idOpp;
		$scope.getListenature();
	 
		$http.get("/api/GetTypechargeId?id_charge=" + $scope.id_charge).success(
				function(data) {
					
				 
					 $scope.nom_charge =  data[0]["nom_charge"];
				 
					 $scope.id_nature =  data[0]["naturecharge"]["id_nature"];
					 $scope.nom_nature =  data[0]["naturecharge"]["nom_nature"];
					 id_charge=$scope.id_charge;

				});
	
		$('#myModaldeupdate').modal('show');
	};

 
 

});
