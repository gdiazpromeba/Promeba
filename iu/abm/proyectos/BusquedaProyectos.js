BusquedaProyectos = Ext.extend(PanelBusquedaAbm, {
	constructor : function(config) {
	  BusquedaProyectos.superclass.constructor.call(this, Ext.apply({
        region: 'west',
        frame: true,
        items: [
          {xtype: 'tabpanel', itemId: 'tabs', region: 'center', activeItem: 0, deferredRender : false, height: 530, width: 500,
            items : [
              {xtype: 'panel', itemId: 'porCampos', title: 'Por Campos', layout: 'form', frame: true, 
                defaults: {
                	labelWidth: 50
                },
                items: [
                  {fieldLabel: 'Subejecutor',  itemId: 'cmbSubejecutores', xtype : 'combosubejecutores',  
                    hiddenName: 'subejecutorIdBus',  hiddenId: 'subejecutorIdBus',  allowBlank : true, width: 160},
	              {fieldLabel: 'Estado', xtype: 'combo', id: 'comboEstadosBus', name: 'comboEstadosBus', itemId: 'comboEstadosBus', ref: '../comboEstadosBus', allowBlank: true, width: 200, 
	                store: new Ext.data.SimpleStore({
	                  fields: ['descripcionEstado'],
	    	           data: [["Viabilidad en estudio"],["Viabilidad en documentada"], ["Viabilidad aprobada"], ["Viabilidad condicional"], ["En formulación"]]
	    	        }),
                    displayField: 'descripcionEstado', valueField: 'descripcionEstado', selectOnFocus: true, mode: 'local', typeAhead: false, editable: false,
                    hiddenName: 'estadoBus', triggerAction: 'all'
                  }
                ]
              },
              {
                itemId: 'porProvincia', title: 'Por Provincia',
			    region: 'center',
			    xtype: 'gmappanel',
			    region: 'center',
                zoomLevel: 4,
                gmapType: 'map',
                id: 'mapaProvincias',
			    border: true,
			    height: 510,
                mapConfOpts: ['enableScrollWheelZoom','enableDoubleClickZoom','enableDragging'],
                mapControls: ['GSmallMapControl','GMapTypeControl'],
                setCenter: {
                  lat: -40.00,
                  lng: -60.38
                }
              },
              {
                itemId: 'porRegion', title: 'Por Región',
			    region: 'center',
			    xtype: 'gmappanel',
			    region: 'center',
                zoomLevel: 4,
                gmapType: 'map',
                id: 'mapaRegiones',
			    border: true,
			    height: 510,
                mapConfOpts: ['enableScrollWheelZoom','enableDoubleClickZoom','enableDragging'],
                mapControls: ['GSmallMapControl','GMapTypeControl'],
                setCenter: {
                  lat: -40.00,
                  lng: -60.38
                }
              },
            ]
          }//del tab
        ],
      
        getParamsBusqueda: function(){
          var resultado=new Array();
          this.agregaClaveValor(resultado, 'estado',  Ext.get('estadoBus').dom.value);
          this.agregaClaveValor(resultado, 'subejecutorId', Ext.get('subejecutorIdBus').dom.value);
          this.agregaClaveValor(resultado, 'provinciaId', this.provinciaId);
          this.agregaClaveValor(resultado, 'regionId', this.regionId);
          return resultado;
        },
        
        listeners: {
        	scope: this,
        	'render' : function(me){
        		me.dibujaProvincias(me);
        		me.dibujaRegiones(me);
        		me.getComponent('tabs').setActiveTab(1);
        		me.getComponent('tabs').setActiveTab(0);
        	}
        }
      
      }, config));
      var poligonoSeleccionProvincia;
      var poligonoSeleccionRegion;
      var regionId;
      var provinciaId;
    
	}, //constructor
	
    dibujaProvincias: function(me) {
    	var poligonos=new Array();
			//var regionId=record.data['id'];
		var map = Ext.getCmp('mapaProvincias');
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
					  me.borraPoligonosSeleccion(me);
					  me.poligonoSeleccionProvincia = new google.maps.Polygon({
						paths : this.getPaths(),
						strokeColor : '#FF0000',
						strokeOpacity : 0.5,
						strokeWeight : 3,
						fillColor : '#FF0000',
						fillOpacity : 0.5,
						regionId : this.provId,
						regionNombre : this.provNombre
					  });
					  me.poligonoSeleccionProvincia.setMap(map.getMap());	
					  me.provinciaId=this.provId;	
                    });
				}
			}
		});

    },	
    
	dibujaRegiones : function(me) {
		var poligonos=new Array();
		//borraPoligonos();
		//var regionId=record.data['id'];
		var map = Ext.getCmp('mapaRegiones');
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
					  //crea un nuevo polígono con los datos de la región seleccionada
					  me.borraPoligonosSeleccion(me);
					  me.poligonoSeleccionRegion = new google.maps.Polygon({
						paths : this.getPaths(),
						strokeColor : '#FF0000',
						strokeOpacity : 0.5,
						strokeWeight : 3,
						fillColor : '#FF0000',
						fillOpacity : 0.5,
						regionId : this.regionId,
						regionNombre : this.regionNombre
					  });
					  me.poligonoSeleccionRegion.setMap(map.getMap());
					  me.regionId=this.regionId;
                    });
					
					poligonos[j].setMap(map.getMap());
				}
			}
		});
	},
	
	borraPoligonos : function(){
		for(i=0; i<poligonos.length; i++){
			poligonos[i].setMap(null);
		}
		poligonos.length=0;
	},	
	
    borraPoligonosSeleccion : function(me){
    	me.regionId=null;
    	me.provinciaId=null;
    	if (me.poligonoSeleccionProvincia!=undefined){
    	  me.poligonoSeleccionProvincia.setMap(null);
    	  me.poligonoSeleccionProvincia.length=0;
        }
        if (me.poligonoSeleccionRegion!=undefined){
    	  me.poligonoSeleccionRegion.setMap(null);
    	  me.poligonoSeleccionRegion.length=0;
    	}
	}   
	
	
});
