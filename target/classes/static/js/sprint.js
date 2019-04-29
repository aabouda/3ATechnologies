'use strict';
angular.module('newApp').controller("sprintProjectController",
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
		
		$scope.Sprint = [];
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
			
			
			
			
			$scope.onLoad = function() {
				$scope.allSprints = [];
				$http.get("/api/sprintProjetId?projectID="+$scope.idProjet).success(function(data) {
					$scope.allSprints = data;
				});
			 };
			 
			 
			 
			
			
			
			$scope.afficherSprint = function(idSp) {
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
			
			
			$scope.ajouterSprint = function() {
				$('#myModaldees').modal('show');
			};
			
			$scope.validerAjouterSprint = function(){
				$http.get("/api/AddSprint?projectID="+$scope.idProjet
						+"&resumeSpNv="+$scope.resumeSpNv
						+"&dateFinEstimeSpNv="+$scope.dateFinEstimeSpNv
						+"&dateDebutEstimeSpNv="+$scope.dateDebutEstimeSpNv
						+"&detailsSpNv="+$scope.detailsSpNv
						+"&commentairesSpNv="+$scope.commentairesSpNv
						).success(
				function(data) {
					$('#myModaldees').modal('hide');
					$('#myModalMessageOKEnregistrement').modal('show');
					$scope.onLoad();
					$scope.afficherSprint(data);
				})
				.error(function(data, status) {
					  console.error('Repos error', status, data);
					  $('#myModalMessageKOEnregistrement').modal('show');
					});
			};
			
			
			
			$scope.validerUpdateSprint = function(idSp){
				$http.get("/api/UpdateSprint?idSp="+idSp
						+"&resumeSp="+$scope.resumeSp
						+"&dateFinEstimeSp="+$scope.dateFinEstimeSp
						+"&dateDebutEstimeSp="+$scope.dateDebutEstimeSp
						+"&detailsSp="+$scope.detailsSp
						+"&commentairesSp="+$scope.commentairesSp
						).success(
				function(data) {
					$('#myModalMessageOKModification').modal('show');
					$scope.onLoad();
				})
				.error(function(data, status) {
					  console.error('Repos error', status, data);
					  $('#myModalMessageKOModification').modal('show');
					});
			}; 
				
			
			$scope.getAllUs = function(idSp) {
				$scope.allUs = [];
				$http.get("/api/userStorySprintId?sprintID="+idSp).success(function(data) {
					$scope.allUs = data;
				});
			 };
			 
			 
			 $scope.Quitter = function(idSp) {
				 $('#myModaldeesUS').modal('hide');
				 };
			 
			 
			 
			 
			 $scope.ajouterUS = function(idSp) {
				 alert (idSp); 
				 	$scope.idSp = idSp;
				 	$scope.dateDebutUS = "";
					$scope.dateFinUS = "";
					$scope.nbHuere = "";
					$scope.statut = "";
					$scope.avancementUS = "";
					$scope.module = "";
					$scope.typeUS = "";
					$scope.priorite = "";
					$scope.complexite = "";
					$scope.dateDebutEstUS = "";
					$scope.dateFinEstUS = "";
					$scope.detailsUS = "";
					$scope.force_us = "";
					$scope.nbHuereEst = "";
					$scope.resumeUS = "";
					$('#myModaldeesUS').modal('show');
				};
				
				$scope.validerAjouterUS = function(){
					$http.get("/api/AddUS?idSp="+$scope.idSp
							+"&dateDebutUS="+$scope.dateDebutUS
							+"&dateFinUS="+$scope.dateFinUS
							+"&nbHuere="+$scope.nbHuere
							+"&statut="+$scope.statut
							+"&avancementUS="+$scope.avancementUS
							+"&module="+$scope.module
							+"&typeUS="+$scope.typeUS
							+"&priorite="+$scope.priorite
							+"&complexite="+$scope.complexite
							+"&dateDebutEstUS="+$scope.dateDebutEstUS
							+"&dateFinEstUS="+$scope.dateFinEstUS
							+"&detailsUS="+$scope.detailsUS
							+"&force_us="+$scope.force_us
							+"&nbHuereEst="+$scope.nbHuereEst
							+"&resumeUS="+$scope.resumeUS
							).success(
					function(data) {
						$('#myModaldeesUS').modal('hide');
						$scope.afficherSprint($scope.idSp);
						$('#myModalMessageOKEnregistrement').modal('show');
					})
					.error(function(data, status) {
						  console.error('Repos error', status, data);
						  $('#myModalMessageKOEnregistrement').modal('show');
						});
				};
			
			

		});
