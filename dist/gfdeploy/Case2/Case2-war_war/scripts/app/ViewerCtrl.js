(function (app) {
    var ViewerCtrl = function($scope, RESTFactory, $window) {
        var baseurl = 'webresources/viewer';
        //Init
        //Initializes variables and gets the vendors from the database
        var init = function() {
        RESTFactory.restCall('get', 'webresources/vendor/vendors', -1, '').then(function(vendors) {
            
            if (vendors.length > 0) { 
                $scope.vendors = vendors;
                $scope.status = 'Vendors Retrieved';
                $scope.vendor = $scope.vendors[0];
                $scope.notcreated = true;
                $scope.subtotal = -1;
            }
            else {
                $scope.status = 'Vendors not retrieved code = ' + vendors;
            }
        }, function(reason) {
            $scope.status = 'Vendors not retrieved' + reason;
        });
        
    };
    init();
    
    //SelectVendor
    //Selects a vendor and retrieves that vendors products from DB
    $scope.selectVendor = function() {
        $scope.pickedVendor = true;
        $scope.subtotal = -1;
        $scope.tax = 0;
        $scope.total = 0;
        $scope.pickedPO = false;
        $scope.vendors.forEach(function(v)
        {
            if ($scope.viewer.vendorno === v.vendorno)
                $scope.vendor = v;
        });
        RESTFactory.restCall('get', 'webresources/po', $scope.viewer.vendorno, '').then(function(pos) {
            if(pos.length > 0) {
                $scope.pos = pos;
                $scope.status = "POs for " + $scope.vendor.name + " loaded!";
                $scope.po = $scope.pos[0];
            }
            else {
                $scope.pos = null;
                $scope.status = 'POs not retrieved code = ' + pos;
            }
        }, function(reason) {
            $scope.status = 'POs not retrieved' + reason;
        });
    };//select vendor
    
    $scope.selectPO = function() {
        $scope.subtotal = 0;
        $scope.tax = 0;
        $scope.total = 0;
        $scope.pickedPO = true;
        $scope.pos.forEach(function(p)
        {
            if($scope.viewer.ponumber === p.ponumber)
                $scope.po = p;
        });
        for(var pCount = 0; pCount < $scope.po.items.length; pCount++)
        {

           $scope.po.items[pCount].extended = $scope.po.items[pCount].qty * $scope.po.items[pCount].costprice;
           $scope.subtotal += ($scope.po.items[pCount].qty * $scope.po.items[pCount].costprice);

        };
        
        $scope.tax = $scope.subtotal * 0.13;
        $scope.total = $scope.subtotal + $scope.tax;
        $scope.orderinfo = "PO " + $scope.po.ponumber + " - " + $scope.po.date;
    };
    
    //viewPDF
    //Links to the PDF servlet to view the Purchase order invoice for the just completed purchase order
    $scope.viewPdf = function() {
        $window.location.href = 'POPDF?po=' + $scope.po.ponumber;
    }; // viewPdf

};
app.controller('ViewerCtrl', ['$scope', 'RESTFactory', '$window', ViewerCtrl]);
})(angular.module('case2'));