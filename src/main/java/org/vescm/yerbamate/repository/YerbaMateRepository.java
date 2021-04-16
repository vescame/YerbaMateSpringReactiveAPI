package org.vescm.yerbamate.repository;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import org.vescm.yerbamate.document.YerbaMate;

@EnableScan
public interface YerbaMateRepository extends CrudRepository<YerbaMate, String> {
}
