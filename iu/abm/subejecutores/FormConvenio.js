FormConvenio = Ext.extend(Ext.Panel, {
  
  constructor : function(config) {
    FormConvenio.superclass.constructor.call(this, Ext.apply({
  		id: 'formConvenio',
  		prefijo: 'formSubejecutores',
  		nombreElementoId: 'subejecutorId',
  	    urlAgregado: '/prototipo/svc/conector/subejecutores/inserta',
  	    urlModificacion: '/prototipo/svc/conector/subejecutores/actualiza',
  	    urlBorrado: '/prototipo/svc/conector/subejecutores/borra',
  	    layout: 'column',
  		items: [
  		  {xtype: 'hidden', name: 'matrizFoto', id: 'matrizFoto', itemId: 'matrizFoto'},
          {fieldLabel: 'Foto', xtype: 'button', text: 'Subir foto', itemId: 'botAceptar', ref: '../botAceptar', 
          listeners: {scope: this,  
            'click' :  function(){
              var win=muestraRemisionFotos('matrizFotoFU', '/produccion/svc/conector/matrices.php/subeFoto');
              win.show();
              win.on("beforedestroy", function(){
                var formulario=Ext.getCmp('formMatrices');
                formulario.getComponent('matrizFoto').setValue(win.getNombreArchivoFoto());
                var colDer=formulario.getComponent('columnaDer');
                colDer.getComponent('foto').el.dom.src='/produccion/recursos/imagenes/' + win.getNombreArchivoFoto();  
                formulario.doLayout();
              });
            }//evento click
           }//listeners
         },//botón Aceptar
         {xtype: 'fieldset', itemId: 'columnaDer', border: true, layout: 'form', columnWidth: 0.4, labelWidth: 0,   
           items:[
             new Ext.Component({itemId: 'foto', autoEl: { tag: 'img'}, height: 150})
           ]
         }         		  
        ],      
      
  	   
  	  pueblaDatosEnForm : function(record){
  	  	 var colIzq=this.getComponent('colIzq');
         this.getComponent('subejecutorId').setValue(record.id);
         colIzq.getComponent('nombre').setValue(record.get('nombre'));
         colIzq.getComponent('calle').setValue(record.get('calle'));
         colIzq.getComponent('numero').setValue(record.get('numero'));
         colIzq.getComponent('piso').setValue(record.get('piso'));
         colIzq.getComponent('departamento').setValue(record.get('departamento'));
         colIzq.getComponent('email').setValue(record.get('email'));
         var cmbCaracter=colIzq.getComponent('comboCaracter');
         cmbCaracter.setRawValue(record.get('caracter'));
         var cmbDepartamentos=colIzq.getComponent('comboDepartamentos');
         // if (record.get('caracter')=='Provincial'){
         	// cmbDepartamentos.clearValue();
         	// cmbDepartamentos.setVisible('false');
         // }else{
         	// cmbDepartamentos.setVisible('true');
         // }
         Ext.get('areaGeograficaId').dom.value=record.get('areaGeograficaId');
         colIzq.getComponent('comboAreasGeograficas').setRawValue(record.get('areaGeograficaNombre'))
         Ext.get('provinciaId').dom.value=record.get('provinciaId');
         colIzq.getComponent('comboProvincias').setRawValue(record.get('provinciaNombre'))
         Ext.get('departamentoId').dom.value=record.get('departamentoId');
         colIzq.getComponent('comboDepartamentos').setRawValue(record.get('departamentoNombre'));
         Ext.get('localidadId').dom.value=record.get('localidadId');
         colIzq.getComponent('comboLocalidades').setRawValue(record.get('localidadNombre'));
         var colDer=this.getComponent('colDer');
         colDer.getComponent('contacto').setValue(record.get('contacto'));
         colDer.getComponent('legitimacion').setValue(record.get('legitimacion'));
  	   },
  	   
  	   pueblaFormEnRegistro : function(record){
  	     record.data['subejecutorId']=  this.getComponent('subejecutorId').getValue();
  	     var colIzq=this.getComponent('colIzq');
  	     record.data['nombre']=  colIzq.getComponent('nombre').getValue();
  	     record.data['calle']=  colIzq.getComponent('calle').getValue();
  	     record.data['numero']=  colIzq.getComponent('numero').getValue();
  	     record.data['piso']=  colIzq.getComponent('piso').getValue();
  	     record.data['departamento']=  colIzq.getComponent('departamento').getValue();
  	     record.data['caracter']=  colIzq.getComponent('comboCaracter').getRawValue();
  	     record.data['email']=  colIzq.getComponent('email').getValue();
  	     record.data['areaGeograficaId']=  Ext.get('areaGeograficaId').dom.value;
  	     record.data['areaGeograficaNombre']=  colIzq.getComponent('comboAreasGeograficas').getRawValue();
  	     record.data['provinciaId']=  Ext.get('provinciaId').dom.value;
  	     record.data['provinciaNombre']=  colIzq.getComponent('comboProvincias').getRawValue();
  	     record.data['departamentoId']=  Ext.get('departamentoId').dom.value;
  	     record.data['departamentoNombre']=  colIzq.getComponent('comboDepartamentos').getRawValue();
    	 record.data['localidadId']=  Ext.get('localidadId').dom.value;
  	     record.data['localidadNombre']=  colIzq.getComponent('comboLocalidades').getRawValue();
  	     var colDer=this.getComponent('colDer');
    	 record.data['contacto']=  colDer.getComponent('contacto').getValue();
    	 record.data['legitimacion']=  colDer.getComponent('legitimacion').getValue();
  	     
  		 record.commit();
  	   },  	   
  	   
  	   validaHijo : function(muestraVentana){
  		   var mensaje=null;
  		   var valido=true;
  		   
  		   var colIzq=this.getComponent('colIzq');
   		   if (!colIzq.getComponent('nombre').isValid()){
  			   valido=false;
  			   mensaje='El nombre no es válido';
  		   }
  		   
  	  	   if (!colIzq.getComponent('comboCaracter').isValid()){
  			   valido=false;
  			   mensaje='El carácter no es válido';
  		   }
  		   
 	       var colDer=this.getComponent('colDer');
   		   if (!colDer.getComponent('legitimacion').isValid()){
  			   valido=false;
  			   mensaje='la fecha de legitimación no es válida';
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











