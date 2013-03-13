// ***********************************************
// tree.js
// Arbol desplegable, soportando distintos tipos de
// carpetas y elementos. Carga al estilo Ajax.
//
// Oscar H.Casas 02/10/2006
// ***********************************************

// Versión
var tree_version = "01.03.2010 10:00";

// Manejadores de eventos

var bGoUrl=false;
function openFolder() {
  
  if (bGoUrl) { //Comprobamos si hemos pasado por el evento goUrl()
	  bGoUrl=false;
	  return;
  }
  var obj = window.event.srcElement;
  if (!obj.id) obj = window.event.srcElement.parentElement;
  var subCapa = document.getElementById("sub" + obj.id);
  if (subCapa) {
    if (subCapa.style.display=="none") {
      subCapa.style.visibility="visible";
      subCapa.style.display="inline";
		} else {
      subCapa.style.visibility="hidden";
      subCapa.style.display="none";
		}
	}
  window.event.returnValue = true;
}

var urlOrigen = "";
function goUrl() {

/*
  urlOrigen = top.work.location+"";
  urlOrigen = urlOrigen.replace(/&/g,'%26');
*/
  bGoUrl=true;
  document.selection.empty();
  var obj = window.event.srcElement;
  if (!obj.id) obj = window.event.srcElement.parentElement;
	var subCapa = document.getElementById("sub" + obj.id);

	if (obj.url){
			if (obj.url.match("javascript:")) {
				eval (obj.url);
			}
			else{
			    //top.abrirCapa();
			    //go(obj.url);
			    alert(obj.url);
			}
			window.event.returnValue = true;
	}
	else {
	  try{
	   obj = window.event.srcElement.parentElement.parentElement;
		 if (obj.url){
			if (obj.url.match("javascript:")) {
				eval (obj.url);
			}
			else{
				//top.abrirCapa();
				//go(obj.url);
				alert(obj.url);
			}
			window.event.returnValue = true;
		 }
	  }
	  catch(e){
		}
	}
	
}

var vCronoStart;
var vCronoStop;
var vCronoLaps;

function cronoStart() {
  vCronoStart = new Date().getTime();
}

function cronoStop () {
  vCronoStop = new Date().getTime();
  vCronoLaps = vCronoStop - vCronoStart;
	//alert("Tiempo empleado en generar codigo HTML del árbol de HC: "+vCronoLaps+" milisegundos");
}

