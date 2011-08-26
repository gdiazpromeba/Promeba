FormProyectos = Ext.extend(PanelFormCabeceraAbm, {
  
  constructor : function(config) {
    FormProyectos.superclass.constructor.call(this, Ext.apply({
  		id: 'formProyectos',
  		prefijo: 'formProyectos',
  		nombreElementoId: 'proyectoId',
  	    urlAgregado: '/prototipo/svc/conector/proyectos/inserta',
  	    urlModificacion: '/prototipo/svc/conector/proyectos/actualiza',
  	    urlBorrado: '/prototipo/svc/conector/proyectos/borra',
  	    layout: 'column',
  		items: [
          {xtype: 'hidden', name: 'proyectoId', id: 'proyectoId', itemId: 'proyectoId'},
          {xtype: 'hidden', name: 'solicitudId', id: 'solicitudId', itemId: 'solicitudId'},
          {xtype: 'fieldset', itemId : 'colIzq', columnWidth: 0.5, layout: 'form', border: false, 
           items: [
            {fieldLabel: 'Descripcion',  xtype: 'textfield', itemId: 'descripcion', name: 'descripcion', width: 220, allowBlank: false},
            {fieldLabel: 'Solicitud',  xtype: 'textfield', itemId: 'solicitudDescripcion', name: 'solicitudDescripcion', width: 220, allowBlank: false},
            {fieldLabel: 'Lotes',  xtype: 'numberfield', itemId: 'cantidadLotes', name: 'cantidadLotes', allowDecimals: false, width: 80, allowBlank: false},
            {fieldLabel: 'Monto estimado',  xtype: 'dinero', itemId: 'presupuestoEstimado', name: 'presupuestoEstimado', width: 80, allowBlank: false},
	        {fieldLabel: 'Estado', xtype: 'combo', id: 'comboEstados', name: 'comboEstados', itemId: 'comboEstados', ref: '../comboEstados', allowBlank: false, 
	          store: new Ext.data.SimpleStore({
	          fields: ['descripcionEstado'],
	    	     data: [["Viabilidad en estudio"],["Viabilidad en documentada"], ["Viabilidad aprobada"], ["Viabilidad condicional"], ["En formulación"]]
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
              {xtype: 'combosituacionesdominiales', itemId: 'comboSituacionesDominiales', hiddenName: 'situacionDominialId', hiddenId: 'situacionDominialId', allowBlank: false},
              {xtype: 'combotiposinversion', itemId: 'comboTiposInversion', hiddenName: 'tipoInversionId', hiddenId: 'tipoInversionId', allowBlank: false},
            ]
          }//del fieldset 'colDer'
      ], 
      listeners: {
          beforerender: function(me) {
            //oculto botAgregar
            me.buttons[0].setVisible(false);
            return true;
          }
 	  },     
  	  pueblaDatosEnForm : function(record){
  	  	 var colIzq=this.getComponent('colIzq');
         this.getComponent('proyectoId').setValue(record.id);
         this.getComponent('solicitudId').setValue(record.get('solicitudId'));
         colIzq.getComponent('descripcion').setValue(record.get('descripcion'));
         colIzq.getComponent('solicitudDescripcion').setValue(record.get('solicitudDescripcion'));
         colIzq.getComponent('presupuestoEstimado').setValue(record.get('presupuestoEstimado'));
         colIzq.getComponent('cantidadLotes').setValue(record.get('cantidadLotes'));
         colIzq.getComponent('comboEstados').setValue(record.get('estado'));
         Ext.get('estado').dom.value=record.get('estado');
         colIzq.getComponent('cmbSubejecutores').setRawValue(record.get('subejecutorNombre'));
         Ext.get('subejecutorId').dom.value=record.get('subejecutorId');
         var colDer=this.getComponent('colDer');
         colDer.getComponent('fechaDesde').setValue(record.data['fechaDesde']);
         colDer.getComponent('fechaHasta').setValue(record.data['fechaHasta']);
         colDer.getComponent('comboSituacionesDominiales').setRawValue(record.get('situacionDominialNombre'));
         Ext.get('situacionDominialId').dom.value=record.get('situacionDominialId');
         colDer.getComponent('comboTiposInversion').setRawValue(record.get('tipoInversionNombre'));
         Ext.get('tipoInversionId').dom.value=record.get('tipoInversionId');
         

         
  	   },
  	   
  	   pueblaFormEnRegistro : function(record){
  	     record.id=  this.getComponent('proyectoId').getValue();
  	     record.data['solicitudDescripcion']=  this.getComponent('solicitudId').getValue();
  	     record.data['solicitudDescripcion']=  colIzq.getComponent('solicitudDescripcion').getValue();
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
    	 record.data['situacionDominialNombre']=  colDer.getComponent('comboSituacionesDominiales').getRawValue();
    	 record.data['situacionDominialId'] = Ext.get('situacionDominialId').dom.value;
         record.data['tipoInversionNombre']=  colDer.getComponent('comboTiposInversion').getRawValue();
    	 record.data['tipoInversionId'] = Ext.get('tipoInversionId').dom.value;
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
  		   
  	   	   if (!colDer.getComponent('comboTiposInversion').isValid()){
  			   valido=false;
  			   mensaje='El tipo de inversión no es válido';
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











