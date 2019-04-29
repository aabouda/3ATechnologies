'use strict';
angular.module('newApp').controller("updateProjectController",
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
			  borderWidth: 2
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
			  endAngle: 0,
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
				  length: '100%',
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
		
		$scope.calMessage = "0";
		$scope.calSprint = "0";
		$scope.calModuleApp = "0";
		$scope.calEnvironnement = "0";
		$scope.calVerssion = "0";
		
		
	
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
				$scope.description =  data[0]["description"];
				$scope.etatProjet =  data[0]["etatProjet"];
				$scope.avancementStat = Math.round(data[0]["avancement"]) ;
				
				$scope.calMessage = Math.round(data[0]["calMessage"]) ;
				$scope.calSprint = Math.round(data[0]["calSprint"]) ;
				$scope.calModuleApp = Math.round(data[0]["calModuleApp"]) ;
				$scope.calEnvironnement = Math.round(data[0]["calEnvironnement"]) ;
				$scope.calVerssion = Math.round(data[0]["calVerssion"]) ;
				
				console.log('length : '+$('#echart_gauge').length);
				if ($('#echart_gauge').length ){ 
					  
					  var echartGauge = echarts.init(document.getElementById('echart_gauge'), theme);

					  echartGauge.setOption({
						tooltip: {
						  formatter: " {a} <br/>{b} : {c}%"
						},
						toolbox: {
						  show: true,
						  feature: {
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
						  name: 'Avancement',
						  type: 'gauge',
						  center: ['50%', '50%'],
						  startAngle: 140,
						  endAngle: -140,
						  min: 0,
						  max: 100,
						  precision: 0,
						  splitNumber: 10,
						  axisLine: {
							show: true,
							lineStyle: {
							  color: [
								[0.2, 'lightgreen'],
								[0.4, 'orange'],
								[0.8, 'skyblue'],
								[1, '#ff4500']
							  ],
							  width: 30
							}
						  },
						  axisTick: {
							show: true,
							splitNumber: 5,
							length: 8,
							lineStyle: {
							  color: '#eee',
							  width: 1,
							  type: 'solid'
							}
						  },
						  axisLabel: {
							show: true,
							formatter: function(v) {
							  switch (v + '') {
								case '10':
								  return 'a';
								case '30':
								  return 'b';
								case '60':
								  return 'c';
								case '90':
								  return 'd';
								default:
								  return '';
							  }
							},
							textStyle: {
							  color: '#333'
							}
						  },
						  splitLine: {
							show: true,
							length: 30,
							lineStyle: {
							  color: '#eee',
							  width: 2,
							  type: 'solid'
							}
						  },
						  pointer: {
							length: '100%',
							width: 8,
							color: 'auto'
						  },
						  title: {
							show: true,
							offsetCenter: ['-65%', -10],
							textStyle: {
							  color: '#333',
							  fontSize: 15
							}
						  },
						  detail: {
							show: true,
							backgroundColor: 'rgba(0,0,0,0)',
							borderWidth: 0,
							borderColor: '#ccc',
							width: 100,
							height: 40,
							offsetCenter: ['-60%', 10],
							formatter: '{value}%',
							textStyle: {
							  color: 'auto',
							  fontSize: 30
							}
						  },
						  data: [{
							value: $scope.avancementStat,
							name: 'Avancement'
						  }]
						}]
					  });

					} 
			});
			
			
			
			$scope.saveProject = function() {
				$scope.errors=null;
				$scope.message=false;
				$http.get("/api/updateAllProjet?projectID="+$scope.idProjet
						+"&membre="+$scope.membre
						+"&nameProject="+$scope.nameProject
						+"&dateDebutPrevu="+$scope.dateDebutPrevu
						+"&dateDebutReelle="+$scope.dateDebutReelle
						+"&dateFinPrevu="+$scope.dateFinPrevu
						+"&dateFinReelle="+$scope.dateFinReelle
						+"&budgetPrevu="+$scope.budgetPrevu
						+"&budgetReelle="+$scope.budgetReelle
						+"&nbRessourcePrevu="+$scope.nbRessourcePrevu
						+"&nbRessourceReelle="+$scope.nbRessourceReelle
						+"&phase="+$scope.phase
						+"&avancement="+$scope.avancement
						+"&description="+$scope.description
						+"&etatProjet="+$scope.etatProjet
						).success(
				function(data) {
						$('#myModalMessageOKModification').modal('show');
				})
				.error(function(data, status) {
					  console.error('Repos error', status, data);
					  $('#myModalMessageKOModification').modal('show');
					});
			};
			
			
		
			
			

		});
