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
    app.directive('myReqfld', function(){
        var getTemplate = function(attrs) {
            var template = '';
            switch(attrs.type) {
                case 'txt':
                    template = '<div class="col-xs-12">' +
                    '<div class="col-xs-5 col-lg-6 text-right">{{label}}: </div>' +
                    '<div class="col-xs-7 col-lg-6"><input ng-model="ngModel" ' +
                    'required type="text" maxlength={{max}} size={{size}}></div>' +
                    '<div ng-show="!ngModel" class="custom-error">' +
                    '{{label}} is required!</div></div>';
                    break;
                case 'dec':
                    template = '<div ng-form={{label}} class="col-xs-12">' +
                    '<div class="col-xs-5 col-lg-6 text-right">{{label}}: </div>' +
                    '<div class="col-xs-7 col-lg-6"><input ng-model="ngModel" ' +
                    'required type="number" min="0" max={{max}} size={{size}} step="1" name="ngModel"></div>' +
                    '<div ng-show="{{label}}.$error.invalid" class="custom-error">' +
                    '{{label}} must be numberic!</div>'+
                    '<div ng-show="{{label}}.$error.required" class="custom-error">' +
                    '{{label}} is required!</div></div>';
                    break;
                case 'int':
                    template = '<div ng-form={{label}} class="col-xs-11">' +
                    '<div class="col-xs-5 col-lg-6 text-right">{{label}}:</div>' +
                    '<div class="col-lg-6 col-xs-7"><input class="text-right" ' +
                    'ng-model="ngModel" required type="number" min="0" max={{max}} ' +
                    'step="1" name="ngModel"></div>' +
                    '<div ng-show="{{label}}.$error.number" class="custom-error pull-left">' +
                    '{{label}} must be numeric!</div>' +
                    '<div ng-show="{{label}}.$error.required" class="custom-error pull-left">' +
                    '{{label}} required!</div></div>';
                    break;
            }
            
            return template;
        };
        
        return{
            restrict: "E",
            scope: {ngModel: '=', size: '@', label: '@', max: '@'},
            template : function(element, attrs) {
                return getTemplate(attrs);
            },
            link: function($scope, element, attrs) {
                var origVal = $scope.ngModel;
                element.bind('input', function() {
                    if(origVal !== $scope.ngModel) {
                        element.addClass('changed');
                    }
                    else {
                        element.removeClass('changed');
                    }
                });
            }
        };
    });
}(angular.module('case2')));