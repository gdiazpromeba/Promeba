function creaVentanaSolicitudes(){

	
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
                        	  var grafico=Ext.getCmp('graficoColumnas');
                        	  grafico.bindStore(storeGraficoRegiones);
                        	}
                        }
                    }
                },
                {boxLabel: 'Provincias', name: 'radioAreas', inputValue: 2, listeners: {
                        check: function (e) {
                        	if (e.checked){
                        	  dibujaProvincias();
                              var grafico=Ext.getCmp('graficoColumnas');
                        	  grafico.bindStore(storeGraficoProvincias);
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
	
	
	var grillaSolicitudes=new GrillaSolicitudes({region: 'center',
		    listeners :{
              'rowdblclick' : function(grid,rowIndex,columnIndex ){
              	var reg=grid.obtieneSeleccionado();
              	var frmSol=new FormActSolicitudes();
              	var solicitudId=reg.data['solicitudId'];
              	
              	
              	//frmSol.pueblaDatosEnForm(reg, frmSol);
                //frmSol.uneAGrillaPrincipal(grid, frmSol);

              	var winAct=new Ext.Window({
		          closable: true,
		          plain: true,
		          modal: true,
		          height: 300,
		          width:  950,
		          layout: 'fit',
		          items: [
		            frmSol
			      ],
			      listeners:{
			      	show: function(){
                      frmSol.pueblaDatosEnForm(reg, frmSol);
                      var solicitudId=reg.id;
                      var panelEqSoc=frmSol.getComponent('solapas').getComponent('eqSoc').getComponent('panelEqSoc');
                      panelEqSoc.setSolicitudId(solicitudId, panelEqSoc);
                      var grillaEqSoc=panelEqSoc.get('esxsGrid');
                      var storeEqSoc=grillaEqSoc.getStore();
                      storeEqSoc.reload({
                      	params:{
                          valorIdPadre : solicitudId	
                      	}
                      });
                      //alert('el id de solicitud es ' + solicitudId);	
     		      	}
			      }
  	           });
  	           winAct.show();
             }
           }
    });
  
  var storeGraficoRegiones = new Ext.data.JsonStore({
    fields:
      [
        {name:'area', type: 'string'},
        {name:'solicitudes', type:'int'}, 
        {name:'presupuesto', type:'float'}
      ],
    data: 
      [
        {area: 'Patagonia', solicitudes: 197, presupuesto: 117},
        {area: 'NOA', solicitudes: 195, presupuesto: 735},
        {area: 'NEA', solicitudes: 90, presupuesto: 423},
        {area: 'Bs.As.', solicitudes: 250, presupuesto: 1400},
        {area: 'Centro', solicitudes: 85, presupuesto: 240}
      ]
  });
  
  var storeGraficoProvincias = new Ext.data.JsonStore({
    fields:
      [
        {name:'area', type: 'string'},
        {name:'solicitudes', type:'int'}, 
        {name:'presupuesto', type:'float'}
      ],
    data: 
      [
        {area: 'Bs.As', solicitudes: 19, presupuesto: 11},
        {area: 'Córdoba', solicitudes: 9, presupuesto: 8},
        {area: 'Catamarca', solicitudes: 5, presupuesto: 7},
        {area: 'Chaco', solicitudes: 15, presupuesto: 6},
        {area: 'Corrientes', solicitudes: 9, presupuesto: 4},
        {area: 'Chubut', solicitudes: 10, presupuesto: 12},
        {area: 'Formosa', solicitudes: 3, presupuesto: 2},
        {area: 'Jujuy', solicitudes: 19, presupuesto: 7},
        {area: 'Formosa', solicitudes: 9, presupuesto: 13},
        {area: 'La Pampa', solicitudes: 2, presupuesto: 11},
        {area: 'La Rioja', solicitudes: 22, presupuesto: 8},
        {area: 'Mendoza', solicitudes: 8, presupuesto: 11},
        {area: 'Misiones', solicitudes: 14, presupuesto: 5},
        {area: 'Neuquén', solicitudes: 18, presupuesto: 6},
        {area: 'Río Negro', solicitudes: 14, presupuesto: 10},
        {area: 'Salta', solicitudes: 9, presupuesto: 13},
        {area: 'San Juan', solicitudes: 4, presupuesto: 3},
        {area: 'San Luis', solicitudes: 3, presupuesto: 8},
        {area: 'Santa Cruz', solicitudes: 9, presupuesto: 9},
        {area: 'Sgo. del Estero', solicitudes: 8, presupuesto: 7},
        {area: 'T.del Fuego', solicitudes: 7, presupuesto: 7},
        {area: 'Tucumán', solicitudes: 9, presupuesto: 8}
      ]
  });  
  	
	
	
	var panelCentro = new Ext.Panel({
		region: 'center',
		layout: 'border',
		items : [
          grillaSolicitudes,
		  //tiene que haber un panel contenedor, agregar el gráfico directamente no funciona 
		 {xtype: 'panel', itemId: 'contenedorGrafico', height: 230, layout: 'hbox', region: 'north', 
            layoutConfig: {
              align : 'stretch',
             pack  : 'start'
            },
            items:[
              {xtype: 'columnchart', 
		       region: 'north',  
		       itemId: 'grafico',
		       id: 'graficoColumnas',
		       height: 600,
		       
	            axes: [{
                  type: 'presupuesto',
                  position: 'left',
                  fields: ['presupuesto'],
                  label: {
                    renderer: Ext.util.Format.numberRenderer('0,0')
                  },
                  title: 'Presupuesto Estimado',
                  grid: true,
                  minimum: 0
               }, {
                 type: 'Numeric',
                 position: 'bottom',
                 fields: ['region'],
                 title: 'Regiones'
               }],	       
		       
               //store: storeGrafico,
               xField: 'area',
               yAxis: new Ext.chart.NumericAxis({
                 displayName: 'Solicitudes',
                 labelRenderer : Ext.util.Format.numberRenderer('0,0')
               }),
            
               tipRenderer : function(chart, record, index, series){
                if(series.yField == 'solicitudes'){
                    return Ext.util.Format.number(record.data.solicitudes, '0,0') + ' solicitudes  ingresadas';
                }else{
                    return Ext.util.Format.number(record.data.presupuesto, '0,0') + ' decenas de miles ';
                }
              },
              /*
              chartStyle: {
                padding: 10, 
                animationEnabled: true,
                font: {name: 'Tahoma', color: 0x444444, size: 11 },
                dataTip: {
                    padding: 5,
                    border: {color: 0x99bbe8, size:1 },
                    background: {color: 0xDAE7F6, alpha: .9 },
                    font: { name: 'Tahoma', color: 0x15428B, size: 10, bold: true}
                },
                xAxis: {
                    color: 0x69aBc8,
                    majorTicks: {color: 0x69aBc8, length: 4},
                    minorTicks: {color: 0x69aBc8, length: 2},
                    majorGridLines: {size: 1, color: 0xeeeeee}
                },
                yAxis: {
                    color: 0x69aBc8,
                    majorTicks: {color: 0x69aBc8, length: 4},
                    minorTicks: {color: 0x69aBc8, length: 2},
                    majorGridLines: {size: 1, color: 0xdfe8f6}
                }
              },
              */
              series: [{
                type: 'column', displayName: 'Cantidad de solicitudes', yField: 'solicitudes',
                style: {color:0x99BBE8}
              },{
                type:'column', displayName: 'Presupuesto Estimado', yField: 'presupuesto',
                style: {color: 0x15428B }
              }]
            }
          ]	
        }//panel superior

      ]//items del contenedorGrafico
	});	
	

  
  

	var win=new Ext.Window({
		closable: true,
		plain: true,
		modal: true,
		height: 640,
		width: 1350,
		layout: 'border',
		items: [
		  panelSeleccion,
		  panelCentro
		]
	});	
	
	win.show();
}
