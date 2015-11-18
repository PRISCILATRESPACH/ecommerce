'use strict';

angular.module('ecommerce',['ngRoute','ngResource'])
  .config(['$routeProvider', function($routeProvider) {
    $routeProvider
      .when('/',{templateUrl:'views/landing.html',controller:'LandingPageController'})
      .when('/Carrinhos',{templateUrl:'views/Carrinho/search.html',controller:'SearchCarrinhoController'})
      .when('/Carrinhos/new',{templateUrl:'views/Carrinho/detail.html',controller:'NewCarrinhoController'})
      .when('/Carrinhos/edit/:CarrinhoId',{templateUrl:'views/Carrinho/detail.html',controller:'EditCarrinhoController'})
      .when('/Categoria',{templateUrl:'views/Categoria/search.html',controller:'SearchCategoriaController'})
      .when('/Categoria/new',{templateUrl:'views/Categoria/detail.html',controller:'NewCategoriaController'})
      .when('/Categoria/edit/:CategoriaId',{templateUrl:'views/Categoria/detail.html',controller:'EditCategoriaController'})
      .when('/Livros',{templateUrl:'views/Livro/search.html',controller:'SearchLivroController'})
      .when('/Livros/new',{templateUrl:'views/Livro/detail.html',controller:'NewLivroController'})
      .when('/Livros/edit/:LivroId',{templateUrl:'views/Livro/detail.html',controller:'EditLivroController'})
      .when('/Pagamentos',{templateUrl:'views/Pagamento/search.html',controller:'SearchPagamentoController'})
      .when('/Pagamentos/new',{templateUrl:'views/Pagamento/detail.html',controller:'NewPagamentoController'})
      .when('/Pagamentos/edit/:PagamentoId',{templateUrl:'views/Pagamento/detail.html',controller:'EditPagamentoController'})
      .when('/PedidoLivros',{templateUrl:'views/PedidoLivros/search.html',controller:'SearchPedidoLivrosController'})
      .when('/PedidoLivros/new',{templateUrl:'views/PedidoLivros/detail.html',controller:'NewPedidoLivrosController'})
      .when('/PedidoLivros/edit/:PedidoLivrosId',{templateUrl:'views/PedidoLivros/detail.html',controller:'EditPedidoLivrosController'})
      .when('/Pedidos',{templateUrl:'views/Pedidos/search.html',controller:'SearchPedidosController'})
      .when('/Pedidos/new',{templateUrl:'views/Pedidos/detail.html',controller:'NewPedidosController'})
      .when('/Pedidos/edit/:PedidosId',{templateUrl:'views/Pedidos/detail.html',controller:'EditPedidosController'})
      .when('/Usuarios',{templateUrl:'views/Usuario/search.html',controller:'SearchUsuarioController'})
      .when('/Usuarios/new',{templateUrl:'views/Usuario/detail.html',controller:'NewUsuarioController'})
      .when('/Usuarios/edit/:UsuarioId',{templateUrl:'views/Usuario/detail.html',controller:'EditUsuarioController'})
      .when('/Vendas',{templateUrl:'views/Venda/search.html',controller:'SearchVendaController'})
      .when('/Vendas/new',{templateUrl:'views/Venda/detail.html',controller:'NewVendaController'})
      .when('/Vendas/edit/:VendaId',{templateUrl:'views/Venda/detail.html',controller:'EditVendaController'})
      .otherwise({
        redirectTo: '/'
      });
  }])
  .controller('LandingPageController', function LandingPageController() {
  })
  .controller('NavController', function NavController($scope, $location) {
    $scope.matchesRoute = function(route) {
        var path = $location.path();
        return (path === ("/" + route) || path.indexOf("/" + route + "/") == 0);
    };
  });
