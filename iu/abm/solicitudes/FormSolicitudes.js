FormSolicitudes = Ext.extend(PanelFormCabeceraAbm, {
  
  constructor : function(config) {
    FormSolicitudes.superclass.constructor.call(this, Ext.apply({
  		id: 'formSolicitudes',
  		prefijo: 'formSolicitudes',
  		nombreElementoId: 'solicitudId',
  	    urlAgregado: '/prototipo/svc/conector/solicitudes/inserta',
  	    urlModificacion: '/prototipo/svc/conector/solicitudes/actualiza',
  	    urlBorrado: '/prototipo/svc/conector/solicitudes/borra',
  	    layout: 'column',
  		items: [
          {xtype: 'hidden', name: 'solicitudId', id: 'solicitudId', itemId: 'solicitudId'},
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
              hiddenName: 'estado', triggerAction: 'all'
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
      
  	   
  	  pueblaDatosEnForm : function(record){
  	  	 var colIzq=this.getComponent('colIzq');
         this.getComponent('solicitudId').setValue(record.id);
         colIzq.getComponent('descripcion').setValue(record.get('descripcion'));
         colIzq.getComponent('presupuestoEstimado').setValue(record.get('presupuestoEstimado'));
         colIzq.getComponent('cantidadLotes').setValue(record.get('cantidadLotes'));
         colIzq.getComponent('comboEstados').setValue(record.get('estado'));
         Ext.get('estado').dom.value=record.get('estado');
         colIzq.getComponent('cmbSubejecutores').setRawValue(record.get('subejecutorNombre'));
         Ext.get('subejecutorId').dom.value=record.get('subejecutorId');
         var colDer=this.getComponent('colDer');
         colDer.getComponent('fechaDesde').setValue(record.data['fechaDesde']);
         colDer.getComponent('fechaHasta').setValue(record.data['fechaHasta']);
         colDer.getComponent('fechaIngresoPOA').setValue(record.data['fechaIngresoPOA']);
         colDer.getComponent('fechaIngresoPA').setValue(record.data['fechaIngresoPA']);
         colDer.getComponent('fechaIngresoPGEP').setValue(record.data['fechaIngresoPGEP']);
         colDer.getComponent('vinculoSolicitud').setValue(record.data['vinculo']);
  	   },
  	   
  	   pueblaFormEnRegistro : function(record){
  	     record.id=  this.getComponent('solicitudId').getValue();
  	     var colIzq=this.getComponent('colIzq');
  	     record.data['descripcion']=  colIzq.getComponent('descripcion').getValue();
  	     record.data['estado']=  Ext.get('estado').dom.value;
  	     record.data['subejecutorId']=  Ext.get('estado').dom.value;
  	     record.data['subejecutorNombre']=  colIzq.getComponent('cmbSubejecutores').getRawValue();
  	     record.data['presupuestoEstimado']=  colIzq.getComponent('presupuestoEstimado').getValue();
  	     record.data['cantidadLotes']=  colIzq.getComponent('cantidadLotes').getValue();
  	     var colDer=this.getComponent('colDer');
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
  
  } //constructor
  
  
  
});











