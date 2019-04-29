'use strict'; 
angular.module("newApp")
.controller(
				"consultationJournalController",
				function($scope, $http, $filter,$timeout,$location,$state) {
					$scope.produits = [];
					$scope.somme = 0;
					$scope.nbElement = 0;

					$scope.dateDebut= $filter('date')(new Date(), 'dd/MM/yyyy');
					$scope.dateFin= $filter('date')(new Date(), 'dd/MM/yyyy');
					$scope.numCarte = "";
					$scope.terminal = "";
					$scope.NumSeq = "";
					$scope.codeTransaction = "";
					$scope.pageCourante = 0;
					$scope.totalPages = 0;
					$scope.totaleOperation = 0;
					$scope.orderColon = "t.gab_pk";
					$scope.orderType = "ASC";
					$scope.visa_arrierees = 0;
					$scope.visa_arrierees_Anomalie = 0;
					$scope.visa_transactions_compensees_non_collectees = 0;
					$scope.visa_anomalie_transactions_compensees_non_collectees = 0;
					$scope.mc_arrierees = 0;
					$scope.mc_arrierees_Anomalie = 0;
					$scope.mc_transactions_compensees_non_collectees = 0;
					$scope.mc_anomalie_transactions_compensees_non_collectees = 0;
					$scope.visa = 0;
					$scope.mc = 0;
					$scope.all = 0;
					$scope.isLoading = false;
					$scope.statut="";
					$scope.model="";
					$scope.courrier = "true";
					
					
					$scope.charger = function() {
						$scope.dateDebut2= $scope.dateDebut;
						$scope.dateFin2= $scope.dateFin;
						$scope.produits = [];
						
						$scope.courrier = "true";
						if (!validateDate($scope.dateDebut)) {
				            $scope.courrier = "false";
				        }
						if (!validateDate($scope.dateFin)) {
				            $scope.courrier = "false";
				        }
				       
						if (validateDate($scope.dateDebut) && validateDate($scope.dateFin) ) {
						$scope.isLoading = true;
						$http.get(
								"/api/gabCollecter?dateDebut="
										+ $scope.dateDebut2 + "&dateFin="
										+ $scope.dateFin2 + "&numCarte="
										+ $scope.numCarte + "&terminal="
										+ $scope.terminal + "&NumSeq="
										+ $scope.NumSeq + "&codeTransaction="
										+ $scope.codeTransaction + "&page="
										+ $scope.pageCourante + "&orderColon="
										+ $scope.orderColon + "&orderType="
										+ $scope.orderType+ "&model="
										+ $scope.model+ "&statut="
										+ $scope.statut).success(
								function(data) {
									$timeout(function(){
										$scope.isLoading = false;
										$scope.produits = data;
										$scope.pageCourante = 0;
								},3000)

								});
						$http.get( 
								"/api/gabSommeAmount?dateDebut="
										+ $scope.dateDebut2 + "&dateFin="
										+ $scope.dateFin2 + "&numCarte="
										+ $scope.numCarte + "&terminal="
										+ $scope.terminal + "&NumSeq="
										+ $scope.NumSeq + "&codeTransaction="
										+ $scope.codeTransaction+ "&model="
										+ $scope.model+ "&statut="
										+ $scope.statut).success(
								function(data) {
									$scope.somme = data;
								});
						$http
								.get(
										"/api/gabCount?dateDebut="
												+ $scope.dateDebut2
												+ "&dateFin=" + $scope.dateFin2
												+ "&numCarte="
												+ $scope.numCarte
												+ "&terminal="
												+ $scope.terminal + "&NumSeq="
												+ $scope.NumSeq
												+ "&codeTransaction="
												+ $scope.codeTransaction+ "&model="
												+ $scope.model+ "&statut="
												+ $scope.statut)
								.success(
										function(data) {
											$scope.totaleOperation = data;
											$scope.pages = parseInt(parseInt($scope.totaleOperation) / 50);
											$scope.nbElement = $scope.totaleOperation;
											$scope.totalPages = parseInt(parseInt($scope.totaleOperation) / 50);

										});
					};
					$scope.chargerPagination = function() {
						$http.get(
								"/api/gabCollecter?dateDebut="
										+ $scope.dateDebut2 + "&dateFin="
										+ $scope.dateFin2 + "&numCarte="
										+ $scope.numCarte + "&terminal="
										+ $scope.terminal + "&NumSeq="
										+ $scope.NumSeq + "&codeTransaction="
										+ $scope.codeTransaction + "&page="
										+ $scope.pageCourante + "&orderColon="
										+ $scope.orderColon + "&orderType="
										+ $scope.orderType+ "&model="
										+ $scope.model+ "&statut="
										+ $scope.statut).success(
								function(data) {
									$scope.produits = data;

								});
					}
					};
					$scope.goToPage = function(p) {
						console.log("PageCourante = " + $scope.pageCourante);
						$scope.pageCourante = p;
						$scope.chargerPagination();
					};

					$scope.edit = function(idOpp) {
						$scope.idOperation = idOpp;
						
						$scope.Smt = [];
						$http.get("/api/GetSMTId?st_pk=" + $scope.idOperation)
								.success(function(data) {
									$scope.Smt = data;
								});
						$('#myModaldee').modal('show');
					};

					
					
					
					$scope.onLoad = function() {
						$scope.charger();
					};

					
					
					$scope.myFunc = function() {
				        $scope.model2=$scope.model;
				
				        if($scope.model2=="CNC")
				        	{
				        	$scope.model3="CNC";
				        	}
				        
				        if($scope.model2=="arrierees")
			        	{
				        	$scope.model3="arrierees";
			        	}
				        
				      };

				
				});
