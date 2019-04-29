'use strict';
angular.module('newApp').controller("verssionProjectController",
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
			
			$scope.allverssion = [];
			$scope.idExec =  "0";
			
			$http.get("/api/VerssionProjetId?projectID="+$scope.idProjet).success(function(data) {
				$scope.allverssion = data;
			});
			
			
			$scope.ajouterverssion = function() {
				$('#myModaldees').modal('show');
			};
			
			
			

			$scope.afficherverssion = function(idAlerte) {
				$http.get(
						"/api/GetVerssionId?verssionID=" + idAlerte
								).success(
						function(data) {
							$scope.idverssion =  data[0]["idVerssion"];
							$scope.idProjet =  data[0]["idProjet"];
							$scope.nom =  data[0]["nomVerssion"];
							$scope.description =  data[0]["description"];
							
							
						});
			};
			
			
			
			
			$scope.validerAjouterverssion = function(){
				$http.get("/api/SaveVerssion?idProject="+$scope.idProjet
						+"&nom="+$scope.nomNv
						+"&description="+$scope.detailsNv
						).success(
				function(data) {
					$('#myModaldees').modal('hide');
					$http.get("/api/VerssionProjetId?projectID="+$scope.idProjet).success(function(data) {
						$scope.allverssion = data;
					});
					$('#myModalMessageOKEnregistrement').modal('show');
				})
				.error(function(data, status) {
					  console.error('Repos error', status, data);
					  $('#myModalMessageKOEnregistrement').modal('show');
					});
			};
			
			
			
			$scope.validerUpdateverssion = function(idverssion){
				$http.get("/api/updateVerssion?idverssion="+idverssion
						+"&name="+$scope.nom
						+"&description="+$scope.description
						).success(
				function(data) {
					$('#myModaldees').modal('hide');
					$http.get("/api/VerssionProjetId?projectID="+$scope.idProjet).success(function(data) {
						$scope.allverssion = data;
					});
					$('#myModalMessageOKModification').modal('show');
				})
				.error(function(data, status) {
					  console.error('Repos error', status, data);
					  $('#myModalMessageKOModification').modal('show');
					});
			}; 
			

});
