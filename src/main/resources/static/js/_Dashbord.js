'use strict';
angular.module('newApp')
.controller("DashbordAgentController",function ($scope,$http,$filter, $q, $timeout,$location,$state) {
	
	var config = {  headers: { 
   		'Content-Type': 'application/json'
   		} 
    }; 
	
	$scope.AllBug = [];
	$scope.TopGab = [];
	$scope.BedGab = [];
    $http.get("/api/TopGab").success(function(data) {
			$scope.TopGab = data;
		
		});
    
    
    
    $http.get("/api/BedGab").success(function(data) {
			$scope.BedGab = data;
		
		});
    
	
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
	  $scope.ConsultationCodeReponse = [];
	  $scope.dateDebut= $filter('date')(new Date(), 'yyyy-MM-dd');
	  $scope.dateFin= $filter('date')(new Date(), 'yyyy-MM-dd');
	  $scope.dateDebut2= $filter('date')(new Date(), 'dd/MM/yyyy');
	  $scope.dateFin2= $filter('date')(new Date(), 'dd/MM/yyyy');
	  var loadPagination = function(D1,D2) {
	  $scope.dateDebut= $filter('date')(new Date(), 'yyyy-MM-dd');
	  $scope.dateFin= $filter('date')(new Date(), 'yyyy-MM-dd');
	
	  
	  	$http.get("/api/dashBoard").success(function(data) {
	  		$scope.AllBug = data;
	  		
	  		$scope.visaARRIERE5 = data[0]["visaARRIERE5"];
	  		$scope.visaARRIERE30 = data[0]["visaARRIERE30"];
	  		$scope.visaARRIERE90 = data[0]["visaARRIERE90"];
	  		$scope.mcARRIERE5 = data[0]["mcARRIERE5"];
	  		$scope.mcARRIERE30 = data[0]["mcARRIERE30"];
	  		$scope.mcARRIERE90 = data[0]["mcARRIERE90"];
	  		$scope.visaTCNC5 = data[0]["visaTCNC5"];
	  		$scope.visaTCNC30 = data[0]["visaTCNC30"];
	  		$scope.visaTCNC90 = data[0]["visaTCNC90"];
	  		$scope.mcTCNC5 = data[0]["mcTCNC5"];
	  		$scope.mcTCNC30 = data[0]["mcTCNC30"];
	  		$scope.mcTCNC90 = data[0]["mcTCNC90"];
	  		$scope.mcBUGARRIEREES5 = data[0]["mcBUGARRIEREES5"];
	  		$scope.mcBUGARRIEREES30 = data[0]["mcBUGARRIEREES30"];
	  		$scope.mcBUGARRIEREES90 = data[0]["mcBUGARRIEREES90"];
	  		$scope.visaBUGARRIEREES5 = data[0]["visaBUGARRIEREES5"];
	  		$scope.visaBUGARRIEREES30 = data[0]["visaBUGARRIEREES30"];
	  		$scope.visaBUGARRIEREES90 = data[0]["visaBUGARRIEREES90"];
	  		$scope.visaBUGTCNC5 = data[0]["visaBUGTCNC5"];
			$scope.visaBUGTCNC30 = data[0]["visaBUGTCNC30"];
			$scope.visaBUGTCNC90 = data[0]["visaBUGTCNC90"];
			$scope.mcBUGTCNC5 = data[0]["mcBUGTCNC5"];
			$scope.mcBUGTCNC30 = data[0]["mcBUGTCNC30"];
			$scope.mcBUGTCNC90 = data[0]["mcBUGTCNC90"];
	  		
	  		
	  		
	  		
	  		
	  		$scope.mc_arrierees = parseInt($scope.mcARRIERE5);
	  		$scope.mc_arrierees_Anomalie = parseInt($scope.mcBUGARRIEREES5);
	  		$scope.mc_transactions_compensees_non_collectees = parseInt($scope.mcTCNC5);
	  		$scope.mc_anomalie_transactions_compensees_non_collectees = parseInt($scope.mcBUGTCNC5);
	  		$scope.visa_arrierees = parseInt($scope.visaARRIERE5);
	  		$scope.visa_arrierees_Anomalie = parseInt($scope.visaBUGARRIEREES5);
	  		$scope.visa_transactions_compensees_non_collectees = parseInt($scope.visaTCNC5);
	  		$scope.visa_anomalie_transactions_compensees_non_collectees = parseInt($scope.visaBUGTCNC5);
			console.log('length : '+$('#echart_donut').length);
			if ($('#echart_donut').length ){  
				  var echartDonut = echarts.init(document.getElementById('echart_donut'), theme);
				  refresh(echartDonut);
			}
		});
	  	
			
			
		/////
		var refresh = function (chart){
			chart.setOption({
				tooltip: {
				  trigger: 'item',
				  formatter: "{a} <br/>{b} : {c} ({d}%)"
				},
				calculable: true,
				legend: {
				  x: 'center',
				  y: 'bottom',
				  data: ["MC Arr",  "MC Arr Ano", "MC TCNC", "MC Ano TCNC", "VISA Arr",  "VISA Arr Ano", "VISA TCNC", "VISA Ano TCNC"]
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
						value: $scope.mc_arrierees,
						name: "MC Arr"
					},
					{
						value: $scope.mc_arrierees_Anomalie,
						name: "MC Arr Ano"
					},
					{
						value: $scope.mc_transactions_compensees_non_collectees,
						name: "MC TCNC"
					},
					{
						value: $scope.mc_anomalie_transactions_compensees_non_collectees,
						name: "MC Ano TCNC"
					},
					{
						value: $scope.visa_arrierees,
						name: "VISA Arr"
					},
					{
						value: $scope.visa_arrierees_Anomalie,
						name: "VISA Arr Ano"
					},
					{
						value: $scope.visa_transactions_compensees_non_collectees,
						name: "VISA TCNC"
					},
					{
						value: $scope.visa_anomalie_transactions_compensees_non_collectees,
						name: "VISA Ano TCNC"
					}]
				}]
			  });
		}	
			/////
	  }
	  
		loadPagination($scope.dateDebut2, $scope.dateFin2);
	    $scope.chargerPagination = function() {
	    	$scope.dateDebut22= $filter('date')(new Date($scope.dateDebut), 'dd/MM/yyyy');
			$scope.dateFin22= $filter('date')(new Date($scope.dateFin), 'dd/MM/yyyy');
			loadPagination($scope.dateDebut22, $scope.dateFin22);
	    }
 
   
});
