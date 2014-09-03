<style>
<!--
body.nomargin{padding:0px 0px 0px 0px; margin:0px 0px 0px 0px; overflow: hidden; scroll: none;}
A{font: 10px};
A.blue{color: blue; font: 10px;}
//-->
</style>
<script language="javascript">
<!--
if(window.onload) window.onload += _addon;
else window.onload = _addon;
//_addon();

function _addon() {
	_sj();
	_st();
	_sb();
	_sa();
	_ss();
}

function _sj() {
	try {
		DoNav = DoNavNew;
	} catch(e) {}
}

function _st() {
	document.body.className = "nomargin";
	try {
		var t = document.all.tags('table')[0];
		t.background = "";
		t.border = "0";
		t.cellspacing = "0";
		t.style.paddingRight = 0;
		t.style.paddingTop = 0;
		t.style.marginTop = 0;
		t.style.lineHeight = "12px";
		t.width = "400";
		t.height = "25";
	
		var r = t.rows[0];
		r.deleteCell(0);
		while(r.cells.length > 1) r.deleteCell(1);
		r.cells[0].width = "";
		r.cells[0].style.position = "absolute";
		r.cells[0].style.top = "0px";
	} catch(e) {}
}

function _sa() {
	var links = document.all.tags('A');
	if(links == null || links == undefined) return;
	try {
		links.item(0).style.fontSize = "11px";
		links.item(0).style.fontWeight = "bold";
		links.item(0).className = "blue";
		links.item(2).style.styleFloat = "none";
		links.item(2).className = "blue";
		links.item(2).innerHTML = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + links.item(2).innerHTML;
		for(i = 0; i < links.length; i++){
			links.item(i).target = "_blank";
		}
	} catch(e) {}
}

function _sb() {
	var br = document.all.tags('br');
	for(i = br.length - 1; i >= 0; i--) {
		br[i].parentNode.removeChild(br[i]);
	}
}

function _ss() {
	var sp = document.all.tags('span');
	for(i = 0; i < sp.length; i++) {
		sp.item(i).style.fontSize = "11px";
	}
}
//-->
</script>