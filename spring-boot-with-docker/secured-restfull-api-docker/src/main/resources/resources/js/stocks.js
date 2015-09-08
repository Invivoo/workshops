angular.module("Stock", []).controller("StockController", function($scope, $http, $timeout){
	$http.get("/stock").success(function(stocks){
		$scope.stocks = stocks;
	});
});