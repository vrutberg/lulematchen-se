$( document ).ready(function() {

/* get city and region from current visitor */
$.get("http://ipinfo.io", function(response) { city = response.city; region = response.region; }, "jsonp");

/**
 * jQuery.browser.mobile (http://detectmobilebrowser.com/)
 *
 * jQuery.browser.mobile will be true if the browser is a mobile device
 *
 **/
(function(a){(jQuery.browser=jQuery.browser||{}).mobile=/(android|bb\d+|meego).+mobile|avantgo|bada\/|blackberry|blazer|compal|elaine|fennec|hiptop|iemobile|ip(hone|od)|iris|kindle|lge |maemo|midp|mmp|mobile.+firefox|netfront|opera m(ob|in)i|palm( os)?|phone|p(ixi|re)\/|plucker|pocket|psp|series(4|6)0|symbian|treo|up\.(browser|link)|vodafone|wap|windows ce|xda|xiino/i.test(a)||/1207|6310|6590|3gso|4thp|50[1-6]i|770s|802s|a wa|abac|ac(er|oo|s\-)|ai(ko|rn)|al(av|ca|co)|amoi|an(ex|ny|yw)|aptu|ar(ch|go)|as(te|us)|attw|au(di|\-m|r |s )|avan|be(ck|ll|nq)|bi(lb|rd)|bl(ac|az)|br(e|v)w|bumb|bw\-(n|u)|c55\/|capi|ccwa|cdm\-|cell|chtm|cldc|cmd\-|co(mp|nd)|craw|da(it|ll|ng)|dbte|dc\-s|devi|dica|dmob|do(c|p)o|ds(12|\-d)|el(49|ai)|em(l2|ul)|er(ic|k0)|esl8|ez([4-7]0|os|wa|ze)|fetc|fly(\-|_)|g1 u|g560|gene|gf\-5|g\-mo|go(\.w|od)|gr(ad|un)|haie|hcit|hd\-(m|p|t)|hei\-|hi(pt|ta)|hp( i|ip)|hs\-c|ht(c(\-| |_|a|g|p|s|t)|tp)|hu(aw|tc)|i\-(20|go|ma)|i230|iac( |\-|\/)|ibro|idea|ig01|ikom|im1k|inno|ipaq|iris|ja(t|v)a|jbro|jemu|jigs|kddi|keji|kgt( |\/)|klon|kpt |kwc\-|kyo(c|k)|le(no|xi)|lg( g|\/(k|l|u)|50|54|\-[a-w])|libw|lynx|m1\-w|m3ga|m50\/|ma(te|ui|xo)|mc(01|21|ca)|m\-cr|me(rc|ri)|mi(o8|oa|ts)|mmef|mo(01|02|bi|de|do|t(\-| |o|v)|zz)|mt(50|p1|v )|mwbp|mywa|n10[0-2]|n20[2-3]|n30(0|2)|n50(0|2|5)|n7(0(0|1)|10)|ne((c|m)\-|on|tf|wf|wg|wt)|nok(6|i)|nzph|o2im|op(ti|wv)|oran|owg1|p800|pan(a|d|t)|pdxg|pg(13|\-([1-8]|c))|phil|pire|pl(ay|uc)|pn\-2|po(ck|rt|se)|prox|psio|pt\-g|qa\-a|qc(07|12|21|32|60|\-[2-7]|i\-)|qtek|r380|r600|raks|rim9|ro(ve|zo)|s55\/|sa(ge|ma|mm|ms|ny|va)|sc(01|h\-|oo|p\-)|sdk\/|se(c(\-|0|1)|47|mc|nd|ri)|sgh\-|shar|sie(\-|m)|sk\-0|sl(45|id)|sm(al|ar|b3|it|t5)|so(ft|ny)|sp(01|h\-|v\-|v )|sy(01|mb)|t2(18|50)|t6(00|10|18)|ta(gt|lk)|tcl\-|tdg\-|tel(i|m)|tim\-|t\-mo|to(pl|sh)|ts(70|m\-|m3|m5)|tx\-9|up(\.b|g1|si)|utst|v400|v750|veri|vi(rg|te)|vk(40|5[0-3]|\-v)|vm40|voda|vulc|vx(52|53|60|61|70|80|81|83|85|98)|w3c(\-| )|webc|whit|wi(g |nc|nw)|wmlb|wonu|x700|yas\-|your|zeto|zte\-/i.test(a.substr(0,4))})(navigator.userAgent||navigator.vendor||window.opera);

/* add class to html element if device is a mobile */
if ( jQuery.browser.mobile ) {
	$('html').addClass("thisisamobile");
} else {
	$('html').addClass("no-thisisamobile");
}	

// add mobile-detect class using modernizr
var md = new MobileDetect(window.navigator.userAgent);

/* add class to html element if webnotifications are supported */
if ('Notification' in window) {
	$('html').addClass("webnotifications");
} else {
	$('html').addClass("no-webnotifications");
}

if ( $('body').hasClass("waiting-for-game") ) {
    $('html').addClass("html-cover-up");
}

if ( $('body').hasClass("body-game-is-on") ) {
    $('html').addClass("html-game-is-on");
}

/* check if cookie is set for retromode */
/* if it is set, check checkbox for retromode, and show retro logo + retro venue name */
/* if it is not set, uncheck checkbox for retromode, and show normal logo + normal venue name */
if ( Cookies.get('retromode') == "on" ) {
        $('svg#lhfwhite').css({"display":"block"}).hide();
        $('svg#lhfretro').css({"display":"block"}).show();
        $('.normal-game-venue').hide();
        $('.retro-game-venue').show();
		$('#retroswitch').prop('checked', true);        
	}
	else {
        $('svg#lhfretro').css({"display":"block"}).hide();
        $('svg#lhfwhite').css({"display":"block"}).show();
        $('.retro-game-venue').hide();        
        $('.normal-game-venue').show();         
		$('#retroswitch').prop('checked', false);        
	};


/* check if checkbox for retromode is checked */
/* if it is checked, hide normal logo, show retro logo + retro venue name */
/* if it is not checked, hide retro logo, show normal logo + normal venue name */
if ( $('#retroswitch').prop('checked') ) {
        $('svg#lhfwhite').hide();
        $('svg#lhfretro').show();
        $('.normal-game-venue').hide();
        $('.retro-game-venue').show();        
    }
    else {
        $('svg#lhfretro').hide();
        $('svg#lhfwhite').show();
        $('.retro-game-venue').hide();        
        $('.normal-game-venue').show();          
    };

/* after retro switch is clicked, check if it checked */
/* if it is checked, create cookie for it, set value = on, */
/* hide normal logo, and fade in retro logo + retro venue name */
/* if it is not checked, remove cookie for it, */
/* hide retro logo and fade in normal logo + normal venue name */
$('#retroswitch').click(function(){
    if ($(this).prop("checked") == true) {
		Cookies.set('retromode', "on", { expires: 3652 });
        $('svg#lhfwhite').hide();
        $('svg#lhfretro').fadeIn("slow");
        $('.normal-game-venue').hide();
        $('.retro-game-venue').fadeIn("slow");           
    }
    else if ($(this).prop("checked") == false) {
		Cookies.remove('retromode');
        $('svg#lhfretro').hide();
        $('svg#lhfwhite').fadeIn("slow");
        $('.retro-game-venue').hide();        
        $('.normal-game-venue').fadeIn("slow");        
    }
});

/* check if cookie is set for audio */
/* if it is set, set muted to false */
/* if it is not set, set muted to true */
if ( Cookies.get('audio') == "off" ) {
		$('audio').prop('muted', true);
		$('#ljudswitch').prop('checked', false);		
	}
	else {
		$('audio').prop('muted', false);
		$('#ljudswitch').prop('checked', true);		
	};

/* after audio switch is clicked, check if checked */
/* if it is not checked, mute audio */
/* if it is checked, unmute audio */
$('#ljudswitch').click(function(){
    if ($(this).prop("checked") == false) {
		Cookies.set('audio', "off", { expires: 3652 });
		$('audio').prop('muted', true);
    }
    else if ($(this).prop("checked") == true) {
		Cookies.remove('audio');
		$('audio').prop('muted', false);
    }
});

/* if autoplay audio is not supported, mute all sounds always and */
/* remove cookie for audio, if it has somehow been added */
if ( $('.audio-autoplay').css('display') == 'none' ) {
    Cookies.remove('audio');
    $('audio').prop('muted', true);    
};

/* initialize menu drawer */
$('.drawer').drawer({
  iscroll: {
    // Configuring the iScroll
    // https://github.com/cubiq/iscroll#configuring-the-iscroll
    mouseWheel: true,
    preventDefault: false
  },
  showOverlay: true
});

/* if LULEÅ HOCKEY won the game, do this */
$(".wewon > div").snowfall({image :"img/teams/lhf-retro.png", flakeCount: 6, minSize: 10, maxSize: 48});

/* testing the event fullpage, should appear when an event occurs, not with timeout function */
/*
setTimeout(function()
	{
		$(".fullpageevent").addClass("fullpageeventshow");
		$("main").addClass("hidden");
	}, 5000);

setTimeout(function()
	{
		$("main").removeClass("hidden");
		$(".fullpageevent").removeClass("fullpageeventshow");
	}, 10000);
*/

/* document ready END */
});

