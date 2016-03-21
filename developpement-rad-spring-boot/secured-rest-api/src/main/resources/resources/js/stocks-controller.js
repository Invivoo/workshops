(function(){
	var STOCK_URL = "/stock";
	
	angular
		.module("Stock", [])
		.controller("StocksCtrl", StocksCtrl);
	
	function StocksCtrl($http){
		var vm = this;
		
		$http.get(STOCK_URL).success(loadStocksSuccess);
		
		vm.openStock = openStock;
		
		function loadStocksSuccess(response){
			vm.stocks = response;
		}
		
		function openStock(stock){
			vm.stock = stock;
			
			$(".modal").modal();
		}
	}
})();