'use strict';
angular.module('newApp').controller("showAllProjectController",
		function($scope, $http, $q, $timeout, $location, $state) {

			$scope.allProjets = [];
			$http.get("/api/Projet").success(function(data) {
				$scope.allProjets = data;
			});
			
			$http.get("/api/avancementTempsAllProjet").success(function(data) {
			});
			
			$http.get("/api/avancementRessourceAllProjet").success(function(data) {
			});
			
			
		});
