/**
 * Created by lbb on 2015/10/4.
 */

angular.module('ionicApp',['ionic'])

.controller('MyCtrl',function($scope,$timeout){
    $scope.myTitle = 'Template';
    $scope.items = ['Item1','Item2','Item3'];
    $scope.doRefresh = function(){
      $timeout(function(){
        $scope.items.push('New Item ' + Math.floor(Math.random()*1000)+4);
        $scope.items.push('New Item ' + Math.floor(Math.random()*1000)+4);
        $scope.items.push('New Item ' + Math.floor(Math.random()*1000)+4);
        $scope.$broadcast('scroll.refreshComplete');
      },1000);
    };
    $scope.load_more=function(){
      $timeout(function(){
        $scope.items.push('New Item ' + Math.floor(Math.random()*1000)+4);
        $scope.items.push('New Item ' + Math.floor(Math.random()*1000)+4);
        $scope.items.push('New Item ' + Math.floor(Math.random()*1000)+4);
        $scope.$broadcast('scroll.infiniteScrollComplete');
      },1000);

    }

  });
