var dsSubejecutores = new Ext.data.JsonStore({
    url: '/prototipo/svc/conector/subejecutores/selecciona',
    root: 'data',
    fields: ['id', 'nombre']
});


