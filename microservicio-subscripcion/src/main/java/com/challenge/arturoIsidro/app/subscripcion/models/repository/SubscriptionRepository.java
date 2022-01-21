package com.challenge.arturoIsidro.app.subscripcion.models.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.challenge.arturoIsidro.app.subscripcion.models.entity.SubscriptionEntity;

public interface SubscriptionRepository extends JpaRepository<SubscriptionEntity, Long> {

	Optional<SubscriptionEntity>findByemail(String email);
}