// Generación del código HTML a partir del array de datos
//Formato: [nivel,estilo,literal,'url',desplegado]
function load(data,nivel,iData) {

var htm = "";
var clase = "";

var colorTachado = "";

//Comprobación de posible overflow
if (iData>=data.length) return "";



// Lista inicial
if (iData==0) htm = "<ul class='tree' style='margin-left:25px'>\n";
// Recorrido por niveles

for ( var i=iData; i<data.length && data[i][0]>=nivel; i++) {
		var cerrarTag = "";
		if (data[i][0]==nivel) {
			if (data[i][1].match("tree")) {//Tree == Carpeta
				// Comprobar si tiene hijos para poner el estilo tree___0 o tree___1
				//alert("Para "+data[i][1]+", el valor del siguiente es "+data[i][0]);
				clase = data[i][1] + ((i+1<data.length && data[i+1][0]>data[i][0]) ? "1" : "0");
				if (data[i][0]=='1') { //Tree de nivel 1
				     //alert("__"+data[i+1][0]+" __"+data[i+1][1]+" __"+data[i+1][2]+" __"+data[i+1][3]+" __"+data[i+1][4]+" __"+data[i+1][5]);
										htm += "<table cellspacing='0' cellpadding='0' style='margin-left:-18px' class=" + clase + ">"+
															"<tr>"+
																"<td width='100%'>"+ "<li id=" + i +" class=" + clase +	" url='" + data[i][4] + "'>";
						if (data[i][8]=='1' ||
						    data[i][8]=='2' ||
								data[i][8]=='3' ||
								data[i][8]=='4' ||
								data[i][8]=='5' ||
								data[i][8]=='6'){ //Episodio anulado <STRIKE>Texto tachado</STRIKE>

								cerrarTag = "</span>";

								// Color de tachado (Falta definir que significa cada valor {2, 3, 4} del campo BMUTUALISTA de la tabla Episodio)
								switch(data[i][8])
								{
									case '1':
										colorTachado = "#CC0000"; //Rojo
								  break;
									case '2':
										colorTachado = "#006600"; //Verde
								  break;
									case '3':
										colorTachado = "#0000FF"; //Azul
								  break;
									case '4':
										colorTachado = "#800080"; //Morado
								  break;
									case '5':
										colorTachado = "#FF00FF"; //Rosado
								  break;
									case '6':
										colorTachado = "#000000"; //Negro
									break;
								}

										htm += "<span style='cursor:hand;text-decoration:line-through;color:" + colorTachado + "' onClick='goUrl()'><span onClick='goUrl()' class=" + clase +">";
						}
						else {
										htm += "<span style='cursor:hand;' onClick='goUrl()'>";
						}
						if (data[i][6] && data[i][6] != '') { //Existe numEpisodio??
								   htm += "Ep.&nbsp;"+data[i][6]+"&nbsp;&nbsp;&nbsp;";
						}

										htm +=	"<font style='font-size:12px;font-stretch:ultra-condensed;font-weight: 500;'>"+data[i][2] + cerrarTag +"</font></span></li>&nbsp;&nbsp;"+ data[i][3];

						if (data[i][7]=='0'){ //Cede datos??   cede = 0 ==> NO CEDE
										htm += "&nbsp;<img src='" + getContextPath() + "/images/ico/icoNoCedeDatos.gif' title='No cede datos' alt=''/>";
						}

										htm +=  "</td></tr></table>" +
														"<ul id=sub" + i + " style='"+((!data[i][5]) ? "display:none" : ";display:inline") + "'>\n";
				}
				else if (data[i][0]>='2') { //Tree nivel 2
					var marginleft = 10*((1*data[i][0])-2);
										htm += "<table cellspacing='0' cellpadding='0' style='margin-left:" + marginleft + "px' class=" + clase + ">"+
															"<tr>"+
																"<td>";
							if (clase.match("treeDoc"))
										htm +=	"<li id=icoDoc" + data[i][7] +" class=" + clase + " url='" + data[i][4] + "'>";
							else
										htm += "<li id=" + i +" class=" + clase + " url='" + data[i][4] + "'>";

										htm +=	"<span style='cursor:hand' onClick='goUrl()'>"+ data[i][2] + "</span></li>"+
																"</td>"+
																"<td>" + data[i][3] +
																"</td>"+
															"</tr>"+
														"</table>" +
														"<ul id=sub" + i + " style='" +((!data[i][5]) ? ";display:none" : ";display:inline") + "'>\n";
				}
				else { //Tree nivel 0
										htm += "<li class=" + clase + " id=" + i + " url='" + data[i][4] + "' onClick='openFolder()'>"+
															"<span style='cursor:hand' onClick='goUrl()'>" + data[i][2] + data[i][3]+ "</span>"+
													 "</li>\n" +
													 "<ul id=sub" + i + " style='" + ((!data[i][5]) ? ";display:none" : ";display:inline") + "'>\n";
				}
										//Llamamos recursivamente a este misma funcion saltando al nivel siguente de carpetas
										htm +=	load(data,nivel+1,i+1);
										htm +=	"</ul>\n";
			}
			//Fin Tree

			//Items
			else if (data[i][1].match("item")) { //Item == punto
										htm +=	"<table cellspacing='2' cellpadding='0' ";
					if (data[i][0]=='2') { //Segundo nivel indentación 0px (aunque son relativos)
										htm +=	"style='margin-left:0px' ";
					}
					else if (data[i][0]=='3') { //Segundo nivel indentación 20px
										htm +=	"style='margin-left:10px' ";
					}
					else if (data[i][0]=='4') { //Segundo nivel indentación 20px
										htm +=	"style='margin-left:20px' ";
					}
										htm += "class=" + clase +">"+
															"<tr>"+
																"<td><li id=" + i + " class='" + data[i][1] + "' ";
					if (data[i][1].match("itemPend")){ //Asistencias pendientes
										htm += "title='Asistencia pendiente de finalizar' ";
					}
										htm += "url='" + data[i][3]+"'>"+
																	"<span style='cursor:hand' onClick='goUrl()'>" + data[i][2] + "</span></li>"+
																"</td>"+
															"</tr>"+
														"</table>";
										//Llamamos recursivamente a este misma funcion saltando al nivel siguente de carpetas
										htm +=	load(data,nivel+1,i+1);
			}
		}
	}


// Fin lista inicial
	if (iData==0) htm += "</ul>\n";
	return htm;
}

// Constructor
function loadTree (data) {

	return load(data,0,0);
}

