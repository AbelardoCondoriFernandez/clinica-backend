package com.clinica.services.impl;


import com.clinica.dao.IConsultaExamenmDao;
import com.clinica.dao.IConsultamDao;
import com.clinica.model.Consultam;
import com.clinica.services.IConsultamService;
import com.clinica.util.ConsultaListaExamenm;
import com.clinica.util.ConsultamResumen;
import com.clinica.util.FiltroConsulta;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ConsultamServiceimpl implements IConsultamService {

	@Override
	public Page<Consultam> listAllByPage(Pageable pageable) {
		return dao.findAll(pageable);
	}

	@Autowired 
	private IConsultaExamenmDao acDao;
	@Autowired
	private IConsultamDao dao;
	@Transactional
	@Override
	public Consultam registrar(ConsultaListaExamenm dto) {

		Consultam cons = new Consultam();
		try {
			cons=dao.save(dto.getConsultam());
		dto.getListaExamenes().forEach(e->acDao.registrar(dto.getConsultam().getIdConsultam(), e.getIdExamen()));
		} catch (Exception e) {

		}
		return cons;
	}

	@Override
	public void modificar(Consultam consultam) {

		dao.save(consultam);
	}

	@Override
	public void eliminar(int idConsultam) {

		dao.delete(idConsultam);
	}

	@Override
	public Consultam listarId(int idConsultam) {
		// TODO Auto-generated method stub
		return dao.findOne(idConsultam);
	}

	@Override
	public List<Consultam> listar() {
		return dao.findAll();
		
	}

	@Override
	public List<Consultam> buscar(FiltroConsulta filtro) {

		return dao.buscar(filtro.getDni(), filtro.getNombreCompleto());
	}

	@Override
	public List<Consultam> buscarfecha(FiltroConsulta filtro) {
		LocalDateTime fechaSgte=filtro.getFechaConsulta().plusDays(1);
		return dao.buscarPorFecha(filtro.getFechaConsulta(),fechaSgte);
	}

	@Override
	public List<ConsultamResumen> listarResumen() {
		List<ConsultamResumen>consultam=new ArrayList<>();
		dao.listarResumen(1).forEach(x -> {
			ConsultamResumen cr=new ConsultamResumen();
			cr.setCantidad(Integer.parseInt(String.valueOf(x[0])));
			cr.setFecha(String.valueOf(x[1]));
			consultam.add(cr);
		});
		return consultam;
	}

	@Override
	public byte[] generarReporte() throws Exception {

		File file=new ClassPathResource("/reports/consultas.jasper").getFile();
		JasperPrint print=JasperFillManager.fillReport(file.getPath(),null,new JRBeanCollectionDataSource(this.listarResumen()));
		
		return JasperExportManager.exportReportToPdf(print);
	}

}
