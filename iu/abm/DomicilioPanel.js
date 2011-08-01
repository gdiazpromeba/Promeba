DomicilioPanel = Ext.extend(Ext.Panel, {
  
  constructor : function(config) {
    DomicilioPanel.superclass.constructor.call(this, Ext.apply({
  	    layout: 'column',
  	    prefijo: null,
  		items: [
          {xtype: 'hidden', name: config.prefijo + 'domicilioId', id: config.prefijo + 'domicilioId', itemId: 'domicilioId'},
          {xtype: 'fieldset', itemId : 'colIzq', columnWidth: 0.5, layout: 'form', border: false, 
           items: [
            {fieldLabel: 'Calle',  xtype: 'textfield', itemId: 'calle', name: config.prefijo + 'calle', allowBlank: true, width: 180},
            {fieldLabel: 'Número',  xtype: 'numberfield', itemId: 'numero', name: config.prefijo +'numero', width: 80, allowBlank: true},
            {fieldLabel: 'Piso',  xtype: 'textfield', itemId: 'piso', name: config.prefijo +'piso', width: 80, align: 'right', allowBlank: true},
            {fieldLabel: 'Depto/Nro',  xtype: 'textfield', itemId: 'departamento', name: config.prefijo +'departamento', width: 80, allowBlank: true},
            {xtype: 'comboregiones', itemId: 'comboRegiones', name: config.prefijo +'comboRegiones', hiddenName: config.prefijo +'regionId', 
               hiddenId: config.prefijo +'regionId', allowBlank: false ,
               listeners: {scope: this,  
                 'select' :  function(combo, value){
                   var ventana=this;
                   var cmbProvincias=ventana.getComponent('colIzq').getComponent('comboProvincias');
                   var regionId = Ext.get(ventana.prefijo +'regionId').dom.value;
                   cmbProvincias.clearValue();
                   var strProvincias=cmbProvincias.getStore();
                   strProvincias.reload({
              		params : {
              		  regionId: regionId
              		 }
              	   });
                 },
                 'render' : function(combo){
                 	combo.getStore().reload();            
                 }//de la función
               }//del listener
            },             
            {xtype: 'comboprovincias', itemId: 'comboProvincias', name: config.prefijo +'comboProvincias', hiddenName: config.prefijo +'provinciaId', 
              hiddenId: config.prefijo +'provinciaId', allowBlank: false , valueField: 'id', displayField: 'nombre',
               listeners: {scope: this,  
                 'select' :  function(combo, record, index){
                   var ventana=this;
                   var cmbDepartamentos=ventana.getComponent('colIzq').getComponent('comboDepartamentos');
                   var provinciaId = Ext.get(ventana.prefijo +'provinciaId').dom.value;
                   cmbDepartamentos.clearValue();
                   var strDepartamentos=cmbDepartamentos.getStore();
                   strDepartamentos.reload({
              		  params : {
              		    provinciaId: provinciaId
              		  }
              	   });
                 }//de la función
               }//del listener
            },
            {xtype: 'combodepartamentos', itemId: 'comboDepartamentos', name: config.prefijo +'comboDepartamentos', hiddenName: config.prefijo +'departamentoId', 
              hiddenId: config.prefijo +'departamentoId', allowBlank: true ,
               listeners: {scope: this,  
                 'select' :  function(combo, record, index){
                   var ventana=this;
                   var cmbLocalidades=ventana.getComponent('colIzq').getComponent('comboLocalidades');
                   var departamentoId = Ext.get(ventana.prefijo +'departamentoId').dom.value;
                   cmbLocalidades.clearValue();
                   var strLocalidades=cmbLocalidades.getStore();
                   strLocalidades.reload({
              		  params : {
              		    departamentoId: departamentoId
              		  }
              	   });
                 }//de la función
               }//del listener
            },
            {xtype: 'combolocalidades', itemId: 'comboLocalidades', name: config.prefijo +'comboLocalidades', hiddenName: config.prefijo +'localidadId', hiddenId: config.prefijo +'localidadId', allowBlank: true }
           ]
          },//del fieldset 'colIzq'
          {xtype: 'fieldset', itemId : 'colDer', columnWidth: 0.5, layout: 'form', border: false,
            items: [
              {fieldLabel: 'Código Postal',  xtype: 'textfield', itemId: 'codigoPostal', name: config.prefijo +'codigoPostal', allowBlank: true},
              {fieldLabel: 'Barrio',  itemId: 'barrio', name: config.prefijo +'barrio', xtype : 'textfield', allowBlank : true},
              {fieldLabel: 'Manzana',  itemId: 'manzana', name: config.prefijo +'manzana', xtype : 'textfield', allowBlank : true},
              {fieldLabel: 'Sector',  itemId: 'sector', name: config.prefijo +'sector', xtype : 'textfield', allowBlank : true},
              {fieldLabel: 'Tipo', xtype: 'combo', id: 'comboTipoDomicilio', name: config.prefijo +'comboTipoDomicilio', itemId: 'comboTipoDomicilio', width: 110, ref: '../comboTipoDomicilio', allowBlank: true, 
	            store: new Ext.data.SimpleStore({
	              fields: ['tipo'],
	    	        data: [["Particular"],["Postal"],["Laboral"],["Legal"],["Otro"]]
	    	      }),
                  displayField: 'tipo', valueField: 'tipo', selectOnFocus: true, mode: 'local', typeAhead: false, editable: false, allowBlank: true,
              } 
             ]
          }//del fieldset 'colDer'
      ],     
      
      getDomicilioId : function (me){
      	return me.getComponent('domicilioId');
      },
      
      getCalle : function (me){
      	return me.getComponent('colIzq').get('calle');
      },
      
      getNumero : function (me){
      	return me.getComponent('colIzq').get('numero');
      }, 
      
      getPiso : function (me){
      	return me.getComponent('colIzq').get('piso');
      },      
      
      getDepartamento : function (me){
      	return me.getComponent('colIzq').get('departamento');
      },      
       
      getTipo : function (me){
      	return me.getComponent('colDer').get('comboTipoDomicilio');
      },
      
      getProvincia : function (me){
      	return me.getComponent('colIzq').get('comboProvincias');
      },
      
      getProvinciaId : function(me){
      	return Ext.get(me.prefijo +'provinciaId').dom.value;
      },
      
      setProvinciaId : function(me, valor){
      	Ext.get(me.prefijo +'provinciaId').dom.value=valor;
      },
      
      getRegion : function (me){
      	return me.getComponent('colIzq').get('comboRegiones');
      },
      
      getRegionId : function(me){
      	return Ext.get(me.prefijo +'regionId').dom.value;
      },
      
      setRegionId : function(me, valor){
      	Ext.get(me.prefijo +'regionId').dom.value=valor;
      },
      
      
      getDepartamento : function (me){
      	return me.getComponent('colIzq').get('comboDepartamentos');
      },
      
      setDepartamentoId : function(me, valor){
      	Ext.get(me.prefijo +'departamentoId').dom.value=valor;
      },
   
      
      getDepartamentoId : function(me){
      	return Ext.get(me.prefijo +'departamentoId').dom.value;
      },
         
            
      getLocalidad : function (me){
      	return me.getComponent('colIzq').get('comboLocalidades');
      },
      
      getLocalidadId : function(me){
      	return Ext.get(me.prefijo +'localidadId').dom.value;
      },      
      
      setLocalidadId : function(me, valor){
      	Ext.get(me.prefijo +'localidadId').dom.value=valor;
      },
      
      
      getDepartamentoId : function(me){
      	return Ext.get(me.prefijo +'departamentoId').dom.value;
      },
            
      
      getCodigoPostal : function (me){
      	return me.getComponent('colDer').get('codigoPostal');
      },      
            
      getBarrio : function (me){
      	return me.getComponent('colDer').get('barrio');
      },      
      
      getManzana : function (me){
      	return me.getComponent('colDer').get('manzana');
      },      
              
      getSector : function (me){
      	return me.getComponent('colDer').get('sector');
      },      
                            

	  controlesARegistro : function (me, record){
    	 record.data['domicilioId'] = me.getDomicilioId(me).getValue();
    	 record.data['calle']=  me.getCalle(me).getValue();
    	 record.data['numero']=  me.getNumero(me).getValue();
    	 record.data['piso']=  me.getPiso(me).getValue();
    	 record.data['departamento']=  me.getDepartamento(me).getValue();
    	 record.data['tipoDomicilio']=  me.getTipo(me).getValue();
         record.data['regionNombre']= me.getRegion(me).getRawValue();
    	 record.data['regionId']= me.getRegionId(me);
         record.data['provinciaNombre']= me.getProvincia(me).getRawValue();
    	 record.data['provinciaId']= me.getProvinciaId(me);
    	 record.data['departamentoNombre']= me.getDepartamento(me).getRawValue();
    	 record.data['departamentoId']= me.getDepartamentoId(me);
      	 record.data['localidadNombre']= me.getLocalidad(me).getRawValue();
    	 record.data['localidadId']= me.getLocalidadId(me);
    	 record.data['codigoPostal']=  me.getCodigoPostal(me).getValue();
    	 record.data['barrio']=  me.getBarrio(me).getValue();
    	 record.data['manzana']=  me.getManzana(me).getValue();
    	 record.data['sector']=  me.getSector(me).getValue();
	  },
	  
	  registroAControles : function(me, record){
         me.getDomicilioId(me).setValue(record.get('domicilioId'));
         me.getCalle(me).setValue(record.get('calle'));
         me.getNumero(me).setValue(record.get('numero'));
         me.getPiso(me).setValue(record.get('piso'));
         me.getDepartamento(me).setValue(record.get('departamento'));
         me.getTipo(me).setValue(record.get('tipoDomicilio'));
         me.getCodigoPostal(me).setValue(record.get('codigoPostal'));
         me.getCodigoPostal(me).setValue(record.get('codigoPostal'));
         me.getManzana(me).setValue(record.get('manzana'));
         me.getBarrio(me).setValue(record.get('barrio'));
         me.getSector(me).setValue(record.get('sector'));
         me.getRegion(me).setRawValue(record.get('regionNombre'));
         me.setRegionId(me, record.get('regionId'));
         me.getProvincia(me).setRawValue(record.get('provinciaNombre'));
         me.setProvinciaId(me, record.get('provinciaId'));
         me.getDepartamento(me).setRawValue(record.get('departamentoNombre'));
         me.setDepartamentoId(me, record.get('departamentoId'));
         me.getLocalidad(me).setRawValue(record.get('localidadNombre'));
         me.setLocalidadId(me, record.get('localidadId'));
	  }
  	  
	}, config));
	
  } //constructor
  
  
  
});

Ext.reg('domiciliopanel', DomicilioPanel);











