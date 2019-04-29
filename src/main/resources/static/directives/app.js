'use strict';

/**
 * @ngdoc overview
 * @name newappApp
 * @description # newappApp
 * 
 * Main module of the application.
 */
var MakeApp = angular
  .module('newApp', [
    'ngAnimate',
    'ngCookies',
    'ngResource',
    'ui.router',
    'ngSanitize',
    'ngTouch',
    'angularUtils.directives.dirPagination',
    'ngFileUpload',
    'checklist-model',
    '720kb.datepicker'
  ])
  .filter('numberFilter', function() {
return function(number) {
    	 var parts = Number(number).toFixed(3).toString().split(".");
            parts[0] = parts[0].replace(/\B(?=(\d{3})+(?!\d))/g, " ");
            return parts.join(".");
    };
})
  .config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider){
	// Redirect any unmatched url
	    $urlRouterProvider.otherwise("/Dashbord");
	    
	// canSee
	    
		  
		// echart Donut
		  
	    
	    $stateProvider
        // Custom states
        .state('login', {
            url: "/login",
            templateUrl: "auth/login.html",
            data: {pageTitle: 'Login'},
            controller: "LoginController",
        })
        .state('dashbord', {
            url: "/Dashbord",
            templateUrl: "Dashbord.html",
            data: {pageTitle: 'dashbord'},
            controller: "DashbordController",
        })
        .state('_dashbord', {
            url: "/_Dashbord",
            templateUrl: "_Dashbord.html",
            data: {pageTitle: '_dashbord'},
            controller: "DashbordAgentController",
        })
        .state('addProject', {
            url: "/addProject",
            templateUrl: "projet/addProject.html",
            data: {pageTitle: 'Ajouter Projet'},
            controller: "addProjectController",
        })
        
        .state('affectRessourceProject', {
            url: "/affectRessourceProject/:param1",
            templateUrl: "projet/affectRessourceProject.html",
            data: {pageTitle: 'Ajouter Projet'},
            controller: "affectRessourceProjectController",
        })
        
        
        .state('showAllProject', {
            url: "/showAllProject",
            templateUrl: "projet/showAllProject2.html",
            data: {pageTitle: 'Ajouter Projet'},
            controller: "showAllProjectController",
        })
        
        
        .state('detailleProjet', {
            url: "/detailleProjet/generale/:param1",
            templateUrl: "detailleProjet/generale.html",
            data: {pageTitle: 'Modifier Projet'},
            controller: "updateProjectController",
        })
        
        .state('projectMembres', {
            url: "/detailleProjet/membres/:param1",
            templateUrl: "detailleProjet/membres.html",
            data: {pageTitle: 'Membres Projet'},
            controller: "membresProjectController",
        })
        
         .state('projectSprint', {
            url: "/detailleProjet/sprint/:param1",
            templateUrl: "detailleProjet/sprint.html",
            data: {pageTitle: 'Sprint Projet'},
            controller: "sprintProjectController",
        })
        
         .state('projectGantt', {
            url: "/detailleProjet/gantt/:param1",
            templateUrl: "detailleProjet/gantt.html",
            data: {pageTitle: 'Gantt Projet'},
            controller: "ganttProjectController",
        })
        
        
         .state('projectTimesheet', {
            url: "/detailleProjet/timesheet/:param1",
            templateUrl: "detailleProjet/timesheet.html",
            data: {pageTitle: 'timesheet Projet'},
            controller: "timesheetProjectController",
        })
        
        
        
        
         .state('projectTests', {
            url: "/detailleProjet/tests/:param1",
            templateUrl: "detailleProjet/tests.html",
            data: {pageTitle: 'Tests Projet'},
            controller: "testsProjectController",
        })
        
        
        
       .state('projectBugs', {
            url: "/detailleProjet/bugs/:param1",
            templateUrl: "detailleProjet/bugs.html",
            data: {pageTitle: 'Bugs Projet'},
            controller: "bugsProjectController",
        })
        
         .state('projectAlertes', {
            url: "/detailleProjet/alertes/:param1",
            templateUrl: "detailleProjet/alertes.html",
            data: {pageTitle: 'Alertes Projet'},
            controller: "alertesProjectController",
        })
        
        
        
        
        .state('projectMessage', {
            url: "/detailleProjet/messages/:param1",
            templateUrl: "detailleProjet/messages.html",
            data: {pageTitle: 'Messages'},
            controller: "messagesProjectController",
        })
        
        
        
        
         .state('projectSad', {
            url: "/detailleProjet/sad/:param1",
            templateUrl: "detailleProjet/sad.html",
            data: {pageTitle: 'Sad Projet'},
            controller: "sadProjectController",
        })
        
        
        .state('projectProjectionTendance', {
            url: "/detailleProjet/projectionTendance/:param1",
            templateUrl: "detailleProjet/projectionTendance.html",
            data: {pageTitle: 'Project Projection Projet'},
            controller: "projectionTendanceProjectController",
        })
        
        
        
         .state('projectRelease', {
            url: "/detailleProjet/release/:param1",
            templateUrl: "detailleProjet/release.html",
            data: {pageTitle: 'Release Projet'},
            controller: "releaseProjectController",
        })
        
         .state('projectEnvironnements', {
            url: "/detailleProjet/environnement/:param1",
            templateUrl: "detailleProjet/environnement.html",
            data: {pageTitle: 'Environnement Projet'},
            controller: "environnementProjectController",
        })

        
          .state('projectVerssion', {
            url: "/detailleProjet/verssion/:param1",
            templateUrl: "detailleProjet/verssion.html",
            data: {pageTitle: 'Verssion Projet'},
            controller: "verssionProjectController",
        })
        
        
         .state('projectModuleApp', {
            url: "/detailleProjet/moduleApp/:param1",
            templateUrl: "detailleProjet/moduleApp.html",
            data: {pageTitle: 'ModuleApp Projet'},
            controller: "moduleAppProjectController",
        })
        
         .state('projectDocuments', {
            url: "/detailleProjet/documents/:param1",
            templateUrl: "detailleProjet/documents.html",
            data: {pageTitle: 'Documents Projet'},
            controller: "documentsProjectController",
        })
        
        
        
        
         .state('projectRisque', {
            url: "/detailleProjet/risque/:param1",
            templateUrl: "detailleProjet/risque.html",
            data: {pageTitle: 'Risque Projet'},
            controller: "risqueProjectController",
        })
        
        
        
        
         .state('projectParametres', {
            url: "/detailleProjet/parametres/:param1",
            templateUrl: "detailleProjet/parametres.html",
            data: {pageTitle: 'Parametres Projet'},
            controller: "parametresProjectController",
        })
        
         .state('regleAlertes', {
            url: "/detailleProjet/regleAlertes/:param1",
            templateUrl: "detailleProjet/regleAlertes.html",
            data: {pageTitle: 'Gestion des Alertes'},
            controller: "regleAlertesController",
        })
        
        
        
        
        
        
        .state('endProject', {
            url: "/endProject",
            templateUrl: "projet/endProject.html",
            data: {pageTitle: 'Ajouter Projet'},
            controller: "endProjectController",
        })
       
		
        
        .state('societe', {
            url: "/societe",
            templateUrl: "societe/societe.html",
            data: {pageTitle: 'societe'},
            controller: "societeController",
        })
        
        
        .state('utilisation', {
            url: "/utilisation",
            templateUrl: "documentation/utilisation.html",
            data: {pageTitle: 'utilisation'},
            controller: "utilisationController",
        })
        
  
		
	.state('ajouteruser', {
            url: "/ajouteruser",
            templateUrl: "user/ajouteruser.html",
            data: {pageTitle: 'ajouteruser'},
            controller: "UserController",
        })
	
	.state('gestionuser', {
            url: "/gestionuser",
            templateUrl: "user/gestionuser.html",
            data: {pageTitle: 'gestionuser'},
            controller: "UserController",
        })
		
	.state('modifierutilisateur', {
            url: "/modifierutilisateur/:userId",
            templateUrl: "user/modifierutilisateur.html",
            data: {pageTitle: 'modifier utilisateur'},
            controller: "UserUpdateController",
        })
       
  }])
  .value('baseUrl','http://127.0.0.1:8091')
 // .value('baseUrl','http://31.193.138.167:8080')
  .value('actuelUrl','/api/actuel')
  .value('loginUrl','/appLogin')
  .value('logoutUrl','/logout')
  .value('rolesActuelsUrl','/api/user/roles')



// Route State Load Spinner(used on page or content load)
MakeApp.directive('ngSpinnerLoader', ['$rootScope',
    function($rootScope) {
        return {
            link: function(scope, element, attrs) {
                // by defult hide the spinner bar
                element.addClass('hide'); // hide spinner bar by default
                // display the spinner bar whenever the route changes(the
				// content part started loading)
                $rootScope.$on('$routeChangeStart', function() {
                    element.removeClass('hide'); // show spinner bar
                });
                // hide the spinner bar on rounte change success(after the
				// content loaded)
                $rootScope.$on('$routeChangeSuccess', function() {
                    setTimeout(function(){
                        element.addClass('hide'); // hide spinner bar
                    },500);
                    $("html, body").animate({
                        scrollTop: 0
                    }, 500);   
                });
            }
        };
    }
])