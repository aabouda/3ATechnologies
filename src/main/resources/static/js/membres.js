'use strict';
angular.module('newApp').controller("membresProjectController",
		function($scope,$http, $q, $timeout,$location,$state, $filter) {
			
			var config = {  headers: { 
		   		'Content-Type': 'application/json'
		   		} 
		    }; 
			
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
					
					$scope.calMessage = data[0]["calMessage"] ;
					$scope.calSprint = data[0]["calSprint"] ;
					$scope.calModuleApp = data[0]["calModuleApp"] ;
					$scope.calEnvironnement = data[0]["calEnvironnement"] ;
					$scope.calVerssion = data[0]["calVerssion"];
				});
			
			
			$scope.saveProject = function() {
				$scope.errors=null;
				$scope.message=false;
				$scope.getRessource();
				$http.get(
						"/api/SaveProjet?nameProject=" + $scope.nameProject 
								+ "&description=" + $scope.description 
								+ "&dateDebut=" + $scope.dateDebut 
								+ "&dateFin=" + $scope.dateFin 
								+ "&budget=" + $scope.budget 
								).success(
						function(data) {
							 $urlRouterProvider.otherwise("/affectRessourceProject");
						});
			};
			
			
			$scope.sort = function(keyname){
				$scope.sortKey = keyname;   //set the sortKey to the param passed
				$scope.reverse = !$scope.reverse; //if true make it false and vice versa
			}
			
			
			
			
			$scope.roleProjet = [];
		    $http.get("/api/RoleProjet").success(function(data) {
					$scope.roleProjet = data;
				});
			
			
			$scope.onLoad = function() {
				$scope.membresIn = [];
				$scope.membresOut = [];
			    $http.get("/api/listeUsersProjetIn?projetId="+$scope.idProjet).success(function(data) {
						$scope.membresIn = data;
					});
			    
			    $http.get("/api/listeUsersProjetOut?projetId="+$scope.idProjet).success(function(data) {
					$scope.membresOut = data;
				});
			 };
			    
			    
			    $scope.supprimerMembre = function(membre) {
					$http.get(
							"/api/supprimerMembre?idProjet=" + $scope.idProjet 
									+ "&membre=" + membre 
									).success(
							function(data) {
								 $scope.onLoad();
							});
				};
				
				
				
				 $scope.updateTJM = function(membre) {
					 var tjm = "updateTJM"+membre;
					 var tjm2 = document.getElementsByName(tjm)[0].value;
						$http.get("/api/updateTJM?idProjet=" + $scope.idProjet 
								+ "&membre=" + membre + "&tjm=" + tjm2 ).success(function(data) {
						});
					 };
					 
					 $scope.updateCharge = function(membre) {
						 var tjm = "updateCharge"+membre;
						 var tjm2 = document.getElementsByName(tjm)[0].value;
							$http.get("/api/updateCharge?idProjet=" + $scope.idProjet 
									+ "&membre=" + membre + "&tjm=" + tjm2 ).success(function(data) {
							});
						 };
					 
					 
					 
					 
				
				$scope.affectMembre = function(membre) {
					var val = "roleProjetUser_" + membre;
					var info = document.getElementById(val).value;
					$http.get(
							"/api/affectMembre?idProjet=" + $scope.idProjet 
									+ "&membre=" + membre 
									+ "&role=" + info 
									).success(
							function(data) {
								 $scope.onLoad();
							});
				};
		   
		});
