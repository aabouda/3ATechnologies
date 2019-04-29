'use strict';
angular.module('newApp')
.controller("DashbordController",function ($scope,$http,$filter, $q, $timeout,$location,$state) {
	
	var config = {  headers: { 
   		'Content-Type': 'application/json'
   		} 
    }; 
	
	$scope.alerteProjet = [];
	$scope.avancementProjet = [];
	$scope.activiteRecentes = [];
	
	 $http.get("/api/avancementProjet").success(function(data) {
			$scope.avancementProjet = data;
		
		});
	 
	 
	 $http.get("/api/activiteRecentes").success(function(data) {
			$scope.activiteRecentes = data;
		
		});
	 
	 
	 
	
	 $http.get("/api/alerteProjet").success(function(data) {
			$scope.alerteProjet = data;
		
		});
	
	
	
    $http.get("/api/nbProjet").success(function(data) {
			$scope.nbProjet = data;
		
		});
	
	
	 $http.get("/api/nbAlert").success(function(data) {
			$scope.nbAlert = data;
		
		});
    
    
    $http.get("/api/nbRessource").success(function(data) {
			$scope.nbRessource = data;
		
		});
    
    
    $http.get("/api/avgAvancement").success(function(data) {
		$scope.avgAvancement = data;
	
	});
    
    
    $http.get("/api/budgetReelle").success(function(data) {
		$scope.budgetReelle = data;
	
	});
 
   
});
