'use strict';
angular.module('newApp').controller("regleAlertesController",
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
		
		$scope.idAlerte =  0;
		$scope.titreAlerte =  "";
		$scope.dateAlerte =  "";
		$scope.niveauAletre =  0;
		$scope.typeAlerte =  "";
		$scope.description =  "";
		
		$scope.calMessage = "0";
		$scope.calSprint = "0";
		$scope.calModuleApp = "0";
		$scope.calEnvironnement = "0";
		$scope.calVerssion = "0";
		$scope.calRelease = "0";
		$scope.calBug = "0";
		
		
	
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
				
				$scope.calRelease = data[0]["calRelease"] ;
				$scope.calBug = data[0]["calBug"] ;
				
				$scope.calMessage = Math.round(data[0]["calMessage"]) ;
				$scope.calSprint = Math.round(data[0]["calSprint"]) ;
				$scope.calModuleApp = Math.round(data[0]["calModuleApp"]) ;
				$scope.calEnvironnement = Math.round(data[0]["calEnvironnement"]) ;
				$scope.calVerssion = Math.round(data[0]["calVerssion"]) ;
			});
			
			$scope.allAlertes = [];
			$scope.idExec =  "0";
			
			$http.get("/api/regleAllAlertesProjetId?projectID="+$scope.idProjet).success(function(data) {
				$scope.allAlertes = data;
			});
			
			
			 $scope.validerdesc = function(idRegleAlerte) {
				 var RegleAlerte = "desc"+idRegleAlerte;
				 var valRegleAlerte = document.getElementsByName(RegleAlerte)[0].value;
					$http.get("/api/updateRegleAlerteDesc?idRegleAlerte="+idRegleAlerte+ "&valRegleAlerte=" + valRegleAlerte).success(function(data) {
					});
				 };
				 
				 $scope.validerMin = function(idRegleAlerte) {
					 var RegleAlerte = "min"+idRegleAlerte;
					 var valRegleAlerte = document.getElementsByName(RegleAlerte)[0].value;
						$http.get("/api/updateRegleAlerteMin?idRegleAlerte="+idRegleAlerte+ "&valRegleAlerte=" + valRegleAlerte).success(function(data) {
						});
					 };
					 
					 $scope.validerMax = function(idRegleAlerte) {
						 var RegleAlerte = "max"+idRegleAlerte;
						 var valRegleAlerte = document.getElementsByName(RegleAlerte)[0].value;
							$http.get("/api/updateRegleAlerteMax?idRegleAlerte="+idRegleAlerte+ "&valRegleAlerte=" + valRegleAlerte).success(function(data) {
							});
						 };
			
			

			//$http.get("/api/regleAlertes?projectID="+$scope.idProjet).success(function(data) {
			//});

			

});
