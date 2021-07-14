package com.clinica.services.impl;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.clinica.dao.IConsultaDao;
import com.clinica.dao.IConsultaExamenDao;
import com.clinica.model.Consulta;
import com.clinica.services.IConsultaService;
import com.clinica.util.ConsultaListaExamen;
import com.clinica.util.ConsultaResumen;
import com.clinica.util.FiltroConsulta;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class ConsultaServiceimpl implements IConsultaService{

	@Override
	public Page<Consulta> listAllByPage(Pageable pageable) {
		return dao.findAll(pageable);
	}

	@Autowired 
	private IConsultaExamenDao acDao; 
	@Autowired
	private IConsultaDao dao;
	@Transactional
	@Override
	public Consulta registrar(ConsultaListaExamen dto) {

		Consulta cons = new Consulta();
		try {
			dto.getConsulta().getDetalleConsultas().forEach(d->d.setConsulta(dto.getConsulta()));
			cons = dao.save(dto.getConsulta());
			
		dto.getListaExamenes().forEach(e->acDao.registrar(dto.getConsulta().getIdConsulta(), e.getIdExamen()));
		} catch (Exception e) {

		}
		return cons;
	}

	@Override
	public void modificar(Consulta consulta) {

		dao.save(consulta);
	}

	@Override
	public void eliminar(int idConsulta) {

		dao.delete(idConsulta);
	}

	@Override
	public Consulta listarId(int idConsulta) {
		// TODO Auto-generated method stub
		return dao.findOne(idConsulta);
	}

	@Override
	public List<Consulta> listar() {
		return dao.findAll();
		
	}

	@Override
	public List<Consulta> buscar(FiltroConsulta filtro) {

		return dao.buscar(filtro.getDni(), filtro.getNombreCompleto());
	}

	@Override
	public List<Consulta> buscarfecha(FiltroConsulta filtro) {
		LocalDateTime fechaSgte=filtro.getFechaConsulta().plusDays(1);
		return dao.buscarPorFecha(filtro.getFechaConsulta(),fechaSgte);
	}

	@Override
	public List<ConsultaResumen> listarResumen() {
		List<ConsultaResumen>consulta=new ArrayList<>();
		dao.listarResumen(1).forEach(x -> {
			ConsultaResumen cr=new ConsultaResumen();
			cr.setCantidad(Integer.parseInt(String.valueOf(x[0])));
			cr.setFecha(String.valueOf(x[1]));
			consulta.add(cr);
		});
		return consulta;
	}

	@Override
	public byte[] generarReporte() throws Exception {

		File file=new ClassPathResource("/reports/consultas.jasper").getFile();
		JasperPrint print=JasperFillManager.fillReport(file.getPath(),null,new JRBeanCollectionDataSource(this.listarResumen()));
		
		return JasperExportManager.exportReportToPdf(print);
	}

}