/* window.load begin */
$(window).load( function() {

/* if city is 'Luleå', show li.lulebo with heart! */
/* comes from ipinfo (above) */
if ( city == 'Luleå' || region == 'Luleå' ) {
    $('.lulebo').css({"display":"list-item"});
}    

/* web notifications using notify.js wrapper */
if ( $('html').hasClass("webnotifications") ) {

//setTimeout(function() {

    function onShowNotification () {
        console.log('notification is shown!');
    }

    function onCloseNotification () {
        console.log('notification is closed!');
    }

    function onClickNotification () {
        console.log('notification was clicked!');
    }

    function onErrorNotification () {
        console.error('Error showing notification. You may need to request permission.');
    }

    function onPermissionGranted () {
        console.log('Permission has been granted by the user');
        doNotification();
    }

    function onPermissionDenied () {
        console.warn('Permission has been denied by the user');
    }

    function doNotification () {
        var myNotification = new Notify('Luleå Hockey vs. HV71', {
            body: 'Luleå Hockey gjorde mål. Ställningen är 5-4.',
            icon: 'img/general/icon-notification.png',
            lang: 'sv',
            tag: 'Unique ID for notifciation',
            notifyShow: onShowNotification,
            notifyClose: onCloseNotification,
            notifyClick: onClickNotification,
            notifyError: onErrorNotification,
            timeout: 5
        });

        myNotification.show();
    }

    if (!Notify.needsPermission) {
        doNotification();
    } else if (Notify.isSupported()) {
        Notify.requestPermission(onPermissionGranted, onPermissionDenied);
    }

//}, 3000);

};

/* preload certain images so stuff is smooth */
$.preloadImages = function() {
  for (var i = 0; i < arguments.length; i++) {
    $("<img />").attr("src", arguments[i]);
  }
}

$.preloadImages("img/general/background_ice.jpg", "img/general/jumbotron-bg2.jpg", "img/general/luleablur.jpg", "img/teams/lhf-retro.png", "img/general/loader.gif", "img/general/icon-notification.png", "img/teams/rbk.png");

/* window load end */
});

	











