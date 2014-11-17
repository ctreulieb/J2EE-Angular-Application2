(function(app) {
    app.directive('confirmClick', function() {
        return {
            restrict: 'A',
            priority: 1,
            terminal:true,
            link: function(scope, element, attr) {
                var msg = attr.confirmationNeeded || "Really Delete?";
                var clickAction = attr.ngClick;
                element.bind('click', function() {
                    if (window.confirm(msg)) {
                        scope.$apply(clickAction);
                    }
                });
            }
        };
    });
    
    app.directive('myHelloWorld', function(){
        return {
            restrict: 'E',
            replace: 'true',
            template: '<h2>Hello World</h2>'
        };
    });
    
    app.directive('myOtherHelloWorld', function(){
        return {
            restrict: 'E',
            replace: 'true',
            scope: { sz: '@'},
            template: '<div style="font-size:{{sz}}px;">Hello other world at {{sz}} pixels </div>'
        };
    });
    app.directive('myThirdDirective', function(){
        return {
            restrict:'E',
            replace: 'true',
            scope: {customer: '='}, //2 way binding
            template: '<div> Output from third example <br/>' +
                    '<input ng-model="customer.name" required type="text" />' +
                    '</div>'
        };
    });
    
    app.directive('myFourthDirective', function() {
        function link($scope, element, attrs) {
            element.bind('input', function(){
                $scope.myCustomer.combined = $scope.myCustomer.name + ' ' + attrs.lastname;
                if($scope.myCustomer.name === 'John') {
                    alert($scope.myCustomer.combined + ' lives on ' + $scope.myCustomer.street);
                    element.css('visibility','hidden');
                }
            });
        };
        return {
            restrict:   'E',
            replace:    'true',
            template:   '<div>Enter "John" to complete 4th directive <br /><input ng-model="myCustomer.name" required type="text"/></div>',
            link: link
        };        
    });
}(angular.module('case2')));