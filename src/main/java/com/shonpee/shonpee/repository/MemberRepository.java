package com.shonpee.shonpee.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.shonpee.shonpee.domain.MemberBean;



public interface MemberRepository extends JpaRepository<MemberBean, String>,JpaSpecificationExecutor<MemberBean> {

	@Query(nativeQuery = true,value = "select password from member where password = :xxx")
	String queryAnnotationParams(@Param("xxx") String password);
}
