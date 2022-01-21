package com.challenge.arturoIsidro.app.subscripcion.view;

import java.util.ArrayList;
import java.util.List;

import com.challenge.arturoIsidro.app.subscripcion.dto.SubscripcionDto;
import com.challenge.arturoIsidro.app.subscripcion.utils.InfoResultView;

public class SubscriptionView extends InfoResultView{
	
	private List<SubscripcionDto>listaSubscripcion = new ArrayList();
	private SubscripcionDto subscripcion;
	
	public List<SubscripcionDto> getListaSubscripcion() {
		return listaSubscripcion;
	}
	public void setListaSubscripcion(List<SubscripcionDto> listaSubscripcion) {
		this.listaSubscripcion = listaSubscripcion;
	}
	public SubscripcionDto getSubscripcion() {
		return subscripcion;
	}
	public void setSubscripcion(SubscripcionDto subscripcion) {
		this.subscripcion = subscripcion;
	}
	
	
	
	
}
