'use strict';
angular.module('newApp').controller("societeController",
		function($scope, $http, $q, $timeout ,$location ,$stateParams) {

			$scope.listeSociete = [];
			$http.get("/api/societe").success(function(data) {
				$scope.listeSociete = data;

			});
			
			
			
			$scope.disabler = function(){
	    	        return true;
			}
			
				$scope.sort = function(keyname){
					$scope.sortKey = keyname;   //set the sortKey to the param passed
					$scope.reverse = !$scope.reverse; //if true make it false and vice versa
				}

		});
