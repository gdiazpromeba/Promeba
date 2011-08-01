/**
 * @author Shea
 */

Ext.onReady(function(){

	var poligonos=new Array();
	
	function borraPoligonos(){
		for(i=0; i<poligonos.length; i++){
			poligonos[i].setMap(null);
		}
		poligonos.length=0;
	}
	
	function dibujaProvincias() {
			borraPoligonos();
			//var regionId=record.data['id'];
			var map = Ext.getCmp('my_map');
			var polProv = new Array();
			Ext.Ajax.request({
				url : '/prototipo/svc/conector/geografia/poligonosProvincias',
				method : 'POST',
				failure : function(response, options) {
					Ext.Msg.show({
						title : 'Información de Google Maps',
						msg : 'Error de red o base de datos',
						buttons : Ext.Msg.OK
					});
				},
				success : function(response, options) {
					var objRespuesta = Ext.util.JSON.decode(response.responseText);
					var totalRegistros = objRespuesta.total;
	
					for( j = 0; j < totalRegistros; j++) {
						var pol = new Array();
						var reg = objRespuesta.data[j];
						//if (regProvincia.provinciaNombre!='Jujuy' && regProvincia.provinciaNombre!='Salta') continue;
						//&& regProvincia.provinciaNombre!='Salta') continue;
						if (typeof reg.coordenadas == 'undefined'){
							alert('el reg ' + i +  ' no tiene coordenadas');
						}
						for( i = 0; i < reg.coordenadas.total; i++) {
							var coor = reg.coordenadas.data[i];
							pol[i] = new google.maps.LatLng(coor.latitud, coor.longitud);
						}
						poligonos[j] = new google.maps.Polygon({
							paths : pol,
							strokeColor : reg.color,
							strokeOpacity : 0.5,
							strokeWeight : 2,
							fillColor : reg.color,
							fillOpacity : 0.35,
							provId : reg.id,
							provNombre : reg.nombre
						});
						poligonos[j].setMap(map.getMap());
						google.maps.event.addListener(poligonos[j],'click',function(evt){
						  var store= grillaSolicitudes.getStore();
						  store.reload({
						  	params:{
						  	  provinciaId: this.provId
						  	}
						  })
                          //alert('cliqueaste id=' + this.regionNombre);
                        });
					}
				}
			});
	
		}
		
		function dibujaRegiones() {
			borraPoligonos();
			//var regionId=record.data['id'];
			var map = Ext.getCmp('my_map');
			var polProv = new Array();
			Ext.Ajax.request({
				url : '/prototipo/svc/conector/geografia/poligonosRegiones',
				method : 'POST',
				failure : function(response, options) {
					Ext.Msg.show({
						title : 'Información de Google Maps',
						msg : 'Error de red o base de datos',
						buttons : Ext.Msg.OK
					});
				},
				success : function(response, options) {
					var objRespuesta = Ext.util.JSON.decode(response.responseText);
					var totalRegistros = objRespuesta.total;
	
					for( j = 0; j < totalRegistros; j++) {
						var pol = new Array();
						var reg = objRespuesta.data[j];
						if (typeof reg.coordenadas == 'undefined'){
							alert('el reg ' + i +  ' no tiene coordenadas');
						}						
						for( i = 0; i < reg.coordenadas.total; i++) {
							var coor = reg.coordenadas.data[i];
							pol[i] = new google.maps.LatLng(coor.latitud, coor.longitud);
						}
						poligonos[j] = new google.maps.Polygon({
							paths : pol,
							strokeColor : reg.color,
							strokeOpacity : 0.8,
							strokeWeight : 3,
							fillColor : reg.color,
							fillOpacity : 0.35,
							regionId : reg.id,
							regionNombre : reg.nombre
						});
						
						google.maps.event.addListener(poligonos[j],'click',function(evt){
						  var store= grillaSolicitudes.getStore();
						  store.reload({
						  	params:{
						  	  regionId: this.regionId
						  	}
						  })
                          //alert('cliqueaste id=' + this.regionNombre);
                        });
						
						poligonos[j].setMap(map.getMap());
					}
				}
			});
	
		}	
	
	var panelOpciones=new Ext.Panel({
	  height: 50,
	  layout: 'form',
	  region: 'north',
	  padding: 5,
	  items : [
	    {
            xtype: 'radiogroup',
            fieldLabel: 'Áreas',
            cls: 'x-check-group-alt',
            columns: 2,
            items: [
                {boxLabel: 'Regiones', name: 'radioAreas', inputValue: 1, listeners: {
                        check: function (e) {
                        	if (e.checked){
                        	  dibujaRegiones();	
                        	}
                        }
                    }
                },
                {boxLabel: 'Provincias', name: 'radioAreas', inputValue: 2, listeners: {
                        check: function (e) {
                        	if (e.checked){
                        	  dibujaProvincias();	
                        	}
                        }
                    }
                },
            ]
        },
	   ],
	   
	   
	});

	var panelSeleccion= new Ext.Panel({
		layout: 'border',
		region: 'west',
		width: 500,
		items: [
		  panelOpciones,
		  {
			region: 'center',
			xtype: 'gmappanel',
			region: 'center',
            zoomLevel: 4,
            gmapType: 'map',
            id: 'my_map',
			border: true,
            mapConfOpts: ['enableScrollWheelZoom','enableDoubleClickZoom','enableDragging'],
            mapControls: ['GSmallMapControl','GMapTypeControl'],
            setCenter: {
                lat: -40.00,
                lng: -64.38
            }
         }
       ]
	});
	
	
	var grillaSolicitudes=new GrillaSolicitudes({
		region: 'center'
	});
	
	var panelCentro = new Ext.Panel({
		region: 'center',
		layout: 'border',
		items : [
		  grillaSolicitudes
		]
	});
	
	var backendViewport = new Ext.Viewport({  
         layout: 'border',  
         items: [
         	panelSeleccion,
         	panelCentro
         ]
    });
	  
	
  
    
 });