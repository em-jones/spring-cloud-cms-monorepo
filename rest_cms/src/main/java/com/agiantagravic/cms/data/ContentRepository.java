package com.agiantagravic.cms.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContentRepository extends JpaRepository<Content, Long> {

//    Content findByUtmCode(String utmCode);
}

