(function () {
    'use strict';

    /**
     * @memberof numaHopApp
     * @ngdoc controller
     * @name MainController
     * @description TODO
     */
    var MainController = function () {};
    angular.module('numaHopApp.controller').controller('MainController', MainController);

    /**
     * @memberof numaHopApp
     * @ngdoc controller
     * @name GlobalSearchController
     * @param location {service}
     * @param route {service}
     * @param timeout {service}
     * @param SearchSrvc {service}
     * @description TODO
     */
    var GlobalSearchController = function ($location, $route, $timeout, SearchSrvc) {
        var globalCtrl = this;
        globalCtrl.onListSelection = onListSelection;
        globalCtrl.search = search;
        globalCtrl.suggestions = suggestions;

        /**
         * @memberof GlobalSearchController
         * @function suggestions
         * @param {string} text
         * @description TODO
         */
        function suggestions(text) {
            return SearchSrvc.query({ suggest: text }).$promise;
        }

        /**
         * @memberof GlobalSearchController
         * @function search
         * @param {string} text
         * @param {Event} event
         * @description TODO
         */
        function search(searchText, event) {
            if (event && event.type === 'keypress' && event.keyCode !== 13) {
                return;
            }
            var reload = $location.path() === '/search/results';

            SearchSrvc.setSearch({ search: [searchText || ''], fuzzy: true });

            $timeout(function () {
                $location.path('/search/results').search({});

                if (reload) {
                    $route.reload();
                }
            });
        }

        /**
         * @memberof GlobalSearchController
         * @function onListSelection
         * @param {*} event
         * @description L'utilisateur appuie sur "entrée" alors que la souris survole un élément de la liste déroulante du composant typeahead:
         * on ne veut pas du comportement par défaut, qui consiste à recherche l'élément survolé,
         * mais plutôt lancer la recherche sur la saisie de l'utilisateur dans le champ de recherche.
         */
        function onListSelection(event) {
            if (event && event.type === 'keydown' && event.keyCode === 13) {
                search(globalCtrl.searchText);
            }
            return false; // pas de sélection dans la liste déroulante des suggestions de recherche
        }
    };
    angular.module('numaHopApp.controller').controller('GlobalSearchController', GlobalSearchController);

    /**
     * @memberof numaHopApp
     * @ngdoc controller
     * @name LanguageController
     * @param {service} window
     * @param {service} scope
     * @param {service} CONFIGURATION
     * @param {service} LocaleService
     * @param {service} UserSrvc
     * @description TODO
     */
    var LanguageController = function ($window, $scope, CONFIGURATION, LocaleService, UserSrvc) {
        /**
         * @function changeLocale
         * @scope {}
         * @description TODO
         */
        $scope.changeLocale = function (locale) {
            LocaleService.changeLocale(locale);
            $window.location.reload();
        };
        /**
         * @property {Array} locales
         * @description TODO
         */
        $scope.locales = CONFIGURATION.numahop.locales;
    };
    angular.module('numaHopApp.controller').controller('LanguageController', LanguageController);

    angular.module('numaHopApp.controller').controller('LoginController', function ($scope, $http, $location, $window, AuthenticationSharedService, gettext, gettextCatalog) {
        $scope.setForgotPassword = setForgotPassword;
        $scope.login = login;

        $window.document.title = gettextCatalog.getString('NumaHOP');
        $scope.rememberMe = true;
        $scope.forgotPassword = false;
        $scope.resetPassword = resetPassword;

        function login() {
            AuthenticationSharedService.login({
                username: $scope.username,
                password: $scope.password,
                rememberMe: $scope.rememberMe,
            });
        }

        function setForgotPassword(value) {
            $scope.forgotPassword = value;
            delete $scope.message;
        }

        function resetPassword() {
            $http
                .post('api/rest/reset', $scope.username)
                .success(function (data, status, headers, config) {
                    $scope.forgotPassword = false;
                    $scope.message = {
                        level: 'success',
                        text: gettextCatalog.getString('Le nouveau mot de passe a été envoyé sur votre adresse email.'),
                    };
                })
                .error(function (data, status, headers, config) {
                    $scope.message = {
                        level: 'danger',
                        text: gettextCatalog.getString('Erreur lors de la réinitialisation du mot de passe.'),
                    };
                });
        }
    });

    angular.module('numaHopApp.controller').controller('LogoutController', function ($location, AuthenticationSharedService) {
        AuthenticationSharedService.logout();
    });

    angular.module('numaHopApp.controller').controller('PasswordController', function ($scope, Password) {
        $scope.success = null;
        $scope.error = null;
        $scope.doNotMatch = null;
        $scope.changePassword = function () {
            if ($scope.password !== $scope.confirmPassword) {
                $scope.doNotMatch = 'ERROR';
            } else {
                $scope.doNotMatch = null;
                Password.save(
                    $scope.password,
                    function (value, responseHeaders) {
                        $scope.error = null;
                        $scope.success = 'OK';
                    },
                    function (httpResponse) {
                        $scope.success = null;
                        $scope.error = 'ERROR';
                    }
                );
            }
        };
    });
})();
