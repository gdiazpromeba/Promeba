FormActSolicitudes = Ext.extend(Ext.FormPanel, {
  
  constructor : function(config) {
    FormActSolicitudes.superclass.constructor.call(this, Ext.apply({
  		id: 'formSolicitudes',
  		prefijo: 'formSolicitudes',
  		nombreElementoId: 'solicitudId',
  	    urlAgregado: '/prototipo/svc/conector/solicitudes/inserta',
  	    urlModificacion: '/prototipo/svc/conector/solicitudes/actualiza',
  	    urlBorrado: '/prototipo/svc/conector/solicitudes/borra',
  	    layout: 'fit',
  	    items: [
  	      {xtype: 'hidden', name: 'solicitudId', id: 'solicitudId', itemId: 'solicitudId'},
  	      {xtype: 'tabpanel', itemId: 'solapas', deferredRender : false, hideMode: 'offsets', activeTab: 0,
  	        items:[
  	          {title:'General', layout:'column', frame: true, itemId: 'generales', 
  		       items: [
				  {xtype: 'fieldset', itemId : 'colIzq', columnWidth: 0.5, layout: 'form', border: false, 
			           items: [
			            {fieldLabel: 'Descripcion',  xtype: 'textfield', itemId: 'descripcion', name: 'descripcion', width: 220, allowBlank: false},
			            {fieldLabel: 'Lotes',  xtype: 'numberfield', itemId: 'cantidadLotes', name: 'cantidadLotes', allowDecimals: false, width: 80, allowBlank: false},
			            {fieldLabel: 'Monto estimado',  xtype: 'dinero', itemId: 'presupuestoEstimado', name: 'presupuestoEstimado', width: 80, allowBlank: false},
				        {fieldLabel: 'Estado', xtype: 'combo', id: 'comboEstados', name: 'comboEstados', itemId: 'comboEstados', ref: '../comboEstados', allowBlank: false,
				          store: new Ext.data.SimpleStore({
				            fields: ['descripcionEstado'],
				    	     data: [["Ingresada"],["Seleccionada"],["Aprobada"], ["En ejecución"],["Finalizada"]]
				    	  }),
			              displayField: 'descripcionEstado', valueField: 'descripcionEstado', selectOnFocus: true, mode: 'local', typeAhead: false, editable: false,
			              hiddenName: 'estadoActSolicitudes', hiddenId: 'estadoActSolicitudes', triggerAction: 'all'
			            },
			            {xtype: 'combosubejecutores', itemId: 'cmbSubejecutores', hiddenName: 'subejecutorId', width: 220}            
			           ]
				   },//del fieldset 'colIzq'
				   {xtype: 'fieldset', itemId : 'colDer', columnWidth: 0.5, layout: 'form', border: false,
			            items: [
			              {fieldLabel: 'Desde',  itemId: 'fechaDesde', name: 'fechaDesde', xtype : 'datefield', format: 'd/m/Y', allowBlank : false},
			              {fieldLabel: 'Hasta',  itemId: 'fechaHasta', name: 'fechaHasta', xtype : 'datefield', format: 'd/m/Y', allowBlank : true},
			              {fieldLabel: 'Ingreso POA',  itemId: 'fechaIngresoPOA', name: 'fechaIngresoPOA', xtype : 'datefield', format: 'd/m/Y', allowBlank : true},
			              {fieldLabel: 'Ingreso PGEP',  itemId: 'fechaIngresoPGEP', name: 'fechaIngresoPGEP', xtype : 'datefield', format: 'd/m/Y', allowBlank : true},
			              {fieldLabel: 'Ingreso PA',  itemId: 'fechaIngresoPA', name: 'fechaIngresoPA', xtype : 'datefield', format: 'd/m/Y', allowBlank : true},
			              new ControlSuba({fieldLabel: 'Vínculo', width: 200, itemId: 'vinculoSolicitud'}),
			            ]
				    }//del fieldset 'colDer'
				 ],      
  	         },//del panel de datos generales
  	         {title:'Equipamientos sociales', layout:'fit', frame: true, itemId: 'eqSoc',
  	           items: [
  	             new PanelEqSoc({height: 280, itemId: 'panelEqSoc'})
  	           ] 
  	         }/* ,//fin del panel de Equipamientos Sociales
  	         {title:'Servicios Públicos Disponibles', layout:'fit', frame: true, itemId: 'spd',
  	           items: [
  	             new PanelSpd({height: 280, itemId: 'panelSpd'})
  	           ] 
  	         },//fin del panel de Servicios Públicos Disponibles
    	     {title:'Estados de mensura', layout:'fit', frame: true, itemId: 'esm',
  	           items: [
  	             new PanelEsm({height: 280, itemId: 'panelEsm'})
  	           ] 
  	         },//fin del panel de Estados de mensura
    	       {title:'Tipos de riesgo', layout:'fit', frame: true, itemId: 'tir',
  	             items: [
  	               new PanelTir({height: 280, itemId: 'panelTir'})
  	             ] 
  	           }//fin del panel de Tipos de riesgo
  	           */
  	         ]
    	    }
  	       ],
  	       
  	       buttons:[
  	         {text : 'Guardar', disabled : false, scope: this,
                handler : function() {
                  this.getForm().submit({
                  url : '/prototipo/svc/conector/solicitudes/actualiza',
                  waitMsg : 'Grabando datos...',
                  failure: function (form, action) {
                    Ext.MessageBox.show({
                        title: 'Error al grabar los datos',
                        msg: 'Error al grabar los datos.',
                        buttons: Ext.MessageBox.OK,
                        icon: Ext.MessageBox.ERROR
                    });
                  },
                  success: function (form, request) {
                    Ext.MessageBox.show({
                        title: 'Datos grabados correctamente',
                        msg: 'Datos grabados correctamente',
                        buttons: Ext.MessageBox.OK,
                        icon: Ext.MessageBox.INFO
                    });
                    /*
                    responseData = Ext.util.JSON.decode(request.response.responseText);
                    this.getForm().load({
                        url : 'formLoader.php',
                        method: 'GET',
                        params: {
                            cat_id: responseData.cat_id
                        },
                        waitMsg : 'Espere por favor'
                    });
                    */
                  }
                });//del submit
              }//del handler
            }, //del button 'Guardar'
            {text : 'Cerrar', disabled : false, scope: this,
              handler : function() {
              	alert('cerrando ...');
              }
             }
           ],//de buttons
           
           listeners: {
           	  'render' :  function(me){
           	    var panEqSoc = me.getComponent('solapas').getComponent('eqSoc').getComponent('panelEqSoc');
           	    var solicitudId = me.getComponent('solicitudId').getValue();
           	    panEqSoc.setSolicitudId(solicitudId, panEqSoc);
           	  }
           	
           },
  	   
  	     pueblaDatosEnForm : function(record, me){
  	  	   var solapas=me.getComponent('solapas');
  	  	   var generales=solapas.getComponent('generales');
  	  	   var colIzq=generales.getComponent('colIzq');
           me.getComponent('solicitudId').setValue(record.id);
           colIzq.getComponent('descripcion').setValue(record.get('descripcion'));
           colIzq.getComponent('presupuestoEstimado').setValue(record.get('presupuestoEstimado'));
           colIzq.getComponent('cantidadLotes').setValue(record.get('cantidadLotes'));
           colIzq.getComponent('comboEstados').setValue(record.get('estado'));
           Ext.get('estadoActSolicitudes').dom.value=record.get('estado');
           colIzq.getComponent('cmbSubejecutores').setRawValue(record.get('subejecutorNombre'));
           Ext.get('subejecutorId').dom.value=record.get('subejecutorId');
           var colDer=generales.getComponent('colDer');
           colDer.getComponent('fechaDesde').setValue(record.data['fechaDesde']);
           colDer.getComponent('fechaHasta').setValue(record.data['fechaHasta']);
           colDer.getComponent('fechaIngresoPOA').setValue(record.data['fechaIngresoPOA']);
           colDer.getComponent('fechaIngresoPA').setValue(record.data['fechaIngresoPA']);
           colDer.getComponent('fechaIngresoPGEP').setValue(record.data['fechaIngresoPGEP']);
           colDer.getComponent('vinculoSolicitud').setValue(record.data['vinculo']);
  	     },
  	   
  	   pueblaFormEnRegistro : function(record){
  	   	 var solapas=this.getComponent('solapas');
  	  	 var generales=solapas.getComponent('generales');
  	  	 var colIzq=generales.getComponent('colIzq');
   	     record.id=  this.getComponent('solicitudId').getValue();
   	     record.data['descripcion']=  colIzq.getComponent('descripcion').getValue();
  	     record.data['estado']=  Ext.get('estadoActSolicitudes').dom.value;
  	     record.data['subejecutorId']=  Ext.get('estado').dom.value;
  	     record.data['subejecutorNombre']=  colIzq.getComponent('cmbSubejecutores').getRawValue();
  	     record.data['presupuestoEstimado']=  colIzq.getComponent('presupuestoEstimado').getValue();
  	     record.data['cantidadLotes']=  colIzq.getComponent('cantidadLotes').getValue();
  	     var colDer=generales.getComponent('colDer');
    	 record.data['fechaDesde']=  colDer.getComponent('fechaDesde').getValue();
    	 record.data['fechaHasta']=  colDer.getComponent('fechaHasta').getValue();
    	 record.data['fechaIngresoPOA']=  colDer.getComponent('fechaIngresoPOA').getValue();
    	 record.data['fechaIngresoPA']=  colDer.getComponent('fechaIngresoPA').getValue();
    	 record.data['fechaIngresoPGEP']=  colDer.getComponent('fechaIngresoPGEP').getValue();
    	 record.data['vinculo']=  colDer.getComponent('vinculoSolicitud').getValue();
  		 record.commit();
  	   },  	   
  	   
  	   validaHijo : function(muestraVentana){
  		   var mensaje=null;
  		   var valido=true;
  		   
  		   var colIzq=this.getComponent('colIzq');
   		   if (!colIzq.getComponent('descripcion').isValid()){
  			   valido=false;
  			   mensaje='La descripción no es válida';
  		   }
  		   
  	  	   if (!colIzq.getComponent('comboEstados').isValid()){
  			   valido=false;
  			   mensaje='El estado no es válido';
  		   }
  		   
 	       var colDer=this.getComponent('colDer');
   		   if (!colDer.getComponent('fechaDesde').isValid()){
  			   valido=false;
  			   mensaje='La fecha desde no es válida';
  		   }	   
	       
  		   
  		   if (!valido && muestraVentana){
  	           Ext.MessageBox.show({
  	               title: 'Validación de campos',
  	               msg: mensaje,
  	               buttons: Ext.MessageBox.OK,
  	               icon: Ext.MessageBox.ERROR
  	           });
  		   }
  		   return valido;
  	   }
  	   
  	   
	   }, config));
  
  }, //constructor
  
  setSolicitudId : function(solicitudId, me){
  	me.getComponent('solicitudId').setValue(solicitudId);
  },
  
  getSolicitudId : function(me){
  	return me.getComponent('solicitudId').getValue();
  }
});











