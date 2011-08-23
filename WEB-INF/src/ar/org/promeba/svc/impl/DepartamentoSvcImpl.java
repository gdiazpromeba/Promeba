package ar.org.promeba.svc.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import ar.org.promeba.beans.Departamento;
import ar.org.promeba.dao.DepartamentoDao;
import ar.org.promeba.svc.DepartamentoSvc;

public class DepartamentoSvcImpl implements DepartamentoSvc {

	@Autowired
	private DepartamentoDao dao;
	
	@Override
	public void borra(String id) {
		dao.borra(id);

	}

	@Override
	public void inserta(Departamento bean) {
		UUID uuid=UUID.randomUUID();
		bean.setId(uuid.toString().substring(0, 32));
		dao.inserta(bean);
	}

	@Override
	public void actualiza(Departamento bean) {
		dao.actualiza(bean);

	}

	@Override
	public List<Departamento> selecciona(String provinciaId) {
		return dao.selecciona(provinciaId);
	}

	@Override
	public int cuenta(String provinciaId) {
		return dao.cuenta(provinciaId);
	}

	@Override
	public Departamento obtiene(String id) {
		return dao.obtiene(id);
	}

}
