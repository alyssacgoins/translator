package com.multi_input.translator.repository;

import com.multi_input.translator.Translation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Stores user and their translations
 */
@Repository
public interface TranslationRepository extends JpaRepository<Translation, Long> {

}
