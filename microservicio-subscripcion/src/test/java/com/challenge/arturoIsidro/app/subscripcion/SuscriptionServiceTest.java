package com.challenge.arturoIsidro.app.subscripcion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.challenge.arturoIsidro.app.subscripcion.dto.SubscripcionDto;
import com.challenge.arturoIsidro.app.subscripcion.exceptions.SubscriptionInternalError;
import com.challenge.arturoIsidro.app.subscripcion.exceptions.SubscriptionNotFound;
import com.challenge.arturoIsidro.app.subscripcion.models.entity.SubscriptionEntity;
import com.challenge.arturoIsidro.app.subscripcion.models.repository.SubscriptionRepository;
import com.challenge.arturoIsidro.app.subscripcion.serviceImp.SubscriptionServiceImpl;
import com.challenge.arturoIsidro.app.subscripcion.utils.SubscriptionMapper;
import com.challenge.arturoIsidro.app.subscripcion.view.SubscriptionView;

@ExtendWith(MockitoExtension.class)
class SuscriptionServiceTest {

	/**
	 * Importante : EL inject mocks, se utiliza para la clase que tiene dependencias
	 * de otras..
	 * 
	 * por ejemplo Userservice ... tiene dependencia de userRepository... por lo
	 * tanto userService seria el injectMock y el mock seria UserRepository
	 * 
	 * para que cuando hagamos la prueba ... se mockea el userRepository
	 * 
	 * when (userRepository.save(entidad).thenReturn(entidad))
	 * 
	 * var servicioCrear =Servicio.crear(entidad);
	 * 
	 * assertEquals(expect,actual )
	 * 
	 * 
	 * 
	 */

	@InjectMocks
	SubscriptionServiceImpl subscrionService = new SubscriptionServiceImpl();
	@Mock
	private SubscriptionRepository subscriptionDAO;

	@Mock
	private SubscriptionMapper subscripcionMapper;

	@Test
	void listarSubscriptionSuccess() {
		SubscriptionView email = new SubscriptionView();
		SubscripcionDto dto = new SubscripcionDto();
		List<SubscripcionDto> lista = new ArrayList<>();
		dto.setName("Arturo");
		dto.setEmail("Art2dit2@hotmail.com");
		dto.setGender("Male");
		lista.add(dto);
		when(subscrionService.findAllSubscription()).thenReturn(lista);
		List<SubscripcionDto> listado = subscrionService.findAllSubscription();
		assertEquals(lista.size(), listado.size());
		System.out.println("Lista " + lista);
		System.out.println("Listado " + listado);
		assertEquals(lista, listado);
	}

	private SubscripcionDto entityDTO(SubscriptionEntity entidad) {
		SubscripcionDto dto = new SubscripcionDto();
		dto.setIdSubscripcion(String.valueOf(entidad.getIdSubscripcion()));
		dto.setName(entidad.getFirstname());
		dto.setBirthDate(entidad.getBirth());
		dto.setEmail(entidad.getEmail());
		dto.setGender(entidad.getGender());
		dto.setNewsletterActivated(entidad.getNewsLetterActivated());
		return dto;
	}

	@Test
	@DisplayName("Error al crear Subscripcion")
	void creacionSubscrionFail() {
		SubscriptionEntity entidad = new SubscriptionEntity();
		entidad.setIdSubscripcion(1L);
		entidad.setFirstname("Arturo");
		entidad.setEmail("Art2dit2@hotmail.com");
		entidad.setGender("Male");
		entidad.setBirth("12/11/1990");
		entidad.setCreateAt(new Date());
		SubscripcionDto dto = entityDTO(entidad);
		when(subscripcionMapper.dtoToEntity(dto)).thenReturn(entidad);
		// Recuerda que cuando se hace un then throw Mockito da preferencia a la
		// excepcion de la propia api
		// antes que a la custom Exception que yo he creado.
		when(subscriptionDAO.save(entidad)).thenThrow(IllegalArgumentException.class);
		try {

			SubscripcionDto dtos = subscrionService.createSubscription(dto);
			assertThrows(IllegalArgumentException.class, () -> {
				subscripcionMapper.entityToDto(entidad);
			});
		} catch (SubscriptionInternalError e) {
			// TODO Auto-generated catch block
			assertEquals("Subscription_002", e.getCodError());
		}

	}

	@Test
	@DisplayName("Creacion de suscripciones")
	void creacionSusbcripcionSuccess() {
		SubscripcionDto dto = new SubscripcionDto();
		SubscripcionDto dto2 = new SubscripcionDto();
		List<SubscripcionDto> lista = new ArrayList<>();
		dto.setName("Arturo");
		dto.setEmail("Art2dit2@hotmail.com");
		dto.setGender("Male");
		dto.setBirthDate("12/11/1990");
		dto.setNewsletterActivated("0");
		SubscriptionEntity entidad = subscripcionMapper.dtoToEntity(dto);
		try {
			when(subscrionService.createSubscription(dto)).thenReturn(dto);
			dto2 = subscrionService.createSubscription(dto);
			assertEquals(dto.getEmail(), dto2.getEmail());
			assertEquals(dto.getName(), dto2.getName());
			verify(subscriptionDAO, times(1)).save(entidad);

		} catch (SubscriptionInternalError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	@DisplayName("Buscar suscripcion por Id")
	void findSubscriptionById() {
		SubscripcionDto dto = new SubscripcionDto();
		dto.setIdSubscripcion("1L");
		dto.setName("Arturo");
		dto.setEmail("Art2dit2@hotmail.com");
		dto.setGender("Male");
		dto.setBirthDate("12/11/1990");
		dto.setNewsletterActivated("0");
		try {
			when(subscrionService.findSubscriptionById(1L)).thenReturn(dto);
			assertEquals("Arturo", dto.getName());
			assertEquals("Art2dit2@hotmail.com", dto.getEmail());
			assertEquals("Male", dto.getGender());
		} catch (SubscriptionNotFound e) {
			System.out.println(e.getMsgError());
		}
	}
	
	@Test
	void findSubscripcionByIdEmpty() {
		try {
		// Arrange
		subscrionService.findSubscriptionById(1L);
		Optional<SubscriptionEntity> optionalEntidad = Optional.empty();
		// Act
		when(subscriptionDAO.findById(1L)).thenReturn(optionalEntidad);
		// Assert		
		} catch (SubscriptionNotFound e) {
			assertEquals("Subscription_001", e.getCodError());
		}

	}

}
