'use strict';
angular
		.module('newApp')
		.controller(
				"StatistiqueallController",
				function($scope, $http, $q, $filter, $stateParams, $timeout, $location, $state) {
					
					/////////////
					
				    
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
					
					///////////////

					$scope.dateDebut= $filter('date')(new Date(), 'dd/MM/yyyy');
					$scope.dateFin= $filter('date')(new Date(), 'dd/MM/yyyy');
						
					$scope.dateDebut2= $filter('date')(new Date(), 'dd/MM/yyyy');
					$scope.dateFin2= $filter('date')(new Date(), 'dd/MM/yyyy');

					$scope.isLoading = false;
					$scope.code_dab = "";
					$scope.agence = "";
					$scope.isShown = "Detail";
					$scope.donneeStat = [];
					$scope.sort = function(keyname) {
						$scope.sortKey = keyname; 
						$scope.reverse = !$scope.reverse; 
					}

					
					
					var loadPagination = function(D1,D2) {
							$scope.Consultationglobale = [];
							$scope.isLoading = true;
							$http.get("/api/statAll?dateDebut=" + D1 + "&dateFin=" + D2).success(function(data) {
								$scope.donneeStat = data;
								$scope.load=true;
								$scope.isLoading = false;
								
								$scope.stb_labelle = data[0]["labelle"];
								$scope.stb_nbre_transaction = data[0]["nbOperation"];
								$scope.stb_montant = data[0]["montant"];

								var a_stb_nbre_transaction = parseInt($scope.stb_nbre_transaction);
								var a_stb_montant = parseInt($scope.stb_montant);
								
								$scope.nat_labelle = data[1]["labelle"];
								$scope.nat_nbre_transaction = data[1]["nbOperation"];
								$scope.nat_montant = data[1]["montant"];

								var a_nat_nbre_transaction = parseInt($scope.nat_nbre_transaction);
								var a_nat_montant = parseInt($scope.nat_montant);
								
								
								$scope.visa_labelle = data[2]["labelle"];
								$scope.visa_nbre_transaction = data[2]["nbOperation"];
								$scope.visa_montant = data[2]["montant"];

								var a_visa_nbre_transaction = parseInt($scope.visa_nbre_transaction);
								var a_visa_montant = parseInt($scope.visa_montant);
								
								
								$scope.mcd_labelle = data[3]["labelle"];
								$scope.mcd_nbre_transaction = data[3]["nbOperation"];
								$scope.mcd_montant = data[3]["montant"];

								var a_mcd_nbre_transaction = parseInt($scope.mcd_nbre_transaction);
								var a_mcd_montant = parseInt($scope.mcd_montant);
								
								/////

								console.log('length : '+$('#echart_donut').length);
								if ($('#echart_donut').length ){  
									  
									  var echartDonut = echarts.init(document.getElementById('echart_donut'), theme);
									  
									  echartDonut.setOption({
										tooltip: {
										  trigger: 'item',
										  formatter: "{a} <br/>{b} : {c} ({d}%)"
										},
										calculable: true,
										legend: {
										  x: 'center',
										  y: 'bottom',
										  data: [$scope.stb_labelle, $scope.nat_labelle, $scope.visa_labelle, $scope.mcd_labelle]
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
											value: a_stb_montant,
											name: $scope.stb_labelle
										  }, {
											value: a_nat_montant,
											name: $scope.nat_labelle
										  }, {
											value: a_visa_montant,
											name: $scope.visa_labelle
										  }, {
											value: a_mcd_montant,
											name: $scope.mcd_labelle
										  }]
										}]
									  });

									} 
								
								
								
								/////
								
								
								console.log('length : '+$('#echart_donut2').length);
								if ($('#echart_donut2').length ){  
									  
									  var echartDonut = echarts.init(document.getElementById('echart_donut2'), theme);
									  
									  echartDonut.setOption({
										tooltip: {
										  trigger: 'item',
										  formatter: "{a} <br/>{b} : {c} ({d}%)"
										},
										calculable: true,
										legend: {
										  x: 'center',
										  y: 'bottom',
										  data: [$scope.stb_labelle, $scope.nat_labelle, $scope.visa_labelle, $scope.mcd_labelle]
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
												value: a_stb_nbre_transaction,
												name: $scope.stb_labelle
											  }, {
												value: a_nat_nbre_transaction,
												name: $scope.nat_labelle
											  }, {
												value: a_visa_nbre_transaction,
												name: $scope.visa_labelle
											  }, {
												value: a_mcd_nbre_transaction,
												name: $scope.mcd_labelle
											  }]
										}]
									  });

									} 
							});
					};
					

					$scope.courrier = "true";
					loadPagination($scope.dateDebut2, $scope.dateFin2);
				    $scope.chargerPagination = function() {
				    	$scope.dateDebut22= $scope.dateDebut;
						$scope.dateFin22= $scope.dateFin;
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
							loadPagination($scope.dateDebut22, $scope.dateFin22);
						}
				    }
				 
				    
						
				});
