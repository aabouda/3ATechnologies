'use strict';
angular.module('newApp').controller("ganttProjectController",
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
		
		$scope.calMessage = "0";
		$scope.calSprint = "0";
		$scope.calModuleApp = "0";
		$scope.calEnvironnement = "0";
		$scope.calVerssion = "0";
		$scope.calRelease = "0";
		$scope.calBug = "0";
		
		
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
				//$scope.description =  data[0]["description"];
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
			  
			$scope.onLoad = function() {
				$scope.allSprints = [];
				$scope.membresIn = [];
				$scope.membresOut = [];
				
				
				$http.get("/api/sprintProjetId?projectID="+$scope.idProjet).success(function(data) {
					$scope.allSprints = data;
				});
				
				 $http.get("/api/listeUsersProjetInForce?projetId="+$scope.idProjet+ "&id_sprint=0").success(function(data) {
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
			 
			 $scope.afficherSprint = function(idSp) {
				 
				 $http.get("/api/listeUsersProjetInForce?projetId="+$scope.idProjet+ "&id_sprint=" + idSp).success(function(data) {
						$scope.membresIn = data;
					});
				 
				 
					$http.get(
							"/api/sprintId?sprintID=" + idSp
									).success(
							function(data) {
								$scope.idSp =  data[0]["idSp"];
								$scope.resumeSp =  data[0]["resumeSp"];
								$scope.dateDebutEstimeSp = $filter('date')(new Date(data[0]["dateDebutEstimeSp"]),'dd-MM-yyyy');
								$scope.dateFinEstimeSp =  $filter('date')(new Date(data[0]["dateFinEstimeSp"]),'dd-MM-yyyy');
								$scope.dateDebutReels =  $filter('date')(new Date(data[0]["dateDebutReels"]),'dd-MM-yyyy');
								$scope.dateFinReels =  $filter('date')(new Date(data[0]["dateFinReels"]),'dd-MM-yyyy');
								$scope.detailsSp =  data[0]["detailsSp"];
								$scope.commentairesSp =  data[0]["commentairesSp"];
								$scope.avancementSp =  parseFloat(data[0]["avancementSp"]);
								$scope.getAllUs($scope.idSp);
							});
				};
			 
				
				$scope.getAllUs = function(idSp) {
					$scope.allUs = [];
					$http.get("/api/userStorySprintId?sprintID="+idSp).success(function(data) {
						$scope.allUs = data;
					});
				 };
				 
				 
		});
