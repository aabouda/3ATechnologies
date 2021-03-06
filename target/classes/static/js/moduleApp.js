'use strict';
angular.module('newApp').controller("moduleAppProjectController",
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
			
			$scope.allmoduleApp = [];
			$scope.idExec =  "0";
			
			$http.get("/api/ModuleAppProjetId?projectID="+$scope.idProjet).success(function(data) {
				$scope.allmoduleApp = data;
			});
			
			
			$scope.ajoutermoduleApp = function() {
				$('#myModaldees').modal('show');
			};
			
			
			

			$scope.affichermoduleApp = function(idAlerte) {
				$http.get(
						"/api/GetModuleAppId?moduleAppID=" + idAlerte
								).success(
						function(data) {
							$scope.idModuleApp =  data[0]["idModuleApp"];
							$scope.idProjet =  data[0]["idProjet"];
							$scope.nom =  data[0]["nomModuleApp"];
							$scope.description =  data[0]["description"];
							$scope.nb_Cas_Test =  data[0]["nb_Cas_Test"];
						});
			};
			
			
			
			
			$scope.validerAjoutermoduleApp = function(){
				$http.get("/api/SaveModuleApp?idProject="+$scope.idProjet
						+"&nom="+$scope.nomNv
						+"&description="+$scope.detailsNv
						+"&nb_Cas_Test="+$scope.nb_Cas_TestNv
						).success(
				function(data) {
					$('#myModaldees').modal('hide');
					$http.get("/api/ModuleAppProjetId?projectID="+$scope.idProjet).success(function(data) {
						$scope.allmoduleApp = data;
					});
					$('#myModalMessageOKEnregistrement').modal('show');
				})
				.error(function(data, status) {
					  console.error('Repos error', status, data);
					  $('#myModalMessageKOEnregistrement').modal('show');
					});
			};
			
			
			
			$scope.validerUpdatemoduleApp = function(idmoduleApp){
				$http.get("/api/updateModuleApp?idmoduleApp="+idmoduleApp
						+"&name="+$scope.nom
						+"&description="+$scope.description
						+"&nb_Cas_Test="+$scope.nb_Cas_Test
						
						).success(
				function(data) {
					$('#myModaldees').modal('hide');
					$http.get("/api/ModuleAppProjetId?projectID="+$scope.idProjet).success(function(data) {
						$scope.allmoduleApp = data;
					});
					$('#myModalMessageOKModification').modal('show');
				})
				.error(function(data, status) {
					  console.error('Repos error', status, data);
					  $('#myModalMessageKOModification').modal('show');
					});
			}; 
			

});
