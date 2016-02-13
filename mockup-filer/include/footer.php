<?php function auto_copyright($year = 'auto'){ ?>
	<?php if(intval($year) == 'auto'){ $year = date('Y'); } ?>
	<?php if(intval($year) == date('Y')){ echo intval($year); } ?>
	<?php if(intval($year) < date('Y')){ echo intval($year) . '&ndash;' . date('Y'); } ?>
	<?php if(intval($year) > date('Y')){ echo date('Y'); } ?>
<?php } ?>

<footer>
<?php auto_copyright('2016'); ?>
</footer>