var dsLocalidades = new Ext.data.JsonStore({
    url: '/prototipo/svc/conector/geografia/seleccionaLocalidades',
    root: 'data',
    fields: ['id', 'nombre']
});


