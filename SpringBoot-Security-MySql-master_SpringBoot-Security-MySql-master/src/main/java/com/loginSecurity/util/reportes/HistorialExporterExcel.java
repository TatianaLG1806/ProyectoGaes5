package com.loginSecurity.util.reportes;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.loginSecurity.entity.Historial;


public class HistorialExporterExcel {
	private XSSFWorkbook libro;
	private XSSFSheet hoja;

	private List<Historial> listaProveedores;

	public HistorialExporterExcel(List<Historial> listaProveedores) {
		
		this.listaProveedores = listaProveedores;
		
		libro = new XSSFWorkbook();
		hoja = libro.createSheet("Historiales");
		
		
	}

	
	private void escribirCabeceraDeTabla() {
		Row fila = hoja.createRow(0);
		
		CellStyle estilo = libro.createCellStyle();
		XSSFFont fuente = libro.createFont();
		fuente.setBold(true);
		fuente.setFontHeight(16);
		estilo.setFont(fuente);
		
		Cell celda = fila.createCell(0);
		celda.setCellValue("ID");
		celda.setCellStyle(estilo);
		
		celda = fila.createCell(1);
		celda.setCellValue("Producto Adquirido");
		celda.setCellStyle(estilo);
		
		
		celda = fila.createCell(2);
		celda.setCellValue("Valor");
		celda.setCellStyle(estilo);
		
		
	}
	
	private void escribirDatosDeLaTabla() {
		int numeroFilas = 1;
		
		CellStyle estilo = libro.createCellStyle();
		XSSFFont fuente = libro.createFont();
		fuente.setFontHeight(14);
		estilo.setFont(fuente);
		
		for(Historial proveedor : listaProveedores) {
			Row fila = hoja.createRow(numeroFilas ++);
			
			Cell celda = fila.createCell(0);
			celda.setCellValue(proveedor.getId());
			hoja.autoSizeColumn(0);
			celda.setCellStyle(estilo);
			
			celda = fila.createCell(1);
			celda.setCellValue(proveedor.getProductoadquirido());
			hoja.autoSizeColumn(1);
			celda.setCellStyle(estilo);
			
			celda = fila.createCell(2);
			celda.setCellValue(proveedor.getValor());
			hoja.autoSizeColumn(2);
			celda.setCellStyle(estilo);
			
		
			
			
		}
	}
	
	public void exportar(HttpServletResponse response) throws IOException {
		escribirCabeceraDeTabla();
		escribirDatosDeLaTabla();
		
		
		ServletOutputStream outPutStream = response.getOutputStream();
		libro.write(outPutStream);
		
		libro.close();
		outPutStream.close();
		
		
		
	}
}
