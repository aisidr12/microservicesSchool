package com.challenge.arturoIsidro.app.subscripcion.serviceImp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.challenge.arturoIsidro.app.subscripcion.dto.SubscripcionDto;
import com.challenge.arturoIsidro.app.subscripcion.exceptions.SubscriptionInternalError;
import com.challenge.arturoIsidro.app.subscripcion.exceptions.SubscriptionNotFound;
import com.challenge.arturoIsidro.app.subscripcion.models.entity.SubscriptionEntity;
import com.challenge.arturoIsidro.app.subscripcion.models.repository.SubscriptionRepository;
import com.challenge.arturoIsidro.app.subscripcion.service.SubscriptionService;
import com.challenge.arturoIsidro.app.subscripcion.utils.SubscriptionMapper;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {

	@Autowired
	private SubscriptionRepository subscriptionDAO;
	@Autowired
	private SubscriptionMapper subscripcionMapper;
	
	public SubscriptionServiceImpl() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	@Transactional(readOnly = true)
	public SubscripcionDto findSubscriptionById(Long idSubscription) throws SubscriptionNotFound {
		Optional<SubscriptionEntity>optionalSubscripcion = subscriptionDAO.findById(idSubscription);
		if(optionalSubscripcion.isPresent()) {
			SubscriptionEntity entity = optionalSubscripcion.get();
			return subscripcionMapper.entityToDto(entity);
		}else {
			throw new SubscriptionNotFound("Subscription_001","Subscription not exist");
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<SubscripcionDto> findAllSubscription() {		
		List<SubscripcionDto> listSubscripcionDto  = subscripcionMapper.entityListToDtoList(subscriptionDAO.findAll());
		return listSubscripcionDto;
	}
	


	@Override
	@Transactional(readOnly = true)
	public Optional<SubscriptionEntity> findbyEmail(String email) {
		// TODO Auto-generated method stub
		return subscriptionDAO.findByemail(email);
	}
	
	@Override
	@Transactional
	public SubscripcionDto createSubscription(SubscripcionDto subscripcionDto) 
			throws SubscriptionInternalError {
		SubscriptionEntity entity = new SubscriptionEntity();
		try {
			entity = subscriptionDAO.save(subscripcionMapper.dtoToEntity(subscripcionDto));
		} catch (Exception e) {
			
			throw new SubscriptionInternalError("Subscription_002", "Subscription fail to persist");
		}

		return subscripcionMapper.entityToDto(entity);
	}

	@Override
	@Transactional
	public void deleteSubscription(Long idSubscription) throws SubscriptionNotFound {
		Optional<SubscriptionEntity> optionalSubscripcion = subscriptionDAO.findById(idSubscription);
		if (optionalSubscripcion.isPresent()) {
			subscriptionDAO.deleteById(idSubscription);
		} else {
			throw new SubscriptionNotFound("Subscription_001", "Subscription not exist");
		}

	}


	
}
