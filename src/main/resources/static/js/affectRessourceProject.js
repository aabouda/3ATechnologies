'use strict';
angular.module('newApp')
.controller("affectRessourceProjectController",function ($scope,$http, $q, $timeout,$location,$state) {
	
	var config = {  headers: { 
   		'Content-Type': 'application/json'
   		} 
    }; 
	
	
	$scope.saveProject = function() {
		$scope.errors=null;
		$scope.message=false;
		$scope.getRessource();
		$http.get(
				"/api/SaveProjet?nameProject=" + $scope.nameProject 
						+ "&description=" + $scope.description 
						+ "&dateDebut=" + $scope.dateDebut 
						+ "&dateFin=" + $scope.dateFin 
						+ "&budget=" + $scope.budget 
						).success(
				function(data) {
					 $urlRouterProvider.otherwise("/affectRessourceProject");
				});
	};
	
	
	$scope.sort = function(keyname){
		$scope.sortKey = keyname;   //set the sortKey to the param passed
		$scope.reverse = !$scope.reverse; //if true make it false and vice versa
	}
	
	$scope.idProjet = $state.params.param1;
	
	
	$scope.roleProjet = [];
    $http.get("/api/RoleProjet").success(function(data) {
			$scope.roleProjet = data;
		});
	
	
	$scope.onLoad = function() {
		$scope.membresIn = [];
		$scope.membresOut = [];
	    $http.get("/api/listeUsersProjetIn?projetId="+$scope.idProjet).success(function(data) {
				$scope.membresIn = data;
			});
	    
	    $http.get("/api/listeUsersProjetOut?projetId="+$scope.idProjet).success(function(data) {
			$scope.membresOut = data;
		});
	 };
 
	   
	    
	    
	    $scope.supprimerMembre = function(membre) {
			$http.get(
					"/api/supprimerMembre?idProjet=" + $scope.idProjet 
							+ "&membre=" + membre 
							).success(
					function(data) {
						 $scope.onLoad();
					});
		};
		
		$scope.affectMembre = function(membre) {
			var val = "roleProjetUser_" + membre;
			var info = document.getElementById(val).value;
			$http.get(
					"/api/affectMembre?idProjet=" + $scope.idProjet 
							+ "&membre=" + membre 
							+ "&role=" + info 
							).success(
					function(data) {
						 $scope.onLoad();
					});
		};
		
		
	    
	    
 
   
});
