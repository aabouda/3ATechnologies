'use strict';
angular.module('newApp').controller("bugsProjectController",
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
		
		
		$scope.Categorie = "Etat";
		$scope.idCategorie =  "1";

			$scope.afficherExecution = function(idCategorie) {
				
				if (idCategorie==1) {
					$scope.Categorie = "Etat";
				} else if (idCategorie==2) {
					$scope.Categorie = "Impact";
				} else if (idCategorie==3) {
					$scope.Categorie = "Environnement concerne";
				} else if (idCategorie==4) {
					$scope.Categorie = "Version";
				} else if (idCategorie==5) {
					$scope.Categorie = "Environnement - Etat";
				} else if (idCategorie==6) {
					$scope.Categorie = "Environnement - Impact";
				} else if (idCategorie==7) {
					$scope.Categorie = "Version - Etat";
				} else {
					$scope.Categorie = "Version - Impact";
				}
				
				$scope.idCategorie =  idCategorie;
				$http.get(
						"/api/bugProjetId?projectID=" + $scope.idProjet + "&idCategorie=" + idCategorie
								).success(
						function(data) {
							
							$scope.labelle = [];
							$scope.session = {'screens': []};
							$scope.line = {'screens': []};
							var val = 0;
							
							
							
							for(var i= 0; i < data.length; i++)
							{
								$scope.labelle[i] = data[i][1];
								$scope.session.screens.push({ 'value': data[i][0], 'name': data[i][1] });
								val = 2010 + i;
								$scope.line.screens.push({ 'year': val.toString() , 'value': data[i][0] });
							}
							
							console.log($scope.idCategorie);
							console.log($scope.line.screens);
							
							
							$scope.nameCompagne =  data[0]["nameCompagne"];
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
							console.log('length : '+$('#echart_donutEtat').length);
							if ($('#echart_donutEtat').length ){  
								  
								  var echartDonut = echarts.init(document.getElementById('echart_donutEtat'), theme);
								  
								  echartDonut.setOption({
									tooltip: {
									  trigger: 'item',
									  formatter: "{a} <br/>{b} : {c} ({d}%)"
									},
									calculable: true,
									legend: {
									  x: 'center',
									  y: 'bottom',
									  data: $scope.labelle
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
									  data: $scope.session.screens
									}]
								  });

								} 
							//////////////////////////
							
						/////
							if ($('#graph_line').length ){
								
								Morris.Line({
								  element: 'graph_line',
								  xkey: 'year',
								  ykeys: ['value'],
								  labels: ['Value'],
								  hideHover: 'auto',
								  lineColors: ['#26B99A', '#34495E', '#ACADAC', '#3498DB'],
								  data: $scope.line.screens,
								  resize: true
								});

								$MENU_TOGGLE.on('click', function() {
								  $(window).resize();
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
