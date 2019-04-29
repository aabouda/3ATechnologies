'use strict';
angular.module('newApp').controller("risqueProjectController",
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
		
		$scope.risque = [];
		$scope.allUs = [];
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
				$scope.allrisques = [];
				$http.get("/api/risqueProjetId?projectID="+$scope.idProjet).success(function(data) {
					$scope.allrisques = data;
				});
			 };
			
			$scope.afficherRisque = function(idRisque) {
				$http.get(
						"/api/risqueId?risqueID=" + idRisque
								).success(
						function(data) {
							$scope.idRisque =  data[0]["idRisque"];
							$scope.risque =  data[0]["risque"];
							$scope.detailsRs = data[0]["detailsRs"];
							$scope.impact =  data[0]["impact"];
							$scope.probabilite =  data[0]["probabilite"];
							$scope.criticite =  parseFloat(data[0]["criticite"]);
							$scope.getAllDeclarationRisque($scope.idRisque);
						});
			};
			
			
			$scope.ajouterRisques = function() {
				$('#myModaldees').modal('show');
			};
			
			
			$scope.CalculCriticite = function() {
				$scope.criticite = $scope.probabilite * $scope.impact;
			};
			
			$scope.CalculCriticiteNV = function() {
				$scope.criticiteNV = $scope.probabiliteNV * $scope.impactNV;
			};
			
			
			
		
			
			$scope.validerAjouterRisques = function(){
				$http.get("/api/Addrisque?projectID="+$scope.idProjet
						+"&risque="+$scope.risqueNV
						+"&detailsRs="+$scope.detailsRsNV
						+"&impact="+$scope.impactNV
						+"&probabilite="+$scope.probabiliteNV
						+"&criticite="+$scope.criticiteNV
						).success(
				function(data) {
					$('#myModaldees').modal('hide');
					$('#myModalMessageOKEnregistrement').modal('show');
					$scope.onLoad();
					$scope.afficherrisque(data);
				})
				.error(function(data, status) {
					  console.error('Repos error', status, data);
					  $('#myModalMessageKOEnregistrement').modal('show');
					});
			};
			
			
			
			$scope.validerUpdateRisque = function(idRisque){
				$http.get("/api/Updaterisque?idRisque="+idRisque
						+"&risque="+$scope.risque
						+"&detailsRs="+$scope.detailsRs
						+"&impact="+$scope.impact
						+"&probabilite="+$scope.probabilite
						+"&criticite="+$scope.criticite
						).success(
				function(data) {
					$scope.onLoad();
					$('#myModalMessageOKModification').modal('show');
				})
				.error(function(data, status) {
					  console.error('Repos error', status, data);
					  $('#myModalMessageKOModification').modal('show');
					});
			}; 
				
			
			
			
			
			$scope.getAllDeclarationRisque = function(risqueID) {
				$scope.allDeclarationRisque = [];
				$http.get("/api/DeclarationRisqueId?risqueID="+risqueID).success(function(data) {
					$scope.allDeclarationRisque = data;
				});
			 };
			 
			 
			 
			 
			 $scope.ajouterDeclarationRisque = function(idRisque) {
				 	$scope.idRisque = idRisque;
					$('#myModaldeesUS').modal('show');
				};
				
				
				
				
				
				$scope.AddDeclarationRisque = function(){
					$http.get("/api/AddDeclarationRisque?idRisque=" + $scope.idRisque
							+"&resume="+$scope.resumeNV
							+"&commentaires="+$scope.commentairesNV
							).success(
					function(data) {
						$('#myModaldeesUS').modal('hide');
					 	$scope.resumeNV = "";
					 	$scope.commentairesNV = "";
						$scope.getAllDeclarationRisque($scope.idRisque);
						$('#myModalMessageOKEnregistrement').modal('show');
					})
					.error(function(data, status) {
						  console.error('Repos error', status, data);
						  $('#myModalMessageKOEnregistrement').modal('show');
						});
				};
				
				
				$scope.cloturer = function(idDeclarationRisque){
					$http.get("/api/cloturerDeclarationRisque?idDeclarationRisque="+idDeclarationRisque
							).success(
					function(data) {
						$scope.getAllDeclarationRisque($scope.idRisque);
					});
				};
				
				
				$scope.ouvrir = function(idDeclarationRisque){
					$http.get("/api/ouvrirDeclarationRisque?idDeclarationRisque="+idDeclarationRisque
							).success(
					function(data) {
						$scope.getAllDeclarationRisque($scope.idRisque);
					});
				};
			
			
				
				
				
				
		});
