'use strict';
angular.module('newApp').controller("endProjectController",
		function($scope, $http, $q, $timeout, $location, $state) {

			$scope.allProjets = [];
			$http.get("/api/Projet").success(function(data) {
				$scope.allProjets = data;
			});
			
			

			$scope.cloturerProjet = function(idProjet){
				$http.get("/api/cloturerProjet?projectID="+idProjet).success(
				function(data) {
					$('#myModalMessageOKCloture').modal('show');
					$scope.allProjets = [];
					$http.get("/api/Projet").success(function(data) {
						$scope.allProjets = data;
					});
				})
				.error(function(data, status) {
					  console.error('Repos error', status, data);
					  $('#myModalMessageKOCloture').modal('show');
					});
			}; 
			
			
			$scope.ouvrirProjet = function(idProjet){
				$http.get("/api/ouvrirProjet?projectID="+idProjet).success(
				function(data) {
					$('#myModalMessageOKOuverture').modal('show');
					$scope.allProjets = [];
					$http.get("/api/Projet").success(function(data) {
						$scope.allProjets = data;
					});
				})
				.error(function(data, status) {
					  console.error('Repos error', status, data);
					  $('#myModalMessageKOOuverture').modal('show');
					});
			}; 
			
			

});
