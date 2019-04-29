angular.module('newApp').controller('mainCtrl',
    ['$scope', 'applicationService', 'quickViewService', 'builderService', 'pluginsService', '$location',
     '$http','$window','$rootScope','$state','baseUrl','actuelUrl','logoutUrl','rolesActuelsUrl',
        function ($scope, applicationService, quickViewService, builderService, pluginsService, $location, 
        		$http,$window,$rootScope,$state,baseUrl,actuelUrl,logoutUrl,rolesActuelsUrl) {
        //Support requests with session
    	$http.defaults.withCredentials = true;
    	$rootScope.loggedIn = false;
    	
    	
    	
    	//Langue Français
    	 ////////////DashBoard
    	$scope.lblDBTotalProjet = "Total Projet";
    	$scope.lblDBAlerte = "Alertes";
    	$scope.lblDBNbCollab = "Nombre des collaborateurs";
    	$scope.lblDBAvancementProjet = "Avancement des projets";
    	$scope.lblDBBudget = "Budget";
    	$scope.lblDNbAlertesProjet = "Nombre des alertes par projet";
    	$scope.lblDBComplete = "Complete";
    	$scope.lblDBProductiviteProjet =  "Productivite par projet";
    	$scope.lblDBAvancement = "Avancement";
    	$scope.lblDBActivitesRecentes = "Activites Recentes";
    	
    	////////////Add Projet
    	$scope.lblAddProjetDesc = "Description generale";
    	$scope.lblAddProjetNom = "Nom du Projet";
    	$scope.lblAddProjetDateDebutPrevu = "Date debut prevu"; 
    	$scope.lblAddProjetDateFinPrevu = "Date fin prevu";
    	$scope.lblAddProjetBudgetPrevu = "Budget prevu";
    	$scope.lblAddProjetNbRessources = "Nb. Ressources prevu";
    	$scope.lblAddProjetPhase = "Phase";
    	$scope.lblAddProjetDescription = "Description";
    	$scope.lblAddProjetAjouter = "Ajouter";
    	
    	
    	
    	$scope.lblAddProjetDateDebutReelle = "Date debut Reelle";
    	$scope.lblAddProjetDateFinRelle = "Date fin Relle";
    	$scope.lblAddProjetBudgetReelle = "Budget Reelle";
    	$scope.lblAddProjetNbRessourcesReelle = "Nb. Ressources Reelle";
    	$scope.lblAddProjetAvancement = "Avancement";
    	$scope.lblAddProjetValider = "Valider";
    	
    	////////////Affect Ressource Projet
    	$scope.lblAffectRessourceChoisirEquipe = "Choisir l'equipe";
    	$scope.lblAffectRessourceChercher = "Chercher";
    	$scope.lblAffectRessourceMemebre = "Memebre";
    	$scope.lblAffectRessourceRole = "Role";
    	$scope.lblAffectRessourceAction = "Action";
    	$scope.lblAffectRessourceAffecter = "Affecter";
    	$scope.lblAffectRessourceSupprimer = "Supprimer";
    	
    	////////////Show All Projet
    	$scope.lblListeProjet = "LISTE DES PROJETS";
    	$scope.lblListeProjetName = "Projet";
    	$scope.lblListeProjetDateDebutReelle = "Date Debut Réelle";	
    	$scope.lblListeProjetDateFinReelle = "Date Fin Réelle";	
    	$scope.lblListeProjetStatus = "Status";
    	$scope.lblListeProjetRessources = "Ressources";	
    	$scope.lblListeProjetAction = "Action";
    	$scope.lblListeProjetDateDebutPrevu = "Date Début prévu";
    	$scope.lblListeProjetDateFinPrevu = "Date Fin prévu";
    	$scope.lblListeProjetComplete = "Complete";
    	$scope.lblListeProjetAVenir = "A venir";
    	$scope.lblListeProjetEnCours = "En cours";
    	$scope.lblListeProjetCloturer = "ClÃ´turer";
    	$scope.lblListeProjetAttendu = "Attendu";
    	$scope.lblListeProjetReelle = "Réelle";
    	$scope.lblListeProjetDetail = "Détail";
    	$scope.lblListeProjetCloturer = "Côturer";
    	$scope.lblListeProjetReOuvrir = "Re-Ouvrir";
    	
        ////////////Top_Menu
        $scope.lblProjet = "Projet" ;
        $scope.lblMembre = "Membre(s)" ;
        $scope.lblSprint = "Sprint(s)" ;
        $scope.lblGantt = "Gantt" ;
        $scope.lblTest = "Test(s)" ;
        $scope.lblBug = "Bug(s)" ;
        $scope.lblRelease = "Release(s)" ;
        $scope.lblAlerte = "Alerte(s)" ;
        $scope.lblMessage = "Message(s)" ;
        $scope.lblSAD = "SAD" ;
        $scope.lblProjection = "Projection/Tendance" ;
        $scope.lblDocument = "Document(s)" ;
        $scope.lblModules = "Modules(s)" ;
        $scope.lblEnvironnement = "Environnement(s)" ;
        $scope.lblVerssion = "Verssion(s)" ;
        $scope.lblTimesheet = "Timesheet" ;
        $scope.lblRisque = "Risque(s)" ;
        $scope.lblParametres = "Configuration" ;
        
        ///////////Projet.DetailProject.Gant
        $scope.lblganttListeSprints = "Liste des Sprints";
        $scope.lblganttDateDebut = "Date Debut";
        $scope.lblganttDateFin = "Date Fin";
        $scope.lblganttHeure = "Heure";
        $scope.lblganttStatut = "Statut";
        $scope.lblganttStatutAFaire = "A faire";
        $scope.lblganttStatutEnTraitement = "En traitement";
        $scope.lblganttStatutAValider = "A valider";
        $scope.lblganttStatutComplete = "Complete";
        $scope.lblganttAvancement = "Avancement";
        $scope.lblganttModule = "Module";
        $scope.lblganttType = "Type";
        $scope.lblganttTypeAmelioration = "Amelioration";
        $scope.lblganttTypeCorrection = "Correction";
        $scope.lblganttTypeModification = "Modification";
        $scope.lblganttTypeAjout = "Ajout";
        $scope.lblganttTypeProbleme = "Probleme";
        $scope.lblganttPriorite = "Priorite";
        $scope.lblganttUrgente = "Urgente";
        $scope.lblganttElevee = "Elevee";
        $scope.lblganttNormale = "Normale";
        $scope.lblganttBasse = "Basse";
        $scope.lblganttComplexite = "Complexite";
        
        
        ///////////Projet.DetailProject.Bug
        $scope.lblBug = "Bug";
        $scope.lblBugCategorie = "Categorie";
        $scope.lblBugEtat = "Etat";
        $scope.lblBugImpact = "Impact";
        $scope.lblBugEnvironnement = "Environnement concerne";
        $scope.lblBugVersion = "Version";
        
    	
    	$http.get(baseUrl+rolesActuelsUrl).success(function(resp,status,config,headers){
        	console.log("Got current roles");
        	$rootScope.roles = resp;
        	//Links visibility
        	$rootScope.canSee = function(role){
        		//console.log('can see : '+resp.roles.includes(role));
    	    	return $rootScope.roles.includes(role);  	
    	    }
        }).error(function(data,status,config,headers){
            //In case of error
            console.log('error : ' + status);
        });
    	
    	$(document).ready(function () {
                applicationService.init();
                quickViewService.init();
                builderService.init();
                pluginsService.init();
                Dropzone.autoDiscover = false;
            });
            
            console.log("main ctrl");
            console.log("Actuel URL : "+baseUrl+actuelUrl);
            $http.get(baseUrl+actuelUrl).success(function(data,status,config,headers){
            	console.log("Active session Success");
            	$rootScope.loggedIn = true;
            	$rootScope.user = data;
            }).error(function(data,status,config,headers){
                //In case of error
                console.log('error : ' + status);
                $state.go("login");
            });
            
            $scope.logout=function(){
            	$http.get(baseUrl+logoutUrl).success(function(data,status,config,headers){
                	console.log("Logout Success");
                	$rootScope.loggedIn = false;
                	$rootScope.user = null;
                	$state.go("login");
                }).error(function(data,status,config,headers){
                    //In case of error
                    console.log('logout error : ' + status);
                   
                });
            }
            $scope.$on('$viewContentLoaded', function () {
                pluginsService.init();
                applicationService.customScroll();
                applicationService.handlePanelAction();
                $('.nav.nav-sidebar .nav-active').removeClass('nav-active active');
                $('.nav.nav-sidebar .active:not(.nav-parent)').closest('.nav-parent').addClass('nav-active active');

                if($location.$$path == '/' || $location.$$path == '/layout-api'){
                    $('.nav.nav-sidebar .nav-parent').removeClass('nav-active active');
                    $('.nav.nav-sidebar .nav-parent .children').removeClass('nav-active active');
                    if ($('body').hasClass('sidebar-collapsed') && !$('body').hasClass('sidebar-hover')) return;
                    if ($('body').hasClass('submenu-hover')) return;
                    $('.nav.nav-sidebar .nav-parent .children').slideUp(200);
                    $('.nav-sidebar .arrow').removeClass('active');
                }
                if($location.$$path == '/'){
                    $('body').addClass('dashboard');
                }
                else{
                    $('body').removeClass('dashboard');
                }
                
                //$.material.init();

                $(document).on('click.card', '.card', function (e) {
                    if ($(this).find('.card-reveal').length) {
                        if ($(e.target).is($('.card-reveal .card-title')) || $(e.target).is($('.card-reveal .card-title i'))) {
                            $(this).find('.card-reveal').velocity(
                                {translateY: 0}, {
                                    duration: 225,
                                    queue: false,
                                    easing: 'easeInOutQuad',
                                    complete: function() { $(this).css({ display: 'none'}) }
                                }
                           );
                        }
                        else if ($(e.target).is($('.card .activator')) ||
                                $(e.target).is($('.card .activator i')) ) {
                            $(this).find('.card-reveal').css({ display: 'block'}).velocity("stop", false).velocity({translateY: '-100%'}, {duration: 300, queue: false, easing: 'easeInOutQuad'});
                        }
                    }
                });

            });

        }]).controller('LoginController',
        	    ['$scope', 'applicationService', 'quickViewService', 'builderService', 'pluginsService',
        	     '$location','$http','$window','$rootScope','$state','baseUrl','actuelUrl','loginUrl','rolesActuelsUrl',
        	        function ($scope, applicationService, quickViewService, builderService, pluginsService,
        	        		$location, $http,$window,$rootScope,$state,baseUrl,actuelUrl,loginUrl,rolesActuelsUrl) {
        	    	$scope.invalidUserPass = false;
        	    	$scope.disconnected = false;
        	    	console.log("Login URL : "+baseUrl+loginUrl);
        	    	$scope.submit = function() {
        	            var user = $scope.username;
        	            var pass = md5($scope.password);
        	            console.log('started login process');
        	            $scope.invalidUserPass = false;
        	            var data = {
        	                    username : user,
        	                    password : pass
        	                };
        	                $http.post(baseUrl+loginUrl,data,{params:data})
        	                    .success(function(resp,status,config,headers){
        	                    
        	                    $rootScope.loggedIn = true;
        	                    //console.log('login success');
        	                    $rootScope.user = resp;
        	                    $rootScope.roles = resp.roles;
        	                    console.log('resp : '+JSON.stringify(resp));
        	                    
        	                    $http.get(baseUrl+rolesActuelsUrl).success(function(resp,status,config,headers){
        	                    	console.log("Got current roles");
        	                    	$rootScope.roles = resp;
        	                    	//Links visibility
        	                    	$rootScope.canSee = function(role){
        	                    		//console.log('can see : '+resp.roles.includes(role));
        	                	    	return $rootScope.roles.includes(role);  	
        	                	    }
        	                    	if ($rootScope.roles.indexOf("ROLE_DASHBORD_AGENT")>-1){
        	                    		 $state.go("_dashbord");
        	                    	}else{
        	                    		 $state.go("dashbord");
        	                    	}
        	                    	
        	                    }).error(function(data,status,config,headers){
        	                        //In case of error
        	                        console.log('error : ' + status);
        	                    });
        	                    
        	                   
        	                    

        	                }).error(function(data,status,config,headers){
        	                    //In case of error
        	                    console.log('error : ' + status+" > "+JSON.stringify(data));

        	                    $scope.invalidUserPass = true;
        	                });
        	        	};
        	            
        	            console.log("login ctrl");
        	            console.log("Actuel URL : "+baseUrl+actuelUrl);
        	            $http.get(baseUrl+actuelUrl).success(function(data,status,config,headers){
        	            	console.log("Logged in, redirect to main page");
        	            	$state.go('dashbord');
        	            }).error(function(data,status,config,headers){
        	                //In case of error
        	                console.log('Not logged in : ');
        	            });
        	            
        	            
        	            
        	            
        	            
        	            
        	            
        	            
        	            
        	            
        	            
        	            
        	            
        	            
        	            

        	        }]);
