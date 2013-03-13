function(){
    var iosTest = /Mac.+Mobile/gi;
    var andTest = /Android/gi;
    var iPhoneTest = /iPhone/gi;
    var iPadTest = /iPad/gi;
    //Para 
    if(iosTest.test(navigator.userAgent)){
        document.write(‘<link href=”css/ios.css” rel=”stylesheet” type=”text/css” />’);
    }
    //Android
    else if(andTest.test(navigator.userAgent)){
        document.write(‘<link href=”css/android.css” rel=”stylesheet” type=”text/css” />’);
    }
    //
    else if(iPhoneTest.test(navigator.userAgent)){
        document.write(‘<link href=”css/iphone.css” rel=”stylesheet” type=”text/css” />’);
    }
    else if(iPadTest.test(navigator.userAgent)){
        document.write(‘<link href=”css/ipad.css” rel=”stylesheet” type=”text/css” />’);
    }
    else{
        
    }
}