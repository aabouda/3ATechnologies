'use strict';
angular.module('newApp').controller("testsProjectController",
		function($scope, $http, $q, $timeout, $location, $state, $filter) {

	
	
	

	if( typeof (echarts) === 'undefined'){ return; }
		console.log('init_echarts');
		  var theme = {
		  color: [
			  '#26B99A', '#34495E', '#BDC3C7', '#3498DB',
			  '#9B59B6', '#8abb6f', '#759c6a', '#bfd3b7'
		  ],

		  title: {
			  itemGap: 8,
			  textStyle: {
				  fontWeight: 'normal',
				  color: '#408829'
			  }
		  },

		  dataRange: {
			  color: ['#1f610a', '#97b58d']
		  },

		  toolbox: {
			  color: ['#408829', '#408829', '#408829', '#408829']
		  },

		  tooltip: {
			  backgroundColor: 'rgba(0,0,0,0.5)',
			  axisPointer: {
				  type: 'line',
				  lineStyle: {
					  color: '#408829',
					  type: 'dashed'
				  },
				  crossStyle: {
					  color: '#408829'
				  },
				  shadowStyle: {
					  color: 'rgba(200,200,200,0.3)'
				  }
			  }
		  },

		  dataZoom: {
			  dataBackgroundColor: '#eee',
			  fillerColor: 'rgba(64,136,41,0.2)',
			  handleColor: '#408829'
		  },
		  grid: {
			  borderWidth: 0
		  },

		  categoryAxis: {
			  axisLine: {
				  lineStyle: {
					  color: '#408829'
				  }
			  },
			  splitLine: {
				  lineStyle: {
					  color: ['#eee']
				  }
			  }
		  },

		  valueAxis: {
			  axisLine: {
				  lineStyle: {
					  color: '#408829'
				  }
			  },
			  splitArea: {
				  show: true,
				  areaStyle: {
					  color: ['rgba(250,250,250,0.1)', 'rgba(200,200,200,0.1)']
				  }
			  },
			  splitLine: {
				  lineStyle: {
					  color: ['#eee']
				  }
			  }
		  },
		  timeline: {
			  lineStyle: {
				  color: '#408829'
			  },
			  controlStyle: {
				  normal: {color: '#408829'},
				  emphasis: {color: '#408829'}
			  }
		  },

		  k: {
			  itemStyle: {
				  normal: {
					  color: '#68a54a',
					  color0: '#a9cba2',
					  lineStyle: {
						  width: 1,
						  color: '#408829',
						  color0: '#86b379'
					  }
				  }
			  }
		  },
		  map: {
			  itemStyle: {
				  normal: {
					  areaStyle: {
						  color: '#ddd'
					  },
					  label: {
						  textStyle: {
							  color: '#c12e34'
						  }
					  }
				  },
				  emphasis: {
					  areaStyle: {
						  color: '#99d2dd'
					  },
					  label: {
						  textStyle: {
							  color: '#c12e34'
						  }
					  }
				  }
			  }
		  },
		  force: {
			  itemStyle: {
				  normal: {
					  linkStyle: {
						  strokeColor: '#408829'
					  }
				  }
			  }
		  },
		  chord: {
			  padding: 4,
			  itemStyle: {
				  normal: {
					  lineStyle: {
						  width: 1,
						  color: 'rgba(128, 128, 128, 0.5)'
					  },
					  chordStyle: {
						  lineStyle: {
							  width: 1,
							  color: 'rgba(128, 128, 128, 0.5)'
						  }
					  }
				  },
				  emphasis: {
					  lineStyle: {
						  width: 1,
						  color: 'rgba(128, 128, 128, 0.5)'
					  },
					  chordStyle: {
						  lineStyle: {
							  width: 1,
							  color: 'rgba(128, 128, 128, 0.5)'
						  }
					  }
				  }
			  }
		  },
		  gauge: {
			  startAngle: 225,
			  endAngle: -45,
			  axisLine: {
				  show: true,
				  lineStyle: {
					  color: [[0.2, '#86b379'], [0.8, '#68a54a'], [1, '#408829']],
					  width: 8
				  }
			  },
			  axisTick: {
				  splitNumber: 10,
				  length: 12,
				  lineStyle: {
					  color: 'auto'
				  }
			  },
			  axisLabel: {
				  textStyle: {
					  color: 'auto'
				  }
			  },
			  splitLine: {
				  length: 18,
				  lineStyle: {
					  color: 'auto'
				  }
			  },
			  pointer: {
				  length: '90%',
				  color: 'auto'
			  },
			  title: {
				  textStyle: {
					  color: '#333'
				  }
			  },
			  detail: {
				  textStyle: {
					  color: 'auto'
				  }
			  }
		  },
		  textStyle: {
			  fontFamily: 'Arial, Verdana, sans-serif'
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
			});
			
			
			
			
			
			$scope.allExecutions = [];
			$scope.idExec =  "88";
			
			$http.get("/api/executionProjetId?projectID="+$scope.idProjet).success(function(data) {
				$scope.allExecutions = data;
			});
			  
			
			



			$scope.afficherExecution = function(idExecution) {
				$http.get(
						"/api/executionId?executionID=" + idExecution
								).success(
						function(data) {
							$scope.idExec =  data[0]["idExecution"];
							$scope.nameCompagne =  data[0]["nameCompagne"];
							$scope.idProjet =  data[0]["idProjet"];
							$scope.dateExecution =  data[0]["dateExecution"];
							$scope.dateImport =  data[0]["dateImport"];
							$scope.refDBImport =  data[0]["refDBImport"];
							$scope.description =  data[0]["description"];
							$scope.passed =  data[0]["passed"];
							$scope.failed =  data[0]["failed"];
							$scope.failedNiv1 =  data[0]["failedNiv1"];
							$scope.failedNiv2 =  data[0]["failedNiv2"];
							$scope.failedNiv3 =  data[0]["failedNiv3"];
							$scope.failedNiv4 =  data[0]["failedNiv4"];
							$scope.blocked =  data[0]["blocked"];
							$scope.notRun =  data[0]["notRun"];
							$scope.toatl =  data[0]["toatl"];
							$scope.Exec =  parseInt($scope.passed) + parseInt($scope.failed) + parseInt($scope.blocked);
							
							
						/////
							console.log('length : '+$('#echart_donutAll').length);
							if ($('#echart_donutAll').length ){  
								  
								  var echartDonut = echarts.init(document.getElementById('echart_donutAll'), theme);
								  
								  echartDonut.setOption({
									tooltip: {
									  trigger: 'item',
									  formatter: "{a} <br/>{b} : {c} ({d}%)"
									},
									calculable: true,
									legend: {
									  x: 'center',
									  y: 'bottom',
									  data: ['Run', 'Not Run']
									},
									toolbox: {
									  show: true,
									  feature: {
										magicType: {
										  show: true,
										  type: ['pie', 'funnel'],
										  option: {
											funnel: {
											  x: '25%',
											  width: '50%',
											  funnelAlign: 'center',
											  max: 1548
											}
										  }
										},
										restore: {
										  show: true,
										  title: "Restore"
										},
										saveAsImage: {
										  show: true,
										  title: "Save Image"
										}
									  }
									},
									series: [{
									  name: 'Access to the resource',
									  type: 'pie',
									  radius: ['35%', '55%'],
									  itemStyle: {
										normal: {
										  label: {
											show: true
										  },
										  labelLine: {
											show: true
										  }
										},
										emphasis: {
										  label: {
											show: true,
											position: 'center',
											textStyle: {
											  fontSize: '14',
											  fontWeight: 'normal'
											}
										  }
										}
									  },
									  data: [{
										value:  parseInt($scope.Exec),
										name: 'Run'
									  }, {
										value:  parseInt($scope.notRun),
										name: 'Not Run'
									  }]
									}]
								  });

								} 
							//////////////////////////
							
						/////
							console.log('length : '+$('#echart_donutBug').length);
							if ($('#echart_donutBug').length ){  
								  
								  var echartDonut = echarts.init(document.getElementById('echart_donutBug'), theme);
								  
								  echartDonut.setOption({
									tooltip: {
									  trigger: 'item',
									  formatter: "{a} <br/>{b} : {c} ({d}%)"
									},
									calculable: true,
									legend: {
									  x: 'center',
									  y: 'bottom',
									  data: ['Graphyque', 'Mineur', 'Majeur', 'Bloquante']
									},
									toolbox: {
									  show: true,
									  feature: {
										magicType: {
										  show: true,
										  type: ['pie', 'funnel'],
										  option: {
											funnel: {
											  x: '25%',
											  width: '50%',
											  funnelAlign: 'center',
											  max: 1548
											}
										  }
										},
										restore: {
										  show: true,
										  title: "Restore"
										},
										saveAsImage: {
										  show: true,
										  title: "Save Image"
										}
									  }
									},
									series: [{
									  name: 'Access to the resource',
									  type: 'pie',
									  radius: ['35%', '55%'],
									  itemStyle: {
										normal: {
										  label: {
											show: true
										  },
										  labelLine: {
											show: true
										  }
										},
										emphasis: {
										  label: {
											show: true,
											position: 'center',
											textStyle: {
											  fontSize: '14',
											  fontWeight: 'normal'
											}
										  }
										}
									  },
									  data: [{
										value:  parseInt($scope.failedNiv1),
										name: 'Graphyque'
									  }, {
										value:  parseInt($scope.failedNiv2),
										name: 'Mineur'
									  }, {
										value:  parseInt($scope.failedNiv3),
										name: 'Majeur'
									  }, {
										value:  parseInt($scope.failedNiv4),
										name: 'Bloquante'
									  }]
									}]
								  });

								} 

							///////////////////////
							
						/////
							console.log('length : '+$('#echart_donutAllExec').length);
							if ($('#echart_donutAllExec').length ){  
								  
								  var echartDonut = echarts.init(document.getElementById('echart_donutAllExec'), theme);
								  
									
									
									
								  echartDonut.setOption({
									tooltip: {
									  trigger: 'item',
									  formatter: "{a} <br/>{b} : {c} ({d}%)"
									},
									calculable: true,
									legend: {
									  x: 'center',
									  y: 'bottom',
									  data: ['Passed', 'Failed', 'Blocked','Not Run']
									},
									toolbox: {
									  show: true,
									  feature: {
										magicType: {
										  show: true,
										  type: ['pie', 'funnel'],
										  option: {
											funnel: {
											  x: '25%',
											  width: '50%',
											  funnelAlign: 'center',
											  max: 1548
											}
										  }
										},
										restore: {
										  show: true,
										  title: "Restore"
										},
										saveAsImage: {
										  show: true,
										  title: "Save Image"
										}
									  }
									},
									series: [{
									  name: 'Access to the resource',
									  type: 'pie',
									  radius: ['35%', '55%'],
									  itemStyle: {
										normal: {
										  label: {
											show: true
										  },
										  labelLine: {
											show: true
										  }
										},
										emphasis: {
										  label: {
											show: true,
											position: 'center',
											textStyle: {
											  fontSize: '14',
											  fontWeight: 'normal'
											}
										  }
										}
									  },
									  data: [{
										value:  parseInt($scope.passed),
										name: 'Passed'
									  }, {
										value:  parseInt($scope.failed),
										name: 'Failed'
									  }, {
										value:  parseInt($scope.blocked),
										name: 'Blocked'
									  }, {
										value:  parseInt($scope.notRun),
										name: 'Not Run'
										}]
									}]
								  });

								} 
							
							
							/////////////////////
							
							
							
							
							
							
						});
			};





			

});
