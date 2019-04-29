'use strict';
angular.module('newApp')
		.controller(
				"CatController",
				function($scope, $http) {
					$scope.produits = [];
					$scope.somme = 0;
					$scope.nbElement = 0;
					$scope.dateDebut = "09/03/2016";
					$scope.dateFin = "15/03/2016";
					$scope.numCarte = "";
					$scope.terminal = "";
					$scope.NumSeq = "";
					$scope.codeTransaction = "";
					$scope.pageCourante = 0;
					$scope.totalPages = 0;
					$scope.totaleOperation = 0;
					$scope.orderColon = "t.st_pk";
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
					$scope.charger = function() {
						$scope.isLoading = true;
						$scope.produits = [];
						$http.get(
								"/api/interneTouteTransactionCollecter?dateDebut="
										+ $scope.dateDebut + "&dateFin="
										+ $scope.dateFin + "&numCarte="
										+ $scope.numCarte + "&terminal="
										+ $scope.terminal + "&NumSeq="
										+ $scope.NumSeq + "&codeTransaction="
										+ $scope.codeTransaction + "&page="
										+ $scope.pageCourante + "&orderColon="
										+ $scope.orderColon + "&orderType="
										+ $scope.orderType).success(
								function(data) {
									$scope.produits = data;
									$scope.pageCourante = 0;
									$scope.isLoading = false;

								});
						$http.get(
								"/api/interneTouteTransactionCollecterSommeAmount?dateDebut="
										+ $scope.dateDebut + "&dateFin="
										+ $scope.dateFin + "&numCarte="
										+ $scope.numCarte + "&terminal="
										+ $scope.terminal + "&NumSeq="
										+ $scope.NumSeq + "&codeTransaction="
										+ $scope.codeTransaction).success(
								function(data) {
									$scope.somme = data;
								});
						$http
								.get(
										"/api/interneTouteTransactionCollecterCount?dateDebut="
												+ $scope.dateDebut
												+ "&dateFin=" + $scope.dateFin
												+ "&numCarte="
												+ $scope.numCarte
												+ "&terminal="
												+ $scope.terminal + "&NumSeq="
												+ $scope.NumSeq
												+ "&codeTransaction="
												+ $scope.codeTransaction)
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
								"/api/interneTouteTransactionCollecter?dateDebut="
										+ $scope.dateDebut + "&dateFin="
										+ $scope.dateFin + "&numCarte="
										+ $scope.numCarte + "&terminal="
										+ $scope.terminal + "&NumSeq="
										+ $scope.NumSeq + "&codeTransaction="
										+ $scope.codeTransaction + "&page="
										+ $scope.pageCourante + "&orderColon="
										+ $scope.orderColon + "&orderType="
										+ $scope.orderType).success(
								function(data) {
									$scope.produits = data;

								});
					};
					$scope.goToPage = function(p) {
						console.log("PageCourante = " + $scope.pageCourante);
						$scope.pageCourante = p;
						$scope.chargerPagination();
						$scope.calculStat();
					};

					$scope.edit = function(idOpp) {
						$scope.idOperation = idOpp;
						alert(idOpp);
						$scope.Smt = [];
						$http.get("/api/GetSMTId?id=" + $scope.idOperation)
								.success(function(data) {
									$scope.Smt = data;
								});
						$('#myModaldee').modal('show');
					};

					$scope.onLoad = function() {
						$scope.charger();
						$scope.getStatAnnomalie();
						$scope.calculStat();
					};

					$scope.getStatAnnomalie = function() {
						$scope.getVisaArrierees();
						$scope.getVisaBugArrierees();
						$scope.getVisaTransactionsCompenseesNonCollectees();
						$scope.getVisaBugTransactionsCompenseesNonCollectees();
						$scope.getMcArrierees();
						$scope.getMcBugArrierees();
						$scope.getMcTransactionsCompenseesNonCollectees();
						$scope.getMcBugTransactionsCompenseesNonCollectees();
					}
					
						$scope.calculStat = function() {
						$scope.visa = ($scope.visa_arrierees)
								+ ($scope.visa_arrierees_Anomalie)
								+ ($scope.visa_transactions_compensees_non_collectees)
								+ ($scope.visa_anomalie_transactions_compensees_non_collectees);
						$scope.mc = ($scope.mc_arrierees)
								+ ($scope.mc_arrierees_Anomalie)
								+ ($scope.mc_transactions_compensees_non_collectees)
								+ ($scope.mc_anomalie_transactions_compensees_non_collectees);
						$scope.all = ($scope.visa) + ($scope.mc);
					}

					$scope.getVisaArrierees = function() {
						$http.get("/api/visaArrierees").success(function(data) {
							$scope.visa_arrierees = data;
						});
					};

					$scope.getVisaBugArrierees = function() {
						$http.get("/api/visaBugArrierees").success(function(data) {
							$scope.visa_arrierees_Anomalie = data;
						});
					};

					$scope.getVisaTransactionsCompenseesNonCollectees = function() {
						$http
								.get("/api/visaTransactionsCompenseesNonCollectees")
								.success(
										function(data) {
											$scope.visa_transactions_compensees_non_collectees = data;
										});
					};

					$scope.getVisaBugTransactionsCompenseesNonCollectees = function() {
						$http
								.get(
										"/api/visaBugTransactionsCompenseesNonCollectees")
								.success(
										function(data) {
											$scope.visa_anomalie_transactions_compensees_non_collectees = data;
										});
					};

					$scope.getMcArrierees = function() {
						$http.get("/api/mcArrierees").success(function(data) {
							$scope.mc_arrierees = data;
						});
					};

					$scope.getMcBugArrierees = function() {
						$http.get("/api/mcBugArrierees").success(function(data) {
							$scope.mc_arrierees_Anomalie = data;
						});
					};

					$scope.getMcTransactionsCompenseesNonCollectees = function() {
						$http
								.get("/api/mcTransactionsCompenseesNonCollectees")
								.success(
										function(data) {
											$scope.mc_transactions_compensees_non_collectees = data;
										});
					};

					$scope.getMcBugTransactionsCompenseesNonCollectees = function() {
						$http
								.get(
										"/api/mcBugTransactionsCompenseesNonCollectees")
								.success(
										function(data) {
											$scope.mc_anomalie_transactions_compensees_non_collectees = data;
										});
					};
				});
