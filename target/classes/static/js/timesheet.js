'use strict';
angular.module('newApp').controller("timesheetProjectController",
		function($scope, $http, $q, $timeout, $location, $state, $filter) {

	
	
	

	
		$scope.idProjet = $state.params.param1;
		
		
		$scope.bug = "0";
		$scope.alerte =  "0";
		$scope.release =  "0";
		$scope.document =  "0";
		$scope.membre =  "0";
		$scope.nameProject =  "";
		$scope.dateDebutPrevu =  "";
		$scope.dateDebutReelle =  "";
		$scope.dateFinPrevu =  "";
		$scope.dateFinReelle =  "";
		$scope.budgetPrevu =  "0";
		$scope.budgetReelle = "0";
		$scope.nbRessourcePrevu =  "0";
		$scope.nbRessourceReelle = "0";
		$scope.phase =  "";
		$scope.avancement =  "0";
		$scope.description = "";
		$scope.etatProjet = "0";
		
		
		$scope.idSp = "0";
		$scope.Sprint = [];
		$scope.AllUs = [];
		$scope.allAssignation = [];
	
			$scope.Projet = [];
			$http.get("/api/GetProjetId?projectID="+$scope.idProjet).success(function(data) {
				$scope.Projet = data;
				$scope.membre =  parseInt(data[0]["nbRessource"]);
				$scope.nameProject =  data[0]["nomProjet"];
				$scope.dateDebutPrevu = $filter('date')(new Date(data[0]["dateDebutPrevu"]),'dd-MM-yyyy');
				$scope.dateDebutReelle =  $filter('date')(new Date(data[0]["dateDebutReelle"]),'dd-MM-yyyy');
				$scope.dateFinPrevu =  $filter('date')(new Date(data[0]["dateFinPrevu"]),'dd-MM-yyyy');
				$scope.dateFinReelle =  $filter('date')(new Date(data[0]["dateFinReelle"]),'dd-MM-yyyy');
				$scope.budgetPrevu =  parseInt(data[0]["budgetPrevu"]);
				$scope.budgetReelle =  parseInt(data[0]["budgetReelle"]);
				$scope.nbRessourcePrevu =  parseInt(data[0]["nbRessource"]);
				$scope.nbRessourceReelle =  parseInt(data[0]["nbRessourceReelle"]);
				$scope.phase =  data[0]["phase"];
				$scope.avancement =  data[0]["avancement"];
				$scope.description =  data[0]["description"];
				$scope.etatProjet =  data[0]["etatProjet"];
				$scope.avancementStat = Math.round(data[0]["avancement"]) ;
			});
			  
			$scope.onLoad = function() {
				$scope.allSprints = [];
				
				$scope.membresIn = [];
				$scope.membresOut = [];
				
				
				$http.get("/api/moisAnneeProjetId?projectID="+$scope.idProjet).success(function(data) {
					$scope.allSprints = data;
				});
				
				 $http.get("/api/listeUsersProjetIn?projetId="+$scope.idProjet).success(function(data) {
						$scope.membresIn = data;
					});
				 
				 
			 };
			 
			 
			 
			
			 $scope.affecterUSMb = function(idUser, idUserStory) {
					$http.get(
							"/api/affecterUSMb?idUserStory=" + idUserStory + "&idUser=" + idUser
									).success(
							function(data) {
								 $scope.afficherSprint($scope.idSp)
							});
				};
			
			 
				
				$scope.getAllUs = function(idMois) {
					$scope.allUs = [];
					$http.get("/api/userprojetIdMoisAnnee?projetId="+$scope.idProjet+ "&idMois=" + idMois).success(function(data) {
						$scope.allUs = data;
					});
				 };
				 
				 
				 $scope.validerNBJours = function(idTimesheet) {
					 var nbJours = "nbJours"+idTimesheet;
					 var nbJours2 = document.getElementsByName(nbJours)[0].value;
						$http.get("/api/updateTimesheet?idTimesheet="+idTimesheet+ "&nbJours=" + nbJours2).success(function(data) {
						});
					 };
				 
					 $scope.validerNBJoursP = function(idTimesheet) {
						 var nbJours = "nbJoursP"+idTimesheet;
						 var nbJours2 = document.getElementsByName(nbJours)[0].value;
							$http.get("/api/updateTimesheetP?idTimesheet="+idTimesheet+ "&nbJours=" + nbJours2).success(function(data) {
							});
						 };
				 
				 
				 
		});
