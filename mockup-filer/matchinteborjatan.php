<?php include_once("include/config.php"); ?>
<?php include_once("include/html-head.php"); ?>

    <?php include_once("include/header.php"); ?>
    <body class="waiting-for-game drawer drawer--right">
        <!--[if lt IE 8]>
            <p class="browserupgrade">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> to improve your experience.</p>
        <![endif]-->

        <div class="facebook-share"><a href="<?php echo 'https://www.facebook.com/sharer/sharer.php?u=' . $current_url; ?>" onclick="javascript:window.open(this.href,'', 'menubar=no,toolbar=no,resizable=yes,scrollbars=yes,height=600,width=600');return false;"><?php echo $facebookicon; ?></a></div>
        <div class="facebook-under"><?php echo $facebookiconhover; ?></div>
        <?php include_once("include/menu.php"); ?>

        <div class="cover-up">
        <div class="pwrapper">
        <div class="cover-up-inner p">
            <div class="next-game-text w">
                Nästa Luleå Hockey-match börjar om:
            </div>
            <div class="next-game-counter w">
                30 dagar, 22 timmar, 18 minuter och 47 sekunder.
            </div>
            <div class="next-game-teams w">

            <div class="game-matchup">

                <div class="team-home svg-container shake-trigger left">
                    <div class="shake-slow"><?php echo $lhf; ?><?php echo $lhfretro; ?></div>
                </div>

                <div class="versus left">
                vs.
                </div>

                <div class="team-away svg-container shake-trigger left">
                    <div class="shake-little"><?php echo $fhc2; ?></div>
                </div>   
            </div>

            </div>
            <div class="next-game-date w">
           <div class="game-metadata">
                <h1 class="game-venue normal-game-venue">COOP Norrbotten Arena, Luleå</h1>
                <h1 class="game-venue retro-game-venue">Delfinen, Luleå</h1>
                <h2 class="game-date">Tisdag, 14 januari 2016 &ndash; 19:00</h2>
                <h2 class="game-info">SHL grundserien</h2>
                <h2 class="game-round">Omgång 32</h2>
            </div>
            </div>
            <div class="next-game-request">
            Kom tillbaka till denna sida vid matchstart för liveuppdateringar från matchen!
            </div>
            </div>
            </div>
            <p class="mobile-only">&nbsp;</p>     
        </div>

    <?php include_once("include/body-end.php"); ?>        
    </body>
</html>