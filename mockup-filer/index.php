<?php include_once("include/config.php"); ?>
<?php include_once("include/html-head.php"); ?>

    <?php include_once("include/header.php"); ?>
    <body class="body-game-is-on drawer drawer--right">
        <!--[if lt IE 8]>
            <p class="browserupgrade">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> to improve your experience.</p>
        <![endif]-->

         <!-- trigga när lule gör mål
        <audio class="hidden" autoplay preload="auto">
          <source src="sound/lule-gor-mal.mp3" type="audio/mpeg">
        </audio>
        -->

        <!-- trigga när lule släpper in mål
        <audio class="hidden" autoplay preload="auto">
            <source src="sound/lule-slapper-in-mal.mp3" type="audio/mpeg">        
        </audio>
        -->

        <!-- trigga när perioden är slut och när perioden börjar, även när matchen börjar
        <audio class="hidden" autoplay preload="auto">
            <source src="sound/period-slut-och-borjar.mp3" type="audio/mpeg">        
        </audio>
        -->

        <!-- trigga när matchen slutar
        <audio class="hidden" autoplay preload="auto">
            <source src="sound/match-slut.mp3" type="audio/mpeg">        
        </audio>
        -->

<!-- vid ett mål eller periodpaus eller liknande (funkar bara för mål just nu) -->
<!--
<div class="fullpageevent">
<div class="pwrapper">
    <div class="eventcontainer p">
        <div class="svgcontainer">
            <?php echo $lhf; ?>
            <?php echo $lhfretro; ?>
        </div>
        <div class="eventtext blink_me">
            Gjorde mål!
        </div>
    </div>
    </div>
</div>
-->


<!-- när lule vunnit matchen -->
<!--
<div class="wewon">
    <div></div>
</div>
-->

        <div class="facebook-share"><a href="<?php echo 'https://www.facebook.com/sharer/sharer.php?u=' . $current_url; ?>" onclick="javascript:window.open(this.href,'', 'menubar=no,toolbar=no,resizable=yes,scrollbars=yes,height=600,width=600');return false;"><?php echo $facebookicon; ?></a></div>
        <div class="facebook-under"><?php echo $facebookiconhover; ?></div>
        <?php include_once("include/menu.php"); ?>  
         
        <main role="main">
        <section class="container-jumbotron-outer w">
        <div class="pwrapper">
           <div class="game-metadata p">
                <h1 class="game-venue normal-game-venue">COOP Norrbotten Arena, Luleå</h1>
                <h1 class="game-venue retro-game-venue">Delfinen, Luleå</h1>
                <h2 class="game-date">Tisdag, 14 januari 2016 &ndash; 19:00</h2>
                <h2 class="game-info">SHL grundserien</h2>
                <h2 class="game-round">Omgång 32</h2>
            </div>
            <div class="clearfix"></div> 
        </div>           

        <div class="pwrapper dblock">
            <div class="game-matchup p">
                <div class="team-home svg-container shake-trigger left">
                    <div class="shake-slow"><?php echo $lhf; ?><?php echo $lhfretro; ?></div>
                </div>

                <div class="versus left">
                vs.
                </div>

                <div class="team-away svg-container shake-trigger left">
                    <div class="shake-little"><?php echo $fhc1; ?></div>
                </div>  
            </div>
        </div>

            <div class="pwrapper dblock">
                <div class="jumbotron p">
                    <div class="jumbotron-inner">
                        <div class="home">
                            <div class="team-heading">
                            Home
                            </div>
                            <div class="score blink_me">
                            <!-- får klassen blink_me när det har gjorts mål, ta bort klassen efter 5s-10s? -->
                            5
                            </div>                        
                        </div>
                        <div class="time-period">
                            <div class="time">
                                <span>
                                <!-- 00:00 till 20:00 -->
                                19:34
                                </span>
                            </div>
                            <div class="period-status">
                                <div class="period-heading">
                                Period
                                </div>
                                <div class="period-data">
                                <!-- 1, 2, 3, OT, SO -->
                                OT
                                </div>
                            </div>
                        </div>
                        <div class="guest">
                             <div class="team-heading">
                             Guest
                            </div>
                            <div class="score">
                            4
                            </div>
                        </div>
                        <div class="clearfix"></div>
                        <div class="game-status green">
                        <!-- när spelet är aktivt, klass green, "Matchen pågår" -->
                        <!-- byts till klassen yellow när det är paus, "Paus" -->
                        <!-- byts till klassen red när matchen är slut, "Matchen är slut" -->
                        Matchen pågår
                        </div>
                    </div>
                </div>
            </div>
        </section>        

    <?php include_once("include/body-end.php"); ?> 
    </main>     
    </body>
</html>