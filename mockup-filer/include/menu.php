  <header role="banner">
    <button type="button" class="drawer-toggle drawer-hamburger">
      <span class="sr-only">toggle navigation</span>
      <span class="drawer-hamburger-icon"></span>
    </button>
    <nav class="drawer-nav" role="navigation">
      <ul class="drawer-menu">
        <li><a class="drawer-brand">Lulematchen.se</a></li>
        <li><a style="cursor: pointer !important;" class="drawer-menu-item" data-featherlight='<?php echo $omlulematchen; ?>' data-featherlight-other-close=".otherclose">Om lulematchen.se</a></li>

        <!-- kom ihåg val med cookie som sätts till så långt det går -->
        <!-- vid retro mode = on, så byts lhf-loggan (den med björnen) ut till gamla stålmannen-loggan -->
        <li>
            <a class="drawer-menu-item" style="display: inline-block; text-decoration: none;">Retro mode</a>
            <div class="onoffswitchretro">
                <input type="checkbox" name="onoffswitchretro" class="onoffswitch-checkboxretro" id="retroswitch">
                <label class="onoffswitch-labelretro" for="retroswitch"></label>
            </div>
        </li>

        <!-- kom ihåg val med cookie som sätts till så långt det går -->
        <!-- om valet är på (default), så är det ljud, slår man av det är det inte ljud -->
        <li class="audio-autoplay">
            <a class="drawer-menu-item" style="display: inline-block; text-decoration: none;">Ljud</a>
            <div class="onoffswitchljud">
                <input type="checkbox" name="onoffswitchljud" class="onoffswitch-checkboxljud" id="ljudswitch" checked>
                <label class="onoffswitch-labelljud" for="ljudswitch"></label>
            </div>
        </li>

        <li><a class="drawer-menu-item" href="http://luleahockey.se" target="_blank">luleahockey.se</a></li>
        <li><a class="drawer-menu-item" href="http://shl.se" target="_blank">shl.se</a></li>
        <li class="lulebo">
            <a class="drawer-menu-item" style="display: inline-block; text-decoration: none; font-weight: bold;">Du är 100% Lule!</a>
            <div><?php echo $heartfilled; ?></div>
        </li>                
      </ul>
    </nav>
  </header>