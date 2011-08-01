FormAreasGeograficas = Ext.extend(PanelFormCabeceraAbm, {
  
  constructor : function(config) {
    FormAreasGeograficas.superclass.constructor.call(this, Ext.apply({
  		id: 'formAge',
        frame: true,
  		prefijo: 'formAge',
  		nombreElementoId: 'areaGeograficaId',
  	    urlAgregado: '/prototipo/svc/conector/areasGeograficas/inserta',
  	    urlModificacion: '/prototipo/svc/conector/areasGeograficas/actualiza',
  	    urlBorrado: '/prototipo/svc/conector/areasGeograficas/borra',
  		items: [
          {xtype: 'hidden', name: 'areaGeograficaId', id: 'areaGeograficaId', itemId: 'areaGeograficaId'},
          {fieldLabel: 'Nombre',  xtype: 'textfield', itemId: 'nombre', name: 'nombre'},
          {xtype: 'controlsuba', fieldLabel: 'Archivo G.E.', width: 200, itemId: 'archivoGE', urlSuba: '/prototipo/upload', 
             urlDestino: document.location.protocol + '//' + document.location.host + '/prototipo/archivos' },
          {fieldLabel: 'Fecha desde',  itemId: 'fechaDesde', name: 'fechaDesde', xtype : 'datefield', format: 'd/m/Y', allowBlank : true},
          {fieldLabel: 'Fecha hasta',  itemId: 'fechaHasta', name: 'fechaHasta', xtype : 'datefield', format: 'd/m/Y', allowBlank : true}
      ],      
      
  	   
  	  pueblaDatosEnForm : function(record){
         this.getComponent('areaGeograficaId').setValue(record.id);
         this.getComponent('nombre').setValue(record.get('nombre'));
         this.getComponent('archivoGE').setValue(record.get('archivoGE'));
         this.getComponent('fechaDesde').setValue(record.get('fechaDesde'));
         this.getComponent('fechaHasta').setValue(record.get('fechaHasta'));
  	   },
  	   
  	   pueblaFormEnRegistro : function(record){
  	     record.data['id']=  this.getComponent('areaGeograficaId').getValue();
    	 record.data['nombre']=  this.getComponent('nombre').getValue();
    	 record.data['archivoGE']=  this.getComponent('archivoGE').getValue();
    	 record.data['fechaDesde']=  this.getComponent('fechaDesde').getValue();
    	 record.data['fechaHasta']=  this.getComponent('fechaHasta').getValue();
    	 record.commit();
  	   },  	   
  	   
  	   validaHijo : function(muestraVentana){
  		   var mensaje=null;
  		   var valido=true;
  		   
   		   if (!this.getComponent('nombre').isValid()){
  			   valido=false;
  			   mensaje='El nombre no es válido';
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











