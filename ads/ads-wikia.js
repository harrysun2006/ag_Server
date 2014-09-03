<style>
<!--
body.nomargin{margin: 0px 0px 0px 6px;}
A{display: none};
A.blue{display: block; color: blue; font: 12px Verdana;line-height:12px;}
//-->
</style>
<script language="javascript">
<!--
if(window.onload) window.onload += _addon;
else window.onload = _addon;
//_addon();

function _addon() {
  _addon2();
}

// this is for all page to change the link's target to _blank
function _addon2() {
  document.body.className = "nomargin";
  var links = document.all.tags('A');
  if(links == null || links == undefined) return;
  try {
    for(var i = 0; i < links.length; i++){
      links.item(i).target = "";
      links.item(i).className = "blue";
    }
  } catch(e) {}
}
//-->
</script>