// Precarga de imágenes
function preloadImages(urlImages) {
	treeAma0=new Image(); treeAma0.src = urlImages + "treeAma0.gif";
	treeAma1=new Image(); treeAma1.src = urlImages + "treeAma1.gif";
	treeAzu0=new Image(); treeAzu0.src = urlImages + "treeAzu0.gif";
	treeAzu1=new Image(); treeAzu1.src = urlImages + "treeAzu1.gif";
	treeNar0=new Image(); treeNar0.src = urlImages + "treeNar0.gif";
	treeNar1=new Image(); treeNar1.src = urlImages + "treeNar1.gif";
	treeRoj0=new Image(); treeRoj0.src = urlImages + "treeRoj0.gif";
	treeRoj1=new Image(); treeRoj1.src = urlImages + "treeRoj1.gif";
	treeVer0=new Image(); treeVer0.src = urlImages + "treeVer0.gif";
	treeVer1=new Image(); treeVer1.src = urlImages + "treeVer1.gif";
	treeCaq0=new Image(); treeCaq0.src = urlImages + "treeCaq0.gif";
	treeCaq1=new Image(); treeCaq1.src = urlImages + "treeCaq1.gif";
  treeGri0=new Image(); treeGri0.src = urlImages + "treeGri0.gif";
	treeGri1=new Image(); treeGri1.src = urlImages + "treeGri1.gif";
}

// Cambio de estilos de carpetas/elementos
// parent.menu.updateTree(3,"itemGri");
function updateTree(pos,valor) {
var sti = document.all.tags("LI")[pos].className;
	if (sti.length>0) {
	  //alert ("1: " + sti);
	  document.all.tags("LI")[pos].className=valor;
	} else {
	  //alert ("2: " + sti + "\n" + parent.menu.document.all.tags("LI")[pos].id);
	  document.all.tags("LI")[pos].style.listStyleImage="url(" + valor + ".gif)"
	  document.all.tags("LI")[pos].className=valor;
	  document.all.tags("LI")[pos].id=valor;
	}
}

// Contador de elementos hijos que contengan en el className el valor indicado
// parent.menu.countChildsStyle(0,"Gri")
function countChildsStyle(pos,sti,sum) {
var c=0;
	for (i=0; i<sum; i++) {
			node = document.getElementById(pos);
			if (node!=null && node.className.match(sti)) c++;
			pos++;
	}
	return c;
}

/* codigo original
function countChildsStyle(pos,sti,sum) {
var c=0;
  node = document.getElementById(pos).children[0];
  for (i=0; i<node.children.length; i++) {
	  if (node.children[i].className.match(sti)) c++;
	}
	return c;
}*/

function windowTopUpdateTreeDocs(idEpisodio, tipo) {
var win = window;
  //alert (idEpisodio + "," + tipo);
  //alert (window.opener.top.menu);
	//alert (window.opener.opener.top.menu);

var doc;
try {
  doc = window.opener.opener.top.menu.document;
} catch (ex) {
  return;
}
//	alert (doc.location);
	privateUpdateTreeDocs(doc,idEpisodio, tipo);
 	for (var i=0; win.opener && i<10; i++) {
    win = win.opener;
    //alert(i + ": " + win.name + "\n" + win.location + "\n" + win.document.location);
		try {
		  doc = win.top.menu.document;
			privateUpdateTreeDocs(doc,idEpisodio, tipo);
		} catch (iex){};
  }
}

function updateTreeDocs(idEpisodio, tipo) {
  privateUpdateTreeDocs(document,idEpisodio, tipo);
}

function privateUpdateTreeDocs(doc,idEpisodio, tipo) {
/*
Actualizar el color de la carpeta docs del árbol
Identificador del icono: "icoDoc"+idEpisodio     ej: id="icoDoc21568452"
Los class de dichos elementos, que habrá que cambiar según los cambios que se realicen, pueden ser:
- treeDocGri0:    No hay documentos                 (treeGri0.gif)
- treeDocInt0:    Hay documentos Internos           (treeAzu1.gif)
- treeDocExt0:    Hay documentos Externos           (treeMor1.gif)
- treeDocExtInt0: Hay documentos de las dos clases  (treeVer1.gif)
*/

var idIcoDoc = "icoDoc" + trim(idEpisodio);
var objIcoDoc = doc.getElementById(idIcoDoc);
  if (objIcoDoc) {
    if (tipo==1) {                                      // Documentos internos
      if (objIcoDoc.className=="treeDocGri0") {         // No hay documentos asociados
			  objIcoDoc.className="treeDocInt0";
  		} else if (objIcoDoc.className=="treeDocExt0") {  // Hay documentos externos
		  	objIcoDoc.className="treeDocExtInt0";
      }
    } else if (tipo==0) {                               // Documentos externos
      if (objIcoDoc.className=="treeDocGri0") {         // No hay documentos asociados
  			objIcoDoc.className="treeDocExt0";
	  	} else if (objIcoDoc.className=="treeDocInt0") {  // Hay documentos internos
  			objIcoDoc.className="treeDocExtInt0";
      }
    }
  }
}

function getContextPath() {
var ctx = location.pathname.substring(0,location.pathname.indexOf("/",1));
if (ctx.charAt(0)!="/") ctx = "/" + ctx;
return ctx;
}
