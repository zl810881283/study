/**
 * Created by zl on 2015/9/30.
 */
angular.module('phonecat', ['ngRoute'])
    .config(['$routeProvider', function($routeProvider) {
        $routeProvider.
            when('/phones', {templateUrl: 'partials/phone-list.html',   controller: 'PhoneListCtrl'}).
            when('/phones/:phoneId', {templateUrl: 'partials/phone-detail.html', controller: 'PhoneDetailCtrl'}).
            otherwise({redirectTo: '/phones'});
    }])
    .controller('PhoneListCtrl',['$scope','$routeParams',function($scope,$routeParams){
                $scope.params=$routeParams;
    }])
    .controller('PhoneDetailCtrl',['$scope','$routeParams','TestService',function($scope,$routeParams,TestService){
        $scope.phoneId=TestService.getPhoneId();
        $scope.params=$routeParams;
    }])
    .service('TestService',function(){
        this.phoneId="test for the service";
        var _this=this;
        this.getPhoneId=function(){
            return _this.phoneId;
        };

        return this;
    });