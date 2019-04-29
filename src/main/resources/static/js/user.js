'use strict';
angular.module('newApp').controller("UserController",
		function($scope, $http, $q, $timeout ,$location ,$stateParams) {

	
	
	
	
			$scope.listeAgence = [];
			
			$scope.InsertUser = 3;
			
			$http.get("/api/Agences").success(function(data) {
				$scope.listeAgence = data;

			});

			$scope.listeModule = [];
			$http.get("/api/module").success(function(data) {
				$scope.listeModule = data;

			});


			$scope.listeUser = [];
			$http.get("/api/listeUser").success(function(data) {
				$scope.listeUser = data;

			});
			
			
			$scope.listeUsers = [];
			$http.get("/api/listeUsers").success(function(data) {
				$scope.listeUsers = data;

			});
			
			$scope.disabler = function(){
	    	        return true;
			}
			
			$scope.roles = [
					'ROLE_DASHBORD',
					'ROLE_RECONCILIATION_ANOMALIES',
					'ROLE_RECONCILIATION_CONSULTATIONS',
					'ROLE_RECONCILIATION_DEVISE',
					'ROLE_CENTRALISEE_DECLARATIONS_CENTRALISEES',
					'ROLE_CENTRALISEE_RECONCILIATION_GAB',
					'ROLE_DECENTRALISEE_DECLARATIONS_AGENCE',
					'ROLE_DECENTRALISEE_STATESTIQUE_AGENCE',
					'ROLE_RENTABILITE_TRANSACTIONS',
					'ROLE_RENTABILITE_GAB',
					'ROLE_RENTABILITE_RESULTAT_EMETTEURS',
					'ROLE_RENTABILITE_PARAMETRES',
					'ROLE_STATESTIQUE_CONSULTATIONS_GLOBALE',
					'ROLE_STATESTIQUE_CONSULTATIONS_NATURE_TRANSACTION',
					'ROLE_STATESTIQUE_CONSULTATIONS_CODE_REPONSE',
					'ROLE_STATESTIQUE_CONSULTATIONS_BUG_RECONCILIATION',
					'ROLE_STATESTIQUE_CONSULTATIONS_BUG_DECLARATION',
					'ROLE_STATESTIQUE_CONSULTATIONS_RRENTABILITE_GLOBALE',
					'ROLE_PARAM_DAB_EDITER_DAB',
					'ROLE_PARAM_DAB_MAINTENANCE_DAB',
					'ROLE_PARAM_DAB_FOURNICEURS',
					'ROLE_PARAM_DAB_NATURE_CHARGE',
					'ROLE_PARAM_DAB_TYPE_CHARGE',
					'ROLE_GESTION_UTILISATEURS',
					'ROLE_DASHBORD_AGENT',
					'ROLE_DOCUMENTATION_UTILISATION',
					'ROLE_DOCUMENTATION_COMPTABLE'
				  ];
			
			
			  
			 	$scope.user = {
				 roles: ['ROLE_DASHBORD']
			 	};
		
			   $scope.modifierCl = function() {
				    $scope.user.roles.splice(0, $scope.user.roles.length);
			    	if($scope.userroleid=="Admin"){
			    		$scope.user.roles.push('ROLE_DASHBORD');
			    		$scope.user.roles.push('ROLE_RECONCILIATION_ANOMALIES');
			    		$scope.user.roles.push('ROLE_RECONCILIATION_CONSULTATIONS');
			    		$scope.user.roles.push('ROLE_RECONCILIATION_DEVISE');
			    		$scope.user.roles.push('ROLE_CENTRALISEE_DECLARATIONS_CENTRALISEES');
			    		$scope.user.roles.push('ROLE_CENTRALISEE_RECONCILIATION_GAB');
			    		$scope.user.roles.push('ROLE_RENTABILITE_TRANSACTIONS');
			    		$scope.user.roles.push('ROLE_RENTABILITE_GAB');
			    		$scope.user.roles.push('ROLE_RENTABILITE_RESULTAT_EMETTEURS');
			    		$scope.user.roles.push('ROLE_RENTABILITE_PARAMETRES');
			    		$scope.user.roles.push('ROLE_STATESTIQUE_CONSULTATIONS_GLOBALE');
			    		$scope.user.roles.push('ROLE_STATESTIQUE_CONSULTATIONS_NATURE_TRANSACTION');
			    		$scope.user.roles.push('ROLE_STATESTIQUE_CONSULTATIONS_CODE_REPONSE');
			    		$scope.user.roles.push('ROLE_STATESTIQUE_CONSULTATIONS_BUG_RECONCILIATION');
			    		$scope.user.roles.push('ROLE_STATESTIQUE_CONSULTATIONS_BUG_DECLARATION');
			    		$scope.user.roles.push('ROLE_STATESTIQUE_CONSULTATIONS_RRENTABILITE_GLOBALE');
			    		$scope.user.roles.push('ROLE_PARAM_DAB_EDITER_DAB');
			    		$scope.user.roles.push('ROLE_PARAM_DAB_MAINTENANCE_DAB');
			    		$scope.user.roles.push('ROLE_PARAM_DAB_FOURNICEURS');
			    		$scope.user.roles.push('ROLE_PARAM_DAB_NATURE_CHARGE');
			    		$scope.user.roles.push('ROLE_PARAM_DAB_TYPE_CHARGE');
			    		$scope.user.roles.push('ROLE_GESTION_UTILISATEURS');
			    		$scope.user.roles.push('ROLE_DASHBORD_AGENT');
			    		$scope.user.roles.push('ROLE_DOCUMENTATION_UTILISATION');
			    		$scope.user.roles.push('ROLE_DOCUMENTATION_COMPTABLE');
			    	}else if ($scope.userroleid=="Gestionnaire"){
			    		$scope.user.roles.push('ROLE_DASHBORD');
			    		$scope.user.roles.push('ROLE_RECONCILIATION_ANOMALIES');
			    		$scope.user.roles.push('ROLE_RECONCILIATION_CONSULTATIONS');
			    		$scope.user.roles.push('ROLE_RECONCILIATION_DEVISE');
			    		$scope.user.roles.push('ROLE_CENTRALISEE_DECLARATIONS_CENTRALISEES');
			    		$scope.user.roles.push('ROLE_CENTRALISEE_RECONCILIATION_GAB');
			    		$scope.user.roles.push('ROLE_RENTABILITE_TRANSACTIONS');
			    		$scope.user.roles.push('ROLE_RENTABILITE_GAB');
			    		$scope.user.roles.push('ROLE_RENTABILITE_RESULTAT_EMETTEURS');
			    		$scope.user.roles.push('ROLE_RENTABILITE_PARAMETRES');
			    		$scope.user.roles.push('ROLE_STATESTIQUE_CONSULTATIONS_GLOBALE');
			    		$scope.user.roles.push('ROLE_STATESTIQUE_CONSULTATIONS_NATURE_TRANSACTION');
			    		$scope.user.roles.push('ROLE_STATESTIQUE_CONSULTATIONS_CODE_REPONSE');
			    		$scope.user.roles.push('ROLE_STATESTIQUE_CONSULTATIONS_BUG_RECONCILIATION');
			    		$scope.user.roles.push('ROLE_STATESTIQUE_CONSULTATIONS_BUG_DECLARATION');
			    		$scope.user.roles.push('ROLE_STATESTIQUE_CONSULTATIONS_RRENTABILITE_GLOBALE');
			    		$scope.user.roles.push('ROLE_DOCUMENTATION_COMPTABLE');
			    	}else{
			    		$scope.user.roles.push('ROLE_DECENTRALISEE_DECLARATIONS_AGENCE');
			    		$scope.user.roles.push('ROLE_DECENTRALISEE_STATESTIQUE_AGENCE');
			    		$scope.user.roles.push('ROLE_DASHBORD_AGENT');
			    		$scope.user.roles.push('ROLE_DOCUMENTATION_UTILISATION');
			    	}
			    	console.log($scope.user.roles);
			   }
		 
			$scope.username="";
			$scope.lastname="";
			$scope.email="";
			$scope.teleph=0;
			$scope.poste="";
			$scope.id_agence=0;
			$scope.password="";
			$scope.nom="";
			$scope.userroleid=0;
			$scope.enabled=0;
			
			$scope.saveUser= function() {
				var pw= md5($scope.password);
				$scope.InsertUser = 0;
				$http.get("/api/finduser?username=" + $scope.username).success(function(data) {
						var NbUsername= data[0][0];
						if(NbUsername==0){
							$http.get("/api/ajouteruser?username=" + $scope.username
													+ "&lastname=" + $scope.lastname
													+ "&email=" + $scope.email
													+ "&poste=" + $scope.poste
													+ "&password=" + pw
													+ "&teleph=" + $scope.teleph
													+ "&nom=" + $scope.nom
													+ "&enabled=" + $scope.enabled
													+ "&role=" + $scope.user.roles
													).success(function(data) {
														$scope.users  = data;
														$scope.InsertUser = 1;
													});
									}else{
										$scope.InsertUser = 2;
									}
								});
			};
			
				$scope.sort = function(keyname){
					$scope.sortKey = keyname;   //set the sortKey to the param passed
					$scope.reverse = !$scope.reverse; //if true make it false and vice versa
				}

		});
