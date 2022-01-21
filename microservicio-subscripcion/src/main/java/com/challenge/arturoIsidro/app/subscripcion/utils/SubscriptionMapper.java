package com.challenge.arturoIsidro.app.subscripcion.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.ListUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.challenge.arturoIsidro.app.subscripcion.dto.SubscripcionDto;
import com.challenge.arturoIsidro.app.subscripcion.models.entity.SubscriptionEntity;

@Component
public class SubscriptionMapper {

	public List<SubscripcionDto> entityListToDtoList(List<SubscriptionEntity> listEntitySubscription) {
		List<SubscripcionDto> listSubscripcionDto = new ArrayList<SubscripcionDto>();
		if (listEntitySubscription.isEmpty()) {
			return new ArrayList<SubscripcionDto>();
		} else {

			for (SubscriptionEntity subscriptionEntity : ListUtils.emptyIfNull(listEntitySubscription)) {
				SubscripcionDto dto = new SubscripcionDto();
				dto.setIdSubscripcion(String.valueOf(subscriptionEntity.getIdSubscripcion()));
				dto.setEmail(subscriptionEntity.getEmail());
				listSubscripcionDto.add(dto);
			}
			return listSubscripcionDto;
		}
	}
	
	public SubscripcionDto entityToDto (SubscriptionEntity entity) {
		SubscripcionDto dto = new SubscripcionDto();
		dto.setIdSubscripcion(String.valueOf(entity.getIdSubscripcion()));
		dto.setEmail(entity.getEmail());
		return dto;
	}
	
	public SubscriptionEntity dtoToEntity(SubscripcionDto dto) {
		SubscriptionEntity subscriptionEntity = new SubscriptionEntity();
		BeanUtils.copyProperties(dto, subscriptionEntity,SubscriptionUtils.getNullPropertyNames(dto));
		return subscriptionEntity;
	}
}
