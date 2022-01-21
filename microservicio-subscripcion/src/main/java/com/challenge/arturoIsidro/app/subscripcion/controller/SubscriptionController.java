package com.challenge.arturoIsidro.app.subscripcion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.arturoIsidro.app.subscripcion.dto.SubscripcionDto;
import com.challenge.arturoIsidro.app.subscripcion.exceptions.SubscriptionBadRequest;
import com.challenge.arturoIsidro.app.subscripcion.exceptions.SubscriptionInternalError;
import com.challenge.arturoIsidro.app.subscripcion.exceptions.SubscriptionNotFound;
import com.challenge.arturoIsidro.app.subscripcion.service.SubscriptionService;
import com.challenge.arturoIsidro.app.subscripcion.view.SubscriptionView;

@RestController
public class SubscriptionController {
	
	@Autowired
	private SubscriptionService subscriptionService;

	@GetMapping("/listsubscription")
	public ResponseEntity<SubscriptionView> listSubscriptions() {
		SubscriptionView infoResult = new SubscriptionView();
		infoResult.setListaSubscripcion(subscriptionService.findAllSubscription());
		infoResult.setCodError("OK");
		return ResponseEntity.ok().body(infoResult);
	}
	
	@GetMapping("/subscription/{idSubscripcion}")
	public ResponseEntity<SubscriptionView> getSubscriptions(@PathVariable("idSubscripcion") Long idSubscripcion) {
		SubscriptionView infoResult = new SubscriptionView();
		try {
			infoResult.getListaSubscripcion().add(subscriptionService.findSubscriptionById(idSubscripcion));
			infoResult.setCodError("OK");
		} catch (SubscriptionNotFound e) {
			infoResult.setCodError(e.getCodError());
			infoResult.setMsgError(e.getMsgError());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(infoResult);
		}
		return ResponseEntity.ok().body(infoResult);
	}
	
	@PostMapping("/subscription/create")
	public ResponseEntity<SubscriptionView> createSubscription(@RequestBody SubscripcionDto dto) {
		SubscriptionView infoResult = new SubscriptionView();
		if(dto !=null) {		
			try {
				subscriptionService.createSubscription(dto);
				infoResult.setListaSubscripcion(subscriptionService.findAllSubscription());
			} catch (SubscriptionInternalError e) {
				infoResult.setCodError(e.getCodError());
				infoResult.setMsgError(e.getMsgError());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(infoResult);	
			}
		}
		return ResponseEntity.ok().body(infoResult);
	}

	@DeleteMapping("/subscription/delete")
	public ResponseEntity<SubscriptionView> deleteSubscription(@RequestBody SubscripcionDto dto) {
		SubscriptionView infoResult = new SubscriptionView();
		try {
			if (dto == null)
				throw new SubscriptionBadRequest("SUBSCRIPTION", "Bad Request");
				subscriptionService.deleteSubscription(Long.parseLong(dto.getIdSubscripcion()));
		} catch (SubscriptionBadRequest e) {
			infoResult.setCodError(e.getCodError());
			infoResult.setMsgError(e.getMsgError());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(infoResult);

		} catch (SubscriptionNotFound e) {
			infoResult.setCodError(e.getCodError());
			infoResult.setMsgError(e.getMsgError());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(infoResult);

		}
		return ResponseEntity.ok().body(infoResult);
	}
	
}
