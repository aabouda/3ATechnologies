'use strict';
angular.module('newApp').controller("sadProjectController",
		function($scope, $http, $q, $timeout, $location, $state, $filter) {

	
	

		$scope.idProjet = $state.params.param1;
		
		$scope.bug = "0";
		$scope.alerte = "";
		$scope.release = "";
		$scope.document = "";
		$scope.membre = "";
		$scope.nameProject =  "";
		$scope.dateDebutPrevu =  "";
		$scope.dateDebutReelle =  "";
		$scope.dateFinPrevu =  "";
		$scope.dateFinReelle =  "";
		$scope.budgetPrevu = "";
		$scope.budgetReelle = "0";
		$scope.nbRessourcePrevu = "";
		$scope.nbRessourceReelle = "0";
		$scope.phase =  "";
		$scope.avancement = "";
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
				
				
				$scope.calMessage = Math.round(data[0]["calMessage"]) ;
				$scope.calSprint = Math.round(data[0]["calSprint"]) ;
				$scope.calModuleApp = Math.round(data[0]["calModuleApp"]) ;
				$scope.calEnvironnement = Math.round(data[0]["calEnvironnement"]) ;
				$scope.calVerssion = Math.round(data[0]["calVerssion"]) ;
			});
		
		
		$scope.Categorie = "Avancement";
		$scope.idCategorie =  "1";
		
		
		
		
		$scope.avancementTemps  =  "";
		$scope.avancementQuantites   = "";
		$scope.avancementTaches  = "";
		$scope.tauxRetard   = "";
		$scope.coutReelProjet   = "";
		$scope.coutRetard   = "";
		$scope.depassementCoutProjet  = ""; 
		$scope.depassementCoutPhase   = "";
		$scope.depassementCoutRessources   = "";
		$scope.depassementDelaiPhase  = "";
		$scope.depassementDelaiRessource   = "";
		$scope.depassementDelaiProjet   = "";
		$scope.ressourceEmploi  = "";
		$scope.ressourceCapacite  = "";
		$scope.ecartCout   = "";
		$scope.ecartDure   = "";
		$scope.pointsSuspens   = "";
		$scope.densiteRisque   = "";
		$scope.nbRisqueRaalise   = "";
		$scope.risqueRealise   = "";
		$scope.SumUsersProjetIn = "";
		$scope.coutVsBudget   = "";
		
		$scope.membresIn = [];
	    $http.get("/api/listeUsersProjetIn?projetId="+$scope.idProjet).success(function(data) {
				$scope.membresIn = data;
			});
	    
	    $scope.listeUsersProjetTimesheet = [];
	    $http.get("/api/listeUsersProjetTimesheet?projetId="+$scope.idProjet).success(function(data) {
				$scope.listeUsersProjetTimesheet = data;
			});
		
		 
	    
	    $http.get("/api/SumUsersProjetIn?projectID=" + $scope.idProjet ).success(function(data){
			$scope.SumUsersProjetIn = data[0];
		 });
	    
	    
		$http.get("/api/avancementTemps?projectID=" + $scope.idProjet ).success(function(data){
			$scope.avancementTemps = data[0];
		 });
		
		$http.get("/api/avancementQuantites?projectID=" + $scope.idProjet ).success(function(data){
			$scope.avancementQuantites = data[0];
		 });
		
		$http.get("/api/avancementTaches?projectID=" + $scope.idProjet ).success(function(data){
			$scope.avancementTaches = data[0];
		 });
		
		$http.get("/api/tauxRetard?projectID=" + $scope.idProjet ).success(function(data){
			$scope.tauxRetard = data[0];
		 });
		
		$http.get("/api/coutReelProjet?projectID=" + $scope.idProjet ).success(function(data){
			$scope.coutReelProjet = data[0];
		 });
		
		$http.get("/api/coutRetard?projectID=" + $scope.idProjet ).success(function(data){
			$scope.coutRetard = data[0];
		 });
		
		$http.get("/api/depassementCoutProjet?projectID=" + $scope.idProjet ).success(function(data){
			$scope.depassementCoutProjet = data[0];
		 });
		
		$http.get("/api/depassementCoutPhase?projectID=" + $scope.idProjet ).success(function(data){
			$scope.depassementCoutPhase = data[0];
		 });
		
		$http.get("/api/depassementCoutRessources?projectID=" + $scope.idProjet ).success(function(data){
			$scope.depassementCoutRessources = data[0];
		 });
		
		$http.get("/api/depassementDelaiPhase?projectID=" + $scope.idProjet ).success(function(data){
			$scope.depassementDelaiPhase = data[0];
		 });
		
		$http.get("/api/depassementDelaiRessource?projectID=" + $scope.idProjet ).success(function(data){
			$scope.depassementDelaiRessource = data[0];
		 });
		
		$http.get("/api/depassementDelaiProjet?projectID=" + $scope.idProjet ).success(function(data){
			$scope.depassementDelaiProjet = data[0];
		 });
		
		$http.get("/api/ressourceEmploi?projectID=" + $scope.idProjet ).success(function(data){
			$scope.ressourceEmploi = data[0];
		 });
		
		$http.get("/api/ressourceCapacite?projectID=" + $scope.idProjet ).success(function(data){
			$scope.ressourceCapacite = data[0];
		 });
		
		$http.get("/api/ecartCout?projectID=" + $scope.idProjet ).success(function(data){
			$scope.ecartCout = data;
		 });
		
		$http.get("/api/ecartDure?projectID=" + $scope.idProjet ).success(function(data){
			$scope.ecartDure = data;
		 });
		
		$http.get("/api/pointsSuspens?projectID=" + $scope.idProjet ).success(function(data){
			$scope.pointsSuspens = data[0];
		 });
		
		$http.get("/api/densiteRisque?projectID=" + $scope.idProjet ).success(function(data){
			$scope.densiteRisque = data[0];
		 });
		
		
		$http.get("/api/nbRisqueRaalise?projectID=" + $scope.idProjet ).success(function(data){
			$scope.nbRisqueRaalise = data[0];
		 });
		
		$http.get("/api/risqueRealise?projectID=" + $scope.idProjet ).success(function(data){
			$scope.risqueRealise = data[0];
		 });
		
		$http.get("/api/coutVsBudget?projectID=" + $scope.idProjet ).success(function(data){
			$scope.coutVsBudget = data;
		 });
		 
		 $scope.afficherSad = function(idCategorie) {
				
				if (idCategorie==1) {
					$scope.Categorie = "Avancement";
				} else if (idCategorie==2) {
					$scope.Categorie = "Bug";
				} else if (idCategorie==3) {
					$scope.Categorie = "Cout";
				} else if (idCategorie==4) {
					$scope.Categorie = "Depassement";
				} else if (idCategorie==5) {
					$scope.Categorie = "Imprevus";
				} else if (idCategorie==6) {
					$scope.Categorie = "Ecarts";
				} else if (idCategorie==7) {
					$scope.Categorie = "Risques";
				} else if (idCategorie==8) {
					$scope.Categorie = "Ressources";
				} else {
					$scope.Categorie = "Resultats";
				}
				
				$scope.idCategorie =  idCategorie;
		}
		 
		 

});
