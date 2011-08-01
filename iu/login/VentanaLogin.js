VentanaLogin = Ext.extend(Ext.Window, {
	constructor : function(config) {
	  var cmbRolesAsociados;
	  var acordeon;
	  VentanaLogin.superclass.constructor.call(this, Ext.apply({
        title: 'Ingreso',
        width: 375,
        height: 145,
        //layout: 'fit',
        layout: 'table',
        layoutConfig: {columns: 2},
        closable: false,
        plain: true,
        modal: true,
        padding: 5,
        items: [
            {xtype: 'panel', width: 251, layout: 'form', border: false,  unstyled: true,
             labelAlign: 'right', itemId: 'panTextoUsuario', padding: 5,
             items: [
               {xtype: 'textfield', fieldLabel: 'Usuario', itemId: 'login', value: 'cgolpe'}                           
             ]
            },
            {xtype: 'panel', width: 81, height: 25, itemId: 'panRelleno1', border: false, unstyled: true,},
            {xtype: 'panel', layout: 'form', labelAlign: 'right', 
             padding: 5,  id: 'panTextoClave', border: false, unstyled: true,
             items: [
               {xtype: 'textfield', itemId: 'clave', fieldLabel: 'Clave', inputType: 'password', value: 'cgolpe'}
             ]
            },
            {xtype: 'panel', itemId: 'panBotonValidar', padding: 5, border: false,  unstyled: true,
                items: [
                    {xtype: 'button', text: 'Validar', itemId: 'botValidar', ref: '../../botAceptar', width: 60, listeners: {scope: this,  
                    	'click' :  function(){
                          var ventana=this;
                          var cmbRoles=ventana.cmbRolesAsociados;
                          Ext.Ajax.request({
                            url:  '/prototipo/svc/conector/usuarios/verifica',
                            method: 'POST',
                            params: {
                              login : ventana.getComponent('panTextoUsuario').getComponent('login').getValue(),  
                              clave : ventana.getComponent('panTextoClave').getComponent('clave').getValue()
                            },
                            failure: function (response, options) {
                              Ext.Msg.show({ title:'Ingreso', msg: 'Error de red o base de datos', buttons: Ext.Msg.OK});
                              cmbRoles.setDisabled(true);
                              cmbRoles.clearValue();
                            },
                            success: function (response, options) {
                              var objRespuesta=Ext.util.JSON.decode(response.responseText);
                              if (objRespuesta.success || debug){
                              	cmbRoles.setDisabled(false);
                              	cmbRoles.clearValue();
                              	var strRoles=cmbRoles.getStore();
                              	strRoles.reload({
                              		params : {
                              		  valorIdPadre: objRespuesta.usuarioId
                              		 }
                              	});
                              }else{
                                Ext.Msg.show({ title:'Ingreso', msg: 'Usuario o clave incorrectos', buttons: Ext.Msg.OK});
                                ventana.cmbRolesAsociados.setDisabled(true);
                                cmbRoles.clearValue();
                              }
                            }
                          });
                        }}
                    }
                ]
            },
            {xtype: 'panel', layout: 'form', labelAlign: 'right', padding: 5, itemId: 'panComboRoles', border: false, unstyled: true},
            {xtype: 'panel', itemId: 'panBotonIngresar', padding: 5, border: false, unstyled: true, 
                items: [
                    {xtype: 'button', text: 'Ingresar', width: 60, listeners: {scope: this,  
                    	'click' :  function(){
                          var ventana=this;
                          var cmbRoles=ventana.cmbRolesAsociados;
                          Ext.Ajax.request({
                            url:  '/prototipo/svc/conector/roles/areasFuncionalesXRolVisibles',
                            method: 'POST',
                            params: {
                              start: 0,
                              limit: 100,
                              rolId : Ext.get('rolAsociadoId').dom.value  
                            },
                            failure: function (response, options) {
                              Ext.Msg.show({ title:'Ingreso', msg: 'Error de red o base de datos', buttons: Ext.Msg.OK});
                              cmbRoles.setDisabled(true);
                              cmbRoles.clearValue();
                            },
                            success: function (response, options) {
                              var objRespuesta=Ext.util.JSON.decode(response.responseText);
                              var acordeon=Ext.getCmp('menuAcordeonId');
                              acordeon.evaluaOcultamiento(objRespuesta.data);
                              acordeon.setVisible(true);
                              ventana.destroy();
                            }
                          });
                        }}
                    }
                ]
            }
        ],
        listeners: {
          /**
           * crea el combo de roles asociados al comienzo durante la instanciación
           * y lo agrega al panel correspondiente 
           */
          render: function(me) {
            var panComboRoles=me.getComponent('panComboRoles');
            me.cmbRolesAsociados=me.creaComboRoles();            
            panComboRoles.add(me.cmbRolesAsociados);
          }
        } 
   }))
  }, //constructor 

  /**
   * crea el combo asociado de roles
   */
  creaComboRoles : function(){
  	  var storeRolesDep =  new Ext.data.JsonStore({
        url: '/prototipo/svc/conector/usuarios/seleccionaRoles',
        baseParams : {start: 0, limit: 100},
        root: 'data',
        fields: ['rolId', 'rolNombre']
      });
	  var cmbRolesDep = new Ext.form.ComboBox({  
        store: storeRolesDep,  
        disabled: true,     //Step 1  
        id: 'rolId',  
        valueField: 'rolId',  
        displayField: 'rolNombre',  
        hiddenId: 'rolAsociadoId',
        hiddenName: 'rolAsociadoId',
        triggerAction: 'all',  
        mode: 'local',      //Step 2  
        emptyText: '-- Roles asociados --',  
        fieldLabel: 'Rol'  
      }); 	
      return cmbRolesDep;
  }
});

