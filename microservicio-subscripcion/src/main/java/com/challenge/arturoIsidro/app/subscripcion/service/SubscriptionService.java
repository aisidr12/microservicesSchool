package com.challenge.arturoIsidro.app.subscripcion.service;

import java.util.List;
import java.util.Optional;

import com.challenge.arturoIsidro.app.subscripcion.dto.SubscripcionDto;
import com.challenge.arturoIsidro.app.subscripcion.exceptions.SubscriptionInternalError;
import com.challenge.arturoIsidro.app.subscripcion.exceptions.SubscriptionNotFound;
import com.challenge.arturoIsidro.app.subscripcion.models.entity.SubscriptionEntity;

public interface SubscriptionService {

	void deleteSubscription(Long idSubscription) throws SubscriptionNotFound;

	SubscripcionDto findSubscriptionById(Long idSubscription) throws SubscriptionNotFound;

	List<SubscripcionDto> findAllSubscription();

	Optional<SubscriptionEntity> findbyEmail(String email);

	SubscripcionDto createSubscription(SubscripcionDto subscripcionDto) throws SubscriptionInternalError;

}
