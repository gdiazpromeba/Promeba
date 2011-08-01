PanelBusquedaAbm = Ext.extend(Ext.form.FormPanel, {
        frame:true,
        //bodyStyle:'padding:5px 5px 0',
        region: 'west',
        collapsible: false,
        //width: 350,
        labelWidth: 150, 
        defaultType: 'textfield',
        prefijo: null,

        initComponent:function() {
    	    var config = {
    	      defaults: {
    	        msgTarget: 'side'
    	      },
            buttons: [
                {text:'Buscar', itemId: 'botBuscar', ref: '../botBuscar',
                  listeners:{'click':  function(){
                    this.fireEvent('buscar pulsado');
                  }, scope: this}},
                {text:'Reinicializar', itemId: 'botReinicializar', ref: 'botReinicializar',
                  listeners:{'click':  function(){
                    this.getForm().reset();
                    this.fireEvent('reinicializar pulsado');
                  }, scope: this}}
            ]
    	    };

            Ext.apply(this, config);
            Ext.apply(this.initialConfig, config);
            
            PanelBusquedaAbm.superclass.initComponent.apply(this, arguments);
            
            this.on('render', this.agregaMapeoTeclas, this);
           
         },
         
         
         
         /**
          * esta función borra los valores de los campos ocultos creados por los combos (si los hay),
          * los cuales el form.reset() parece no ser capaz de borrar
          */
//         borraOcultos : function(){
//        	 Ext.select("div[id=" + this.id + "] input[type=hidden]").each(function(item){
//        		item.dom.value='';
//        	 });
//         },
         
         agregaMapeoTeclas : function(panel){
             var map = new Ext.KeyMap(panel.id, [{key: [10,13], scope: panel, fn: function(){
                panel.botBuscar.fireEvent('click'); //buscar
              }
           }
          ]);
        },         




  
        /**
         * devuelve qué parámetros aporta esta form, en forma de array asociativo [indice][nombre/valor]
         * Por ejemplo, una form que tenga un solo campo de nombre 'apellido' que en ese momento tenga escrito
         * el valor Díaz, devolverá un array así: ((arr[0]['nombre']=>'apellido', arr[0]['valor']=>'Díaz'))
         */
        getParamsBusqueda: function(){
      	  var resultado=new Array();
          var items = this.items;
          for (i=0; i<items.getCount(); ++i) {
              var objRef = items.get(i);
      		  if (Ext.isDefined(objRef.getValue())){
      			  var fila=new Array();
      			  fila['nombre']=objRef.getName();
      			  fila['valor']=objRef.getValue();
      			  resultado.push(fila);
      		  }		
      	  }
      	  return resultado;
        },
        
        /**
         * devuelve una cadena con la fecha formateada como '31/12/2001', o nulo
         * es llamada desde el 'getValue()' de los controles dateField
         */
        formatoFecha : function(date){
        	if (date==''){
        		return '';
        	}else{
        		return date.format('d/m/Y');
        	}
        },

        reinicializar: function(){
          	this.getForm().reset();
        },
        
        /**
         * expone el botón 'Buscar', para que se le pueda asignar externamente un evento
         * que recargue el store
         */
        getBotonBuscar: function(){
        	return this.botBuscar;
        },
        
        /**
         * expone el botón 'Reinicializar', para que se le pueda asignar externamente un evento
         * que recargue el store sin parámetros. También se debe reinicializar el formulario
         * externamente, pues si se hace internamente un form.reset(), no llega a concretarse
         * antes de que el evento externo se cumpla  
         */
        getBotonReinicializar: function(){
        	return this.buttons[1];
        },
        
        /**
         * función utilitaria que le agrega una fila más con par nombre-valor
         * al array de parámetros que se devuelve
         */
        agregaClaveValor : function (arr, nombre, valor){
      	  var fila=new Array();
      	  fila['nombre']=nombre; fila['valor']=valor;
      	  arr.push(fila);        	
        },
        
       /**
        * esta función borra los valores de los campos ocultos creados por los combos (si los hay),
        * los cuales el form.reset() parece no ser capaz de borrar
        */
       borraOcultos : function(){
         Ext.select("div[id=" + this.id + "] input[type=hidden]").each(function(item){
          item.dom.value='';
         });
       }          
  });
