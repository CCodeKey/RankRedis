package redis.com.RankRedis.model.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import redis.com.RankRedis.model.TokenBlackList;

@Repository
public interface TokenBlackListRepository extends CrudRepository<TokenBlackList, String> {}