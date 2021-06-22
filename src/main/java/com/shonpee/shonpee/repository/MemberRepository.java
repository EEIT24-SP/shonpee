package com.shonpee.shonpee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.shonpee.shonpee.domain.MemberBean;

@Repository
public interface MemberRepository extends JpaRepository<MemberBean, String> {
	@Query(nativeQuery = true,value = "select Password from member where Password = :xxx")
	String QueryAnnotationParams(@Param("xxx") String password);
	}

