    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script>window.jQuery || document.write('<script src="js/vendor/jquery-1.11.2.min.js"><\/script>')</script>

    <!-- flexbox support for lte IE 9 -->
    <!--[if lte IE 9]>
    <script src="js/ie/flexibility.js"></script>
    <![endif]-->

    <!-- JS -->
    <script src="js/production.min.js?<?php echo filemtime('js/production.min.js'); ?>"></script>

    <!-- Google Analytics -->
    <script>
        (function(b,o,i,l,e,r){b.GoogleAnalyticsObject=l;b[l]||(b[l]=
        function(){(b[l].q=b[l].q||[]).push(arguments)});b[l].l=+new Date;
        e=o.createElement(i);r=o.getElementsByTagName(i)[0];
        e.src='//www.google-analytics.com/analytics.js';
        r.parentNode.insertBefore(e,r)}(window,document,'script','ga'));
        ga('create','<?php echo $google_analytics_id; ?>','auto');ga('send','pageview');
    </script>