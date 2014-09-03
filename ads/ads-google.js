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

// this is for Google Adsense, to reformat the final ad page.
function _addon1() {
  var li_taw, div_ad, a_aw, a_adt, div_adb, div_adu, div_att, adt, adb, adu;
  var i, w_ad, w_adu;
  _st();
  div_att = document.getElementById("att");
  _h(div_att);
  for(i = 0; i < 4; i++) {
    li_taw = document.getElementById("taw" + i);
    div_ad = _f(li_taw, "item.className == 'ad'");
    a_adt = _f(div_ad, "item.className == 'adt'");
    div_adb = _f(div_ad, "item.className == 'adb'");
    div_adu = _f(div_ad, "item.className == 'adu'");
    adt = _go(a_adt);
    adb = _gi(div_adb);
    adu = _gi(div_adu);
    _si(div_ad, adt
      + "&nbsp;&nbsp;&nbsp;&nbsp;<span class=adb>" + adb + "</span>" + "&nbsp;&nbsp;<span class=adu>" + adu + "</span>");
    setTimeout("_sw(" + i + ")", 200);
    a_aw = document.getElementById("aw" + i);
    _sa(a_aw);
  }
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

function _f(o, cc) {
  if(o == null || o == undefined) return null;
  try {
    var item, items = o.children;
    var i, count = items.length;
    for(i = 0; i < count; i++) {
      item = items[i];
      if(eval(cc)) return item;
    }
  } catch(e) {
    return null;
  }
}

function _h(o) {
  if(o == null || o == undefined) return;
  try {
    o.innerHTML = "&nbsp;";
    o.style.display = "none";
  } catch(e) {}
}

function _gi(o) {
  if(o == null || o == undefined) return "";
  try {
    return o.innerHTML;
  } catch(e) {
    return "";
  }
}

function _go(o) {
  if(o == null || o == undefined) return "";
  try {
    return o.outerHTML;
  } catch(e) {
    return "";
  }
}

function _si(o, h) {
  if(o == null || o == undefined) return;
  try {
    o.innerHTML = h;
  } catch(e) {}
}

function _sa(o) {
  if(o == null || o == undefined) return;
  try {
    var h = o.href.substring(o.href.indexOf("/pagead"));
    o.href="http://pagead2.googlesyndication.com" + h;
  } catch(e) {}
}

function _sw(i) {
  var li_taw, div_ad, div_adb, div_adu;
  var w_ad, l_adu;
  try {
    li_taw = document.getElementById("taw" + i);
    if(li_taw == null || li_taw == undefined) return;
    div_ad = _f(li_taw, "item.className == 'ad'");
    div_adu = _f(div_ad, "item.className == 'adu'");
    w_ad = div_ad.offsetWidth;
    l_adu = div_adu.offsetLeft;
    div_adu.style.width = w_ad - l_adu - 6;
    //alert("w_ad = " + w_ad + ", l_adu = " + l_adu + ", div_adu.style.width = " + div_adu.style.width);
  } catch(e) {}
}

function _st() {
  // #aus
  document.styleSheets[0].rules[6].style.cssText = "height:24px;width:100%";
  // #aub
  document.styleSheets[0].rules[7].style.cssText = "background-color:#ffffff;height:24px;width:99%";
  // #ads
  document.styleSheets[0].rules[8].style.cssText = "position:absolute;top:0px;left:1px";
  // #ads ul li
  document.styleSheets[0].rules[10].style.cssText = "cursor:hand;float:left;height:24px;width:49%;overflow:hidden";
  // .adt:link
  document.styleSheets[0].rules[12].style.cssText = "cursor:hand;font-weight:bold;font-size:11px;color:#000000;line-height:12px";
  // .adt:visited
  document.styleSheets[0].rules[13].style.cssText = document.styleSheets[0].rules[12].style.cssText;
  // .adt:hover
  document.styleSheets[0].rules[14].style.cssText = document.styleSheets[0].rules[12].style.cssText;
  // .adt:active
  document.styleSheets[0].rules[15].style.cssText = document.styleSheets[0].rules[12].style.cssText;
  // .adb
  document.styleSheets[0].rules[16].style.cssText = "font-size:10px;color:#000000;line-height:12px";
  // .adu
  document.styleSheets[0].rules[17].style.cssText = "font-size:10px;color:#002bb8;line-height:12px;overflow:hidden;white-space:nowrap;text-overflow:ellipsis;width:1px";
}
//-->
</script>
