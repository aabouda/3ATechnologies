'use strict';
angular.module('newApp')
.controller("addProjectController",function ($scope,$http, $q, $filter, $timeout,$location,$state) {
	
	var config = {  headers: { 
   		'Content-Type': 'application/json'
   		} 
    }; 
	
	
	$scope.saveProject = function() {
		$scope.errors=null;
		$scope.message=false;
		if(($scope.budget > 0) && ($scope.nbRessource > 0) && ($scope.phase > 0))
		{
			$http.get(
					"/api/SaveProjet?nameProject=" + $scope.nameProject 
							+ "&description=" + $scope.description 
							+ "&dateDebut=" + $scope.dateDebut 
							+ "&dateFin=" + $scope.dateFin 
							+ "&budget=" + $scope.budget 
							+ "&nbRessource=" + $scope.nbRessource 
							+ "&phase=" + $scope.phase 
							).success(
					function(data) {
						 //$urlRouterProvider.otherwise("/affectRessourceProject");
						 //$window.location.href = '/affectRessourceProject';
						 $location.path('/affectRessourceProject/'+data);
					});
		}
	               
	};
	
	
	$scope.sort = function(keyname){
		$scope.sortKey = keyname;   //set the sortKey to the param passed
		$scope.reverse = !$scope.reverse; //if true make it false and vice versa
	}
	
	
	
	
   
   
});
