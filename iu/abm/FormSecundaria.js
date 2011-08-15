FormSecundaria = Ext.extend(Ext.form.FormPanel, {
  constructor : function(config) {
    FormSecundaria.superclass.constructor.call(this, Ext.apply({
  	  frame:true,
      defaultType: 'textfield',
      agregando: false,
      modificando: false,
      exito: false,
      prefijo: null,
      layout: 'border',
      items:[
        {xtype: 'panel', itemId: 'panelCentral', region: 'center', layout: 'form'},
        {xtype: 'panel', itemId: 'panelBotones', region: 'east', width: 80, padding: 5, layout: 'form', defaultType: 'button',
          items: [
            {text: 'Agregar',   width: 70, scope: this, itemId: 'botAgregar',    ref: '../botAgregar',
              listeners:{'click':  function(me){
              	  var panelCentral=me.ownerCt;
              	  var form=panelCentral.ownerCt;
              	  //var form = me.findParentByType(Ext.form.FormPanel);
              	  form.fireEvent('agregar pulsado', form);
                } 
             }
           },
            {text: 'Borrar',   width: 70, scope: this, itemId: 'botBorrar',    ref: '../botBorrar',
              listeners:{'click':  function(me){
              	  var panelCentral=me.ownerCt;
              	  var form=panelCentral.ownerCt;
              	  form.fireEvent('borrar pulsado', form);
                }
              } 
            }
          ]
        }
      ]
     }, config));
     //this.on('render', this.inicioCampos(this), this);
    }, //constructor
    
    generaUUID : function(){
      var result, i, j;
      result = '';
      for(j=0; j<32; j++){
        if( j == 8 || j == 12|| j == 16|| j == 20)
          result = result + '-';
        i = Math.floor(Math.random()*16).toString(16).toUpperCase();
        result = result + i;
      }
      return result
    } 
   
});
