'use strict';
angular.module('newApp')
.controller("StatController",function ($scope,$http, $q, $timeout,$location,$state) {
	
	$scope.donneeStat = [];
    $scope.getAll = function(){
    	$http.get("/api/statAll").success(function(data){
    		$scope.donneeStat = data;
    	});
    } 
    
    $scope.getAll = function(){
    	$http.get("/api/statTypeReponse").success(function(data){
    		$scope.donneeStat = data;
    	});
    } 
    
    $scope.getAll = function(){
    	$http.get("/api/statTypeOperation").success(function(data){
    		$scope.donneeStat = data;
    	});
    } 

});
